<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<div style="width:100%;height:100%">
	<video id="video"  ></video>
	<audio id="music"  ></audio>
</div>
<script type="text/javascript">
	$(function(){
		var obje = $("#mavdg").datagrid('getSelected');
		var path = obje.path;
		console.info("视频地址：");
		console.info(obje.path);
		if(path.indexOf(".mp4")>0){
			$("#video").attr("src","/"+path);
			$("#video").attr("controls","controls");
			$("#music").hide();
		}else{
			$("#music").attr("src","/"+path);
			$("#music").attr("controls","controls");
			$("#video").hide();
		}
	});
</script>
