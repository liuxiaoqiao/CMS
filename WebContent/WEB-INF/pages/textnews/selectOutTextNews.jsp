<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="${pageContext.request.contextPath}/css/com.css" rel="stylesheet" type="text/css" media="screen" />
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/managerutil.js"></script>
<script type="text/javascript">
	window.name = "select_outtextnews";
	 $(document).ready(function()
			 {
		 $("a").each(function(){
			$(this).attr("target","select_outtextnews");
			 });
		 if($("#dataSourceOld").val()!=''){
			 $("#dataSource").val($("#dataSourceOld").val());
		 } 
		 });
	 
	function search()
	{
		$("#count").val("");
	}
	function selectButton(newsID,newsTitle)
	{
	   window.returnValue = newsID+";"+newsTitle;
	   window.close();
	}

	
</script>
<title>选择外部稿件页面</title>
</head>
<body style="width:800px">

<form id="form" method="post" action="${pageContext.request.contextPath}/textNews.do?action=getOutTextNewsListForLov"  target="select_outtextnews">
<fieldset><legend>查询</legend>
<div>
<table class="Condition" cellpadding="0" cellspacing="0">
	<tr>
		<td>数据来源:</td>
		<td>
		<select id="dataSource" name="dataSource" style="margin-left: 0px;padding-left: 0px;width: 170px;">
			<c:forEach items="${dataSourceList}" var="dataSource">
			 <option label="${dataSource.label}" value="${dataSource.value}" />
			</c:forEach>
		</select>
		</td>
		<td>标题</td>
		<td><input name="newsTitle" value="${newsTitle}" /></td>
		<td><input type="submit" class="button" value="查找"
			onclick="search();"/></td>
	</tr>
</table>
</div>
</fieldset>

<div>
    <input type="hidden" id="page" name="page" value="${page}" />
    <input type="hidden" id="count" name="count" value="${count}" />
	<input type="hidden" name="action" value="getOutTextNewsListForLov" />
	<input type="hidden" id="dataSourceOld" name="dataSourceOld" value="${dataSource}" />
<display:table  
	list="${outTextNewsList}" id="row" export="false" excludedParams="*" form="form"
	requestURI="${pageContext.request.contextPath}/textNews.do"
	partialList="enable">
	<display:column title="文档标题">
	<input  style="border:0px" readonly="readonly" value="${row.newsTitle}"></input>
	</display:column> 
	<display:column title="数据来源">
	<input  style="border:0px" readonly="readonly" value="${row.dataSourceName}"></input>
	</display:column>
	<display:column title="文档来源">
	<input style="border:0px" readonly="readonly" value="${row.newsSourceName}"></input>
	</display:column>
	<display:column title="时间">
	<input  style="border:0px" readonly="readonly" value="${row.entryDate}"></input>
	</display:column>
	<display:column>
		<input type="button" value="选择" class="button"
			onclick="selectButton('${row.newsID}','${row.newsTitle}')">
	</display:column>
	<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>
</display:table></div>

</form>
</body>
</html>