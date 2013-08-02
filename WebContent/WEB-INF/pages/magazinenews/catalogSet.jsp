<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增杂志栏目</title>
<link href="${pageContext.request.contextPath}/css/com.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/js/asyncbox/skins/ZCMS/asyncbox.css" 
type="text/css" rel="stylesheet" /> 
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/asyncbox/AsyncBox.v1.4.js"></script> 
	<style type="text/css">
   #addTable{
		width:99%;
	}
   #addTable td{
		border:0px;
   }
   
  </style>
<script type="text/javascript">
function saveCatalog() {
	$.ajax({
		url : "magazineNews.do?action=saveCatalog",
		cache : false,
		type : "POST",
		dataType : "json",
		data :{
		 "magazineID" : '${magazineID}',
		 "catalogID":'${catalogInfo.catalogID}',
		 "catalogName":$("#catalogName").val(),
		 "catalogOrder":$("#catalogOrder").val(),
		 "magazineNum":'${magazineNum}'
		 
		},
		success : function(data){
			
			asyncbox.alert(data.msg, "信息窗口", function() {
				/* $("#searchData").click(); */
				document.location.href="${pageContext.request.contextPath}/magazineNews.do?action=turnToMagazineDetail&magazineID=${magazineID}";
			});
			
			}
		}
	);

	
}
</script>
</head>
<body>
<fieldset>
<legend>杂志栏目</legend>
<table id="addTable">
<tr>
<td style="text-align: right;">
栏目名称：
</td>
<td style="text-align: left;">
<input type="text" value="${catalogInfo.catalogName}" id="catalogName">
</td>
</tr>
<tr>
<td style="text-align: right;" >
顺序号：
</td>
<td style="text-align: left;">
<input type="text" value="${catalogInfo.catalogOrder}" id="catalogOrder">
</td>

</tr>
<tr>
<td style="text-align: right;">
杂志期数：
</td>
<td style="text-align: left;">
<input type="text" value="${magazineNum }" id="catalogNum" readonly="readonly"  disabled="disabled" > 
</td>
</tr>
</table>

<hr>
<div align="right">
<input type="button" value="保存" onclick="saveCatalog()" class="button">
<input type="button" value="返回" onclick="history.go(-1)" class="button">
</div>
</fieldset>
</body>
</html>