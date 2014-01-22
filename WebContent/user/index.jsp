<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="wd" uri="http://wd.ah3nong.com/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://ask.ah3nong.com/tags/security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<wd:sysData var="sitename" name="sitename" />
<wd:sysData var="title" name="title" />
<wd:sysData var="keywords" name="keywords" />
<wd:sysData var="description" name="description" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="<s:property value='#attr.keywords.content'/>" />
<meta name="description" content="<s:property value='#attr.description.content'/>" />
<title>${viewUser.screenName}_<s:property value='#attr.sitename.content'/>-<s:property value='#attr.title.content'/></title>
<link href="${pageContext.request.contextPath}/css/exp_view.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/wdhome.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/checklogin.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/tip_jq.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.lightbox-0.5.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.lightbox-0.5.css" media="screen" />
</head>
<body>
	<script type="text/javascript">
	$(document).ready(function(){
		var s = "${s}";
		$("#"+s).addClass("on");
	});
	
	$(function() {
	    $('#gallery a ').lightBox();
	});
	</script>
	<%@include file="../top.jsp"%>
	<!--内容区开始-->
	<div class="ask_nav">
		<a href="${pageContext.request.contextPath}/index.html">首页</a>&gt;
		<a href="${pageContext.request.contextPath}/user/${viewUser.id}.html">${viewUser.screenName}</a>
	</div>
	<div class="area">
		<!--左侧开始-->
		<div class="q_leftarea">
			<!--  内容开始-->
			<div class="q_quest01">
				<!--标题开始-->
				<c:choose>
					<c:when test="${viewUser.expert==1}">
						<div class="exp_tit">
						农业专家：
						${viewUser.expertName}
					</c:when>
					<c:otherwise>
						<div class="user_tit">
						用&nbsp;&nbsp;户：
						${viewUser.screenName}
					</c:otherwise>
				</c:choose>
				</div>
				<!--用户信息开始-->
				<div class="exp_Content">
					<div class="exppic fl">
						<c:choose>
							<c:when test="${viewUser.expert==1}">
								<s:if test="expert.photoUrl != null">
									<img src="${pageContext.request.contextPath}/question/${expert.photoUrl}" class="imgboder" width="150" height="190" />
								</s:if>
								<s:else>
									<img src="${pageContext.request.contextPath}/images/150X190.jpg" class="imgboder" width="150" height="190" />
								</s:else>
							</c:when>
							<c:otherwise>
								<img src="http://${applicationScope.passportUrl}/avatar/${viewUser.username}/150" class="imgboder" width="150" height="150" />
								<%-- <s:if test="viewUser.avatar != null">
									<img src="${pageContext.request.contextPath}/${viewUser.avatar}" class="imgboder" width="150" height="150" />
								</s:if>
								<s:else>
									<img src="${pageContext.request.contextPath}/images/150X190.jpg" class="imgboder" width="150" height="150" />
								</s:else> --%>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="exptxt fl">
						<p>
							<c:choose>
								<c:when test="${viewUser.expert==1}">
									<b>擅&nbsp;&nbsp;长：</b>
									${expert.summary}
									<br/>
									<b>回答数：</b>
									<span class="g12">${replyCount}</span>&nbsp;个
									<br /> 
									<b>采纳率：</b>
									<span class="g12">${acceptedPrecent}</span> 
									<br/>
									<b>领&nbsp;&nbsp;域：</b>
									<c:if test="${expert.domains== null || fn:length(expert.domains) == 0}">
										暂无
									</c:if>
									<c:if test="${fn:length(expert.domains) > 0}">
										<s:iterator value="expert.domains" id="d" status="st">
											<span class="g12"><a href="${pageContext.request.contextPath}/domain/<s:property value="#d.id" />.html" target="_blank"><s:property value="#d.name" /></a></span>
										</s:iterator>
									</c:if>
									<br /> <a href="${pageContext.request.contextPath}/question/new_${viewUser.id}.html" target="_blank"><img src="${pageContext.request.contextPath}/images/zj_05.jpg" alt="咨询专家"
										width="113" height="35" /></a>
								</c:when>
								<c:otherwise>
									<b>提问数：</b>
									<span class="g12">${askCount}</span>&nbsp;个
									<br/>
									<b>回答数：</b>
									<span class="g12">${replyCount}</span>&nbsp;个
									<br /> 
									<b>采纳率：</b>
									<span class="g12">${acceptedPrecent}</span> 
									<br/>
									<b>结帖率：</b>
									<span class="g12">${sovedPercent}</span>
									<br /> 
									<b>最近关注的领域：</b>
									<c:if test="${userDomains== null || fn:length(userDomains) == 0}">
										暂无
									</c:if>
									<c:if test="${fn:length(userDomains) > 0}">
										<s:iterator value="userDomains" id="d" status="st">
											<span class="g12"><a  target="_blank" href="${pageContext.request.contextPath}/domain/<s:property value="#d.id"/>.html"><s:property value="#d.name" /></a></span>
										</s:iterator>
									</c:if>
								</c:otherwise>
							</c:choose>
						</p>
					</div>
				</div>
				<c:if test="${viewUser.expert==1}">
					<!--专家简介开始-->
					<div class="exp_jj">专家简介</div>
					<div class="exp_jj_con">
						<c:if test="${fn:length(expert.resume)>232}" >
							<s:property value="expert.resume.substring(0,230)" />...
						</c:if>
						<c:if test="${fn:length(expert.resume)<=232 }" >
							<s:property value="expert.resume" />
						</c:if>
					</div>
				</c:if>
			</div>
			<!--  相关内容开始-->
			<div class="q_xgqus">
				<div class="sbar">
					<ul class="c_nav">
						<li id='q'><a href="${pageContext.request.contextPath}/user/q/${viewUser.id}.html">他的回答</a></li>
						<li id='a'><a href="${pageContext.request.contextPath}/user/a/${viewUser.id}.html">他的提问</a></li>
						<%-- <c:if test="${viewUser.expert==1}">
							<li id='pic'><a href="${pageContext.request.contextPath}/user/pic/${viewUser.id}.html">他的图片</a></li>
							<li id='movie'><a href="${pageContext.request.contextPath}/user/movie/${viewUser.id}.html">他的视频</a></li>
						</c:if> --%>
					</ul>
				</div>
				<div>
					<!-- 问题列表开始 -->
					<c:if test="${s == null || s=='' || s=='a' || s== 'q' }" >
						<div class="pad6">
							<ul class="q_wtlist">
								<s:iterator value="questionByUser" id="q" status="st">
									<li>
										<s:if test="#q.status == 1">
											<span class="rlist fr">未解决</span>
										</s:if> <s:else>
											<span class="rlist fr">已解决</span>
										</s:else> 
										<a href="${pageContext.request.contextPath}/question/<s:property value="#q.questionId" />.html" target="_blank"><s:property value="#q.subject" /></a> 
										<span class="titfl">[<a href="#"><s:property value="#q.name" /></a>]</span>
									</li>
								</s:iterator>
							</ul>
						</div>
						<div class="Paging">
						<if test="${s==null }">
							<input type="hidden" name="s" value="q" />
						</if>
							共有${count }条信息 页次: ${currentPage }/${countSize}
							<s:if test="currentPage != 1">
							      【<a href="${pageContext.request.contextPath}/user/${s}/${viewUser.id}_1.html">第一页</a>】
							      【<a href="${pageContext.request.contextPath}/user/${s}/${viewUser.id}_${currentPage-1 }.html">上一页</a>】 
						    </s:if>
							<s:else>
								<span style="color: #CCCCCC">【第一页】</span>
								<span style="color: #CCCCCC">【上一页】</span>
							</s:else>
							<s:if test="currentPage < countSize">
							      【<a href="${pageContext.request.contextPath}/user/${s}/${viewUser.id}_${currentPage+1 }.html">下一页</a>】 
							      【<a href="${pageContext.request.contextPath}/user/${s}/${viewUser.id}_${countSize}.html">最末页</a>】
						     </s:if>
							<s:else>
								<span style="color: #CCCCCC">【下一页】</span>
								<span style="color: #CCCCCC">【最末页】</span>
							</s:else>
						</div>
					</c:if>
					<!-- 问题列表结束 -->
					<%-- <!-- 图片  -->
					<c:if test="${s=='pic' }">
						<div style="width: 700px;" class="expimg_list" id="gallery">
							<ul>
								<s:iterator value="attachments" id="attach">
									<s:if test="#attach.type==3">
										<li title="点击查看大图">
											<a href="${pageContext.request.contextPath}/file/expert/attachment/<s:property value='#attach.path' />">
												<img src="${pageContext.request.contextPath}/file/expert/attachment/<s:property value='#attach.path' />" width="200px" height="128px;"/>
												<div style="text-align:center;margin-top: 10px;font-size: 12px;font-weight: bold;color: #006940;">
													<s:property value='#attach.name' />
												</div>
											</a>
										</li>
									</s:if>
								</s:iterator>
							</ul>
						</div>
						<br style="clear:both;"/>
					</c:if>
					<!-- 图片结束 -->
					<!-- 视频 -->
					<c:if test="${s=='movie' }">
						<s:iterator value="attachments" id="attachmt">
							<s:if test="#attachmt.type==2">
								<div align="center" style="margin-top:20px; ">
									<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" height="120" width="190"> 
										<param name="movie" value="${pageContext.request.contextPath}/vcastr22.swf?vcastr_file=${pageContext.request.contextPath}/file/expert/attachment/<s:property value='#attachmt.path' />"> 
										<param name="quality" value="high"> 
										<param name="allowFullScreen" value="true" /> 
										<embed src="${pageContext.request.contextPath}/vcastr22.swf?vcastr_file=${pageContext.request.contextPath}/file/expert/attachment/<s:property value='#attachmt.path' />" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="500" height="300"> 
										</embed> 
									</object> 
									<div style="margin: 10px;font-size: 14px;font-weight: bold;color: #006940;">
										<s:property value="#attachmt.name" />
									</div>
								</div>
							</s:if>
						</s:iterator>
					</c:if>
					<!-- 视频结束 --> --%>
				</div>
			</div>
			<!--  相关内容结束-->
			<c:if test="${viewUser.expert==1}">
				<!-- 图片专家 -->
				<div class="q_xgqus" >
					<div class="sbar">
						<h2>更多专家</h2>
					</div>
					<div style="height:150px;">
						<ul>
							<wd:expertList var="recommendExpert" count="6" orderBy="id desc "/>
							<s:iterator value="#attr.recommendExpert" id="rexpert">
								<li style="float: left;width: 92px;text-align: left;height: 140px;margin: 12px;display: inline;position:relative; ">
									<a href="${pageContext.request.contextPath}/user/${rexpert.id}.html" title="${rexpert.username}" target="_blank">
										<s:if test="#rexpert.photoUrl != null">
											<img src="${pageContext.request.contextPath}/question/<s:property value='#rexpert.photoUrl'/>" class="imgboder" width="82" height="106" />
										</s:if>
										<s:else>
											<img src="${pageContext.request.contextPath}/question/images/70X91.jpg" class="imgboder" width="82" height="106" />
										</s:else>
										<div style="width: 90px;margin-top: 5px;text-align: center;font-size: 12px;font-weight: bold;color: #61A707;">
											<s:property value='#rexpert.fullName'/>
										</div>
									</a>
								</li>
							</s:iterator>
						</ul>
					</div>
				</div>
				<!-- 图片专家结束 -->
			</c:if>
		</div>
		<!--左侧结束-->
		<!--右侧开始-->
		<div class="c_rightarea">
			<%-- <!-- 按采纳数推荐专家开始-->
			<div class="cr_tjzj" style="margin-top: 0px;">
				<div class="sbar">
					<h2>
						专家采纳数排行
					</h2>
					<span class="more"> </span>
				</div>
				<div class="olist pad6">
					<ul class="olistn">
						<s:iterator value="expertAcceptedCountMap" id="expert" status="st">
							<s:if test="#expert.acceptNum gt 0" >
								<li class="no<s:property value='#st.index+1'/>">
									<span class="rlist fr"><s:property value="#expert.acceptNum" />条回答被采纳</span>
									<a href="${pageContext.request.contextPath}/user/<s:property value="#expert.id" />.html" target="_blank"><s:property value="#expert.fullName" /></a>
								</li>
							</s:if>
						</s:iterator>
					</ul>
				</div>
			</div>
			<!-- 按采纳数推荐专家结束-->
			<!-- 按回复数推荐专家开始-->
			<div class="cr_tjzj" >
				<div class="sbar">
					<h2>
						专家回复数排行
					</h2>
					<span class="more"> </span>
				</div>
				<div class="olist pad6">
					<ul class="olistn">
						<s:iterator value="expertReplyNumMap" id="expertr" status="st">
							<s:if test="#expertr.count gt 0" >
								<li class="no<s:property value='#st.index+1'/>">
									<span class="rlist fr"><s:property value="#expertr.count" />条回答</span>
									<a href="${pageContext.request.contextPath}/user/<s:property value="#expertr.id" />.html" target="_blank"><s:property value="#expertr.fullName" /></a>
								</li>
							</s:if>
						</s:iterator>
					</ul>
				</div>
			</div>
			<!-- 按回复数推荐专家结束--> --%>
			<!-- 待解决开始-->
			<div class="cr_tjzj" style="margin-top: 0px;">
				<div class="sbar">
					<h2>
						<a href="${pageContext.request.contextPath}/agriculture/index.html" title="待解决问题">等您回答</a>
					</h2>
					<span class="more"><a href="${pageContext.request.contextPath}/agriculture/index.html">更多>></a> </span>
				</div>
				<div class="olist pad6">
					<ul class="olistn">
					<wd:questionList var="latestOpenedQuestions" count="8" status="1" orderBy="created_time desc" />
						<s:iterator value="#attr.latestOpenedQuestions" id="questionl">
							<li><c:if test="${fn:length(questionl.subject)>20}">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#questionl.id"/>.html" target="_blank"><s:property value="#questionl.subject.substring(0,18)" />...</a>
								</c:if> 
								<c:if test="${fn:length(questionl.subject)<= 20 }">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#questionl.id"/>.html" target="_blank"><s:property value="#questionl.subject" /></a>
								</c:if> 
							</li>
						</s:iterator>
					</ul>
				</div>
			</div>
			<!-- 待解决结束-->
			<!-- 已解决问题开始-->
			<div class="cr_tjzj">
				<div class="sbar">
					<h2>
						<a href="${pageContext.request.contextPath}/agriculture/resolved/0.html" title="已解决问题">已解决问题</a>
					</h2>
					<span class="more"><a href="${pageContext.request.contextPath}/agriculture/resolved/0.html">更多>></a> </span>
				</div>
				<div class="olist pad6">
					<ul class="olistn">
					<wd:questionList var="latestSolvedQuestions" count="8" status="2" orderBy="solved_time desc" />
						<s:iterator value="#attr.latestSolvedQuestions" id="questionv">
							<li><c:if test="${fn:length(questionv.subject)>20}">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#questionv.id"/>.html" target="_blank"><s:property value="#questionv.subject.substring(0,18)" />...</a>
								</c:if> 
								<c:if test="${fn:length(questionv.subject)<= 20 }">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#questionv.id"/>.html" target="_blank"><s:property value="#questionv.subject" /></a>
								</c:if> 
							</li>
						</s:iterator>
					</ul>
				</div>
			</div>
			<!-- 已解决问题结束-->
		</div>
	</div>
	<!--内容区结束-->
	<div class="clear"></div>
	<%@include file="../bottom.jsp"%>
	<!--底部结束-->
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/common/checkLogin/checkLogin.jsp"></script> --%>
</body>
</html>
