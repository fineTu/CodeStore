package server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class TCPClient2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket socket = null;
		socket = new Socket(); //建立空Socket对象
		OutputStream out = null;
		
		FileInputStream fis = null;
		try {
			socket.setSoTimeout(5000); //设置连接超时时间
			InetSocketAddress address = new InetSocketAddress("10.25.130.132", 6666); //建立要连接的对象
			socket.connect(address); //连接到服务器
			out = socket.getOutputStream();
			
			fis = new FileInputStream("c:/Opera.exe");
			byte[] buffer = new byte[1024 * 6];
			int len = 0;
			while((len = fis.read(buffer)) != -1){
				out.write(buffer, 0, len);
			}
			out.flush();
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				out.close();
				fis.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
