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

	function showInfo(typeID) {
		document.getElementById("basicInfoFrame").style.display = "";
		$("#basicInfoFrame")
				.attr(
						"src",
						"${pageContext.request.contextPath}/basicData.do?action=getBasicInfoList&typeID="
								+ typeID + "&infoName=");
	}

	function showAdd() {
		document.getElementById("basicInfoFrame").style.display = "none";
		document.location.href = "${pageContext.request.contextPath}/basicData.do?action=turnToAddBasicType&typeID=";
	}

	function showEdit(typeID) {
		document.getElementById("basicInfoFrame").style.display = "none";
		document.location.href = "${pageContext.request.contextPath}/basicData.do?action=turnToAddBasicType&typeID="
				+ typeID;
	}
	function deleteBasicType(typeID) {
		document.getElementById("basicInfoFrame").style.display = "none";
		asyncbox.confirm("确定删除该笔信息?", "信息窗口", function(action) {
			if (action == "ok") {
				$.ajax({
					url : "basicData.do?action=deleteBasicType",
					cache : false,
					type : "GET",
					dataType : "text",
					data : {
						"typeID" : typeID

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
		document.getElementById("basicInfoFrame").style.display = "none";
		$.ajax({
			url : "basicData.do?action=queryBasicTypeListByPageIndex",
			cache : false,
			type : "POST",
			dataType : "text",
			data : {
				"pageIndex" : jumpPage,
				"pageSize" : pageSize,
				"typeName" : $("#typeName").val() == null ? ""
						: encodeURIComponent($("#typeName").val())
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
			newPageHtml += tableData[i].TYPENAME;
			newPageHtml += "</td>";

			newPageHtml += "<td >";
			newPageHtml += tableData[i].TYPEDESC;
			newPageHtml += "</td>";

			newPageHtml += "<td >";
			newPageHtml += "<a class='btn44_20 btnBlue' onclick=showEdit('"
					+ tableData[i].TYPEID + "');return false;'>编辑</a>";
			newPageHtml += "<a class='btn44_20 btnBlue' onclick=deleteBasicType('"
					+ tableData[i].TYPEID + "');>删除</a>";
			newPageHtml += "<a class='btn44_20 btnBlue' onclick=showInfo('"
					+ tableData[i].TYPEID + "');return false;'>內容</a>";
			newPageHtml += "</td>";

			newPageHtml += "</tr>";
		}

		return newPageHtml;
	}
</script>

<title>基本数据类别管理</title>
</head>
<body style="background: white;">
	<form:form id="form">
		<div class="searchBox">
			<h3>基本数据类别</h3>
			<ul>
				<li><label for="title">名称：</label><input class="text"
					type="text" id="typeName"> <a onclick="queryData('1','15')"
					class="btnBlue77_32">查询</a> <a onclick="showAdd()"
					class="btnBlue77_32">新增</a> <input type="button" value="查询"
					style="display: none" id="searchData" class="button"
					onclick="queryData('1','15')"> <input type="button"
					value="新增" style="display: none" class="button" onclick="showAdd()">
					<input type="hidden" id="page" name="page" value="${page}" /> <input
					type="hidden" id="count" name="count" value="${count}" /></li>
			</ul>
		</div>

		<div>
			<display:table class="infoList"
				style="margin-top: 2px;width:100%;border:0;"
				list="${paginations.result}" id="row" export="false"
				requestURI="${pageContext.request.contextPath}/basicData.do"
				partialList="enable">
				<display:column property="typeName" title="名称" />
				<display:column property="typeDesc" title="描述" />
				<display:column title="操作">
					<div align="center">
						<a class="btn44_20 btnBlue"
							onclick="showEdit('${row.typeID}');return false;">编辑</a> <a
							class="btn44_20 btnBlue"
							onclick="deleteBasicType('${row.typeID}');return false;">删除</a> <a
							class="btn44_20 btnBlue"
							onclick="showInfo('${row.typeID}');return false;">內容</a>
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

		<iframe name="basicInfoFrame" height="700px" id="basicInfoFrame"
			width="100%" src="" frameborder="0" scrolling="no"> </iframe>
	</form:form>
	<script src="${pageContext.request.contextPath}/js/sys.js"></script>
</body>
</html>