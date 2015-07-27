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
<title>错误页</title>

<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/zhifu.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript" src="js/common.js"></script>
</head>
<script type="text/javascript">
</script>
<body>
	<div class="errorMsg">
		<span id="EEE">系统未知错误!请联系管理员<br>错误信息:${message}</span>
	</div>
</body>
</html>