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
		&nbsp;用户名称：<input type="text" name="name"/>
		&nbsp;用户角色：<input type="text" name="ident"/>
		<button id="buts" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="search()">查询</button>
		<button id="butr" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="reset()">清空</button>
	</div>
	<table id="udg" class="easyui-datagrid" style="width:100%;height:90%"></table>  
</div>
</div>
<div id = "studia"></div>
<script type="text/javascript" charset="utf-8">
	function search(){
		var names = $("#tbs input:eq(0)").val();
		var ident = $("#tbs input:eq(1)").val();
		$('#udg').datagrid('load',{
              name: names,
              ident: ident
          });
	}
	function reset(){
		$("#tbs input:eq(0)").val("");
		$("#tbs input:eq(1)").val("");
	}
	$(function(){
		$('#udg').datagrid({    
		    url:'<%=basePath %>system/userList.do', 
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
		        {field:"uname",title:'用户名',width:100,align:'center', 
		        formatter:function(value){
		        	if( value == ""){
		        		return '空';
		        	}else{
		        		return value;
		        	}
		        }},    
		        {field:"name",title:'姓名',width:100,align:'center', 
		        formatter:function(value){
		        	if( value == ""){
		        		return '空';
		        	}else{
		        		return value;
		        	}
		        }}, 
		        {field:"address",title:'地址',width:100,align:'center', 
			        formatter:function(value){
			        	if( value == ""){
			        		return '未填写';
			        	}else{
			        		return value;
			        	}
		       	}},
		        {field:"email",title:'邮箱',width:100,align:'center'},
		        {field:"phone",title:'电话',width:100,align:'center'},
		        {field:"address",title:'地址',width:100,align:'center', 
		        formatter:function(value){
		        	if( value == ""){
		        		return '未填写';
		        	}else{
		        		return value;
		        	}
		        }
		        },
		        {field:"password",title:'密码',width:100,align:'center', 
		        formatter:function(value){
		        	if( value == ""){
		        		return '未填写';
		        	}else{
		        		return value;
		        	}
		        }
		        }
		    ]], 
			 toolbar: [{
			 	text:'增加',
				iconCls: 'icon-add',
				handler: function(){
					$('#studia').dialog({
						title: "添加学生信息",    
					    width: 600,    
					    height: 400,    
					    closed: false,    
					    cache: false,  
					    href: 'student/addStudent.jsp',    
					    modal: true ,
					});
				}
			},'-',{
				text:'删除',
				iconCls: 'icon-remove',
				handler: function(){
					var obje = $("#dg").datagrid('getSelected');
					if(obje == null){
						$.messager.alert("错误","未选择数据");
					}else{
						console.info(obje.id);
						$.ajax({
						 type:"post",
						 url: "<%=basePath %>show.deleteStudent.do", 
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
					var objes = $("#dg").datagrid('getSelections');
					if(objes.length > 1){
						$.messager.alert("错误","只能编辑单条数据！");
					}else if(objes.length == 0){
						$.messager.alert("错误","未选择数据！");
					}else{
						var obje = $("#dg").datagrid('getSelected');
						console.info(objes);
						console.info(obje.id);
						$.ajax({
						 url: "<%=basePath %>show.updateStudent.do", 
						 data: "id="+obje.id,
						 async:false,
						 type:"POST",
						 dateType:"JSON",	
						 success:function(datas){
						 	console.info(datas.name);
						 	$('#studia').dialog({
								title: "修改学生信息",    
							    width: 600,    
							    height: 400,    
							    closed: false,    
							    cache: false,  
							    href: 'student/updateStudent.jsp',    
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
			    text:'导出',
				iconCls: 'icon-edit',
				handler: function(){
					var objes = $("#dg").datagrid('getSelections');
					var enlen=objes.length;
					if(enlen==0){
						$.messager.alert('错误','没有选择要导出的数据！');
						return false;
					}else{
						$.messager.confirm('导出数据','是否确认导出?',function(r){
							if(r){
								var ids = ""; 
								$.each(objes,function(i){
									console.info(objes[i]);
									ids +=  objes[i].id+",";
								});
								console.info(ids);
								ids = ids.slice(0,-1);
								console.info(ids);
								window.location.href="<%=basePath %>show/exportStudent.do?ids="+ids;
							}
						});
					}
				}
			},'-',{
			    text:'导入',
				iconCls: 'icon-edit',
				handler: function(){
					$('#studia').dialog({
						title: "导入学生信息",    
					    width: 600,    
					    height: 400,    
					    closed: false,    
					    cache: false,  
					    href: 'student/uploadStudent.jsp',    
					    modal: true ,
					});
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