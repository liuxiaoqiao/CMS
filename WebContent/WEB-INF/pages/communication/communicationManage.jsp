<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>意见征集</title>
<link href="${pageContext.request.contextPath}/css/com.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/js/asyncbox/skins/ZCMS/asyncbox.css" type="text/css" rel="stylesheet" /> 
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/selfpagination.js"></script>
<script
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/asyncbox/AsyncBox.v1.4.js"></script>  

<script type="text/javascript" >
  $(document).ready(function() {
 	 if ("${msg}" != "") {
		 alertInfo("${msg}");
	 } 	
   });
  
	function alertInfo(message) {
		asyncbox.alert(message, "信息窗口", function() {
		});
	}

	function queryData(p,s){
		var param="";		
			pageIndex=p;
            pageSize= s;
            optionTitle=$("#optionTitle").val()==null?"":encodeURIComponent(encodeURIComponent($("#optionTitle").val()));
            optionId=$("#row.optionId").val()==null?"":$("#row.optionId").val();
            infoName=$("#infoName").val()==null?"":$("#infoName").val();
            sDate=$("#sDate").val()==null?"":$("#sDate").val();
            eDate=$("#eDate").val()==null?"":$("#eDate").val();
            param+="&optionTitle="+optionTitle;
            param+="&optionId="+optionId;
            param+="&infoName="+infoName;
            param+="&sDate="+sDate;
            param+="&eDate="+eDate;
            param+="&pageIndex="+pageIndex;
            param+="&pageSize="+pageSize;
            
    			
			
		window.location.href="${pageContext.request.contextPath}/communication.do?action=queryCommunicationList"+param;
		//jumpPage(p, s);
		
	//function与DOM ID 冲突	
 
	}
	
	//分页控制
	function jumpPage(pageIndex, pageSize) {
		var param="";		
        optionTitle=$("#optionTitle").val()==null?"":encodeURIComponent(encodeURIComponent($("#optionTitle").val()));
        optionId=$("#row.optionId").val()==null?"":$("#row.optionId").val();
        infoName=$("#infoName").val()==null?"":$("#infoName").val();
        sDate=$("#sDate").val()==null?"":$("#sDate").val();
        eDate=$("#eDate").val()==null?"":$("#eDate").val();
        param+="&optionTitle="+optionTitle;
        param+="&optionId="+optionId;
        param+="&infoName="+infoName;
        param+="&sDate="+sDate;
        param+="&eDate="+eDate;
        param+="&pageIndex="+pageIndex;
        param+="&pageSize="+pageSize;
        
			
		
	window.location.href="${pageContext.request.contextPath}/communication.do?action=queryCommunicationList"+param;
		
/* 			$.ajax({
				url : "communication.do?action=queryCommunicationList",
				cache : false,
				type : "POST",
				dataType : "text",
				data : {			   
			    	"pageIndex" : jumpPage,
		            "pageSize"  : pageSize,
		            "optionTitle":$("#optionTitle").val()==null?"":encodeURIComponent($("#optionTitle").val()),
		            "optionId":$("#row.optionId").val()==null?"":$("#row.optionId").val(),
		            "infoName":$("#infoName").val()==null?"":$("#infoName").val(),		
		            "sDate":$("#sDate").val()==null?"":$("#sDate").val(),
		            "eDate":$("#eDate").val()==null?"":$("#eDate").val()
	        	},
	        	
				success : function(content){
	        		var pageData = eval('(' + content + ')');
					var pageIndex = eval(pageData.pageIndex);
					var pageSize = eval(pageData.pageSize);
					var pageCount = eval(pageData.pageCount);
					var tableData = eval(pageData.pageData);
					$("tbody").empty().append(createNewPageHTML(tableData,pageIndex,pageSize,pageCount));
					updatePageInfo(pageData.pageIndex,pageData.totalCount,pageData.pageCount);											
				},
				error : function(){
				alert("查询失败！");	
	    		}
			});  */
	}
	
	function updatePageInfo(pageIndex,totalCount,pageCount)
	{  
		$("#totalCount").empty().append(totalCount);
		$("#pageIndex1").empty().append(pageIndex);
		$("#pageCount1").empty().append(pageCount);
		$("#jumpPage1").val(pageIndex);
		$("#pageIndex").val(pageIndex);
		$("#pageCount").val(pageCount);
		hanlderPageLinkAttr('firstPage','perPage',
				'nextPage','lastPage',pageIndex,pageCount);
	}
	
	
	/* function createNewPageHTML(tableData)
	{
		var newPageHtml = "";
		for (var i = 0; i < tableData.length; i++) {
				newPageHtml += "<tr height='25px;'>";

				newPageHtml += "<td style='text-align:center;'>";
				//newPageHtml += tableData[i].optionTitle;
				newPageHtml+="<a onclick=previewFormList('"+tableData[i].OPTIONID+"')'>";
				newPageHtml+=tableData[i].OPTIONTITLE;
				newPageHtml+="</a>";
				newPageHtml += "</td>";

				newPageHtml += "<td style='text-align:center;'>";
				newPageHtml += tableData[i].INFONAME;
				newPageHtml += "</td>";
				
				newPageHtml += "<td style='text-align:center;'>";
				newPageHtml +="<input type='button' value='意见查看' class='button' onclick='alert();' />	";
				newPageHtml +="<input type='button' value='编辑' class='button' onclick='updateTextNewsView(${row.optionId});'/>";
				newPageHtml +="<input type='button' value='删除' class='button' onclick='deleteCommunication(${row.optionId});' />";
 
				
				alert(tableData[i].BTNAPPROVEENABLE);//BTNAPPROVEENABLE
				if(tableData[i].BTNAPPROVEENABLE=='true'){
					newPageHtml += "<input type='button' value='审核' class='button' onclick='approvalTextNews(${row.optionId});'/>";
				}else{
					newPageHtml += "<input type='button'  disabled='disabled' value='审核' class='button' onclick='approvalTextNews(${row.optionId});'/>";
				}
				
				
				newPageHtml += "</td>";

				newPageHtml += "</tr>";
			}
		return newPageHtml;
	} */
	
    function previewFormList(optionId){
    
    	window.location.href="${pageContext.request.contextPath}/communication.do?action=previewFormList&optionId="+optionId;

    }
	
	function insertCommunicationView(){
		var programType='010201';
		 window.location.href="${pageContext.request.contextPath}/communication.do?action=insertcommunicationView&programType="+programType;
	}
	
   function updateCommunicationView(optionId,pageType){
		var param="";
		var programType='010201';
		param+="&optionId="+optionId+"&programType="+programType+"&pageType="+pageType;
		
		window.location.href="${pageContext.request.contextPath}/communication.do?action=updateCommunicationView"+param;  
   }
   
   
   
   function deleteCommunication(optionId){

	var programType = '010201';	
	asyncbox
	.confirm(
			"确定删除该条意见?",
			"信息窗口",
			function(action) {
				if (action == "ok") {
					$.ajax({
						url : "${pageContext.request.contextPath}/communication.do?action=deleteCommunication",
						cache : false,
						type : "GET",
						dataType : "text",
						data :{
						 "optionId" : optionId,
						 "programType":programType
						 
							},
							success : function(content){
							
							asyncbox.alert(content, "信息窗口", function() {
								$("#searchData").click();
								});
							
							}
						}
					);
				}});
	   
   		}
   
   
