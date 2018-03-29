<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <style>
		#div1{
			width: 500px;
			height: 330px;
			margin: 50px 0 0 0;
			border: 2px solid grey;
		}
		#div2{
			width: 300px;
			height: 200px;
		}
		h2{
			margin: 100px 0 0 0;
		}
		a{
			text-decoration: none;
		}
	</style>
    <title>注册</title>
  </head>
  
<body>
  <div align="center">
	<h2>欢迎使用个人财务管理系统</h2>
	<div id="div1" align="center">
		<div id="div2">
			<h3>用户登录</h3><br>
			<form action="<%=basePath %>show.registCount" method="post">
				姓名:<input name="user_name" type="text"><br>
  				编号:<input name="user_id" type="text" value="${user_id }"><br>
  				押金:<input name="user_count" type="text"><br>
  				<input type="submit" value="注册">
  				<input type="reset" value="取消">
			</form>
		</div><br/><br/><br/><br/>
		还没有帐号<a href="<%=basePath %>regist.do">点击这里进行注册</a>
	</div>
  </div>
</body>
</html>
