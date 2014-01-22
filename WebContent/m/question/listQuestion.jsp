<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html" language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<link rel="stylesheet" href="../css/mainzone.css" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.7.1.js"> </script>
<title>管理区域</title>
<script>

	function btnlogin()
{
     if(navigator.userAgent.indexOf("MSIE")>0) {
	  
            return ""; 
        }
    if(navigator.userAgent.indexOf("Firefox")>0){
            return "m/question/"; 
        }
    if(navigator.userAgent.indexOf("Opera")>0){
           return "m/question/";
        }
    if(navigator.userAgent.indexOf("Safari")>0) { 
            return "m/question/"; 
        } 
    if(navigator.userAgent.indexOf("Camino")>0){ 
            return "m/question/";
        } 
}


	function ddd(object){
		object.style.display="none";
		document.getElementById("showContent").style.display="block";
	}

var id = 1;
function findAllChecBox(){
  var ids = document.getElementById("ids");
   var r = document.getElementsByName("entityIds");
           if(id==1){
       for(var i=0;i<r.length;i++){
	     r[i].checked=true;
	       id=2;
		 }
	   }else{
	     for(var i=0;i<r.length;i++){
	     r[i].checked=false;
	       id=1
		 }
	   }
}

function submitQuestionForm(){
	document.questionForm.action=btnlogin()+"view.action";
	document.questionForm.submit();
}

function ss(a){
	if(window.event.keyCode==13){
		if(navigator.userAgent.indexOf("MSIE")>0) {
            a.action="view.action";
        }
	}
}

function checkcount(){
	var e = document.getElementsByName("entityIds");
	var elen = e.length;
	var i ;
	if ($("#jci1").val() == "" || $("#jci1").val() == 0) {
		$("#domainNodepath").val($("#jpr1").val());
	}else{
		if($("#jco1").val()!=""&&$("#jco1").val()!=0){
			$("#domainNodepath").val($("#jco1").val());
		}else{
			$("#domainNodepath").val($("#jci1").val());
		}
	}
	var domainNodepath=$("#domainNodepath").val();
	
	var topicId = document.getElementById("topicId");
	for(i=0;i<elen;i++){
		if(e[i].checked==true){
			if(domainNodepath=="000000"&&topicId.value==0){
				alert("请在下拉框中选择...");
				return false;
			//如果选的是为专题加问题的时候进行验证选择的问题是否有未解决的，如果有就弹出提示
			}else if(domainNodepath=="000000"&&topicId.value!=0){
				<c:forEach items="${pager.pageRecords}" var="q" varStatus="s">
			 	var qid = "<c:out value='${q.id}' default='0'/>";
			 	var qstatus = "<c:out value='${q.status}' default='0'/>";
			 	if(e[i].value==qid){
			 		if(qstatus!='2'){
						alert("为专题添加新问题时请选择已解决的问题...");
						return false;
			 		}
			 	}
				</c:forEach>
			 return true;
			}else{
				return true;
			}
		}
	}
	alert("请选择问题！");
	return false;
}

$(document).ready(function(){
	var status="${status}";
	var expertId="${expertId}";
	var content="${content}";
	if(status!=0){
		$(".status").val(status);
	}
	if(expertId!=0){
		$(".expertId").val(expertId);
	}
	if(content!=null && content!=""){
		$(".content").val(content);
	}
});

//三级联动

var city_data = "";
city_data = "${domainsStr}";
PCAN = city_data.split(",");
function PCAS() {
	this.SelP = document.getElementsByName(arguments[0])[0];
	this.SelC = document.getElementsByName(arguments[1])[0];
	this.SelA = document.getElementsByName(arguments[2])[0];
	this.DefP = this.SelA ? arguments[3] : arguments[2];
	this.DefC = this.SelA ? arguments[4] : arguments[3];
	this.DefA = this.SelA ? arguments[5] : arguments[4];
	this.SelP.PCA = this;
	this.SelC.PCA = this;
	this.SelP.onchange = function() {
		PCAS.SetC(this.PCA)
	};
	if (this.SelA)
		this.SelC.onchange = function() {
			PCAS.SetA(this.PCA)
		};
	PCAS.SetP(this);
	
};

