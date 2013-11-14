package test;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DESUtil {
	private static Key key;
	private static byte[] iv = {1,2,3,4,5,6,7,8};
	private static String KEY_STR = "mykey";
	static {
		try {
			KeyGenerator generator = KeyGenerator.getInstance("DES");
			generator.init(new SecureRandom(KEY_STR.getBytes()));
			key = generator.generateKey();
			generator = null;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getEncryptString(String str){
		BASE64Encoder base64en = new BASE64Encoder();
		try {
			IvParameterSpec zeroIv = new IvParameterSpec(iv);
			byte[] strBytes = str.getBytes("utf-8");
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key,zeroIv);
			byte[] encryptStrBytes = cipher.doFinal(strBytes);
			return base64en.encode(encryptStrBytes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String getDecryptString(String str){
		BASE64Decoder base64De = new BASE64Decoder();
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		try {
			byte[] strBytes = base64De.decodeBuffer(str);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key,zeroIv);
			byte[] decryptStrBytes = cipher.doFinal(strBytes);
			return new String(decryptStrBytes,"utf-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) throws Exception{
//		if(args==null||args.length<1){
//			System.out.println("请输入要加密的字符用口个分割");
//		}else{
//			for(String arg:args){
//				System.out.println(arg+": "+getEncryptString(arg));
//			}
//		}
		System.out.println("冷风: "+getEncryptString("冷风"));
		System.out.println(getDecryptString(getEncryptString("冷风")));
	}
}
