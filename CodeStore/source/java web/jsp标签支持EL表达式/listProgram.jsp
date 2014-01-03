<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jsp/inc/tld.inc"%>
<%@ taglib prefix="exp" uri="cptag/export" %>
<script language="javascript" src="<c:url value="/js/options.js"/>"></script>
<%@ page import="java.util.*"%>


<script>
	function validate(form) {		
		return true;		 
	}
	function onSearch(){
	    var url = "<c:url value='/pm/program/list.do?act=list'/>";
	    document.all.pmGatherProgramInfoForm.action=url;
	    loadOn();
		document.all.pmGatherProgramInfoForm.submit();
    }
	/*
	function onExport(paramStr){
		var start =  document.getElementById("startPage").value;
		var end =  document.getElementById("endPage").value;
	    var url = "<c:url value='/pm/program/list.do?act=exportExcel&startPage="+start+
	    		"&endPage="+end+paramStr+"'/>";
	    document.all.pmGatherProgramInfoForm.action=url;
		document.all.pmGatherProgramInfoForm.submit();
    }*/
</script>

<!-- <script type="text/javascript">
    $(document).ready(function(){
        $('#PageDialog_submitpage').click(function(){
            if(parseInt($('#PageDialog_endpage').val())-parseInt($('#PageDialog_startpage').val())>300){
                $('#PageDialog_error').text('ҳ��������300ҳ֮�ڣ�');
            }
            else if(
                (parseInt($('#PageDialog_startpage').val())>0)&&
                (parseInt($('#PageDialog_startpage').val())<=parseInt($('#PageDialog_endpage').val()))
            ){
                onExport($('#PageDialog_startpage').val(),$('#PageDialog_endpage').val());
                $('#PageDialog').dialog('close');
                $('#PageDialog_error').text('');
            }
            else{
                $('#PageDialog_error').text('��������ȷ��ҳ�뷶Χ��');
            }
        });
    });
</script> -->

<div id="PageDialog" title="������ҳ��" style="display:none;">
    <div id="PageDialog_error" style="color:red;"></div>
    ��ʼҳ�룺
    <input 
        id="PageDialog_startpage" 
        type="text" 
        value="1" 
        onkeyup="value=value.replace(/[^\d]/g,'')" 
        onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
    ><br>
    ����ҳ�룺
    <input 
        id="PageDialog_endpage" 
        type="text" 
        value="1" 
        onkeyup="value=value.replace(/[^\d]/g,'')" 
        onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
    ><br>
    <input id="PageDialog_submitpage" type="button" value="����excel">
</div>


<bean:define id="pageBean" name="pmGatherProgramInfoForm" property="pageBean" />
<html:form action="program/list?act=list" method="post" styleId="pmGatherProgramInfoForm" onsubmit="return validate(this)">
<webui:panel title="��Ŀ������ͳ��" width="100%" icon="../../images/icon_list.gif">
	<webui:formTable>		 
	<tr>
		<webui:input label="��ʼʱ��" >
			<webui:calendar id="f1" property="beginDate"  size="25"/>
			<%-- <html:text property="beginDate" size="25"></html:text> --%>
		</webui:input>
		<webui:input label="����ʱ��" >
			<webui:calendar id="f2" property="endDate"  size="25"/>
			<%-- <html:text property="endDate" size="25"></html:text> --%>
		</webui:input>
		<webui:input label="��Ŀ����" >
			<html:text property="searchInfo"  size="25"/>
		</webui:input>
		
	</tr>
	<tr>
		<webui:input label="��Ŀ����" >
			<html:text property="proType" size="25"></html:text>
		</webui:input>
		<webui:input label="��Ŀ״̬" >
		<html:select property="searchType"  >
			<option value="0">ȫ��</option>
			<option selected="selected" value="1">����</option>
			<option value="2">����</option>
		</html:select>
		</webui:input>
	</tr>
	
	</webui:formTable>	
	<webui:linkButton styleClass="clsButtonFace" href="javascript:onSearch();" value="��ѯ"/>	
