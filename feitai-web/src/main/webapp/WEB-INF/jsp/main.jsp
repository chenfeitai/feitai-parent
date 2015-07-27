<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page isELIgnored="false" %> --%>
<%@ taglib prefix="c" uri="http//java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http//java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>主页</title>

<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript" src="js/ajax.js"></script>
</head>
<script type="text/javascript">

</script>
<body>
	<span>${user.name},您好!您的积分为${user.credits};</span>
	<div class="errorMsg">
		<span id="EEE"></span>
	</div>
</body>
</html>