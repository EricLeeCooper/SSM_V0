<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<style type="text/css">
	#content{
		width:300px;
		height:400px;
		margin: 100px auto;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册成功</title>
<%@include file="/jsp_use.jsp" %>
</head>
<body>
<div id = "content">
	恭喜${user.ident}:${user.ownername}注册成功，<h4>5</h4>秒后返回首页。。。
</div>
</body>
<script>
	var interval;
	$(function(){
		interval = setInterval(timer, 1000);
	});
	function timer(){
		var sec = $("h4").html();
		console.info(sec);
		$("h4").html(sec - 1);
		if(sec == 1){
			clearInterval(interval);
			window.location("/login.jsp");
		}
	}
</script>
