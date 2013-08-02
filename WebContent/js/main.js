/*
@: main.js
@: author:XieBei date:2013-05-11
*/
$(function(){

	$(".tab dt").click(function(){
		var index=$(".tab dt").index(this);
		$(this).removeClass("cur").parent(".tab").siblings(".tabCon").hide();
		$(this).siblings(".tab dt").removeClass("cur");
		$(this).addClass("cur").parent(".tab").siblings(".tabCon:eq("+index+")").show();
	})
	$(".part_jtzz .tab li").click(function(){
		var index=$(".tab li").index(this);
		$(this).removeClass("cur").parent(".tab").siblings(".tabCon").hide();
		$(this).siblings(".part_jtzz .tab li").removeClass("cur");
		$(this).addClass("cur").parent(".tab").siblings(".tabCon:eq("+index+")").show();
	});
	$(".part_cywz .tab li").click(function(){
		var index=$(".part_cywz .tab li").index(this);
		$(this).removeClass("cur").parent(".tab").siblings(".tabCon").hide();
		$(this).siblings(".part_cywz .tab li").removeClass("cur");
		$(this).addClass("cur").parent(".tab").siblings(".tabCon:eq("+index+")").show();
	});

	//顶部导航切换效果
	$(".nav li.tabHover").bind({
		mouseover:function(){
			var index=$(".nav li.tabHover").index(this);
			$(".nav li").removeClass("cur");
			$(this).parent(".nav").siblings(".navConHover").hide();
			$(this).siblings(".nav li.tabHover").removeClass("cur");
			$(this).addClass("cur").parent(".nav").siblings(".navConHover:eq("+index+")").slideDown(1000);
		},
		mouseout:function(){
			$(".navConHover").hover(function(){$(this).show();});
			$(".navConHover").mouseleave(function(){
				$(".navConHover").slideUp(1000);
				$(".nav li.tabHover").removeClass("cur");
				$(".nav .home").addClass("cur");
			});
		}
	});

	$(".home").hover(function(){
		$(".navConHover").slideUp(1000);
		$(".nav li.tabHover").removeClass("cur");
		$(".nav .home").addClass("cur");
	});
	$("#imgList li").click(
		function(){
			$(this).addClass("cur").siblings().removeClass("cur");
		});
	/*=======================
	@ 模仿Gif JS start 
	=========================*/
//	var flag=1;
//	function Gif(){
//		var element=document.getElementById("slogan");
//		switch (flag)
//		{
//			case 1:
//			element.style.backgroundPosition="0 -6px";
//			//$('#slogan').animate({backgroundPosition:"0 -6px"}, 1000);
//			flag=2;
//			break;
//			case 2:
//			element.style.backgroundPosition="0 -41px";
//			//$("#slogan").animate({backgroundPosition:"0 -41px"}, 1000);
//			flag=3;
//			break;
//			case 3:
//			element.style.backgroundPosition="0 -76px";
//			//$("#slogan").animate({backgroundPosition:"0 -76px"}, 1000);
//			flag=1;
//			break;
//		}
//	}
//	var Animat=setInterval(Gif,3000);
	
	
	/*
	@:列表隔行变色
	@:xiebei 20130615
	*/
	$(".trafficNews li:even").css("background-color","#f2f2f2");
});
/*
@:$(function(){}) End
@:xiebei 20130525
*/

