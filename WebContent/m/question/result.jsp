<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>

  </head>
  
  <body>
    添加成功！返回请点击:
    <%if(request.getAttribute("addtips")== null) {%>
   		<a href="detail.action?questionId=${questionId}">查看</a>
    <%}else{ %>
    	<a >查看</a>
    <%} %> 
  </body>
</html>
