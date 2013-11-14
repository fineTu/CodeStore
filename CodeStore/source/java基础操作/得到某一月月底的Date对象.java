package com.huashu.boss.web.pm.baseInfo;

import java.sql.Date;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.huashu.boss.busi.pm.model.PMAppActionConstant;
import com.huashu.boss.pm.entity.ReportCpBalance;
import com.huashu.boss.web.pm.BaseValidatorForm;
import com.huashu.commons.page.PageBean;

/**
 * 
 * 
 * @struts.form name="reportCpBalanceForm"
 * 
 * @version 1.0
 */
public class ReportCpBalanceForm extends BaseValidatorForm {
	private ReportCpBalance reportCpBalance;
	private int mounth = -1;
	private int year = -1;
	
	public ReportCpBalanceForm() {
		super();
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        if (null == reportCpBalance) {
        	reportCpBalance = new ReportCpBalance();
        }
        
        this.getAppRequest().setAppAction(
                PMAppActionConstant.PM_PARTNER_MAINTENANCE);
    }
	
	public Date getStartDate(){
		if(mounth!=-1&&year!=-1){
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, mounth-1);
			c.set(Calendar.DAY_OF_MONTH, 1);
			return new Date(c.getTime().getTime());
		}
		return null;
	}
	//得到某个月最后一天的Date对象
	public Date getEndDate(){
		if(mounth!=-1&&year!=-1){
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, mounth);
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			return new Date(c.getTime().getTime()); 
		}
		return null;
	}

	public ReportCpBalance getReportCpBalance() {
		return reportCpBalance;
	}

	public void setReportCpBalance(ReportCpBalance reportCpBalance) {
		this.reportCpBalance = reportCpBalance;
	}

	public int getMounth() {
		return mounth;
	}

	public void setMounth(int mounth) {
		this.mounth = mounth;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	
	
}
