$(function(){
	/*
	@:后台管理系统树形菜单JS
	@:xiebei 20130727
	*/
	$(".t_first").click(function(){
		if($(this).next(".t_firstCon").is(":visible"))
			{
			$(this).next(".t_firstCon").hide();
			$(this).find("b").removeClass("minus_t1").addClass("add_t1");
			$(this).removeClass("cur");
			}
		else{
			$(this).next(".t_firstCon").show();
			$(this).find("b").removeClass("add_t1").addClass("minus_t1");
			$(this).addClass("cur").siblings("h3,ul").removeClass("cur").find(".t_first b").addClass("add_t1");
			}
	});
	$(".t_sec").click(function(){

		if($(this).siblings(".t_secCon").is(":visible"))
			{
			$(this).siblings(".t_secCon").hide();
			$(this).find("b").removeClass("minus_t2").addClass("add_t2");
			$(this).removeClass("cur");
			}
		else{
			$(this).next(".t_secCon").show();
			$(this).find("b").removeClass("add_t2").addClass("minus_t2");
			$(this).addClass("cur").find("b").removeClass("add_t2").addClass("minus_t2");
			}

	});
});
/*=============
@:下拉菜单 JS
@:xiebei 20130706
================*/
var curZindex = 10;
function selectMenu(select,option,ev){
	curZindex++;
	var span = document.getElementById(select);
	var ul = document.getElementById(option);
	var li = document.getElementById(option).getElementsByTagName('li');
	var ev=ev||window.event;
	$("#"+select).parent().css("z-index",curZindex);
	if(ul.style.display == 'none'){
			$(".smLine ul").css("display","none");
			$(".selectMenu ul").css("display","none");
			ul.style.display = 'block';
	}
	else {
		ul.style.display = 'none';
	}
	ev.cancelBubble = true;//取消冒泡事件,即父级以上的东西的事件不触发
	//在頁面點擊時		
	document.onclick=function(){
		$(".option").hide();
		$("#"+option).hide();
	}
	for(var i=0;i<li.length;i++){
		li[i].onclick=function(){
			span.innerHTML=this.innerHTML;
		}
	}
}