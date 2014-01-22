<div class="footer">
	<div class="footer_zjft">
		<div class="sbar">
				<h2>友情链接</h2>
				<span class="more"><a id="applylink">申请友情链接</a></span>
		</div>
		<div style="height:68px; ">
			<ul class="olistn">
				<#list linkList as link>
					<li style="float: left;width: 105px;text-align: left">
						<a href="${link.url}" target="_blank" rel="nofollow">${link.name}</a>
					</li>
				</#list>
			</ul>
		</div>
	</div>
	<hr style="border: 1px solid #019C7A;margin-top: 10px;margin-bottom: 10px;" />
	Copyright &copy; 2013 三农专家问答系统 &nbsp;
	<script language="javascript" type="text/javascript" src="../js/51la.js"></script>
	<noscript>
		<a href="http://www.51.la/?15914640" target="_blank"><img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="http://img.users.51.la/15914640.asp" style="border: none" /></a>
	</noscript>
	<br /> 皖ICP备05009138号 增值电信业务经营许可证：皖B2-20120022<br />
	
	<!--申请友链-->
	<div style="position: absolute;  display: none;height:251px;" id="applybox" class="applybox">
		<h2>
			申请友情链接
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" id="closeapplylink" value="关闭"/>
		</h2>
		<div class="urlmainlist">
			链接名称：<input type="text" size="40" id="linkname" style="border: 1px solid rgb(198, 196, 196);"/><br/>
			链接地址：<input type="text" size="40" id="linkurl" style="border: 1px solid rgb(198, 196, 196);"/><br/>
			请在您的网站增加我们的友情链接后在此处提交！<br/>
			网站名：三农专家问答<br/>
			网站地址：http://ask.longcom.com<br/>
			友情链接请联系：<br/>
			电话：0551-65310086&nbsp;&nbsp;QQ：1223004761&nbsp;&nbsp;E-mail：1223004761@qq.com <br/>
			<input type="button" id="submitlink" value="提交" style="border:1px solid #003551;background-color:#EEF0EB;height: 20px;border-radius: 3px;"/>
		</div>
	</div>
</div>
<script>
jQuery.fn.center = function(loaded) {
	var obj = this;
	body_width = parseInt($(window).width());
	body_height = parseInt($(window).height());
	block_width = parseInt(obj.width());
	block_height = parseInt(obj.height());

	left_position = parseInt((body_width / 2) - (block_width / 2) + $(window).scrollLeft());
	if (body_width < block_width) {
		left_position = 0 + $(window).scrollLeft();
	}
	;

	top_position = parseInt((body_height / 2) - (block_height / 2) + $(window).scrollTop());
	if (body_height < block_height) {
		top_position = 0 + $(window).scrollTop();
	}
	;

	if (!loaded) {

		obj.css({
			'position' : 'absolute'
		});
		obj.css({
			'top' : top_position,
			'left' : left_position
		});
		$(window).bind('resize', function() {
			obj.center(!loaded);
		});
		$(window).bind('scroll', function() {
			obj.center(!loaded);
		});

	} else {
		obj.stop();
		obj.css({
			'position' : 'absolute'
		});
		obj.animate({
			'top' : top_position
		}, 200, 'linear');
	}
}

$(document).ready(function() {
	//弹出友链申请窗口
	$('#applylink').click(function(){
		$('#applybox').fadeIn();
		if($('#applybox').center()){
			$('#applybox').center();
		}
		
	});
	//关闭友链申请窗口
	$('#closeapplylink').click(function(){
		$('#applybox').fadeOut();
	});
	//提交友链申请
	$('#submitlink').click(function(){
		var applyurl="../link/addLink.action";
		var linkname = $('#linkname').val();
		var linkurl = $('#linkurl').val();
		if(linkname.length==0||linkurl.length==0){
			alert("请将申请信息填写完整...");
		}else{
			$.post(applyurl,{name:linkname,url:linkurl,status:"0"},
				function(d){
					$('.urlmainlist').empty(); 
					if(d.result=="success"){
						$('.urlmainlist').append("友情链接申请成功。");
					}else{
						$('.urlmainlist').append("该链接已经存在，请勿重复申请。如有疑问请与网站管理人员联系。<br/>电话：0551-65310086&nbsp;&nbsp;QQ：1223004761&nbsp;&nbsp;E-mail：1223004761@qq.com");
					}
				}
			);
		}
	});
});	
</script>