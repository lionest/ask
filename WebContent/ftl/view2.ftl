<script>
	var acceptReplyUrl ;
	var acceptReplyScore = null ;
	var questionId = '${question.id?c}';
	function showProbing(id) {
		//alert(id);
		document.getElementById("probing" + id).style.display = "block";
	}
	function showReply(id) {
		//alert(id);
		document.getElementById("replytoprobing" + id).style.display = "block";
	}

	function showExtr(Img) {
		Img.src = "${bathPath}/images/detail_04.jpg";
		document.getElementById("qExtr").style.display = "block";
		document.getElementById("qExtr").focus();
	}

	function showEdit2(outid, preId, aId, action, param, hiddenName, textareaName) {
		document.getElementById(preId).style.display = "none";
		document.getElementById(aId).style.display = "none";
		var div = document.createElement("div");
		var form = document.createElement("form");
		form.action = action;
		form.method = "post";
		var inputHidden = document.createElement("input");
		inputHidden.type = "hidden";
		inputHidden.name = hiddenName;
		inputHidden.value = param;
		var textarea = document.createElement("textarea");
		textarea.name = textareaName;
		textarea.rows = "5";
		textarea.cols = "80";
		textarea.style.border = "2px";
		textarea.value = document.getElementById(preId).innerHTML;
		var input = document.createElement("input");
		input.type = "submit";
		input.value = "提交";
		var inputReturn = document.createElement("input");
		inputReturn.type = "button";
		inputReturn.value = "返回";
		inputReturn.onclick = function() {
			window.location.reload();
		};
		var br = document.createElement("br");
		form.appendChild(inputHidden);
		form.appendChild(textarea);
		form.appendChild(br);
		form.appendChild(input);
		form.appendChild(inputReturn);
		div.appendChild(form);
		document.getElementById(outid).appendChild(div);
	}

	function showEdit3(actionType, probingValue, addArea, hiddenArea, paramId) {
		if (actionType == "edit") {
			document.getElementById(probingValue).style.display = "none";
		}
		document.getElementById(hiddenArea).style.display = "none";
		var form = document.createElement("form");
		if (actionType == "add") {
			form.action = "${bathPath}/p/question/addProbing.html";
		} else if (actionType = "edit") {
			form.action = "${bathPath}/p/question/editProbing.html";
		}
		form.method = "post";
		if (actionType == "edit") {
			var inputHidden = document.createElement("input");
			inputHidden.type = "hidden";
			inputHidden.name = "probing.id";
			inputHidden.value = paramId;
			form.appendChild(inputHidden);
		}
		if (actionType == "add") {
			var replyIdHidden = document.createElement("input");
			replyIdHidden.type = "hidden";
			replyIdHidden.name = "probing.replyId";
			replyIdHidden.value = paramId;
			form.appendChild(replyIdHidden);
			var questionIdHidden = document.createElement("input");
			questionIdHidden.type = "hidden";
			questionIdHidden.name = "probing.questionId";
			questionIdHidden.value = "${question.id?c}";
			form.appendChild(questionIdHidden);
		}

		var textArea = document.createElement("textArea");
		textArea.rows = "5";
		textArea.cols = "80";
		textArea.name = "probing.content";
		textArea.style.border = "#69C74C 1px solid";
		textArea.onfocus = function() {
			this.style.border = "#69C74C 2px solid ";
		}
		textArea.onblur = function() {
			this.style.border = "#69C74C 1px solid ";
		}
		if (actionType == "edit") {
			textArea.value = document.getElementById(probingValue).innerHTML;
		}
		var submitDIV = document.createElement("div");
		submitDIV.style.styleFloat = "right";
		var inputSubmit = document.createElement("input");
		inputSubmit.type = "submit";
		inputSubmit.style.background = "url(${bathPath}/images/detail_01.jpg) no-repeat";
		inputSubmit.style.width = "50px";
		inputSubmit.style.height = "35px";
		inputSubmit.style.cursor = "pointer";
		inputSubmit.value = "";
		var inputReturn = document.createElement("input");
		inputReturn.type = "button";
		inputReturn.style.background = "url(${bathPath}/images/detail_02.jpg) no-repeat";
		inputReturn.style.margin = "10px 0 0 7px";
		inputReturn.style.width = "50px";
		inputReturn.style.height = "35px";
		inputReturn.style.cursor = "pointer";
		inputReturn.value = "";
		inputReturn.onclick = function() {
			window.location.reload();
		};
		var br = document.createElement("br");
		submitDIV.appendChild(inputSubmit);
		submitDIV.appendChild(inputReturn);
		var clearDIV = document.createElement("div");
		clearDIV.style.clear = "both";
		clearDIV.style.display = "block";
		form.appendChild(textArea);
		form.appendChild(br);
		form.appendChild(submitDIV);
		form.appendChild(clearDIV);
		document.getElementById(addArea).appendChild(form);
	}
	//编辑问题图片
	function editImg(qImgListId){
		$("#"+qImgListId+"IMGEdit").hide();
	  	$("#"+qImgListId+"selectQuestionImg").show();
	}
	
	function returnToQuestion(qImgListId){
		$("#"+qImgListId+"selectQuestionImg").hide();
	  	$("#"+qImgListId+"IMGEdit").show();
	}
	/* 星级评分 */
	
	function score(){
		var oStar = document.getElementById("star");
		var aLi = oStar.getElementsByTagName("li");
		var oUl = oStar.getElementsByTagName("ul")[0];
		var oSpan = oStar.getElementsByTagName("span")[1];
		var oP = oStar.getElementsByTagName("p")[0];
		var i = iScore = iStar = 0;
		var testaMsg = "${questionStarDescribeStr}" ;
		var aMsg= testaMsg.split("_");
		for (i = 1; i <= aLi.length; i++){
			aLi[i - 1].index = i;
			//鼠标移过显示分数
			aLi[i - 1].onmouseover = function (){
				fnPoint(this.index);
				//浮动层显示
				oP.style.display = "block";
				//计算浮动层位置
				oP.style.left = oUl.offsetLeft + this.index * this.offsetWidth - 104 + "px";
				//匹配浮动层文字内容
				oP.innerHTML = "<em><b>" + this.index + "</b> 分 " + aMsg[this.index - 1].match(/(.+)\|/)[1] + "</em>" + aMsg[this.index - 1].match(/\|(.+)/)[1];
			};
			
			//鼠标离开后恢复上次评分
			aLi[i - 1].onmouseout = function (){
				fnPoint();
				//关闭浮动层
				oP.style.display = "none";
			};
			
			//点击后进行评分处理
			aLi[i - 1].onclick = function (){
				iStar = this.index;
				oP.style.display = "none";
				oSpan.innerHTML = "<strong>" + (this.index) + " 分</strong> (" + aMsg[this.index - 1].match(/\|(.+)/)[1] + ")<input name= 'questionscore' type='hidden' value='" + (this.index) + "' />";
				acceptReplyScore = iStar;
				var replyuid = $('#replyUserId').val();
				acceptReplyUrl = "${bathPath}/p/question/acceptReply/${question.id?c}_"+replyuid+"_"+acceptReplyScore+".html";
				//alert(acceptReplyUrl);
				if(acceptReplyScore == null||acceptReplyScore ==''){
					alert("请先为答案打分...");
				}else{
					window.location.href = acceptReplyUrl;
				}
			};
		}
		//评分处理
		function fnPoint(iArg){
			//分数赋值
			iScore = iArg || iStar;
			for (i = 0; i < aLi.length; i++) aLi[i].className = i < iScore ? "on" : "";	
		};
	}
	/* 点击选为满意答案跳转链接 */
	function toAcceptReplyUrl(replyuserid){
		$('#'+replyuserid+'givestar').append("<div id='star' style='width: 250px;margin: 10px;'><span>为答案打分</span><ul style='line-height: 100px;'><li><a href='javascript:;'>1</a></li><li><a href='javascript:;'>2</a></li><li><a href='javascript:;'>3</a></li><li><a href='javascript:;'>4</a></li></ul><span></span><p></p></div><input type='hidden' id='replyUserId' value=''>"); 
		$(document).ready(function() {
			score();
		})
		$('#replyUserId').val(replyuserid);
	}

