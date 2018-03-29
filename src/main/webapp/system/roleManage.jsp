<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<div id="studentList" fit="true">
<div style="height:100%;width:100%">
	<div id="tbs" style="height:10%;width:100%">
			<br>
			&nbsp;姓名：<input type="text" name="username"/>
			&nbsp;地址：<input type="text" name="useraddress"/>
			<button id="buts" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="search()">查询</button>
			<button id="butr" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="reset()">清空</button>
		</div>
	<table id="roledg" class="easyui-datagrid" style="width:100%;height:90%"></table>  
</div>
</div>
<div id = "roledia"></div>
<script type="text/javascript" charset="utf-8">
function search(){
	var names = $("#tbs input:eq(0)").val();
	var address = $("#tbs input:eq(1)").val();
		$('#roledg').datagrid('load',{
	          username: names,
	          useraddress: address
	      });
	}
	function reset(){
		$("#tbs input:eq(0)").val("");
		$("#tbs input:eq(1)").val("");
	}
	$(function(){
		$('#roledg').datagrid({    
		    url:'<%=basePath %>system/roleList.do', 
		    border:false, 
		    pagination:true,
		    singleSelect:false,
		    rownumbers:true,
		    pageSize:10,
		    pageList:[10,20,30],
		    fitColums:true,
		    nowrap:true,
		    columns:[[ 
		    	{field:"ids",title:'序号',width:30,align:'center',checkbox:true},  
		        {field:"id",title:'编号',width:100,align:'center'},    
		        {field:"rname",title:'角色名称',width:100,align:'center', 
		        formatter:function(value){
		        	if( value == ""){
		        		return '空';
		        	}else{
		        		return value;
		        	}
		        }},    
		        {field:"rcode",title:'角色编号',width:100,align:'center'},
		        {field:"rlevel",title:'角色等级',width:100,align:'center'}
		    ]], 
			 toolbar: [{
			 	text:'增加',
				iconCls: 'icon-add',
				handler: function(){
					$('#roledia').dialog({
						title: "添加角色信息",    
					    width: 600,    
					    height: 400,    
					    closed: false,    
					    cache: false,  
					    href: 'system/addRole.jsp',    
					    modal: true ,
					});
				}
			},'-',{
				text:'删除',
				iconCls: 'icon-remove',
				handler: function(){
					var obje = $("#roledg").datagrid('getSelected');
					if(obje == null){
						$.messager.alert("错误","未选择数据");
					}else{
						console.info(obje.id);
						$.ajax({
						 type:"post",
						 url: "<%=basePath %>system/deleteRole.do", 
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
				text:'编辑',
				iconCls: 'icon-edit',
				handler: function(){
					var objes = $("#roledg").datagrid('getSelections');
					if(objes.length > 1){
						$.messager.alert("错误","只能编辑单条数据！");
					}else if(objes.length == 0){
						$.messager.alert("错误","未选择数据！");
					}else{
						var obje = $("#roledg").datagrid('getSelected');
						console.info(objes);
						console.info(obje.id);
						$.ajax({
						 url: "<%=basePath %>system/updateRole.do", 
						 data: "id="+obje.id,
						 async:false,
						 type:"POST",
						 dateType:"JSON",	
						 success:function(datas){
						 	console.info(datas.name);
						 	$('#roledia').dialog({
								title: "修改角色信息",    
							    width: 600,    
							    height: 400,    
							    closed: false,    
							    cache: false,  
							    href: 'system/updateRole.jsp',    
							    modal: true ,
							    onLoad:function(){
							    	$("input[name='id']").val(datas.id);
							    	$("input[name='name']").val(datas.name);
							    	$("input[name='age']").val(datas.age);
							    	$("input[name='phone']").val(datas.phone);
							    	$("input[name='address']").val(datas.address);
							    }
							}); 
						 }					 
						});
					}
				}
			},'-',{
				text:'分配权限',
				iconCls: 'icon-edit',
				handler: function(){
					var objes = $("#roledg").datagrid('getSelected');
					if(objes.length > 1){
						$.messager.alert("错误","只能选择单条数据！");
					}else if(objes.length == 0){
						$.messager.alert("错误","未选择数据！");
					}else{
						$('#roledia').dialog({
							title: "权限分配信息",    
						    width: 800,    
						    height: 400,    
						    closed: false,    
						    cache: false,  
						    href: 'system/role_perManage.jsp?id='+objes.id  ,
						    modal: true 
						}); 
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