/*
@: main.js
@: author:XieBei date:2013-05-11
*/
/*#myFocus 首页焦点图片*/
var myFocus={
	$:function(id){return document.getElementById(id);},
	$$:function(tag,obj){return (typeof obj=='object'?obj:this.$(obj)).getElementsByTagName(tag);},
	linear:function(t,b,c,d){return c*t/d + b;},
	easeIn:function(t,b,c,d){return c*(t/=d)*t*t*t + b;},
	easeOut:function(t,b,c,d){return -c*((t=t/d-1)*t*t*t - 1) + b;},
	opa:function(obj,v){
		if(v!=undefined) {v=v>100?100:(v<0?0:v); obj.style.filter = "alpha(opacity=" + v + ")"; obj.style.opacity = (v / 100);}
		else return (document.all)?((obj.filters.alpha)?obj.filters.alpha.opacity:100):((obj.style.opacity)?obj.style.opacity*100:100);
	},
	move:function(obj,dir,val,type,spd,fn){
		var t=0,b=parseInt(obj.style[dir])||0,c=val-b,d=spd||50,st=type||'linear',m=c>0?'ceil':'floor';
		if(obj[dir+'timer']) clearInterval(obj[dir+'timer']);
		obj[dir+'timer']=setInterval(function(){
			if(t<d){obj.style[dir]=Math[m](myFocus[st](t++,b,c,d))+'px';}
			else {clearInterval(obj[dir+'timer']);fn&&fn.call(myFocus);}
		},10);return this;
	},
	fade:function(obj,type,spd,fn){
		var o=this.opa(obj),m=spd||5;
		if(o==0) obj.style.display='';
		if(type=='out') m=-m;
		if(obj.fadeTimer) clearInterval(obj.fadeTimer);
		obj.fadeTimer=setInterval(function(){
			o+=m;myFocus.opa(obj,o);
			if(o<=0) obj.style.display='none';
			if(o>=100||o<=0){clearInterval(obj.fadeTimer);fn&&fn.call(myFocus);}
		},10);return this;
	},
	addList:function(obj,cla,arr){
		var s=[],n=this.$$('li',this.$$('ul',obj)[0]).length,num=cla.length;
		for(var j=0;j<num;j++){
			s.push('<ul class='+cla[j]+'>');
			for(var i=0;i<n;i++){s.push('<li>'+(cla[j]=='num'?(i+1):(cla[j]=='txt'?this.$$('li',obj)[i].innerHTML.replace(/\<img.*?\>/i,this.$$('img',obj)[i].alt):''))+'<span></span></li>')};
			s.push('</ul>');
		}; obj.innerHTML+=s.join('');
	},
	setting:function(par){//设置DOM/文档加载就绪后执行的任务
		if(window.attachEvent){window.attachEvent('onload',function(){myFocus[par.style](par)});}
　　		else{window.addEventListener('load',function(){myFocus[par.style](par)},false);}
	},
	mF_expo2010:function(par){
		var box=this.$(par.id),t=par.time*1000;
		this.addList(box,['txt-bg','txt','num-bg','num']);
		var pic=this.$$('ul',box)[0],txt=this.$$('ul',box)[2],num=this.$$('ul',box)[4],img=this.$$('li',pic),tip=this.$$('li',txt);
		var H=tip[0].clientHeight+60;
		var n=img.length;
		var index=0;
		for(var i=0;i<img.length;i++){this.opa(img[i],0); img[i].style.display='none'; tip[i].style.top=-H+'px'}//bottom->top
		box.removeChild(this.$$('div',box)[0]);
		this.fade(img[index],'in');
		this.move(tip[index],'top',0,'easeOut',40)//bottom->top
		this.$$('li',num)[index].className='current';
		var run=function(idx){
			myFocus.fade(img[index],'out');
			myFocus.move(tip[index],'top',-H,'easeIn',15);//bottom->top
			myFocus.$$('li',num)[index].className='';
			if(index==n-1) index=-1;
			var N=idx!=undefined?idx:index+1;
			myFocus.fade(img[N],'in');
			myFocus.move(tip[N],'top',0,'easeOut',40);//bottom->top
			myFocus.$$('li',num)[N].className='current';
			index=N;
		}
		var auto=setInterval(function(){run()},t);
		for (var j=0;j<n;j++){
			this.$$('li',num)[j].j=j;
			this.$$('li',num)[j].onclick=function(){run(this.j)}
			this.$$('li',num)[j].onmouseover=function(){if(!this.className) this.className = 'hover';}
			this.$$('li',num)[j].onmouseout=function(){if(this.className=='hover') this.className ='';}
		}
		box.onmouseover=function(){clearInterval(auto);}
    	box.onmouseout=function(){auto=setInterval(function(){run()},t);}
	}
}
myFocus.setting({style:'mF_expo2010',id:'myFocus',time:3});//style为风格样式，id为焦点图ID，time为每帧间隔时间(秒)


