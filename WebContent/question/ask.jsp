<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="wd" uri="http://wd.ah3nong.com/tags"%>
<%@ taglib uri="http://ckfinder.com" prefix="ckfinder"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
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
<title><s:property value='#attr.sitename.content'/>-<s:property value='#attr.title.content'/></title>
<link href="${pageContext.request.contextPath}/css/ask.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/wdhome.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/tip_jq.js" type="text/javascript"></script>
</head>
<body>
	<!--顶部开始-->
	<jsp:include page="../top.jsp" />
	<input type="hidden" id="flag" />
	<!--内容区开始-->
	<div class="ask_nav">
		<a href="index.html">首页</a> > <a href="${pageContext.request.contextPath}/question/new.html">我要提问</a>
	</div>
	<div class="ask_area">
		<div class="ask_form fl">
			<form id="myform" action="${pageContext.request.contextPath}/p/question/add.html" method="post" enctype="multipart/form-data" onsubmit=" return checkForm();">
				<table width="640" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="70px">&nbsp;</td>
						<td height="36" align="left" valign="top"><img src="../images/ask_02.jpg" width="269" height="30" /></td>
					</tr>
					<tr>
						<td height="42" valign="top" class="fon14">&nbsp;&nbsp;标题：</td>
						<td align="left" valign="top">
							<input type="text" name="question.subject" id="subject" class="textr1" onfocus="this.style.border= '#69C74C 1px solid '; "
							onblur="this.style.border='#C6C4C4 1px solid'" value="" style="width: 450px;"/>&nbsp;&nbsp;
							<input type="checkbox" id="anonymous" style="vertical-align:middle"/><label style="vertical-align:middle">&nbsp;匿名&nbsp;</label>
							<input type="hidden" name="question.anonymous" id="questionAnonymous" value="0"/>
						</td>
					</tr>
					<tr>
						<td height="94" valign="top" class="fon14">&nbsp;&nbsp;描述：</td>
						<td align="left" valign="top">
							<textarea id="content" name="question.content" class="texdet" onblur="this.style.border='#C6C4C4 1px solid'" onfocus="this.style.border= '#69C74C 1px solid '; " style="border: 1px solid rgb(198, 196, 196);height: 120px;"></textarea>
						</td>
					</tr>
					<tr>
						<td height="42" valign="top" class="fon14"></td>
						<td align="left" valign="top" class="g12 pad4">
							<div style="float: left;" id="domainDiv">
							<c:choose>
								<c:when test="${not empty expertDomains}">
									<c:forEach items="${expertDomains}" var="domains">
										<input onclick="putValue('${domains.domainId }');" id="domain${domains.domainId }" type="radio" name="domains" value="${domains.domainId }" />&nbsp;${domains.name }&nbsp;
										</c:forEach>
								</c:when>
								<c:otherwise>
									<a class="popbox-link" id="selectClick" href="javascript:void(0);">选择分类&nbsp;&gt;</a>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${not empty domain}">
									<span id="domainName2">${domain.name }</span> <input type="hidden" name="question.domainId" id="domainId2" value="${domain.id }"/>
								</c:when>
								<c:otherwise>
									<span id="domainName2"></span> <input type="hidden" name="question.domainId" id="domainId2" />
								</c:otherwise>
							</c:choose>
							</div>
							<p id="nowing" style="float: left;">
								<c:if test="${not empty expert}">
									&nbsp;&nbsp;正在向<span class='g12'><b><a href='javascript:void(0);' onclick='viewExpert(this.id);'>${expert.fullName}</a></b></span>专家咨询.
								</c:if>
							</p>
						</td>
					</tr>
					<tr>
						<td height="42" valign="top" class="fon14"></td>
						<td align="left" valign="top" style="color: #61A707;">
							图片：
							<input type="file" name="questionImgs" style="width: 160px;height: 23px;" id="questionImg1" class="textr1" onfocus="this.style.border= '#69C74C 1px solid '; " onblur="this.style.border='#C6C4C4 1px solid'" /> 
						</td> 
					</tr>
					<c:if test="${empty expert}">
						<tr style="display: none;">
							<td  valign="top" class="fon14">专家选择：</td>
							<td align="left" valign="top" class="g12 pad4"><a class="popbox-link1" id="selectClick1" href="javascript:void(0);" >选择专家>></a></td>
						</tr>
					</c:if>
					<c:choose>
						<c:when test="${empty expert}">
							<tr id="expertInfo" style="display: none;">
						</c:when>
						<c:otherwise>
							<tr id="expertInfo" style="display: none;">
						</c:otherwise>
					</c:choose>
					<td valign="top" class="fon14"><c:choose>
							<c:when test="${empty expert}">
								&nbsp;
							</c:when>
							<c:otherwise>
								专家选择：
							</c:otherwise>
						</c:choose></td>
					<td align="left" valign="top" class="pad4">
					<!-- 选择专家ID -->
					<input type="hidden" id="hiddenExpertInfoId" name="questionExpert.expertId" value="${expert.id}" />
						</td>
					</tr>
					<tr>
						<td height="80" align="right">&nbsp;</td>
						<td align="left"><input style="cursor: pointer" type="submit" name="button" id="button" value="" class="bt1" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="ask_info fl">
			<table width="260" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="7" style="background: url(${pageContext.request.contextPath}/images/ask_04.jpg) no-repeat 0 0">&nbsp;</td>
					<td class="cont">
						<p>
							<span class="g12"><b>完整描述才能优先得到专家回复</b> </span>
						</p>
						<p>完整描述：如“羔羊后腿不能站立是什么病，应该怎么治疗？”</p>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<!--内容区结束-->
	<div class="clear"></div>
	<!--底部开始-->
	<jsp:include page="../bottom.jsp" />
	<!--底部结束-->
	<div style="height: 800px; display: none;" id="screen"></div>
	<div style="position: absolute; top: 0px; left: 410px; display: none;" class="popbox" id="selectItem">
		<h2>
			选择 <a href="javascript:void(0);" onclick="makeSure();" style="right: 40px;">确定</a> <a class="close-btn" href="javascript:void(0);">关闭</a>
		</h2>
		<div class="mainlist">
			<jsp:include page="selectDomain.jsp" />
			<jsp:include page="selectExpert.jsp" />
		</div>
	</div>
	<script type="text/javascript">
	
	//点击发送请求获得关键字(即问题标签)
		$("#createTag").click(
			function(){
				var url = "${pageContext.request.contextPath}/createQuestionKeyword.action";
				$.post(url,
						{questionSubject:document.getElementById("subject").value},
						function(d){
							document.getElementById("questionKeywords").value = d.data.questionKeywords;
						}
				);
			}
		);
	
		$(document).ready(function() {
			//选择分类
			$('.close-btn').click(function() {
				$('.popbox').fadeOut(function() {
					$('#screen').hide();
				});
				return false;
			});
			$('.popbox-link').click(function() {
				$("#flag").val("DOMAIN");
				$("#selectExperts").hide();
				$("#selectPanel").show();
				$("#selectPanelChild").show();
				var h = $(document).height();
				$('#screen').css({
					'height' : h
				});
				$('#screen').show();
				$('.popbox').center();
				$('.popbox').fadeIn();
				return false;
			});
			$('.popbox-link1').click(function() {
				$("#flag").val("EXPERT");
				$("#selectPanel").hide();
				$("#selectExperts").show();
				$("#selectPanelChild").hide();
				var h = $(document).height();
				$('#screen').css({
					'height' : h
				});
				$('#screen').show();
				$('.popbox').center();
				$('.popbox').fadeIn();
				return false;
			});

		});

		function addNewFilePane() {
			var divs = $("#uploadImgs").find("div");
			if (divs.length < 3) {
				$("#uploadImgs").append("<div><input type='file' name='questionImgs' size='40' id='questionImg1'class='texpic fl'/></div>");
			} else {
				alert("每个问题最多可以上传三张图片.");
			}
		}
	
		function openSelect(state) { //选择层关闭打开控制
			if (state == 1) {
				document.getElementById("chooseExpert").innerHTML = "";
				document.getElementById('selectItem').style.display = "block";
				document.getElementById('selectItem').style.left = "20%";
				document.getElementById('selectItem').style.top = "15%";
			} else {
				document.getElementById('selectItem').style.display = "none";
			}
		}

		function makeSure() {
			var flag = $("#flag").val();
			if (flag == "DOMAIN") {
				var selectedId = document.getElementById("domainName").value;
				if (selectedId) {
					$("#domainDiv").css("display", "inline");
					$("#domainName2").attr("innerHTML", $("#domainName").val());
					$("#domainId2").val($("#domainId").val());
					//alert($("#domainId2").val());

					var URL = "findExpert.action?id=" + $("#domainId2").val();
					$.post(URL, function(data) {
						$("#chooseExpert").attr("innerHTML", "");
						//将expertid信息清空
						document.getElementById("hiddenExpertInfoId").value = 0;
						if (data!=null && data.length > 0) {
							var expertName;
							//如果重新选择分类 就先清空之前选择的专家
							$("#nowing").attr("outerHTML", "<p id='nowing'></p>");
							var names ="<select id='chooseExpert' style='width:200px;' name='chooseExpert'>";
							for (i = 0; i < data.length; i++) {
								expertName = data[i].fullName;
								names = names + "<option value='"+data[i].id+"'>  " + expertName + "&nbsp;&nbsp;</option>";
							}
							names = names + "</select>";
							$("#chooseExpert").attr("outerHTML", names);
						} else {
							$("#nowing").attr("outerHTML", "<p id='nowing'></p>");
							$("#chooseExpert").attr("outerHTML", "<div id='chooseExpert' name='chooseExpert'>该分类暂时没有专家...</div>	");
						}
						$('.popbox').fadeOut(function() {
							$('#screen').hide();
						});
						return false;

					}, 'json');

					openSelect(0);
				} else {
					alert("请先选择问题的分类.");
				}
				//如果是专家
			} else {
				if ($("#chooseExpert").val()) {
					$("#hiddenExpertInfoId").val($("#chooseExpert").val());
					findExpertDomain($("#chooseExpert").val());
				}
				$('.popbox').fadeOut(function() {
					$('#screen').hide();
				});
			}
		}

		//获得专家的擅长描述
		function findExpertDomain(id) {
			$.ajax({
				type : 'POST',
				url : "findExpertInfo.action",
				data : {
					"id" : id
				},
				success : function(data) {
					var info = data;
					$("#expertInfo").show();
					$("#nowing").attr("innerHTML", "正在向<span class='g12'><b><a href='javascript:void(0);' onclick='viewExpert(this.id);'>" + info.fullName + "</a> </b> </span> 专家咨询");
					$("#expertSummary").attr("innerHTML", info.summary);
				},
				dataType : "json"
			});
		}

		//表单验证
		function checkForm() {
			// 验证标题
			var subject = $("#subject").val().replace(/(^\s*)|(\s*$)/g, "").length;
			if (subject >= 100) {
				alert("您输入的标题过长...");
				return false;
			}
			if (subject == 0) {
				alert("请输入标题...");
				return false;
			}
			
			//是否匿名取值 questionAnonymous 
			if($("#anonymous").attr("checked")){
				$("#questionAnonymous").val(1);
			}else{
				$("#questionAnonymous").val(0);
			}
			
			// 验证描述
			var content = $("#content").val().replace(/(^\s*)|(\s*$)/g, "").length;
			if (content >= 1000) {
				alert("您输入的描述内容过长...");
				return false;
			}
			if (content == 0) {
				alert("请输入问题描述...");
				return false;
			}
			
			//验证有没有选择分类
			if ($("#domainId2").val() == null || $("#domainId2").val() == "" || $("#domainId2").val() == undefined) {
				alert("请选择问题分类...");
				return false;
			}
			
			//验证图片格式 
			var checkImg = '0';
			$('*[name="questionImgs"]').each(function(){
				var file = $(this).val();
				if(file!=null&&file!=''){
					if (!/.(gif|jpg|png|bmp)$/.test(file)) {
						alert("上传图片类型必须是gif,jpg,png,bmp中的一种...");
						checkImg = '1';
					}
				}
			});
			if(checkImg=='1'){
				return false;
			}
			
			return true;
		}

		function putValue(id) {
			$("#domainId2").val(id);
		}
	</script>
</body>
</html>

