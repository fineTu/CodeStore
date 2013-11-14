package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import pojo.Entity;

public class TCPServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket server = null;
		Socket client = null;
		try {
			server = new ServerSocket(6666); //建立服务器对象，打开端口
			while(true){
				client = server.accept(); //等待客户端连接
				if(client != null){
					System.out.println("已有客户端连接");
				}
				//new Thread(new Operator(client)).start();
				new Thread(new Operator2(client)).start();
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				client.close();
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
