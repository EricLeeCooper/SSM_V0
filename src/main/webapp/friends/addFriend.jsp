<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<div id="addfriendList" fit="true" align="center">
	 
		<br> 
		&nbsp;姓名：<input type="text" name="username"> 
		&nbsp;地址：<input type="text" name="useraddress"> 
		<button id="buts" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="search()">查询</button> 
		<button id="butr" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="reset()">清空</button> 
		<div style="height:10px;"></div>
	<table id="addfriendsdg" class="easyui-datagrid" style="width:100%;height:165%"></table>  
</div>
<script type="text/javascript" charset="utf-8">
	function search(){
		var names = $("#tbs input:eq(0)").val();
		var address = $("#tbs input:eq(1)").val();
		$('#addfriendsdg').datagrid('load',{
              username: names,
              useraddress: address
          });
	}
	function reset(){
		$("#tbs input:eq(0)").val("");
		$("#tbs input:eq(1)").val("");
	}
	$(function(){
		$('#addfriendsdg').datagrid({    
		    url:'<%=basePath %>friends/friendsList.do?addf=addf', 
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
		        	
		        }},
		        {field:"  ",title:'操作',width:100,align:'center',
		         formatter:function(value){
		        		return "<a href='javascript:addfriend()' style='color:blue;text-decoration:none;'>添加</a>";
		        }}
		    ]],
		});  
	});
	function addfriend(){
		var obje = $("#addfriendsdg").datagrid('getSelected');
		$.ajax({
			 type:"post",
			 url: "<%=basePath %>friends/addFriend.do", 
			 data: "id="+obje.id,
			 async:false,
			 success:function(data){
			 	var datas = JSON.parse(data);
			 	if(datas.message == "yes"){
			 		addFriendTo();
			 	}else if(datas.message == "me"){
			 		$.messager.alert("提示信息","不能添加本人为好友！");
			 	}else{
			 		$.messager.alert("提示信息","你们已经是好友，不能重复添加！");
			 	}
			 	 
			 }
		});
	}
	function addFriendTo(){
		var obje = $("#addfriendsdg").datagrid('getSelected');
		$.messager.confirm('确认', '是否添加此人为好友?', function(r){
			if(r){
				$('#frdia').dialog('close');
       			var tab = $('#tt').tabs('getSelected');  
				tab.panel('refresh'); 
				$.messager.show({
							title:'提示消息',
							msg:'添加完成',
							timeout:3000,
							showType:'slide'
						});
				$.ajax({
					type:"post",
	 				url: '<%=basePath %>friends/addFriendToMe.do', 
	 				data: "id="+obje.id,
	 				async:false,
	 				success:function(){
	 					console.info("success");
	 				}
				});
			}
		});
 		
	}
</script>