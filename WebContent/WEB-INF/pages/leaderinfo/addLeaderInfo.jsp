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
<title>新增交委领导</title>

<link href="${pageContext.request.contextPath}/css/com.css"
	rel="stylesheet" type="text/css" media="screen" />
<link
	href="${pageContext.request.contextPath}/js/asyncbox/skins/ZCMS/asyncbox.css"
	type="text/css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/util.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/ueditor/themes/default/css/ueditor.css"/>
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript">

   // $(document).ready(function()
	//	 {

	// });

  function preViewLeaderInfo(){
	
  }
 
 //存草稿
 function draftLeaderInfo(){
	 $("#actFlag").val("5"); //拟稿
		var options  = {
		           url:'leaderInfo.do?action=addOrEditLeaderInfo',
		           type:'post',
		           dataType : "html",
		           beforeSubmit:checkFormData,
		           success:function(data){
		        	   var content = eval('(' + data + ')'); 
		        	   asyncbox.alert(content.msg, "信息窗口",function() {
		        		 window.location.href="${pageContext.request.contextPath}/leaderInfo.do?action=getLeaderInfoList";
		        	   });
		        	   
		           }
		      };
	    $("#uploadFile").ajaxSubmit(options);
	    return false; 
 }

 //发表
function publishLeaderInfo() {
	if($("#approvaler").val()==""){
		 $("#actFlag").val("7"); //已发表
	}else{
		 $("#actFlag").val("6"); //待审核
	}
	var options  = {
	           url:'leaderInfo.do?action=addOrEditLeaderInfo',
	           type:'post',
	           dataType : "html",
	           beforeSubmit:checkFormData,
	           success:function(data){
	        	   var content = eval('(' + data + ')'); 
	        	   asyncbox.alert(content.msg, "信息窗口",function() {
	        		 window.location.href="${pageContext.request.contextPath}/leaderInfo.do?action=getLeaderInfoList";
	        	   });
	        	   
	           }
	      };
    $("#uploadFile").ajaxSubmit(options);
    return false; 

}

 //审核通过
function approvalLeaderInfo(){
   $("#actFlag").val("7"); //已发表
	var options  = {
	           url:'leaderInfo.do?action=addOrEditLeaderInfo',
	           type:'post',
	           dataType : "html",
	           beforeSubmit:checkFormData,
	           success:function(data){
	        	   var content = eval('(' + data + ')'); 
	        	   asyncbox.alert(content.msg, "信息窗口",function() {
	        		 window.location.href="${pageContext.request.contextPath}/leaderInfo.do?action=getLeaderInfoList";
	        	   });
	        	   
	           }
	      };
   $("#uploadFile").ajaxSubmit(options);
   return false; 
}
 
function refuseLeaderInfo(){
 var leaderID=$("#leaderID").val();
 var strURL = "${pageContext.request.contextPath}/leaderInfo.do?action=openRefuseLeaderInfo&leaderID="+leaderID;
 var strRtnXML = window
		.showModalDialog(strURL, window,
				"dialogHeight:320px; dialogWidth:300px; center:Yes; help:No; status:No;");
if (strRtnXML != undefined) {
	var values = strRtnXML.split(";");
	var newsID=values[0];
	var newsType =values[1];
	var refuseDesc =values[2];
	$.ajax({
		url : "leaderInfo.do?action=insertRefuseLeaderInfo",
		cache : false,
		type : "POST",
		dataType : "text",
		data : {			   
		   "newsID" : newsID,
	       "newsType"  : newsType,
	       "refuseDesc":refuseDesc
     	},
			success : function(content){
			//asyncbox.alert("退回成功", "信息窗口");
			alert("退回成功");
			window.location.href="${pageContext.request.contextPath}/leaderInfo.do?action=getLeaderInfoList";
			},
			error : function(){
			alert("退回失败")
			//asyncbox.alert("退回失败", "信息窗口");
 		}
	}); 
}
}

