package com.feitai.common.id;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IdTable {
	private static final Log log = LogFactory.getLog(IdTable.class);
	private DataSource dataSource;
	private String tableName;
	private String idColName;
	private String typeColName;
	private String rangeColName;
	private String _insert;
	private String _update;
	private String _update1;
	private String _select;

	public void setTableName(String tname) {
		this.tableName = tname;
	}

	public void setIdColName(String idName) {
		this.idColName = idName;
	}

	public void setTypeColName(String typeName) {
		this.typeColName = typeName;
	}

	public void setRangeColName(String rangeName) {
		this.rangeColName = rangeName;
	}

	public void setDataSource(DataSource src) {
		this.dataSource = src;
	}

	public void init() {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = this.dataSource.getConnection();
			stmt = conn.createStatement();
			String createSQL = "create table "
					+ this.tableName
					+ "("
					+ this.typeColName
					+ " char(2) not null, "
					+ ((this.rangeColName != null) ? this.rangeColName
							+ " char(10) not null, " : "")
					+ this.idColName
					+ " bigint, primary key("
					+ this.typeColName
					+ ((this.rangeColName != null) ? ", " + this.rangeColName
							: "") + "))";
			stmt.execute(createSQL);
			stmt.close();
		} catch (SQLException e1) {
			if(log.isDebugEnabled()){
				log.debug("Table '"+this.tableName+"' is exists");
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e2) {
				log.error(e2);
			}
		}
	}

	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}

	public long doSelect(Connection conn, String type, String range)
			throws SQLException {
		if ((range != null) && (this.rangeColName == null))
			throw new IllegalArgumentException("you must define rangeColName.");
		if ((range == null) && (this.rangeColName != null)) {
			throw new IllegalArgumentException("you must input range value.");
		}
		long result = -1L;
		PreparedStatement qps = conn.prepareStatement(getSelectSql());
		try {
			qps.setString(1, type);
			if (range != null) {
				qps.setString(2, range);
			}
			ResultSet rs = qps.executeQuery();
			if (rs.next())
				result = rs.getLong(1);
			rs.close();
		} catch (SQLException sqle) {
			log.error("could not read a id value!", sqle);
			throw sqle;
		} finally {
			qps.close();
		}
		return result;
	}

	public void doInsert(Connection conn, String type, String range, long start)
			throws SQLException {
		if ((range != null) && (this.rangeColName == null))
			throw new IllegalArgumentException("you must define rangeColName.");
		if ((range == null) && (this.rangeColName != null)) {
			throw new IllegalArgumentException("you must input range value.");
		}
		PreparedStatement ups = conn.prepareStatement(getInsertSql());
		try {
			ups.setString(1, type);
			ups.setLong(2, start);
			if (range != null)
				ups.setString(3, range);
			int rows = ups.executeUpdate();
			if (rows != 1)
				throw new SQLException("IdTable insert fail.");
		} catch (SQLException sqle) {
			log.error("could not insert id value for initialize!", sqle);
			throw sqle;
		} finally {
			ups.close();
		}
	}

	public int doUpdate(Connection conn, String type, String range, long seq,int count) throws SQLException {
		if (range != null && rangeColName == null)
			throw new IllegalArgumentException("you must define rangeColName.");
		if (range == null && rangeColName != null)
			throw new IllegalArgumentException("you must input range value.");
		PreparedStatement ups = conn.prepareStatement(getUpdateSql());
		try {
			long next = seq + (long) count;
			if (next < 0L)
				next = 0L;
			ups.setLong(1, next);
			ups.setString(2, type);
			ups.setLong(3, seq);
			if (range != null)
				ups.setString(4, range);
			int i = ups.executeUpdate();
			return i;
		} catch (SQLException sqle) {
			log.error("could not update id value!", sqle);
			throw sqle;
		} finally {
			ups.close();
		}
	}

	public int doUpdate(Connection conn, String type, String range, int count)
			throws SQLException {
		if (range != null && rangeColName == null)
			throw new IllegalArgumentException("you must define rangeColName.");
		if (range == null && rangeColName != null)
			throw new IllegalArgumentException("you must input range value.");
		PreparedStatement ups = conn.prepareStatement(getUpdateSql1());
		try {
			ups.setLong(1, count);
			ups.setString(2, type);
			if (range != null)
				ups.setString(3, range);
			int i = ups.executeUpdate();
			return i;
		} catch (SQLException sqle) {
			log.error("could not update id value!", sqle);
			throw sqle;
		} finally {
			ups.close();
		}
	}

	private String getSelectSql() {
		if (this._select == null)
			this._select = "select "
					+ this.idColName
					+ " from "
					+ this.tableName
					+ " where "
					+ this.typeColName
					+ "=?"
					+ ((this.rangeColName != null) ? " and "
							+ this.rangeColName + "=?" : "");
		return this._select;
	}

	private String getUpdateSql() {
		if (this._update == null)
			this._update = "update "
					+ this.tableName
					+ " set "
					+ this.idColName
					+ "=? where "
					+ this.typeColName
					+ "=? and "
					+ this.idColName
					+ "=?"
					+ ((this.rangeColName != null) ? " and "
							+ this.rangeColName + "=?" : "");
		return this._update;
	}

	private String getUpdateSql1() {
		if (this._update1 == null)
			this._update1 = "update "
					+ this.tableName
					+ " set "
					+ this.idColName
					+ "="
					+ this.idColName
					+ "+? where "
					+ this.typeColName
					+ "=?"
					+ ((this.rangeColName != null) ? " and "
							+ this.rangeColName + "=?" : "");
		return this._update1;
	}

	private String getInsertSql() {
		if (this._insert == null)
			this._insert = "insert into "
					+ this.tableName
					+ " ("
					+ this.typeColName
					+ ","
					+ this.idColName
					+ ((this.rangeColName != null) ? "," + this.rangeColName
							: "") + ") values (?,?"
					+ ((this.rangeColName != null) ? ",?" : "") + ")";
		return this._insert;
	}
}