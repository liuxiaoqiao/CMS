<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>播放视频</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/homeCom.css" /> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/news.css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/flowplayer-3.2.14/flowplayer-3.2.11.min.js"></script>


</head>

<body>
<div class="bg_w1284">
	<div class="wrap">
		<%@ include file="/WEB-INF/pages/common/header.jsp" %>

		<%@ include file="/WEB-INF/pages/common/navigation.jsp" %>

		<div class="content">
			<div class="clearfix">
					<div class="breadCutNav">
						<b class="icon_home"></b> <span>当前位置：</span> <a href="#">首页</a>
						<span> &gt;</span> <a href="#" class="cur">交通视频</a>
					</div>

					<div align="center">
						<table border="0" id="main" cellspacing="0" cellpadding="0" >
							<tbody>
								<tr>
									<td align="center" height="28">
										<div >
											<font face="黑体"><span
												style="font-size: 14pt;" id="thread_subject">${videoName}</span></font>
										</div>
									</td>
								</tr>
								<tr>
									<td height="6"></td>
								</tr>
								<tr>
									<td align="center" bgcolor="#222222"  id="thevodview" height="500">
										<a href="${pageContext.request.contextPath}/${videoPath}" style="display:block;width:720px;height:480px" id="player"> </a>		
										<script type="text/javascript">
										var ctx = "${pageContext.request.contextPath}";
										flowplayer("player", ctx+"/resource/flowplayer-3.2.14/flowplayer-3.2.14.swf");
										</script>
									</td>
								</tr>
			
							</tbody>
						</table>
			
					</div>
					
			</div>
		</div>
		<%@ include file="/WEB-INF/pages/common/friendLink.jsp" %> 
		<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
	</div>
	
</div>

</body>
</html>