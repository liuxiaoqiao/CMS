<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>left</title>
	<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/left.css"/>
	<script type="text/javascript" src="js/left.js"></script>
	<style type="text/css">
	table {
	border:0px;
	width:120px;
	}
	tr{
	height:5px;
	}
	td {
	border:0px；
	}
	.bgitem{
	background:url(images/bgitem1.png);
	font-family: Verdana, Arial, Helvetica, AppleGothic, sans-serif;
	color:#2A7AA3;
	font-weight:bold;
	font-size:12px;
	height:25px;
	width:100%;
	padding-top:7px;
	border:0px;
	}
	.item{
	border:0px
	}
	a:LINK {
	text-decoration: none;
	font-family: Verdana, Arial, Helvetica, AppleGothic, sans-serif;
	color:#222222;
	font-size: 12px;
	}
	a:VISITED {
	text-decoration: none;
	font-family: Verdana, Arial, Helvetica, AppleGothic, sans-serif;
	color:#222222;
	font-size: 12px;
	}
	a:HOVER {
	text-decoration: none;;
	color:#222222;
	font-size: 12px;
	}
	a:ACTIVE {
	text-decoration: none;
	font-family: Verdana, Arial, Helvetica, AppleGothic, sans-serif;
	color:#222222;
	font-size: 12px;
	}
	.hover{
	background: url(images/im2.png);
	)

	ul li span{
		margin-left:15px;
		margin-right:5px;
		padding-bottom: 2px; 
		padding-top: 2px;
		text-decoration:none;
		color:#4579b9;
	}
	</style>

</head>
<body topmargin=0 leftmargin=0 bgcolor="#FFFFFF">
<div style="border: 2px #a9c9ee solid;height:1490px">
<div class="demo">

<div id="leftmenu">
		<div class="bgitem"><span style="margin-left:25px;margin-top:3px;">基本信息</span></div>
		<div class="bgitem"><span style="margin-left:30px;padding-bottom:2px;">公用区</span></div>
		<div class="content">
               <div class="boxheader">
                       <div class="boxbottom">
                             <div class="boxcontext">
                                 <ul style="margin-left:15px;">
                                    	<li>
                                            <span>北富廠區</span>
                                        </li>
                       
                                        <li>
                                            <span>龍華廠區</span>
                                        </li>
										<li>
                                            <span>觀瀾廠區</span>
                                        </li>  
										<li>
                                            <span>武漢廠區</span>
                                        </li>
										<li>
                                            <span>成都廠區</span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
					<div class="bgitem"><span style="margin-left:30px;margin-top:3px;">工作區</span></div>
					<div class="content">
						<div class="boxheader">
							 <div class="boxbottom">
								 <div class="boxcontext">
									 <ul style="margin-left:15px;">
                                    	<li>
                                            <span>北富廠區</span>
                                        </li>
                       
                                        <li>
                                            <span>龍華廠區</span>
                                        </li>
										<li>
                                            <span>觀瀾廠區</span>
                                        </li>   
										<li>
                                            <span>武漢廠區</span>
                                        </li> 
										<li>
                                            <span>成都廠區</span>
                                        </li> 
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
					<div class="bgitem"><span style="margin-left:30px;margin-top:3px;">管理區</span></div>
					<div class="content">
						 <div class="boxheader">
							 <div class="boxbottom">
								<div class="boxcontext">
									 <ul style="margin-left:15px;">
                                    	<li>
                                            <span onclick="mainframeinfo('ManagerInfo.do?action=setproxy',3);"><img src="images/shezhi.gif" alt="" border=0 height="15px" width="20px" style="vertical-align:middle;">&nbsp;代理人設置</span>
                                        </li>
                       
                                        <li>
                                            <span onclick="mainframeinfo('ManagerInfo.do?action=getManagerInfo',3);"><img src="images/userinfo.gif" alt="" border=0 height="15px" width="20px" style="vertical-align:middle">&nbsp;人員信息</span>
                                        </li>
										<li>
                                            <span onclick="mainframeinfo('ManagerInfo.do?action=authority',3);"><img src="images/detail.gif" alt="" border=0 height="15px" width="20px" style="vertical-align:middle">&nbsp;角色信息</span>
                                        </li>   
										<li>
                                            <span onclick="mainframeinfo('ManagerInfo.do?action=system',3);"><img src="images/jiagou.gif" alt=""	border=0 height="15px" width="20px" style="vertical-align:middle">&nbsp;組織架構</span>
                                        </li> 
										<li>
                                            <span onclick="mainframeinfo('ManagerInfo.do?action=filemanager',3);"><img src="images/Folders.gif" alt=""	border=0 height="15px" width="20px" style="vertical-align:middle">&nbsp;文件夾管理</span>
                                        </li> 
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
</div>
</div>
</div>
</body>
</html>
