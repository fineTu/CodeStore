<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/pageCtrlTag" prefix="page" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'postList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<style type="text/css">
a { display:block; color:#FFF;font-weight:bold; text-decoration:none; }
a:hover { color:#329124; text-decoration:none; }
body{ background-image:url('image/main_bg.png');}
.banner{ background-image:url('image/banner_bg.png');}
.tab{ background-image:url('image/tab_bg.png'); color:#fff;}
.tab tr td{padding:6px;}
</style>
</head>

<body>
<table height="768" width="100%"  align="center">
	<tr height="10">
    	<td></td>
    </tr>
    <tr>
    	<td width="10px"></td>
    	<td class="banner" height="30">
        	<div style="padding-left:40px ; font-weight:bold;width:200px; float:left;">岗位信息列表</div>
            <div style="float:right; padding-right:40px;"><a href="addPost.jsp" >新建岗位</a></div>
        </td>
        <td width="10px"></td>
    </tr>
    <tr height="10">
    	<td></td>
        <td></td>
        <td></td>
    </tr>
    <tr height="500px">
    	<td></td>
        <td  align="center" valign="top">
        	<table class="tab" width="90%" cellpadding="0" cellspacing="0" border="1px" bordercolor="#ccc">
            	<tr style="color:#ddd;">
                    <td>岗位名称</td>
                    <td>岗位类型</td>
                    <td>成立时间</td>
                    <td>电话</td>
                    <td>传真</td>
                    <td>描述</td>
                    <td>上级岗位</td>
                    <td>查看下属员工</td>
                    <td>修改</td>
                    <td>删除</td>
                </tr>
                <c:forEach items="${pc.pageList}" var="post">
                	<tr>
	                    <td>${post.postName}</td>
	                    <td>${post.postType}</td>
	                    <td>${post.postFound}</td>
	                    <td>${post.postTel}</td>
	                    <td>${post.postFax}</td>
	                    <td>${post.postDescrip}</td>
	                    <td>${post.postuper} }</a></td>
                    	<td><a href="postStaff.jsp">查看下属员工</a></td>
                    	<td><a href="postInfo?postId=${post.postId}">修改</a></td>
                    	<td><a onclick="javascript:if(!confirm('确定要删除此信息吗？')){return false;}" href="deletdPost?postId=${post.postId}">删除</a></td>
                	</tr>
                </c:forEach>
                <tr>
                	<td colspan="11">
                		<page:pageCtrlLinkTag pageCtrl="${pc}" submitAction="postList" />
                	</td>
                </tr>

			</table>        
        </td>
        <td></td>
    </tr>
    <tr>
    	<td></td>
        <td></td>
        <td></td>
    </tr>
</table>
  </body>
</html>
