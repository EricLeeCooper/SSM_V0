<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<style>
	#divUploadS{
		width:150px;
		margin:100px auto;
	}
</style>
<div class="easyui-layout" style="width:100%;height:100%">
	<div data-options="region:'center',border:true">
		<h3><a href="<%=basePath %>show/downloadStudent.do">下载模版</a></h3>
	    <form  id = "addFile" action="<%=basePath %>show/uploadStudent.do" method="post" enctype="multipart/form-data">
		    <div id="divUploadS">
		    	<input type="file" name="file"><br><br>
		    </div>
	    </form>
	</div>
	<div data-options="region:'south',border:true" style="height:30px;">
		<div id="dlg-buttons">
			<div style="height:100%;width:120px;margin-left: 460px">
				<a class="easyui-linkbutton" iconCls="icon-ok" onclick="addStudent()">确定</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#studia').dialog('close')">取消</a>
			</div>
		</div>
	</div>  
</div>
<script type="text/javascript">
	function addStudent(){
		console.info("提交---");
		$("#addFile").form('submit', {    
		    success: function(){ 
		        $('#studia').dialog('close');
		        var tab = $('#tt').tabs('getSelected');  
				tab.panel('refresh'); 
		    }    
		});  
	}
</script>