/* },
error : function(){
alert("查询失败！");	
}
}); 
} */
   

</script>
   
   



</head>

<body >
<form:form id="form" >
 	<fieldset id="homeSearch"><legend>意见征集</legend>
 	<table width="200px">
 	<tr>
	<td align="right" width=4% style="font:14px/22px Microsoft YaHei;text-align:right;">标题：</td>
	<td align="left" width=20% style="text-align:left" colspan="2"><input type="text" id="optionTitle" style="width:310px;height:24px;" value="${optionTitle }">
    </td>
    
    </tr>
   
	<tr>
	<td align="right" style="font:14px/22px Microsoft YaHei;text-align:right;" >
	时间(开始或结束)：
	</td>
	
	<td align="left" style="text-align:left" colspan="2">
	<input class="Wdate"
					id="sDate"
					onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy.MM.dd'})"
					size="12" maxlength="30" readonly="false"  value="${sDate}" style="width:16%;height:24px;"/> 
	&nbsp;至
		<input class="Wdate"
					id="eDate"
					onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy.MM.dd'})"
					size="12" maxlength="30" readonly="false"  value="${eDate}" style="width:16%;height:24px;" /> 
	<input type="button" value="查询"  id="searchData" class="button" onclick="queryData('1','15');" />
	<input type="button" value="新增" id="insertData" class="button" onclick="insertCommunicationView();" />
	</td>
	
	</tr>
	</table>
		</fieldset>
  <input type="hidden" id="page" name="page" value="${page}" />
  <input type="hidden" id="count" name="count" value="${count}" />
 <%-- <fieldset><legend>列表</legend>--%>
