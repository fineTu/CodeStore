package server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Operator2 implements Runnable {

	private Socket socket = null;
	
	public Operator2(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		InputStream in = null;
		
		FileOutputStream fos = null;
		
		try {
			in = socket.getInputStream();
			fos = new FileOutputStream("d:/Opera.exe");
			byte[] buffer = new byte[1024*6];
			int len = 0;
			while((len = in.read(buffer)) != -1){
				fos.write(buffer, 0, len);
			}
			fos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				fos.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
