package com.huashu.boss.pm.util;

public class HQLBuilder extends SQLBuilder {

	public HQLBuilder(String tableName, String selectStr) {
		super(tableName, selectStr);
		// TODO Auto-generated constructor stub
	}
	public HQLBuilder(String tableName,String selectStr,String orderStr){
		super(tableName,selectStr,orderStr);
	}
	@Override
	public String build() {
		// TODO Auto-generated method stub
		if(conditionSb!=null&&conditionSb.length()!=0&&conditionSb.toString()!=""){
			conditionSb.delete(0, 5);
			if(selectStr!=null&&selectStr!=""&&selectStr!="*"){
				res =  "select "+selectStr+" from "+tableName+" where "+conditionSb;
			}else{
				res =  "from "+tableName+" where "+conditionSb;
			}
			
		}else{
			if(selectStr!=null&&selectStr!=""&&selectStr!="*"){
				res = "select "+selectStr+" from "+tableName;
			}else{
				res = "from "+tableName;
			}
		}
		
		if(orderStr != null){
			res = res + orderStr;
		}
		if(groupStr != null){
			res = res + groupStr;
		}
		return res;
	}
	
	
}
