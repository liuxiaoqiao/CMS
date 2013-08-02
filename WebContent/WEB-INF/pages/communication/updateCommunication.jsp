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
<title>编辑意见</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/managerutil.js"></script>
<link href="${pageContext.request.contextPath}/js/asyncbox/skins/ZCMS/asyncbox.css" type="text/css" rel="stylesheet" /> 
<link href="${pageContext.request.contextPath}/css/communication.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/ueditor/themes/default/css/ueditor.css"/>
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ueditor/ueditor.all.min.js"></script>
<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script >
$(document).ready(function() {
	 if ("${pageType}" == "EDIT") {
		  $("#divApproveBtn1").hide();
		  $("#divApproveBtn2").hide();
		 
	 }else{
	 } 	
	 
  });

</script>
</head>
<body>
<fieldset>
		<legend>意见征集</legend>
<form:form id="form">
      <input type="hidden" id="optionId" name="optionId"
			value="${optionId}" />
		<input type="hidden" id="programType" name="programType"
			value="${programType}" />
		<div>
			<table class="Condition" cellpadding="0" width="90%">
				<tr  class="lable">
					<td align="right" width=15% class="edittil">标题：</td>
					<td width="80%" align="left"><input type="text" style="width:80%;height:24px;" id="optionTitle" value="${optionTitle}"></td>
				</tr>

				<tr  class="lable">
					<td align="right" class="edittile">副标题：</td>
					<td align="left">
					<input type="text"  id="subTitle" style="width:80%;height:24px;" value="${subTitle}">
					</td>
				</tr>

				<tr  class="lable">
					<td align="right" class="edittil">时间起止：</td>
					<td align="left"><input  class="Wdate"
					id="sDate" type="text"
					onfocus="WdatePicker({readOnly:false,dateFmt:'yyyy.MM.dd' })"
					size="12" maxlength="30" readonly="false" value="${sDate}" style="width:20%;height:24px;" />
					至
					<input  class="Wdate"
					id="eDate" type="text"
					onfocus="WdatePicker({readOnly:false,dateFmt:'yyyy.MM.dd' })"
					size="12" maxlength="30" readonly="false" value="${eDate}" style="width:20%;height:24px;" /></td>
				</tr>
				<tr  class="lable">
					<td align="right" >
					<div class="optionsumtext">摘要：</div>
                    </td>
					<td align="left">
					<textarea  id="optionSummary" style="width:80%;" value="${optionSummary}" >${optionSummary}</textarea>
                     </td>
				</tr>
				
	   			<tr  class="lable">
					<td align="right" class="edittile">审稿人：</td>
					<td align="left">
					<select id="approvaler" name="selse" var="approvaler"  style="width:170px;height:25px;">
					<option selected="selected"></option>
					<c:forEach items="${approvalerList}" var="approvaler">
			          <option label="${approvaler.userName}" value="${approvaler.userName}" >${approvaler.userName}</option>
			        </c:forEach>
					</select>
					</td>
				</tr>
				
				<tr  class="lable">
					<td align="right"   colspan="1" class="edittile">内容： <br /></td>
					</tr>
					</table>
				    <table width="90%">
					<tr>
					<td > 
				     <script type="text/plain" value="${optionContent}" id="myEditor">
                      ${optionContent}
                     </script>
					</td>
				</tr>
				<tr  class="lable">
				 <td style="text-align:right" >
				
				
				<input type="button" style="${isPreViewHide}"  value="预览" id="preView"
						class="button" onclick="preViewView();"> 
				   <input type="button" value="存草稿" style="${isDraftHide}" 
						id="draftTextNews"  class="button" onclick="updateCommunication('1');"> 
					    <input type="button" style="${isPublishHide}"
						value="发表" id="publishTextNews" onclick="updateCommunication('2');" class="button">
						<input type="button" id="divApproveBtn1" value="退回" id="refuseTextNews" onclick="refuseView();" class="button">
					   <input type="button"	id="divApproveBtn2" value="审核通过" id="approvalTextNews" onclick="insertCommunication('3')" class="button">
						
						
						</td>
				</tr>
			</table>
		</div>
		
		
