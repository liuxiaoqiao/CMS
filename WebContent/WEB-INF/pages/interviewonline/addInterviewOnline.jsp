<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线访谈维护</title>
<link href="${pageContext.request.contextPath}/css/com.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/js/asyncbox/skins/ZCMS/asyncbox.css" 
type="text/css" rel="stylesheet" /> 
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form_3.25.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/asyncbox/AsyncBox.v1.4.js"></script> 
	<script
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ueditor/ueditor.all.min.js"></script>
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
	console.log('${interviewOnline.interviewContent}');
	if("${interviewOnline.interviewID}"!=""){
	UE.getEditor('myEditor').setContent('${interviewOnline.interviewContent}');
	}
})
function saveInterviewOnline(operType) {
if(operType=="7"&&$("#approvaler").val()!=""){
	operType="6";	
	}
	$("#operType").val(operType);
	$("#interviewContent").val(UE.getEditor('myEditor').getContent());
	var options  = {
	           url:'interviewOnline.do?action=saveInterviewOnline',
	           type:'post',
	           dataType : "html",
	          beforeSubmit:checkFormData,
	           success:function(data){
	        	   var content = eval('(' + data + ')'); 
	        	   
	        	   asyncbox.alert(content.msg, "信息窗口",function() {
	        		   if(operType=="5"){
	        			   $("#interviewID").val(content.interviewID);
	        		   }
	        		   else{
	        			   document.location.href="${pageContext.request.contextPath}/interviewOnline.do?action=turnToInterviewOnline";
	        		   }
	        		   //
	        	   });
	        	   
	           }
	      };
    $("#uploadFile").ajaxSubmit(options);
    return false; 

}
/* function isCheck(operType){
	if(operType!="5")
		checkFormData();
	
} */
function checkFormData(operType){
	if($("#interviewTitle").val()==""){
		asyncbox.alert("请填写主题", "信息窗口");
		return false;
	}
	if($("#operType").val()!="5"){
	if($("#interviewDate").val()==""){
		asyncbox.alert("请填写时间", "信息窗口");
		return false;
	}
	if($("#guestName").val()==""){
		asyncbox.alert("请填写嘉宾", "信息窗口");
		return false;
	}
	if($("#interviewID").val()==""){
		if($("#curPhoto").val()==""){
			asyncbox.alert("请选择实况照片", "信息窗口");
			return false;
		}
		 imgFormatValidate("curPhoto");
		if($("#guestPhoto").val()==""){
			asyncbox.alert("请选择嘉宾免冠照", "信息窗口");
			return false;
		}
		 imgFormatValidate("guestPhoto");
	}
	/* if($("#approvaler").val()==""){
		asyncbox.alert("请填写审稿人", "信息窗口");
		return false;
	} */
	if($("#guestDesc").val()==""){
		asyncbox.alert("请填写嘉宾介绍", "信息窗口");
		return false;
	}
	if($("#interviewSummary").val()==""){
		asyncbox.alert("请填写摘要", "信息窗口");
		return false;
	}
	if(UE.getEditor('myEditor').getContent()==""){
		asyncbox.alert("请填写文字直播内容", "信息窗口");
		return false;
	}
	}
	else{
		if($("#curPhoto").val()!=""){
			if(imgFormatValidate("curPhoto")==false){
				return false;
			};
		
		}
		if($("#guestPhoto").val()!=""){
			if(imgFormatValidate("guestPhoto")==false){
				return false;
			};
		}
	}
}
function reviewInterviewOnline(){
	$.ajax({
		url : "interviewOnline.do?action=reviewInterviewOnline",
		cache : false,
		type : "GET",
		dataType : "json",
		data :{
		 "interviewID" : "${interviewOnline.interviewID}"
		 
		 
		},
		success : function(data){
			
			asyncbox.alert(data.msg, "信息窗口", function() {
				/* $("#searchData").click(); */
				document.location.href="${pageContext.request.contextPath}/interviewOnline.do?action=turnToInterviewOnline";
			});
		}
	})
}
function openBackWindow(){
	asyncbox.open({
		html :  $("#backWindow").html(),
	      width : 400,
	      height :  280,
	      title : "退回窗口"
	  });
}
function sendBack() {
	$.ajax({
		url : "interviewOnline.do?action=sendBackInterviewOnline",
		cache : false,
		type : "POST",
		dataType : "json",
		data :{
		 "interviewID" : "${interviewOnline.interviewID}",
		 "refuseDesc":$(".txt_back_reason:eq(1)").val()
		 
		 
		},
		success : function(data){
			
			asyncbox.alert(data.msg, "信息窗口", function() {
				/* $("#searchData").click(); */
				document.location.href="${pageContext.request.contextPath}/interviewOnline.do?action=turnToInterviewOnline";
			});
		}
	})
}
</script>
</head>
<body>
<fieldset>
<legend>在线访谈</legend>
<form:form id="uploadFile"   enctype="multipart/form-data" method="POST" name="uploadFile"  modelAttribute="interviewOnline"> 
<!-- <form id="uploadFile"   name="uploadFile" > -->
 <form:hidden   path="interviewID" />
 <form:hidden   path="operType" />
  <form:hidden   path="interviewContent" />
 <table id="addTable" style="width:60%">
