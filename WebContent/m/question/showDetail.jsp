<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<base href="<%=basePath%>"/>
    <title>My JSP 'showDetail.jsp' starting page</title>
    <script >
    var questionId=${question.id};
    
    function showProbing(id){
		//alert(id);
		document.getElementById("probing"+id).style.display = "block";
	}
	function showReply(id){
		//alert(id);
		document.getElementById("replytoprobing"+id).style.display = "block";
	}
	function showEdit(){
	alert(1);
		document.getElementsByName("detail").display = "none";
		document.getElementsByName("edit").display = "block";
	}
	function changeColor(o,style){
		//alert(1);
		if(style== 1){
			o.style.color="red";
		}else{
			o.style.color="gray";
		}
		
	}
	function showEdit(id,id2){
		document.getElementById(id).style.display="none";
		document.getElementById(id2).style.display="block";
	}
	
	//修改问题内容
	function showEdit2(outid,preId,aId,action,param,hiddenName,textareaName){
		document.getElementById(preId).style.display="none";
		document.getElementById(aId).style.display="none";
		var div=document.createElement("div");
			var form=document.createElement("form");
			form.action=action;
			form.method="post";
				var inputHidden = document.createElement("input");
				inputHidden.type="hidden";
				inputHidden.name=hiddenName;
				inputHidden.value=param;
				var textarea = document.createElement("textarea");
				textarea.name=textareaName;
				textarea.rows="3";
				textarea.cols="100";
				textarea.style.border="2px";
				textarea.value=document.getElementById(preId).innerHTML;
				
					var input = document.createElement("input");
					input.type="submit";
					input.value="提交";
					var inputReturn = document.createElement("input");
					inputReturn.type="button";
					inputReturn.value="返回";
					inputReturn.onclick=function (){
						window.location.href='m/question/detail.action?questionId='+questionId;
					};
				var br =document.createElement("br");
			form.appendChild(inputHidden);
			form.appendChild(textarea);
			form.appendChild(br);
			form.appendChild(input)
			form.appendChild(inputReturn);
			
		div.appendChild(form);
		
		document.getElementById(outid).appendChild(div);
	}
	function showUpload(outid,preId,action,param,param2,hiddenName,hiddenName2,uploadFileName){
		
		document.getElementById(preId).style.display="none";
		
		var div=document.createElement("div");
			var form=document.createElement("form");
			form.action=action;
			form.method="post";
			form.enctype="multipart/form-data";
				var inputHidden = document.createElement("input");
				inputHidden.type="hidden";
				inputHidden.name=hiddenName;
				inputHidden.value=param;
				var inputHidden2 = document.createElement("input");
				inputHidden2.type="hidden";
				inputHidden2.name=hiddenName2;
				inputHidden2.value=param2;
				var inputUpload = document.createElement("input");
				inputUpload.type="file";
				inputUpload.name=uploadFileName;
				var input = document.createElement("input");
				input.type="submit";
				input.value="提交";
				var inputReturn = document.createElement("input");
				inputReturn.type="button";
				inputReturn.value="返回";
				inputReturn.onclick=function (){
					window.location.href='m/question/detail.action?questionId='+questionId;
				};
				
				
			form.appendChild(inputHidden);
			form.appendChild(inputHidden2);
			form.appendChild(inputUpload);		
			form.appendChild(input)
			form.appendChild(inputReturn);
			
		div.appendChild(form);

		document.getElementById(outid).appendChild(div);
	}
    </script>
<SCRIPT language="javascript">

<!--
	function checktext(text){
	    allValid = true;
	    for (i = 0; i < text.length; i++)
	    {
	      if (text.charAt(i) != " ")
	      {
	        allValid = false;
	        break;
	      }
	    }
	return allValid;
	}
	function gbcount(message,total,used,remain){
		var max;
		max = total.value;
		if (message.value.length > max) {
		message.value = message.value.substring(0,max);
		used.value = max;
		remain.value = 0;
		<!-- alert("留言不能超过 200 个字!");-->
		alert("不能超过"+total.value+"个字!");
		}
		else {
			if(/^[\x00-\xff]*$/.test(message.value)){
				used.value = message.value.length;
			}else{
				used.value = message.value.length*2;
			}	
		remain.value = max - used.value;
		//alert( );
		}
	}
	function count(s,total,used,remain){
		
		var str = s.value;
		var c =0;//统计字数
		var i=0;
		for(i=0;i<str.length;i++){
			if(str.charCodeAt(i)>255){
				c=c+2;
			}else{
				c++;
			}
		}		
		var max = total.value;		
		used.value = c;			
		remain.value = max - used.value;
		//alert( );
		
	}
