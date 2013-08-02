<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<style type="text/css">
table {
	border: RGB(223,236,251) 1px solid;
	margin-top:20px;
	table-layout:fixed;
}
tr{
	font-family: Verdana, Arial, Helvetica, AppleGothic, sans-serif;
	color:#052E47;
	font-size: 12px;
	height:25px;
}
td {
	border: RGB(223,236,251) 1px solid;
	text-align:center;
}
th {
	border: RGB(223,236,251) 1px solid;
	text-align:center;
}
.content{
	border:#DEF1FC 1px solid;
	font-size: 12px;
	padding: 0px;
	width:90%;
	margin:40px 0px 0px 0px;
}
.top1{
	text-align:left;
	padding:10px 0px 10px 8px;
	height:40px;
	width:100%;
	float:left;
	font-size: 12px;
}
.top2{
	background:url(operat1.png);
	height:40px;
	width:100%;
	float:right;
}
.left{
	float: left;
	color:#3666aa;
	width: 25%;
	height:448px;
	border: 1px #a9c9ee solid;
	text-align: center;
	padding: 10px;
}
.right{
	float: left;
	border: 1px;
	border: 1px #a9c9ee solid;
	color::#052E47;
	height:448px;
	width: 75%;
}
.operate1{
	float: left;
}
.operate2{
	float: right;
	margin-top:10px;
}
a:LINK {
	text-decoration: none;
	font-family: Verdana, Arial, Helvetica, AppleGothic, sans-serif;
	color:#000000;
}
a:VISITED {
	text-decoration: none;
	font-family: Verdana, Arial, Helvetica, AppleGothic, sans-serif;
	color:#000000;
}
a:HOVER {
	text-decoration: none;;
	color:#000000;
}
a:ACTIVE {
	text-decoration: none;
	color:#000000;
}
.itop1{
	width:200px;
	height:20px;
	background:#B3D3F3;
	float:left;
	padding-top:10px;
	font-size:12px;
	font-weight:bolder;
}

.itop2{
	width:90%;
	height:145px;
	background:#B3D3F3;
	float:left;
	padding:3px;
	margin-bottom:10px;
}
.icontent{
	background:#F6F9FD;
	width:100%;
	height:100%;
	padding-top:5px;
}
</style>

<!-- <script>
var flag=-1;

//function $(id){return document.getElementById(id);}
function mainchange(id){
	clearContent();
	$("#welcome").attr("style", "display:none;");
	$("#maincontent"+id).attr("style", "display:block;");
	flag=id;
	reinitIframe(flag);
}

function reinitIframe(id){
	if(id!=-1){
		var iframe = document.getElementById("frame"+id);
		var bHeight = iframe.contentWindow.document.body.scrollHeight;
		var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
		var height = Math.max(bHeight, dHeight);
		iframe.height =  height;
	}else{
		if(flag!=-1){
			var iframe = document.getElementById("frame"+flag);
			var bHeight = iframe.contentWindow.document.body.scrollHeight;
			var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
			var height = Math.max(bHeight, dHeight);
			iframe.height =  height;
		}
	}
}

window.setInterval("reinitIframe(-1)", 100);

//隱藏所有選項卡
function clearContent(){
	for(var i=0;i<4;i++){
		$("#maincontent"+i).attr("style", "display:none;");
	}
}

//控制選項卡的顯示
function changetabs(id){
	clearContent();
	if(id=="-1"){
		$("#welcome").attr("style", "display:block;");
	}
	else{
		$("#maincontent"+id).attr("style", "display:block;");
	}
}

//主页面鏈接
function linktonewpage(link,num){
	clearContent();
	$("#maincontent"+num).attr("style", "display:block;");
	$("#frame"+num).attr("src", link);
	reinitIframe(num);
}

</script> -->


</head>

<body topmargin=0 leftmargin=0 bgcolor="#FFFFFF" style="text-align: center;">
<div id="welcome"></div>
<%-- 
<div id="welcome">
<table  cellspacing="0" width="90%">
<tr>
	<td colspan="4" style="background:#E4F1FA;text-align:left;">
		<fmt:message key="test"/>
	</td>
</tr>
<tr>
	<th>序号</th>
	<th>描述</th>
	<th>状态</th>
	<th>截止时间</th>
</tr>
<tr>
	<td>1</td>
	<td>提交XX模具的设计图
	</td>
	<td>待完成
	</td>
	<td>2011.9.27</td>
</tr>
<tr>
	<td>2</td>
	<td>提交XX模具的设计图
	</td>
	<td>待完成
	</td>
	<td>2011.9.27</td>
</tr>
<tr>
	<td>3</td>
	<td>提交XX模具的设计图
	</td>
	<td>待完成
	</td>
	<td>2011.9.27</td>
</tr>
</table>
<table  cellspacing="0" width="90%">
<tr>
	<td colspan="4" style="background:#E4F1FA;text-align:left;">
		系统公告
	</td>
</tr>
<tr>
	<th>序号</th>
	<th>描述</th>
	<th>状态</th>
	<th>截止时间</th>
</tr>
<tr>
	<td>1</td>
	<td>提交XX模具的设计图
	</td>
	<td>待完成
	</td>
	<td>2011.9.27</td>
</tr>
<tr>
	<td>2</td>
	<td>提交XX模具的设计图
	</td>
	<td>待完成
	</td>
	<td>2011.9.27</td>
</tr>
<tr>
	<td>3</td>
	<td>提交XX模具的设计图
	</td>
	<td>待完成
	</td>
	<td>2011.9.27</td>
</tr>
</table>
<table  cellspacing="0" width="90%">
<tr>
	<td colspan="4" style="background:#E4F1FA;text-align:left;">
		维护人员
	</td>
</tr>	
<tr>
	<th>序号</th>
	<th>姓名</th>
	<th>移动电话</th>
	<th>公司短号</th>
</tr>
<tr>
	<td>1</td>
	<td>张三
	</td>
	<td>15888888888</td>
	<td>888888</td>
</tr>
<tr>
	<td>2</td>
	<td>张三
	</td>
	<td>15888888888</td>
	<td>888888</td>
</tr>
<tr>
	<td>3</td>
	<td>张三
	</td>
	<td>15888888888</td>
	<td>888888</td>
</tr>

</table>
</div>

<div id="maincontent0" style="display:none">
	<iframe id="frame0" src="baseInfo.do?action=getBaseInfo" height="100%" width="100%" frameborder="0" scrolling="no"></iframe>
</div>

<div id="maincontent1" style="display:none">
	<iframe id="frame1" src="publicAreaInfo.do?action=getPublicAreaInfo" height="100%" width="100%" frameborder="0" scrolling="no"></iframe>
</div>

<div id="maincontent2" style="display:none">
<iframe id="frame2" src="workAreaInfo.do?action=getWorkAreaInfo" height="100%" width="100%" frameborder="0" scrolling="no"></iframe>
</div>

<div id="maincontent3" style="display:none">
<iframe id="frame3" src="ManagerInfo.do?action=getManagerInfo" height="100%" width="100%" frameborder="0" scrolling="no"></iframe>
</div> --%>
</body>
</html>
	