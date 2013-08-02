<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/news.css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/util.js"></script>
<title>magazineNews</title>
<style type="text/css">
.column03 dt{
background-image: url();
}
</style> 
<script type="text/javascript">
$(function(){
	getSelectList("magazineNews.do?action=getMagazineNumList","magazineNumList","${magazineInfo.magazineID}");
})
function getContentInfo(contentID){
	 $.ajax({
	url : "magazineNews.do?action=getContentInfo",
	cache : false,
	type : "GET",
	dataType : "text",
	data : { contentID: contentID },
	success : function(data){
		var content = eval('(' + data + ')');
		/* console.log(data.CONTENTTITLE);	
		console.log(data.magazineContent); */
		//console.log(content);
		$("#contentTitle").html(content.CONTENTTITLE);
		$("#magazineContent").html(content.MAGAZINECONTENT);
	},
	error : function(){
	}
});  
			
	 }
function downloadMagazine(magazineURL){
	window.location.href="${pageContext.request.contextPath}/magazineNews.do?action=downloadMagazine&magazineURL="
		+encodeURIComponent(magazineURL);

	
}
function magazineNumChange(obj)
{
	if(obj.value=="")
	{
		
	}
	else{
	window.location.href="${pageContext.request.contextPath}/magazineNews.do?action=previewMagazineNews&magazineID="+obj.value;
	}
}
</script>
</head>
<%-- <body>
 <div class="wrap">
 <%@ include file="../common/header.jsp" %>
  
  <%@ include file="../common/navigation.jsp" %>
  <div class="breadCutNav">
                        <b class="icon_home"></b>
                        <span>当前位置：</span>
                        <a href="${pageContext.request.contextPath}/index.do?action=home">首页</a>
                        <span> &gt;</span>
                        <a href="#" onclick="backNewsList();">交通杂志</a>
                      
                    </div>
<div align="right">
<!-- <select>
<option>
2012年第三期
</option>
</select> -->
 <form:form   id="form1" name="form1" method="post" action=""  modelAttribute="magazineNews"> 
<form:select path="magazineID"  style="width:150px" onchange="magazineNumChange(this)">
				<form:option value=""></form:option>
				<form:options items="${magazineNumList}" itemValue="value" itemLabel="label"/>
				</form:select> 
			</form:form> 
</div>
<!--  <div>

<div>
123
</div>
<div style="float: left">
<img alt="" src="images/ad/focusPic01.jpg">
</div>
<div style="float: right;">
456
</div>
</div>  -->
<table width="100%" style="background-color: rgb(204,204,204);">
<tr>
<td width="30%"><img alt="" src="${photoURL }" style="padding: 20px;"></td>
<td width="40%">
<span style="font-weight:bold; font-size: 25px">重庆交通 </span> <span style="font-size: 15px">${magazineTitle}</span><br/>
<span style="color:white;background: black;font-size: 15px">主题策划：${magazineTitle}</span><br/>
<img alt="" src="images/global/bt_readonline.png" style="margin-top: 30px" onclick="downloadMagazine('${fileURL}')"><br/>
<img alt="" src="images/global/bt_download.png" style="margin-top: 10px" onclick="downloadMagazine('${fileURL}')"><br/>
</td>
<td width="30%" style="padding: 10px;">
<table style="background-color: rgb(235,235,235); " width="100%" height="350px">
<tr>
<td>
<span style="font-size: 20px;font-weight:bold;">|</span><span style="font-size: 20px;font-weight:bold;">目录</span><br>
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
</td>
</tr>
<c:set var="catalogID" value="0" ></c:set>
	<c:forEach items="${magazineNewsList}" var="p" varStatus="xh">
	<c:if test="${p.catalogID ne catalogID }">
	<tr>
	<td>
	<span style="color: blue;font-size: 15px;">${p.catalogName }</span>
	</td>
	</tr>
	<c:set var="catalogID" value="${p.catalogID}" ></c:set>
	</c:if>
	<c:if test="${p.catalogID eq catalogID }">
	<tr>
	<td>
	<a  onclick="getContent(${p.contentID})">${p.contentTitle }</a>
	</td>
	</tr>
	</c:if>
	
	</c:forEach>