function checkFormData(){
	var actFlag=$("#actFlag").val();
		if($("#leaderName").val()==""){
		asyncbox.alert("请填写领导姓名", "信息窗口");
		}
	
if(actFlag=="6"||actFlag=="7"||actFlag=="8"){
		if($("#leaderPost").val()==""){
			asyncbox.alert("请填写领导职务", "信息窗口");
			return false;
		}
		if($("#leaderID").val()==""){
			if($("#leaderPhotosUrl").val()==""){
				asyncbox.alert("请选择领导照片", "信息窗口");
				return false;
			}
			//return imgFormatValidate("leaderPhotosUrl");
		}
		if($("#columnUrl").val()==""){
			asyncbox.alert("请填写URL", "信息窗口");
			return false;
		}
}
}


function backList(){
window.location.href="${pageContext.request.contextPath}/leaderInfo.do?action=getLeaderInfoList";	
}

</script>	

</head>
<body>
	<fieldset>
		<legend>交委领导</legend>
		<form id="uploadFile" enctype="multipart/form-data" method="POST"
			name="uploadFile">
			<input type="hidden" id="leaderID" name="leaderID"
				value="${leaderID}" />
			<input type="hidden" id="actFlag" name="actFlag"/>
			<%-- 
			<input type="hidden" id="leaderResume" name="leaderResume" />--%>
			<input type="hidden" id="leaderPhotosNameOld" name="leaderPhotosNameOld"
				value="${leaderPhotosNameOld}" />
			<input type="hidden" id="leaderPhotosUrlOld" name="leaderPhotosUrlOld"
				value="${leaderPhotosUrlOld}" />
			<table>
				<tr>
					<td>姓名：</td>
					<td colspan="3" style="margin-left: 0px; text-align: left;"><input value="${leaderName}"
						style="margin-left: 0px;" type="text" id="leaderName" name="leaderName" />
					</td>
				</tr>
				<tr>
					<td>职务：</td>
					<td colspan="3" style="margin-left: 0px; text-align: left;"><input value="${leaderPost}"
						style="margin-left: 0px;" type="text" id="leaderPost" name="leaderPost" />
					</td>
				</tr>
				<tr>
					<td>顺序：</td>
					<td style="margin-left: 0px; text-align: left;"><input style="margin-left: 0px;" type="text" value="${leaderOrder}" name="leaderOrder"
						id="leaderOrder" />
					</td>
					<td style="margin-left: 0px; text-align: left;">免冠照：</td>
					<td style="margin-left: 0px; text-align: left;"><input type=file name="leaderPhotos" id="leaderPhotosUrl" >
					</td>
				</tr>
				<tr>
					<td>审稿人：</td>
					<td colspan="3" style="margin-left: 0px; text-align: left;"><input style="margin-left: 0px;" type="text"
						id="approvaler" name="approvaler"  value="${approvaler}" /></td>
				</tr>
			<tr>
					<td  colspan="4" style="margin-left: 0px; text-align: left;">简历： <br /> 
                     <script type="text/plain"  value="${leaderResume}" name="leaderResume"  id="myEditor">
                      <p>这里我可以写一些输入提示</p>
                     </script>
					</td>
				</tr>

			</table>
			<hr>
			<div align="right">
			    <input type="button" value="返回" onclick="backList();" class="button">
				<input type="button" value="预览" style="${isPreViewHide}"  onclick="preViewLeaderInfo();" class="button">
				<input type="button" value="存草稿" style="${isDraftHide}"  onclick="draftLeaderInfo();" class="button">
				<input type="button" value="发表" style="${isPublishHide}" onclick="publishLeaderInfo();"
					class="button">
				<input type="button" value="退回" style="${isRefuseHide}" onclick="refuseLeaderInfo();"
				class="button">
				<input type="button" value="审稿通过" style="${isAppHide}"  onclick="approvalLeaderInfo();"
				class="button">
			</div>
<script type="text/javascript">
UE.getEditor('myEditor');
</script>
			</form>
	</fieldset>
</body>
</html>