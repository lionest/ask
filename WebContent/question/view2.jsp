<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="wd" uri="http://wd.ah3nong.com/tags"%>
<script>
	var acceptReplyUrl ;
	var acceptReplyScore = null ;
	var questionId = ${question.id};
	function showProbing(id) {
		//alert(id);
		document.getElementById("probing" + id).style.display = "block";
	}
	function showReply(id) {
		//alert(id);
		document.getElementById("replytoprobing" + id).style.display = "block";
	}

	function showExtr(Img) {
		Img.src = "${pageContext.request.contextPath}/images/detail_04.jpg";
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
			form.action = "${pageContext.request.contextPath}/p/question/addProbing.html";
		} else if (actionType = "edit") {
			form.action = "${pageContext.request.contextPath}/p/question/editProbing.html";
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
			questionIdHidden.value = "${questionId}";
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
		inputSubmit.style.background = "url(${pageContext.request.contextPath}/images/detail_01.jpg) no-repeat";
		inputSubmit.style.width = "50px";
		inputSubmit.style.height = "35px";
		inputSubmit.style.cursor = "pointer";
		inputSubmit.value = "";
		var inputReturn = document.createElement("input");
		inputReturn.type = "button";
		inputReturn.style.background = "url(${pageContext.request.contextPath}/images/detail_02.jpg) no-repeat";
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
				acceptReplyUrl = "${pageContext.request.contextPath}/p/question/acceptReply/${question.id }_"+replyuid+"_"+acceptReplyScore+".html";
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
	//点击选为满意答案跳转链接
	function toAcceptReplyUrl(replyuserid){
		$('#'+replyuserid+'givestar').append("<div id='star' style='width: 250px;margin: 10px;'><span>为答案打分</span><ul style='line-height: 100px;'><li><a href='javascript:;'>1</a></li><li><a href='javascript:;'>2</a></li><li><a href='javascript:;'>3</a></li><li><a href='javascript:;'>4</a></li><li><a href='javascript:;'>5</a></li></ul><span></span><p></p></div><input type='hidden' id='replyUserId' value=''>"); 
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
			<img src="${pageContext.request.contextPath}/images/q_01.jpg" width="18" height="18" />
		</div>
		<div class="fon14 fl">${question.subject }</div>
	</div>
	<!--提问者信息开始-->
	<div class="q_info">
		<div class="fl">
			提问者：<span class="g12">
			<c:choose>
				<c:when test="${question.anonymous==1}">
					匿名网友
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/user/${question.user.id}.html" target="_blank">${question.user.screenName }</a>
					<c:if test="${question.user.expert!=1 }">
						<wd:userLevel var="userLevel0" userId="${question.user.id}" />
						<a style="color:rgb(102, 102, 102);" href="${pageContext.request.contextPath}/user/levelhelper.jsp" target="_blank" >
							(<s:property value="#attr.userLevel0" />)
						</a>
					</c:if>
				</c:otherwise>
			</c:choose>
			</span> 
			| 浏览次数：${question.viewCount+1 }次
			<c:if test="${question.experience > 0 }">
				| <img src="${pageContext.request.contextPath}/images/explogo.jpg" width="13px" height="13px" />
				悬赏分：
				<font color="#DA8301">${question.experience}分</font>
			</c:if>
		</div>
		<div class="fr">
			<fmt:formatDate value="${question.createdTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss " />
		</div>
	</div>
	<!--问题内容息开始-->
	<div class="q_Content">${question.content }</div>
	<!-- 问题图片 -->
	<div class="q_replenish2" id="gallery">
		<ul>
			<c:forEach items="${qImgList}" var="qImgList1">
				<li>
					<a href="${pageContext.request.contextPath}/question/${qImgList1.url }"  > 
						<img src="${pageContext.request.contextPath}/question/${qImgList1.url }" width="200px" height="140px" />
					</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div class="q_replenish2">
		<c:forEach items="${qImgList}" var="qImgList">
			<div class="con fl" id="IMGoutId${qImgList.id }" style="margin-left: 5px;width: 200px;">
				<div id="${qImgList.id }IMGEdit">
					<a style="color: gray; cursor: pointer" onClick="editImg(${qImgList.id });">修改</a> &nbsp;<a style="color: gray; cursor: pointer" onmouseover="changeColor(this,1);"
						onmouseout="changeColor(this,2);" href="${pageContext.request.contextPath}/p/question/removeQuestionImg/${qImgList.id }.html">删除</a>
				</div>
				<!-- 隐藏的选择图片。提交图片层 -->
				<div id="${qImgList.id }selectQuestionImg" style="display: none; width: 200px;">
					<form action="${pageContext.request.contextPath}/p/question/editQuestionImg.html" method="post" enctype="multipart/form-data">
						<input type="hidden" name="questionImg.id" value="${qImgList.id }" /> <input type="hidden" name="questionImg.imgOrder" value="${qImgList.imgOrder }" /> <input type="file" name="questionImgs" /><br />
						<input type="submit" value="提交" /> <input type="button" onclick="returnToQuestion(${qImgList.id })" value="返回" />
					</form>
				</div>
			</div>
		</c:forEach>
	</div>
	<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
	<c:if test="${question.extraContent != null}">
		<div class="q_replenish2">
			<div class="fon14 fl">问题补充：</div>
			<div class="con fl">
				<pre style="white-space: pre-wrap; word-wrap: break-word; display: inline; font-size: 14px">${question.extraContent }</pre>
			</div>
			<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
		</div>
	</c:if>
	<c:if test="${question.status != 2}">
		<div class="q_replenish2">
			<!-- 补充提问 -->
			<span class="cc"><img src="${pageContext.request.contextPath}/images/detail_03.jpg" onmouseover="javascript:this.src='${pageContext.request.contextPath}/images/detail_04.jpg'"
				onmouseout="javascript:this.src='${pageContext.request.contextPath}/images/detail_03.jpg'" onclick="showExtr(this);" style="cursor: pointer;" /></span>
			<div style="display: none" id="qExtr">
				<form action="${pageContext.request.contextPath}/p/question/edit.html" method="post">
					<input type="hidden" name="question.id" value="${question.id }" />
					<textarea name="question.extraContent" rows="5" cols="80" onfocus="this.style.border= '#69C74C 2px solid '; " onblur="this.style.border='#69C74C 1px solid'"
						style="margin-bottom: 10px; border: #69C74C 1px solid; font-size: 14px">${question.extraContent }</textarea>
					<div style="float: right">
						<input style="cursor:pointer;background:url(${pageContext.request.contextPath}/images/detail_01.jpg) no-repeat; width:50px; height:35px; cursor:hand;" type="submit" value="" /> <input
							type="button" value="" style="cursor:pointer;background:url(${pageContext.request.contextPath}/images/detail_02.jpg) no-repeat; width:50px; height:35px; cursor:hand;" type="button"
							onclick="javascript:window.location.reload()" />
					</div>
					<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
				</form>
			</div>
		</div>
	</c:if>
</div>
<!-- 审核中的追问 -->
<c:if test="${auditProbingList!=null && fn:length(auditProbingList)>0}">
	<div class="q_xgqus">
		<div class="sbar">
			<h2 style="color: red;">审核中的追问</h2>
		</div>
		<!-- 标题开始-->
		<div class="pad6">
			<ul class="q_wtlist">
				<c:forEach items="${auditProbingList}" var="auditProbing">
					<li >
						追问内容：<br/>
						<font color="black" id="pro${auditProbing.id}">${auditProbing.content }</font>
						<c:if test="${auditProbing.status==5 }">
							<div class="q_replenish2" id="proArea${auditProbing.id }">
								<span id="modPro${auditProbing.id}"> <span class="cc2" ><a href="javascript:void(0)"
										onclick="showEdit3('edit','pro${auditProbing.id }','proArea${auditProbing.id}','modPro${auditProbing.id }','${auditProbing.id }');">修改追问</a> </span>
									<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
								</span>
							</div>
						</c:if>
					</li>
					<div class="q_answer"  id="editReply${auditProbing.id}"  style="padding-left: 0px;"></div>
					<li>
						审核状态：<br/>
						<c:if test="${auditProbing.status==4 }">
							<font color="blue">审核中...</font>
						</c:if>
						<c:if test="${auditProbing.status==5 }">
							<font color="red">审核未通过...</font>
						</c:if>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</c:if>
<!-- 审核中的追问结束 -->
<!--  采纳答案开始-->
<c:forEach items="${replyList}" var="reply">
	<c:if test="${reply.accepted eq 1 && reply.probingId eq null}">
		<!--  采纳答案开始-->
		<div class="q_quest02">
			<!--标题开始-->
			<div class="q_tit">
				<div class="icon fl">
					<img src="${pageContext.request.contextPath}/images/q_02.jpg" width="18" height="18" />
				</div>
				<div class="fon14 fl">采纳答案</div>
			</div>
			<!--回答者信息开始-->
			<div class="q_info">
				<c:if test="${reply.user.id!=1}">
					<div class="fl">
						回答者
						<span class="g12"><a href="${pageContext.request.contextPath}/user/${reply.user.id}.html" target="_blank">${reply.user.screenName}</a>
							<c:if test="${reply.user.expert!=1 }">
								<wd:userLevel var="userLevel1" userId="${reply.user.id}" />
								<a style="color:rgb(102, 102, 102);" href="${pageContext.request.contextPath}/user/levelhelper.jsp" target="_blank" >
									(<s:property value="#attr.userLevel1" />)
								</a>
							</c:if>
						</span> 
					</div>
				</c:if>
				<c:if test="${reply.user.expert eq 1}">
					<div class="zjicon fl">
						<img src="${pageContext.request.contextPath}/images/q_04.jpg" width="15" height="13" />
					</div>
				</c:if>
				<div class="fr">
					<fmt:formatDate value="${reply.createdTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss " />
				</div>
			</div>
			<!--问题内容息开始-->
			<div class="q_answer_con">
				<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply.content}</div>
			</div>
			<c:forEach items="${probingList}" var="probing">
				<c:if test="${probing.reply.id eq reply.id }">
					<div class="q_replenish2">
						<div class="bfon14 fl">追问：</div>
						<div class="con fl">
							<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing.content }</div>
						</div>
						<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
					</div>
					<c:forEach items="${replyList}" var="reply2probing">
						<c:if test="${reply2probing.probingId eq probing.id }">
							<div class="q_replenish2">
								<div class="bfon14 fl">回答：</div>
								<div class="con fl">
									<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing.content}</div>
								</div>
								<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
							</div>
							<c:forEach items="${probingList}" var="probing2">
								<c:if test="${probing2.reply.id eq reply2probing.id }">
									<div class="q_replenish2">
										<div class="bfon14 fl">追问：</div>
										<div class="con fl">
											<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing2.content }</div>
										</div>
										<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
									</div>
									<c:forEach items="${replyList}" var="reply2probing2">
										<c:if test="${reply2probing2.probingId eq probing2.id }">
											<div class="q_replenish2">
												<div class="bfon14 fl">回答：</div>
												<div class="con fl">
													<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing2.content}</div>
												</div>
												<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
											</div>
											<c:forEach items="${probingList}" var="probing3">
												<c:if test="${probing3.reply.id eq reply2probing2.id }">
													<div class="q_replenish2">
														<div class="bfon14 fl">追问：</div>
														<div class="con fl">
															<div id="pro${probing3.id }" style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing3.content }</div>
														</div>
														<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
													</div>
													<c:if test="${probing3.status eq 2}">
														<c:forEach items="${replyList}" var="reply2probing3">
															<c:if test="${reply2probing3.probingId eq probing3.id }">
																<div class="q_replenish2">
																	<div class="bfon14 fl">回答：</div>
																	<div class="con fl">
																		<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing3.content}</div>
																	</div>
																	<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																</div>
															</c:if>
														</c:forEach>
													</c:if>
												</c:if>
											</c:forEach>
										</c:if>
									</c:forEach>
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>
				</c:if>
			</c:forEach>
			<!-- 评论开始 -->
				<%-- <div id="${reply.id}commenturl" style="margin-left: 600px;">
					&nbsp;&nbsp;&nbsp;<a onclick="openComment('${reply.id}');"><font color="#2DA508" id="${reply.id}commentNum">评论(${reply.commentNum })</font></a>
				</div>
				<div id="${reply.id}removecomment" style="display: none;margin-left: 600px;">
					&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="removecomment('${reply.id}');"><font color="#2DA508">收起评论</font></a>
				</div>
				<div id="${reply.id}listcomment" style="display: none; width: 633px; margin-left: 20px; margin-top: 10px; margin-bottom: 10px;">
					<div id="${reply.id}addc">
						回复：<br />
						<textarea id="${reply.id}replyCommentContent" style="width: 622px; border: 1px solid #006940;"></textarea>
						<div style="margin-top: 10px; margin-left: 463px;">
							<a onclick="addreplycomment('${reply.id}','${reply.commentNum}');"><img src="${pageContext.request.contextPath}/images/ask_tj.jpg" width="77px" height="30px;"></img></a> <a
								onclick="removecomment('${reply.id}');"><img src="${pageContext.request.contextPath}/images/ask_qx.jpg" width="77px" height="30px;"></img></a>
						</div>
					</div>
					<div id="${reply.id}listc"></div>
				</div> --%>
				<!-- 评论结束 -->
			<div class="q_Comment">
				<div class="fr">${scoreContent }</div>
				<c:if test="${score == 1}">
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_07.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_07.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_07.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_07.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_05.jpg" width="22" height="21" />
					</div>
				</c:if>
				<c:if test="${score == 2}">
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_07.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_07.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_07.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_05.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_05.jpg" width="22" height="21" />
					</div>
				</c:if>
				<c:if test="${score == 3}">
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_07.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_07.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_05.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_05.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_05.jpg" width="22" height="21" />
					</div>
				</c:if>
				<c:if test="${score == 4 }">
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_07.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_05.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_05.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_05.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_05.jpg" width="22" height="21" />
					</div>
				</c:if>
				<c:if test="${score == 5 || score == 0}">
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_05.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_05.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_05.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_05.jpg" width="22" height="21" />
					</div>
					<div class="star fr">
						<img src="${pageContext.request.contextPath}/images/q_05.jpg" width="22" height="21" />
					</div>
				</c:if>
				<div class="fr">评价：</div>
			</div>
		</div>
		<!--  采纳答案结束-->
	</c:if>
