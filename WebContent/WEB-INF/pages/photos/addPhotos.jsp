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
<title>维护图片新闻</title>
<link href="${pageContext.request.contextPath}/css/com.css"
	rel="stylesheet" type="text/css" media="screen" />
<link
	href="${pageContext.request.contextPath}/js/asyncbox/skins/ZCMS/asyncbox.css"
	type="text/css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.form_3.25.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/asyncbox/AsyncBox.v1.4.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/util.js"></script>
 
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ueditor/third-party/swfupload/swfupload.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ueditor/third-party/swfupload/swfupload.queue.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ueditor/third-party/swfupload/fileprogress.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ueditor/third-party/swfupload/handlers.js"></script>

	
<script type="text/javascript">
var swfu;  
window.onload = function() {  
    var settings = {  
        flash_url : "${pageContext.request.contextPath}/swfupload/swfupload.swf",  
        upload_url: "photosNews.do?action=mulFileUpload",  
        post_params: {"jsessionid" : ""},  
        file_size_limit : "100 MB",  
        file_types : "*.*",  
        file_types_description : "All Files",  
        file_upload_limit : 100,  
        file_queue_limit : 0,  
        custom_settings : {  
            progressTarget : "fsUploadProgress",  
            cancelButtonId : "btnCancel"  
        },  
        debug: false,  

        // Button settings  
        button_image_url: "${pageContext.request.contextPath}/images/TestImageNoText_65x29.png",  
        button_width: "65",  
        button_height: "29",  
        button_placeholder_id: "spanButtonPlaceHolder",  
        button_text: '<span class="theFont">Hello</span>',  
        button_text_style: ".theFont { font-size: 16; }",  
        button_text_left_padding: 12,  
        button_text_top_padding: 3,  
          
        // The event handler functions are defined in handlers.js  
        file_queued_handler : fileQueued,  
        file_queue_error_handler : fileQueueError,  
        file_dialog_complete_handler : fileDialogComplete,  
        upload_start_handler : uploadStart,  
        upload_progress_handler : uploadProgress,  
        upload_error_handler : uploadError,  
        upload_success_handler : uploadSuccess,  
        upload_complete_handler : uploadComplete,  
        queue_complete_handler : queueComplete  // Queue plugin event  
    };  

    swfu = new SWFUpload(settings);  
  }; 

/*
  var swfu;
  window.onload = function () {
    swfu = new SWFUpload({
        // Flash Settings
        flash_url :"${pageContext.request.contextPath}/js/ueditor/third-party/swfupload/swfupload.swf",
    
        // Backend settings
        //preserve_relative_urls: true,                 // 保留相对路径不做转换
        //upload_url : "${pageContext.request.contextPath}/uploadServlet", // servlet path
                 
        // Flash file settings
        file_types : "*.jpg;*.png",
        file_types_description : "Image File",
        file_size_limit : "10 MB",
        file_queue_limit : "1",
    
        // Event handler settings
      //  file_dialog_start_handler: fileDialogStart, // fired after selectFile is called.
       // file_queued_handler : fileQueued,           // fired after file selection dialog close.
        file_queue_error_handler : fileQueueError,  // fired when file was not queued.
        file_dialog_complete_handler : fileDialogComplete,// fired when all files queued.
                 
        upload_progress_handler : uploadProgress,  // upload status
        upload_error_handler : uploadError,        // fired when upload error thrown
        upload_success_handler : uploadSuccess,    // fired when server return a 200 status
        upload_complete_handler : uploadComplete,  // fired at the end of an upload cycle
    
        // Button Settings
        button_image_url : "${pageContext.request.contextPath}/js/ueditor/third-party/swfupload/TestImageNoText_65x29.png",
        button_placeholder_id : "btnPlaceHolder",
        button_width: 61,
        button_height: 22,
        button_action: SWFUpload.BUTTON_ACTION.SELECT_FILE, // 单选

        // debug infor
        debug:false
    });
};
*/

