<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<div id="friendsList" fit="true">
	<div id="tbs" style="height:15%;width:100%">
		<br>
		&nbsp;姓名：<input type="text" name="username"/>
		&nbsp;地址：<input type="text" name="useraddress"/>
		<button id="buts" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="search()">查询</button>
		<button id="butr" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="reset()">清空</button>
	</div>
	<table id="friendsdg" class="easyui-datagrid" style="width:100%;height:143%"></table>  
</div>
<div id = "frdia"></div>
<script type="text/javascript" charset="utf-8">
	function search(){
		var names = $("#tbs input:eq(0)").val();
		var address = $("#tbs input:eq(1)").val();
		$('#dg').datagrid('load',{
              username: names,
              useraddress: address
          });
	}
	function reset(){
		$("#tbs input:eq(0)").val("");
		$("#tbs input:eq(1)").val("");
	}
	$(function(){
		$('#friendsdg').datagrid({    
		    url:'<%=basePath %>friends/friendsList.do', 
		    border:false, 
		    pagination:true,
		    singleSelect:true,
		    pageSize:10,
		    pageList:[10,20,30],
		    fitColums:true,
		    nowrap:true,
		    columns:[[    
		        {field:"id",title:'编号',width:100,align:'center'},    
		        {field:"name",title:'姓名',width:100,align:'center', 
		        formatter:function(value){
		        	if( value == ""){
		        		return '空';
		        	}else{
		        		return value;
		        	}
		        	
		        }},    
		        {field:"age",title:'年龄',width:100,align:'center'},
		        {field:"phone",title:'电话',width:100,align:'center'},
		        {field:"address",title:'地址',width:100,align:'center', 
		        formatter:function(value){
		        	if( value == ""){
		        		return '未填写';
		        	}else{
		        		return value;
		        	}
		        	
		        }}
		    ]], 
			 toolbar: [{
			 	text:'增加',
				iconCls: 'icon-add',
				handler: function(){
					$('#frdia').dialog({
						title: "添加朋友信息",    
					    width: 650,    
					    height: 400,    
					    closed: false,    
					    cache: false,  
					    href: 'friends/addFriend.jsp',    
					    modal: true ,
					});
				}
			},'-',{
				text:'删除',
				iconCls: 'icon-remove',
				handler: function(){
					var obje = $("#friendsdg").datagrid('getSelected');
					if(obje == null){
						$.messager.alert("错误","未选择数据");
					}else{
						console.info(obje.id);
						$.ajax({
						 type:"post",
						 url: "<%=basePath %>friends/deleteFriend.do", 
						 data: "id="+obje.id,
						 async:false
						});
						$.messager.show({
							title:'提示消息',
							msg:'删除完成',
							timeout:3000,
							showType:'slide'
						});
						
						var tab = $('#tt').tabs('getSelected');  
						tab.panel('refresh'); 
					}
				}
			},'-',{
			    text:'帮助',
				iconCls: 'icon-help',
				handler: function(){

				}
			}], 
		});  
	});
</script>