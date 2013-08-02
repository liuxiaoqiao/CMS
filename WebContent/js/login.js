/**
 * @author f2907364
 */
 
$(document).ready(function(){
	var node;
	$('input').focusin(function(){
		node=this;
		if(node.type=="text"||node.type=='password'){
			$(node).css("border","1px solid #f78d06");
		}		
	});
	
	$('input').focusout(function(){
		node=this;
		if(node.type=="text"||node.type=='password'){
			$(node).css("border","1px solid #acacac");
		}
	});
	
	/*
	$("#botton_submit").mousemove(function(){
		$(this).css("background","transparent url(images/office2007/submitbg.jpg) no-repeat 4px 0px");
	});
	
	$("#botton_submit").mouseout(function(){
		$(this).css("background","transparent");
	});
	
	$("#botton_reset").mousemove(function(){
		$(this).css("background","transparent url(images/office2007/resetbg.jpg) no-repeat 4px 0px");
	});
	
	$("#botton_reset").mouseout(function(){
		$(this).css("background","transparent");
	});
	*/
});