PCAS.SetP = function(PCA) {
	var p_i = 0;
	for (i = 0; i < PCAN.length; i++) {
		// document.write(city_arr[i-1].substring(2,6)+"<br>");
		if (PCAN[i].substring(2, 6) == "0000") {
			PCAPV = PCAN[i].split('|')[0];
			PCAPT = PCAN[i].split('|')[1];
			PCA.SelP.options.add(new Option(PCAPT, PCAPV));

			if (PCA.DefP == PCAPV)
				PCA.SelP[p_i].selected = true;
			p_i++;
		}
	}
	PCAS.SetC(PCA)
};

PCAS.SetC = function(PCA) {
	PCA.SelC.length = 1;
	var c_i = 0;
	var city1_str = PCA.SelP.value;
	var str_city1 = city1_str / 10000;
	// alert(str_city1);
	for (i = 0; i < PCAN.length; i++) {
		if (PCAN[i].substring(0, 2) == str_city1 && PCAN[i].substring(2, 6) != "0000" && PCAN[i].substring(4, 6) == "00") {
			PCACV = PCAN[i].split('|')[0];
			PCACT = PCAN[i].split('|')[1];
			PCA.SelC.options.add(new Option(PCACT, PCACV));
			if (PCA.DefC == PCACV)
				PCA.SelC[c_i + 1].selected = true
			c_i++;
		}
	}
	if (PCA.SelA)
		PCAS.SetA(PCA)
};

PCAS.SetA = function(PCA) {
	PCA.SelA.length = 1;
	var a_i = 0;
	var city2_str = PCA.SelC.value;
	var str_city2 = city2_str / 100;
	// alert(str_city1);
	for (i = 0; i < PCAN.length; i++) {
		if (PCAN[i].substring(0, 4) == str_city2 && PCAN[i].substring(4, 6) != "00") {
			PCAAV = PCAN[i].split('|')[0];
			PCAAT = PCAN[i].split('|')[1];
			PCA.SelA.options.add(new Option(PCAAT, PCAAV));
			if (PCA.DefA == PCAAV)
				PCA.SelA[a_i + 1].selected = true
			a_i++;
		}
	}
}


</script>
</head>
<body>
	<div id="man_zone">
		<div class="title_content">
			<form action="m/question/view.action" method="post" onkeydown="ss(this);" name="questionForm">
				<!-- input type="text" value="请输入问题内容" style="color:#999999;"  onfocus="ddd(this);"/> -->
				请输入问题内容：<input type="text" value="${content}" name="content" /> <select name="status">
					<option value="0">请选择状态</option>
					<c:forEach items="${statusMap}" var="item">
						<option value="${item.value}" ${status == item.value?'selected=selected':'' }>${item.key }</option>
					</c:forEach>
				</select>
				<input type="hidden" name="submitForm" value="1" />
				<%-- <select name="expertId">
					<option value="0">请选择专家</option>
					<c:forEach items="${expertList}" var="item">
						<option value="${item.id}" ${expertId == item.id?'selected=selected':'' }>${item.fullName }</option>
					</c:forEach>
				</select> --%> <input type="button" onclick="submitQuestionForm();return false;" value="查询" />
			</form>
		</div>
		<div class="pageContent" >
			<s:form action="m/question/updateQuestionDomain.action" method="post" onsubmit="return checkcount();">
				<table class="table" width="1150" layoutH="138">
					<thead>
						<tr>
							<th width="5%" align="center"><input type="checkbox" group="entityIds" class="checkboxCtrl" onclick="findAllChecBox();" /></th>
							<th class="asc" align="center">标题</th>
							<th width="14%" align="center">提问时间</th>
							<th width="8%" align="center">提问人</th>
							<th width="7%" align="center">领域</th>
							<th width="5%" align="center">回答</th>
							<th width="6%" align="center">状态</th>
							<th width="5%" align="center">操作</th>
							<th width="5%" align="center">推荐</th>
							<th width="5%" align="center">头条</th>
						</tr>
					</thead>
					<tbody><% 
