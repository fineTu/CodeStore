package com.neusoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDAO {

    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    
    private String[] columns=null;

    public BaseDAO() {
	this.conn = DBConnection.getConnection();
    }

    /*
     * 返回结果集，无条件
     */
    public List<Map> query(String sql) throws SQLException {
	List<Map> list = new ArrayList<Map>();
	HashMap map = null;
	stmt = conn.createStatement();
	rs = stmt.executeQuery(sql);
	this.setColumns();
	int columnsLength=this.columns.length;
	while (rs.next()) {
	    map = new HashMap();
	    for (int i = 0; i < columnsLength; i++) {
		map.put(i, rs.getObject(i + 1));
	    }
	    list.add(map);
	}
	rs.close();
	stmt.close();
	return list;
    }

    /*
     * 返回结果集，有条件
     */
    public List<Map> query(String sql, Object args[]) throws SQLException {
	ArrayList<Map> list = new ArrayList<Map>();
	Map map = null;
	pstmt = conn.prepareStatement(sql);
	for (int i = 0; i < args.length; i++) {
	    pstmt.setObject(i + 1, args[i]);
	}
	rs = pstmt.executeQuery();
	this.setColumns();
	int columnsLength=this.columns.length;
	while (rs.next()) {
	    map = new HashMap();
	    for (int i = 0; i < columnsLength; i++) {
		map.put(i, rs.getObject(i + 1));
	    }
	    list.add(map);  
	}
	rs.close();
	pstmt.close();
	return list;
    }

    /*
     *  返回单一对象
     */
    public Map findByConditions(String sql, Object[] args) throws SQLException {
	Map map = null;
	pstmt = conn.prepareStatement(sql);
	for (int i = 0; i < args.length; i++) {
	    pstmt.setObject(i + 1, args[i]);
	}
	rs = pstmt.executeQuery();
	this.setColumns();
	int columnsLength=this.columns.length;
	if(rs.next()) {
	    map = new HashMap();
	    for (int i = 0; i < columnsLength; i++) {
		map.put(i, rs.getObject(i + 1));
	    }
	}
	rs.close();
	pstmt.close();
	return map;
    }

    /*
     * 返回统计值，无条件
     */
    public Object findByFunction(String sql) throws SQLException {
	Object result = null;
	stmt = conn.createStatement();
	rs = stmt.executeQuery(sql);
	this.setColumns();
	if (rs.next()) {
	    result = rs.getObject(1);
	}
	rs.close();
	stmt.close();
	return result;
    }

    /*
     * 返回统计值，有条件
     */
    public Object findByFunction(String sql, Object[] args) throws SQLException {
	Object result = null;
	pstmt = conn.prepareStatement(sql);
	for (int i = 0; i < args.length; i++) {
	    pstmt.setObject(i + 1, args[i]);
	}
	rs = pstmt.executeQuery();
	this.setColumns();
	if (rs.next()) {
	    result = rs.getObject(1);
	}
	rs.close();
	pstmt.close();
	return result;
    }

    /*
     *  增删改，无条件
     */
    public int update(String sql) throws SQLException {
	int flag = 0;
	stmt = conn.createStatement();
	flag = stmt.executeUpdate(sql);
	stmt.close();
	return flag;
    }

    /*
     *  增删改，有条件
     */
    public int update(String sql, Object[] args) throws SQLException {
	int flag = 0;
	pstmt = conn.prepareStatement(sql);
	for (int i = 0; i < args.length; i++) {
	    pstmt.setObject(i + 1, args[i]);
	}
	flag = pstmt.executeUpdate();
	pstmt.close();
	return flag;
    }

    /*
     *  批量增删改,无条件
     */
    public int[] updateAll(String[] sql) throws SQLException {
	int[] flags = null;
	stmt = conn.createStatement();
	for (int i = 0; i < sql.length; i++) {
	    stmt.addBatch(sql[i]);
	}
	flags = stmt.executeBatch();
	stmt.close();
	return flags;
    }

    /*
     *  批量增删改,有条件
     */
    public int[] updateAll(String[] sql, List<Object[]> args) throws SQLException {
	int[] flags = new int[sql.length];
	for (int i = 0; i < args.size(); i++) {
    	    pstmt = conn.prepareStatement(sql[i]);
    	    Object[] arg = args.get(i);
    	    for (int j = 0; j < arg.length; j++) {
    	        pstmt.setObject(j + 1, arg[j]);
    	    }
    	    flags[i] = pstmt.executeUpdate();    
    	    pstmt.close();
	}
	return flags;
    }

    /*
     *  一条增删改执行多次
     */
    public int[] updateMore(String sql, List<Object[]> args) throws SQLException {
	int[] flags = null;
	pstmt = conn.prepareStatement(sql);
	for (int i = 0; i < args.size(); i++) {
	    Object[] arg = args.get(i);
	    for (int j = 0; j < arg.length; j++) {
		pstmt.setObject(j + 1, arg[j]);
	    }
	    pstmt.addBatch();
	}
	flags = pstmt.executeBatch();
	pstmt.close();
	return flags;
    }

    /*
     * 	设置所有列名
     */
    private void setColumns() throws SQLException {
	String[] colunms = null;
	if (rs != null) {
	    ResultSetMetaData rsmd = rs.getMetaData();
	    colunms = new String[rsmd.getColumnCount()];
	    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
		colunms[i - 1] = rsmd.getColumnName(i);
	    }
	}
	this.columns=colunms;
    }
    
    /*
     * 返回所有列名
     */
    
    public String[] getColumns(){
	return this.columns;
    }
    
    /*
     *  数据提交
     */
    public void commit() throws SQLException {
	conn.commit();
    }

    /*
     *  创建一个保存点
     */
    public Savepoint savepoint(String name) throws SQLException {
	Savepoint savepoint = null;
	savepoint = conn.setSavepoint(name);
	return savepoint;
    }

    /*
     *  数据回滚
     */
    public void rollBack() throws SQLException {
	conn.rollback();
    }

    /*
     *  数据回滚到一个保存点
     */
    public void rollBack(Savepoint savepoint) throws SQLException {
	conn.rollback(savepoint);
    }
    /*
     *  关闭连接
     */
    public void closeConnection() throws SQLException{
    	this.conn.close();
    }
}
