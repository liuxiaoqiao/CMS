<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增杂志栏目</title>
<link
	href="${pageContext.request.contextPath}/js/asyncbox/skins/ZCMS/asyncbox.css"
	type="text/css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/asyncbox/AsyncBox.v1.4.js"></script>
	<script
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/util.js"></script> 
<script type="text/javascript">
	$(function() {
		if("${contentInfo.contentID}"!="")
			UE.getEditor('myEditor').setContent('${contentInfo.magazineContent}');
		getSelectList("magazineNews.do?action=getContentSourceList","contentSource",'${contentInfo.contentSource}');

	})
	function saveContent() {
		if($("#contentTitle").val()==""){
			asyncbox.alert("文章标题不得为空", "信息窗口", function() {});
			return false;
		}
		/* if($("#subContentTitle").val()==""){
			asyncbox.alert("文章副标题不得为空", "信息窗口", function() {});
			return false;
		} */
		if($("#pressPerson").val()==""){
			asyncbox.alert("作者不得为空", "信息窗口", function() {});
			return false;
		}
		if($("#entryTime").val()==""){
			asyncbox.alert("录入时间不得为空", "信息窗口", function() {});
			return false;
		}
		if($("#contentOrder").val()==""){
			asyncbox.alert("顺序不得为空", "信息窗口", function() {});
			return false;
		}
		if($("#contentSource").val()==""){
			asyncbox.alert("文档来源不得为空", "信息窗口", function() {});
			return false;
		}
		if($("#contentKeyWord").val()==""){
			asyncbox.alert("关键字不得为空", "信息窗口", function() {});
			return false;
		}
		if($("#contentSummary").val()==""){
			asyncbox.alert("摘要不得为空", "信息窗口", function() {});
			return false;
		}
		if(UE.getEditor('myEditor').getContent()==""){
			asyncbox.alert("文章内容不得为空", "信息窗口", function() {});
			return false;
		}
		$.ajax({
					url : "magazineNews.do?action=saveContent",
					cache : false,
					type : "POST",
					dataType : "json",
					data : {
						"catalogID":"${catalogID}",
						"contentID":"${contentInfo.contentID}",
						"contentTitle" : $("#contentTitle").val(),
						"subContentTitle" : $("#subContentTitle").val(),
						"pressPerson" : $("#pressPerson").val(),
						"recordDate" : $("#recordDate").val(),
						"contentOrder" : $("#contentOrder").val(),
						"contentSource" : $("#contentSource").val(),
						"keyWord" : $("#contentKeyWord").val(),
						"summary" : $("#contentSummary").val(),
						"content" : UE.getEditor('myEditor').getContent()
						

					},
					success : function(data) {

						asyncbox
								.alert(
										data.msg,
										"信息窗口",
										function() {
											/* $("#searchData").click(); */
											document.location.href = "${pageContext.request.contextPath}/magazineNews.do?action=turnToMagazineDetail&magazineID=${magazineID}";
										});

					}
				});

	}
	function checkData(){
		
	}
	/* function queryContentSourceList()
	{
		$.ajax({
    		type: "GET",
    		url: "magazineNews.do?action=getContentSourceList",
    		dataType:"json",
    		success: function(data){
    			var option = '<option value=""></option>';
    			$.each(data,function(i,item){
    				 option += '<option value=' + item.value + '>' + item.label + '</option>';
    			});
    			$("#contentSource").empty().append(option);
            },
    		error:function(data){
    		}
    	});	
	} */
	
</script>
</head>
<body>
	<fieldset>
		<legend>杂志文章</legend>
		<table>
			<tr>
				<td style="text-align: right;">标题：</td>
				<td colspan="3"><input type="text"
					value="${contentInfo.contentTitle}" id="contentTitle" style="width: 400px;"></td>
			</tr>
			<tr>
				<td style="text-align: right;">副标题：</td>
				<td colspan="3"><input type="text"
					value="${contentInfo.subContentTitle}" id="subContentTitle" style="width: 400px;"></td>

			</tr>
			<tr>
				<td style="text-align: right;">作者：</td>
				<td><input type="text" value="${contentInfo.pressPerson }" id="pressPerson">
				</td>
				<td>录入时间：</td>
				<td><input type="text" id="recordDate" name="recordDate" onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy.M.d HH:mm'})"
					size="16" maxlength="30" readonly="true" value="${contentInfo.recordDate}">
			</tr>
			<tr>
				<td>顺序号：</td>
				<td><input type="text" value="${contentInfo.contentOrder}" id="contentOrder"></td>
				<td>文档来源：</td>
				<td>
				<select id="contentSource" style="width: 130px"></select>
				<%-- <input type="text" id="contentSource" value="${contentInfo.contentSource}"> --%>
				</td>
			</tr>
			<tr>
				<td>关键字：</td>
				<td colspan="3"><input type="text"  id="contentKeyWord" value="${contentInfo.contentKeyWord}" style="width: 300px;">
				<input type="button" value="关键字抽取"></td>
			</tr>
			<tr>
				<td style="text-align: right;">摘要：</td>
				<td colspan="3"><textarea rows="10" cols="48" id="contentSummary">${contentInfo.contentSummary}</textarea></td>
			</tr>
			<tr>
				<td colspan="4" >内容：</td>
			</tr>
		</table>
		 <!-- <p id="tip">这里我可以写一些输入提示</p> -->
		<script type="text/plain" id="myEditor">
                     

                     </script>
		<script type="text/javascript">
			UE.getEditor('myEditor');
		</script>
		<!--    <div id="newscontent"> </div>
<script type="text/javascript">
	var editor = new baidu.editor.ui.Editor();
	editor.render('newscontent');
</script> -->

	</fieldset>
	<hr>
	<input type="button" value="预览" onclick="">
<input type="button" value="存草稿" onclick="" class="button">
<input type="button" value="发表" onclick="saveContent()">
<input type="button" value="返回" onclick="history.go(-1)" class="button">
</body>
</html>