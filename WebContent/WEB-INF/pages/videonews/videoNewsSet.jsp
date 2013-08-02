<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增杂志栏目</title>
<link
	href="${pageContext.request.contextPath}/js/asyncbox/skins/ZCMS/asyncbox.css"
	type="text/css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/asyncbox/AsyncBox.v1.4.js"></script>
	<script
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/util.js"></script> 
<script type="text/javascript">
	$(function() {
		$("input[type='button']").attr("class","button");
		getSelectList("util.do?action=getContentSourceList","videoNewsSource",'${videoNews.videoNewsSource}');

	})
	function checkFormData(operType){
		if($("#videoNewsName").val()==""){
			asyncbox.alert("请填写主题", "信息窗口");
			return false;
		}
		if($("#operType").val()!="5"){
		if($("#entryDate").val()==""){
			asyncbox.alert("请填写录入时间", "信息窗口");
			return false;
		}
		if($("#videoNewsSource").val()==""){
			asyncbox.alert("请选择文档来源", "信息窗口");
			return false;
		}
		
		if($("#videoNewsId").val()==""){
			if($("#mainPhoto").val()==""){
				asyncbox.alert("请选择主图片", "信息窗口");
				return false;
			}
			 imgFormatValidate("mainPhoto");
			if($("#video").val()==""){
				asyncbox.alert("请选择视频", "信息窗口");
				return false;
			}
			videoFormatValidate("video");
		}
		}
		else{
			if($("#mainPhoto").val()!=""){
				if(imgFormatValidate("mainPhoto")==false){
					return false;
				};
			
			}
			if($("#video").val()!=""){
				if(videoFormatValidate("video")==false){
					return false;
				};
			}
		}
	}
	function saveVideoNews(operType) {
		if(operType=="7"&&$("#approvaler").val()!=""){
			operType="6";	
			}
			$("#operType").val(operType);
			var options  = {
			           url:'videoNews.do?action=saveVideoNews',
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
			        			   document.location.href="${pageContext.request.contextPath}/videoNews.do?action=turnToVideoNewsList";
			        		   }
			        	   });
			        	   
			           }
			      };
		    $("#uploadFile").ajaxSubmit(options);
		    return false; 

		}
	function reviewVideoNews(){
		$.ajax({
			url : "videoNews.do?action=reviewVideoNews",
			cache : false,
			type : "GET",
			dataType : "json",
			data :{
			 "videoNewsId" : "${videoNews.videoNewsId}"
			 
			 
			},
			success : function(data){
				
				asyncbox.alert(data.msg, "信息窗口", function() {
					/* $("#searchData").click(); */
					document.location.href="${pageContext.request.contextPath}/videoNews.do?action=turnToVideoNewsList";
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
			url : "videoNews.do?action=sendBackVideoNews",
			cache : false,
			type : "POST",
			dataType : "json",
			data :{
			 "videoNewsId" : "${videoNews.videoNewsId}",
			 "refuseDesc":$(".txt_back_reason:eq(1)").val()
			 
			 
			},
			success : function(data){
				
				asyncbox.alert(data.msg, "信息窗口", function() {
					document.location.href="${pageContext.request.contextPath}/videoNews.do?action=turnToVideoNewsList";
				});
			}
		})
	}
	function previewVideoNews(){
		var URL="${pageContext.request.contextPath}/videoNews.do?action=previewVideoNews&newsID=${videoNews.videoNewsId}";
		window.open(URL,"_blank", "预览窗口", true );
	}
</script>
</head>
<body>
	<fieldset>
		<legend>交通视频</legend>
		<form:form id="uploadFile"   enctype="multipart/form-data" method="POST" name="uploadFile"  modelAttribute="videoNews">
		<form:hidden path="videoNewsId"/>
		<form:hidden   path="operType" />
		<table>
			<tr>
				<td style="text-align: right;">标题：</td>
				<td colspan="3">
				<form:input type="text"
					path="videoNewsName" style="width: 400px;"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">副标题：</td>
				<td colspan="3">
				<form:input type="text"
					path="subVideoNewsTitle" style="width: 400px;"/></td>

			</tr>
			<tr>
				<td style="text-align: right;">作者：</td>
				<td><form:input type="text" path="writer"/>
				</td>
				<td>录入时间：</td>
				<td><form:input type="text" path="entryDate" onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy.M.d HH:mm'})"
					size="16" maxlength="30" readonly="true" />
			</tr>
			<tr>
				<td>审稿人：</td>
				<td><form:input type="text" path="approvaler"/></td>
				<td>文档来源：</td>
				<td>
				<form:select path="videoNewsSource" style="width: 130px"></form:select>
				</td>
			</tr>
			<tr>
				<td>主图片：</td>
				<td colspan="2"><input type="file" id="mainPhoto" name="mainPhoto"></td>
				<td rowspan="2">
				<img alt="" src="${videoNews.mainPhotosUrl}" width="120" height="150">
				 </td>
			</tr>
			<tr>
				<td style="text-align: right;">视频说明：</td>
				<td colspan="2">
				<form:textarea rows="10" cols="48" path="videoDesc"></form:textarea>
				</td>
			</tr>
			<tr>
				<td>视频上传：</td>
				<td colspan="3"><input type="file" id="video" name="video"> </td>
			</tr>
		</table>
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
	<hr>
	<input type="button" value="预览" onclick="previewVideoNews()">
<input type="button" value="存草稿" onclick="saveVideoNews('5')" class="button">
<input type="button" value="发表" onclick="saveVideoNews('7')">
<input type="button" value="返回" onclick="history.go(-1)" class="button">
<c:if test="${roleName=='Approver' && userNo==videoNews.approvaler}">
<input type="button" value="退回" onclick="openBackWindow()" class="button">
<input type="button" value="审核通过" onclick="reviewVideoNews()" class="button">
</c:if>
</body>
</html>