</script>
<!--  问题开始-->
<div class="q_quest01">
	<!--标题开始-->
	<div class="q_tit">
		<div class="icon fl">
			<img src="${bathPath}/images/q_01.jpg" width="18" height="18" />
		</div>
		<div class="fon14 fl">${question.subject }</div>
	</div>
	<!--提问者信息开始-->
	<div class="q_info">
		<div class="fl">
			提问者： <span class="g12">${question.user.screenName }</span> | 浏览次数：${question.viewCount+1 }次
		</div>
		<div class="fr">
			<#setting date_format="yyyy-MM-dd HH:mm:ss">
			${question.createdTime?date}
		</div>
	</div>
	<!--问题内容息开始-->
	<div class="q_Content">${question.content }</div>
	<!-- 问题图片 -->
	<div class="q_replenish2" id="gallery">
		<ul>
			<#list qImgList as qImg >
				<li>
					<a href="${bathPath}/question/${qImg.url }"  > 
						<img src="${bathPath}/question/${qImg.url }" width="200px" height="140px" />
					</a>
				</li>
			</#list>
		</ul>
	</div>
	<div class="q_replenish2">
		<#list qImgList as qImgList>
			<div class="con fl" id="IMGoutId${(qImgList.id)?c}" style="margin-left: 5px;width: 200px;">
				<div id="${qImgList.id?c }IMGEdit">
					<a style="color: gray; cursor: pointer" onClick="editImg(${qImgList.id?c });">修改</a> &nbsp;<a style="color: gray; cursor: pointer" onmouseover="changeColor(this,1);"
						onmouseout="changeColor(this,2);" href="${bathPath}/p/question/removeQuestionImg/${qImgList.id?c }.html">删除</a>
				</div>
				<!-- 隐藏的选择图片。提交图片层 -->
				<div id="${qImgList.id?c }selectQuestionImg" style="display: none; width: 200px;">
					<form action="${bathPath}/p/question/editQuestionImg.html" method="post" enctype="multipart/form-data">
						<input type="hidden" name="questionImg.id" value="${qImgList.id?c }" /> <input type="hidden" name="questionImg.imgOrder" value="${qImgList.imgOrder }" /> <input type="file" name="questionImgs" /><br />
						<input type="submit" value="提交" /> <input type="button" onclick="returnToQuestion(${qImgList.id?c })" value="返回" />
					</form>
				</div>
			</div>
		</#list>
	</div>
	<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
	<#if question.extraContent??>
		<div class="q_replenish2">
			<div class="fon14 fl">问题补充：</div>
			<div class="con fl">
				<pre style="white-space: pre-wrap; word-wrap: break-word; display: inline; font-size: 14px">${question.extraContent }</pre>
			</div>
			<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
		</div>
	</#if>
	<div class="q_replenish2">
		<!-- 补充提问 -->
		<span class="cc"><img src="${bathPath}/images/detail_03.jpg" onmouseover="javascript:this.src='${bathPath}/images/detail_04.jpg'"
			onmouseout="javascript:this.src='${bathPath}/images/detail_03.jpg'" onclick="showExtr(this);" style="cursor: pointer;" /></span>
		<div style="display: none" id="qExtr">
			<form action="${bathPath}/p/question/edit.html" method="post">
				<input type="hidden" name="question.id" value="${question.id?c }" />
				<textarea name="question.extraContent" rows="5" cols="80" onfocus="this.style.border= '#69C74C 2px solid '; " onblur="this.style.border='#69C74C 1px solid'"
					style="margin-bottom: 10px; border: #69C74C 1px solid; font-size: 14px"><#if question.extraContent??>${question.extraContent }</#if></textarea>
				<div style="float: right">
					<input style="cursor:pointer;background:url(${bathPath}/images/detail_01.jpg) no-repeat; width:50px; height:35px; cursor:hand;" type="submit" value="" /> <input
						type="button" value="" style="cursor:pointer;background:url(${bathPath}/images/detail_02.jpg) no-repeat; width:50px; height:35px; cursor:hand;" type="button"
						onclick="javascript:window.location.reload()" />
				</div>
				<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
			</form>
		</div>
	</div>
	<!-- 问题标签 -->
	<div style="margin-left: 20px; margin-bottom: 20px; margin-top: 10px;">
		标签：
		<#list qkList as qk >
			<a class="g12" target="_blank"  onclick="urlEnco('${qk.keyword}');">${qk.keyword}</a>
		</#list>
	</div>
