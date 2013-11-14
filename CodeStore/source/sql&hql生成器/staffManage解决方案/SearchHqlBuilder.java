package cn.pms.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.log4testng.Logger;

import cn.pms.pojo.Page;
import cn.pms.pojo.SearchInfo;

public class SearchHqlBuilder {
	private final static Logger logger = Logger.getLogger(SearchHqlBuilder.class);
	private String hql = null;
	private String countHql = null;
	private List<Object> paramList;
	public SearchHqlBuilder(SearchInfo searchInfo,Page page){
		StringBuffer hqlBf = new StringBuffer("from Check");
		paramList = new ArrayList<Object>();
		Integer empId = searchInfo.getEmpId();
		Integer depId = searchInfo.getDepId();
		String empName = searchInfo.getEmpName();
		Date startTime = searchInfo.getStartTime(), endTime = searchInfo.getEndTime();
		boolean notNull[] = {false,false,false,false,false};
		boolean firstOne = true;
		if(empId != null){
			paramList.add(empId);
			notNull[0] = true;
		}
		if(depId != null){
			paramList.add(depId);
			notNull[1] = true;
		}
		if(empName != null&&!empName.equals("")){
			paramList.add(empName);
			notNull[2] = true;
		}
		if(startTime != null){
			paramList.add(startTime);
			notNull[3] = true;
		}
		if(endTime != null){
			paramList.add(endTime);
			notNull[4] = true;
		}
		if(paramList.size()>0){
			hqlBf.append(" where");
		}
		if(notNull[0]){
			if(!firstOne){
				hqlBf.append(" and");
				
			}else{
				firstOne = false;
			}
			hqlBf.append(" employee.id = ?");
		}
		if(notNull[1]){
			if(!firstOne){
				hqlBf.append(" and");
			}else{
				firstOne = false;
			}
			hqlBf.append(" department.id = ?");
		}
		if(notNull[2]){
			if(!firstOne){
				hqlBf.append(" and");
			}else{
				firstOne = false;
			}
			hqlBf.append(" employee.name like ?");
		}
		if(notNull[3]&&notNull[4]){
			if(!firstOne){
				hqlBf.append(" and");
			}else{
				firstOne = false;
			}
			hqlBf.append(" date.date between ? and ?");
		}else if(notNull[3]){
			if(!firstOne){
				hqlBf.append(" and");
			}else{
				firstOne = false;
			}
			hqlBf.append(" date.date > ?");
		}else if(notNull[4]){
			if(!firstOne){
				hqlBf.append(" and");
			}else{
				firstOne = false;
			}
			hqlBf.append(" date.date < ?");
		}
		hql = hqlBf.toString();
		countHql = "select count(*)"+ hqlBf.toString();
//		logger.debug(hql);
		System.out.println(hql);
	}
	public String getHql(){
		return hql;
	}
	public List<Object> getParameters(){
		return this.paramList;
	}
	public String getCountHql() {
		return countHql;
	}
	public void setCountHql(String countHql) {
		this.countHql = countHql;
	}
	
}