</webui:panel>
	
	<%-- <div style="float:left;margin-top:10px; margin-left: 40px;">
		<span>��ʼҳ��<input style="width:30px;" type="text" id="startPage"
			onkeyup="value=value.replace(/[^\d]/g,'')"
			onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" /></span>
		<span>����ҳ��<input style="width:30px;" type="text" id="endPage"
			onkeyup="value=value.replace(/[^\d]/g,'')"
			onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" /></span>
		<a style="color:#66a" href="javascript:onExport('&beginDate=<c:out value="${requestScope.pmGatherProgramInfoForm.beginDate}" />&endDate=<c:out value="${requestScope.pmGatherProgramInfoForm.endDate}" />&searchType=<c:out value="${requestScope.pmGatherProgramInfoForm.searchType}" />&proType=<c:out value="${requestScope.pmGatherProgramInfoForm.proType}" />&searchInfo=<c:out value="${requestScope.pmGatherProgramInfoForm.searchInfo}" />');">����excel</a>
	</div> --%>
	<!--paramStr='&beginDate=<c:out value="${requestScope.pmGatherProgramInfoForm.beginDate}" />&endDate=<c:out value="${requestScope.pmGatherProgramInfoForm.endDate}" />&searchType=<c:out value="${requestScope.pmGatherProgramInfoForm.searchType}" />&proType=<c:out value="${requestScope.pmGatherProgramInfoForm.proType}" />&searchInfo=<c:out value="${requestScope.pmGatherProgramInfoForm.searchInfo}" />'  -->
	<exp:export
		submitForm="pmGatherProgramInfoForm"
		paramStr='&beginDate=${requestScope.pmGatherProgramInfoForm.beginDate}&endDate=${requestScope.pmGatherProgramInfoForm.endDate}&searchType=${requestScope.pmGatherProgramInfoForm.searchType}&proType=${requestScope.pmGatherProgramInfoForm.proType}&searchInfo=${requestScope.pmGatherProgramInfoForm.searchInfo}'
		
		submitAction="/pm/program/list.do"></exp:export>
	<br />
	<webui:panel title="��Ŀ������ͳ��" width="100%" icon="/images/icon_list.gif">
	<webui:table 
		items="infolist" tableId = "resTab"
		action="${pageContext.request.contextPath}/pm/program/list.do?act=list&changePage=1&beginDate=${pmGatherProgramInfoForm.beginDate }&endDate=${pmGatherProgramInfoForm.endDate }&searchType=${pmGatherProgramInfoForm.searchType }"
		title="�б�"
		width="95%"
		rowsDisplayed="10"
		var="item"
		showPagination="true"
		showStatusBar="true"
		showTitle="false"
		filterable="false"
		sortable="false"	
		showExports="true"
		autoIncludeParameters="false"
	    form="pmGatherProgramInfoForm"
	    pageBean="pageBean"
	    
		>
		<%-- <webui:row>	
			<webui:column property="td1" title="cp����" >
				<c:out value='${item.partner.partnerName}'/>
			</webui:column>
			<webui:column property="td2" title="��Ŀ������" >
				<c:out value='${item.reportProGatherBaseInfo.programType}'/>
				<c:if test="${item.reportProGatherBaseInfo.programType==1}">
			        <a href="#" onclick="javascript:onDelete(<c:out value="${group.groupId}"/>);"/>ɾ��</a>
			    </c:if>
			</webui:column>
			<webui:column property="td3" title="��Ŀ����">
				<c:out value='${item.pmGatherProgramInfo.id.proName}'/>
			</webui:column>
			<webui:column property="td4" title="���߼���" >
				<c:out value='${item.pmGatherProgramInfo.id.proGatherCount}'/>
			</webui:column>
			<webui:column property="td5" title="�ܼ���">
				<c:out value='${item.pmGatherProgramInfo.id.proGatherAllcount}'/>
			</webui:column>
			<webui:column property="td6" title="ʱ��">
				<c:out value='${item.pmGatherProgramInfo.id.length}'/>
			</webui:column>
		</webui:row> --%>
		<webui:row>	
			<webui:column property="td1" title="cp����" >
				<c:out value='${item.pmGatherProgramInfo.partnerName}'/>
			</webui:column>
			<webui:column property="td2" title="��Ŀ������" >
				<c:out value='${item.pmGatherProgramInfo.proGatherName}'/>
			</webui:column>
			<webui:column property="td3" title="��Ŀ������" >
				<c:out value='${item.pmGatherProgramInfo.programType}'/>
			</webui:column>
			<webui:column property="td4" title="��Ŀ����">
				<c:out value='${item.pmGatherProgramInfo.proName}'/>
			</webui:column>
			<webui:column property="td5" title="��Ŀ״̬" >
				<c:if test="${item.pmGatherProgramInfo.releaseStatus==1}">
					<span>����</span>
				</c:if>
				<c:if test="${item.pmGatherProgramInfo.releaseStatus==2}">
					<span>����</span>
				</c:if>
			</webui:column>
			<webui:column property="td6" title="����ʱ��">
				<c:out value='${item.pmGatherProgramInfo.onlineDate}'/>
			</webui:column>
			<webui:column property="td7" title="����ʱ��">
				<c:out value='${item.pmGatherProgramInfo.offlineDate}'/>
			</webui:column>
		</webui:row>
	</webui:table>
</webui:panel>
</html:form>
