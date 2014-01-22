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
			提问者：<span class="g12">${question.user.screenName }</span> | 浏览次数：${question.viewCount+1}次
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
			<#list qImgList as qImg>
				<li><a href="${bathPath}/question/${qImg.url }"> <img src="${bathPath}/question/${qImgList.url }" width="200px" height="140px" />
				</a></li>
			</#list>
		</ul>
	</div>
	<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
	<!--问题补充开始-->
	<#if question.extraContent??>
		<div class="q_replenish2">
			<div class="fon14 fl">问题补充：</div>
			<div class="con fl">
				<div style="white-space: pre-wrap; word-wrap: break-word; display: inline; font-size: 14px">${question.extraContent }</div>
			</div>
			<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
		</div>
	</#if>
	<!-- 问题标签 -->
	<div style="margin-left: 20px; margin-bottom: 20px;">
		标签：
		<#list qkList as qk>
			<a class="g12" target="_blank"  onclick="urlEnco('${qk.keyword}')">${qk.keyword}</a>
		</#list>
	</div>
	<!--我来帮他解答开始-->
	<#if question.status!=99>
		<div class="q_answer">
			<div class="gb12">您还没有登录，请&nbsp;<a style="color: red;" href="http://sso.passport.longcom.com/login?service=http://ask.longcom.com/question/${question.id?c}.html" >登录</a>&nbsp;后再进行回复操作。</div>
		</div>
	<!-- 问题已经关闭提示开始 -->
	<#else>
		<div class="q_answer">
			<b style="color: #006940;">该问题已经过去很久，无法进行回答...</b>
		</div>
	</#if>
	<!-- 问题已经关闭提示结束 -->
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
			<div id="${(reply.id)?c}commenturl"  style="margin-left: 600px;">
				&nbsp;&nbsp;&nbsp;<a onclick="openComment('${(reply.id)?c}');"><font color="#2DA508" id="${(reply.id)?c}commentNum">评论(${reply.commentNum })</font></a>
			</div>
			<div id="${(reply.id)?c}removecomment" style="display: none;margin-left: 600px;">
				&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="removecomment('${(reply.id)?c}');"><font color="#2DA508">收起评论</font></a>
			</div>
			<div id="${(reply.id)?c}listcomment" style="display: none; width: 633px; margin-left: 20px; margin-top: 10px; margin-bottom: 10px;">
				<div id="${(reply.id)?c}addc">
				</div>
				<div id="${(reply.id)?c}listc"></div>
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
<#if (question.replyNum>0&&question.status!=2)||(question.replyNum >1 && question.status==2)>
	<div class="q_quest02">
		<!--标题开始-->
		<div class="q_tit">
			<div class="icon fl">
				<img src="${bathPath}/images/q_03.jpg" width="18" height="18" />
			</div>
			<div class="fon14 fl">
				<#if question.status == 2>
					其他答案
				<#else>
					答案
				</#if>
			</div>
			<div class="fg12 fl">
				共
				<#if question.replyNum gt 0 && question.status == 2>
					${question.replyNum-1 }
				<#else>
					${question.replyNum }
				</#if>
				条
			</div>
		</div>
		<#list replyList as reply>
			<#if reply.accepted == 0 && !(reply.probingId??) >
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
				<!--问题内容息开始-->
				<div class="q_answer_con">
					<div id="reply${reply.id }" style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply.content}</div>
				</div>
				<#if reply.status == 2>
					<#list probingList as probing>
						<#if probing.reply.id == reply.id>
							<div class="q_replenish2">
								<div class="bfon14 fl">${question.user.username }：</div>
								<div class="con fl">
									<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing.content }</div>
								</div>
								<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
							</div>
							<#if probing.status == 2>
								<#list replyList as reply2probing>
									<#if reply2probing.probingId?? && probing.id== reply2probing.probingId>
										<div class="q_replenish2">
											<div class="bfon14 fl">${reply.user.screenName }：</div>
											<div class="con fl">
												<div id="reply${reply2probing.id }" style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing.content}</div>
											</div>
											<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
										</div>
										<#if reply2probing.status == 2>
											<#list probingList as probing2>
												<#if probing2.reply.id == reply2probing.id >
													<div class="q_replenish2">
														<div class="bfon14 fl">${question.user.username }：</div>
														<div class="con fl">
															<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing2.content }</div>
														</div>
														<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
													</div>
													<#if probing2.status == 2>
														<#list replyList as reply2probing2>
															<#if reply2probing2.probingId?? && reply2probing2.probingId == probing2.id >
																<div class="q_replenish2">
																	<div class="bfon14 fl">${reply.user.screenName }：</div>
																	<div class="con fl">
																		<div id="reply${reply2probing2.id }" style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing2.content}</div>
																	</div>
																	<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																</div>
																<#if reply2probing2.status == 2 >
																	<#list probingList as probing3 >
																		<#if probing3.reply.id == reply2probing2.id >
																			<div class="q_replenish2">
																				<div class="bfon14 fl">${question.user.username }：</div>
																				<div class="con fl">
																					<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing3.content }</div>
																				</div>
																				<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																			</div>
																			<#if test="${probing3.status == 2}">
																				<#list replyList as reply2probing3 >
																					<#if reply2probing3.probingId?? && reply2probing3.probingId == probing3.id >
																						<div class="q_replenish2">
																							<div class="bfon14 fl">${reply.user.screenName }：</div>
																							<div class="con fl">
																								<div id="reply${reply2probing3.id }" style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing3.content}</div>
																							</div>
																							<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																						</div>
																					</#if>
																				</#list>
																			</#if>
																		</#if>
																	</#list>
																</#if>
															</#if>
														</#list>
													</#if>
												</#if>
											</#list>
										</#if>
									</#if>
								</#list>
							</#if>
						</#if>
					</#list>
				</#if>
				<!-- 评论开始 -->
				<div id="${(reply.id)?c}commenturl" style="margin-left: 600px;">
					&nbsp;&nbsp;&nbsp;<a onclick="openComment('${(reply.id)?c}');"><font color="#2DA508" id="${(reply.id)?c}commentNum">评论(${reply.commentNum })</font></a>
				</div>
				<div id="${(reply.id)?c}removecomment" style="display: none;margin-left: 600px;">
					&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="removecomment('${(reply.id)?c}');"><font color="#2DA508">收起评论</font></a>
				</div>
				<div id="${(reply.id)?c}listcomment" style="display: none; width: 633px; margin-left: 20px; margin-top: 10px; margin-bottom: 10px;">
					<div id="${(reply.id)?c}addc">
					</div>
					<div id="${(reply.id)?c}listc"></div>
				</div>
				<!-- 评论结束 -->
				<div class="q_line" style="margin-top: 20px;"></div>
			</#if>
		</#list>
	</div>
</#if>