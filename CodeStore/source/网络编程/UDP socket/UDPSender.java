package sender;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPSender {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatagramSocket socket = null; 
		try {
			socket = new DatagramSocket();
			String msg = "咱班男生都很帅！女生都很漂亮！";
			byte[] buf = msg.getBytes();
			//创建数据报文（数据内容，数据长度，目的地ip，目的地端口）
			DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("10.25.130.132"), 6666);
			//发送报文
			socket.send(dp);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
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
