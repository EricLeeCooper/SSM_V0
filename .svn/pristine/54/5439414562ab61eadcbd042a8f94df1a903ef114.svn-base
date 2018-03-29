<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>"> 
	<title>综合管理系统</title>
	<script type="text/javascript" src="js/jquery-easyui-1.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
    <link rel="stylesheet" href="js/jquery-easyui-1.5.1/themes/default/easyui.css" type="text/css"></link>
	<link rel="stylesheet" href="js/jquery-easyui-1.5.1/themes/icon.css" type="text/css"></link>
	<script type="text/javascript" src="js/jquery-easyui-1.5.1/locale/easyui-lang-zh_CN.js"></script>
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
</head>
<script type="text/javascript">
		jQuery(function(){
		});
		function reloadValidateCode(){
	        $("#validateCodeImg").attr("src","<%=basePath%>validateCode.do?data="+ new Date() + Math.floor(Math.random()*24));
	    }
</script>
<body>
<div align="center">
	<h2>欢迎使用个人综合管理系统</h2>
	<div id="div1" align="center">
		<div id="div2">
			<h3>用户登录</h3><br>
			<form action="<%=basePath %>show.login.do" method="post">
				用户名:<input type="text" name="username" required="true" /><br/><br/>
				密&nbsp;&nbsp;&nbsp;码:<input type="password" name="password"  required="true" /><br/><br/>
				验证码:<input type="text" name="validateCode" required="true"/><br><br/>
				&nbsp;&nbsp;&nbsp;<img id="validateCodeImg" src="<%=basePath%>validateCode.do" />&nbsp;&nbsp;
				<a href="javascript:void(0);" onclick="reloadValidateCode()">看不清？</a><br><br>
				<input type="button" onclick="login()" value="登录"/>&nbsp;&nbsp;<input type="reset" value="重置"/>
			</form>
		</div><br/><br/><br/><br/>
		还没有帐号<a href="<%=basePath %>regist.do">点击这里进行注册</a>
	</div>
</div>
</body> 
<script>
$(function(){
     if(window.top!==window.self){
     	window.top.location=window.location;
     }
});
</script>
<script type="text/javascript">
	function login(){
		var code = $("input[name='validateCode']").val();
		$.ajax({
			type:"post",
			url: "<%=basePath %>loginTestCode.do", 
		 	data: "code="+code,
			async:false,
			success:function(data){
			var datas = JSON.parse(data);
				var datas = JSON.parse(data);
				console.info(datas.message);
				if(datas.message=="correct"){
					$("form").submit();
				}else{
					$.messager.confirm("验证码信息","验证码有误，请重新输入！",function(){
					/* location.reload();  //刷新页面*/
						location=location; 
					});
					
				}
			}
		});
	}
</script>
</html>