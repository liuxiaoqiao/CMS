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
<title>维护专题专栏</title>
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
<script type="text/javascript">

function publishSpecialColumn() {
	var options  = {
	           url:'specialColumn.do?action=publishSpecialColumn',
	           type:'post',
	           dataType : "html",
	           beforeSubmit:checkFormData,
	           success:function(data){
	        	   var content = eval('(' + data + ')'); 
	        	   asyncbox.alert(content.msg, "信息窗口",function() {
	        		   document.location.href="${pageContext.request.contextPath}/specialColumn.do?action=getSpecialColumnList&columnTitle=";
	        	   });
	        	   
	           }
	      };
    $("#uploadFile").ajaxSubmit(options);
    return false; 

}
function checkFormData(){
	if($("#columnTitle").val()==""){
		asyncbox.alert("请填写专题标题", "信息窗口");
		return false;
	}
	if($("#columnID").val()==""){
		if($("#columnPhotosUrl").val()==""){
			asyncbox.alert("请选择专题图片", "信息窗口");
			return false;
		}
		//return imgFormatValidate("columnPhotosUrl");
	}
	if($("#columnUrl").val()==""){
		asyncbox.alert("请填写URL", "信息窗口");
		return false;
	}
}

function testLink(){
	var testUrl=$("#columnUrl").val();
	window.open(testUrl);		
}

function backList(){
window.location.href="${pageContext.request.contextPath}/specialColumn.do?action=getSpecialColumnList&columnTitle=";	
}

</script>	

</head>
<body>
	<fieldset>
		<legend>专题专栏</legend>
		<form id="uploadFile" enctype="multipart/form-data" method="POST"
			name="uploadFile">
			<input type="hidden" id="columnID" name="columnID"
				value="${columnID}">
			<table>
				<tr>
					<td>标题：</td>
					<td colspan="3" style="margin-left: 0px; text-align: left;"><input value="${columnTitle}"
						style="margin-left: 0px;" type="text" id="columnTitle" name="columnTitle" />
					</td>
				</tr>
				<tr>
					<td>建立时间：</td>
					<td><input style="margin-left: 0px;" type="text" value="${entryDate}" name="entryDate"
						id="entryDate" />
					</td>
					<td>专题图片(1024*104px)：</td>
					<td><input type=file name="columnPhotos" id="columnPhotosUrl" >
					</td>
				</tr>
				<tr>
					<td>URL：</td>
					<td colspan="3"><input style="margin-left: 0px;" type="text"
						id="columnUrl" name="columnUrl"  value="${columnUrl}" /> <input type="button" value="测试链接"
						class="button" onclick="testLink();" /></td>
				</tr>
				<tr>
					<td>设定为页面专栏：</td>
					<td colspan="3"><input type="checkbox" value="1" name="isShow" />
					</td>
				</tr>

			</table>

			<hr>
			<div align="right">
				<input type="button" value="返回" onclick="backList();" class="button">
				<input type="button" value="发表" onclick="publishSpecialColumn()"
					class="button">
			</div>
			</form>
	</fieldset>

	<%-- 
	<form:form id="form">
		<input type="hidden" id="columnID" name="columnID" value="${columnID}" />
		<div>
			<table class="Condition" cellpadding="0" cellspacing="0">
				<tr>
					<td>标题：</td>
					<td colspan="3" style="margin-left: 0px; text-align: left;"><input
						style="margin-left: 0px;" type="text" id="columnTitle" /></td>
				</tr>
				<tr>
					<td>建立时间：</td>
					<td><input style="margin-left: 0px;" type="text"
						id="entryDate" /></td>
					<td>专题图片(1024*104px)：</td>
					<td><input style="margin-left: 0px;" type="text"
						id="columnPhotosName" /></td>
				</tr>
				<tr>
					<td>URL：</td>
					<td colspan="3"><input style="margin-left: 0px;" type="text"
						id="columnUrl" />
					 <input type="button"  value="测试链接" id="testLink"
						class="button" onclick="testLink();" />	
					</td>
				</tr>
				<tr>
					<td>设定为页面专栏：</td>
					<td colspan="3"><input type="checkbox" value="1" name="isShow" />
					</td>
				</tr>
				<tr>
				<td style="text-align:center" colspan="4">
			    <input type="button"  value="返回" id="backList"
						class="button" onclick="backList();" /> 
				<input type="button" value="存草稿" 
				id="draftTextNews"  class="button" onclick="insertTextNews('1');" /> 
				</td>
				</tr>
			</table>
		</div>

		<script type="text/javascript">
			function insertSpecialColumn() {
				var columnTitle = $("#columnTitle").val();

				$
						.ajax({
							url : "specialColumn.do?action=insertSpecialColumn",
							cache : false,
							type : "POST",
							dataType : "text",
							data : {
								"columnTitle" : columnTitle
							},
							success : function(content) {
								window.location.href = "${pageContext.request.contextPath}/specialColumn.do?action=getSpecialColumnList&columnTitle=";
								alert("新增成功！");
							},
							error : function() {
								alert("新增失败！");
							}
						});
			}
		</script>
	</form:form>
	--%>
</body>
</html>