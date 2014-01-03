<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>考勤信息查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		.leftDiv{ float: left; width:320px; background-color: #f4fffa; height: 95%;}
		.mainDiv{ float:left; padding-left:25px; padding-top:90px; font-family: 微软雅黑;}
		.leftLogo{ margin-top:160px; margin-left: 30px;}
		.leftFont{ font-size: 27px; margin-left: 30px;}
		table tr td{ font-size: 14px;}
		.tr1{ background-color: #f7fafe; }
		.tr2{ background-color: #ffffff; }
	body{ margin:0 0; padding:0 0; text-align: center;}
	a{text-decoration: none;}
	a:hover{text-decoration: none;}
	#header{ margin:0 0; padding:0 0; background-color: #f4f8ff; height:30px;}
	#logo{ float:left;margin-left:30px;}
	.menu{ float:right; margin:5px 30px 0px 30px;}
	.menu:hover{ float:right; margin:5px 30px 0px 30px;}
	#banner{ padding-right: 50px;}
	.prev{ margin-right: 10px; margin-left: 10px;}
	.next{ margin-right: 10px; margin-left: 10px;}
	</style>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function submitForm( pageNum){
			var searchForm = document.getElementById("searchForm");
			searchForm.setAttribute("action", "search.do?curPage="+pageNum);
			searchForm.submit();
		}
	</script>
	<script language="javascript" type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
	<!--<script type="text/javascript">
		var xmlHttp;
		
		function createXMLHttpRequest() {
			if (window.ActiveXObject) {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			else if (window.XMLHttpRequest) {
				xmlHttp = new XMLHttpRequest();
			}
		}
		
		function startRequest() {
			createXMLHttpRequest();
			xmlHttp.onreadystatechange = handleStateChange;
			xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xmlHttp.open("POST", "getDeps.do", true);
			xmlHttp.send(null);
		}
		
		function handleStateChange() {
			if(xmlHttp.readyState == 4) {
				if(xmlHttp.status == 200) {
					var doc=xmlHttp.responseXML;
					var nameList=doc.getElementsByTagName("name");
					var idList = doc.getElementsByTagName("id");
					var depSelect=document.getElementById("depName");
					depSelect.length = 0;
					for(i=0;i<nameList.length;i++){
						depSelect.options.add(new Option(nameList.item(i).firstChild.nodeValue,idList.item(i).firstChild.nodeValue));
					}
				}
			}
		}
	</script>-->
  </head>
  
  <body>
  	<div id="container">
  	<div id="header">
		<div id="banner">
			<span id="logo"><img style="height:26px;" src="../img/summer.png"></img></span>
			<span class="menu"><a href="../logout.do">退出</a></span>
			<span class="menu"><a href="../message/findAllMessage.do">消息</a></span>
			<span class="menu"><a href="../search/goToSearch.do">历史查询</a></span>
			<s:if test='#session.emp.role.toString()  == "CONTROLLER"'>
				<span class="menu"><a href="../manager/goToManager.do">时间管理</a></span>
			</s:if>
			<span class="menu"><a href="../sign/goToSign.do">考勤</a></span>
		</div>
	</div>
  	<div class="leftDiv">
  		<div class="leftLogo">
  			<img src="../img/search.PNG"></img>
  		</div>
  		<div class="leftFont">
  			<span>考勤记录查询</span>
  		</div>
  	</div>
  	<div class="mainDiv">
  		<table>
    		<tr>
    			<td>
    			<form action="search.do?curPage=1" method="post" id="searchForm">
			    	<s:if test="%{#session.emp.role.toString() == 'NORMAL'}">
			    		姓名：<s:property value="#session.emp.name"/><input type="hidden" name="searchInfo.empId" value="<s:property value="#session.emp.id"/>"/><br/>
			    		部门：<s:property value="#session.emp.department.name"/><input type="hidden" name="searchInfo.depId" value="<s:property value="#session.emp.department.id"/>"/><br/>
			    		起始时间：<input name="startStr" type="text"  onfocus="WdatePicker({isShowClear:false})" value="<s:property value="searchInfo.startTime"/>" /><br/>
			    		结束时间：<input name="endStr" type="text"  onfocus="WdatePicker({isShowClear:false})" value="<s:property value="searchInfo.endTime" />" /><br/>
			    	</s:if>
			    	<s:elseif test="%{#session.emp.role.toString() == 'MANAGER'}">
			    		姓名：<input type="text" name="searchInfo.empName" /><br/>
			    		部门：<s:property value="#session.emp.department.name"/><input type="hidden" name="searchInfo.depId" value="<s:property value="#session.emp.department.id"/>"/><br/>
			    		起始时间：<input name="startStr" type="text"  onfocus="WdatePicker({isShowClear:false})" value="<s:property value="searchInfo.startTime"/>" /><br/>
			    		结束时间：<input name="endStr" type="text"  onfocus="WdatePicker({isShowClear:false})" value="<s:property value="searchInfo.endTime" />" /><br/>
			    	</s:elseif>
			    	<s:else>
			    		<span>姓名:</span><input type="text" name="searchInfo.empName" value="<s:property value="searchInfo.empName"/>" /><br/>
			    		<span>部门：</span><select id="depName" name="searchInfo.depId" >
			    		 			<option value="">未选择</option>
			    		 		<s:iterator value="%{#request.depList}" id ="dep">
			    		 			<s:if test="%{#dep.id==searchInfo.depId }">
			    		 				<option value="<s:property value="#dep.id" />" selected="selected"><s:property value="#dep.name" /></option>
			    		 			</s:if>
			    		 			<s:else>
			    		 				<option value="<s:property value="#dep.id" />"><s:property value="#dep.name" /></option>
			    		 			</s:else>
			    		 			
			    		 		</s:iterator>
			    		 	</select><span></span><br/>
			    		<span>起始时间：</span><input name="startStr" type="text"  onfocus="WdatePicker({isShowClear:false})" value="<s:property value="searchInfo.startTime"/>" /><br/>
			    		<span>结束时间：</span><input name="endStr" type="text"  onfocus="WdatePicker({isShowClear:false})" value="<s:property value="searchInfo.endTime" />" /><br/>
			    	</s:else>
			    	<input type="submit" value="提交"/>
			    </form>
    			</td>
    		</tr>
    		<tr>
    			<td style="padding-top: 50px;">
    				<table>
    				<s:if test="#request.searchResult.resList.size() != 0">
    					<tr>
    						<td align="center">
	    						部门
	    					</td>
	    					<td align="center">
	    						员工姓名
	    					</td>
	    					<td align="center">
	    						备注
	    					</td>
	    					<td align="center">
	    						考勤情况
	    					</td>
	    					<td align="center">
	    						时间
	    					</td>
    					</tr>
    				</s:if>
    				<s:iterator value="%{#request.searchResult.resList}" id ="check" status="st">
    					
    						<tr <s:if test="%{#st.index%2 == 0}">class="tr2"</s:if><s:else>class="tr1"</s:else>>
	    						<td>
	    							<s:property value="#check.department.name"/>
	    						</td>
	    						<td>
	    							<s:property value="#check.employee.name"/>
	    						</td>
	    						<td>
	    							<s:property value="#check.remark"/>
	    						</td>
	    						<td>
	    							<s:if test='#check.chkState.toString() == "BE_LATE"'>迟到</s:if>
					  				<s:elseif test='#check.chkState.toString() == "LEAVE_EARLY"'>早退</s:elseif>
					  				<s:elseif test='#check.chkState.toString() == "ABSENTEEISM"'>旷工</s:elseif>
					  				<s:elseif test='#check.chkState.toString() == "VACATE"'>请假</s:elseif>
	    						</td>
	    						<td>
	    							<s:property value="#check.date.date"/>
	    						</td>
	    					</tr>
    				</s:iterator>
    				<s:if test="#request.searchResult.resList.size() != 0">
    					<tr>
    						<td colspan="5">
    							<s:if test="%{page.currentPage != 1}">
    							
									<span class="prev"> <a
										onclick="submitForm(1);">首页</a>
									</span>
									<span class="prev"> <a
										onclick="submitForm(<s:property value="page.currentPage - 1"/>);">
											&lt;前页</a> </span>
								</s:if>
								<span>当前第<s:property value="page.currentPage"/>页</span>
								<s:if test="%{page.currentPage != page.totalPageNum}">
									<span class="next"> <a
										onclick="submitForm(<s:property value="page.currentPage + 1"/>);">后页&gt;</a>
									</span>
									<span class="next"> <a
										onclick="submitForm(<s:property value="page.totalPageNum"/>);">尾页</a>
									</span>
								</s:if>
									<span>共<s:property value="page.totalPageNum"/>页</span>
    						</td>
    					</tr>
    				</s:if>
    				</table>
    			</td>
    		</tr>
    	</table>
  	</div>
  	</div>
  </body>
</html>
