package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class DownloadTest {

	private static int runnThread = 3;
	private static int threadCount = 3;
	private static Object lock = new Object();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "http://10.25.130.132:8008/Opera.exe";
		try {
			
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			int code = conn.getResponseCode();
			if(code == 200){
				int length = conn.getContentLength();
				System.out.println(length);
				//建立与下载文件大小相同的临时文件
				RandomAccessFile raf = new RandomAccessFile("Opera.exe", "rwd");
				raf.setLength(length);
				raf.close();
				//计算没给线程下载的开始位置和结束位置
				int blockSize = length / threadCount; //1.计算每块的大小
				for(int threadId = 1; threadId <= threadCount; threadId++){
					int startIndex = (threadId - 1) * blockSize; //2.每个线程下载的开始位置
					int endIndex = 0;
					if(threadId == threadCount){ //3.每个线程下载的结束位置
						endIndex = length;
					}else{
						endIndex = threadId * blockSize -1;
					}
					System.out.println("线程:"+threadId+" start:"+startIndex+" end:"+endIndex);
					new DownloadThread(threadId, startIndex,endIndex).start();
				}
				
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static class DownloadThread extends Thread{
		private int threadId;
		private int startIndex;
		private int endIndex;
		
		public DownloadThread(int threadId, int startIndex, int endIndex){
			this.threadId = threadId;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			String path = "http://10.25.130.132:8008/Opera.exe";
			try {
				File tempFile = new File(threadId+".tmp"); 
				if(tempFile.exists() && tempFile.length() > 0){ //判断临时文件是否存在
					FileInputStream fis = new FileInputStream(tempFile); 
					byte[] b = new byte[1024];
					int size = fis.read(b); //读取临时文件中，下载位置
					String startStr = new String(b, 0, size);
					startIndex = Integer.parseInt(startStr); //将文件中下载位置赋给变量
					fis.close();
				}
				
				URL url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(5000);
				conn.setRequestProperty("Range", "bytes:"+startIndex+"-"+endIndex);
				conn.setRequestMethod("GET");
				/*
				 * 200:成功
				 * 300:目标文件丢失或位置被改变
				 * 400:找不到目标文件
				 * 500:服务器内部错误
				 */
				int code = conn.getResponseCode();
				System.out.println("code:"+code);
				RandomAccessFile raf = new RandomAccessFile("Opera.exe", "rwd");
				raf.seek(startIndex);
				
				InputStream is = conn.getInputStream();
				int len = 0;
				byte[] buffer = new byte[1024];
				int total = 0;
				while((len = is.read(buffer)) != -1){
					RandomAccessFile progress = new RandomAccessFile(threadId+".tmp", "rwd");//创建一个临时文件，保存下载位置
					raf.write(buffer, 0, len);
					total += len; //统计下载量
					System.out.println("线程"+threadId+" total:"+total);
					progress.write((startIndex+total+"").getBytes()); //将下载起始位置写入临时文件
					progress.close();
				}
				raf.close();
				is.close();
				System.out.println("线程"+threadId+":下载完毕");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				synchronized (lock) {
					runnThread--; //单线程下载完毕，线程运行数减一
					if (runnThread == 0) { //当前线程运行数为0，删除所有临时文件
						for (int i = 1; i <= threadCount; i++) {
							File temp = new File(i + ".tmp");
							temp.delete();
						}
					}
				}
			}
		}
		
	}

}
