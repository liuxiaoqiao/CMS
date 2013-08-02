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
<title>领导近期活动</title>
<link href="${pageContext.request.contextPath}/css/com.css"
	rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/sysManage.css" />
<link
	href="${pageContext.request.contextPath}/js/asyncbox/skins/ZCMS/asyncbox.css"
	type="text/css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/selfpagination.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/asyncbox/AsyncBox.v1.4.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		if ("${msg}" != "") {
			alertInfo("${msg}");
		}
	});

	function alertInfo(message) {
		asyncbox.alert(message, "信息窗口", function() {
		});
	}

	function queryData(p, s) {
		jumpPage(p, s);
		//function与DOM ID 冲突	
	}

	//分页控制
	function jumpPage(jumpPage, pageSize) {
		$.ajax({
			url : "leaderInfo.do?action=queryLeaderRecentActListByPageIndex",
			cache : false,
			type : "POST",
			dataType : "text",
			data : {
				"pageIndex" : jumpPage,
				"pageSize" : pageSize,
				"programType" : $("#programType").val()
			},
			success : function(content) {
				var pageData = eval('(' + content + ')');
				var pageIndex = eval(pageData.pageIndex);
				var pageSize = eval(pageData.pageSize);
				var pageCount = eval(pageData.pageCount);
				var tableData = eval(pageData.pageData);
				$("tbody").empty().append(
						createNewPageHTML(tableData, pageIndex, pageSize,
								pageCount));
				updatePageInfo(pageData.pageIndex, pageData.totalCount,
						pageData.pageCount);
			},
			error : function() {
				alert("查询失败！");
			}
		});
	}

	function updatePageInfo(pageIndex, totalCount, pageCount) {
		$("#totalCount").empty().append(totalCount);
		$("#pageIndex1").empty().append(pageIndex);
		$("#pageCount1").empty().append(pageCount);
		$("#jumpPage1").val(pageIndex);
		$("#pageIndex").val(pageIndex);
		$("#pageCount").val(pageCount);
		hanlderPageLinkAttr('firstPage', 'perPage', 'nextPage', 'lastPage',
				pageIndex, pageCount);
	}

	function createNewPageHTML(tableData) {
		var newPageHtml = "";
		var leaderName = $("#leaderName").val();
		for ( var i = 0; i < tableData.length; i++) {
			newPageHtml += "<tr height='25px;'>";

			newPageHtml += "<td >";
			newPageHtml += leaderName;
			newPageHtml += "</td>";

			newPageHtml += "<td >";
			newPageHtml += tableData[i].NEWSTITLE;
			newPageHtml += "</td>";

			newPageHtml += "<td >";
			newPageHtml += tableData[i].ENTRYDATE;
			newPageHtml += "</td>";

			newPageHtml += "<td >";
			newPageHtml += tableData[i].ENTRYUSER;
			newPageHtml += "</td>";

			newPageHtml += "<td >";
			newPageHtml += tableData[i].NEWSSTATUSNAME;
			newPageHtml += "</td>";

			newPageHtml += "<td >";
			newPageHtml += "<a class='btn44_20 btnBlue' onclick=updateTextNewsView('"
					+ tableData[i].NEWSID + "')>编辑</a>";
			newPageHtml += "<a class='btn44_20 btnBlue' onclick=deleteTextNews('"
					+ tableData[i].NEWSID + "')>刪除</a>";
			newPageHtml += "</td>";

			newPageHtml += "</tr>";
		}
		return newPageHtml;
	}

	function insertTextNewsView() {
		var programType = $("#programType").val() == null ? "" : $(
				"#programType").val();
		window.location.href = "${pageContext.request.contextPath}/textNews.do?action=insertTextNewsView&programType="
				+ programType;
	}

	function updateTextNewsView(newsID) {
		var programType = $("#programType").val();
		window.location.href = "${pageContext.request.contextPath}/textNews.do?action=updateTextNewsView&newsID="
				+ newsID + "&programType=" + programType;
	}

	function deleteTextNews(newsID) {
		var programType = $("#programType").val() == null ? "" : $(
				"#programType").val();
		asyncbox.confirm("确定删除该条新闻?", "信息窗口", function(action) {
			if (action == "ok") {
				$.ajax({
					url : "textNews.do?action=deleteTextNews",
					cache : false,
					type : "GET",
					dataType : "text",
					data : {
						"newsID" : newsID,
						"programType" : programType

					},
					success : function(content) {

						asyncbox.alert(content, "信息窗口", function() {
							$("#searchData").click();
						});

					}
				});
			}
		})

	}
