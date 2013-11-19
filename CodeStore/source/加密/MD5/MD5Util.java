package test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	static final int _16_BIT = 16;
	static final int _32_BIT = 32;
	private static String md5(String plainText,int length) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			switch (length) {
			case _16_BIT:
				return buf.toString().substring(8, 24);
			case _32_BIT:
				return buf.toString();
			default:
				break;
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args){
		System.out.println(MD5Util.md5("1",MD5Util._16_BIT));
	}
}