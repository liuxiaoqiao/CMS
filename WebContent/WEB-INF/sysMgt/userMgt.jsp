<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/sysManage.css" />
<style type="text/css">
body {
	background: #fff;
}
</style>
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"
	type="text/javascript"></script>
</head>
<body>
	<div class="searchBox">
		<h3>用户管理</h3>
		<ul>
			<li><label for="userNO">用户名：</label><input type="text"
				name="userNO" class="text" /> <label for="role">角色：</label><input
				type="text" name="role" class="text" /> <label for="dept">部门：</label><input
				type="text" name="dept" class="text" /><a href="javascript:;"
				class="btnBlue77_32">新增</a> <a href="javascript:;"
				class="btnBlue77_32">查询</a></li>
		</ul>
	</div>
	<table width="100%" border="0" class="infoList">
		<tr>
			<th width="5%">序号</th>
			<th width="45%">用户名</th>
			<th width="8%">姓名</th>
			<th width="8%">角色</th>
			<th width="8%">部门</th>
			<th width="26%">操作</th>
		</tr>

		<c:forEach items="${userList}" var="p" varStatus="status">
			<tr>
				<td>${status.count }</td>
				<td>${p.userNo }</td>
				<td>${p.userName }</td>
				<td>${p.roleDesc }</td>
				<td>${p.deptName }</td>
				<td><a href="javascript:;" class="btn44_20 btnBlue">编辑</a> <a
					href="javascript:;" class="btn44_20 btnBlue">删除</a></td>
			</tr>
		</c:forEach>


	</table>

</body>
</html>