</script>
</head>

<body style="background: white;">
	<form:form id="form">
           <a onclick="insertTextNewsView()" class="btnBlue77_32">新增</a>
					<input type="button" value="新增" style="display: none"
					id="insertData" class="button" onclick="insertTextNewsView()" /> <input
					type="button" style="display: none" value="查询" id="searchData"
					class="button" onclick="queryData('1','15')"> <input
					type="hidden" id="page" name="page" value="${page}" /> <input
					type="hidden" id="count" name="count" value="${count}" /> <input
					type="hidden" id="programType" name="programType"
					value="${programType}" /> <input type="hidden" id="leaderName"
					name="leaderName" value="${leaderName}" />
		
		<div>

			<display:table class="infoList"
				style="margin-top: 2px;width:100%;border:0;"
				list="${paginations.result}" id="row" export="false"
				requestURI="${pageContext.request.contextPath}/leaderInfo.do"
				partialList="enable">

				<display:column title="领导名称">
     ${leaderName}
    </display:column>
				<display:column property="newsTitle" title="标题" />
				<display:column property="entryDate" title="发稿时间" />
				<display:column property="entryUser" title="发稿人" />
				<display:column property="newsStatusName" title="状态" />
				<display:column title="操作">
					<div align="center">
						<a class="btn44_20 btnBlue"
							onclick="updateTextNewsView('${row.newsID}')">编辑</a> <a
							class="btn44_20 btnBlue"
							onclick="deleteTextNews('${row.newsID}')">删除</a>
					</div>
				</display:column>
				<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>
			</display:table>
		</div>
		<div>
			<label style="float: left; margin-left: 5px;">&nbsp;&nbsp;&nbsp;共&nbsp;<span
				id="totalCount" style="font-weight: bold;">${paginations.totalCount}</span>&nbsp;条记录</label>
			<label style="float: right; margin-right: 5px;"> <span
				id="firstPage" onclick="getFirstPage()"><a>首页</a> </span>&nbsp; <span
				id="perPage" onclick="getPerPage()"><a>上一页</a> </span>&nbsp; <span
				id="nextPage" onclick="getNextPage()"><a>下一页</a> </span>&nbsp; <span
				id="lastPage" onclick="getLastPage()"><a>尾页</a> </span>&nbsp;&nbsp;页次：
				<span id="pageIndex1" style="color: Red; font-weight: bold;">${paginations.pageIndex}</span>/
				<span id="pageCount1" style="font-weight: bold;">${paginations.pageCount}</span>页&nbsp;&nbsp;
				<span style="color: Red; font-weight: bold;">15</span>条记录/页&nbsp;&nbsp;转到第
				<input id="jumpPage" type="text" size="4"
				value="${paginations.pageIndex}" />页 <input id='jump' name=''
				type='image' src='${pageContext.request.contextPath}/images/go.gif'
				onclick='freeJump();return false;'> <input type="hidden"
				id="pageIndex" value="${paginations.pageIndex}"></input> <input
				type="hidden" id="pageCount" value="${paginations.pageCount}"></input>
			</label>
		</div>
	</form:form>
	<script src="${pageContext.request.contextPath}/js/sys.js"></script>
</body>
</html>