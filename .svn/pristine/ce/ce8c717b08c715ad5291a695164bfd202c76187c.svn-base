<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div id="content_score" >
	<table id="scoredg" class="easyui-datagrid" style="width:100%;height:340%">
		
	</table>
</div>
<script type="text/javascript">
	$(function(){
		$('#scoredg').datagrid({    
		    url:'<%=basePath %>show.scoreList.do', 
		    border:false, 
		    pagination:true,
		    singleSelect:true,
		    pageSize:10,
		    pageList:[10,20,30],
		    fitColums:true,
		    nowrap:true,
		    columns:[[    
		         {field:'index',title:'序号',width:100, align: 'center',formatter:function(val,row,index){
				     var options = $("#scoredg").datagrid('getPager').data("pagination").options; 
				     var currentPage = options.pageNumber;
				     var pageSize = options.pageSize;
				     return (pageSize * (currentPage -1))+(index+1);
			    }},    
		        {field:"userName",title:'姓名',width:100,align:'center'},    
		        {field:"score",title:'分数',width:100,align:'center'},
		        {field:"time",title:'时间',width:100,align:'center'},
		        {field:"charts ",title:'排行',width:100,align:'center'},
		    ]]
		});
	});
</script>