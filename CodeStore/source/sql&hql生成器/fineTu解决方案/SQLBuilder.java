package com.huashu.boss.pm.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.hql.classic.GroupByParser;

public class SQLBuilder {
	protected String tableName = null;
	protected String selectStr = null;
	protected String groupStr = null;
	protected String orderStr = null;
	protected StringBuffer conditionSb = null;
	protected List<Object> paramList = null;
	protected String res = null;
	
	public SQLBuilder(String tableName,String selectStr){
		this.tableName = tableName;
		this.selectStr = selectStr;
		this.conditionSb = new StringBuffer();
		this.paramList = new ArrayList<Object>();
	}
	public SQLBuilder(String tableName,String selectStr,String orderStr){
		this.tableName = tableName;
		this.selectStr = selectStr;
		this.orderStr = orderStr;
		this.conditionSb = new StringBuffer();
		this.paramList = new ArrayList<Object>();
	}
	public void addEqualParam(String clomName,Object param){
		conditionSb.append(" and "+clomName+" = ?");
		paramList.add(param);
	}
	public void addLessThanParam(String clomName,Object param){
		conditionSb.append(" and "+clomName+" < ?");
		paramList.add(param);
	}
	public void addLessEquelParam(String clomName,Object param){
		conditionSb.append(" and "+clomName+" <= ?");
		paramList.add(param);
	}
	public void addMoreThanParam(String clomName,Object param){
		conditionSb.append(" and "+clomName+" > ?");
		paramList.add(param);
	}
	
	public void addMoreEqualParam(String clomName,Object param){
		conditionSb.append(" and "+clomName+" >= ?");
		paramList.add(param);
	}
	public void addLikeParam(String clomName,Object param){
		conditionSb.append(" and "+clomName+" like ?");
		paramList.add(param);
	}
	public void addBetweenParam(String clomName,Object param1,Object param2){
		conditionSb.append(" and "+clomName+" between ? and ?");
		paramList.add(param1);
		paramList.add(param2);
	}
	public void addInParam(String clomName,Object ... params){
		conditionSb.append(" and "+clomName+" in (");
		for(Object p : params){
			conditionSb.append("?,");
			paramList.add(p);
		}
		conditionSb.replace(conditionSb.length()-1, conditionSb.length(), ")");
		
	}
	public void addCondition(String hqlStr){
		conditionSb.append(" and "+hqlStr);
	}
	public void addCondition(String hqlStr,Object[] params){
		conditionSb.append(" and "+hqlStr);
		for(Object p : params){
			paramList.add(p);
		}
	}
	public void setOderbyAsc(String clomName){
		orderStr = " "+"oder by " + clomName+" asc";
	}
	public void setOderbyDesc(String clomName){
		orderStr = " "+"oder by " + clomName+" desc";
	}
	public void setGroupBy(String hqlStr){
		groupStr = " group by "+hqlStr;
	}
	public String build(){
		if(conditionSb!=null&&conditionSb.length()!=0&&conditionSb.toString()!=""){
			conditionSb.delete(0, 5);
			res = "select "+selectStr+" from "+tableName+" where "+conditionSb;
		}else{
			res = "select "+selectStr+" from "+tableName;
		}
		
		if(orderStr != null){
			res = res + orderStr;
		}
		if(groupStr != null){
			res = res + groupStr;
		}
		return res;
	}
	
	
	public List<Object> getParamList() {
		return paramList;
	}
	public void setSelectStr(String selectStr) {
		this.selectStr = selectStr;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}
	
	
}
