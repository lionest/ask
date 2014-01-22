<%@ page contentType="text/html" language="java" pageEncoding="utf-8"%>
<%@page import="com.yy.qa.bean.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
		function checkSub() {
			var t = document.getElementById("title");
			var c = document.getElementById("checkInfo");
			var n = document.getElementById("con");
			var i = document.getElementById("info");
			if (t.value == "") {
				c.innerHTML = "*请输入姓名";
				return false;
			} else {
				c.innerHTML = "";
			}
			if (n.value == "") {
				document.getElementById("checkInfo1").innerHTML = "*请输入内容";
				return false;
			} else {
				document.getElementById("checkInfo1").innerHTML = "";
			}
			if (i.value == "") {
				document.getElementById("checkInfo2").innerHTML = "*请输入内容";
				return false;
			} else {
				document.getElementById("checkInfo2").innerHTML = "";
			}

			if (navigator.userAgent.indexOf("MSIE") > 0) {
				window.location.href = "addExpert.action?id=${domain.id}";
				return true;
			}
			if (navigator.userAgent.indexOf("Firefox") > 0) {
				window.location.href = "m/expert/addExpert.action?id=${domain.id}";
				return true;
			}
			if (navigator.userAgent.indexOf("Opera") > 0) {
				return "Opera";
			}
			if (navigator.userAgent.indexOf("Safari") > 0) {
				return "Safari";
			}
			if (navigator.userAgent.indexOf("Camino") > 0) {
				return "Camino";
			}
			if (navigator.userAgent.indexOf("Gecko") > 0) {
				return "Gecko";
			}
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
		var domains = "";
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
			domains = domains + "<input type='checkbox' name='selectedDomains' value="+domainNodePath+" checked='checked' />" + domainName;;
			document.getElementById('r1').innerHTML = domains;
		}
	</script>

	<div>
		<!-- 	<input type="button" value="test" onclick="testme();"/> -->
		<form method="post" action="addExpert.action" onsubmit="return checkSub();" enctype="multipart/form-data">
			<table class="mytable" cellpadding="0" cellspacing="0" width="700">
				<tr>
					<td height="40px" colspan="4" style="border-bottom: 1px solid; font-size: 14px; font-weight: bold; padding-left: 20px;">添加<font color="red" >${username}</font>为专家<span style="color: #D41F55; font-size: 11px"> *请将尽量将专家资料填写完整后提交</span> <input type="hidden" value="${id}" name="id" /> <input
						type="hidden" value="${username}" name="username" />
					</td>
				</tr>
				<tr>
					<th>专家描述</th>
					<td colspan="3"><textarea cols="55" rows="2" id="textarea" name="expert.summary"></textarea> <span style="color: #D41F55; font-size: 11px" id="checkInfo"> </span></td>
				</tr>
				<tr>
					<th>专家头像</th>
					<td colspan="3"><input type="file" name="expertImgs" /> <span style="color: #D41F55; font-size: 11px" id="checkInfo"> </span></td>
				</tr>
				<tr>
					<th>所在单位</th>
					<td colspan="3"><input type="text" name="expert.organization" style="height: 25px; width: 300px; padding-top: 5px; font-size: 14px;" maxlength="25" /> <span
						style="color: #D41F55; font-size: 11px" id="checkInfo">*最多不能超过25个汉字 </span></td>
				</tr>
				<tr>
					<th>是否推荐</th>
					<td colspan="3"><input type="radio" value="T" checked="checked" name="recommended" />推荐 <input type="radio" value="F" name="recommended" />不推荐 <span style="color: #D41F55; font-size: 11px">
							*默认为推荐</span></td>
				</tr>
				<tr>
					<th>专家领域</th>
					<%-- 					<td colspan="3">
						<div id="domainSelectDIV">
							<select name="domainIds">
								<c:forEach items="${domains}" var="domain" varStatus="s">
									<option value="${domain.id }">${domain.name }</option>
								</c:forEach>
							</select> <select id="s1" style="display: none" name="domainIds">
								<option value="" selected="selected">-请选择-</option>
								<c:forEach items="${domains}" var="domain" varStatus="s">
									<option value="${domain.id }">${domain.name }</option>
								</c:forEach>
							</select> <select id="s2" style="display: none" name="domainIds">
								<option value="" selected="selected">-请选择-</option>
								<c:forEach items="${domains}" var="domain" varStatus="s">
									<option value="${domain.id }">${domain.name }</option>
								</c:forEach>
							</select> <span style="color: #D41F55; font-size: 11px"> *最多可选3个领域</span> <input id="countButton" type="button" value="再加一个" onclick="addDomain();" /> <input type="hidden" id="countNum" value="2" />
						</div>
					</td> --%>
					<td>
						<div>
							请选择领域：<select name="jpr1" id="jpr1"></select>-><select name="jci1" id="jci1"></select>-><select name="jco1" id="jco1"></select>
							<script language="javascript" defer="defer">
								new PCAS("jpr1", "jci1", "jco1", "010000", "0", "0");
							</script>
							<input type="button" value="添加领域" onclick="getSelectedDomain();" />
						</div>
						已添加领域：
						<div id="r1">
							&nbsp
						</div>
					</td>
				</tr>

				<tr>
					<th>专家简历</th>
					<td colspan="3"><textarea cols="55" rows="8" id="info" name="expert.resume"></textarea><br /> <span style="color: #D41F55; font-size: 11px" id="checkInfo2"> </span></td>
				</tr>
				<tr>
					<td colspan="4" align="center" height="20"><input type="submit" value="提交" /> <input type="reset" value="重填" style="margin-left: 30px;" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
