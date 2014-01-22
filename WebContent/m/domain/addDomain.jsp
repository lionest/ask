<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<link rel="stylesheet" href="../css/admin.css" type="text/css" />
<title>管理区域</title>
</head>
<body>
	<script>
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
				PCAS.SetC(this.PCA);
			};
			if (this.SelA)
				this.SelC.onchange = function() {
					PCAS.SetA(this.PCA);
				};
			PCAS.SetP(this);
		};

		PCAS.SetP = function(PCA) {
			var p_i = 0;
			for (var i = 0; i < PCAN.length; i++) {
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
			PCAS.SetC(PCA);
		};

		PCAS.SetC = function(PCA) {
			PCA.SelC.length = 1;
			var c_i = 0;
			var city1_str = PCA.SelP.value;
			var str_city1 = city1_str / 10000;
			// alert(str_city1);
			for (var i = 0; i < PCAN.length; i++) {
				if (PCAN[i].substring(0, 2) == str_city1 && PCAN[i].substring(2, 6) != "0000" && PCAN[i].substring(4, 6) == "00") {
					PCACV = PCAN[i].split('|')[0];
					PCACT = PCAN[i].split('|')[1];
					PCA.SelC.options.add(new Option(PCACT, PCACV));
					if (PCA.DefC == PCACV)
						PCA.SelC[c_i + 1].selected = true;
					c_i++;
				}
			}
			if (PCA.SelA)
				PCAS.SetA(PCA);
		};

		PCAS.SetA = function(PCA) {
			PCA.SelA.length = 1;
			var a_i = 0;
			var city2_str = PCA.SelC.value;
			var str_city2 = city2_str / 100;
			// alert(str_city1);
			for (var i = 0; i < PCAN.length; i++) {
				if (PCAN[i].substring(0, 4) == str_city2 && PCAN[i].substring(4, 6) != "00") {
					PCAAV = PCAN[i].split('|')[0];
					PCAAT = PCAN[i].split('|')[1];
					PCA.SelA.options.add(new Option(PCAAT, PCAAV));
					if (PCA.DefA == PCAAV)
						PCA.SelA[a_i + 1].selected = true;
					a_i++;
				}
			}
		};
		
		//检查表单
		function checkForm() {
			var jpr1 = document.getElementById("jpr1").value;
			if(jpr1=='000000'){
				//如果选择无上级则判断是否选择分类。
				var chk = false;
				var pId = document.getElementsByName("parentId");
				for(var m=0;m<pId.length;m++){
					if(pId[m].checked){
						chk = true;
					}
				}
				if(chk==false){
					alert("请选择分类...");
					return false;
				}
			}
			
			var dname = document.getElementById("name").value;
			if (dname.replace(/(^\s*)|(\s*$)/g, "").length == 0) {
				alert("领域名称不合法，请重新输入...");
				return false;
			}
			
			return true;
		}
		/* String.prototype.trim = function() {
			return this.replace(/(^\s*)|(\s*$)/g, "");
		} */
	</script>
	<div id="man_zone">
		<div style="margin-left: 10px;">
			<form method="post" action="add.action" onsubmit="return checkForm();">
				<table class="mytable" cellpadding="0" cellspacing="0" style="margin-top: 10px;" width="800">
					<tr>
						<td height="40px" colspan="4" style="border-bottom: 1px solid; font-size: 14px; font-weight: bold; padding-left: 20px;">添加领域</td>
					</tr>
					<tr>
						<th></th>
						<td colspan="3">
							 <input type="radio" name="parentId" value="-1" />农事&nbsp;&nbsp;
							 <input type="radio" name="parentId" value="-2" />市场&nbsp;&nbsp;
							 <input type="radio" name="parentId" value="-3" />政策&nbsp;&nbsp;
							 <input type="radio" name="parentId" value="-4" />生活&nbsp;&nbsp;
						</td>
					</tr>
					<tr>
						<th>选择父级</th>
						<td colspan="3">
							<select name="jpr1" id="jpr1" style="width: 80px;"></select>&nbsp;&gt;
							<select name="jci1" id="jci1" style="width: 80px;"></select>&nbsp;&gt;
							<select name="jco1" id="jco1" style="width: 80px;"></select>
							<script language="javascript" defer="defer">
								new PCAS("jpr1", "jci1", "jco1", "000000", "0", "0");
							</script>
							<div id="r1"></div>
						</td>
					</tr>
					<tr>
						<th>领域名称</th>
						<td colspan="3">
							<input id="name" name="name" size="38" value=""></input>
							<span style="color: red;">${errorMsg }</span>
							<span style="color: #A0A0A4; font-size: 11px">*最多可输入不超过20个汉字</span>
						</td>
					</tr>
					<tr>
						<th>是否推荐</th>
						<td><input type="radio" name="recommended" value="1" />推荐&nbsp; <input type="radio" name="recommended" value="0" checked="checked" />不推荐&nbsp;</td>
					</tr>
					<tr>
						<td colspan="4" align="center" height="50px"><input type="submit" value="提交" /> <input type="reset" value="重填" style="margin-left: 30px;" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
