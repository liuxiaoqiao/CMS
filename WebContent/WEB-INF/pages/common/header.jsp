 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css" />
 <!--header Start-->
		<div class="header clearfix">
            <div class="clearfix">
                <a href="javascript:;" class="logo" ><span><img src="images/global/logo.png" /></span></a>
               <div class="weather">
                    <iframe width="180"  id="weatherIframe" scrolling="no" allowtransparency="true" style="background-color:transparent;"  frameborder="0" height="97"  src="http://i.tianqi.com/index.php?c=code&id=19&icon=1&py=chongqing&temp=0&num=1"></iframe>
              
                </div>
                

            </div>
  			<!--<img src="images/global/Slogan.gif" width="183" height="24"  class="slogan"/>-->
            <div class="slogan" id="slogan">
            <!-- 	<div id="slogan_01"></div>
                <div id="slogan_02"></div>
                <div id="slogan_03"></div> -->
            </div>
        </div>
        <!--header End-->
        <!--nav Start-->
            <!-- <ul class="nav clearfix">
                <li class="cur home"><a href="index.html">首页</a></li>
                <li class="tabHover"><a href="javascript:;">公众出行</a></li>
                <li class="tabHover"><a href="javascript:;">网上办事</a></li>
                <li class="tabHover"><a href="javascript:;">互动交流</a></li>
                <li class="tabHover">
                    <a href="javascript:;">信息公开</a>
                    <span id="dateView"></span>
                    <div class="search">
                        <input type="text" value="请输入关键字"  onfocus="inputFocus(this)" onblur="inputBlur(this)"/>
                        <a href="javascript:;" class="btn_search" title="搜索"></a>
                    </div>
                     <div class="search">
                 <form action="http://www.baidu.com/baidu" target="_blank" name="searchForm" id="searchForm">
                 <input name=tn type=hidden value=baidu>
                    <input type="text" value="请输入关键字"  onfocus="inputFocus(this)" onblur="inputBlur(this)" name="word"/>
                    <input name=tn type=hidden value="bds"> 
                    <input name=cl type=hidden value="3"> 
                    <input name=ct type=hidden value="2097152"> 
                    <input name=si type=hidden value="www.cqjt.gov.cn"> 
                    <a href="javascript:searchForm.submit();" class="btn_search" title="搜索"></a>
                    </form>
                </div>
                </li>
            </ul> -->
            <!--nav End-->
  
   