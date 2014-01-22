<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">
	String.prototype.trim = function() {
		return this.replace(/(^\s*)|(\s*$)/g, "");
	}

	function GetRequest() { 
		   var url = location.search; //获取url中"?"符后的字串 
		   var theRequest = new Object(); 
		   if (url.indexOf("?") != -1) { 
		      var str = url.substr(1); 
		      strs = str.split("&"); 
		      for(var i = 0; i < strs.length; i ++) { 
		         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
		      } 
		   } 
		   return theRequest; 
	} 
	var Request = new Object();
	Request = GetRequest();
	var casUsername=Request['casUsername'];
	var casPassword=Request['casPassword'];
	if(casUsername!=null&&casPassword!=null){
		parent.document.getElementById('ssoLoginFrame').src="http://${applicationScope.siteUrl }/success.jsp?rand="+Math.random() + "&casUsername=" +casUsername+"&casPassword="+casPassword;
	}
	
</script>
</head>
</html>