<script type="text/javascript">
     UE.getEditor('myEditor');
     // var editor = new UE.ui.Editor();
      //editor.render("myEditor");
      /* editor.ready(function(){
    	  var content=${optionContent};
    	  content = UE.utils.html(content);
          editor.setContent(content);
      }) */
      
      function preViewView(){
     	 var optionTitle=encodeURIComponent($("#optionTitle").val());
          var subTitle=encodeURIComponent($("#subTitle").val());
          var sDate=encodeURIComponent($("#sDate").val());
          var eDate=encodeURIComponent($("#eDate").val());
        	 var optionContent=encodeURIComponent(UE.getEditor('myEditor').getContent());
        	 var optionSummary=encodeURIComponent($("#optionSummary").val());
        	 var approvaler=encodeURIComponent($("#approvaler").val());
      	 
       	 $.ajax({
        			url : "${pageContext.request.contextPath}/communication.do?action=preViewViewS",
        			cache : false,
        			type : "POST",
        			dataType : "text",
        			data : {			   
        	            "optionTitle":optionTitle,
        	            "subTitle":subTitle,
        	            "sDate":sDate,
        	            "eDate":eDate,
        	            "optionContent":optionContent,
        	            "optionSummary":optionSummary,
        	            "approvaler":approvaler,
                	},
        			success : function(content){
        		
        				var strURL="${pageContext.request.contextPath}/communication.do?action=preViewViewT";
             		    var strRtnXML = window
             			showModalDialog(strURL, window,
             			"dialogHeight:700px; dialogWidth:800px; center:Yes; help:No; status:No;");
               			},
        		
               
        			error : function(){
        			  alert("保存失败！");
            		}
        		}); 
        	
      }
      
      function updateCommunication(sflag){ 
    	  
    	//  window.location.href="${pageContext.request.contextPath}/communication.do?action=updateCommunication22";
    	 var optionId=$("#optionId").val();
     	 var optionTitle=encodeURIComponent($("#optionTitle").val());
      	 var subTitle=encodeURIComponent($("#subTitle").val());
      	 var sDate=encodeURIComponent($("#sDate").val());
      	 var eDate=encodeURIComponent($("#eDate").val());
      	 var optionContent=encodeURIComponent(UE.getEditor('myEditor').getContent());
     	 var optionSummary=encodeURIComponent($("#optionSummary").val());
     	 var approvaler=encodeURIComponent($("#approvaler").val());
    	 if(optionTitle==""){
    			alert("请填写标题");
    			return false;
       	 }
       	 if(sflag=="2"||sflag=="3"){
       		 
       		 if(sDate==""||eDate==""){
          	 	  alert("请填写时间");
          	 	  return false;
          		 }
       		 if(optionContent==""){
             	 	  alert("请填写内容");
             	 	  return false;
             		 }
       	 }
    	 $.ajax({
     			url : "${pageContext.request.contextPath}/communication.do?action=updateCommunicationData",
     			cache : false,
     			type : "POST",
     			dataType : "text",
     			data : {			   
     		    	"optionId" : optionId,
     	            "optionTitle":optionTitle,
     	            "subTitle":subTitle,
     	            "sDate":sDate,
     	            "eDate":eDate,
     	            "optionContent":optionContent,
     	            "optionSummary":optionSummary,
     	            "approvaler":approvaler,
     	            "flag":sflag
             	},
     			success : function(content){
     				        alert("保存成功！");
     							window.location.href="${pageContext.request.contextPath}/communication.do?action=getCommunicationList&optionId="
     		       					 +"&optionTitle="+"&sDate="+"&eDate="+"&infoName="; 
     			},
     			error : function(){
     			  alert("保存失败！");
         		}
     		}); 
    	   
      }
      
      function refuseView(){
       var programType='010201';
       var optionId=$("#optionId").val();
       var strURL = "${pageContext.request.contextPath}/communication.do?action=openRefuseCommunication&optionId="+optionId;
       var strRtnXML = window
      		.showModalDialog(strURL, window,
      				"dialogHeight:320px; dialogWidth:300px; center:Yes; help:No; status:No;");
      if (strRtnXML != undefined) {
      	var values = strRtnXML.split(";");
      	var newsID=values[0];
      	var newsType =values[1];
      	var refuseDesc =values[2];
      	$.ajax({
      		url : "communication.do?action=insertRefuseTextNews",
      		cache : false,
      		type : "POST",
      		dataType : "text",
      		data : {			   
      		   "optionId" : optionId,
      	       "refuseDesc":refuseDesc
           	},
      			success : function(content){
      			alert("退回成功");
      			window.location.href="${pageContext.request.contextPath}/communication.do?action=getCommunicationList&optionId="
					 +"&optionTitle="+"&sDate="+"&eDate="+"&infoName=";
      			},
      			error : function(){
      			alert("退回失败")
       		}
      	}); 
      }  
      }
      
   
    </script>
    	</form:form>
    	</fieldset>
</body>
</html>