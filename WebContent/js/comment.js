	//打开评论
	function openComment(replyId){
		alert(replyId);
		var url="${pageContext.request.contextPath}/s/rc/listReplyComment.action";
		$.post(url,{replyId:replyId},
			function(d){
				for(var i=0;i<d.data.replyCommentList.length;i++){
					var repc = d.data.replyCommentList[i];
					$("#"+replyId+"listc").append("评论人："+repc.username+"&nbsp;&nbsp;&nbsp;&nbsp;评论时间："+repc.createdTime+"<br/>评论内容："+repc.content+"<br/><font color='#2DA508'>————————————————————————————————————————————————————</font><br/>");
				};
				$("#"+replyId+"listcomment").show();
				$("#"+replyId+"commenturl").hide();
				$("#"+replyId+"removecomment").show();
			}		
		);
	}
	//关闭评论
	function removecomment(replyId){
		$("#"+replyId+"listc").empty();
		$("#"+replyId+"listcomment").hide();
		$("#"+replyId+"commenturl").show();
		$("#"+replyId+"removecomment").hide();
	}
	//增加评论
	function addreplycomment(replyId){
		var url = "${pageContext.request.contextPath}/s/rc/addReplyComment.action";
		var content = document.getElementById(replyId+"replyCommentContent").value;
		if(content.length>50){
			alert("评论字数过多，请控制在50字以内...");
		}else{
			$.post(url,{replyId:replyId,replyCommentContent:content},
				function(d){
					//alert(d.result);
					if(d.result=="success"){
						$("#"+replyId+"listc").append("评论人："+d.data.newComment.username+"&nbsp;&nbsp;&nbsp;&nbsp;评论时间："+d.data.newComment.createdTime+"<br/>评论内容："+d.data.newComment.content+"<br/><font color='#2DA508'>————————————————————————————————————————————————————</font><br/>");
						document.getElementById(replyId+"replyCommentContent").value =="";
					}else{
						alert("评论失败...");
					};
				}		
			);
		};
	}
