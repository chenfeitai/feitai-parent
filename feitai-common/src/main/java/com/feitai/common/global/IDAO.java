package com.feitai.common.global;

import java.io.Serializable;

/**
 * <B>DAO层基本接口</B>
 * @author chenzg
 * @since 2015-07-23
 */
public interface IDAO<T> {
	public T get(Serializable id);
	
	public int insert(T eobj);
	
	public int delete(T eobj);

	public int update(T eobj);
}
