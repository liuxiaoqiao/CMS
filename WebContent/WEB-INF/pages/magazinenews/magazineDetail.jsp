<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/com.css" rel="stylesheet" type="text/css" media="screen" />
<link
	href="${pageContext.request.contextPath}/js/asyncbox/skins/ZCMS/asyncbox.css"
	type="text/css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/asyncbox/AsyncBox.v1.4.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/util.js"></script>
	
	<style type="text/css">
   #addTable{
		width:99%;
	}
   #addTable td{
		border:0px;
   }
   
  </style>
<script type="text/javascript">
$(function(){
	if("${msg}"!=""){
		asyncbox.alert("${msg}", "信息窗口", function() {});
	}
})

function deleteCatalog(catalogID){
	asyncbox
	.confirm(
			"确定删除该条信息?",
			"信息窗口",
			function(action) {
				if (action == "ok") {
					$.ajax({
						url : "magazineNews.do?action=deleteCatalog",
						cache : false,
						type : "GET",
						dataType : "json",
						data :{
						 "catalogID" :catalogID
						 
						 
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
function reviewMagazine(){
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
						 "magazineID" : '${magazineInfo.magazineID}'
						 
						 
						},
						success : function(data){
							
							asyncbox.alert(data.msg, "信息窗口", function() {
								/* $("#searchData").click(); */
								document.location.href="${pageContext.request.contextPath}/magazineNews.do?action=getMagazineNewsList&magazineTitle=&pressPerson=&startTime=&endTime=";
							});
							
							}
						}
					);
				}});
}
function saveAsDraft(){
	$("#uploadFile").submit();
	/* $.ajax({
		url : "magazineNews.do?action=saveAsDraft",
		cache : false,
		type : "POST",
		dataType : "json",
		data :{
		 "magazineTitle":$("#magazineTitle").val(),
		 "entryTime":$("entryTime").val(),
		 "approvaler" : $("approvaler").val()
		 
		 
		},
		success : function(data){
			
			asyncbox.alert(data.msg, "信息窗口", function() {
				 $("#searchData").click(); 
				document.location.href="${pageContext.request.contextPath}/magazineNews.do?action=getMagazineNewsList&magazineTitle=&pressPerson=&startTime=&endTime=";
			});
			
			}
		}
	); */
}
function checkFormData(){
	if($("#magazineTitle").val()==""){
		asyncbox.alert("请填写杂志标题", "信息窗口");
		return false;
	}
	if($("#magazineNum").val()==""){
		asyncbox.alert("请填写杂志期数", "信息窗口");
		return false;
	}
/* 	if($("#approvaler").val()==""){
		asyncbox.alert("请选择审核人", "信息窗口");
		return false;
	} */
	if($("#magazineID").val()==""){
		if($("#mainPhotoURL").val()==""){
			asyncbox.alert("请选择主图片", "信息窗口");
			return false;
		}
		return imgFormatValidate("mainPhotoURL");
		if($("#magazineFileURL").val()==""){
			asyncbox.alert("请选择文件", "信息窗口");
			return false;
		}
		return fileFormatValidate("magazineFileURL");
	}
}
function distributeMagazineNews(){
	
					$.ajax({
						url : "magazineNews.do?action=distributeMagazineNews",
						cache : false,
						type : "POST",
						dataType : "json",
						data :{
						 "magazineID" : '${magazineInfo.magazineID}'
						 
						},
						success : function(data){
							
							asyncbox.alert(data.msg, "信息窗口", function() {
								/* $("#searchData").click(); */
								document.location.href="${pageContext.request.contextPath}/magazineNews.do?action=getMagazineNewsList&magazineTitle=&pressPerson=&startTime=&endTime=";
							});
							
							}
						}
					);
				
}
function editContent(){
	
}
function deleteContent(contentID){
	asyncbox
	.confirm(
			"确定删除该条信息?",
			"信息窗口",
			function(action) {
				if (action == "ok") {
					$.ajax({
						url : "magazineNews.do?action=deleteContent",
						cache : false,
						type : "GET",
						dataType : "json",
						data :{
						 "contentID" :contentID
						 
						 
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

</script>
</head>
<body>
<form id="uploadFile"   enctype="multipart/form-data" method="POST" name="uploadFile" action="magazineNews.do?action=saveAsDraft"  onsubmit="return checkFormData()"> 
	<input type="hidden" id="magazineID" name="magazineID" value="${magazineInfo.magazineID}" >
	<fieldset>
		<legend>图片集新增</legend>
		<table id="addTable">
			<tr>
				<td style="text-align: right;" width="20%">标题：</td>
				<td colspan="2" style="text-align: left;" width="50%"><input type="text"
					value="${magazineInfo.magazineTitle}" id="magazineTitle" name="magazineTitle" style="width: 430px"></td>
				<td rowspan="4" style="text-align:left;" width="30%">
				<c:if test="${not empty magazineInfo.magazineID}"> 
				<img alt="" src="${magazineInfo.photoURL}" width="120" height="150">
				</c:if>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">杂志期数：</td>
				<td style="text-align: left;"><input type="text" id="magazineNum" name="magazineNum" onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy.M'})"
					size="12" maxlength="30" readonly="true" value="${magazineInfo.magazineNum}"></td>
				<td style="text-align: left;">主图片：<input type="file" id="mainPhotoURL" name="mainPhotoURL">
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">审稿人：</td>
				<td colspan="2" style="text-align: left;"><input type="text"
					value="${magazineInfo.approvaler}" id="approvaler" name="approvaler"></td>
			</tr>
			<tr>
				<td style="text-align: right;">杂志文件：</td>
				<td colspan="2" style="text-align: left;"><input type="file" id="magazineFileURL" name="magazineFileURL" style="width: 440px"></td>
			</tr>
		</table>
</form>

	</fieldset>
	<div align="right">
		<a
			href="${pageContext.request.contextPath}/magazineNews.do?action=turnToCatalogSet&catalogID=&magazineID=${magazineInfo.magazineID}&magazineNum=${magazineInfo.magazineNum}"
			style="margin-right: 5px">新增栏目</a> <a
			href="${pageContext.request.contextPath}/magazineNews.do?action=previewMagazineNews&magazineID=${magazineInfo.magazineID}"
			style="margin-right: 5px" target="_blank">预览</a> 
			<a href="#" onclick="saveAsDraft()" style="margin-right: 5px">存草稿</a> <a
			href="#" onclick="distributeMagazineNews()"
			style="margin-right: 5px">发稿</a> <a
			href="${pageContext.request.contextPath}/magazineNews.do?action=getMagazineNewsList&magazineTitle=&pressPerson=&startTime=&endTime=">返回</a>
		<a href="#"
			style="margin-right: 5px" onclick="reviewMagazine()">审核通过</a>
	</div>
	<fieldset>
		<legend>栏目管理</legend>
		<table>
			<tr>
				<th>序号</th>
				<th>栏目名称</th>
				<th>杂志期数</th>
				<th>顺序</th>
				<th>操作</th>
			</tr>
			<tbody>
				<c:forEach items="${catalogPaginations.result}" var="p"
					varStatus="xh">
					<tr align="center">
						<td><c:out value="${xh.count}" /></td>
						<td><c:out value="${p.catalogName}" /></td>
						<td><c:out value="${p.magazineNum}" /></td>
						<td><c:out value="${p.catalogOrder}" /></td>
						<td><a href="${pageContext.request.contextPath}/magazineNews.do?action=turnToContentSet&catalogID=${p.catalogID}&contentID=&magazineID=${magazineInfo.magazineID}" >新增 </a> <a
							href="${pageContext.request.contextPath}/magazineNews.do?action=turnToCatalogSet&catalogID=${p.catalogID}&magazineID=${magazineInfo.magazineID}&magazineNum=${magazineInfo.magazineNum}"
							onclick="edit()">编辑 </a> <a href="#"
							onclick="deleteCatalog('${p.catalogID}')">删除 </a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>
	<fieldset>
		<legend>栏目文档管理</legend>
		<table>
			<tr>
				<th>序号</th>
				<th>栏目名称</th>
				<th>文档标题</th>
				<th>顺序</th>
				<th>状态</th>
				<th>操作</th>
			
			
			
			</tr>
			<tbody>
				<c:forEach items="${contentPaginations.result}" var="p"
					varStatus="xh">
					<tr align="center">
						<td><c:out value="${xh.count}" /></td>
						<td><c:out value="${p.catalogName}" /></td>
						<td><c:out value="${p.contentTitle}" /></td>
						<td><c:out value="${p.contentOrder}" /></td>
						<td><c:out value="${p.magazineStatus}" /></td>
						<td><a href="${pageContext.request.contextPath}/magazineNews.do?action=turnToContentSet&catalogID=${p.catalogID}&contentID=${p.contentID}&magazineID=${magazineInfo.magazineID}" onclick="editContent('${p.contentID}')">编辑 </a> <a href="#"
							onclick="deleteContent('${p.contentID}')">删除 </a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>
</body>
</html>