</table>
</td>
</tr>

</table>
<div><img alt="" src="images/global/divisionline.png"></div>
<table width="100%">
<tr>
<td colspan="3" style="text-align: center;">
 <font size="5px" id="contentTitle">${firstContent.contentTitle}</font> 
<font size="5px">${magazineNewsList[1].contentTitle}</font>
</td>
</tr>
<tr>
<td colspan="3" id="magazineContent">${firstContent.magazineContent}</td>
</tr>
</table>
<!-- <div align="center" style="float:left;"> 
内容
</div> -->
<%@ include file="../common/friendLink.jsp" %>
</div>
 <%@ include file="../common/footer.jsp" %>
</body> --%>
<body onload="dateView()">
	<div class="bg_w1284">
		<div class="wrap">
			<!--header Start-->
			<%@ include file="../common/header.jsp"%>
			<!--header End-->
			<!--navTab-->
			<%@ include file="../common/navigation.jsp"%>
			<!--navTab End-->
			<!--content Start-->
			<div class="content">
				<div class="clearfix">
					<div class="breadCutNav">
						<b class="icon_home"></b> <span>当前位置：</span> <a href="#">首页</a> <span>
							&gt;</span> <a href="#">交通杂志</a>
					</div>
					<!--mgzSelect Start 下拉菜单-->
					<div align="right">
						<select id="magazineNumList" style="width: 80px" onchange="magazineNumChange(this)"></select>
					</div>
					<div class="mgzSelect">
						<!-- <div class="selectMenu" onclick="selectMenu('mgzSelect','mgzOption',event)">
                            <span id="mgzSelect" class="selectText" >2012年第三期</span>
                            <ul id="mgzOption" class="option" style="display:none">
                                <li>2012年第一期</li>
                                <li>2012年第二期</li>
                                <li>2012年第三期</li>
                                <li>2012年第四期</li>
                                <li>2012年第五期</li>
                                <li>2012年第六期</li>
                            </ul>
                        </div>-->  
                          </div>

						<!--mgzSelect End 下拉菜单-->
						<!--magazine Start-->
						<div id="magazine">
							<!--mgzPhotoIntro Start-->
							<div class="mgzPhotoIntro clearfix">
								<div class="column01">
									<img src="${magazineInfo.photoURL}" />
								</div>
								<div class="column02">
									<h3 class="clearfix">
										<span title="重庆交通"></span><b>${magazineInfo.magazineTitle}</b>
									</h3>
									<dl>
										<dt>主题策划：${magazineInfo.magazineTitle}</dt>
										<%-- <dd class="btn_read" title="在线阅读"
											onclick="downloadMagazine('${magazineInfo.fileURL}')"></dd> --%>
											<%-- <dd class="btn_downLoad" title="在线下载"
											onclick="downloadMagazine('${magazineInfo.fileURL}')"></dd> 图片错位，无显示--%>
											<dd>
											<img alt="" src="images/global/bt_readonline.png"  onclick="downloadMagazine('${magazineInfo.fileURL}')">
											</dd>
										<dd>
											<img alt="" src="images/global/bt_download.png"  onclick="downloadMagazine('${magazineInfo.fileURL}')">
											</dd>
									</dl>
								</div>
								<div class="column03">
									<h3 title="目录"></h3>
									<dl>
										<c:set var="catalogID" value="0"></c:set>
										<c:forEach items="${magazineNewsList}" var="p" varStatus="xh">
											<c:if test="${p.catalogID ne catalogID }">
												<dt title="${p.catalogName}" style="font:bold 16px/30px \9ED1\4F53;">${p.catalogName}</dt>
												<c:set var="catalogID" value="${p.catalogID}"></c:set>
											</c:if>
											<c:if test="${p.catalogID eq catalogID }">
												<dd><a onclick="getContentInfo(${p.contentID})">${p.contentTitle}</a></dd>
											</c:if>

										</c:forEach>
										<!-- <dt title="交通要闻" class="title01"></dt>
                                    <dd><a href="javascript:;">国家“公交都市”重庆首批试点</a></dd>
                                    <dd><a href="javascript:;">内河船型标准化，重庆经验向全国推广</a></dd>
                                    <dd><a href="javascript:;">滕宏伟：“星球”高速服务区  便民服务进万家</a></dd>
                                    <dt title="主题策划" class="title02"></dt>
                                    <dd><a href="javascript:;">高速公路管理的重庆智慧</a></dd>
                                    <dd><a href="javascript:;">严管！高速公路安全畅通的秩序构建</a></dd>
                                    <dd><a href="javascript:;">及时！新媒体发力与交通智能化</a></dd>
                                    <dd><a href="javascript:;">高效！联动机制下的整合力量</a></dd>
                                    <dd><a href="javascript:;">创新！小办法有大智慧</a></dd> -->
									</dl>
								</div>
							</div>
							<!--mgzPhotoIntro End-->
							<div class="dividerLine">
								<div class="Line130_10"></div>
								<b class="triangleGray32_16"></b>
							</div>
							<div class="mgzTextIntro">
								<!-- <h2>国家“公交都市”重庆首批试点</h2>
								<div>
									<p>据重庆市发改委消息，在建的渝利铁路有望年内正式开通运营。渝利铁路开通后，重庆到武汉的旅行时间将从目前的15个小时缩短至5个小时，从重庆发出的动车组抵达武汉后，可以沿目前以武汉为中心的高速铁路网驶往国内多座城市。这意味着重庆将正式接入国内高速铁路网络。其中，重庆到北京的旅行时间将从目前的23小时缩短</p>
									<p>至10小时左右，到广州的旅行时间将从目前的22小时缩短至8小时左右。
										“从重庆坐高铁到上海，票价将按照国家标准制定，但肯定比汽车要便宜。”渝利铁路有限公司副总经理袁步德表示，渝利铁路为双线高速铁路，修通后将以客运为主，列车时速为200km/h。运营初期将开通客运列车25至30对，货运列车10对。其中，货运列车将采用双层集装箱式专列。渝利铁路在重庆境内途径渝北、江北、长寿、涪陵、丰都和石柱等6个区县，在湖北省利川境内的凉雾车站与已修成的宜万铁路接轨。渝利铁路修通后，丰都和石柱两县将结束境内无铁路的历史。而随铁路修建的丰都、石柱火车站，也将成为两县的第一座火车站。据了解，渝利铁路修通后，重庆主城到石柱的时间将从目前的3小时缩短为1小时10分钟；而从主城到丰都仅需要40分钟，和目前所需时间相比将缩短至少一倍以上。
										袁步德表示，渝利铁路沿线地质构造复杂，属于典型的西南山区铁路。铁路修建过程中，要克服山区特有的危岩、落石、岩溶、突水等多种地质风险。因此，桥梁、隧道在全段铁路中所占比例高达81%，其中的蔡家沟双线特大桥，最高桥墩达
										到了139米，是目前世界上双线铁路最高桥。
										据了解，渝利铁路是国家规划的沪——汉——渝——蓉铁路大通道的组成部分，这一东西铁路大通道最早见于孙中山先生百年前“沿长江铁路”的设想。目前，大通道其余路段已全部修通。渝利铁路的建成，也将标志着这条。</p>
								</div> -->
								<h2 id="contentTitle">${firstContent.contentTitle}</h2>
								<div id="magazineContent">
									${firstContent.magazineContent}
								</div>
							</div>
						</div>
						<!--magazine End-->
					</div>


					<%@ include file="../common/friendLink.jsp"%>
				</div>
				<%@ include file="../common/footer.jsp"%>
</body>
</html>
