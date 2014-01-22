<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>用户登录</TITLE>
<LINK href="css/User_Login.css" type="text/css" rel="stylesheet">
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<META content="MSHTML 6.00.6000.16674" name=GENERATOR>
</HEAD>
<BODY id=userlogin_body>
	<DIV id=user_login>
		<DL>
			<DD id=user_top>
				<UL>
					<LI class=user_top_l></LI>
					<LI class=user_top_c></LI>
					<LI class=user_top_r></LI>
				</UL>
			<DD id=user_main>
				<UL>
					<LI class=user_main_l></LI>
					<LI class=user_main_c>
						<DIV class=user_main_box>
						<form method="post" action="${pageContext.request.contextPath}/m/login.action">
							<UL>
								<LI class=user_main_text>用户名：</LI>
								<LI style="width: 150px;" class=user_main_input><INPUT class=TxtUserNameCssClass id="username" name="username" maxLength=20 value=""/></LI>
							</UL>
							<UL>
								<LI class=user_main_text>密 码：</LI>
								<LI class=user_main_input><INPUT class=TxtPasswordCssClass id="password" name="password" type=password value=""/></LI>
							</UL>

							<UL>
								<LI class=user_main_text></LI>
								<LI class=user_main_input><div id="logDiv"><font color="red"><s:property value="info"/></font></div></LI>
							</UL>
						</DIV>
					</LI>
					<LI class=user_main_r style="line-height: 80px;"><!-- <INPUT class=IbtnEnterCssClass id=IbtnEnter style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px;"
						 type=image src="images/user_botton.gif" name=IbtnEnter> -->
					<input type="submit" value=""  style="background-color: transparent;background-image:url('${pageContext.request.contextPath}/m/images/user_botton.gif'); width: 83px;height: 83px;">
					</LI>
				</UL>
					</form>
			<DD id=user_bottom>
				<UL>
					<LI class=user_bottom_l></LI>
					<LI class=user_bottom_c></LI>
					<LI class=user_bottom_r></LI>
				</UL>
			</DD>
		</DL>
	</DIV>
	<SPAN id=ValrUserName style="DISPLAY: none; COLOR: red"></SPAN>
	<SPAN id=ValrPassword style="DISPLAY: none; COLOR: red"></SPAN>
	<SPAN id=ValrValidateCode style="DISPLAY: none; COLOR: red"></SPAN>
	<DIV id=ValidationSummary1 style="DISPLAY: none; COLOR: red"></DIV>
	<DIV></DIV>
	</FORM>
</BODY>
</HTML>