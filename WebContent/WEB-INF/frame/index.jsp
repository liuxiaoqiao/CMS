<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>重庆交委门户后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/sysManage.css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		var sw = window.screen.availWidth;
		var sh = window.screen.availHeight;
		var w = sw - 210;
		var h = sh - 220;
		$("#divBody").css("width", sw - 20 + "px");
		$(".sysCon").css("width", w + "px");
		$(".sysCon").css("display", "block");
		$(".sys_header").css("width", sw - 20 + "px");
		//$("iframe").css("height", h - 120 + "px");
		$("iframe").css("height", h +100 + "px");
		$("#divBody").css("display", "block");

		$(".sysTopMenu li").click(function() {
			$(this).siblings().removeClass("cur");
			$(this).addClass("cur");
			toggleSubMenu($(this).attr("id"));
		});
		$(".sysTopMenu li:first-child").addClass("cur");
		var topMenuID = $(".sysTopMenu li:first-child").attr("id");
		toggleSubMenu(topMenuID);
	});

	function toggleSubMenu(pid) {
		$("#leftFrame").attr("src", "login.do?action=leftMenu" + "&pid=" + pid);
	}
</script>
</head>
<body>
	<div class="dividLine"></div>
	<div style="margin: 0 auto; white-space: no-wrap;">
		<!--sys_Header Start-->
		<div class="sys_header">
			<p class="sys_userInfo">
				<b class="i_user"></b> <span>您好，${userNO}（${roleDesc
					}），${curDate}</span>
			</p>
			<div class="sysLogo"></div>
			<a href="${pageContext.request.contextPath}/j_spring_security_logout"
				class="quit"><b class="i_quit"></b>登出</a>
		</div>
		<!--sys_Header End-->

		<!--sys_content Start-->
		<div class="sys_content clearfix" id="divBody" style="display: none;">
			<!--sysSubMenu Start-->
			<div class="sysSubMenu">
				<h2>内容管理系统</h2>
				<iframe src="" name="leftFrame" id="leftFrame" style="width: 175px;"
					frameborder="0" scrolling="auto"></iframe>
			</div>
			<!--sysSubMenu End-->
			<!--sysCon Start-->
			<div class="sysCon">
				<ul class="sysTopMenu">
					<c:forEach items="${topMenu}" var="p">
						<li id="${p.id }"><a href="javascript:;"
							style="cursor: hand;"> <b class="fL fillet"></b> <span>
									<c:out value="${p.name}" />
							</span> <b class="fR fillet"></b>
						</a></li>
					</c:forEach>

				</ul>
				<iframe name="mainFrame" id="mainFrame" style="width: 100%;"
					frameborder="0" scrolling="auto"></iframe>
			</div>
			<!--sysCon End-->
		</div>
		<!--sys_content End-->

	</div>
	<!--sys_footer Start-->
	<div class="sys_footer">版权所有：重庆市交通信息中心</div>
</body>
</html>
