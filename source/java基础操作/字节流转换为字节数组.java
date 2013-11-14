package com.example.androiddemo24.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTools {

	public static byte[] stream2ByteArray(InputStream is){
		int len = 0;
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			while((len = is.read(buffer)) != -1){
				baos.write(buffer, 0, len);
			}
			baos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				baos.close();
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return baos.toByteArray();
	}
}
