<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="wd" uri="http://wd.ah3nong.com/tags"%>
<%@ taglib uri="http://ckfinder.com" prefix="ckfinder"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<script type="text/javascript">
	//修改回复，追问后增加新回复
	var questionId = '${question.id}';
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
				$("#editReply"+replyId).append("<div class='gb12'>修改回答：</div><form action='${pageContext.request.contextPath}/p/question/editReply.html' onsubmit='return checkReplyLength();' method='post'><input type='hidden' id='editReplyId' value='' name='reply.id' /><textarea style='overflow: auto' class='texdet'  name='replyContent' id='replyContent'></textarea><p align='letf' ><font color='#707070'>&nbsp;回答字数最多不能超过4000字。</font></p><div class='fer'><input type='submit' value='' style='background: url(../images/detail_01.jpg) no-repeat scroll 0% 0% transparent; width: 50px; height: 35px; cursor: pointer;' ></input><input type='button' onclick='removeEdit()' style='background: url(../images/detail_02.jpg) no-repeat scroll 0% 0% transparent; margin: 10px 0px 0px 7px; width: 50px; height: 35px; cursor: pointer;'></input></div></form>");
			 }
			var oldContent = $("#reply"+lastReplyId).text();
			$("#replyContent").val(oldContent);
			$("#editReplyId").val(lastReplyId);
		}else{
			$("#"+addArea).hide();
			 var len = $("#editReply"+replyId).text().length;   
			 if(len>0){
				 $("#editReply"+replyIds).show();
			 }else{
				$("#editReply"+replyId).append("<div class='gb12'>我来回答：</div><form action='${pageContext.request.contextPath}/p/question/addReply.html' onsubmit='return checkReplyLength();' method='post'><input type='hidden' id='addProbingId' value='' name='reply.probingId' /><input type='hidden' id='addReplyQuestionId' value='' name='reply.questionId' /><textarea style='overflow: auto' class='texdet'  name='replyContent'  id='replyContent' ></textarea><p align='letf' ><font color='#707070'>&nbsp;回答字数最多不能超过4000字。</font></p><div class='fer'><input type='submit' value='' style='background: url(../images/detail_01.jpg) no-repeat scroll 0% 0% transparent; width: 50px; height: 35px; cursor: pointer;' ></input><input type='button' onclick='removeEdit()' style='background: url(../images/detail_02.jpg) no-repeat scroll 0% 0% transparent; margin: 10px 0px 0px 7px; width: 50px; height: 35px; cursor: pointer;'></input></div></form>");
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
			<img src="${pageContext.request.contextPath}/images/q_01.jpg" width="18" height="18" />
		</div>
		<div class="fon14 fl">${question.subject }</div>
	</div>
	<!--提问者信息开始-->
	<div class="q_info">
		<div class="fl">
			提问者： 
			<span class="g12">
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
	<div class="q_replenish2" id="gallery">
     	<ul>
	     	<c:forEach items="${qImgList}" var="qImgList">
		        <li>
			        <a href="${pageContext.request.contextPath}/question/${qImgList.url }">
			        	<img src="${pageContext.request.contextPath}/question/${qImgList.url }" width="200px;" height="140px"/>
			        </a>
		        </li>    
	    	</c:forEach>
    	</ul>
      </div>
	<!--问题补充开始-->
	<c:if test="${question.extraContent != null}">
		<div class="q_replenish2">
			<div class="fon14 fl">问题补充：</div>
			<div class="con fl">
				<div id="content${question.id }" style="white-space: pre-wrap; word-wrap: break-word; display: inline">${question.extraContent }</div>
			</div>
			<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
		</div>
	</c:if>
	<!--我来帮他解答开始-->
	<c:if test="${userType eq 2  && question.status == 1}">
		<div class="q_answer">
			<div class="gb12">我来帮他解答：</div>
			<form action="${pageContext.request.contextPath}/p/question/addReply.html" onsubmit="return checkReplyLength();" method="post">
				<input type="hidden" name="reply.questionId" value="${questionId }" />
				<textarea style="overflow: auto" class="texdet" name="replyContent" id="replyContent" onKeyUp="count(this,this.form.total,this.form.used,this.form.remain);"
					onfocus="this.style.border= '#69C74C 1px solid '; " onblur="this.style.border='#C6C4C4 1px solid'"></textarea>
				<p align="letf" >
					<font color="#707070">&nbsp;回复字数最多不能超过4000字。</font>
				</p>
				<div class="fr">
					<input type="submit" name="button" id="button" value="" class="bt1" />
				</div>
			</form>
			<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
		</div>
	</c:if>
</div>
<!--  问题结束-->
<!-- 审核中的回复 -->
<c:if test="${auditReply!=null }">
	<div class="q_xgqus">
		<div class="sbar">
			<h2 style="color: red;">审核中的回复</h2>
		</div>
		<!-- 标题开始-->
		<div class="pad6">
			<ul class="q_wtlist">
				<li >
					回复内容：<br/>
					<font color="black" id="reply${auditReply.id }">${auditReply.content }</font>
					<c:if test="${auditReply.status==5 }">
						<div class="q_replenish2" id="replyArea${auditReply.id }">
							<span id="modReply${auditReply.id}"> <span class="cc2"><a href="javascript:void(0)"
									onclick="showEdit3('edit','replyArea${auditReply.id }','${auditReply.id}','${auditReply.id }');">修改回答</a> </span>
								<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
							</span>
						</div>
					</c:if>
				</li>
				<div class="q_answer"  id="editReply${auditReply.id}"  style="padding-left: 0px;"></div>
				<li>
					审核状态：<br/>
					<c:if test="${auditReply.status==4 }">
						<font color="blue">审核中...</font>
					</c:if>
					<c:if test="${auditReply.status==5 }">
						<font color="red">审核未通过...</font>
					</c:if>
				</li>
			</ul>
		</div>
	</div>
</c:if>
<!-- 审核中的回复结束 -->
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
												<c:if test="${probing3.replyId eq reply2probing2.id }">
													<div class="q_replenish2">
														<div class="bfon14 fl">追问：</div>
														<div class="con fl">
															<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing3.content }</div>
														</div>
														<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
													</div>
													<c:forEach items="${replyList}" var="reply2probing3">
														<c:if test="${reply2probing3.reply.id eq probing3.id }">
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
					<div id="reply${reply.id }" style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply.content}</div>
				</div>
				<c:choose>
					<c:when test="${reply.status eq 2}">
						<c:forEach items="${probingList}" var="probing">
							<c:if test="${probing.reply.id eq reply.id }">
								<div class="q_replenish2">
									<div class="bfon14 fl">追问：</div>
									<div class="con fl">
										<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing.content }</div>
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
														<div id="reply${reply2probing.id }" style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing.content}</div>
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
																		<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing2.content }</div>
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
																						<div id="reply${reply2probing2.id }" style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing2.content}</div>
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
																										<div style="white-space: pre-wrap; word-wrap: break-word; display: inline">${probing3.content }</div>
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
																														<div id="reply${reply2probing3.id }" style="white-space: pre-wrap; word-wrap: break-word; display: inline">${reply2probing3.content}</div>
																													</div>
																													<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>

																												</div>
																												<c:if test="${reply2probing3.user.id eq user.id}">
																													<div class="q_replenish2" id="replyArea${reply2probing3.id }">
																														<span id="modReply${reply2probing3.id}"> <span class="cc2">&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)"
																																onclick="showEdit3('edit','replyArea${reply2probing3.id }','${reply2probing3.id}','${reply.id }');">修改回答</a>
																														</span>
																															<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																														</span>
																													</div>
																												</c:if>
																											</c:if>
																										</c:forEach>
																									</c:when>
																									<c:when test="${probing3.reply.userId eq user.id}">
																										<div class="q_replenish2" id="replyAddArea${probing3.id }"">
																											<span id="addReply${probing3.id}"> <span class="cc">&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)"
																													onclick="showEdit3('add','replyAddArea${probing3.id }','${probing3.id }','${reply.id }');">我来回答</a>
																											</span>
																												<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																											</span>
																										</div>
																									</c:when>
																								</c:choose>
																							</c:if>
																						</c:forEach>
																					</c:when>
																					<c:when test="${reply2probing2.user.id eq user.id}">
																						<div class="q_replenish2" id="replyArea${reply2probing2.id }">
																							<span id="modReply${reply2probing2.id}"> <span class="cc2">&nbsp;&nbsp;<a href="javascript:void(0)"
																									onclick="showEdit3('edit','replyArea${reply2probing2.id }','${reply2probing2.id}','${reply.id }');">修改回答</a>
																							</span>
																								<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																							</span>
																						</div>
																					</c:when>
																				</c:choose>
																			</c:if>
																		</c:forEach>
																	</c:when>
																	<c:when test="${probing2.reply.userId eq user.id}">
																		<div class="q_replenish2" id="replyAddArea${probing2.id }">
																			<span id="addReply${probing2.id}"> <span class="cc">&nbsp;&nbsp;<a href="javascript:void(0)"
																					onclick="showEdit3('add','replyAddArea${probing2.id }','${probing2.id }','${reply.id }');">我来回答</a>
																			</span>
																				<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
																			</span>
																		</div>
																	</c:when>
																</c:choose>
															</c:if>
														</c:forEach>
													</c:when>
													<c:when test="${reply2probing.user.id eq user.id}">
														<div class="q_replenish2" id="replyArea${reply2probing.id }">
															<span id="modReply${reply2probing.id}"> <span class="cc2"><a href="javascript:void(0)"
																	onclick="showEdit3('edit','replyArea${reply2probing.id }','${reply2probing.id}','${reply.id }');">修改回答</a> </span>
																<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
															</span>
														</div>
													</c:when>
												</c:choose>
											</c:if>
										</c:forEach>
									</c:when>
									<c:when test="${probing.reply.userId eq user.id}">
										<div class="q_replenish2" id="replyAddArea${probing.id }">
											<span id="addReply${probing.id}"> <span class="cc"><a href="javascript:void(0)"
													onclick="showEdit3('add','replyAddArea${probing.id }','${probing.id }','${reply.id }');">我来回答</a> </span>
												<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
											</span>
										</div>
									</c:when>
								</c:choose>
							</c:if>
						</c:forEach>
					</c:when>
					<c:when test="${reply.user.id eq user.id}">
						<div class="q_replenish2" id="replyArea${reply.id }">
							<span id="modReply${reply.id}"> <span class="cc2"><a href="javascript:void(0)"
									onclick="showEdit3('edit','replyArea${reply.id }','${reply.id}','${reply.id }');">修改回答</a> </span>
								<div style="font: 0px/0px sans-serif; clear: both; display: block"></div>
							</span>
						</div>
					</c:when>
				</c:choose>
				<!-- 修改回答 -->
				<div class="q_answer"  id="editReply${reply.id}" ></div>
				<!-- 修改回答 -->
				<%-- <!-- 评论开始 -->
				<div id="${reply.id}commenturl" style="margin-left: 600px; ">
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
				</div>
				<!-- 评论结束 --> --%>
				<div class="q_line"></div>
			</c:if>
		</c:forEach>
	</div>
</c:if>
