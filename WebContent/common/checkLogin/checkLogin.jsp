<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ah3nong.wd.bean.User"  %>
<% User user = (User) session.getAttribute("user"); %>
<%if(user!=null){ %>
	checkLogin({username:'<%=user.getUsername() %>',nickname:'<%=user.getNickname() %>',
	email:'<%=user.getEmail() %>',	avatar:'<%=user.getAvatar() %>',expert:'<%=user.getExpert() %>)',
	sex:'<%=user.getSex() %>',fullName:'<%=user.getFullName()%>'});
<%}else{ %>
	checkLogin();
<%} %>
