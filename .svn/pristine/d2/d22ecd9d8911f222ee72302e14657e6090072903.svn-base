<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<div id="mavList" fit="true">
	<div id="tbs" style="height:16%;width:100%">
		<br>
		&nbsp;名称：<input type="text" name="username"/>
		&nbsp;&nbsp;&nbsp;类型：<input type="radio" name="fileType" checked="checked" value="">全部
		&nbsp;&nbsp;<input type="radio"  name="fileType" value="视频">视频&nbsp;&nbsp;<input type="radio"  name="fileType" value="音乐">音乐
		&nbsp;&nbsp;<button id="buts" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="search()">查询</button>
		&nbsp;<button id="butr" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="reset()">重置</button>
	</div>
	<table id="mavdg" class="easyui-datagrid" style="width:100%;height:142%"></table>  
</div>
<div id = "mavdia"></div>
<div id = "mavudia"></div>
<div id = "mavsdia"></div>
<script type="text/javascript" charset="utf-8">
	function search(){
		var names = $("#tbs input:eq(0)").val();
		var filetype = $("#tbs input[name='fileType']");
		var type = null;
		$.each(filetype,function(i){
			if(filetype[i].checked){
				type = this.value;
			}
		});
		console.info(names+","+type);
		$('#mavdg').datagrid('load',{
             username: names,
             fileType: type
        }); 
	}
	function reset(){
		$("#tbs input:eq(0)").val("");
		$("#tbs input:eq(1)").val("");
	}
	var ns = null;
	$(function(){
		$('#mavdg').datagrid({    
		    url:'<%=basePath %>vedio/vedioList.do', 
		    border:false, 
		    pagination:true,
		    singleSelect:true,
		    rownumbers:true,
		    pageSize:10,
		    pageList:[10,20,30],
		    fitColums:true,
		    nowrap:true,
		    columns:[[  
		        {field:"id",title:'编号',width:100,align:'center'},    
		        {field:"name",title:'名称',width:300,align:'center', 
		        formatter:function(value){
		        	ns = value;
		        	if( value == ""){
		        		return '空';
		        	}else{
		        		return value;
		        	}
		        	
		        }},    
		        {field:"size",title:'大小',width:100,align:'center'},
		        {field:"length",title:'时长',width:100,align:'center'},
		        {field:"path",title:'路径',width:100,align:'center',hidden:'true'},
		        {field:"fileType",title:'类型',width:100,align:'center'},
		        {field:"  ",title:'操作',width:100,align:'center', 
			        formatter:function(value){
			        	return "<a style='color:blue;text-decoration:none;' href='javascript:open()'>播放</a>";
			        }}
		    ]], 
			 toolbar: [{
			 	text:'增加',
				iconCls: 'icon-add',
				handler: function(){
					$('#mavsdia').dialog({
						title: "添加文件",    
					    width: 600,    
					    height: 400,    
					    closed: false,    
					    cache: false,  
					    href: 'vedio/addMav.jsp',    
					    modal: true ,
					});
				}
			},'-',{
				text:'删除',
				iconCls: 'icon-remove',
				handler: function(){
					var obje = $("#mavdg").datagrid('getSelected');
					if(obje == null){
						$.messager.alert("错误","未选择数据");
					}else{
						console.info(obje.id);
						$.ajax({
						 type:"post",
						 url: "<%=basePath %>vedio/deleteMAV.do", 
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
					var obje = $("#mavdg").datagrid('getSelected');
					if(obje == null){
						$.messager.alert("错误","未选择数据");
					}else{
						console.info(obje.id);
						$.ajax({
						 url: "<%=basePath %>vedio/updateMAV.do", 
						 data: "id="+obje.id,
						 async:false,
						 type:"POST",
						 dateType:"JSON",	
						 success:function(datas){
						 	console.info(datas.name);
						 	$('#mavdia').dialog({
								title: "修改信息",    
							    width: 600,    
							    height: 400,    
							    closed: false,    
							    cache: false,  
							    href: 'vedio/updateMAV.jsp',    
							    modal: true ,
							    onLoad:function(){
							    	$("input[name='id']").val(datas.id);
							    	$("input[name='name']").val(datas.name);
							    	$("input[name='size']").val(datas.size);
							    	$("input[name='length']").val(datas.length);
							    	$("input[name='path']").val(datas.path);
							    }
							}); 
						 }					 
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
	function open(){
		$('#mavudia').dialog({
						title: "播放视频",    
					    width: 527,    
					    height: 345,    
					    closed: false,    
					    cache: false,  
					    href: 'vedio/showMav.jsp',    
					    modal: true ,
					});
	}
</script>