package com.feitai.common.core;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.feitai.common.util.DESHelper;

/**
 * <b>加载system.properties时对加密字段进行解密处理<br>
 * 		--默认加密字段<i>jdbc_password</i>,可通过注入<i>encryptPropNames</i>参数,修改需要解密的字段
 * </b>
 * @since 2015-07-07
 * @author chenzhigang
 *
 */
public class EncryptPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {
	private String[] encryptPropNames = { "jdbc_password" };

	public void setEncryptPropNames(String[] encryptPropNames) {
		this.encryptPropNames = encryptPropNames;
	}

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if(isEncryptProp(propertyName)){
			String decryptValue = null;
			try{
				javax.crypto.SecretKey deskey = DESHelper.genDESKey("DESede");
				decryptValue = DESHelper.desDecrypt(deskey, propertyValue);
			}catch(Exception e){
				 e.printStackTrace();
			}
			return decryptValue;
		}else{
			return propertyValue;
		}
	}

	private boolean isEncryptProp(String propertyName) {
		for (String encryptPropName : encryptPropNames) {
			if (encryptPropName.equals(propertyName)) {
				return true;
			}
		}
		return false;
	}
}
