<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>重庆交委门户网站后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/sysManage.css" />
<style type="text/css">
body {
	background: #002760 url(images/sysManage/bg_sysLogin_repeatX.jpg)
		repeat-x;
}

.sysLogo {
	width: 288px;
	height: 87px;
	margin-left: -144px;
	background-image: url(images/sysManage/sysLogo_Large.png);
}
</style>
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
	if (window != top) {
		top.location.href = location.href;
	}

	function getObj(objName) {
		return document.getElementById(objName);
	}

	function validateAll() {
		var alertMessage = "";
		var userName = $('#username1').val();
		userName = userName.replace(/\s+$|^\s+/g, "");
		var password = document.getElementById('text_password').value;
		password = password.replace(/\s+$|^\s+/g, "");
		if (userName.length == 0 && password.length == 0) {
			alertMessage = "usernull";
			getObj("wrong").innerHTML = "<font color='red'>账号密码不能为空</font>";
		} else if (userName.length == 0) {
			alertMessage = "usernull";
			getObj("wrong").innerHTML = "<font color='red'>账号不能为空</font>";
		} else if (password.length == 0) {
			alertMessage = "usernull";
			getObj("wrong").innerHTML = "<font color='red'>密码不能为空</font>";
		} else {
			alertMessage = "";
		}
		if (alertMessage.length == 0) {
			$('#username').val(userName.toUpperCase());
			return true;
		} else {
			return false;
		}
	}

	$(function() {
		$("#btnLogin").click(function() {
			if (validateAll())
				$("form").submit();
			else
				return false;
		});

		$("#btnReset").click(function() {
			$("#username1").val("");
			$("#text_password").val("");
			return false;
		});
		document.onkeyup = function(ee) {
			var e = ee || event;
			var key = e.keyCode;
			if (key == 13) {
				$("#btnLogin").click();
			}
		};
		$("#username1").focus();
	});
</script>
</head>
<body>
	<div id="loginwrap" class="sys_login">
		<div class="sysLogo"></div>
		<form name="myform" action="j_spring_security_check" method="post">

			<ul class="loginInfo">
				<li><label for="username">用户名</label><input type="text"
					class="inputText" name="username1" id="username1" type="text"
					value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}" /><input
					type="hidden" id="username" name="j_username" /></li>
				<li><label for="password">密 码</label><input type="password"
					name="j_password" id="text_password" class="inputText" /></li>
				<li class="btn"><label>&nbsp;</label> <a href="javascript:;"
					class="btnBlue70_31 mr14" id="btnLogin"> <span class="a">登陆</span>
						<span class="b">登陆</span>
				</a> <a href="javascript:;" class="btnBlue70_31" id="btnReset"> <span
						class="a">重置</span> <span class="b">重置</span>
				</a></li>
				<li>
					<div style="height: 30px; margin-top: 6px;" align="center">
						<c:if test="${not empty param.error}">
							<span id="wrong"
								style="font-size: 12px; color: red; display: block;"
								class="login_txt">用户名或密码错误，请重新输入。</span>
						</c:if>
					</div>
				</li>
			</ul>
		</form>
	</div>
</body>
</html>