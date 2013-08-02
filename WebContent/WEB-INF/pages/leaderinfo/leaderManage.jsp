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

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/com.css" />
<link
	href="${pageContext.request.contextPath}/js/asyncbox/skins/ZCMS/asyncbox.css"
	type="text/css" rel="stylesheet" />
<link type="text/css"
	href="${pageContext.request.contextPath}/css/jquery/jquery-ui-1.8.16.custom.css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/sysManage.css" />
<style type="text/css">
td {
	font-family: Arial;
	font-size: 10pt;
	border-width: 1px;
	border-color: #E7E8E0;
	border-style: solid;
	background-color: white;
	color: rgb(100, 100, 100);
	text-align: left;
}

option {
	font-family: Arial;
	font-size: 10pt;
	border-width: 1px;
	border-style: solid;
	text-align: left;
}
</style>
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/managerutil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/selfpagination.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/handleSpace.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		if ("${msg}" != "") {
			alertInfo("${msg}");
		}
	});

	//新增
	function showAdd() {
		document.getElementById("recentActFrame").style.display = "none";
		document.location.href = "${pageContext.request.contextPath}/leaderInfo.do?action=turnToAddLeaderInfo&leaderID=&sFlag="
				+ "1";
	}

	//编辑
	function showEdit(leaderID) {
		document.getElementById("recentActFrame").style.display = "none";
		document.location.href = "${pageContext.request.contextPath}/leaderInfo.do?action=turnToAddLeaderInfo&leaderID="
				+ leaderID + "&sFlag=" + "2";
	}

	//审核
	function showApproval(leaderID) {
		document.getElementById("recentActFrame").style.display = "none";
		document.location.href = "${pageContext.request.contextPath}/leaderInfo.do?action=turnToAddLeaderInfo&leaderID="
				+ leaderID + "&sFlag=" + "3";
	}

	//近期活动
	function showRecentAct(leaderID, leaderName) {
		document.getElementById("recentActFrame").style.display = "";
		$("#recentActFrame")
				.attr(
						"src",
						"${pageContext.request.contextPath}/leaderInfo.do?action=getLeaderRecentActList&programType="
								+ leaderID
								+ "&leaderName="
								+ encodeURIComponent(encodeURIComponent(leaderName)));

	}

	function deleteLeaderInfo(leaderID) {
		document.getElementById("recentActFrame").style.display = "none";
		asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) {
			if (action == "ok") {
				$.ajax({
					url : "leaderInfo.do?action=deleteLeaderInfo",
					cache : false,
					type : "GET",
					dataType : "text",
					data : {
						"leaderID" : leaderID

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
		document.getElementById("recentActFrame").style.display = "none";
		$.ajax({
			url : "leaderInfo.do?action=queryLeaderInfoListByPageIndex",
			cache : false,
			type : "POST",
			dataType : "text",
			data : {
				"pageIndex" : jumpPage,
				"pageSize" : pageSize
			},
			success : function(content) {
				var pageData = eval('(' + content + ')');
				var pageIndex = eval(pageData.pageIndex);
				var pageSize = eval(pageData.pageSize);
				var pageCount = eval(pageData.pageCount);
				var tableData = eval(pageData.pageData);
				//console.log(tableData);
				$("tbody").empty().append(
						createNewPageHTML(tableData, pageIndex, pageSize,
								pageCount));
				updatePageInfo(pageData.pageIndex, pageData.totalCount,
						pageData.pageCount);
			},
			error : function() {
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
		/* console.log(tableData);
		return false; */

		var newPageHtml = "";
		for ( var i = 0; i < tableData.length; i++) {
			newPageHtml += "<tr height='25px;'>";

			newPageHtml += "<td >";
			newPageHtml += tableData[i].LEADERNAME;
			newPageHtml += "</td>";

			newPageHtml += "<td >";
			newPageHtml += tableData[i].LEADERPOST;
			newPageHtml += "</td>";

			newPageHtml += "<td >";
			newPageHtml += tableData[i].STATUSNAME;
			newPageHtml += "</td>";

			newPageHtml += "<td >";
			newPageHtml += "<a class='btn44_20 btnBlue'  onclick=showEdit('"
					+ tableData[i].LEADERID + "');return false;'>编辑</a>";
			newPageHtml += "<a class='btn44_20 btnBlue'   onclick=deleteLeaderInfo('"
					+ tableData[i].LEADERID + "');>删除</a>";
			newPageHtml += "<a class='btn44_20 btnBlue'  onclick=showApproval('"
					+ tableData[i].LEADERID + "');return false;'>审核</a>";
			newPageHtml += "<a class='btn44_20 btnBlue'  onclick=showRecentAct('"
					+ tableData[i].LEADERID
					+ "','"
					+ tableData[i].LEADERNAME
					+ "');return false;'>近期活动</a>";
			newPageHtml += "</td>";

			newPageHtml += "</tr>";
		}

		return newPageHtml;
	}
</script>

<title>交通领导</title>
</head>
<body style="background: white;">
	<form:form id="form">
		<div class="searchBox">
			<h3>交通领导</h3>
			<ul>
				<li><a onclick="showAdd()" class="btnBlue77_32">新增</a> <input
					type="button" style="display: none" value="查询" id="searchData"
					class="button" onclick="queryData('1','15')"> <input
					type="button" style="display: none" value="新增" class="button"
					onclick="showAdd()"> <input type="hidden" id="page"
					name="page" value="${page}" /> <input type="hidden" id="count"
					name="count" value="${count}" /></li>
			</ul>

		<div>

			<display:table class="infoList"
				style="margin-top: 2px;width:100%;border:0;"
				list="${paginations.result}" id="row" export="false"
				requestURI="${pageContext.request.contextPath}/leaderInfo.do"
				partialList="enable">

				<display:column property="leaderName" title="领导名称" />
				<display:column property="leaderPost" title="职务" />
				<display:column property="statusName" title="状态" />

				<display:column title="操作">
					<div align="center">
						<a class="btn44_20 btnBlue"
							onclick="showEdit('${row.leaderID}');return false;">编辑</a> <a
							class="btn44_20 btnBlue"
							onclick="deleteLeaderInfo('${row.leaderID}');return false;">删除</a>
						<a class="btn44_20 btnBlue"
							onclick="showApproval('${row.leaderID}');return false;">审核</a> <a
							class="btn44_20 btnBlue" 
							onclick="showRecentAct('${row.leaderID}','${row.leaderName}');return false;">近期活动</a>

					</div>
				</display:column>
				<display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty>
			</display:table>
		</div>
		<div>
			<label style="float: left; margin-left: 5px;">&nbsp;&nbsp;&nbsp;共&nbsp;<span
				id="totalCount" style="font-weight: bold;">${paginations.totalCount}</span>&nbsp;条记录</label>
			<label style="float: right; margin-right: 5px;"> <span
				id="firstPage" onclick="getFirstPage()"><a>首页</a>
			</span>&nbsp; <span id="perPage" onclick="getPerPage()"><a>上一页</a>
			</span>&nbsp; <span id="nextPage" onclick="getNextPage()"><a>下一页</a>
			</span>&nbsp; <span id="lastPage" onclick="getLastPage()"><a>尾页</a>
			</span>&nbsp;&nbsp;页次： <span id="pageIndex1"
				style="color: Red; font-weight: bold;">${paginations.pageIndex}</span>/
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
		<iframe name="recentActFrame" height="700px" id="recentActFrame"
			width="100%" src="" frameborder="0" scrolling="no"> </iframe>
			</div>
	</form:form>
	<script src="${pageContext.request.contextPath}/js/sys.js"></script>
</body>
</html>