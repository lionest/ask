<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec" uri="http://ask.ah3nong.com/tags/security" %>
<%@ taglib prefix="wd" uri="http://wd.ah3nong.com/tags"%>

<div class="c_denglu">
	<div class="sbar">
		<h2>
			<a href="${pageContext.request.contextPath}/i/index.html" target="_blank">${user.username}</a>
		</h2>
		<span class="more_u"><a href="${pageContext.request.contextPath}/i/index.html" target="_blank">我的问答</a> | <a
			href="${applicationScope.loginOutUrl }">退出>></a> </span>
	</div>
	<table width="260" border="0" cellspacing="0" cellpadding="0"
		style="margin: 8px 0 0 8px; line-height: 22px;">
		<tr>
			<td width="90px" style="line-height: 12px;">
				<a title="点击此处修改头像"  href="${applicationScope.editUserAvatarUrl }" target="_blank">
				<%-- <c:choose>
					<c:when test="${user.avatar==null||user.avatar==''}">
						<c:choose>
							<c:when test="${expertId!=null}">
							<c:choose>
								<c:when test="${null!=user.sex&&user.sex=='女'}">
									<img src="${pageContext.request.contextPath}/images/tx.jpg" class="imgboder" width="73" height="60" />
								</c:when>
								<c:otherwise>
									<img src="${pageContext.request.contextPath}/images/tx_nan.jpg" class="imgboder" width="73"
										height="60" />
								</c:otherwise>
							</c:choose>
							</c:when>
							<c:otherwise>
							<c:choose>
								<c:when test="${null!=user.sex&&user.sex== 1 }">
									<img src="" class="imgboder" width="73" height="60" />
								</c:when>
								<c:otherwise>
									<img src="${pageContext.request.contextPath}/images/tx_nan.jpg" class="imgboder" width="73"
										height="60" />
								</c:otherwise>
							</c:choose>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img src="${pageContext.request.contextPath}/${user.avatar}" class="imgboder" width="73" height="60" />
					</c:otherwise>
				</c:choose> --%>
				<img src="http://${applicationScope.passportUrl}/avatar/${user.username}/60" class="imgboder" width="60" height="60" />
				</a>
			</td>
			<td align="left">
				<c:if test="${user.expert != 1 }">
					<wd:userLevel var="userLevel" userId="${user.id}" />
					<a href="${pageContext.request.contextPath}/user/levelhelper.jsp" target="_blank" >
						<s:property value="#attr.userLevel" />&nbsp;(经验值:${user.experience })
					</a>
					<br />
				</c:if>
				<div style="float: left; width: 90px;">
					<a href="${pageContext.request.contextPath}/i/q.html" target="_blank">提问数：${askCount}个</a>
				</div>
				<div style="float: left;">
					<a href="${pageContext.request.contextPath}/i/2_q.html" target="_blank">结贴率：${sovedPercent}</a>
				</div>
				<br />
				<div  style="float: left; width: 90px;">
					<a href="${pageContext.request.contextPath}/i/r.html" target="_blank">回答数：${replyCount}个</a>
				</div>
				<div style="float: left;">
					<a href="${pageContext.request.contextPath}/i/2_r.html" target="_blank" >采纳率：${acceptedPrecent}</a>
				</div>
			</td>
		</tr>
	</table>
	<div class="u_list">
		<ul>
			<li>
				<a id="myq" href="${pageContext.request.contextPath}/i/q.html" target="_blank">我的提问</a>
			</li>
			<li>
				<a id="myr" href="${pageContext.request.contextPath}/i/r.html" target="_blank">我的回答</a>
			</li>
			<%-- <li>
						<a id="myc" href="${pageContext.request.contextPath}/i/c.html" target="_blank">我的评论</a>
			</li> --%>
			<li>
				<a id="myn" href="${pageContext.request.contextPath}/i/reply.html" target="_blank">最新动态</a>
			</li>
			<c:if test="${user.expert eq 1 }">
				<li>
					<a href="${pageContext.request.contextPath}/i/e.html" target="_blank">向我提问</a>
				</li>
			</c:if>
		</ul>
	</div>
	<!-- 上传头像 -->
	<div style="position: absolute;  display: none;height:180px;width:290px;border: 1px solid #006940 ;background-color: #F3FCF9;" id="addavatar" >
		<h2>
			上传头像
		</h2>
		<div style="width: 200px;margin-top: 40px;margin-left: 50px;">
			<form action="${pageContext.request.contextPath}/addUserAvatar.action" method="post" enctype="multipart/form-data" onsubmit="return checkAvatar();">
				<input type="file" id="useravatar" name="useravatar" size="40" id="useravatar"  />
				<br/><br/>
				用户可以上传1M之内的JPG,GIF,PNG图片 来作为自己的头像。
				<br/><br/>
				<input type="submit"  style="background:url(${pageContext.request.contextPath}/images/detail_01.jpg); width:48px;height:35px;" value="" />
				&nbsp;&nbsp;
				<input type="button"  onclick="closeavatar()" style="background:url(${pageContext.request.contextPath}/images/detail_02.jpg); width:48px;height:35px;" value="" />
			</form>
		</div>
	</div>
</div>