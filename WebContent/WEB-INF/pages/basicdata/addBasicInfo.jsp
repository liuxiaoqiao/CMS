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
<title>维护基本数据类别内容</title>
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
	function addOrEditBasicInfo() {
		var typeID = $("#typeID").val();
		var options = {
			url : 'basicData.do?action=addOrEditBasicInfo',
			type : 'post',
			dataType : "html",
			beforeSubmit : checkFormData,
			success : function(data) {
				var content = eval('(' + data + ')');
				asyncbox
						.alert(
								content.msg,
								"信息窗口",
								function() {
									document.location.href = "${pageContext.request.contextPath}/basicData.do?action=getBasicInfoList&typeID="
											+ typeID + "&infoName=";
								});

			}
		};
		$("#uploadFile").ajaxSubmit(options);
		return false;

	}
	function checkFormData() {
		if ($("#infoName").val() == "") {
			asyncbox.alert("请填写名称", "信息窗口");
			return false;
		}
	}

	function backList() {
		var typeID = $("#typeID").val();
		document.location.href = "${pageContext.request.contextPath}/basicData.do?action=getBasicInfoList&typeID="
				+ typeID + "&infoName=";
	}
</script>

</head>
<body>
	<fieldset>
		<legend>基本数据类别内容</legend>
		<form id="uploadFile" enctype="multipart/form-data" method="POST"
			name="uploadFile">
			<input type="hidden" id=infoID name="infoID" value="${infoID}">
			<input type="hidden" id=typeID name="typeID" value="${typeID}">
			<table>
				<tr>
					<td>名称：</td>
					<td style="margin-left: 0px; text-align: left;"><input
						value="${infoName}" style="margin-left: 0px;" type="text"
						id="infoName" name="infoName" /></td>
				</tr>
				<tr>
					<td>描述：</td>
					<td style="margin-left: 0px; text-align: left;"><textarea
							name="infoDesc" value="${infoDesc}" id="infoDesc"></textarea></td>
				</tr>
			</table>

			<hr>
			<div align="right">
				<input type="button" value="返回" onclick="backList();" class="button">
				<input type="button" value="保存" onclick="addOrEditBasicInfo()"
					class="button">
			</div>
		</form>
	</fieldset>
</body>
</html>