</c:forEach>
<!--  采纳答案结束-->
<!--  其他答案开始-->
<c:if test="${(question.replyNum>0&&question.status!=2)||(question.replyNum >1 && question.status==2) }">
	<div class="q_quest02">
		<!--标题开始-->
		<div class="q_tit">
			<div class="icon fl">
				<img src="${pageContext.request.contextPath}/images/q_03.jpg" width="18" height="18" />
			</div>
			<div class="fon14 fl">
				<c:choose>
					<c:when test="${question.status eq 2}">
						其他答案
					</c:when>
					<c:otherwise>
						答案
					</c:otherwise>
				</c:choose>
			</div>
			<div class="fg12 fl">
				共
				<c:choose>
					<c:when test="${question.replyNum >0 && question.status eq 2}">${question.replyNum-1 }</c:when>
					<c:otherwise>${question.replyNum }</c:otherwise>
				</c:choose>
				条
			</div>
		</div>
		<c:forEach items="${replyList}" var="reply">
			<c:if test="${reply.accepted eq 0 && reply.probingId eq null}">
				<!--回答者信息开始-->
				<div class="q_info">
					<!-- 锚点 -->
					<a name="${reply.id}" id="${reply.id}" ></a>
					<div class="fl">
						回答者
						<span class="g12"><a href="${pageContext.request.contextPath}/user/${reply.user.id}.html" target="_blank">${reply.user.screenName}</a>
							<c:if test="${reply.user.expert!=1 }">
								<wd:userLevel var="userLevel1" userId="${reply.user.id}" />
								<a style="color:rgb(102, 102, 102);" href="${pageContext.request.contextPath}/user/levelhelper.jsp" target="_blank" >
									(<s:property value="#attr.userLevel1" />)
								</a>
							</c:if>
						</span> 
					</div>
					<c:if test="${reply.user.expert eq 1}">
						<div class="zjicon fl">
							<img src="${pageContext.request.contextPath}/images/q_04.jpg" width="15" height="13" />
						</div>
					</c:if>

					<div class="fr">
						<fmt:formatDate value="${reply.createdTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss " />
					</div>
				</div>
				<!--问题内容息开始-->
				<!--问题内容息开始-->
				<div class="q_answer_con">
					<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply.content}</div>
				</div>
				<c:choose>
					<c:when test="${reply.status eq 2}">
						<c:forEach items="${probingList}" var="probing">
							<c:if test="${probing.reply.id eq reply.id }">
								<div class="q_replenish2">
									<div class="bfon14 fl">追问：</div>
									<div class="con fl">
										<div id="pro${probing.id }" style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing.content }</div>
									</div>
									<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
								</div>
								<c:choose>
									<c:when test="${probing.status eq 2}">
										<c:forEach items="${replyList}" var="reply2probing">
											<c:if test="${reply2probing.probingId eq probing.id }">
												<div class="q_replenish2">
													<div class="bfon14 fl">回答：</div>
													<div class="con fl">
														<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing.content}</div>
													</div>
													<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
												</div>
												<c:choose>
													<c:when test="${reply2probing.status eq 2}">
														<c:forEach items="${probingList}" var="probing2">
															<c:if test="${probing2.reply.id eq reply2probing.id }">
																<div class="q_replenish2">
																	<div class="bfon14 fl">追问：</div>
																	<div class="con fl">
																		<div id="pro${probing2.id }" style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing2.content }</div>
																	</div>
																	<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																</div>
																<c:choose>
																	<c:when test="${probing2.status eq 2}">
																		<c:forEach items="${replyList}" var="reply2probing2">
																			<c:if test="${reply2probing2.probingId eq probing2.id }">
																				<div class="q_replenish2">
																					<div class="bfon14 fl">回答：</div>
																					<div class="con fl">
																						<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing2.content}</div>
																					</div>
																					<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																				</div>
																				<c:choose>
																					<c:when test="${reply2probing2.status eq 2}">
																						<c:forEach items="${probingList}" var="probing3">
																							<c:if test="${probing3.reply.id eq reply2probing2.id }">
																								<div class="q_replenish2">
																									<div class="bfon14 fl">追问：</div>
																									<div class="con fl">
																										<div id="pro${probing3.id }" style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing3.content }</div>
																									</div>
																									<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																								</div>
																								<c:choose>
																									<c:when test="${probing3.status eq 2}">
																										<c:forEach items="${replyList}" var="reply2probing3">
																											<c:if test="${reply2probing3.probingId eq probing3.id }">
																												<div class="q_replenish2">
																													<div class="bfon14 fl">回答：</div>
																													<div class="con fl">
																														<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing3.content}</div>
																													</div>
																													<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																												</div>
																												<div class="q_replenish2">
																													<c:if test="${question.status eq 1}">
																														<span class="cc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="toAcceptReplyUrl('${reply.user.id }')">选为满意答案</a></span>
																													</c:if>
																													<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																												</div>
																											</c:if>
																										</c:forEach>
																									</c:when>
																									<c:otherwise>
																										<div class="q_replenish2" id="proArea${probing3.id }">
																											<span id="modPro${probing3.id}"> <c:if test="${question.status eq 1}">
																													<span class="cc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="toAcceptReplyUrl('${reply.user.id }')">选为满意答案</a></span>
																												</c:if> <span class="cc2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)"
																													onclick="showEdit3('edit','pro${probing3.id }','proArea${probing3.id }','modPro${probing3.id }','${probing3.id }');">修改追问</a>
																											</span>
																												<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																											</span>
																										</div>
																									</c:otherwise>
																								</c:choose>
																							</c:if>
																						</c:forEach>
																					</c:when>
																					<c:otherwise>
																						<div class="q_replenish2" id="proAddArea${reply2probing2.id }">
																							<span id="addPro${reply2probing2.id}"> <c:if test="${question.status eq 1}">
																									<span class="cc">&nbsp;&nbsp;&nbsp;<a onclick="toAcceptReplyUrl('${reply.user.id }')">选为满意答案</a></span>
																								</c:if> <span class="cc">&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)"
																									onclick="showEdit3('add','','proAddArea${reply2probing2.id }','addPro${reply2probing2.id }','${reply2probing2.id }');">继续追问</a>
																							</span>
																								<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																							</span>
																						</div>
																					</c:otherwise>
																				</c:choose>
																			</c:if>
																		</c:forEach>
																	</c:when>
																	<c:otherwise>
																		<div class="q_replenish2" id="proArea${probing2.id }">
																			<span id="modPro${probing2.id}"> <c:if test="${question.status eq 1}">
																					<span class="cc">&nbsp;&nbsp;&nbsp;<a onclick="toAcceptReplyUrl('${reply.user.id }')">选为满意答案</a></span>
																				</c:if> <span class="cc2">&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)"
																					onclick="showEdit3('edit','pro${probing2.id }','proArea${probing2.id }','modPro${probing2.id }','${probing2.id }');">修改追问</a>
																			</span>
																				<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																			</span>
																		</div>
																	</c:otherwise>
																</c:choose>
															</c:if>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<div class="q_replenish2" id="proAddArea${reply2probing.id }">
															<span id="addPro${reply2probing.id}"> <c:if test="${question.status eq 1}">
																	<span class="cc"><a onclick="toAcceptReplyUrl('${reply.user.id }')">选为满意答案</a>&nbsp;&nbsp;</span>
																</c:if> <span class="cc"><a href="javascript:void(0)" onclick="showEdit3('add','','proAddArea${reply2probing.id }','addPro${reply2probing.id }','${reply2probing.id }');">继续追问</a> </span>
																<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
															</span>
														</div>
													</c:otherwise>
												</c:choose>
											</c:if>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<div class="q_replenish2" id="proArea${probing.id }">
											<span id="modPro${probing.id}"> <c:if test="${question.status eq 1}">
													<span class="cc"><a onclick="toAcceptReplyUrl('${reply.user.id }')">选为满意答案</a>&nbsp;&nbsp;</span>
												</c:if> <span class="cc2"><a href="javascript:void(0)" onclick="showEdit3('edit','pro${probing.id }','proArea${probing.id }','modPro${probing.id }','${probing.id }');">修改追问</a> </span>
												<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
											</span>
										</div>
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="q_replenish2" id="proAddArea${reply.id }">
							<span id="addPro${reply.id}"> <c:if test="${question.status eq 1}">
									<span class="cc"><a onclick="toAcceptReplyUrl('${reply.user.id }')">选为满意答案</a>&nbsp;&nbsp;</span>
								</c:if> <span class="cc"><a href="javascript:void(0)" onclick="showEdit3('add','','proAddArea${reply.id }','addPro${reply.id }','${reply.id }');">继续追问</a> </span>
								<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
							</span>
						</div>
					</c:otherwise>
				</c:choose>
				<!-- 评分 -->
				<c:if test="${question.status==1}">
					<div id="${reply.user.id}givestar" style="display: block;">
						<%-- <div id="star" style="width: 250px;margin: 10px;">
							<span>为答案打分</span>
							<ul style="line-height: 100px;">
								<li><a href="javascript:;">1</a></li>
								<li><a href="javascript:;">2</a></li>
								<li><a href="javascript:;">3</a></li>
								<li><a href="javascript:;">4</a></li>
							</ul>
							<span></span>
							<p></p>
						</div><br/>
						<input type="hidden" id="replyUserId" value=""> --%>
						<script type='text/javascript'>score();</script>
					</div>
				</c:if>
				<!-- 评论开始 -->
				<%-- <div id="${reply.id}commenturl" style="margin-left: 600px;">
					&nbsp;&nbsp;&nbsp;<a onclick="openComment('${reply.id}');"><font color="#2DA508" id="${reply.id}commentNum">评论(${reply.commentNum })</font></a>
				</div>
				<div id="${reply.id}removecomment" style="display: none;margin-left: 600px;">
					&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="removecomment('${reply.id}');"><font color="#2DA508">收起评论</font></a>
				</div>
				<div id="${reply.id}listcomment" style="display: none; width: 633px; margin-left: 20px; margin-top: 10px; margin-bottom: 10px;">
					<div id="${reply.id}addc">
						回复：<br />
						<textarea id="${reply.id}replyCommentContent" style="width: 622px; border: 1px solid #006940;"></textarea>
						<div style="margin-top: 10px; margin-left: 463px;">
							<a onclick="addreplycomment('${reply.id}','${reply.commentNum}');"><img src="${pageContext.request.contextPath}/images/ask_tj.jpg" width="77px" height="30px;"></img></a> <a
								onclick="removecomment('${reply.id}');"><img src="${pageContext.request.contextPath}/images/ask_qx.jpg" width="77px" height="30px;"></img></a>
						</div>
					</div>
					<div id="${reply.id}listc"></div>
				</div> --%>
				<!-- 评论结束 -->
				<div class="q_line"></div>
			</c:if>
		</c:forEach>
		
	</div>
</c:if>
