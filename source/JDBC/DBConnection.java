package com.neusoft.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	private static String driver=null;
	private static String url=null;
	private static String loginName=null;
	private static String loginPassword=null;

	private static Connection conn=null;
	
	//设置参数
	public static void setConnection(String driverParam,String urlParam,String nameParam,String passwordParam){
	    driver=driverParam;
	    url=urlParam;
	    loginName=nameParam;
	    loginPassword=passwordParam;
	}
	
	//打开连接
	public static Connection getConnection(){
	    try {
		Class.forName(driver);
		conn=DriverManager.getConnection(url,loginName,loginPassword);
		conn.setAutoCommit(false);
	    } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    return conn;
	}
	
	
	//关闭连接
	public static void connClose(){
	    try {
		if(conn!=null && !conn.isClosed()){
		    conn.close();
		}
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
}
