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

	function showAdd() {
		var typeID = $("#typeID").val();
		document.location.href = "${pageContext.request.contextPath}/basicData.do?action=turnToAddBasicInfo&infoID=&typeID="
				+ typeID;
	}

	function showEdit(typeID, infoID) {
		document.location.href = "${pageContext.request.contextPath}/basicData.do?action=turnToAddBasicInfo&infoID="
				+ infoID + "&typeID=" + typeID;
	}
	function deleteBasicInfo(infoID) {
		asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) {
			if (action == "ok") {
				$.ajax({
					url : "basicData.do?action=deleteBasicInfo",
					cache : false,
					type : "GET",
					dataType : "text",
					data : {
						"infoID" : infoID

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
		$.ajax({
			url : "basicData.do?action=queryBasicInfoListByPageIndex",
			cache : false,
			type : "POST",
			dataType : "text",
			data : {
				"pageIndex" : jumpPage,
				"pageSize" : pageSize,
				"typeID" : $("#typeID").val(),
				"infoName" : $("#infoName").val() == null ? ""
						: encodeURIComponent($("#infoName").val())
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
			newPageHtml += tableData[i].INFONAME;
			newPageHtml += "</td>";

			newPageHtml += "<td >";
			newPageHtml += tableData[i].INFODESC;
			newPageHtml += "</td>";

			newPageHtml += "<td >";
			newPageHtml += "<a class='btn44_20 btnBlue' onclick=showEdit('"
					+ tableData[i].TYPEID + "'," + tableData[i].INFOID
					+ ");return false;'>编辑</a>";
			newPageHtml += "<a class='btn44_20 btnBlue'  onclick=deleteBasicInfo('"
					+ tableData[i].INFOID + "');>删除</a>";
			newPageHtml += "</td>";

			newPageHtml += "</tr>";
		}

		return newPageHtml;
	}
</script>

<title>基本数据类别内容管理</title>
</head>
<body style="background: white;">
	<form:form id="form">
		<div class="searchBox">
			<h3>基本数据类别内容</h3>
			<ul>
				<li><label for="title">名称：</label><input type="text"
					class="text" id="infoName"> <a
					onclick="queryData('1','15')" class="btnBlue77_32">查询</a> <a
					onclick="showAdd()" class="btnBlue77_32">新增</a> <input
					type="button" value="查询" style="display: none" id="searchData"
					class="button" onclick="queryData('1','15')"> <input
					type="button" value="新增" style="display: none" class="button"
					onclick="showAdd()"> <input type="hidden" id="typeID"
					name="typeID" value="${typeID}" /> <input type="hidden" id="page"
					name="page" value="${page}" /> <input type="hidden" id="count"
					name="count" value="${count}" /></li>
			</ul>
		</div>

		<div>
			<display:table class="infoList"
				style="margin-top: 2px;width:100%;border:0;"
				list="${paginations.result}" id="row" export="false"
				requestURI="${pageContext.request.contextPath}/basicData.do"
				partialList="enable">
				<display:column property="infoName" title="名称" />
				<display:column property="infoDesc" title="描述" />
				<display:column title="操作">
					<div align="center">
						<a class="btn44_20 btnBlue"
							onclick="showEdit('${row.typeID}','${row.infoID}');return false;">编辑</a>

						<a class="btn44_20 btnBlue"
							onclick="deleteBasicInfo('${row.infoID}');return false;">删除</a>

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
	</form:form>
	<script src="${pageContext.request.contextPath}/js/sys.js"></script>
</body>
</html>