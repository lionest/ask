<%@ page contentType="text/html" language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://ckfinder.com" prefix="ckfinder"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<link rel="stylesheet" href="../css/mainzone.css" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="../js/ajaxfileupload2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<title>管理区域</title>
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
		domains = domains + "<input type='checkbox' name='selectedDomains' value="+domainNodePath+" checked='checked' />" + domainName;
		;
		document.getElementById('r1').innerHTML = domains;
	}
//表单检查
	function checkcontent() {
		var title = $("#title").val().replace(/[^\x00-\xff]/g, '**').length;
		if (title >= 100) {
			alert("您输入的问题标题过长...");
			return false;
		}
		if (title == 0) {
			alert("请输入问题标题...");
			return false;
		}
		if ($("#jci1").val() == "" || $("#jci1").val() == 0) {
			$("#domainNodepath").val($("#jpr1").val());
		}else{
			if($("#jco1").val()!=""&&$("#jco1").val()!=0){
				$("#domainNodepath").val($("#jco1").val());
			}else{
				$("#domainNodepath").val($("#jci1").val());
			}
		}
		var content = CKEDITOR.instances.content.getData();
		var contentlen = content.replace(/[^\x00-\xff]/g, '**').length;
		if (contentlen==0) {
			alert("请输入满意答案...");
			return false;
		}else{
			$("#cont").val(content);
		}
		return true;
	}
</script>
</head>
<body>
	<div id="man_zone">
		<div class="title_content">
			添加新问题
		</div>
		<div class="pageContent" style="line-height: 30px;">
			<form action="addArticle.action" method="post" onsubmit="return checkcontent()">
				问题标题： <input name="title" id="title" type="text" size="60" /><br />
				问题分类：
				<select name="jpr1" id="jpr1" style="width: 80px;"></select>- 
				<select name="jci1" id="jci1" style="width: 80px;"></select>- 
				<select name="jco1" id="jco1" style="width: 80px;"></select>
				<script language="javascript" defer="defer">
					new PCAS("jpr1", "jci1", "jco1", "010000", "0", "0");
				</script>
				<input type="hidden" name="domainNodepath" id="domainNodepath"/>
				<br/>满意答案：
				<div style="width: 800px;">
					<textarea cols="80" id="content" name="content" rows="10"></textarea>
				</div>
				<div>
				图片：
					<input type="file" name="questionImgs" style="width: 160px;height: 23px;" id="questionImg1" class="textr1" onfocus="this.style.border= '#69C74C 1px solid '; " onblur="this.style.border='#C6C4C4 1px solid'" />
							</div> 
				<input type="hidden" name="cont" id="cont"/>
				<input type="submit" value="提交" /> <input type="reset" value="清除" />
			</form>
			<ckfinder:setupCKEditor basePath="/ckfinder/" editor="content" />
			<ckeditor:replace replace="content" basePath="/ckeditor/" />
		</div>
	</div>
</body>
</html>