String path = request.getContextPath(); %>
						<c:forEach items="${pager.pageRecords}" var="q" varStatus="s">
							<tr target="sid_user" rel="1">
								<td height="30px" align="center"><input name="entityIds" value="${q.id}" type="checkbox" /></td>
								<td align="left"><c:if test="${fn:length(q.subject)>18}">
										<a href="<%=path%>/question/${q.id }.html" target="_blank">${fn:substring(q.subject,0,16)}...</a>
									</c:if> <c:if test="${fn:length(q.subject)<=18}">
										<a href="<%=path%>/question/${q.id }.html" target="_blank">${q.subject}</a>
									</c:if></td>
								<td align="center"><fmt:formatDate value="${q.createdTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss " /></td>
								<td><c:if test="${fn:length(q.user.username)>5}">
         		    ${fn:substring(q.user.username,0,4)}..
         		</c:if> <c:if test="${fn:length(q.user.username)<=5}">
         		    ${q.user.username}  
         		</c:if></td>
								<td align="center">${q.domain.name}</td>
								<td align="center">${q.replyNum }</td>
								<td align="center">
									<c:if test="${q.status eq 1 }">
										<span style="color: red">未解决</span>
									</c:if>
									<c:if test="${q.status eq 2 }">
										<span>已解决</span>
									</c:if>
									<c:if test="${q.status eq 99 }">
										<span  style="color: black;">已关闭</span>
									</c:if>
								</td>
								<td align="center"><a href="javascript:void(0)" class="removeQuestion" id="${q.id }" onclick="">删除</a></td>
								<td align="center" id="${q.id}recommended"><c:choose>
										<c:when test="${q.recommended eq 1}">
											<a style="color: gray" href="javascript:void(0);" onclick="changestatus(${q.id },'recommended',0)">取消</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0);" onclick="changestatus(${q.id },'recommended',1)">推荐</a>
										</c:otherwise>
									</c:choose></td>
								<td align="center" id="${q.id}head"><c:choose>
										<c:when test="${q.head eq 1}">
											<a style="color: gray" href="javascript:void(0);" onclick="changestatus(${q.id },'head',0)">取消</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0);" onclick="changestatus(${q.id },'head',1)">推荐</a>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="panelBar" align="left" style="margin-top: 10px;">
					<font color="black" size="2">&nbsp;&nbsp;将选中问题移动到</font>
					<select name="jpr1" id="jpr1" style="width: 80px;"></select>- 
					<select name="jci1" id="jci1" style="width: 80px;"></select>- 
					<select name="jco1" id="jco1" style="width: 80px;"></select>
					<script language="javascript" defer="defer">
						new PCAS("jpr1", "jci1", "jco1", "000000", "0", "0");
					</script>
					<input type="hidden" name="domainNodepath" id="domainNodepath"/>
					<s:submit value="确定" method="execute" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<!-- 为专题增加问题 -->
					<font color="black" size="2">将选中问题添加到</font> <select id="topicId" name="topicId">
						<option value="0">请选择专题</option>
						<c:forEach items="${allTopics}" var="topic">
							<option value="${topic.id}">${topic.title }</option>
						</c:forEach>
					</select>
					<s:submit value="添加" method="addQuestionToTopic" />
				</div>
			</s:form>
			<div class="pages" align="right" style="padding-right: 50px; margin-top: 10px;">
				<jsp:include page="../pager.jsp" />
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(function(){   				
   		$("a.removeQuestion").click(function(){
   			if(confirm("你确信要删除该问题记录吗？")){
   				var id =$(this).attr("id");
   			   	window.location.href=btnlogin()+'m/question/remove.action?question.id='+id; 		
   			}
   		}); //end click
   		
   	});
	
	//修改是否推荐,头条状态
	function changestatus(id,column,status){
		var url="${pageContext.request.contextPath}/m/question/edits.action";
		$.post(url,{id:id,column:column,status:status},
				function(d){
					if(d.result="success"){
						alert("设置成功");
						$("#"+id+column).empty();
						if(status==1){
							$("#"+id+column).append("<a style='color:gray' href='javascript:void(0);' onclick=changestatus("+id+",'"+column+"',0)>取消</a>");
						}else{
							$("#"+id+column).append("<a href='javascript:void(0);' onclick=changestatus("+id+",'"+column+"',1)>推荐</a>");
						}
					}else{
						alert("设置失败");
					};
				}
		); 
	}

</script>
	</div>
</body>
</html>
