package com.neusoft.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neusoft.po.Depart;

public class DepartDao {
	private static String driverParam = "com.mysql.jdbc.Driver";
	private static String urlParam = "jdbc:mysql://localhost:3306/staff_manager";
	private static String nameParam = "root";
	private static String passwordParam = "root";
	private static BaseDAO dao = null;
	public DepartDao(){
		DBConnection.setConnection(driverParam, urlParam, nameParam, passwordParam);
		//Connection conn = DBConnection.getConnection();
		dao = new BaseDAO();
	}
	
	public List<Depart> allDeparts() throws SQLException{
		String sql = "select * from departments";
		List<Map> res = null;
		res = dao.query(sql);
		List<Depart> list = new ArrayList<Depart>();

		for(int i=0;i<res.size();i++){
			Depart depart = new Depart();
//			long temp = (Long)res.get(i).get(0);
//			depart.setDepartId((int)temp);
//			depart.setDepartName((String)res.get(i).get(1));
//			temp = (Long)res.get(i).get(2);
//			depart.setDepartType((int)temp);
//			depart.setDepartTel((String)res.get(i).get(3));
//			depart.setDepartFax((String)res.get(i).get(4));
//			depart.setDepartDescrip((String)res.get(i).get(5));
//			depart.setDepartFound((Date)res.get(i).get(6));
//			temp = (Long)res.get(i).get(7);
//			depart.setDepartSupper((int)temp);
			depart.setDepartId((Integer)res.get(i).get(0));
			depart.setDepartName((String)res.get(i).get(1));
			depart.setDepartType((Integer)res.get(i).get(2));
			depart.setDepartTel((String)res.get(i).get(3));
			depart.setDepartFax((String)res.get(i).get(4));
			depart.setDepartDescrip((String)res.get(i).get(5));
			depart.setDepartFound((Date)res.get(i).get(6));
			depart.setDepartSupper((Integer)res.get(i).get(7));
			list.add(depart);
		}
		return list;
	}
	public List<Depart> searchDepart(String sql2) throws SQLException{
		String sql1 = "select * from departments ";
		List<Map> res = null;
		res = dao.query(sql1+sql2);
		List<Depart> list = new ArrayList<Depart>();

		for(int i=0;i<res.size();i++){
			Depart depart = new Depart();
			depart.setDepartId((Integer)res.get(i).get(0));
			depart.setDepartName((String)res.get(i).get(1));
			depart.setDepartType((Integer)res.get(i).get(2));
			depart.setDepartTel((String)res.get(i).get(3));
			depart.setDepartFax((String)res.get(i).get(4));
			depart.setDepartDescrip((String)res.get(i).get(5));
			depart.setDepartFound((Date)res.get(i).get(6));
			depart.setDepartSupper((Integer)res.get(i).get(7));
			list.add(depart);
		}
		return list;
	}
	public boolean updateDepart(Depart depart) throws SQLException{
		String sql = "update departments set depart_name=?, depart_type=?, depart_tel=?, depart_fax=?, depart_descrip=?, depart_found=?, depart_supper=? where depart_id=?";
		Object params[] = new Object[]{
				depart.getDepartName(),
				depart.getDepartType(),
				depart.getDepartTel(),
				depart.getDepartFax(),
				depart.getDepartDescrip(),
				depart.getDepartFound(),
				depart.getDepartSupper(),
				depart.getDepartId()
				};
		int flag = dao.update(sql,params);
		if(flag==1){
			return true;
		}else{
			return false;
		}
	}
	public boolean addDepart(Depart depart) throws SQLException{
		String sql = "insert into departments values (?,?,?,?,?,?,?,?)";
		Object params[] = new Object[]{
				null,
				depart.getDepartName(),
				depart.getDepartType(),
				depart.getDepartTel(),
				depart.getDepartFax(),
				depart.getDepartDescrip(),
				depart.getDepartFound(),
				depart.getDepartSupper()
				};
		int flag = dao.update(sql,params);
		if(flag==1){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean deleteDepart(int id) throws SQLException{
		String sql = "delete from departments where depart_id=?";
		Object params[] = new Object[]{id};
		int flag = dao.update(sql,params);
		if(flag==1){
			return true;
		}else{
			return false;
		}
	}
	
	public Depart getDepartById(int id) throws SQLException{
		//通过id得到Depart
		String sql = "select * from departments where depart_id=?";
		Map res = null;
		Integer params[] = new Integer[]{id};
		res = dao.findByConditions(sql,params);
		Depart depart = new Depart();
		depart.setDepartId((Integer)res.get(0));
		depart.setDepartName((String)res.get(1));
		depart.setDepartType((Integer)res.get(2));
		depart.setDepartTel((String)res.get(3));
		depart.setDepartFax((String)res.get(4));
		depart.setDepartDescrip((String)res.get(5));
		depart.setDepartFound((Date)res.get(6));
		depart.setDepartSupper((Integer)res.get(7));
		return depart;
	}
	
	public List<Depart> querySubDeparts(int supper) throws SQLException{
		String sql = "select * from departments where depart_supper = ?";
		Integer params[] = new Integer[]{supper};
		List<Map> res = null;
		res = dao.query(sql,params);
		List<Depart> list = new ArrayList<Depart>();

		for(int i=0;i<res.size();i++){
			Depart depart = new Depart();
//			long temp = (Long)res.get(i).get(0);
//			depart.setDepartId((int)temp);
//			depart.setDepartName((String)res.get(i).get(1));
//			temp = (Long)res.get(i).get(2);
//			depart.setDepartType((int)temp);
//			depart.setDepartTel((String)res.get(i).get(3));
//			depart.setDepartFax((String)res.get(i).get(4));
//			depart.setDepartDescrip((String)res.get(i).get(5));
//			depart.setDepartFound((Date)res.get(i).get(6));
//			temp = (Long)res.get(i).get(7);
//			depart.setDepartSupper((int)temp);
			depart.setDepartId((Integer)res.get(i).get(0));
			depart.setDepartName((String)res.get(i).get(1));
			depart.setDepartType((Integer)res.get(i).get(2));
			depart.setDepartTel((String)res.get(i).get(3));
			depart.setDepartFax((String)res.get(i).get(4));
			depart.setDepartDescrip((String)res.get(i).get(5));
			depart.setDepartFound((Date)res.get(i).get(6));
			depart.setDepartSupper((Integer)res.get(i).get(7));
			list.add(depart);
		}
		return list;
	}
	public BaseDAO getBaseDAO(){
		return this.dao;
	}
}
