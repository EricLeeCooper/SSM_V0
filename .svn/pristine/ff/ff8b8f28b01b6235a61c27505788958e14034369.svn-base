<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div>
	<form id="pform" action="<%=basePath %>newPicture.do" method="post" enctype="multipart/form-data">
		<input type="file" name="file"/><br><br>
		<a class="easyui-linkbutton"  onclick="submitPic()">提交</a>	
		<a class="easyui-linkbutton" onclick="cancle()">取消</a>
	</form>
</div>
<script type="text/javascript">
	function submitPic(){
			$("#pform").form('submit',{
				success:function(){
				 	$('#dial').dialog('close');
		        	var tab = $('#tt').tabs('getSelected');
					tab.panel('refresh'); 
					$.messager.show({
						title:'消息提醒',
						msg:'图片上传成功',
						timeout:1000,
						showType:'slide',
						style:{
							right:'',
							top:document.body.scrollTop+document.documentElement.scrollTop,
							bottom:'',
					}
					});
				}
			});
		}
	function cancle(){
		$('#dial').dialog('close');
	}
</script>