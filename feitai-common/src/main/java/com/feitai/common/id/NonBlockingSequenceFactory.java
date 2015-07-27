package com.feitai.common.id;

import java.sql.Connection;
import java.sql.SQLException;

public class NonBlockingSequenceFactory extends AbstractSequenceFactory {

	@Override
	protected synchronized long internalGenerate(String range,int count) {
		try {
			Connection conn = this.table.getConnection();
			try {
				long result = -1L;
				int rows = 0 ;
				do{
					result = this.table.doSelect(conn, type, range);
					if (result == -1L) {
						result = 0L;
						table.doInsert(conn, type , range, result);
					}
					if(result + (long)count < 0L){
						result = 0L;
					}
					rows = table.doUpdate(conn, type, range, result, count);
				}while(rows == 0);
				long l = result + 1L;
				return l;
			}finally{
				conn.close();
			}
		} catch (SQLException e) {
			log.error("internalGenerate is error",e);
		}
		return 0L;
	}
}