<div>

	<display:table    style="margin-top: 2px; "
	list="${paginations.result}" id="row" export="false" 
	requestURI="${pageContext.request.contextPath}/communication.do"
	partialList="enable">
	
    <display:column title="序号">
    <a>${row_rowNum}</a>
    </display:column>
    <display:column title="标题" style="text-align:left">
     <a onclick="previewFormList('${row.optionId}')">${row.optionTitle}</a>	
    </display:column>   
    <display:column  title="状态" style="text-align:center;">	
    <a>${row.infoName }</a>
    </display:column> 
   	<display:column title="操作" style="width:30%" >

	<div align="center">
        <input type="button" value="意见查看" class="button" onclick="previewFormList('${row.optionId}');"  />	
	    <input type="button" value="编辑" class="button" onclick="updateCommunicationView('${row.optionId}','EDIT');" />
	    
	    
	    <input type="button" value="删除" class="button" onclick="deleteCommunication('${row.optionId}');" />
	    
	   
	    
	    <c:choose>
       		<c:when test="${row.btnApproveEnable==true }">
          		<input type="button" value="审核"    class="button" onclick="updateCommunicationView('${row.optionId}','APPROVE');"  />
       		</c:when>     
       		<c:otherwise>
               <input type="button" value="审核" disabled='disabled'   class="button" onclick="updateCommunicationView('${row.optionId}','APPROVE');"  />
       		</c:otherwise>
		</c:choose>
	    	
 	
	</div>
	</display:column>
   <display:setProperty name="paging.banner.placement" value="bottom"></display:setProperty> 
</display:table>
</div>
 <div>	
	<label style="float: left;margin-left: 5px;">&nbsp;&nbsp;&nbsp;共&nbsp;<span id = "totalCount" style="font-weight:bold;">${paginations.totalCount}</span>&nbsp;條記錄</label>
	<label style="float: right;margin-right: 5px;">
		<span id="firstPage" onclick="getFirstPage()"><a>首頁</a></span>&nbsp;
		<span id = "perPage" onclick="getPerPage()"><a>上一頁</a></span>&nbsp;
		<span id = "nextPage" onclick="getNextPage()"><a>下一頁</a></span>&nbsp;
		<span id="lastPage" onclick="getLastPage()" ><a >尾頁</a></span>&nbsp;&nbsp;頁次：
		<span id="pageIndex1" style="color:Red;font-weight:bold;">${paginations.pageIndex}</span>/
		<span id="pageCount1" style="font-weight:bold;">${paginations.pageCount}</span>頁&nbsp;&nbsp;
		<span style="color:Red;font-weight:bold;">15</span>條記錄/頁&nbsp;&nbsp;轉到第
		<input id="jumpPage" type="text" size="4" value="${paginations.pageIndex}" />頁
		<input id='jump' name='' type='image' src='${pageContext.request.contextPath}/images/go.gif' onclick='freeJump();return false;'>
		<input type="hidden" id="pageIndex" value="${paginations.pageIndex}"></input> 
        <input type="hidden" id="pageCount" value="${paginations.pageCount}"></input>
    </label>
    </div>
 <%-- </fieldset>--%> 
</form:form>
</body>
</html>