<tr>
<td style="text-align: right;">主题：</td>
<td colspan="3" style="text-align: left;">
<form:input style="width: 550px;" path="interviewTitle"/>
</td>
</tr>
<tr>
<td style="text-align: right;">时间：</td>
<td style="text-align: left;">
<form:input   onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy.M.d'})"
					size="16" maxlength="30" readonly="true" path="interviewDate"/>
</td>
<td style="text-align: right;">嘉宾：</td>
<td style="text-align: left;">
<form:input  path="guestName"/>
</td>
</tr>
<tr>
<td style="text-align: right;">实况图片：</td>
<td style="text-align: left;">
<input type="file" id="curPhoto" name="curPhoto">
</td>
<td style="text-align: right;">嘉宾免冠照：</td>
<td style="text-align: left;">
<input type="file" id="guestPhoto" name="guestPhoto">
</td>
</tr>
<tr>
<td style="text-align: right;">
审稿人：
</td>
<td colspan="3" style="text-align: left;">
<form:input  path="approvaler"/>
</td>
</tr>
<tr>
<td style="text-align: right;" valign="top">
嘉宾介绍：
</td>
<td colspan="3" style="text-align: left;">
<form:textarea rows="5" cols="50"  path="guestDesc"></form:textarea>
</td>
</tr>
<tr>
<td style="text-align: right;" valign="top">
摘要：
</td>
<td colspan="3" style="text-align: left;">
<form:textarea rows="5" cols="50"  path="interviewSummary"></form:textarea>
</td>
</tr>
<!-- <tr>
<td colspan="4" style="text-align: left;">

</td>
</tr> -->
</table> 
文字直播：
<script type="text/plain" id="myEditor">
                     

                     </script>
		<script type="text/javascript">
			UE.getEditor('myEditor');
		</script>
<hr>
<div align="right">
<input type="button" value="预览" onclick=""  class="button">
<input type="button" value="存草稿" onclick="saveInterviewOnline('5')" class="button">
<input type="button" value="发表" onclick="saveInterviewOnline('7')"  class="button">
<input type="button" value="返回" onclick="history.go(-1)" class="button">
<input type="button" value="退回" onclick="openBackWindow()" class="button">
<input type="button" value="审稿通过" onclick="reviewInterviewOnline()" class="button">
</div>
</form:form>
</fieldset>
<div style="display:none" id="backWindow">
<br>
<table>
<tr>
<td valign="top" style="text-align: right;">
<span id="s_node_name">退回原因：</span>
</td>
<td style="text-align: left;">
<textarea rows="10" cols="25" class="txt_back_reason"></textarea>
</td></tr>
<tr>
<td colspan="2" style="text-align:center;" >
<input type="button" class="button" value="确定" onclick="sendBack()">
</td></tr>
</table>


</div>
</body>
</html>