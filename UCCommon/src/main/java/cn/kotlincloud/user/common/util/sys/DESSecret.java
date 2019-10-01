package cn.kotlincloud.user.common.util.sys;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class DESSecret {

	/**
	 * des加密
	 */
	public static String encryptDES(String encryptString, String encryptKey,byte[] iv)
			throws Exception {
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes("UTF-8"),
				"DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
		byte[] encryptedData = cipher.doFinal(encryptString.getBytes("UTF-8"));
		String temp = Base64.encodeBase64String(encryptedData);
		String secret = URLEncoder.encode(temp,"UTF-8");
		return secret;
	}
	
	// oli  20150522 base64 防止默认换行导致上传json数组和服务器解析异常问题
	public static String encryptDES(String encryptString, String encryptKey,byte[] iv,int flag)
			throws Exception {
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes("UTF-8"),
				"DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
		byte[] encryptedData = cipher.doFinal(encryptString.getBytes("UTF-8"));
		String temp = Base64.encodeBase64String(encryptedData);
		String secret = URLEncoder.encode(temp,"UTF-8");
		return secret;
	}
	
	/**
	 * des解密
	 */
	public static String decryptDES(String decryptString, String decryptKey,byte[] iv) throws Exception {
		String str1 = URLDecoder.decode(decryptString, "UTF-8");
		byte str2[] = Base64.decodeBase64(str1.getBytes());
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes("UTF-8"), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		byte[] str3 = cipher.doFinal(str2);
		String original = new String(str3, "UTF-8");
		return original;
	}
	
	/***
	 * MD5加密 生成32位md5码
	 */
	public static String String2MD5(String string) {
		try {
			MessageDigest bmd5 = MessageDigest.getInstance("MD5");
			bmd5.update(string.replaceAll("\r", "").replaceAll("\n", "").getBytes());
			int i;
			StringBuffer buf = new StringBuffer();
			byte[] b = bmd5.digest();
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";

	}
	
	public static void main(String[] args) {
		
		try {
			StringBuilder param = new StringBuilder();
			String a = "d6ZxuTx63j9zdH94gyDofhswyp3g6fKowQhBdEPRYqw%3D%0A";//macid
			String b = "YoccOZBFIuzzhvjjEh5PSQ%3D%3D%0A";//tocken
			String c = "fps4tlfz";//itemid
			param.append("a");
			param.append(URLDecoder.decode(a, "UTF-8"));
			param.append("b");
			param.append(URLDecoder.decode(b, "UTF-8"));
			param.append("c");
			param.append(c);
			System.out.println(URLDecoder.decode(a, "UTF-8"));
			System.out.println(URLDecoder.decode(b, "UTF-8"));
			System.out.println(param.toString());
			System.out.println(String2MD5(param.toString()));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