</div>
<!--我来帮他解答开始-->
<!--  问题结束-->
<!--  采纳答案开始-->
<#list replyList as reply>
	<#if reply.accepted == 1 && !(reply.probingId??) >
		<!--  采纳答案开始-->
		<div class="q_quest02">
			<!--标题开始-->
			<div class="q_tit">
				<div class="icon fl">
					<img src="${bathPath}/images/q_02.jpg" width="18" height="18" />
				</div>
				<div class="fon14 fl">采纳答案</div>
			</div>
			<!--回答者信息开始-->
			<div class="q_info">
				<#if reply.user.id!=17 >
					<div class="fl">
						回答者 <span class="g12">${reply.user.screenName }</span>
					</div>
				</#if>
				<#if  reply.user.expert == 1>
					<div class="zjicon fl">
						<img src="${bathPath}/images/q_04.jpg" width="15" height="13" />
					</div>
				</#if>
				<div class="fr">
					${reply.createdTime?date}
				</div>
			</div>
			<!--问题内容息开始-->
			<div class="q_answer_con">
				<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply.content}</div>
			</div>
			<#if probingList??>
			<#list probingList as probing>
				<#if probing.reply.id == reply.id>
					<div class="q_replenish2">
						<div class="bfon14 fl">${question.user.username }：</div>
						<div class="con fl">
							<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing.content }</div>
						</div>
						<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
					</div>
					<#list replyList as reply2probing>
						<#if reply2probing.probingId?? && reply2probing.probingId == probing.id>
							<div class="q_replenish2">
								<div class="bfon14 fl">${reply.user.screenName }：</div>
								<div class="con fl">
									<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing.content}</div>
								</div>
								<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
							</div>
							<#list probingList as probing2 >
								<#if probing2.reply.id == reply2probing.id >
									<div class="q_replenish2">
										<div class="bfon14 fl">${question.user.username }：</div>
										<div class="con fl">
											<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing2.content }</div>
										</div>
										<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
									</div>
									<#list replyList as reply2probing2>
										<#if reply2probing2.probingId?? && reply2probing2.probingId == probing2.id >
											<div class="q_replenish2">
												<div class="bfon14 fl">${reply.user.screenName }：</div>
												<div class="con fl">
													<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing2.content}</div>
												</div>
												<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
											</div>
											<#list probingList as probing3>
												<#if probing3.reply.id == reply2probing2.id >
													<div class="q_replenish2">
														<div class="bfon14 fl">${question.user.username }：</div>
														<div class="con fl">
															<div id="pro${probing3.id?c }" style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing3.content }</div>
														</div>
														<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
													</div>
													<#if probing3.status == 2>
														<#list replyLis as reply2probing3 >
															<#if reply2probing3.probingId?? && reply2probing3.probingId == probing3.id>
																<div class="q_replenish2">
																	<div class="bfon14 fl">${reply.user.screenName }：</div>
																	<div class="con fl">
																		<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing3.content}</div>
																	</div>
																	<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																</div>
															</#if>
														</#list>
													</#if>
												</#if>
											</#list>
										</#if>
									</#list>
								</#if>
							</#list>
						</#if>
					</#list>
				</#if>
			</#list>
			</#if>
			<!-- 评论开始 -->
			<div id="${reply.id?c}commenturl" style="margin-left: 600px;">
				&nbsp;&nbsp;&nbsp;<a onclick="openComment('${reply.id?c}');"><font color="#2DA508" id="${reply.id?c}commentNum">评论(${reply.commentNum })</font></a>
			</div>
			<div id="${reply.id?c}removecomment" style="display: none;margin-left: 600px;">
				&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="removecomment('${reply.id?c}');"><font color="#2DA508">收起评论</font></a>
			</div>
			<div id="${reply.id?c}listcomment" style="display: none; width: 633px; margin-left: 20px; margin-top: 10px; margin-bottom: 10px;">
				<div id="${reply.id}addc">
					回复：<br />
					<textarea id="${reply.id?c}replyCommentContent" style="width: 622px; border: 1px solid #006940;"></textarea>
					<div style="margin-top: 10px; margin-left: 463px;">
						<a onclick="addreplycomment('${reply.id?c}','${reply.commentNum}');"><img src="${bathPath}/images/ask_tj.jpg" width="77px" height="30px;"></img></a> <a
							onclick="removecomment('${reply.id?c}');"><img src="${bathPath}/images/ask_qx.jpg" width="77px" height="30px;"></img></a>
					</div>
				</div>
				<div id="${reply.id?c}listc"></div>
			</div>
			<!-- 评论结束 -->
			<div class="q_Comment">
				<div class="fr">${scoreContent }</div>
				<#if score??>
				<#if score == 1>
					<div class="star fr">
						<img src="${bathPath}/images/q_07.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${bathPath}/images/q_07.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${bathPath}/images/q_07.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${bathPath}/images/q_05.jpg" width="22" height="21" />
					</div>
				</#if>
				<#if score == 2>
					<div class="star fr">
						<img src="${bathPath}/images/q_07.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${bathPath}/images/q_07.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${bathPath}/images/q_05.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${bathPath}/images/q_05.jpg" width="22" height="21" />
					</div>
				</#if>
				<#if score == 3>
					<div class="star fr">
						<img src="${bathPath}/images/q_07.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${bathPath}/images/q_05.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${bathPath}/images/q_05.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${bathPath}/images/q_05.jpg" width="22" height="21" />
					</div>
				</#if>
				<#if score == 4 || score == 0>
					<div class="star fr">
						<img src="${bathPath}/images/q_05.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${bathPath}/images/q_05.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${bathPath}/images/q_05.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${bathPath}/images/q_05.jpg" width="22" height="21" />
					</div>
				</#if>
				<#else>
					<div class="star fr">
						<img src="${bathPath}/images/q_05.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${bathPath}/images/q_05.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${bathPath}/images/q_05.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${bathPath}/images/q_05.jpg" width="22" height="21" />
					</div>
				</#if>
				<div class="fr">评价：</div>
			</div>
		</div>
		<!--  采纳答案结束-->
	</#if>
