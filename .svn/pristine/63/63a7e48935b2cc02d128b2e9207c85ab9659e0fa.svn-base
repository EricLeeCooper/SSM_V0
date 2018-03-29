<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div>
	请选择你要转给的好友：
	<select name="username">
		<option>-请选择-</option>
	</select><br>
	请输入要转账的金额数：<input type="text" name="money"><br><br>
	
	<button id="butsub">确定</button>
	<button id="butcan">取消</button>
	
</div>
<script type="text/javascript">
	$("#butsub").click(function(){
        var userId = $("select[name='username']").val();
        var money = $("input[name='money']").val();
        var mmoney = $("label:eq(2)").val();
        if(mmoney<money){
        	$.messager.alert("请输入小于"+mmoney+"的数额！");
        }else{
			$.ajax({
				type:"POST",
				dateType:"JSON",
				data:"userId="+	userId+"&money="+money,
			    url:"<%=basePath %>show.translate.do" ,
			    async:false,
				success:function(datas){
					var data = JSON.parse(datas);
					console.info("lalala");
					$.messager.show({
						title:'消息提醒',
						msg:'转账结果：'+data.message,
						timeout:2000,
						showType:'slide',
						style:{
								right:'',
								top:document.body.scrollTop+document.documentElement.scrollTop,
								bottom:''
						}
					});
					$('#dial').dialog('close');
	        		var tab = $('#tt').tabs('getSelected');
					tab.panel('refresh'); 
				}
			});
		}
	});
	$("#butcan").click(function (){
		$('#dial').dialog('close');
	});
	$(function(){
		$.ajax({
			type:"POST",
			dateType:"JSON",	
		    url:"<%=basePath %>translate.do" ,
		    async:false,
			success:function(datas){
				var data = JSON.parse(datas);
				console.info("123");
				$.each(data,function(i){
					$("select").append("<option value='"+data[i].fid+"'>"+data[i].username+"</option>");
				});
			}
		});
	});
</script>