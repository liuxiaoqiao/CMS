<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增杂志栏目</title>
<link href="${pageContext.request.contextPath}/css/com.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/js/asyncbox/skins/ZCMS/asyncbox.css" 
type="text/css" rel="stylesheet" /> 
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form_3.25.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/asyncbox/AsyncBox.v1.4.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
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
})
function publishYearBooks() {
	/* $("#uploadFile").ajaxSubmit({
        type: 'post',
        url: "yearBooks.do?action=publishYearBooks" ,
        dataType : "json",
        success: function(data){
            alert( data.msg);
           // $( "#uploadFile").resetForm();
        },
        error: function(XmlHttpRequest, textStatus, errorThrown){
            alert( "error");
        }
    }); */
	var options  = {
	           url:'yearBooks.do?action=publishYearBooks',
	           type:'post',
	           dataType : "html",
	           beforeSubmit:checkFormData,
	           success:function(data){
	        	   var content = eval('(' + data + ')'); 
	        	   asyncbox.alert(content.msg, "信息窗口",function() {
	        		   document.location.href="${pageContext.request.contextPath}/yearBooks.do?action=turnToYearBooks";
	        	   });
	        	   
	           }
	      };
    $("#uploadFile").ajaxSubmit(options);
    return false; 

}
function checkFormData(){
	if($("#yearBooksName").val()==""){
		asyncbox.alert("请填写年鉴标题", "信息窗口");
		return false;
	}
	if($("#yearBooksID").val()==""){
		if($("#photoURL").val()==""){
			asyncbox.alert("请选择主图片", "信息窗口");
			return false;
		}
		imgFormatValidate("photoURL");
		if($("#fileURL").val()==""){
			asyncbox.alert("请选择文件", "信息窗口");
			return false;
		}
		fileFormatValidate("fileURL");
	}
}
</script>
</head>
<body>
<fieldset>
<legend>交通年鉴</legend>
<form id="uploadFile"   enctype="multipart/form-data" method="POST" name="uploadFile" > 
<!-- <form id="uploadFile"   name="uploadFile" > -->
<input type="hidden" id="yearBooksID" name="yearBooksID" value="${yearBooksID}">
<table id="addTable" style="width:70%">
<tr>
<td style="text-align: right ;">
标  题：
</td>
<td style="text-align: left;">

<input type="text"  id="yearBooksName" name="yearBooksName" value="${yearBooksInfo.yearBooksName}">

</td>
<td style="text-align: left;">
主图片：<input type=file name="photo" id="photoURL" > 
</td>
<td rowspan="2">
 <c:if test="${not empty yearBooksID}"> 
<img src="${yearBooksInfo.mainPhotosURL}" width="120" height="150" alt="交通年鉴封面">
 </c:if> 
</td>
</tr>
<tr>
<td style="text-align: right;">
年鉴文件：
</td>
<td style="text-align: left;" colspan="2" >
 <input type="file" id="fileURL" style="width:460px" name="file"> 
</td>

</tr>
</table>

<hr>
<div align="right">
<input type="button" value="预览" onclick="" class="button">
<input type="button" value="发表" onclick="publishYearBooks()" class="button">
<input type="button" value="返回" onclick="history.go(-1)" class="button">
</div>
</fieldset>
</body>
</html>