</#list>
<!--  采纳答案结束-->
<!--  其他答案开始-->
<#if (question.replyNum gt 0&&question.status!=2) || (question.replyNum gt 1 && question.status==2) >
	<div class="q_quest02">
		<!--标题开始-->
		<div class="q_tit">
			<div class="icon fl">
				<img src="${bathPath}/images/q_03.jpg" width="18" height="18" />
			</div>
			<div class="fon14 fl">
				<#if question.status == 2 >
					其他答案
				<#else>
					答案
				</#if>
			</div>
			<div class="fg12 fl">
				共
				<#if question.replyNum gt 0 && question.status == 2 >
					${question.replyNum-1 }
				<#else>
					${question.replyNum }
				</#if>
				条
			</div>
		</div>
		<#list replyList as reply>
			<#if  reply.accepted == 0 && !(reply.probingId??) >
				<!--回答者信息开始-->
				<div class="q_info">
					<!-- 锚点 -->
					<a name="${reply.id}" id="${reply.id}" ></a>
					<div class="fl">
						回答者 <span class="g12">${reply.user.screenName }</span>
					</div>
					<#if reply.user.expert == 1 >
						<div class="zjicon fl">
							<img src="${bathPath}/images/q_04.jpg" width="15" height="13" />
						</div>
					</#if>
					<div class="fr">
						${reply.createdTime?date}
					</div>
				</div>
				<!--问题内容息开始-->
				<!--问题内容息开始-->
				<div class="q_answer_con">
					<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply.content}</div>
				</div>
				<#if reply.status == 2>
					<#if probingList??>
					<#list probingList as probing>
						<#if probing.reply.id == reply.id  >
							<div class="q_replenish2">
								<div class="bfon14 fl">${question.user.username }：</div>
								<div class="con fl">
									<div id="pro${probing.id?c }" style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing.content }</div>
								</div>
								<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
							</div>
							<#if probing.status == 2 >
								<#list replyList as reply2probing>
									<#if  reply2probing.probingId?? && reply2probing.probingId == probing.id  >
										<div class="q_replenish2">
											<div class="bfon14 fl">${reply.user.screenName }：</div>
											<div class="con fl">
												<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing.content}</div>
											</div>
											<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
										</div>
										<#if reply2probing.status == 2 >
											<#list probingList as probing2>
												<#if  probing2.reply.id == reply2probing.id  >
													<div class="q_replenish2">
														<div class="bfon14 fl">${question.user.username }：</div>
														<div class="con fl">
															<div id="pro${probing2.id?c }" style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing2.content }</div>
														</div>
														<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
													</div>
													<#if probing2.status == 2 >
														<#list replyList as reply2probing2 >
															<#if  reply2probing2.probingId?? && reply2probing2.probingId == probing2.id >
																<div class="q_replenish2">
																	<div class="bfon14 fl">${reply.user.screenName }：</div>
																	<div class="con fl">
																		<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing2.content}</div>
																	</div>
																	<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																</div>
																<#if reply2probing2.status == 2 >
																	<#list probingList as probing3>
																		<#if  probing3.reply.id == reply2probing2.id >
																			<div class="q_replenish2">
																				<div class="bfon14 fl">${question.user.username }：</div>
																				<div class="con fl">
																					<div id="pro${probing3.id?c }" style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing3.content }</div>
																				</div>
																				<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																			</div>
																			<#if probing3.status == 2 >
																				<#list replyList as reply2probing3 >
																					<#if reply2probing3.probingId?? && reply2probing3.probingId == probing3.id >
																						<div class="q_replenish2">
																							<div class="bfon14 fl">${reply.user.screenName }：</div>
																							<div class="con fl">
																								<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing3.content}</div>
																							</div>
																							<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																						</div>
																						<div class="q_replenish2">
																							<#if question.status == 1 >
																								<span class="cc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="toAcceptReplyUrl('${reply.user.id?c }')">选为满意答案</a></span>
																							</#if>
																							<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																						</div>
																					</#if>
																				</#list>
																			<#else>
																				<div class="q_replenish2" id="proArea${probing3.id?c }">
																					<span id="modPro${probing3.id?c}"> <#if question.status == 1 >
																							<span class="cc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="toAcceptReplyUrl('${reply.user.id?c }')">选为满意答案</a></span>
																						</#if> <span class="cc2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)"
																							onclick="showEdit3('edit','pro${probing3.id?c }','proArea${probing3.id?c }','modPro${probing3.id?c }','${probing3.id?c }');">修改追问</a>
																					</span>
																						<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																					</span>
																				</div>
																			</#if>
																		</#if>
																	</#list>
																<#else>
																	<div class="q_replenish2" id="proAddArea${reply2probing2.id?c }">
																		<span id="addPro${reply2probing2.id?c}"> <#if question.status == 1 >
																				<span class="cc">&nbsp;&nbsp;&nbsp;<a onclick="toAcceptReplyUrl('${reply.user.id?c }')">选为满意答案</a></span>
																			</#if> <span class="cc">&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)"
																				onclick="showEdit3('add','','proAddArea${reply2probing2.id?c }','addPro${reply2probing2.id?c }','${reply2probing2.id?c }');">继续追问</a>
																		</span>
																			<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																		</span>
																	</div>
																</#if>
															</#if>
														</#list>
													<#else>
														<div class="q_replenish2" id="proArea${probing2.id?c }">
															<span id="modPro${probing2.id?c}"> <#if question.status == 1 >
																	<span class="cc">&nbsp;&nbsp;&nbsp;<a onclick="toAcceptReplyUrl('${reply.user.id?c }')">选为满意答案</a></span>
																</#if> <span class="cc2">&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)"
																	onclick="showEdit3('edit','pro${probing2.id?c }','proArea${probing2.id?c }','modPro${probing2.id?c }','${probing2.id?c }');">修改追问</a>
															</span>
																<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
															</span>
														</div>
													</#if>
												</#if>
											</#list>
										<#else>
											<div class="q_replenish2" id="proAddArea${reply2probing.id?c }">
												<span id="addPro${reply2probing.id?c}"> <#if question.status == 1 >
														<span class="cc"><a onclick="toAcceptReplyUrl('${reply.user.id?c }')">选为满意答案</a>&nbsp;&nbsp;</span>
													</#if> <span class="cc"><a href="javascript:void(0)" onclick="showEdit3('add','','proAddArea${reply2probing.id?c }','addPro${reply2probing.id?c }','${reply2probing.id?c }');">继续追问</a> </span>
													<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
												</span>
											</div>
										</#if>
									</#if>
								</#list>
							<#else>
								<div class="q_replenish2" id="proArea${probing.id?c }">
									<span id="modPro${probing.id?c}"> <#if question.status == 1 >
											<span class="cc"><a onclick="toAcceptReplyUrl('${reply.user.id?c }')">选为满意答案</a>&nbsp;&nbsp;</span>
										</#if> <span class="cc2"><a href="javascript:void(0)" onclick="showEdit3('edit','pro${probing.id?c }','proArea${probing.id?c }','modPro${probing.id?c }','${probing.id?c }');">修改追问</a> </span>
										<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
									</span>
								</div>
							</#if>
						</#if>
					</#list>
					</#if>
				<#else>
					<div class="q_replenish2" id="proAddArea${reply.id?c }">
						<span id="addPro${reply.id?c}"> <#if  question.status == 1 >
								<span class="cc"><a onclick="toAcceptReplyUrl('${reply.user.id?c }')">选为满意答案</a>&nbsp;&nbsp;</span>
							</#if> <span class="cc"><a href="javascript:void(0)" onclick="showEdit3('add','','proAddArea${reply.id?c }','addPro${reply.id?c }','${reply.id?c }');">继续追问</a> </span>
							<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
						</span>
					</div>
				</#if>
				<!-- 评分 -->
				<#if  question.status==1 >
					<div id="${reply.user.id?c}givestar" style="display: block;">
						<script type='text/javascript'>score();</script>
					</div>
				</#if>
				<!-- 评论开始 -->
				<div id="${reply.id?c}commenturl" style="margin-left: 600px;">
					&nbsp;&nbsp;&nbsp;<a onclick="openComment('${reply.id?c}');"><font color="#2DA508" id="${reply.id?c}commentNum">评论(${reply.commentNum })</font></a>
				</div>
				<div id="${reply.id?c}removecomment" style="display: none;margin-left: 600px;">
					&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="removecomment('${reply.id?c}');"><font color="#2DA508">收起评论</font></a>
				</div>
				<div id="${reply.id?c}listcomment" style="display: none; width: 633px; margin-left: 20px; margin-top: 10px; margin-bottom: 10px;">
					<div id="${reply.id}addc">
						回复：<br />
						<textarea id="${reply.id?c}replyCommentContent" style="width: 622px; border: 1px solid #006940;"></textarea>
						<div style="margin-top: 10px; margin-left: 463px;">
							<a onclick="addreplycomment('${reply.id?c}','${reply.commentNum}');"><img src="${bathPath}/images/ask_tj.jpg" width="77px" height="30px;"></img></a> <a
								onclick="removecomment('${reply.id?c}');"><img src="${bathPath}/images/ask_qx.jpg" width="77px" height="30px;"></img></a>
						</div>
					</div>
					<div id="${reply.id?c}listc"></div>
				</div>
				<!-- 评论结束 -->
				<div class="q_line"></div>
			</#if>
		</#list>
	</div>
</#if>