<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>

<HEAD>
	<title>登录</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<LINK HREF="${pageContext.request.contextPath}/style/blue/login.css" type=text/css rel=stylesheet />
	<script type="text/javascript">
		$(document).ready(function(){
			document.forms[0].loginName.focus();
		});
		
		//在被嵌套时就刷新上级窗口
		if(window.parent != window){
			window.parent.location.reload(true);
		}	
		
	</script>
	
</HEAD>

<BODY LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 CLASS=PageBody >

<!-- 显示表单 -->
<s:form  action="user_login" focusElement="loginNameInput" id="form1">
    <DIV ID="CenterAreaBg">
        <DIV ID="CenterArea">
            <DIV ID="LogoImg"><IMG BORDER="0" SRC="${pageContext.request.contextPath}/style/blue/images/logo.png" /></DIV>
            <DIV ID="LoginInfo">
                <TABLE BORDER=0 CELLSPACING=0 CELLPADDING=0 width=100%>
                	<tr>
                		<td colspan="3"><!-- 显示错误 -->
                			<font color="red"> <s:fielderror /> </font>
                		</td>
                	</tr>
                
                    <TR>
                        <TD width=45 CLASS="Subject">
                        	<IMG BORDER="0" SRC="${pageContext.request.contextPath}/style/blue/images/login/userId.gif" />
                        </TD>
                        <TD>
                        	<s:textfield id="loginNameInput" size="20" cssCLASS="TextField required"  name="loginName" tabindex="1" />
                        </TD>
                        <TD ROWSPAN="2" STYLE="padding-left:10px;">
                        	<input type="image" tabindex="3" src="${pageContext.request.contextPath}/style/blue/images/login/userLogin_button.gif" />
                        </TD>
                    </TR>
                    
                    <TR>
                        <TD CLASS="Subject">
                        	<IMG BORDER="0" SRC="${pageContext.request.contextPath}/style/blue/images/login/password.gif" />
                        </TD>
                        <TD>
                        	<s:password id="aa" SIZE="20" cssCLASS="TextField required" NAME="password" showPassword="false" tabindex="2" />
                        </TD>
                    </TR>
                </TABLE>
            </DIV>
            <DIV ID="CopyRight"><A HREF="javascript:void(0)">&copy; 2017 版权所有 SMART</A></DIV>
        </DIV>
    </DIV>
</s:form>
</BODY>

</html>