package com.feitai.common.util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import jodd.util.Base64;

public class DESHelper {

//	private static Log log = LogFactory.getLog(DESHelper.class);

	private static SecretKey key = null;

	private DESHelper() {
		//do nothing
	}

	/**
	 * 生成3DES密钥.
	 *
	 * @param key_byte seed key
	 * @throws Exception
	 * @return javax.crypto.SecretKey Generated DES key
	 */
	public static javax.crypto.SecretKey genDESKey(String algorithm) throws Exception {

		String privateKey;
//		privateKey = "E87a362L";//DES key 64bit
		privateKey = "A32g112TB23k115GF66i118P";//DESede key 192bit

		if(key == null){
			key = new SecretKeySpec(privateKey.getBytes("UTF-8"),algorithm);
		}
        return key;
	}

	/**
	 * 3DES加密(String).
	 *
	 * @param key SecretKey
	 * @param src byte[]
	 * @throws Exception
	 * @return byte[]
	 */
	public static String desEncrypt(javax.crypto.SecretKey key, String src) throws	Exception {
		//定义 加密算法,可用 DES,DESede,Blowfish
		String Algorithm = "DESede";
		//加密随机数生成器 (RNG)
		SecureRandom sr = new SecureRandom();

		try {
			//得到加密/解密器
			Cipher c1 = Cipher.getInstance(Algorithm);
			//用指定的密钥和模式初始化Cipher对象
			//参数:(ENCRYPT_MODE, DECRYPT_MODE, WRAP_MODE,UNWRAP_MODE)
			c1.init(Cipher.ENCRYPT_MODE, key, sr);
			//对要加密的内容进行编码处理
			return Base64.encodeToString(c1.doFinal(src.getBytes("UTF-8")));
		} catch (Exception e) {
			 e.printStackTrace();
			return null;
		}
	}

	/**
	 * 3DES 解密(String).
	 *
	 * @param key SecretKey
	 * @param crypt byte[]
	 * @throws Exception
	 * @return byte[]
	 */
	public static String desDecrypt(javax.crypto.SecretKey key, String crypt) throws Exception {
		String Algorithm = "DESede";
		SecureRandom sr = new SecureRandom();
		byte[] plainByte = null;
		try {
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, key, sr);
			plainByte = c1.doFinal(Base64.decode(crypt));
			return new String(plainByte, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 生成文件摘要
	 *
	 * @param input 输入字节
	 * @return SHA1算法生成摘要
	 *
	 * @version 1.0
	 * @since 1.0
	 */
	public  static  String getFileDigest(byte[] input) {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(input);
			return byteToHex(md5.digest());
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	private static String byteToHex(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			String hexString = Integer.toHexString(bytes[i] & 0x00ff);
			if (hexString.length() != 2) {
				sb.append('0').append(hexString);
			} else {
				sb.append(hexString);
			}
		}
		return new String(sb).toUpperCase();
	}

	
	public static void main(String[] args) throws Exception{
		javax.crypto.SecretKey deskey;
		deskey = genDESKey("DESede");
		System.out.println(deskey);

		//加密
		String password = "root";
		String SecretPwd = desEncrypt(deskey, password);
		System.out.println(SecretPwd);

		SecretKey deskey1 = genDESKey("DESede");
//		System.out.println(deskey1);
//		System.out.println(deskey == deskey1);

		//解密
		SecretPwd = "hPSmtKunNDE=";
		String newPwd = desDecrypt(deskey1, SecretPwd);
		System.out.println(newPwd);
	}

}
