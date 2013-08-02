<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/news.css" />
<link href="${pageContext.request.contextPath}/js/asyncbox/skins/ZCMS/asyncbox.css" type="text/css" rel="stylesheet" /> 
<link href="${pageContext.request.contextPath}/css/communication.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>


<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/asyncbox/AsyncBox.v1.4.js"></script>

<script type="text/javascript">
//twoColHeight("lH","rH");   

function alertInfo(message) {
		asyncbox.alert(message, "信息窗口", function() {
		});
	}
   
   function deleteCommunicationUser(Id){
	   var optionId=$("#optionId").val();
	   alert('ssss'+optionId);
		var programType = '010201';	
		asyncbox
		.confirm(
				"确定删除该条意见?",
				"信息窗口",
				function(action) {
					if (action == "ok") {
						$.ajax({
							url : "${pageContext.request.contextPath}/communication.do?action=deleteCommunicationUser",
							cache : false,
							type : "GET",
							dataType : "text",
							data :{
							 "Id" : Id,
							 "programType":programType
							 
								},
								success : function(content){
									window.location.href="${pageContext.request.contextPath}/communication.do?action=previewFormList&optionId="+optionId;
								
								}
							}
						);
					}});
		   
	   		}
   
</script>
<title>意见征集</title>
</head>
<body>
<fieldset style="width:800px;" >
   <legend >意见征集</legend>
	<form id="form">
		<div class="mainList" >
			<div class="communicationDetail">
				<h2>${optionTitle}</h2>
				<input value="${optionId}" type="hidden" id="optionId">
				<p class="dotLine_2px"></p>
				<ul class="tip clearfix">
                            <li class="al_c" style="float:center">征集时间：${currentsDate}~${currenteDate}</li>
                            <li class="al_c" style="float:right">共${readRecordCount}人参加</li>
                        </ul>
                        <br>
                        
				<div>${optionContent}</div>
			</div>
			<br>
			</br>
			<div  class="communicationDetail" >
			
			<c:forEach items="${communication}"  var="p" varStatus="xh">
			<table width=100% style="border-collapse:collapse;" >
       <tr align="center" bgcolor="#E0E0E0">
       
           <td width=20% style="font:Microsoft YaHei;text-align:left;"><b>姓名:</b><c:out value="${p.userName}"/></td>
           <td width=20%  style="font:Microsoft YaHei;text-align:left;"><b>单位:</b><c:out value="${p.userDept}"/></td>
           <td width=20% style="font:Microsoft YaHei;text-align:left;"><b>邮箱:</b><c:out value="${p.userMail}"/></td>
           <td width=20% style="font:Microsoft YaHei;text-align:left;"><b>IP来源:</b><c:out value="${p.userIp}"/></td>
           <td width=20% >
           <input type="button" value="删除" onclick="deleteCommunicationUser('${p.ID}');"></td>
       </tr>
       <tr bgcolor="#F0F0F0"><td colspan=5><div class="userCommend">${p.userCommend}</div>
       <br>
       </br>
       
       </td></tr>
       </table>
	</c:forEach>
	
	</div>
	
		</div>
	
		</form>
		
	</fieldset>
		
	
</body>
</html>