/*
//var upload1;
window.onload = function() {

    upload1 = new SWFUpload({

       // Backend Settings

       upload_url: "photosNews.do?action=savePhotoNews",//上传文件的action

       file_post_name: "file",

       post_params: {"contentId" :""},//$("#msgContentId").val()

          //要使用到的参数，contentId在后台设置set get方法，不传就为空

       // File Upload Settings

       file_size_limit : "10240",  // 100MB大小自己设置

       file_types : "*.*",

       file_types_description :"All Files",//文件类型自己控制

       file_upload_limit :"15",

       file_queue_limit :"15",


       file_dialog_start_handler : fileDialogStart,

       file_queued_handler : fileQueued,

       file_queue_error_handler : fileQueueError,

       file_dialog_complete_handler : fileDialogComplete,

       upload_start_handler : uploadStart,

       upload_progress_handler : uploadProgress,

       upload_error_handler : uploadError,

       upload_success_handler : uploadSuccess,

       upload_complete_handler : uploadComplete,



       // ButtonSettings

       button_image_url :"${pageContext.request.contextPath}/js/ueditor/third-party/swfupload/TestImageNoText_65x29.png",

       button_placeholder_id :"spanButtonPlaceholder1",

       button_width: 61,

       button_height: 22,

       button_text:"添加附件",

       button_text_left_padding:5,

       // Flash Settings

       flash_url : "${pageContext.request.contextPath}/js/ueditor/third-party/swfupload/swfupload.swf",

      custom_settings : { 
           progressTarget :"fsUploadProgress1",

           cancelButtonId :"btnCancel1"

       },


       debug: false

    });

}
*/

/*
function deleteAttach(attachId,value){

    if(confirm("确定要删除该附件吗?")){

       $.post("${ctx}/suqiu/topicattach-delete.action?key="

              + attachId,function(data) {

                  if (data =="no") {

                        alert("删除失败");

                     }else{

                         alert("删除成功");

                         window.location.reload();

                  }

         }

      ); 
    }

}*/


function savePhotoNews() {
	var options  = {
	           url:'photosNews.do?action=savePhotoNews',
	           type:'post',
	           dataType : "html",
	           beforeSubmit:checkFormData,
	           success:function(data){
	        	   var content = eval('(' + data + ')'); 
	        	   asyncbox.alert(content.msg, "信息窗口",function() {
	        		   document.location.href="${pageContext.request.contextPath}/photosNews.do?action=getPhotosNewsList&photosNewsTitle=&entryUser=&pressDateS=&pressDateE=";
	        	   });
	        	   
	           }
	      };
    $("#uploadFile").ajaxSubmit(options);
    return false; 

}
function checkFormData(){
	if($("#photosNewsTitle").val()==""){
		asyncbox.alert("请填写标题", "信息窗口");
		return false;
	}
}



function backList(){
	window.location.href="${pageContext.request.contextPath}/photosNews.do?action=getPhotosNewsList&photosNewsTitle=&entryUser=&pressDateS=&pressDateE=";
}

function refusePhotoNews(){
	 var photosNewsID=$("#photosNewsID").val();
	 var strURL = "${pageContext.request.contextPath}/photosNews.do?action=openRefusePhotoNews&photosNewsID="+photosNewsID;
	 var strRtnXML = window
			.showModalDialog(strURL, window,
					"dialogHeight:320px; dialogWidth:300px; center:Yes; help:No; status:No;");
	if (strRtnXML != undefined) {
		var values = strRtnXML.split(";");
		var newsID=values[0];
		var newsType =values[1];
		var refuseDesc =values[2];
		$.ajax({
			url : "photosNews.do?action=insertRefusePhotosNews",
			cache : false,
			type : "POST",
			dataType : "text",
			data : {			   
			   "newsID" : newsID,
		       "newsType"  : newsType,
		       "refuseDesc":refuseDesc
	     	},
				success : function(content){
				//asyncbox.alert("退回成功", "信息窗口");
				alert("退回成功");
				window.location.href="${pageContext.request.contextPath}/photosNews.do?action=getPhotosNewsList&photosNewsTitle=&entryUser=&pressDateS=&pressDateE=";
				},
				error : function(){
				alert("退回失败")
				//asyncbox.alert("退回失败", "信息窗口");
	 		}
		}); 
	}
	}

</script>

