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
<link href="${pageContext.request.contextPath}/css/com.css" rel="stylesheet" type="text/css" media="screen" />
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/managerutil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/util.js"></script>
<script type="text/javascript">
	window.name = "select_outtextnews";

	 $(document).ready(function()
			 {
		 $("a").each(function(){
			$(this).attr("target","select_outtextnews");
			 });
		 });

	function submitRefuseDesc()
	{
	 var newsID = $("#newsID").val();
	 var newsType =  $("#newsType").val();
	 var refuseDesc =trim($("#refuseDesc").val());
	 if(refuseDesc==""){
		 alert("请填写退回原因");
		 return false;
	  }
		window.returnValue = newsID+";"+newsType+";"+refuseDesc;
		window.close(); 
	}
	 

	
</script>
<title>网页对话框</title>
</head>
<body style="width:300px">

<form id="form"  target="select_outtextnews">
<fieldset><legend>退回原因</legend>
<div>
<input type="hidden" id="newsID" name="newsID" value="${newsID}" />
<input type="hidden" id="newsType" name="newsType" value="${newsType}" />
<table class="Condition" cellpadding="0" cellspacing="0">
	<tr>
	<td>
	<textarea rows="10" cols="30" id="refuseDesc">
     </textarea>
	</td>
	</tr>
	<tr>
	<td>
	 <input type="button" value="退回" onclick="submitRefuseDesc();" class="button">
	</td>
	</tr>
</table>
</div>
</fieldset>

</form>
</body>
</html>