<%@ page contentType="text/html" language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<title>管理区域</title>
<style>  
   table,th,td, caption{ margin:0px; padding:0px; line-height:18px; color: #0099CC; font-size:12px; background:#D5DEDB;font-weight:bold; }
   .mytable th{   text-align:left; font-weight:normal; width:150px;  padding:6px; font-weight:bold; }
   .mytable caption{ color:#0099CC; line-height:30px; }
   .mytable td{ padding:3px;  }
</style>
</head>
<body>
<script>
  function checkSub(){
    var t = document.getElementById("title");
    var c = document.getElementById("checkInfo");
	if(t.value.trim() == ""){
	   c.innerHTML = "*请输入标题";
	   return false;
	}else{
	    c.innerHTML = "";
	}
  
  if(navigator.userAgent.indexOf("MSIE")>0) {
	     window.location.href="add.action";
            return true; 
        }
    if(navigator.userAgent.indexOf("Firefox")>0){
		window.location.href="m/keyword/update.action";
            return true; 
        }
    if(navigator.userAgent.indexOf("Opera")>0){
           window.location.href="m/keyword/update.action";
            return true; 
        }
    if(navigator.userAgent.indexOf("Safari")>0) { 
           window.location.href="m/keyword/update.action";
            return true; 
        } 
  }
  
</script>
<div id="man_zone">
  <div style="margin:10px;">
  <form method="post" action="update.action" onsubmit="return checkSub();">
  <table class="mytable" cellpadding="0" cellspacing="0" style="margin-top:50px;" width="400px;">
   <tr>
   <td height="50px" colspan="4" style="border-bottom:1px solid 
   ; font-size:14px; font-weight:bold; padding-left:20px;">修改</td>
   </tr>
    <tr>
     <th>关键字名称</th>
        <td colspan="3">
        <input type="hidden" name="keyword.id" value="${keyword.id }"/>
        <input type="text" id="title" value="${keyword.content }" name="keyword.content" 
        style="height:15px; width:100px; padding:6px 5px 2px 2px;" maxlength="30" /> 
        ${errorMsg}<span style="color:#FF0000; font-size:11px" id="checkInfo"> </span></td>
    </tr>
    <tr>
        <td colspan="4" align="center"><input type="submit" value="提交" /> <input type="reset" value="重填" style="margin-left:30px;"/></td>
    </tr>
</table>
</form>
</div>




</div>
</body>
</html>
