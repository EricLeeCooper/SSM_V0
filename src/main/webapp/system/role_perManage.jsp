<%@page import="com.sun.research.ws.wadl.Request"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String mid = request.getParameter("id");
%>
<%@include file="/js/publicUtil.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<div class="easyui-layout" id="studentList" fit="true">
	<div data-options="region:'center',border:true" style="height:85%;width:100%">
		<table id="menuTree"></table>  
	</div>
	<div data-options="region:'south',border:true" >
		<div id="dlg-buttons" style="float:right;">
			<a class="easyui-linkbutton" iconCls="icon-ok" onclick="addPers()">确定</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeDialog('roledia')">取消</a>
		</div>
	</div>  
</div>
<div id = "studia"></div>
<script type="text/javascript" charset="utf-8">
	var mid = <%=mid%>;
	$(function() {  
		console.info(mid+"-------------");
	    $("#menuTree").treegrid({  
           idField : 'id',  
           treeField : 'pname',  
           rownumbers : true,  
           checkbox:true,
          //首次查询参数           
           columns : [ [ {  
               field : "pname",  
               title : "名称",  
               width : 200  
           }, {  
               field : "url",  
               title : "链接",  
               width : 300  
           },{  
               field : "description",  
               title : "描述",  
               width : 200  
           }, {  
               field : "createtime",  
               title : "创建时间",  
               width : 100  
           }, {  
               field : "icon",  
               title : "图标地址",  
               width : 100  
           } ] ]
    	}); 
	    loadRootPer();
	});  
	
	function loadRootPer(){
		  $.ajax({
				 url: "<%=basePath %>system/getRootPermission.do", 
				 async:false,
				 type:"POST",
				 dateType:"JSON",	
				 success:function(datas){
					obj = JSON.parse(datas);
				 	 $("#menuTree").treegrid({
				 		 data:[{
				 			 id:obj.id,
				 			 pname:obj.pname,
				 			 url:obj.url,
				 			 state:'open',
				 			 lines:true
				 		 }]
				 	 });
				    loadChildren(obj.id);
				 }					 
			});
	}
	function loadChildren(pid){
		 $.ajax({
			 url: "<%=basePath %>system/getChildrenPers.do?id="+pid, 
			 async:false,
			 type:"POST",
			 dateType:"JSON",	
			 contentType:'application/x-www-form-urlencoded; charset=UTF-8',
			 success:function(datas){
				var obj = JSON.parse(datas).value;
				$.each(obj,function(i){
					if(obj[i].per_flag == "open"){
						 $("#menuTree").treegrid('append',{
							 parent:pid,
					 		 data:[{
					 			 id:obj[i].per_id,
					 			 pname:obj[i].per_name,
					 			 url:obj[i].per_url,
					 			 state:'open',
					 			 lines:true
					 		 }]
					 	 });
					}else{
						 $("#menuTree").treegrid('append',{
							 parent:pid,
					 		 data:[{
					 			 id:obj[i].per_id,
					 			 pname:obj[i].per_name,
					 			 url:obj[i].per_url,
					 			 state:'open',
					 			 lines:true
					 		 }]
					 	 });
						 loadChildren(obj[i].per_id);
					}
				});
			 }					 
		});
		setSelected();
	}
	function setSelected(){
		 $.ajax({
			 url: "<%=basePath %>system/getPersonalPers.do?id="+mid, 
			 async:false,
			 type:"POST",
			 dateType:"JSON",	
			 contentType:'application/x-www-form-urlencoded; charset=UTF-8',
			 success:function(datas){
				var obj = JSON.parse(datas).value;
				$.each(obj,function(i){
					var nodesAll = $("#menuTree").treegrid("getPanel").find(".tree-checkbox"); 
					$.each(nodesAll,function(j){
						var key = $($(nodesAll[j]).closest('tr')[0]).attr("node-id");
						if(obj[i].per_id == key){
							$($(nodesAll[j]).closest('span')).attr("class","tree-checkbox tree-checkbox1");
						}
					});
				});		
			 }
		});
	}
	function addPers(){
		var ids = "";
	    var nodes = $("#menuTree").treegrid("getPanel").find(".tree-checkbox1"); 
	    $.each(nodes,function(j){
			var key = $($(nodes[j]).closest('tr')[0]).attr("node-id");
			ids+=key+",";
		});
	    $.ajax({
		   url: "<%=basePath %>system/addPersonalPers.do?id="+mid+"&ids="+ids, 
		   async:false,
		   type:"POST",
		   dateType:"JSON",	
		   contentType:'application/x-www-form-urlencoded; charset=UTF-8',
		   success:function(datas){
			   $.messager.show({
					title:'提示消息',
					msg:JSON.parse(datas).title,
					timeout:3000,
					showType:'slide'
				}); 
			   closeDialog('roledia')
		        var tab = $('#tt').tabs('getSelected');  
				tab.panel('refresh'); 
				$('#studia').dialog('close');
		  }
	  });
	}
</script>