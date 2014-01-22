<%@ page contentType="text/html" language="java" pageEncoding="utf-8"%>
<%@page import="com.yy.qa.bean.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="../js/jquery-1.7.1.js"></script>
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<title>管理区域</title>
<style>
table,th,td,caption {
	margin: 0px;
	padding: 0px;
	line-height: 18px;
	color: #0099CC;
	font-size: 12px;
	background: #D5DEDB;
	font-weight: bold;
}

.mytable th {
	text-align: left;
	font-weight: normal;
	width: 150px;
	padding: 6px;
	font-weight: bold;
}

.mytable caption {
	color: #0099CC;
	line-height: 30px;
}

.mytable td {
	padding: 3px;
}
</style>
</head>
<body>
	<script>
	//增加附件
	$(document).ready(function() {
		$("#addPicAttachment").click(function() {
			$("#picAttachment").append(" <input type='file' name='picAttachments' />");
		});

		$("#addMovieAttachment").click(function() {
			$("#movieAttachment").append(" <input type='file' name='movieAttachments' />");
		});
	});
	
	//删除附件
	function deleteAtt(id){
		var url="${pageContext.request.contextPath}/m/s/deleteExpertAttachment.action";
		$.post(url,{id:id},
			function(d){
				if(d.result="success"){
					alert("删除成功");
					$("#delatt"+id).empty();
				}else{
					alert("删除失败");
				};
			}
		);
	}
	function checkSub() {
		
		// 专家描述
		var summary = $("#summary").val().replace(/(^\s*)|(\s*$)/g, "").length;
		if (summary >= 100) {
			alert("专家描述过长...");
			return false;
		}
		if (summary == 0) {
			alert("专家描述不能为空...");
			return false;
		}
		
		//专家头像
		var expertImgs = $("#expertImgs").val();
		if(expertImgs!=null&&expertImgs!=''){
			if (!/.(${picType})$/.test(expertImgs)) {
				alert("专家头像类型必须是${picType}中的一种。");
				return false;
			}
		}
		
		// 所在单位
		var organization = $("#organization").val().replace(/(^\s*)|(\s*$)/g, "").length;
		if (organization >= 50) {
			alert("所在单位字数过多...");
			return false;
		}
		if (organization == 0) {
			alert("所在单位不能为空...");
			return false;
		}
		
		//专家领域r1
		var r1 = $("#r1").html();
		var obj = document.getElementsByName("selectedDomains"); // 获取多选框数组
	    var objLen = obj.length;
	    var objYN = false; // 是否有选择
	    for (var i = 0; i < objLen; i++) {
	        if (obj [i].checked == true) {
	            objYN = true;
	            break;
	        }
	    }
		if(!objYN){
			alert("专家领域不能为空...");
			return false;
		}
		
		// 专家简历
		var resume = $("#resume").val().replace(/(^\s*)|(\s*$)/g, "").length;
		if (resume >= 2000) {
			alert("专家简历字数过多...");
			return false;
		}
		if (resume == 0) {
			alert("专家简历不能为空...");
			return false;
		}
		
		//验证图片附件
		var checkPicAttachment = '0';
		$('*[name="picAttachments"]').each(function(){
			var file = $(this).val();
			if(file!=null&&file!=''){
				if (!/.(${picType})$/.test(file)) {
					alert("上传专家图片类型必须是${picType}中的一种。");
					checkPicAttachment = '1';
				}
			}
		});
		if(checkPicAttachment=='1'){
			return false;
		}
		
		//验证视频附件
		var checkMovieAttachment = '0';
		$('*[name="movieAttachments"]').each(function(){
			var moviefile = $(this).val();
			if(moviefile!=null&&moviefile!=''){
				if (!/.(${movieType})$/.test(moviefile)) {
					alert("上传专家视频类型必须是${movieType}中的一种。");
					checkMovieAttachment = '1';
				}
			}
		});
		if(checkMovieAttachment=='1'){
			return false;
		}
		return true;
	}

	function addDomain() {
		//var mainDiv=document.getElementById("domainSelectDIV");
		//var selectDiv= document.createElement("select");
		//	selectDiv.name="domainIds";

		//mainDiv.appendChild(selectDiv);
		var num = document.getElementById("countNum").value;
		//alert(num);
		document.getElementById("s" + num).style.display = "inline";
		num = num - 1;
		document.getElementById("countNum").value = num;
		if (num == 0) {
			document.getElementById("countButton").style.display = "none";
		}
	}
	var ds = "${domainsStr}";
	function testme() {
		alert(ds);
	}

	// 三级联动

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
		PCAS.SetP(this)
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

	//在input里面显示已经选择的分类
	var domains ="";
	var domainNodePath;
	var domainName;
	function getSelectedDomain() {
		domainNodePath = document.getElementById('jco1').options[document.getElementById('jco1').selectedIndex].value;
		if (domainNodePath == "") {
			domainNodePath = document.getElementById('jci1').options[document.getElementById('jci1').selectedIndex].value;
			if (domainNodePath == "") {
				domainNodePath = document.getElementById('jpr1').options[document.getElementById('jpr1').selectedIndex].value;
				domainName = document.getElementById('jpr1').options[document.getElementById('jpr1').selectedIndex].text;
			} else {
				domainName = document.getElementById('jci1').options[document.getElementById('jci1').selectedIndex].text;
			}
		} else {
			domainName = document.getElementById('jco1').options[document.getElementById('jco1').selectedIndex].text;
		}
		domains = domains + "<input type='checkbox' name='selectedDomains' value="+domainNodePath+" checked='checked' />" + domainName;
		;
		document.getElementById('r1').innerHTML = domains;
	}
	</script>

	<div>
		<form method="post" action="m/expert/updateExpert.action" onsubmit="return checkSub();" enctype="multipart/form-data">
			<table class="mytable" cellpadding="0" cellspacing="0" width="700">
				<tr>
					<td height="40px" colspan="4" style="border-bottom: 1px solid; font-size: 14px; font-weight: bold; padding-left: 20px;">修改<font color="red">${expert.fullName}</font>的专家信息 <input
						type="hidden" value="${id}" name="id" /> <input type="hidden" value="${username}" name="username" />
					</td>
				</tr>
				<tr>
					<th>专家描述</th>
					<td colspan="3"><textarea cols="55" rows="1" id="summary" name="expert.summary">${expert.summary}</textarea> <span style="color: #D41F55; font-size: 11px" id="checkInfo"> </span></td>
				</tr>
				<tr>
					<th>专家头像</th>
					<td colspan="3"><c:if test="${expert.photoUrl!='images/70X91.jpg' }">
							${expert.photoUrl}
							<input type="hidden" name="expert.photoUrl" value="${expert.photoUrl }" />
						</c:if> <input type="file" name="expertImgs" /> <span style="color: #D41F55; font-size: 11px" id="checkInfo"> </span></td>
				</tr>
				<tr>
					<th>所在单位</th>
					<td colspan="3"><input name="expert.organization" id="organization" value="${expert.organization}" /> <span
						style="color: #D41F55; font-size: 11px" id="checkInfo">*最多不能超过25个汉字 </span></td>
				</tr>
				<tr>
					<th>是否推荐</th>
					<td colspan="3"><input type="radio" value="T" checked="checked" name="recommended" />推荐 <input type="radio" value="F" name="recommended" />不推荐 <span style="color: #D41F55; font-size: 11px">
							*默认为推荐</span></td>
				</tr>
				<tr>
					<th>专家领域</th>
					<td colspan="3">
						<font id="r2">
							<s:iterator value="selectedDomains" id="domain">
								<input type="checkbox" name="selectedDomains" value="<s:property value='#domain.nodePath' />" checked="checked" /><s:property value='#domain.name' />
							</s:iterator>
						</font>
						<font id="r1"></font>
						<div>
							请选择领域：<select name="jpr1" id="jpr1"></select>-><select name="jci1" id="jci1"></select>-><select name="jco1" id="jco1"></select>
							<script language="javascript" defer="defer">
								new PCAS("jpr1", "jci1", "jco1", "010000", "0", "0");
							</script>
							<input type="button" value="添加领域" onclick="getSelectedDomain();" />
						</div>
					</td>

				</tr>
				<tr>
					<th>专家简历</th>
					<td colspan="3"><textarea cols="55" rows="5" id="resume" name="expert.resume">${expert.resume }</textarea><br /> <span style="color: #D41F55; font-size: 11px" id="checkInfo2"> </span></td>
				</tr>
				<tr>
					<th>图片附件</th>
					<td colspan="3">
						<s:iterator value="attachments" id="attach">
							<s:if test="#attach.type==3">
								<div  id="delatt<s:property value='#attach.id' />" style="height: 120px;width: auto;float: left;margin-right:10px; "> 
									<img src="${pageContext.request.contextPath}/file/expert/attachment/<s:property value='#attach.path' />" width="110px" height="90px;"/>
									<br/>
									<a href='javascript:void(0);' onclick="deleteAtt(<s:property value='#attach.id' />)">删除</a>
								</div>
							</s:if>
						</s:iterator>
					</td>
				</tr>
				<tr>
					<th></th>
					<td colspan="3">
						<input type="button" value="添加图片" id="addPicAttachment" />
						<div id="picAttachment" style="margin-top: 10px; width: 240px;"></div>
					</td>
				</tr>
				<tr>
					<th>视频附件</th>
					<td colspan="3">
						<s:iterator value="attachments" id="attachmt">
							<s:if test="#attachmt.type==2">
								<font id="delatt<s:property value='#attachmt.id' />"> <s:property value="#attachmt.name" />&nbsp;&nbsp; 
									<a href='javascript:void(0);' onclick="deleteAtt(<s:property value='#attachmt.id' />)">删除</a>
									丨&nbsp;&nbsp;
								</font>
							</s:if>
						</s:iterator>
					</td>
				</tr>
				<tr>
					<th></th>
					<td colspan="3">
						<input type="button" value="添加视频" id="addMovieAttachment" />
						<div id="movieAttachment" style="margin-top: 10px; width: 280px;"></div>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center" height="20"><input type="submit" value="提交" /> <input type="reset" value="重填" style="margin-left: 30px;" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
