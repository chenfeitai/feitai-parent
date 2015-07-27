package com.feitai.common.core;

import com.alibaba.druid.pool.DruidDataSource;
import com.feitai.common.util.DESHelper;

/**
 * <b>数据源 password解密,有更好的解决方案,见EncryptPropertyPlaceholderConfigurer</b>
 * 
 * @since 2015-06-05
 * @author chenzhigang
 */
public class SecretDataSource extends DruidDataSource {

	private static final long serialVersionUID = 1L;

	public synchronized void setPassword(String password) {
		super.setPassword(password);
		String plainPwd = null;
		try {
			javax.crypto.SecretKey deskey = DESHelper.genDESKey("DESede");
			plainPwd = DESHelper.desDecrypt(deskey, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.password = plainPwd;
	}
}
