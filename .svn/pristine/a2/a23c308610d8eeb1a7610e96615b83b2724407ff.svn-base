<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<div class="easyui-layout" style="width:100%;height:100%">
	<div data-options="region:'center',border:true">
		    <form  id = "addForm" action="<%=basePath %>vedio/updateSaveMAV.do" method="post">
		    	<input name="id" type="hidden" >
		    	<input name="path" type="hidden" >
			    <table style="margin:100px 0 0 180px;">
			    	<tr>
			    		<td>名称：</td>
			    		<td><input name="name" type="text"></td>
			    	</tr>
			    	<tr>
			    		<td>大小：</td>
			    		<td><input name="size" type="text" readonly></td>
			    	</tr>
			    	<tr>
			    		<td>时长：</td>
			    		<td><input name="length" type="text" readonly></td>
			    	</tr>
			    	<tr>
			    		<td>路径：</td>
			    		<td><input name="path" type="text" readonly></td>
			    	</tr>
			    </table>
		    </form>
	</div>
	<div data-options="region:'south',border:true" style="height:30px;">
		<div id="dlg-buttons" style="height:100%;width:120px;margin-left: 460px">
			<a class="easyui-linkbutton" iconCls="icon-ok" onclick="updateStudent()">确定</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeDialog()">取消</a>
		</div>
	</div>  
</div>
<script type="text/javascript">
	function updateStudent(){
		$("#addForm").form('submit', {    
		    success: function(){ 
		        $('#mavdia').dialog('close');
		        var tab = $('#tt').tabs('getSelected');  
				tab.panel('refresh'); 
				$.messager.show({
							title:'提示消息',
							msg:'修改完成',
							timeout:3000,
							showType:'slide'
						}); 
						
		    }    
		});  
	}
	function closeDialog(){
		console.info("关闭对话框");
		$('#mavdia').dialog('close');
	}
</script>
