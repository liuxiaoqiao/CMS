<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu</title>
<link
	href="${pageContext.request.contextPath}/js/zTree/css/zTreeStyle/zTreeStyle.css"
	rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/zTree/js/jquery.ztree.core-3.5.min.js"></script>

<script type="text/javascript">
	$(function() {

		var setting = {
			async : {
				enable : true,
				url : "login.do?action=getMenu&pid=${parentID}",
				/* 				autoParam:["id", "name=n", "level=lv"],
				 otherParam:{"otherParam":"zTreeAsyncTest"}, */
		
				type : "post",
				dataType : "text"

			},
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					idPKey : "pId",
					rootPid : 0
				}
			}

		};
		$.fn.zTree.init($("#treeMenu"), setting);
	});
</script>
</head>
<body>
	<div>
		<ul id="treeMenu" class="ztree"></ul>
	</div>
</body>
</html>