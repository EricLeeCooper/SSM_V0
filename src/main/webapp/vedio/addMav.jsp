<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<div class="easyui-layout" style="width:100%;height:100%">
	<div data-options="region:'center',border:true">
		<form  id = "addForm" method="post" enctype="multipart/form-data">
			<table style="margin:5px;height:70px;" id = "center_table">
	            <tr>
	                <td>请选择文件：</td>
	                <td width="5px;"></td>
	                <td><input type="file" name="file" style="width:260px;"></td>
	                <td></td></tr>
	            <tr>
	                <td colspan="4"><label id="fileName" /></td>
	            </tr>
	            <tr>
	                <td colspan="4"><label id="uploadInfo" /></td>
	            </tr>
	        </table>
	        <!-- <div id='progressbar' class='easyui-progressbar' style='width:400px;'></div> -->
	        <div style="text-align:center;clear:both;margin:5px;">
	            <a id="uploadFile" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addMAV()">上传</a>
	            <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" data-bind="click:closeImportClick" onclick="cancle()">取消</a>
	        </div>
		</form>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		$("#center_table").css({"margin-left":"130px","margin-top":"60px"});
	});
	function addMAV(){
		var filename = $("input[type='file']").val();
		if(filename != undefined && filename != ""){
			$("#addForm").append("<div id='progressbar' class='easyui-progressbar' data-options='value:60' style='width:400px;'></div>");
			$("#progressbar").css("margin","30px auto"); 
			uploadFile();
			/* $("#addForm").form('submit', {    
			    success: function(){ 
			        $('#mavsdia').dialog('close');
			        var tab = $('#tt').tabs('getSelected');  
					tab.panel('refresh'); 
			    }    
			});   */
		}else{
			alert("请先选择要上传的文件！");
		}
	}
	function uploadFile() { 
      var fileObj = $("input[type='file']").get(0).files[0]; // js 获取文件对象 
      console.info("上传的文件："+fileObj); 
      var FileController = "<%=basePath %>vedio/addMAV.do"; // 接收上传文件的后台地址  
      // FormData 对象 
      var form = new FormData(); 
      // form.append("author", "hooyes"); // 可以增加表单数据 
      form.append("file", fileObj); // 文件对象 
      // XMLHttpRequest 对象 
      var xhr = new XMLHttpRequest(); 
      xhr.open("post", FileController, true); 
      xhr.onload = function() { 
        $("#progressbar").progressbar({'value': 100});
        $.messager.confirm('完成提示','上传完成，是否继续上传？',function(r){
        	if(r){
        		var tab = $('#mavsdia');  
				tab.panel('refresh');
        	}else{
        		$('#mavsdia').dialog('close');
			    var tab = $('#tt').tabs('getSelected');  
				tab.panel('refresh');
        	}
        });
      }; 
      xhr.upload.addEventListener("progress", progressFunction, false); 
      xhr.send(form); 
    } 
     
    function progressFunction(evt) { 
      if (evt.lengthComputable) { 
        var value = evt.loaded / evt.total * 100;
        console.info(evt.loaded / evt.total * 100);
		if (evt.loaded < evt.total){
		    value = Math.round(evt.loaded / evt.total * 100);
		    $("#progressbar").progressbar({'value': value});
		}
      } 
    }; 
    
    function cancle(){
    	$('#mavsdia').dialog('close');
        var tab = $('#tt').tabs('getSelected');  
		tab.panel('refresh'); 
    }
</script>
