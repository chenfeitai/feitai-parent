<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page isELIgnored="false" %> --%>
<%@ taglib prefix="c" uri="http//java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http//java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>登录页</title>

<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/zhifu.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript" src="js/ajax.js"></script>
<script language="javascript" type="text/javascript" src="js/common.js"></script>

</head>
<script type="text/javascript">
	function doIt(obj) {
		document.forms[0].action="loginCheck.html";
		document.forms[0].submit();
	}
</script>
<body>
	<form id="_form" action="#" method="post">
		<table width="90%" border="0" cellspacing="0" cellpadding="0"
			class="main_bzlch">
			<tr>
				<th>用户名:</th>
				<td><input type="text" name="name" class="ydhinp3" /></td>
			</tr>
			<tr>
				<th>密码:</th>
				<td><input type="password" name="password" class="ydhinp3" /></td>
			</tr>
			<input type="hidden" name="pageSize" value="10">
			<input type="hidden" name="pageNo" value="1">
		</table>
	</form>
	<div class="anniudiv">
		<input type="button" id="doItButton" class="main_anne" value="确认"
			onclick="doIt(this);" />&nbsp;&nbsp;&nbsp;&nbsp; <input type="reset"
			id="gobackButton" class="main_anne_h" value="清空 " />
	</div>
	<div class="errorMsg">
		<span id="EEE"></span>
	</div>
</body>
</html>