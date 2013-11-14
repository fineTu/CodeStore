package client;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import pojo.Entity;

public class TCPClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket socket = null;
		OutputStream out = null;
		InputStream in = null;
//		DataOutputStream dos = null;
		ObjectOutputStream oos = null;
		DataInputStream dis = null;
		try {
			socket = new Socket(); //建立空Socket对象
			socket.setSoTimeout(5000); //设置连接超时时间
			InetSocketAddress address = new InetSocketAddress("10.25.130.132", 6666); //建立要连接的对象
			socket.connect(address); //连接到服务器
			out = socket.getOutputStream(); //网络输出流
//			dos = new DataOutputStream(out);
//			dos.writeUTF("你好");
//			dos.flush();
			oos = new ObjectOutputStream(out);
			Entity entity = new Entity("你猜",1);
			oos.writeObject(entity);
			oos.flush();
			
			in = socket.getInputStream();
			dis = new DataInputStream(in);
			System.out.println(dis.readUTF());
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				out.close();
				in.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
