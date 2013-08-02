
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--nav Start-->
<!-- div id="test"> -->
<div class="navTab">
	<ul class="nav clearfix">
		<li class="cur home"><a href="#" style="text-align: center;">首页</a></li><!-- 这里IE显示不居中，单独控制样式 -->
		<li class="tabHover"><a href="javascript:;">公众出行</a></li>
		<li class="tabHover"><a href="javascript:;">网上办事</a></li>
		<li class="tabHover"><a href="javascript:;">互动交流</a></li>
		<li class="tabHover"><a href="javascript:;">政务公开</a> <span
			id="dateView"></span> <!--  <div class="search">
                        <input type="text" value="请输入关键字"  onfocus="inputFocus(this)" onblur="inputBlur(this)"/>
                        <a href="javascript:;" class="btn_search" title="搜索"></a>
                    </div> -->
			<div class="search">
				<form action="http://www.baidu.com/baidu" target="_blank"
					name="searchForm" id="searchForm">
					<input name=tn type=hidden value=baidu> <input type="text"
						value="请输入关键字" onfocus="inputFocus(this)" onblur="inputBlur(this)"
						name="word" /> <input name=tn type=hidden value="bds"> <input
						name=cl type=hidden value="3"> <input name=ct type=hidden
						value="2097152"> <input name=si type=hidden
						value="www.cqjt.gov.cn"> <a
						href="javascript:searchForm.submit();" class="btn_search"
						title="搜索"></a>
				</form>
			</div></li>
	</ul>
	<!--nav End-->
	
	<!--navCon02 Start-->
	<div class="navCon navConHover navCon_gzcx clearfix"
		style="display: none;z-index:1111;">
		<ul class="link">
			<li><a href="javascript:;" alt="自驾出行" class="i_zjcx"></a></li>
			<li><a href="javascript:;" alt="轨道交通" class="i_gdjt"></a></li>
			<li><a href="javascript:;" alt="航空出行" class="i_hkcx"></a></li>
			<li><a href="javascript:;" alt="城市公交" class="i_csjt"></a></li>
			<li><a href="javascript:;" alt="长途客运" class="i_ctky"></a></li>
			<li><a href="javascript:;" alt="水路交通" class="i_sljt"></a></li>
		</ul>
		<div class="list">
			<h2 class="clearfix">
				<span><b class="icon i_broadcast"></b>最新路况播报</span><a
					href="javascript:;" class="more">更多</a>
			</h2>
			<ul>
				<li><a href="javascript:;">&bull;2013年4月25日9:25分红旗河沟立交至机场一小车自燃，现交...</a></li>
				<li><a href="javascript:;">&bull;2013年4月25日07:45分九滨路往鹅公岩大桥方向有三车追尾...</a></li>
				<li><a href="javascript:;">&bull;2013年4月24日18：20分绕城高速北碚三溪口出城方向有单...</a></li>
				<li><a href="javascript:;">&bull;2013年4月24日渝武高速北碚隧道已经堵到三溪口位置。</a></li>
				<li><a href="javascript:;">&bull;2013年4月24日18：06分虎头岩隧道下行方向有两起追尾事故...</a></li>
				<li><a href="javascript:;">&bull;2013年4月24日10：12分嘉华隧道往袁家岗方向因发生交通...</a></li>
				<li><a href="javascript:;">&bull;2013年4月23日6:52分二郎往中梁山隧道目前行驶缓慢...</a></li>
				<li><a href="javascript:;">&bull;2013年4月22日9：10分鲁能新城往黄花园方向双向行驶...</a></li>
				<li><a href="javascript:;">&bull;2013年4月24日10：12分嘉华隧道往袁家岗方向因发生交通...</a></li>
				<li><a href="javascript:;">&bull;2013年4月23日6:52分二郎往中梁山隧道目前行驶缓慢...</a></li>
			</ul>
		</div>
		<div class="otherCon">
			<h2 class="clearfix">
				<span><b class="icon i_blog"></b>重庆交通微博</span><a href="javascript:;"
					class="more">更多</a>
			</h2>
			<div class="blog">
				<div class="userInfo clearfix">
					<a href="javascript:;" class="userPhoto50_50"><img
						src="images/ad/userPhoto_small01.jpg" /></a>
					<ul>
						<li><a href="javascript:;" class="userName">阿信</a>&nbsp;&nbsp;&nbsp;&nbsp;<span>台湾&nbsp;台北</span></li>
						<li><a href="javascript:;" class="btnGray73_19">我自己</a></li>
					</ul>
				</div>
				<dl class="blogList">
					<dt>
						<a href="javascript:;">謝謝大家，為雅安賑災，五月天追加50萬捐款，希望幫助更多人: )
							//@典歌王: 即使小小一元,五元,十元都是愛 ,謝謝達芙妮.綠箭口香糖,哎呀呀飾品的捐款,感謝大家熱烈響應,分享今夜小
							八卦-陳主唱真的是個小朋友,一碗蟹粥就讓他這麼開心。</a>
					</dt>
					<dd>
						<span>1分钟前</span> <a href="javascript:;">评论</a> <a
							href="javascript:;">转发&nbsp;&nbsp;|&nbsp;&nbsp;</a>
					</dd>
					<dt>
						<a href="javascript:;">謝謝大家，為雅安賑災，五月天追加50萬捐款</a>
					</dt>
					<dd>
						<span>1分钟前</span> <a href="javascript:;">评论</a> <a
							href="javascript:;">转发&nbsp;&nbsp;|&nbsp;&nbsp;</a>
					</dd>
					<dt>
						<a href="javascript:;">謝謝大家，為雅安賑災，五月天追加50萬捐款</a>
					</dt>
					<dd>
						<span>1分钟前</span> <a href="javascript:;">评论</a> <a
							href="javascript:;">转发&nbsp;&nbsp;|&nbsp;&nbsp;</a>
					</dd>

				</dl>
			</div>
		</div>
	</div>
	<!--navCon02 End-->
	<!--navCon03 Start-->
	<div class="navCon navConHover navCon_wsbs clearfix"
		style="display: none;z-index:1111">
		<ul class="link">
			<li><a href="javascript:;" alt="进入大厅" class="i_jrdt"></a></li>
			<li><a href="javascript:;" alt="服务指南" class="i_fwzn"></a></li>
			<li><a href="javascript:;" alt="表格下载" class="i_bgxz"></a></li>
			<li><a href="javascript:;" alt="在线咨询" class="i_zxzx"></a></li>
		</ul>
		<div class="list">
			<h2 class="clearfix">
				<span><b class="icon i_triangleBlue"></b>办事情况</span><a
					href="javascript:;" class="more">更多</a>
			</h2>
			<ul>
				<li><a href="javascript:;">&bull;渝潼SN（2011）179]水路运输业办理船舶营业...</a>
					<span>审核通过，办结</span></li>
				<li><a href="javascript:;">&bull;渝潼SN（2011）178]水路运输业办理船舶营业...</a><span>审核通过，办结</span></li>
				<li><a href="javascript:;">&bull;渝武运2—77]水路运输企业开业(个人)</a><span>审核通过，办结</span></li>
				<li><a href="javascript:;">&bull;渝潼SN（2011）180]水路运输业办理船舶营业...</a><span>审核通过，办结</span></li>
				<li><a href="javascript:;">&bull;渝SJ（2011）1046]水路运输业办理船舶营业...</a><span>审核通过，办结</span></li>
				<li><a href="javascript:;">&bull;渝武运2-92]水路运输企业开业(个人)</a><span>审核通过，办结</span></li>
				<li><a href="javascript:;">&bull;渝SJ（2011）1336]水路运输业办理船舶营业...</a><span>审核通过，办结</span></li>
				<li><a href="javascript:;">&bull;渝SJ（2011）1393]水路运输业办理船舶营业...</a><span>审核通过，办结</span></li>
				<li><a href="javascript:;">&bull;渝运GXK131]水路运输企业开业(个人)</a><span>审核通过，办结</span></li>
				<li><a href="javascript:;">&bull;交长渝XK513]水路运输企业开业</a><span>审核通过，办结</span></li>
			</ul>
		</div>
		<div class="otherCon">
			<h2 class="clearfix">
				<span><b class="icon i_triangleBlue"></b>主要服务</span><a
					href="javascript:;" class="more">更多</a>
			</h2>
			<div class="workInfo">
				<a href="javascript:;" class="icon_01" title="行政审批预受理"><span>行政审批预受理</span></a>
				<a href="javascript:;" class="icon_02" title="行政办件查询"><span>行政办件查询</span></a>
				<a href="javascript:;" class="icon_03" title="高速违法查询"><span>高速违法查询</span></a>
				<a href="javascript:;" class="icon_04" title="易居畅通卡查询"><span>易居畅通卡查询</span></a>
				<a href="javascript:;" class="icon_05" title="出租车诚信查询"><span>出租车诚信查询</span></a>
				<a href="javascript:;" class="icon_06" title="法制超载查询"><span>法制超载查询</span></a>
				<a href="javascript:;" class="icon_07" title="高速ETC查询"><span>高速ETC查询</span></a>
				<a href="javascript:;" class="icon_08" title="96096投诉"><span>96096投诉</span></a>
				<a href="javascript:;" class="icon_09" title="失物招领"><span>失物招领</span></a>
			</div>
		</div>
	</div>
	<!--navCon03 End-->
	<!--navCon04 Start-->
	<div class="navCon navConHover navCon_hdjl clearfix"
		style="display: none;z-index:1111">
		<ul class="link">
			<li><a href="javascript:;" alt="领导信箱" class="i_ldxx"></a></li>
			<li><a href="javascript:;" alt="在线访谈" class="i_zxft"></a></li>
			<li><a href="communication.do?action=getCommunicationList&curpage=1"; alt="意见征集" class="i_yjzj"></a></li>
			<li><a href="javascript:;" alt="交通微博" class="i_jtwb"></a></li>
		</ul>
		<div class="list">
			<h2 class="clearfix">
				<span><b class="icon i_triangleBlue"></b>最近来信</span><a
					href="javascript:;" class="more">更多</a>
			</h2>
			<ul>
				<li><a href="javascript:;">&bull;关于目前轨道6号线礼嘉站周边公共交通衔接的建议</a> <span>2013-4-27</span></li>
				<li><a href="javascript:;">&bull;关于建立和完善私家车往返主城与区县拼车的建议</a><span>2013-4-27</span></li>
				<li><a href="javascript:;">&bull;关于驾校学员未考理论考试，是否存在过期一事？</a><span>2013-4-27</span></li>
				<li><a href="javascript:;">&bull;关于驾校的潜规则和乱象</a><span>2013-4-27</span></li>
				<li><a href="javascript:;">&bull;咨询万利高速</a><span>2013-4-27</span></li>
				<li><a href="javascript:;">&bull;关于鱼洞到主城区一票制</a><span>2013-4-27</span></li>
				<li><a href="javascript:;">&bull;重庆菜园坝汽车站太不公平的退票业务</a><span>2013-4-27</span></li>
				<li><a href="javascript:;">&bull;关于龙头寺长途汽车站重庆-万州路线春节、国庆期</a><span>2013-4-27</span></li>
				<li><a href="javascript:;">&bull;驾校未签订合同考试挂科收取燃油费没有正规场地</a><span>2013-4-27</span></li>
				<li><a href="javascript:;">&bull;车辆保险 </a><span>2013-4-27</span></li>
			</ul>
		</div>
		<div class="otherCon">
			<h2 class="clearfix">
				<span><b class="icon i_triangleBlue"></b>主要服务</span><a
					href="javascript:;" class="more">更多</a>
			</h2>
			<div class="interview">
				<img src="images/ad/photo_onlineInterview_big.jpg"
					class="interviewPhoto" /> <b>标题：</b> <span>荣昌交通_门户枢纽</span> <br />
				<b>时间：</b> <span>6月20日 上午9:00</span> <br /> <b>嘉宾：</b> <span>林仕文：男，荣昌
					县交通委员会党委副书记 主任，主持行政全面工作。 简介：荣昌地处重庆的最
					西边，是当之无愧的"西大门"，地处渝西川结合部，与重庆永川、大 足和四川内江、泸州、隆昌、安岳接壤，位结合部，与重庆永川、大
					足和四川内江、泸州、隆昌、安岳接壤，位结合部，与重庆永川、大 足和四川内江、泸州、隆昌、安岳接壤，位渝西川东结合部及重庆
					"一小时经济圈"内，是连接成渝经济区... </span>

			</div>
		</div>
	</div>
	<!--navCon04 End-->
	<!--navCon05 Start-->
	<div class="navCon navConHover navCon_xxgk clearfix"
		style="display: none;z-index:1111">
		<ul class="link">
			<li><a href="javascript:;" alt="组织机构" class="i_zzjg"></a></li>
			<li><a href="javascript:;" alt="主任网页" class="i_zrwy"></a></li>
			<li><a href="javascript:;" alt="通知公告" class="i_tzgg"></a></li>
			<li><a href="javascript:;" alt="招标公告" class="i_zbgg"></a></li>
			<li><a href="javascript:;" alt="中标公告" class="i_zbgg"></a></li>
			<li><a href="javascript:;" alt="公开目录" class="i_gkml"></a></li>
		</ul>
		<div class="list">
			<h2 class="clearfix">
				<span><b class="icon i_triangleBlue"></b>最近来信</span><a
					href="javascript:;" class="more">更多</a>
			</h2>
			<ul>
				<li><a href="javascript:;">&bull;重庆市地方海事局关于乌江航道整治工程C标</a> <span>2013-4-27</span></li>
				<li><a href="javascript:;">&bull;重庆市港航管理局关于做好“五一”节期间</a><span>2013-4-27</span></li>
				<li><a href="javascript:;">&bull;[质监局]2013年重庆市公路水运工程试验检</a><span>2013-4-27</span></li>
				<li><a href="javascript:;">&bull;重庆市2012年度水运建设市场从业单位信用</a><span>2013-4-27</span></li>
				<li><a href="javascript:;">&bull;关于举办2013年公路水运工程试验检测人员</a><span>2013-4-27</span></li>
				<li><a href="javascript:;">&bull;重庆市2012年度公路建设市场施工企业全市</a><span>2013-4-27</span></li>
				<li><a href="javascript:;">&bull;重庆市2012年度水运工程监理信用评价结果</a><span>2013-4-27</span></li>
				<li><a href="javascript:;">&bull;重庆市2012年度公路工程监理信用评价结果</a><span>2013-4-27</span></li>
				<li><a href="javascript:;">&bull;[质监局]关于重庆市2013年第一批公路水运</a><span>2013-4-27</span></li>
				<li><a href="javascript:;">&bull;重庆市地方海事局关于乌江大溪河口至白马</a><span>2013-4-27</span></li>
			</ul>
		</div>
		<div class="otherCon">
			<div class="interview">
				<img src="images/ad/photo_onlineInterview_big.jpg"
					class="interviewPhoto" /> <span> 重庆市委、市政府将交 通作为经济社会发展的第一
					要务，紧紧抓住西部大开发、 三峡工程建设、统筹城乡发 展等重大机遇加大投入，加 快建设，重庆交通面貌发生
					了翻天覆地的变化，成为全市发展速度最快、变化最大、群众最为满意 的领域之一，使重庆“成为每三个月就要更换一次地图的城市”。交通
					为全市经济增长、社会和谐发展提供了有力基础保障，做出了突出贡献。 （一）交通投资规模持续扩大。交通基础设施建设投资总量持续增
					了翻天覆地的变化，成为全市发展速度最快、变化最大、群众最为满意 的领域之一，使重庆“成为每三个月就要更换一次地图的城市”。交通
					长，直辖以来重庆公路水路... <span class="pos_rb">（<a href="javascript:;"
						class="cBlue">更多</a>）
				</span>
				</span>
			</div>
		</div>
	</div>
	<!--navCon05 End-->
</div>