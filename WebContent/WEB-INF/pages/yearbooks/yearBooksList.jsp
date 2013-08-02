<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>交通杂志列表</title>
<link href="${pageContext.request.contextPath}/css/com.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/sysManage.css" />
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
<style type="text/css">
   #searchTable{
		width:40%;
	}
   #searchTable td{
		border:0px;
   }
   
  </style>
<script type="text/javascript">
	$(function() {
		queryData('1','15');
	})

	function queryData(p, s) {
		jumpPage(p, s);
		//function与DOM ID 冲突	
	}
	//分页控制
	function jumpPage(jumpPage, pageSize) {


		$.ajax({
			url : "yearBooks.do?action=queryYearBooksListByPageIndex",
			cache : false,
			type : "post",
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
				/* var tableData = eval(pageData.pageData); */ 
				var tableData = eval(pageData.result); 
				$("#mytbody").empty().append(
						createNewPageHTML(tableData, pageIndex, pageSize,
								pageCount));
				updatePageInfo(pageData.pageIndex, pageData.totalCount,
						pageData.pageCount);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				//console.log(XMLHttpRequest);
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
		for ( var i = 0; i < tableData.length; i++) {
			newPageHtml += "<tr height='25px;'>";

			newPageHtml += "<td style='text-align:center;width:5%;'>";
			newPageHtml += i + 1;
			newPageHtml += "</td>";

			newPageHtml += "<td style='text-align:center;width:50%;'>";
			/* newPageHtml += tableData[i].MAGAZINETITLE; */
			newPageHtml += nullToEmpty(tableData[i].yearBooksName);
			newPageHtml += "</td>";

			newPageHtml += "<td style='text-align:center;width:25%;'>";
			newPageHtml += nullToEmpty(tableData[i].entryUser);
			newPageHtml += "</td>";

			newPageHtml += "<td style='text-align:center;width:20%;'>";
			newPageHtml += "<a href='#'  class='btn44_20 btnBlue' onclick=turnToAddYearBooks('"+tableData[i].yearBooksID+"')>编辑</a>";
			newPageHtml += "<a href='#' class='btn44_20 btnBlue' style='margin-left:5px' onclick=deleteYearBooks('"+tableData[i].yearBooksID+"')>删除</a>";
			newPageHtml += "</td>";

			newPageHtml += "</tr>";
		}
		return newPageHtml;
	}
	
	function deleteYearBooks(yearBooksID){
		asyncbox
		.confirm(
				"确定删除该条信息?",
				"信息窗口",
				function(action) {
					if (action == "ok") {
						$.ajax({
							url : "yearBooks.do?action=deleteYearBooks",
							cache : false,
							type : "GET",
							dataType : "json",
							data :{
							 "yearBooksID" : yearBooksID
							 
							 
							},
							success : function(data){
								
								asyncbox.alert(data.msg, "信息窗口", function() {
									/* $("#searchData").click(); */
									window.location.reload();
								});
								
								}
							}
						);
					}});
	}
		function reviewMagazine(magazineID){
			asyncbox
			.confirm(
					"确认审核通过?",
					"信息窗口",
					function(action) {
						if (action == "ok") {
							$.ajax({
								url : "magazineNews.do?action=reviewMagazine",
								cache : false,
								type : "GET",
								dataType : "json",
								data :{
								 "magazineID" : magazineID
								 
								 
								},
								success : function(data){
									
									asyncbox.alert(data.msg, "信息窗口", function() {
										/* $("#searchData").click(); */
										window.location.reload();
									});
									
									}
								}
							);
						}});
	}
	function turnToAddYearBooks(yearBooksID) {
		document.location.href="${pageContext.request.contextPath}/yearBooks.do?action=turnToAddYearBooks&yearBooksID="+yearBooksID;
	}
</script>
</head>
<body style="background:white;">
	<fieldset class="searchBox">
		<legend><h3>交通年鉴</h3></legend>
		<div align="right">
		<a onclick="turnToAddYearBooks('')" class="btnBlue77_32">新增</a>
		</div>
	<div>
		<table cellspacing="1" width="100%" align="center" class="infoList"
			style="margin: auto;">
				<tr bgcolor="FFFFFF">
					<th style='width:5%;'>序号</th>
					<th style='width:50%;'>文档标题</th>
					<th style='width:25%;'>发稿人</th>
					<th style='width:20%;'>操作</th>
				</tr>
			<tbody id="mytbody">
			</tbody>
			<tr style="background: #EEF7FE">
				<td colspan="6"><label style="float: left;"> 共&nbsp;<span
						id="totalCount" style="font-weight: bold;">${paginations.totalCount}</span>&nbsp;条记录&nbsp;&nbsp;
				</label> <label style="float: right;"> <span id="firstPage"
						onclick="getFirstPage()"><a>首页</a></span>&nbsp; <span id="perPage"
						onclick="getPerPage()"><a href="#">上一页</a></span>&nbsp; <span
						id="nextPage" onclick="getNextPage()"><a href="#">下一页</a></span>&nbsp;
						<span id="lastPage" onclick="getLastPage()"><a href="#">尾页</a></span>&nbsp;&nbsp;
						页次：<span id="pageIndex1" style="color: Red; font-weight: bold;">${paginations.pageIndex}</span>/
						<span id="pageCount1" style="font-weight: bold;">${paginations.pageCount}</span>页&nbsp;&nbsp;
						<span style="color: Red; font-weight: bold;">15</span>条记录/页&nbsp;&nbsp;转到第
						<input id="jumpPage" name="EgvContent" type="text"
						value="${paginations.pageIndex}" maxlength="3" width="5" size="5" />页
						<input id="jump" name="" type="image" src="images/go.gif"
						onclick="freeJump()"> <input type="hidden" id="pageIndex"
						value="${paginations.pageIndex}"></input> <input type="hidden"
						id="pageCount" value="${paginations.pageCount}"></input>
				</label></td>
			</tr>
		</table>
	</div>
	</fieldset>
</body>
</html>