function dateView(){
	var dateView=document.getElementById("dateView")
	var date=new Date();
	var Y=date.getFullYear();
	var M=date.getMonth();
	var D=date.getDate();
	var WeekDay=date.getDay();
	switch (WeekDay)
	{
		case 0:
		WeekDay="日"	;
		break;
		case 1:
		WeekDay="一"	;
		break;
		case 2:
		WeekDay="二"	;
		break;
		case 3:
		WeekDay="三"	;
		break;
		case 4:
		WeekDay="四"	;
		break;
		case 5:
		WeekDay="五"	;
		break;
		case 6:
		WeekDay="六"	;
		break;
	}
	var dateStr=Y+"年"+M+"月"+D+"日&nbsp;&nbsp;&nbsp;星期"+WeekDay
	dateView.innerHTML=dateStr;
}
/*=======================
@ 單擊表單值清空JS start
=========================*/

function inputFocus(obj){
	var inputValue=obj.value;
	var inputTip=obj.defaultValue;
	if(inputValue==inputTip){
		obj.style.color = "#333";//设置用户输入时颜色
		obj.value="";
	}
}
function inputBlur(obj){
	var inputValue=obj.value;
	var inputTip=obj.defaultValue;
	if(inputValue==""){
		obj.style.color = "#333";//重置提示文字颜色
		obj.value=inputTip;
	}
}
/*=======================
@ 新闻滚动 JS start
=========================*/

function Mar(){
var speed=40 
var demo=document.getElementById("demo"); 
var demo2=document.getElementById("demo2"); 
var demo1=document.getElementById("demo1"); 
demo2.innerHTML=demo1.innerHTML 
function Marquee(){ 
if(demo2.offsetTop-demo.scrollTop<=0) 
  demo.scrollTop-=demo1.offsetHeight 
else{ 
  demo.scrollTop++ 
} 
} 
var MyMar=setInterval(Marquee,speed) 
demo.onmouseover=function() {clearInterval(MyMar)} 
demo.onmouseout=function() {MyMar=setInterval(Marquee,speed)} 
}
/*=======================
@ 标签菜单 JS start
=========================*/
function tags(tagsId,tagsCntId,tagName,tabConName){
	//tagsId     标签菜单父元素的ID;
	//tagName    标签菜单的标签名称;
	//tagsCntId  菜单对应内容的父元素的ID;
	//tabConName 菜单对应内容的标签名称;

	var Tags=document.getElementById(tagsId).getElementsByTagName(tagName);
	var TagsCnt=document.getElementById(tagsCntId).getElementsByTagName(tabConName);
	var len=Tags.length;
	var flag=0;//修改默认值
	for(i=0;i<len;i++){
		Tags[i].value = i;
		Tags[i].onmouseover=function(){changeNav(this.value)};
		TagsCnt[i].className='undis';
	}
	Tags[flag].className='cur';
	TagsCnt[flag].className='dis';
	function changeNav(v){
		Tags[flag].className='normal';
		TagsCnt[flag].className='undis';
		flag=v;
		Tags[v].className='cur';
		TagsCnt[v].className='dis';
	}
}

/*=======================
@ 新闻相关页面左右等高 JS start
=========================*/
function twoColHeight(lId,rId){
	var lEle=document.getElementById(lId);
	var rEle=document.getElementById(rId);
	var lH=lEle.offsetHeight-2;
	var rH=rEle.offsetHeight-2;
	if(lH>rH){
		rEle.style.height = lH+"px";	
	}
	else if(lH<rH){
		lEle.style.height = rH+"px";	
		alert(lH+":"+rH+";\n lEleHeight:"+lEle.style.height);
	}
}
/*=======================
@ 顶部 Gif JS start
=========================*/

//function slideSwitch() {
//	var $current = $("#slogan div.current");
//	if ( $current.length == 0 ) $current = $("#slogan div:last");
//	var $next =  $current.next().length ? $current.next() : $("#slogan div:first");
//	$current.addClass('prev');
//	$next.css({opacity: 0.0}).addClass("current").animate({opacity: 1.0}, 1000, function() {
//			$current.removeClass("current prev");
//	});
//}
//$(function() {
//	$(".current").css("opacity","1.0");
//	setInterval( "slideSwitch()", 3000 );
//});
/*=============
@ 首页产品预览-图片切换JS
@:xiebei 20130703
================*/
function photoSwitch(picSrc){
	var ele=document.getElementById("bigPhoto").getElementsByTagName("img");
	ele[0].src=picSrc;
}
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
