package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import pojo.Entity;

public class Operator implements Runnable {
	
	private Socket client = null;
	
	
	public Operator(Socket client) {
		super();
		this.client = client;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		InputStream in = null;
		OutputStream out = null;
		ObjectInputStream ois = null;
		DataOutputStream dos = null;
		try{
			in = client.getInputStream(); //获得网络输入流
//			dis = new DataInputStream(in);
//			System.out.println(dis.readUTF());
			ois = new ObjectInputStream(in);
			Entity e = (Entity)ois.readObject();
			System.out.println(e.getName()+" "+e.getStatus());
			
			out = client.getOutputStream();
			dos = new DataOutputStream(out);
			dos.writeUTF("我好");
			dos.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
