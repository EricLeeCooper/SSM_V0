<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style>
	#content{
		margin:80px 0;
	}
	#table{
		width:600px;
		height:200px;
		border:1px solid black;
		border-collapse: collapse;
		text-align:center;
	}
</style>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
	<div id="dp" class="easyui-panel" data-options="fit:'true';border:'false';">
		<div id="content">
			<table id="table">
				<thead>
					<td>姓名</td>
					<td>照片</td>
					<td>职务</td>
					<td>资产金额</td>
				</thead>
				<tr>
					<td><h5><label></label></h5></td>
					<td>
						<img alt="" width="80px" style="overflow: hidden;"><br><br>
						<button id="pic"></button>
					</td>
					<td><h5><label></label></h5></td>
					<td>
						<h5><label></label></h5><br>
						<button id="turnout">转账</button><button id="turnin" disabled>充值</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
<div id="dial"></div>
</div>
<script type="text/javascript">
	$("#pic").click(function(){
		$("#dial").dialog({
		    title: '上传图片',
		    width: 400,
		    height: 200,
		    closed: false,
		    cache: false,
		    href: '<%=basePath %>jsp/addfile.jsp',
		    modal: true
			});
	});
	$("#turnout").click(function(){
		$("#dial").dialog({
		    title: '转账',
		    width: 400,
		    height: 200,
		    closed: false,
		    cache: false,
		    href: '<%=basePath %>jsp/transmoney.jsp',
		    modal: true
			});
	});
	$(function(){
		$("#dlg").hide();
		$("#table td").css("border","1px solid black");
		$("#table td").css("width","150px");
		$.ajax({
			type:"POST",
			dateType:"JSON",	
		    url:"<%=basePath %>show.personalInfo.do" ,
		    async:false,
			success:function(data){
				var us = JSON.parse(data); 
				if(us.fileurl == null || us.fileurl == ""){
					$("img").attr("src","<%=basePath %>/js/respic/initpic.jpg");
					$("#pic").append("上传图片");
				}else{
					$("img").attr("src","/"+us.fileurl);
					$("#pic").append("修改图片");
				}
				
				if(us.count<=0){
					$("#turnout").attr("disabled","disabled");
				}
				$("#pic").click(function(){
						$("#dlg").dialog();
					});
				
				$("label:eq(0)").append(us.user);
				$("label:eq(1)").append(us.userIdenti); 
				$("label:eq(2)").append(us.count); 
			}  
		});
	});
	
</script>