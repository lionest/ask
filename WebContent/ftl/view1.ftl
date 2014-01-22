<#assign ckfinder=JspTaglibs["http://ckfinder.com"]>
<#assign ckeditor=JspTaglibs["http://ckeditor.com"]>
<script type="text/javascript">

	function loadCKeditor(){
	    editor = CKEDITOR.replace('replyContent'); //参数‘content’是textarea元素的name属性值，而非id属性值
	};
	//修改回复，追问后增加新回复
	var questionId = '${question.id?c}';
	var addAreas;
	var replyIds;
	function showEdit3(actionType, addArea, lastReplyId, replyId) {
		addAreas = addArea;
		replyIds = replyId;
		if(actionType=="edit"){
			$("#"+addArea).hide();
			 var len = $("#editReply"+replyId).text().length;   
			 if(len>0){
				 $("#editReply"+replyIds).show();
			 }else{
				$("#editReply"+replyId).append("<div class='gb12'>修改回答：</div><form action='${bathPath}/p/question/editReply.html' onsubmit='return checkReplyLength();' method='post'><input type='hidden' id='editReplyId' value='' name='reply.id' /><textarea style='overflow: auto' class='texdet'  name='replyContent' ></textarea><p align='letf' ><font color='#707070'>&nbsp;回答字数最多不能超过4000字。</font></p><div class='fer'><input type='submit' value='' style='background: url(../images/detail_01.jpg) no-repeat scroll 0% 0% transparent; width: 50px; height: 35px; cursor: pointer;' ></input><input type='button' onclick='removeEdit()' style='background: url(../images/detail_02.jpg) no-repeat scroll 0% 0% transparent; margin: 10px 0px 0px 7px; width: 50px; height: 35px; cursor: pointer;'></input></div></form>");
				editor = CKEDITOR.replace('replyContent');
			 }
			var oldContent = $("#reply"+lastReplyId).html();
			CKEDITOR.instances.replyContent.setData(oldContent); 
			$("#editReplyId").val(lastReplyId);
		}else{
			$("#"+addArea).hide();
			 var len = $("#editReply"+replyId).text().length;   
			 if(len>0){
				 $("#editReply"+replyIds).show();
			 }else{
				$("#editReply"+replyId).append("<div class='gb12'>我来回答：</div><form action='${bathPath}/p/question/addReply.html' onsubmit='return checkReplyLength();' method='post'><input type='hidden' id='addProbingId' value='' name='reply.probingId' /><input type='hidden' id='addReplyQuestionId' value='' name='reply.questionId' /><textarea style='overflow: auto' class='texdet'  name='replyContent' ></textarea><p align='letf' ><font color='#707070'>&nbsp;回答字数最多不能超过4000字。</font></p><div class='fer'><input type='submit' value='' style='background: url(../images/detail_01.jpg) no-repeat scroll 0% 0% transparent; width: 50px; height: 35px; cursor: pointer;' ></input><input type='button' onclick='removeEdit()' style='background: url(../images/detail_02.jpg) no-repeat scroll 0% 0% transparent; margin: 10px 0px 0px 7px; width: 50px; height: 35px; cursor: pointer;'></input></div></form>");
				editor = CKEDITOR.replace('replyContent');
			 }
			$("#addReplyQuestionId").val(questionId);
			$("#addProbingId").val(lastReplyId);
		}
	}
	
	function removeEdit(){
		$("#"+addAreas).show();
		$("#editReply"+replyIds).hide();
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
	<div class="q_replenish2" id="gallery">
     	<ul>
	     	<#list qImgList as qImgList>
		        <li>
			        <a href="${bathPath}/question/${qImgList.url }">
			        	<img src="${bathPath}/question/${qImgList.url }" width="200px;" height="140px"/>
			        </a>
		        </li>    
	    	</#list>
    	</ul>
      </div>
	<!--问题补充开始-->
	<#if question.extraContent??>
		<div class="q_replenish2">
			<div class="fon14 fl">问题补充：</div>
			<div class="con fl">
				<div id="content${question.id?c }" style="white-space: pre-wrap; word-wrap: break-word; display: inline">${question.extraContent }</div>
			</div>
			<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
		</div>
	</#if>
	<!--我来帮他解答开始-->
	<#if userType == 2  && question.status == 1>
		<div class="q_answer">
			<div class="gb12">我来帮他解答：</div>
			<form action="${bathPath}/p/question/addReply.html" onsubmit="return checkReplyLength();" method="post">
				<input type="hidden" name="reply.questionId" value="${question.id }" />
				<textarea style="overflow: auto" class="texdet" name="replyContent"  onKeyUp="count(this,this.form.total,this.form.used,this.form.remain);"
					onfocus="this.style.border= '#69C74C 1px solid '; " onblur="this.style.border='#C6C4C4 1px solid'"></textarea>
				<p align="letf" >
					<font color="#707070">&nbsp;回复字数最多不能超过4000字。</font>
				</p>
				<div class="fr">
					<input type="submit" name="button" id="button" value="" class="bt1" />
				</div>
				<script type="text/javascript">loadCKeditor();</script>
			</form>
			<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
		</div>
	</#if>
	<!-- 问题标签 -->
	<div style="margin-left: 20px; margin-bottom: 20px;">
		标签：
		<#list  qkList as qk >
			<a class="g12" target="_blank"  onclick="urlEnco('${qk.keyword}');">${qk.keyword}</a>
		</#list>
	</div>
</div>
<!--  问题结束-->
<!--  采纳答案开始-->
<#list replyList as reply >
	<#if reply.accepted == 1 && !(reply.probingId??)>
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
				<#if reply.user.id!=17>
					<div class="fl">
						回答者 <span class="g12">${reply.user.screenName }</span>
					</div>
				</#if>
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
			<div class="q_answer_con">
				<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply.content}</div>
			</div>
			<#list probingList as probing>
				<#if probing.reply.id == reply.id >
					<div class="q_replenish2">
						<div class="bfon14 fl">${question.user.username }：</div>
						<div class="con fl">
							<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing.content }</div>
						</div>
						<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
					</div>
					<#list replyList as reply2probing>
						<#if reply2probing.probingId?? && reply2probing.probingId == probing.id >
							<div class="q_replenish2">
								<div class="bfon14 fl">${reply.user.screenName }：</div>
								<div class="con fl">
									<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing.content}</div>
								</div>
								<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
							</div>
							<#list probingList as probing2>
								<#if probing2.reply.id == reply2probing.id>
									<div class="q_replenish2">
										<div class="bfon14 fl">${question.user.username }：</div>
										<div class="con fl">
											<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing2.content }</div>
										</div>
										<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
									</div>
									<#list replyList as reply2probing2>
										<#if reply2probing2.probingId?? && reply2probing2.probingId == probing2.id>
											<div class="q_replenish2">
												<div class="bfon14 fl">${reply.user.screenName }：</div>
												<div class="con fl">
													<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing2.content}</div>
												</div>
												<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
											</div>
											<#list probingList as probing3>
												<#if probing3.replyId == reply2probing2.id>
													<div class="q_replenish2">
														<div class="bfon14 fl">${question.user.username }：</div>
														<div class="con fl">
															<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing3.content }</div>
														</div>
														<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
													</div>
													<#list replyList as reply2probing3>
														<#if reply2probing3.reply.id == probing3.id>
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
											</#list>
										</#if>
									</#list>
								</#if>
							</#list>
						</#if>
					</#list>
				</#if>
			</#list>
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
<#if  (question.replyNum gt 0&&question.status!=2)||(question.replyNum gt 1 && question.status==2) >
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
				<#if question.replyNum gt 0 && question.status == 2 >${question.replyNum-1 }
				<#else>${question.replyNum }</#if>
				条
			</div>
		</div>
		<#list replyList as reply>
			<#if reply.accepted == 0 && !(reply.probingId??)>
				<!--回答者信息开始-->
				<div class="q_info">
					<div class="fl">
						回答者 <span class="g12">${reply.user.screenName }</span>
					</div>
					<#if reply.user.expert == 1>
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
					<div id="reply${reply.id?c }" style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply.content}</div>
				</div>
				<#if reply.status == 2>
				<#if probingList??>
					<#list probingList as probing>
						<#if probing.reply.id == reply.id  >
							<div class="q_replenish2">
								<div class="bfon14 fl">${question.user.username }：</div>
								<div class="con fl">
									<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing.content }</div>
								</div>
								<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
							</div>
							<#if probing.status == 2 >
								<#list replyList as reply2probing>
									<#if reply2probing.probingId?? && reply2probing.probingId == probing.id  >
										<div class="q_replenish2">
											<div class="bfon14 fl">${reply.user.screenName }：</div>
											<div class="con fl">
												<div id="reply${reply2probing.id?c }" style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing.content}</div>
											</div>
											<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
										</div>
										<#if reply2probing.status == 2>
											<#list  probingList as probing2 >
												<#if probing2.reply.id == reply2probing.id >
													<div class="q_replenish2">
														<div class="bfon14 fl">${question.user.username }：</div>
														<div class="con fl">
															<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing2.content }</div>
														</div>
														<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
													</div>
													<#if probing2.status == 2 >
														<#list replyList as reply2probing2>
															<#if reply2probing2.probingId?? && reply2probing2.probingId == probing2.id >
																<div class="q_replenish2">
																	<div class="bfon14 fl">${reply.user.screenName }：</div>
																	<div class="con fl">
																		<div id="reply${reply2probing2.id?c }" style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing2.content}</div>
																	</div>
																	<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																</div>
																<#if reply2probing2.status == 2 >
																	<#list probingList as probing3>
																		<#if probing3.reply.id == reply2probing2.id  >
																			<div class="q_replenish2">
																				<div class="bfon14 fl">${question.user.username }：</div>
																				<div class="con fl">
																					<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing3.content }</div>
																				</div>
																				<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																			</div>
																			<#if probing3.status == 2>
																				<#list replyList as reply2probing3 >
																					<#if  reply2probing3.probingId?? && reply2probing3.probingId == probing3.id >
																						<div class="q_replenish2">
																							<div class="bfon14 fl">${reply.user.screenName }：</div>
																							<div class="con fl">
																								<div id="reply${reply2probing3.id?c }" style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing3.content}</div>
																							</div>
																							<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																						</div>
																						<#if reply2probing3.user.id == user.id >
																							<div class="q_replenish2" id="replyArea${reply2probing3.id?c }">
																								<span id="modReply${reply2probing3.id?c}"> <span class="cc2">&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)"
																										onclick="showEdit3('edit','replyArea${reply2probing3.id?c }','${reply2probing3.id?c}','${reply.id?c }');">修改回答</a>
																								</span>
																									<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																								</span>
																							</div>
																						</#if>
																					</#if>
																				</#list>
																			<#elseif probing3.reply.userId == user.id >
																				<div class="q_replenish2" id="replyAddArea${probing3.id?c }">
																					<span id="addReply${probing3.id?c}"> <span class="cc">&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)"
																							onclick="showEdit3('add','replyAddArea${probing3.id?c }','${probing3.id?c }','${reply.id?c }');">我来回答</a>
																					</span>
																						<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																					</span>
																				</div>
																			</#if>
																		</#if>
																	</#list>
																<#elseif reply2probing2.user.id == user.id>
																	<div class="q_replenish2" id="replyArea${reply2probing2.id?c }">
																		<span id="modReply${reply2probing2.id?c}"> <span class="cc2">&nbsp;&nbsp;<a href="javascript:void(0)"
																				onclick="showEdit3('edit','replyArea${reply2probing2.id?c }','${reply2probing2.id?c}','${reply.id?c }');">修改回答</a>
																		</span>
																			<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																		</span>
																	</div>
																</#if>
															</#if>
														</#list>
													<#elseif  probing2.reply.userId == user.id >
														<div class="q_replenish2" id="replyAddArea${probing2.id?c }">
															<span id="addReply${probing2.id?c}"> <span class="cc">&nbsp;&nbsp;<a href="javascript:void(0)"
																	onclick="showEdit3('add','replyAddArea${probing2.id?c }','${probing2.id?c }','${reply.id?c }');">我来回答</a>
															</span>
																<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
															</span>
														</div>
													</#if>
												</#if>
											</#list>
										<#elseif reply2probing.user.id == user.id>
											<div class="q_replenish2" id="replyArea${reply2probing.id?c }">
												<span id="modReply${reply2probing.id?c}"> <span class="cc2"><a href="javascript:void(0)"
														onclick="showEdit3('edit','replyArea${reply2probing.id?c }','${reply2probing.id?c}','${reply.id?c }');">修改回答</a> </span>
													<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
												</span>
											</div>
										</#if>
									</#if>
								</#list>
							<#elseif probing.reply.userId == user.id>
								<div class="q_replenish2" id="replyAddArea${probing.id?c }">
									<span id="addReply${probing.id?c}"> <span class="cc"><a href="javascript:void(0)"
											onclick="showEdit3('add','replyAddArea${probing.id?c }','${probing.id?c }','${reply.id?c }');">我来回答</a> </span>
										<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
									</span>
								</div>
							</#if>
						</#if>
					</#list>
					</#if>
				<#elseif reply.user.id == user.id >
					<div class="q_replenish2" id="replyArea${reply.id?c }">
						<span id="modReply${reply.id?c}"> <span class="cc2"><a href="javascript:void(0)"
								onclick="showEdit3('edit','replyArea${reply.id?c }','${reply.id?c}','${reply.id?c }');">修改回答</a> </span>
							<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
						</span>
					</div>
				</#if>
				<!-- 修改回答 -->
				<div class="q_answer"  id="editReply${reply.id?c}" ></div>
				<!-- 修改回答 -->
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
