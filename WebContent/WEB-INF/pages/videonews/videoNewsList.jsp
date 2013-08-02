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
		width:50%;
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

		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		$.ajax({
			url : "videoNews.do?action=queryVideoNewsListByPageIndex",
			cache : false,
			type : "post",
			dataType : "text",
			data : {
				"pageIndex" : jumpPage,
				"pageSize" : pageSize,
				"videoNewsName" : $("#videoNewsName").val(),
				"entryUser" : $("#entryUser").val(),
				"startTime" : startTime,
				"endTime" : endTime

			},
			success : function(content) {
			    var pageData = eval('(' + content + ')'); 
				var pageIndex = eval(pageData.pageIndex);
				var pageSize = eval(pageData.pageSize);
				var pageCount = eval(pageData.pageCount);
				/* var tableData = eval(pageData.pageData); */ 
				var tableData = eval(pageData.result); 
				$("tbody:eq(2)").empty().append(
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

			newPageHtml += "<td style='text-align:center;width:30%;'>";
			newPageHtml += nullToEmpty(tableData[i].videoNewsName);
			newPageHtml += "</td>";

			newPageHtml += "<td style='text-align:center;width:25%;'>";
			newPageHtml += nullToEmpty(tableData[i].entryDate);
			newPageHtml += "</td>";

			newPageHtml += "<td style='text-align:center;width:10%;'>";
			newPageHtml += nullToEmpty(tableData[i].entryUser);
			newPageHtml += "</td>";

			newPageHtml += "<td style='text-align:center;width:10%;'>";
			newPageHtml += nullToEmpty(tableData[i].videoNewsStatus);
			newPageHtml += "</td>";

			newPageHtml += "<td style='text-align:center;width:20%;'>";
			newPageHtml += "<a class='btn44_20 btnBlue' href='#' onclick=editVideoNews('"+tableData[i].videoNewsId+"')>编辑</a>";
			newPageHtml += "<a class='btn44_20 btnBlue' href='#' style='margin-left:5px' onclick=deleteVideoNews('"+tableData[i].videoNewsId+"','"+tableData[i].videoURL+"')>删除</a>";
			
			
			if("${roleName}"=="Approver"&&"${userNo}"==tableData[i].approvaler){
			newPageHtml += "<a href='#' btn44_20 class='btn44_20 btnBlue' style='margin-left:5px' onclick=editVideoNews('"+tableData[i].videoNewsId+"')>审核</a>"; 
				/* newPageHtml += "<input type='button' style='margin-left:5px' value='审核' onclick=editVideoNews('"+tableData[i].videoNewsId+"') />"; */
			}
			else{
				newPageHtml += "<a href='#' btn44_20 class='btn44_20 btnGray' style='margin-left:5px' >审核</a>"; 
			}
			newPageHtml += "</td>";

			newPageHtml += "</tr>";
		}
		return newPageHtml;
	}
	function editVideoNews(videoNewsId) {
		document.location.href="${pageContext.request.contextPath}/videoNews.do?action=turnToVideoNewsSet&videoNewsId="+escape(videoNewsId);
	}
	function deleteVideoNews(videoNewsId,videoURL){
		asyncbox
		.confirm(
				"确定删除该条信息?",
				"信息窗口",
				function(action) {
					if (action == "ok") {
						$.ajax({
							url : "videoNews.do?action=deleteVideoNews",
							cache : false,
							type : "GET",
							dataType : "json",
							data :{
							 "videoNewsId" : videoNewsId,
							 "videoURL" : videoURL
							 
							 
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
	function turnToVideoNewsSet(){
		document.location.href="${pageContext.request.contextPath}/videoNews.do?action=turnToVideoNewsSet&videoNewsId=";
	}
</script>
</head>
<body style="background:white;">
<div class="searchBox">
					<h3>交通新闻</h3>
					<ul>
						<li><label for="title">标题：</label><input type="text"
							id="videoNewsName" class="text"> <label for="pressPerson">发稿人：</label><input
							type="text" id="entryUser" class="text"></li>
						<li><label class="l">时间：</label> <input class="Wdate"
							id="startTime"
							onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy.MM.dd'})"
							size="12" maxlength="30" readonly="true" /> &nbsp;至 <input
							class="Wdate" id="endTime"
							onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy.MM.dd'})"
							size="12" maxlength="30" readonly="true" /> <a
							onclick="queryData('1','15');" class="btnBlue77_32">查询</a> <a
							onclick="turnToVideoNewsSet()" class="btnBlue77_32">新增</a> <input
							type="button" value="查询" style="display: none" id="searchData"
							class="button" onclick="queryData('1','15')" />
                       
					</ul>
				</div>
	<%-- <fieldset>
		<legend>交通新闻</legend>
		<table id="searchTable">
			<tr>
				<td style="text-align: left;">标题：<input type="text"
					id="videoNewsName">
				</td>
				<td style="text-align: right;">发稿人：<input type="text"
					id="entryUser">
				</td>
			</tr>
			<tr>
				<td style="text-align: left;">时间：<input class="Wdate"
					id="startTime"
					onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy.MM.dd'})"
					size="12" maxlength="30" readonly="true" /> <span
					style="margin: 0 5px;">至</span><input class="Wdate" id="endTime"
					onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy.MM.dd'})"
					size="12" maxlength="30" readonly="true"</td>
				<td style="text-align: right;">
				<input type="button" value="查询" onclick="queryData('1','15');" class="button"> 
				<input type="button" value="新增" onclick="turnToVideoNewsSet()" class="button">
				<a href="${pageContext.request.contextPath}/magazineNews.do?action=turnToMagazineDetail&magazineID=">新增</a>
				</td>
			</tr>
		</table>
	</fieldset> --%>

	<div>
		<table cellspacing="1" width="100%" align="center" class="infoList"
			style="margin: auto;">
			<tbody>
				<tr bgcolor="FFFFFF">
					<th></th>
					<th>文档标题</th>
					<th>时间</th>
					<th>发稿人</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			<tbody id="mytbody">
				<%-- <c:forEach items="${paginations.result}" var="p" varStatus="xh">
					<tr align="center">
						<td><c:out value="${xh.count}" /></td>
						<td><c:out value="${p.magazineTitle}" /></td>
						<td><c:out value="${p.magazineNum}" /></td>
						<td><c:out value="${p.pressPerson}" /></td>
						<td><c:out value="${p.magazineStatus}" /></td>
						<td><a href="#"  class="btn44_20 btnBlue" onclick="editMagazine('${p.magazineID}')">编辑 </a> <a href="#"
							onclick="deleteMagazine('${p.magazineID}')">删除 </a> 
							<a href="#" onclick="reviewMagazine('${p.magazineID}')">审核 </a>
							<a href="#" class="btn44_20 btnBlue" onclick="editMagazine('${p.magazineID}')">审核 </a>
							</td>
					</tr>
				</c:forEach> --%>
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
</body>
</html>