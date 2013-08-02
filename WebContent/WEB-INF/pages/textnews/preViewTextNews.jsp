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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/news.css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/main.js"></script>

<script type="text/javascript">
   twoColHeight("lH","rH");   
</script>
<title>文字新闻预览</title>
</head>
<body>
	<form id="form">
		<div class="mainList" id="lH">
			<div class="newsDetail">
				<h2>${newsTitle}</h2>
				<p class="dotLine_2px"></p>
				<ul class="tip clearfix">
					<li class="al_l">${currentDate} 文章来源：${newsSourceName}</li>
					<li class="al_c">共&nbsp;人阅读</li>
					<li class="share"><a href="#" class="sinaBlog" alt="新浪微博"></a>
						<a href="#" class="qZone" alt="腾迅空间"></a> <a href="#"
						class="qqBlog" alt="腾迅微博"></a><span>分享到：</span>
					</li>

				</ul>
				<div>${newsContent}</div>
				<dl class="evaluate clearfix">
					<dt>&nbsp;</dt>
					<dd class="sy">
						<b></b> <span>实用</span> <em>0%</em>
					</dd>
					<dd class="gr">
						<b></b> <span>感人</span> <em>0%</em>
					</dd>
					<dd class="kx">
						<b></b> <span>开心</span> <em>0%</em>
					</dd>
					<dd class="wl">
						<b></b> <span>无聊</span> <em>0%</em>
					</dd>
					<dd class="ng">
						<b></b> <span>难过</span> <em>0%</em>
					</dd>
					<dd class="hd last">
						<b></b> <span>火大</span> <em>0%</em>
					</dd>
				</dl>
			</div>
		</div>


	</form>
</body>
</html>