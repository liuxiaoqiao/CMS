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
<title>新增文字新闻</title>
<%-- 
<link href="${pageContext.request.contextPath}/css/com.css" rel="stylesheet" type="text/css" media="screen" />--%>
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/managerutil.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/ueditor/themes/default/css/ueditor.css"/>
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ueditor/ueditor.all.min.js"></script>

</head>
<body>
	<form:form id="form">
		<input type="hidden" id="programType" name="programType"
			value="${programType}" />
		<div>
			<table class="Condition" cellpadding="0" cellspacing="0">
				<tr>
					<td>标题：</td>				
					<td colspan="3" style="margin-left: 0px;text-align: left;">
					<input style="margin-left: 0px;" type="text" id="newsTitle" />
					<input type="button"
						class="button" value="..." onclick="outTextNewsLov()" />
					</td>
					
				</tr>

				<tr>
					<td>副标题：</td>
					<td colspan="3"><input type="text" id="subNewsTitle">
					</td>
				</tr>

				<tr>
					<td>版面位置：</td>
					<td><input type="radio" value="4" name="sectionPosition" checked />普通新闻
						<input type="radio" value="2" name="sectionPosition" />头版头条 <input
						type="radio" value="3" name="sectionPosition" />头版次条</td>
					<td>录入时间：</td>
					<td><input type="text" id="entryDate"></td>
				</tr>

				<tr>
					<td>作者：</td>
					<td><input type="text" id="writer"></td>
					<td>文档来源：</td>
					<td><select id="newsSource" name="newsSource" style="margin-left: 0px;padding-left: 0px;width: 170px;">
			         <c:forEach items="${newsSourceList}" var="newsSource">
			         <option label="${newsSource.label}" value="${newsSource.value}" />
			        </c:forEach>
		</select></td>
				</tr>

				<tr>
					<td>关键字：</td>
					<td colspan="3"><input type="text" id="newsKeyWord"></td>
				</tr>

				<tr>
					<td>摘要：</td>
					<td colspan="3"><textarea id="newsSummary">
                     </textarea>
                     </td>
				</tr>
				
	   			<tr>
					<td>审稿人：</td>
					<td colspan="3">
					<select id="approvaler" name="approvaler" style="margin-left: 0px;padding-left: 0px;width: 170px;">
			         <c:forEach items="${approverList}" var="approvaler">
			         <option label="${approvaler.label}" value="${approvaler.value}" />
			        </c:forEach>
		</select>
					</td>
				</tr>
				
				<tr>
					<td colspan="4">内容： <br /> 
				     <script type="text/plain" id="myEditor">
                      <p>这里我可以写一些输入提示</p>
                     </script>
					</td>
				</tr>
				<tr>
					<td style="width:200px">图片滚动展示：</td>
					<td><input type="radio" name="isPhotosShow" value="0" checked />否 <input
						type="radio" name="isPhotosShow" value="1"  />是</td>
					<td colspan="2">
					<input type="button"  value="预览" id="preView"
						class="button" onclick="preViewView();" /> 
				   <input type="button" value="存草稿" 
						id="draftTextNews"  class="button" onclick="insertTextNews('1');" /> 
					<input type="button" 
						value="发表" id="publishTextNews" onclick="insertTextNews('2');" class="button" />
						</td>
				</tr>
			</table>
		</div>

    <script type="text/javascript">
      UE.getEditor('myEditor');
      function preViewView(){
     	 var newsTitle=encodeURIComponent(encodeURIComponent($("#newsTitle").val()));
     	 var newsSource=encodeURIComponent(encodeURIComponent($("#newsSource").val()));
     	 var newsContent=encodeURIComponent(encodeURIComponent(UE.getEditor('myEditor').getContent()));
     	 var isPhotosShow =encodeURIComponent(encodeURIComponent( $("#isPhotosShow").val()));  	   	
   		$.ajax({
 			url : "textNews.do?action=preViewViewS",
 			cache : false,
 			type : "POST",
 			dataType : "text",
 			data : {			   
 		    	"newsTitle" : newsTitle,
 	            "newsSource"  : newsSource,
 	            "newsContent":newsContent,
 	            "isPhotosShow":isPhotosShow
         	},
 			success : function(content){	
 			var strURL="${pageContext.request.contextPath}/textNews.do?action=preViewViewT";
 		    var strRtnXML = window
 			showModalDialog(strURL, window,
 			"dialogHeight:700px; dialogWidth:800px; center:Yes; help:No; status:No;");
 			},
 			error : function(){
     		}
 		}); 
     	 
       }
      
      function insertTextNews(sflag){
    	 var programType=$("#programType").val();
     	 var newsTitle=encodeURIComponent($("#newsTitle").val());
     	 var subNewsTitle=encodeURIComponent($("#subNewsTitle").val());
     	 var sectionPosition=$("input[name='sectionPosition']:checked").val();
     	 var entryDate=$("#entryDate").val(); 
     	 var writer=encodeURIComponent($("#writer").val()); 
    	 var newsKeyWord=encodeURIComponent($("#newsKeyWord").val());
    	 var newsSummary=encodeURIComponent($("#newsSummary").val());
    	 var approvaler=encodeURIComponent($("#approvaler").val());  	 
    	 var newsContent=encodeURIComponent(UE.getEditor('myEditor').getContent());
    	 var isPhotosShow = $("input[name='isPhotosShow']:checked").val();
    	 var newsSource =$("#newsSource").val();
    	 
    	 if(newsTitle==""){
 			alert("请填写新闻标题");
 			return false;
    	 }
    	 if(sflag=="2"){
    		 if(writer==""){
    	 	  alert("请填写新闻作者");
    	 	  return false;
    		 }
    		 if(newsKeyWord==""){
       	 	  alert("请填写新闻关键字");
       	 	  return false;
       		 }
    		 if(newsContent==""){
          	 	  alert("请填写新闻内容");
          	 	  return false;
          		 }
    	 }
    	 
   		$.ajax({
 			url : "textNews.do?action=insertTextNews",
 			cache : false,
 			type : "POST",
 			dataType : "text",
 			data : {			   
 		    	"programType" : programType,
 	            "newsTitle"  : newsTitle,
 	            "subNewsTitle":subNewsTitle,
 	            "sectionPosition":sectionPosition,
 	            "entryDate":entryDate,
 	            "writer":writer,
 	            "newsSource":newsSource,
 	            "newsKeyWord":newsKeyWord,
 	            "newsSummary":newsSummary,
 	            "approvaler":approvaler,
 	            "newsContent":newsContent,
 	            "isPhotosShow":isPhotosShow,
 	            "flag":sflag
         	},
 			success : function(content){	
 			 window.location.href="${pageContext.request.contextPath}/textNews.do?action=getTextNewsList&newsTitle="
 					 +"&pressPerson="+"&pressDateS="+"&pressDateE="+"&programType="+programType;
 			 alert("新增成功！");
 			},
 			error : function(){
 			  alert("新增失败！");
     		}
 		});   
   		
    	 
      }
      
  	function outTextNewsLov() {
		var strURL = "${pageContext.request.contextPath}/textNews.do?action=getOutTextNewsListForLov&newsTitle="
				+ "&dataSource=&page=&count=";
		var strRtnXML = window
				.showModalDialog(strURL, window,
						"dialogHeight:500px; dialogWidth:800px; center:Yes; help:No; status:No;");
		if (strRtnXML != undefined) {
			var values = strRtnXML.split(";");
			var newsID=values[0];
			var programType=$("#programType").val();
			//var newsTitle=values[1];
			window.location.href="${pageContext.request.contextPath}/textNews.do?action=updateTextNewsView&newsID="+newsID+"&programType="+programType; 
		}
	}
  

    </script>
	</form:form>
</body>
</html>