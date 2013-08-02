<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>稿件访问量分析</title>
	<link href="${pageContext.request.contextPath}/js/asyncbox/skins/ZCMS/asyncbox.css" 
type="text/css" rel="stylesheet" /> 

<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/selfpagination.js"></script>
<script
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/asyncbox/AsyncBox.v1.4.js"></script> 
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/util.js"></script> 
<script type="text/javascript">
function query(){
	var para;
	var eDate= $("#eDate").val();
	var sDate= $("#sDate").val();
	var ranknum=$("#ranknum").val();
	var reg1 = /^[0-9]*[1-9][0-9]*$/;
	if (sDate!=0 && eDate!=0)
	{
	   para="&eDate="+eDate;
	   para+="&sDate="+sDate;
	   para+="&ranknum="+ranknum;
	   if (reg1.test(ranknum)==true)
	   {	
		   window.location.href="reports.do?action=getAnalysisaccess"+para;
		}
	   else
			alert("请在"排名数"一栏输入整数");
	}
	else
		alert("请输入日期");
}
</script>

<script type="text/javascript">
function getexport(){
	var para;
	var eDate= $("#eDate").val();
	var sDate= $("#sDate").val();
	if (sDate!=0 && eDate!=0 )
	{para="&eDate="+eDate;
	para+="&sDate="+sDate;
	window.location.href="reports.do?action=exportexl"+para;
	}
	else
		alert("请先查询再导出");
}
</script>

</script>
</head>
<body>
	<fieldset>
		<legend>稿件访问量分析</legend>
		<table>
			<tr>
				<td style="text-align: left;">时间：<input class="Wdate"
					id="sDate"
					onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy.MM.dd'})"
					size="12" maxlength="30" readonly="true" value="${sDate}" />
					 <span
					style="margin: 0 5px;">至</span>
					<input class="Wdate"
					id="eDate"
					onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy.MM.dd'})"
					size="12" maxlength="30" readonly="true" value="${eDate}" />
					排名数<input id="ranknum" size="12" maxlength="30" value="${ranknum}"/>
				<td style="text-align: right;">
				<input type="button" value="查询" 	onclick="query();"> 
				<input type="button" value="导出" 	onclick="getexport();"> 
				
				</td>
			</tr>
		</table>
	</fieldset>

	<div>
		<table cellspacing="1" width="100%" align="center"
			style="margin: auto;" frame="box" Rules="all">
			<tbody>
				<tr bgcolor="#004B97">
					<th width=7%>排序</th>
					<th width=39%>稿件名称</th>
					<th width=10%>栏目名称</th>
					<th width=12%>发稿人</th>
					<th width=10%>作者</th>
					<th width=15%>稿件来源</th>
					<th width=7%>点击数</th>
				</tr>
				
			<tbody id="mytbody">
				<c:forEach items="${AnalysisaccessData}"  var="p" varStatus="xh">
				     
					<tr align="center">
						<td><c:out value="${xh.count}" /></td>
						<td align=left ><c:out value="${p.NEWS_TITLE}" /></td>
						<td><c:out value="${p.TYPE}" /></td>
						<td><c:out value="${p.PUBLISHER}" /></td>
						<td><c:out value="${p.WRITER}" /></td>
						<td><c:out value="${p.NEWS_ID}" /></td>
						<td><c:out value="${p.READ_COUNT}" /></td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
	</div>

	
	
</body>
</html>