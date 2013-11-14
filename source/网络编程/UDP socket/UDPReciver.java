package receiver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketPermission;

public class UDPReciver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(6666); //创建socket时，设置接收端口
			byte[] buf = new byte[1024];
			//创建空报文，接收信息
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			//接收报文信息
			socket.receive(dp);
			String msg = new String(dp.getData(),dp.getOffset(),dp.getLength());
			System.out.println(msg);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			//关闭连接
			socket.close();
		}
		
	}

}