-->
	
</script>
<style type="text/css">
<!--
* {padding:0; margin:0;}
body, html {text-align:left; font-size:12px; line-height:150%; margin:0 auto; background:#fff; padding-top:3px;}
fieldset {padding:10px; width:550px; margin:0 0;}
legend {font-size:14px; font-weight:bold;}
.inputtext {border:none; background:#fff;}
-->
</style>
<link href="css/question.css" rel="stylesheet" type="text/css" />
  </head>

  <body>
   <div id="man_zone"> 
  <div class=""> 
  <!--左侧开始-->
  <div class=""> 
    <!--  问题开始-->
    <div class="q_quest01">
       <!--标题开始-->
       <div class="q_tit">
         <div class="icon fl"><img src="images/q_01.jpg" width="18" height="18" /></div>
         <div class="fon14 fl" id="title${question.id }">${question.subject }
       </div>
		 </div>
		 <!-- 修改标题 -->
         <div class="con fl" >
         &nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:pointer;font-weight:normal;color:gray" id="xiugaititle"  onclick="showEdit2('titleEditArea','title${question.id }','xiugaititle','m/question/edit.action','${question.id }','question.id','question.subject');">修改标题</a>                       
         </div>
         <div id="titleEditArea"></div>
         <br/><br/>
       <!--提问者信息开始-->
       <div class="q_info">
         <div class="fl">提问者：  <span class="g12"><a href="#">${question.user.username }</a></span> | 浏览次数：349次</div>
         <div class="fr"><fmt:formatDate value="${question.createdTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/></div>
       </div>
       <!--问题内容息开始-->
       <div class="q_Content" id="test">
       	 <pre id="questionContent" style="white-space:pre-wrap;word-wrap:break-word" >${question.content }&nbsp;&nbsp;&nbsp;<a style="color:gray;cursor:pointer" onmouseover="changeColor(this,1);" onmouseout="changeColor(this,2);" onclick="showEdit('questionContent','contentEditArea');">修改</a></pre>
         <div id="contentEditArea" style="display:none">
         	<form action="m/question/edit.action" method="post">       	      	        
		  		<input type="hidden" name="question.id" value="${questionId }"/>
				<textarea id="" name="question.content" rows="5"  cols="142"  onKeyUp="count(this,this.form.total,this.form.used,this.form.remain);">${question.content }</textarea>				
				<p align="right"  >最多字数：
				<input disabled maxLength="4" name="total" size="3" value="1000" class="inputtext"/>
				已用字数：
				<input disabled maxLength="4" name="used" size="3" value="0" class="inputtext"/>
				剩余字数：
				<input disabled maxLength="4" name="remain" size="3" value="1000" class="inputtext"/>&nbsp;&nbsp;
				<input type="submit" value="提交" style="color:red;font-size:14px"/>&nbsp;<input type="button" value="返回" style="color:red;font-size:14px" onclick="javascript:window.location.href='m/question/detail.action?questionId='+${question.id }" />
				</p>	
		  	
		  	</form>
         </div>        
       </div>
        <!--问题补充开始-->
        <c:forEach items="${qImgList}" var="qImgList">
        	<div class="q_replenish">
	          <!-- <div class="fon14 fl">图&nbsp;&nbsp;片：</div> -->
	          <div class="con fl" id="IMGoutId${qImgList.id }"><img src="${qImgList.url }" width="20%" height="10%"/>
	          <span id="IMG${qImgList.id}">
	          &nbsp;&nbsp;&nbsp;<a style="color:gray;cursor:pointer" onclick="showUpload('IMGoutId${qImgList.id }','IMG${qImgList.id}','m/question/editQuestionImg.action','${qImgList.id }','${qImgList.imgOrder }','questionImg.id','questionImg.imgOrder','questionImgs');">修改</a>
	          &nbsp;<a style="color:gray;cursor:pointer" onmouseover="changeColor(this,1);" onmouseout="changeColor(this,2);" href="m/question/removeQuestionImg.action?questionImg.id=${qImgList.id }">删除</a>
	          </span>
	          </div>
	          
	        </div>
	        <div style="font: 0px/0px sans-serif;clear: both;display: block"> </div>
        </c:forEach>
        <div class="q_replenish2" id="outdiv${question.id }">
         <div class="fon14 fl">问题补充：</div>
         <div class="con fl" >
         <pre id="content${question.id }" style="white-space:pre-wrap;word-wrap:break-word;display:inline">${question.extraContent }</pre>
         &nbsp;&nbsp;<a style="cursor:pointer;font-weight:normal;color:gray" id="xiugai"  onclick="showEdit2('outdiv${question.id }','content${question.id }','xiugai','m/question/edit.action','${question.id }','question.id','question.extraContent');">修改</a>                       
         </div>
 		 <div style="font: 0px/0px sans-serif;clear: both;display: block"> </div>
       </div>
       <!--我来帮他解答开始-->
    </div>
    <!--  问题结束-->
    <c:forEach items="${replyList}" var="reply">
    	<c:if test="${reply.accepted eq 1 && reply.probingId eq null}">
    		<!--  采纳答案开始-->
		    <div class="q_quest02">
		       <!--标题开始-->
		       <div class="q_tit">
		         <div class="icon fl"><img src="images/q_02.jpg" width="18" height="18" /></div>
		         <div class="fon14 fl">采纳答案</div>
		       </div>
		       <!--回答者信息开始-->
		       <div class="q_info">
		         <div class="fl">回答者  <span class="g12"><a href="#">${reply.user.nickname }</a></span></div>
		         <div class="zjicon fl"><img src="images/q_04.jpg" width="15" height="13" /></div>
		         <div class="fr"><fmt:formatDate value="${reply.createdTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/></div>
		       </div>
		       <!--问题内容息开始-->
		       <div class="q_answer_con" id="outid${reply.id }">  
		       <pre id="content${reply.id }" style="white-space:pre-wrap;word-wrap:break-word;display:inline">${reply.content}</pre>
		       <span id="ReplyOper${reply.id }">
		       &nbsp;&nbsp;<a style="cursor:pointer;font-weight:normal;color:gray" id=""  onclick="showEdit2('outid${reply.id }','content${reply.id }','ReplyOper${reply.id }','m/question/editReply.action','${reply.id }','reply.id','reply.content');">修改</a>
		       &nbsp;<a style="cursor:pointer;font-weight:normal;color:gray" id=""  href="m/question/removeReply.action?reply.id=${reply.id }">删除</a> 
		       </span>
		       </div>
		      <c:forEach items="${probingList}" var="probing">
		      	<c:if test="${probing.reply.id eq reply.id }">
		      		<div class="q_replenish2">
				       <div class="bfon14 fl">追问：</div>
				       <div class="con fl" id="ProOut${probing.id }">
				       <span id="ProOper${probing.id }">
				       <pre id="ProContent${probing.id }" style="white-space:pre-wrap;word-wrap:break-word;display:inline">${probing.content }</pre>
				       &nbsp;&nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"   onclick="showEdit2('ProOut${probing.id }','ProContent${probing.id }','ProOper${probing.id }','m/question/editProbing.action','${probing.id }','probing.id','probing.content');">修改</a> 				       
				       &nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"   href="m/question/removeProbing.action?probing.id=${probing.id }">删除</a> 
				       </span>
				       </div>
				       <div style="font: 0px/0px sans-serif;clear: both;display: block"> </div>
				     </div>
				     <c:forEach items="${replyList}" var="reply2probing">
		      			<c:if test="${reply2probing.probingId eq probing.id }">
		      				<div class="q_replenish2">
					          <div class="bfon14 fl">回答：</div>
					          <div class="con fl" id="outid${reply2probing.id }">
					          <span id="ReplyOper${reply2probing.id }">
					          <pre id="content${reply2probing.id }" style="white-space:pre-wrap;word-wrap:break-word;display:inline">${reply2probing.content}</pre>
					          &nbsp;&nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"   onclick="showEdit2('outid${reply2probing.id }','content${reply2probing.id }','ReplyOper${reply2probing.id }','m/question/editReply.action','${reply2probing.id }','reply.id','reply.content');">修改</a>
					          &nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"   href="m/question/removeReply.action?reply.id=${reply2probing.id }">删除</a>   
		       	        	  </span>
		       	        		</div>
		       	        		<div style="font: 0px/0px sans-serif;clear: both;display: block"> </div>
		       	        		</div>
					        	<c:forEach items="${probingList}" var="probing2">
							      	<c:if test="${probing2.reply.id eq reply2probing.id }">
							      		<div class="q_replenish2">
									       <div class="bfon14 fl">--追问：</div>
									       <div class="con fl" id="ProOut${probing2.id }">
									       	   <span id="ProOper${probing2.id }">
										       <pre id="ProContent${probing2.id }" style="white-space:pre-wrap;word-wrap:break-word;display:inline">${probing2.content }</pre>
										       &nbsp;&nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"   onclick="showEdit2('ProOut${probing2.id }','ProContent${probing2.id }','ProOper${probing2.id }','m/question/editProbing.action','${probing2.id }','probing.id','probing.content');">修改</a> 				       
										        &nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"   href="m/question/removeProbing.action?probing.id=${probing2.id }">删除</a> 
										       </span>
										    </div>
										    <div style="font: 0px/0px sans-serif;clear: both;display: block"> </div>
									       </div>
									     <c:forEach items="${replyList}" var="reply2probing2">
							      			<c:if test="${reply2probing2.probingId eq probing2.id }">
							      				<div class="q_replenish2">
										          <div class="bfon14 fl">&nbsp;&nbsp;回答：</div>
										          <div class="con fl" id="outid${reply2probing2.id }">
										          	<span id="ReplyOper${reply2probing2.id }">
										          	<pre id="content${reply2probing2.id }" style="white-space:pre-wrap;word-wrap:break-word;display:inline">${reply2probing2.content}</pre>
										          	&nbsp;&nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"  onclick="showEdit2('outid${reply2probing2.id }','content${reply2probing2.id }','ReplyOper${reply2probing2.id }','m/question/editReply.action','${reply2probing2.id }','reply.id','reply.content');">修改</a>  
							       	        	    &nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"   href="m/question/removeReply.action?reply.id=${reply2probing2.id }">删除</a>
							       	        	    </span>
							       	        	  </div>
							       	        	  <div style="font: 0px/0px sans-serif;clear: both;display: block"> </div>
										        </div>
										        	<c:forEach items="${probingList}" var="probing3">
												      	<c:if test="${probing3.reply.id eq reply2probing2.id }">
												      		<div class="q_replenish2">
														       <div class="bfon14 fl">----追问：</div>
														       <div class="con fl" id="ProOut${probing3.id }">
														       	   <span id="ProOper${probing3.id }">
															       <pre id="ProContent${probing3.id }" style="white-space:pre-wrap;word-wrap:break-word;display:inline">${probing3.content }</pre>
															       &nbsp;&nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"  onclick="showEdit2('ProOut${probing3.id }','ProContent${probing3.id }','ProOper${probing3.id }','m/question/editProbing.action','${probing3.id }','probing.id','probing.content');">修改</a> 				       
															       &nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"   href="m/question/removeProbing.action?probing.id=${probing3.id }">删除</a> 
															       </span>
															    </div>
															    <div style="font: 0px/0px sans-serif;clear: both;display: block"> </div>
														    </div>
														     <c:forEach items="${replyList}" var="reply2probing3">
												      			<c:if test="${reply2probing3.probingId eq probing3.id }">
												      				<div class="q_replenish2">
															          <div class="bfon14 fl">&nbsp;&nbsp;&nbsp;&nbsp;回答：</div>
															          <div class="con fl" id="outid${reply2probing3.id }">
															          	<span id="ReplyOper${reply2probing3.id }">
															          	<pre id="content${reply2probing3.id }" style="white-space:pre-wrap;word-wrap:break-word;display:inline">${reply2probing3.content}</pre>
															          	&nbsp;&nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"  onclick="showEdit2('outid${reply2probing3.id }','content${reply2probing3.id }','ReplyOper${reply2probing3.id }','m/question/editReply.action','${reply2probing3.id }','reply.id','reply.content');">修改</a>  
												       	        	    &nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"   href="m/question/removeReply.action?reply.id=${reply2probing3.id }">删除</a>
												       	        	    </span>
												       	        	  </div>
												       	        	  <div style="font: 0px/0px sans-serif;clear: both;display: block"> </div>
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
		     <div class="q_Comment">
		         <div class="fr">很满意！</div>
		         <div class="star fr"><img src="images/q_05.jpg" width="22" height="21" /></div>
		         <div class="star fr"><img src="images/q_05.jpg" width="22" height="21" /></div>
		         <div class="star fr"><img src="images/q_05.jpg" width="22" height="21" /></div>
		         <div class="star fr"><img src="images/q_05.jpg" width="22" height="21" /></div>
		         <div class="fr">评价：</div>
		       </div>
		    </div>
		    
		    <!--  采纳答案结束-->
    	</c:if>
    </c:forEach> 
    <!--  其他答案开始-->
    <c:if test="${question.replyNum > 0 }">
    	<div class="q_quest02">
	       <!--标题开始-->
	       <div class="q_tit">
	         <div class="icon fl"><img src="images/q_03.jpg" width="18" height="18" /></div>
	         <div class="fon14 fl">其他答案</div>
	         <div class="fg12 fl">共<c:choose><c:when test="${question.replyNum >0 && question.status eq 2}">${question.replyNum-1 }</c:when><c:otherwise>${question.replyNum }</c:otherwise></c:choose>条</div>
	       </div>
	       <c:forEach items="${replyList}" var="reply">
		     <c:if test="${reply.accepted eq 0 && reply.probingId eq null}">
		    	 <!--回答者信息开始-->
		         <div class="q_info">
		         	<div class="fl">回答者  <span class="g12"><a href="#">${reply.user.nickname }</a></span></div>
		         	<div class="zjicon fl"></div>
		         	<div class="fr"><fmt:formatDate value="${reply.createdTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/></div>
		         </div>
		         <!--问题内容息开始-->
		         <!--问题内容息开始-->
		         <div class="q_answer_con" id="outid${reply.id }">
		         <span id="ReplyOper${reply.id }">  
		         <pre id="content${reply.id }" style="white-space:pre-wrap;word-wrap:break-word;display:inline">${reply.content}</pre>
		         &nbsp;&nbsp;<a style="cursor:pointer;font-weight:normal;color:gray" onclick="showEdit2('outid${reply.id }','content${reply.id }','ReplyOper${reply.id }','m/question/editReply.action','${reply.id }','reply.id','reply.content');">修改</a> 
		         &nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"   href="m/question/removeReply.action?reply.id=${reply.id }">删除</a>
		         </span>
		         </div>
		         <c:forEach items="${probingList}" var="probing">
			      	<c:if test="${probing.reply.id eq reply.id }">
			      		<div class="q_replenish2">
					       <div class="bfon14 fl">追问：</div>
					       <div class="con fl" id="ProOut${probing.id }">
					            <span id="ProOper${probing.id }">
				       			<pre id="ProContent${probing.id }" style="white-space:pre-wrap;word-wrap:break-word;display:inline">${probing.content }</pre>
				       			&nbsp;&nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"  onclick="showEdit2('ProOut${probing.id }','ProContent${probing.id }','ProOper${probing.id }','m/question/editProbing.action','${probing.id }','probing.id','probing.content');">修改</a> 				       
				       	   		&nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"   href="m/question/removeProbing.action?probing.id=${probing.id }">删除</a> 
								</span>
				       	   </div>
				           <div style="font: 0px/0px sans-serif;clear: both;display: block"> </div>				       
					    </div>
					     <c:forEach items="${replyList}" var="reply2probing">
			      			<c:if test="${reply2probing.probingId eq probing.id }">
			      				<div class="q_replenish2">
						          <div class="bfon14 fl">回答：</div>
						          <div class="con fl" id="outid${reply2probing.id }">
						            <span id="ReplyOper${reply2probing.id }">
						            <pre id="content${reply2probing.id }" style="white-space:pre-wrap;word-wrap:break-word;display:inline">${reply2probing.content}</pre>
						            &nbsp;&nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"   onclick="showEdit2('outid${reply2probing.id }','content${reply2probing.id }','ReplyOper${reply2probing.id }','m/question/editReply.action','${reply2probing.id }','reply.id','reply.content');">修改</a>  
			       	        	    &nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"   href="m/question/removeReply.action?reply.id=${reply2probing.id }">删除</a>
			       	        	    </span>
			       	        	  </div>
		       	        		<div style="font: 0px/0px sans-serif;clear: both;display: block"> </div>
						        </div>
						        <c:forEach items="${probingList}" var="probing2">
							      	<c:if test="${probing2.reply.id eq reply2probing.id }">
							      		<div class="q_replenish2">
									       <div class="bfon14 fl">--追问：</div>
									        <div class="con fl" id="ProOut${probing2.id }">
									           <span id="ProOper${probing2.id }">
										       <pre id="ProContent${probing2.id }" style="white-space:pre-wrap;word-wrap:break-word;display:inline">${probing2.content }</pre>
										       &nbsp;&nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"   onclick="showEdit2('ProOut${probing2.id }','ProContent${probing2.id }','ProOper${probing2.id }','m/question/editProbing.action','${probing2.id }','probing.id','probing.content');">修改</a> 				       
										       &nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"   href="m/question/removeProbing.action?probing.id=${probing2.id }">删除</a> 
										       </span>
										    </div>
										    <div style="font: 0px/0px sans-serif;clear: both;display: block"> </div>
									    </div>
									     <c:forEach items="${replyList}" var="reply2probing2">
							      			<c:if test="${reply2probing2.probingId eq probing2.id }">
							      				<div class="q_replenish2">
										          <div class="bfon14 fl">&nbsp;&nbsp;回答：</div>
										          <div class="con fl" id="outid${reply2probing2.id }">
										            <span id="ReplyOper${reply2probing2.id }">
										          	<pre id="content${reply2probing2.id }" style="white-space:pre-wrap;word-wrap:break-word;display:inline">${reply2probing2.content}</pre>
										          	&nbsp;&nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"   onclick="showEdit2('outid${reply2probing2.id }','content${reply2probing2.id }','ReplyOper${reply2probing2.id }','m/question/editReply.action','${reply2probing2.id }','reply.id','reply.content');">修改</a>  
							       	        	    &nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"   href="m/question/removeReply.action?reply.id=${reply2probing2.id }">删除</a>
							       	        	    </span>
							       	        	  </div>
							       	        	  <div style="font: 0px/0px sans-serif;clear: both;display: block"> </div>
										        </div>
										        	<c:forEach items="${probingList}" var="probing3">
												      	<c:if test="${probing3.reply.id eq reply2probing2.id }">
												      		<div class="q_replenish2">
														       <div class="bfon14 fl">----追问：</div>
														       <div class="con fl" id="ProOut${probing3.id }">
														           <span id="ProOper${probing3.id }">
															       <pre id="ProContent${probing3.id }" style="white-space:pre-wrap;word-wrap:break-word;display:inline">${probing3.content }</pre>
															       &nbsp;&nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"  onclick="showEdit2('ProOut${probing3.id }','ProContent${probing3.id }','ProOper${probing3.id }','m/question/editProbing.action','${probing3.id }','probing.id','probing.content');">修改</a> 				       
															       &nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"   href="m/question/removeProbing.action?probing.id=${probing3.id }">删除</a> 
															       </span>
															    </div>
															    <div style="font: 0px/0px sans-serif;clear: both;display: block"> </div>
														    </div>
														     <c:forEach items="${replyList}" var="reply2probing3">
												      			<c:if test="${reply2probing3.probingId eq probing3.id }">
												      				<div class="q_replenish2">
															          <div class="bfon14 fl">&nbsp;&nbsp;&nbsp;&nbsp;回答：</div>
															          <div class="con fl" id="outid${reply2probing3.id }">
															          	<span id="ReplyOper${reply2probing3.id }">
															          	<pre id="content${reply2probing3.id }" style="white-space:pre-wrap;word-wrap:break-word;display:inline">${reply2probing3.content}</pre>
															          	&nbsp;&nbsp;<a style="cursor:pointer;font-weight:normal;color:gray" onclick="showEdit2('outid${reply2probing3.id }','content${reply2probing3.id }','ReplyOper${reply2probing3.id }','m/question/editReply.action','${reply2probing3.id }','reply.id','reply.content');">修改</a>  
												       	        	    &nbsp;<a style="cursor:pointer;font-weight:normal;color:gray"   href="m/question/removeReply.action?reply.id=${reply2probing3.id }">删除</a>
												       	        	    </span>
												       	        	  </div>
												       	        	  <div style="font: 0px/0px sans-serif;clear: both;display: block"> </div>
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
		         <div class="q_line"></div>
		     </c:if>
		   </c:forEach>
	    </div>
    </c:if>
    

  </div>
  </div>
  </div>

  </body>
</html>
  	