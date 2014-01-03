package com.huashu.boss.pm.util;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

public class ExportExcelTag extends SimpleTagSupport{
	private String submitAction = null;
	private String submitForm = null;
	private String paramStr = null;
	
	public void doTag() throws JspException, IOException {
		//super.doTag();
		JspContext jspContent=this.getJspContext();
		JspWriter out=jspContent.getOut();
		out.print("<script type=\"text/javascript\">" +
				"function onExport(paramStr){" +
				"var start =  document.getElementById(\"startPage\").value;" +
				"var end =  document.getElementById(\"endPage\").value;" +
				"var url = \"/card"+submitAction+"?act=exportExcel&startPage=\"+start+\"&endPage=\"+end+paramStr;" +
				"document.all."+submitForm+".action=url;" +
				"document.all."+submitForm+".submit();}</script>");
		
		out.print("<div style='float:left;margin-top:10px; margin-left: 40px;'>");
		out.print("<span>");
		out.print("起始页：<input style='width:30px;' type='text' id='startPage' " +
				"onkeyup='value=value.replace(/[^\\d]/g,'')' " +
				"onbeforepaste='clipboardData.setData('text',clipboardData.getData('text').replace(/[^\\d]/g,''))' />");
		out.print("</span>");
		out.print("<span>");
		out.print("结束页：<input style='width:30px;' type='text' id='endPage'" +
				" onkeyup='value=value.replace(/[^\\d]/g,'')' " +
				"onbeforepaste='clipboardData.setData('text',clipboardData.getData('text').replace(/[^\\d]/g,''))' />");
		out.print("</span>");
		out.print("<a style=\"color:#66a\" href=\"javascript:onExport('"+paramStr+"');\">生成excel</a>");
		out.print("</div>");
	}
	
	public String getSubmitAction() {
		return submitAction;
	}

	public void setSubmitAction(String submitAction) {
		this.submitAction = submitAction;
	}

	public String getSubmitForm() {
		return submitForm;
	}
	public void setSubmitForm(String submitForm) {
		this.submitForm = submitForm;
	}
	public String getParamStr() {
		return paramStr;
	}
	public void setParamStr(String paramStr) {
		//特别注意此处用法，该属性接受一个带有EL表达式的字符串，需在此处手工解析并付给paramStr属性才能使EL表达式生效。
		//另外，JspContext可以强转成PageContext，可查看继承树并未找到此关系。
		try {
			this.paramStr = ExpressionEvaluatorManager.evaluate("paramStr", paramStr.toString(), Object.class,(PageContext) this.getJspContext()).toString();
		} catch (JspException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		this.paramStr = paramStr;
	}
	
	
}
