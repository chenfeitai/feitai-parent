package com.feitai.common.global;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.feitai.common.page.Page;

/**
 * <B>实体对象</B>
 * @author henry
 * @since 2013-11-17
 */
public class EObject implements Serializable{
	private static final long serialVersionUID = -1714625295587582013L;
	/** 内部序列号 */
	protected String id;
	/** 创建人内部序列号 */
	protected String createBy;
	/** 创建人名称 */
	protected String createByName;
	/** 创建时间 */
	protected Date createTime;
	/** 更新人内部序列号 */
	protected String updateBy;
	/** 更新人名称 */
	protected String updateByName;
	/** 更新时间 */
	protected Date updateTime;
	/** 开始时间 */
	private Date startTime;
	/** 结束时间 */
	private Date endTime;
	
	/**
	 * <B>获得内部序列号</B>
	 * @return {@link java.lang.String}
	 */
	public String getId() {
		return id;
	}
	
	/** 
	 * <B>设置内部序列号</B>
	 * @param id {@link java.lang.String}
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * <B>获得创建人内部序列号</B>
	 * @return {@link java.lang.String}
	 */
	public String getCreateBy() {
		return createBy;
	}
	
	/** 
	 * <B>设置创建人内部序列号</B>
	 * @param createBy {@link java.lang.String}
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	/**
	 * <B>获得创建人名称</B>
	 * @return {@link java.lang.String}
	 */
	public String getCreateByName() {
		return createByName;
	}
	
	/** 
	 * <B>设置创建人名称</B>
	 * @param createByName {@link java.lang.String}
	 */
	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
	
	/**
	 * <B>获得创建时间</B>
	 * @return {@link java.sql.Date}
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/** 
	 * <B>设置创建时间</B>
	 * @param createTime {@link java.sql.Date}
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * <B>获得更新人内部序列号</B>
	 * @return {@link java.lang.String}
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	
	/** 
	 * <B>设置更新人内部序列号</B>
	 * @param updateBy {@link java.lang.String}
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	/**
	 * <B>获得更新人名称</B>
	 * @return {@link java.lang.String}
	 */
	public String getUpdateByName() {
		return updateByName;
	}
	
	/** 
	 * <B>设置更新人名称</B>
	 * @param updateByName {@link java.lang.String}
	 */
	public void setUpdateByName(String updateByName) {
		this.updateByName = updateByName;
	}
	
	/**
	 * <B>获得更新时间</B>
	 * @return {@link java.sql.Date}
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	
	/** 
	 * <B>设置更新时间</B>
	 * @param updateTime {@link java.sql.Date}
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
