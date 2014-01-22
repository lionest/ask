<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="denglu">
	<div class="sbar">
		<h2>
			<a href="${applicationScope.loginUrl }" title="用户登录">用户登录</a>
		</h2>
		<span class="more">还不是会员？<a href="http://${applicationScope.casUrl }">注册>></a>
		</span>
	</div>
	<div class="lg_form">
	<br/>
	&nbsp;&nbsp;请点击此处进行<a href="${applicationScope.loginUrl }" style="color:red" >登录</a>！
	<!-- 
		<s:form action="login.action" method="post"> 
         ${errorMsg }
          <table width="200" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="59">用户名：</td>
              <td width="150" align="left"><s:textfield name="user.username" label="用户名" class="textr1" onfocus= "this.style.border= '#69C74C 1px solid '; "  cssStyle="border:#C6C4C4 1px solid;" />
              </td>
            </tr>
            <tr>
              <td height="50">密　码：</td>
              <td align="left"><s:password  name="user.password" class="textr1" onfocus= "this.style.border= '#69C74C 1px solid '; "  cssStyle="border:1px solid #C6C4C4 ;"/>
              </td>
            </tr>
            <tr>
              <td align="right">&nbsp;</td>
              <td align="left">  <s:submit value="提交" class="bt1" style="cursor:pointer;"/>
                <a href="#">忘记密码？</a></td>
            </tr>
          </table>
        </s:form>
     -->
	</div>
</div>