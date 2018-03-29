<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<div class="easyui-layout" style="width:100%;height:100%">
	<div data-options="region:'center',border:true">
		    <form  id = "addForm" action="<%=basePath %>show.addStudent.do" method="post">
			    <table style="margin:100px 0 0 180px;">
			    	<tr>
			    		<td>姓名：</td>
			    		<td><input name="name" type="text"></td>
			    	</tr>
			    	<tr>
			    		<td>年龄：</td>
			    		<td><input name="age" type="text"></td>
			    	</tr>
			    	<tr>
			    		<td>电话：</td>
			    		<td><input name="phone" type="text"></td>
			    	</tr>
			    	<tr>
			    		<td>地址：</td>
			    		<td><input name="address" type="text"></td>
			    	</tr>
			    </table>
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
		$("#addForm").form('submit', {    
		    success: function(){ 
		        $('#studia').dialog('close');
		        var tab = $('#tt').tabs('getSelected');  
				tab.panel('refresh'); 
		    }    
		});  

	}
</script>
