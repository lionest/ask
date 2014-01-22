<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="wd" uri="http://wd.ah3nong.com/tags"%>
<%@ taglib prefix="sec" uri="http://ask.ah3nong.com/tags/security"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<wd:sysData var="sitename" name="sitename" />
<wd:sysData var="title" name="title" />
<wd:sysData var="keywords" name="keywords" />
<wd:sysData var="description" name="description" />
<meta name="keywords" content="<s:property value='#attr.keywords.content'/>" />
<meta name="description" content="<s:property value='#attr.description.content'/>" />
<title>${user.username}的个人中心_<s:property value='#attr.sitename.content'/>-<s:property value='#attr.title.content'/></title>
<link href="${pageContext.request.contextPath}/css/center.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/logon_2.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/wdhome.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/checklogin.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/tip_jq.js" type="text/javascript"></script>
</head>
<body>
	<script>
		var t1 = '${t1}';
		function giveStyle(){
			//alert(t1);
			if(t1!=null&&t1!=''){
				$('#my'+t1).addClass('u_list_choosed');
			}
		};
		
		function changeavatar(){
			$('#addavatar').fadeIn();
			$('#addavatar').center();
		}
		
		function closeavatar(){
			$('#addavatar').fadeOut();
		}
		
		function checkAvatar() {
			var file = $("#useravatar").val();
		    if(file!=null&&file!=''){
				if (!/.(gif|jpg|png)$/.test(file)) {
					alert("上传图片类型必须是gif,jpg,png中的一种。");
					return false;
				}
			}
			return true; 
		}
	</script>
	<%@include file="top.jsp"%>
	<!--内容区开始-->
	<div class="ask_nav">
		<a href="${pageContext.request.contextPath}/index.html">首页</a> &gt; <a href="${pageContext.request.contextPath}/i/index.html">我的问答</a>
		<c:choose>
			<c:when test="${t1=='q'}">&gt;<a href="${pageContext.request.contextPath}/i/q.html">&nbsp;我的提问</a>
			</c:when>
			<c:when test="${t1=='r'}">&gt;<a href="${pageContext.request.contextPath}/i/r.html">&nbsp;我的回答</a>
			</c:when>
			<c:when test="${t1=='e'}">&gt;<a href="${pageContext.request.contextPath}/i/e.html">&nbsp;向我提问</a>
			</c:when>
			<c:when test="${t1=='c'}">&gt;<a href="${pageContext.request.contextPath}/i/c.html">&nbsp;我的评论</a>
			</c:when>
			<c:otherwise>&nbsp;</c:otherwise>
		</c:choose>
	</div>
	<div class="area">
		<!--左侧开始-->
		<div class="c_rightarea">
			<!-- 登录框开始-->
			<div id="dl">
				<font color="red">Loading……</font>
			</div>
			<!-- 登录框结束-->
			<!-- 最新问题开始-->
			<div class="cr_tjzj">
				<div class="sbar">
					<h2>
						<a href="${pageContext.request.contextPath}/agriculture/index.html" title="最新问题">最新问题</a>
					</h2>
					<span class="more"><a href="${pageContext.request.contextPath}/agriculture/index.html">更多>></a> </span>
				</div>
				<div class="olist pad6">
					<ul class="olistn">
						<wd:questionList var="latestOpenedQuestions" count="8" status="1" orderBy="created_time desc" />
						<s:iterator value="#attr.latestOpenedQuestions" id="question">
							<li><span class="rlist fr"><s:property value="#question.replyNum" />回答</span> <c:if test="${fn:length(question.subject)>16}">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#question.id"/>.html" target="_blank"><s:property value="#question.subject.substring(0,14)" />... </a>
								</c:if> <c:if test="${fn:length(question.subject)<=16}">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#question.id"/>.html" target="_blank"><s:property value="#question.subject" /> </a>
								</c:if></li>
						</s:iterator>
					</ul>
				</div>
			</div>
			<!-- 最新问题结束-->
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
						<s:iterator value="#attr.latestSolvedQuestions" id="question">
							<li><c:if test="${fn:length(question.subject)>19}">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#question.id"/>.html" target="_blank"><s:property value="#question.subject.substring(0,17)" />...</a>
								</c:if> <c:if test="${fn:length(question.subject)<= 19 }">
									<a href="${pageContext.request.contextPath}/question/<s:property value="#question.id"/>.html" target="_blank"><s:property value="#question.subject" /></a>
								</c:if></li>
						</s:iterator>
					</ul>
				</div>
			</div>
			<!-- 已解决问题结束-->
		</div>
		<!--左侧结束-->
		<!--右侧开始-->
		<div class="c_leftarea">
			<!--  所有问题开始-->
			<div class="c_allqus1">
				<div class="sbar">
					<ul class="c_nav">
						<c:if test="${s==null}">
							<li class="on"><a href="${pageContext.request.contextPath}/i/${t1}.html">全部问题</a></li>
							<li><a href="${pageContext.request.contextPath}/i/2_${t1}.html">已解决</a></li>
							<li><a href="${pageContext.request.contextPath}/i/1_${t1}.html">未解决</a></li>
						</c:if>
						<c:if test="${s==2}">
							<li><a href="${pageContext.request.contextPath}/i/${t1}.html">全部问题</a></li>
							<li class="on"><a href="${pageContext.request.contextPath}/i/2_${t1}.html">已解决</a></li>
							<li><a href="${pageContext.request.contextPath}/i/1_${t1}.html">未解决</a></li>
						</c:if>
						<c:if test="${s==1}">
							<li><a href="${pageContext.request.contextPath}/i/${t1}.html">全部问题</a></li>
							<li><a href="${pageContext.request.contextPath}/i/2_${t1}.html">已解决</a></li>
							<li class="on"><a href="${pageContext.request.contextPath}/i/1_${t1}.html">未解决</a></li>
						</c:if>
					</ul>
				</div>
				<!-- 标题开始-->
				<div class="pad6">
					<ul class="c_tit">
						<li>
						<c:choose>
							<c:when test="${t1=='r'}">
								<span class="rlist fr" style="width:60px;">回答时间 </span>
							</c:when>
							<c:otherwise>
								<span class="rlist fr" style="width:60px;">提问时间 </span>
							</c:otherwise>
						</c:choose>
						<c:if test="${t1=='r'||t1=='c'}">
							<span style="float:right;width:75px;text-align:center;" >提问者</span>
						</c:if>
						<c:if test="${t1=='r'&&s==null}">
							<span style="float:right;width:40px;" >&nbsp;采纳 </span>
						</c:if>
						<span style="float:right;width:45px;" >回复数 </span>
						<c:if test="${s==null}">
							<span >是否解决&nbsp;&nbsp;</span>
						</c:if>
						标题
						 <span class="titfl">(共 ${pager.totalRecords } 项)</span></li>
					</ul>
					<c:if test="${pager.totalRecords==0}">
						<c:choose>
							<c:when test="${t1=='q'&&s==null}">
								<p class="center_msg">您还没有任何提问.</p>
							</c:when>
							<c:when test="${t1=='q'&&s!=null}">
								<p class="center_msg">您还没有已解决的提问.</p>
							</c:when>
							<c:when test="${t1=='e'&&s==null}">
								<p class="center_msg">还没人向您提问.</p>
							</c:when>
							<c:when test="${t1=='e'&&s!=null}">
								<p class="center_msg">还没有已解决的向您提问.</p>
							</c:when>
							<c:when test="${t1=='r'&&s==null}">
								<p class="center_msg">您还没有任何回答.</p>
							</c:when>
							<c:when test="${t1=='r'&&s!=null}">
								<p class="center_msg">您还没有已解决的回答.</p>
							</c:when>
							<c:when test="${t1=='c'&&s==null}">
								<p class="center_msg">您还没有任何评论.</p>
							</c:when>
							<c:when test="${t1=='c'&&s!=null}">
								<p class="center_msg">您还没有已解决的问题评论.</p>
							</c:when>
							<c:otherwise>
								<p class="center_msg">请正确选择.</p>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${pager.totalRecords>0}">
						<ul class="c_wtlist">
							<c:forEach items="${pager.pageRecords}" var="Q" varStatus="T">
								<li>
									<span class="rlist fr" style="width:60px;">
										<fmt:formatDate value="${Q.createdTime}" type="both" pattern="yyyy-MM-dd" />
									</span>
									<!-- 提问者名字 -->
									<c:if test="${t1=='r'||t1=='c'}">
										<span style="float:right;width:75px;text-align:center;" >${Q.user.username}</span>
									</c:if>
									<!-- 我的回答-采纳 start--> 
									<c:if test="${t1=='r'&&s==null&&Q.userId!=null}">
										<span style="float:right;width:40px;text-align:center;" >
											<img src="${pageContext.request.contextPath}/images/caina.png" width="20px;" height="20px;" ></img>
										</span>
									</c:if>
									<c:if test="${t1=='r'&&s==null&&Q.userId==null}">
										<span style="float:right;width:40px;text-align:center;">
											<img src="${pageContext.request.contextPath}/images/weicaina.jpg" width="20px;" height="20px;" ></img>
										</span>
									</c:if>
									<!-- 我的回答-采纳 end--> 
									<!-- 回复数start -->
									<span style="float:right;width:45px;text-align:center;" >
										${Q.replyNum }
									</span>
									<!-- 回复数end -->
									<!-- 问题状态start -->
									<c:if test="${s==null}">
											<c:if test="${Q.status==1}">
												<font color="red">[未解决]</font>
											</c:if>
											<c:if test="${Q.status==2}">
												<font color="#61A707">[已解决]</font>
											</c:if>
											<c:if test="${Q.status==99}">
												<font color="black">[已关闭]</font>
											</c:if>
									</c:if>
									<!-- 问题状态 end-->
									<c:if test="${fn:length(Q.subject)>22}">
										<a href="${pageContext.request.contextPath}/question/${Q.id}.html" target="_blank">${fn:substring(Q.subject,0,20)}...</a>
									</c:if> 
									<c:if test="${fn:length(Q.subject)<=22}">
										<a href="${pageContext.request.contextPath}/question/${Q.id}.html" target="_blank">${Q.subject }</a>
									</c:if> <span class="titfl">[${Q.domain.name }]</span>
								</li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
				<c:if test="${pager.totalRecords>0}">
					<div class="Paging">
						共有${pager.totalRecords }条信息 页次: ${pager.currentPage }/${pager.pageCount}
						<c:if test="${s==null}">
							<c:if test="${pager.currentPage !=1}">
      【<a href="${pageContext.request.contextPath}/i/${t1}_1.html">第一页</a>】
      【<a href="${pageContext.request.contextPath}/i/${t1}_${pager.currentPage-1 }.html">上一页</a>】 
     </c:if>
							<c:if test="${pager.currentPage ==1}">
								<span style="color: #CCCCCC">【第一页】</span>
								<span style="color: #CCCCCC">【上一页】</span>
							</c:if>
							<c:if test="${pager.currentPage < pager.pageCount}">
      【<a href="${pageContext.request.contextPath}/i/${t1}_${pager.currentPage+1 }.html">下一页</a>】 
      【<a href="${pageContext.request.contextPath}/i/${t1}_${pager.pageCount}.html">最末页</a>】
      </c:if>
							<c:if test="${pager.currentPage >=pager.pageCount }">
								<span style="color: #CCCCCC">【下一页】</span>
								<span style="color: #CCCCCC">【最末页】</span>
							</c:if>
						</c:if>
						<c:if test="${s!=null}">
							<c:if test="${pager.currentPage!=1 }">
      【<a href="${pageContext.request.contextPath}/i/1_${t1}_1.html">第一页</a>】
      【<a href="${pageContext.request.contextPath}/i/1_${t1}_${pager.currentPage-1 }.html">上一页</a>】 
     </c:if>
							<c:if test="${pager.currentPage==1 }">
								<span style="color: #CCCCCC">【第一页】</span>
								<span style="color: #CCCCCC">【上一页】</span>
							</c:if>
							<c:if test="${pager.currentPage < pager.pageCount}">
      【<a href="${pageContext.request.contextPath}/i/1_${t1}_${pager.currentPage+1 }.html">下一页</a>】 
      【<a href="${pageContext.request.contextPath}/i/1_${t1}_${pager.pageCount}.html">最末页</a>】
      </c:if>
							<c:if test="${pager.currentPage >= pager.pageCount }">
								<span style="color: #CCCCCC">【下一页】</span>
								<span style="color: #CCCCCC">【最末页】</span>
							</c:if>
						</c:if>

					</div>
				</c:if>
			</div>
		</div>
	</div>
	<!--内容区结束-->
	<div class="clear"></div>
	<!--底部开始-->
	<%@include file="bottom.jsp"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/checkLogin/checkLogin.jsp"></script>
	<script type="text/javascript">
		giveStyle();
	</script>
</body>
</html>
