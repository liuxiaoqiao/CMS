<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>稿件采用分析</title>
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
<%--
<script type="text/javascript">
$(function () {
        $('#container').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
            	text:'<strong><h1>${getDate}稿件采用分析</h1></strong>'
            },
            tooltip: {
        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
            	percentageDecimals: 1
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        color: '#000000',
                        connectorColor: '#000000',
                        formatter: function() {
                            return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
                        }
                    }
                }
            },
            series: [{
                type: 'pie',
                name: '',
                data:[${strPie}]
            }]
        });
    });
    

		</script>
		
		
--%>

<script type="text/javascript">
function query(){
	var para;
	var eDate= $("#eDate").val();
	var sDate= $("#sDate").val();
	if (sDate!=0 && eDate!=0 )
	{para="&eDate="+eDate;
	para+="&sDate="+sDate;
	window.location.href="reports.do?action=getAnalysisUsingManuscript"+para;
	}
		
	else
		alert("请输入日期");
}
</script>
<script type="text/javascript">

function export(){
	
}
</script>

</script>
</head>
<body>
	<fieldset>
		<legend>稿件采用分析</legend>
		<table>
			<tr>
				<td style="text-align: left;">时间：<input class="Wdate"
					id="sDate" type="text"
					onfocus="WdatePicker({readOnly:false,dateFmt:'yyyy.MM.dd' })"
					size="12" maxlength="30" readonly="false"  value="${sDate}"/>
					 <span
					style="margin: 0 5px;">至</span>
					<input class="Wdate"
					id="eDate"
					onfocus="WdatePicker({readOnly:false,dateFmt:'yyyy.MM.dd'})"
					size="12" maxlength="30" readonly="false"  value="${eDate}"/>
				<td style="text-align: right;">
				<input type="button" value="查询" 	onclick="query();"> 
				<input type="button" value="导出" 	onclick=""> 
				
				</td>
			</tr>
		</table>
	</fieldset>

	<div>
		<table cellspacing="1" width="100%" align="center"
			style="margin: auto;" frame="box" Rules="all">
			<tbody>
				<tr bgcolor="#004B97">
					<th width="10%">序号</th>
					<th width="20%">数据来源</th>
					<th width="30%">发表数量</th>
					<th width="40%">所点比例</th>
				</tr>
				
			<tbody id="mytbody">
				<c:forEach items="${AnalysisUsingData}" var="p" varStatus="xh">
					<tr align="center">
						<td><c:out value="${xh.count}" /></td>
						<td><c:out value="${p.SOURCENAME}" /></td>
						<td><c:out value="${p.COUNTS}" /></td>
						<td><c:out value="${p.PERCENT}" /></td>
					</tr>
					
				</c:forEach>
				<tr bgcolor="#84C1FF">
					<td align="center" colspan="2">合计</td>
					<td align="center" ><c:out value="${totle}" /></td>
					<td></td>
			</tr>
				
			</tbody>
			
			
			</tr>
		</table>
		
	</div>
 
<%--
<script src="${pageContext.request.contextPath}/js/highcharts.js" type="text/javascript"></script>
<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto">
</div>
 --%>
	
	
</body>
</html>