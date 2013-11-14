package com.neusoft.tags;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.neusoft.util.PageController;

public class PageCtrlLinkTag extends SimpleTagSupport {

    private PageController pageCtrl=null;
    private String submitAction=null;
    
    public void setPageCtrl(PageController pageCtrl){
	this.pageCtrl=pageCtrl;
    }
    
    public void setSubmitAction(String submitAction){
	this.submitAction=submitAction;
    }
    
    @Override
    public void doTag() throws JspException, IOException {
	// TODO Auto-generated method stub
	JspContext jspContent=this.getJspContext();
	JspWriter out=jspContent.getOut();
	int prev=pageCtrl.getPrePageIndex();
	int next=pageCtrl.getNextPageIndex();
	int pageCount = pageCtrl.getPageCount();
	int currentPage = pageCtrl.getCurrentPageIndex();
	int itemNum = pageCount <= 5?pageCount:5;
	int startNum = pageCount - itemNum + 1;
		if (pageCtrl.getRecordCount() > pageCtrl.getCountPerpage()) {
			if(pageCount < 6){//如果页表可以一次显示完
				if (pageCtrl.getCurrentPageIndex() == 1) {//页表可以一次显示完 且 在第一页
					out.print("<span>");
					out.print("1&nbsp;");
					
					for(int i = 1;i<pageCount;i++){
						out.print("<a style=\"display: inline;\" href=\"" + submitAction + "?currentPageIndex="
								+ (currentPage+i) + "\">"+(currentPage+i)+"</a>&nbsp;");
					}
					out.print("</span>");
				} else if (pageCtrl.getCurrentPageIndex() == pageCount) {//页表可以一次显示完 且 在最后一页
					
					out.print("<span>");
					for(int i = startNum;i<pageCount;i++){
						out.print("<a style=\"display: inline;\" href=\"" + submitAction + "?currentPageIndex="
								+ i + "\">"+(i)+"</a>&nbsp;");
					}
					out.print(pageCount+"&nbsp;");
					out.print("</span>");
				} else {//页表可以一次显示完 且 在中间
					out.print("<span>");
					for(int i = 1;i<=pageCount;i++){
						if(i != currentPage){
							out.print("<a style=\"display: inline;\" href=\"" + submitAction + "?currentPageIndex="
									+ i + "\">"+(i)+"</a>&nbsp;");
						}else{
							out.print(i+"&nbsp;");
						}
					}
					out.print("</span>");
				}
			}else{//页表一次显示不完
				if(currentPage < 4){//页表一次显示不完 且 当前页在1~3间
					startNum = 1;
					int endNum = startNum + 4;
					for(int i = startNum;i<=endNum;i++){
						if(i != currentPage){
							out.print("<a style=\"display: inline;\" href=\"" + submitAction + "?currentPageIndex="
									+ i + "\">"+(i)+"</a>&nbsp;");
						}else{
							out.print(i+"&nbsp;");
						}
					}
					out.print("<a style=\"display: inline;\" href=\"" + submitAction + "?currentPageIndex="
							+ pageCtrl.getNextPageIndex() + "\">下一页</a>");
					out.print("<a href=\"" + submitAction + "?currentPageIndex="
							+ pageCount + "\">末页</a>");
				}else if(currentPage > pageCount-3 ){//页表一次显示不完 且 当前页在最后三个的时候
					out.print("<a style=\"display: inline;\" href=\"" + submitAction + "?currentPageIndex="
							+ 1 + "\">首页</a>");
					out.print("<a style=\"display: inline;\" href=\"" + submitAction + "?currentPageIndex="
							+ pageCtrl.getPrePageIndex() + "\">上一页</a>");
					startNum = pageCount - 4;
					for(int i = startNum;i<=pageCount;i++){
						if(i != currentPage){
							out.print("<a style=\"display: inline;\" href=\"" + submitAction + "?currentPageIndex="
									+ i + "\">"+(i)+"</a>&nbsp;");
						}else{
							out.print(i+"&nbsp;");
						}
					}
				}else{//页表一次显示不完 且 当前页在中间
					out.print("<a style=\"display: inline;\" href=\"" + submitAction + "?currentPageIndex="
							+ 1 + "\">首页</a>");
					out.print("<a style=\"display: inline;\" href=\"" + submitAction + "?currentPageIndex="
							+ pageCtrl.getPrePageIndex() + "\">上一页</a>");
					startNum = currentPage - 2;
					int endNum = startNum + 4;
					for(int i = startNum;i<=endNum;i++){
						if(i != currentPage){
							out.print("<a style=\"display: inline;\" href=\"" + submitAction + "?currentPageIndex="
									+ i + "\">"+(i)+"</a>&nbsp;");
						}else{
							out.print(i+"&nbsp;");
						}
					}
					out.print("<a style=\"display: inline;\" href=\"" + submitAction + "?currentPageIndex="
							+ pageCtrl.getNextPageIndex() + "\">下一页</a>");
					out.print("<a style=\"display: inline;\" href=\"" + submitAction + "?currentPageIndex="
							+ pageCount + "\">末页</a>");
				}
			}
		}
	}
    
}