</head>
<body>
	<fieldset>
		<legend>图片新闻</legend>
		<form id="uploadFile" enctype="multipart/form-data" method="POST"
			name="uploadFile">
			<input type="hidden" id="photosNewsID" name="photosNewsID"
				value="${photosNewsID}">
			<table>
				<tr>
					<td>标题：</td>
					<td colspan="3" style="margin-left: 0px; text-align: left;"><input
						value="${photosNewsTitle}" style="margin-left: 0px;" type="text"
						id="photosNewsTitle" name="photosNewsTitle" /></td>
				</tr>
								<tr>
					<td>副标题：</td>
					<td colspan="3" style="margin-left: 0px; text-align: left;"><input
						value="${subPhotosNewsTitle}" style="margin-left: 0px;" type="text"
						id="subPhotosNewsTitle" name="subPhotosNewsTitle" /></td>
				</tr>
				<tr>
					<td>作者：</td>
					<td><input style="margin-left: 0px;" type="text"
						value="${writer}" name="writer" id="writer" /></td>
					<td>录入时间：</td>
					<td><input style="margin-left: 0px;" type="text"
						value="${entryDate}" name="entryDate" id="entryDate" />
					</td>
				</tr>
				<tr>
					<td>审稿人：</td>
					<td><input style="margin-left: 0px;" type="text"
						value="${approvaler}" name="approvaler" id="approvaler" /></td>
					<td>文档来源：</td>
					<td><input style="margin-left: 0px;" type="text"
						value="${photosNewsSource}" name="photosNewsSource" id="photosNewsSource" />
					</td>
				</tr>
			<tr>

              <td class="text">&nbsp;附件：</td>
              <td colspan='3'>
              <div id="header"> 

            <h1 id="logo">
    	  <a name="t0"></a>
   		  <a href="<%  
            String path = request.getContextPath(); 	 
            String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
            System.out.println(basePath);
            System.out.print(basePath);
          %>">SWFUpload</a>
    </h1>  
 
    <div id="version">v2.2.0</div>  
</div>  
              <div id="content">  
              <h2><a name="t1"></a>Simple Demo</h2>  
            <form id="form1" action="photosNews.do?action=mulFileUpload" method="post" enctype="multipart/form-data">  
          <p>This page demonstrates a simple usage of SWFUpload.  It uses the Queue Plugin to simplify uploading or cancelling all queued files.</p>  
  
            <div class="fieldset flash" id="fsUploadProgress">  
            <span class="legend">Upload Queue</span>  
            </div>  
        <div id="divStatus">0 Files Uploaded</div>  
            <div>  
                <span id="spanButtonPlaceHolder"></span>  
                <input id="btnCancel" type="button" value="Cancel All Uploads" onclick="swfu.cancelQueue();" disabled="disabled" style="margin-left: 2px; font-size: 8pt; height: 29px;">  
            </div>  
  
    </form>  
</div> 
              
              
              <%-- 
              <div id="uploadContent">
              <input id="txtFileName" type="text" disabled="disabled" value="" style="width:151px;"/>
              <span id="btnPlaceHolder"></span>
              <div id="uploadProgress">
              <div id="colorStatus"></div>
              <div id="percentTxt"></div>
              </div>
             </div>
<input id="btnStartUpload" type="button" value="开始上传" onclick="startUpload()" disabled="disabled" style="position:relative;left:200px;"/>
             --%>
     
              <%-- 
              <div style="padding-left:5px;">

              <span id="spanButtonPlaceholder1"></span>

              <input id="btnCancel1" type="button" value="取消上传" onclick="cancelQueue(upload1);" disabled="disabled"style="margin-left:2px;height:22px;font-size: 8pt;"/>

              <br/>
              
                <div class="fieldset flash" id="fsUploadProgress1">

                  <s:iterator value="topicalContent.Attachs"status="u">

                   <div class="progressWrapper" id='FWSWFUpload <s:property value="#u.index"/>' style="opacity:1;">

                     <div class="progressContainerblue">

                       <a class="progressCancel" href="#" style="visibility:visible;"> </a>

                         <div class="progressName"><input type="button" class="ico_attbig"><s:propertyvalue="attachName" /></div>

                         <div class="progressBarStatus">

                         <a onclick="deleteAttach('<s:property value="attachId"/>',this);" href="#this">删除</a></div>

                         <div class="progressBarComplete" style=""></div>

                      </div>

                  </div>

                 </s:iterator>

              </div>
              </div>
--%>
              </td>

           </tr> 
			
			</table>

			<hr>
			<div align="right">
				<input type="button" value="返回" onclick="backList();" class="button">
				<input type="button" value="预览" style="${isPreViewHide}" onclick="" class="button"> <input
					type="button" value="存草稿" style="${isDraftHide}" onclick="" class="button"> <input
					type="button" value="发表" style="${isPublishHide}" onclick="" class="button"> <input
					type="button" value="退回" style="${isRefuseHide}" onclick="refusePhotoNews();" class="button"> <input
					type="button" value="审核通过" style="${isAppHide}" onclick="" class="button">
			</div>
		</form>
	</fieldset>
</body>
</html>