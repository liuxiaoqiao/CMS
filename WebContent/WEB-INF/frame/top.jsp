<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<title></title>
<style type="text/css">
.top {
	background: url(images/top.png);
	height: 71px;
	width: 100%;
}

.intop {
	margin-top: 42px;
	margin-left: 187px;
}

#topTags ul li {
	width: 100px;
	height: 21px;
	margin-right: 4px;
	float: left;
	display: block;
	text-align: center;
	cursor: pointer;
	padding-top: 5px;
	color: #15428B;
	font-size: 12px;
}

#topTags ul li span {
	margin-left: 1px;
	padding-top: 5px;
}
</style>

<script>
	var count = 0;
	function openNew(j, name, obj) {
		function $(id) {
			return document.getElementById(id)
		}
		var menu = $("topTags").getElementsByTagName("ul")[0];
		if ($("p" + j) == null) {
			var tagMenu = document.createElement("li");
			tagMenu.id = "p" + j;
			tagMenu.innerHTML = name
					+ "&nbsp;&nbsp;"
					+ "<img src='images/off.gif' style='vertical-align:middle;padding-right:5px;'/>";

			tagMenu.onclick = function(evt) { //添加Tag事件
				clearStyle();
				tagMenu.style.background = 'url(images/tabbg1.gif) repeat-x';
				parent.frames["leftFrame"].cascade(obj); //展開或收縮左側菜單
				parent.frames["mainFrame"].changetabs(j); //控制主页面的顯示
			}
			menu.appendChild(tagMenu);
			$("p" + j).style.background = 'url(images/tabbg1.gif) repeat-x';
			count++;

			tagMenu.getElementsByTagName("img")[0].onclick = function(evt) { //添加img點擊事件(關閉)
				evt = (evt) ? evt : ((window.event) ? window.event : null);
				if (evt.stopPropagation) {
					evt.stopPropagation()
				} //取消opera和Safari冒泡行為;
				this.parentNode.parentNode.removeChild(tagMenu);//刪除當前標簽
				parent.frames["leftFrame"].close(obj);
				count--;
				//	$("intop").each(function(){
				//alert($(this).id);
				//		alert("a");
				//	});
				parent.frames["mainFrame"].changetabs(count - 1); //關閉選項卡時改變mainFrame中內容

			}
		} else {
			$("p" + j).style.background = 'url(images/tabbg1.gif) repeat-x';
		}
	}

	function clearStyle() {
		function $(id) {
			return document.getElementById(id);
		}
		var menu = $("topTags").getElementsByTagName("ul")[0];
		for (i = 0; i < count; i++) {
			menu.getElementsByTagName("li")[i].style.background = 'url(images/tabbg2.gif) repeat-x';
		}
	}
</script>
</head>

<body topmargin=0 leftmargin=0>
	<div class="top" id="topTags">
		<ul class="intop">
		</ul>
		<span style="float: right; margin-right: 5px;"> <span>${roleDesc}：${userNO}</span>
			<span style="cursor: pointer;">修改密码</span>| <a
			style="text-decoration: none; color: #000000;"
			href="${pageContext.request.contextPath}/j_spring_security_logout">
				退出</a>
		</span>
	</div>
</body>
</html>
