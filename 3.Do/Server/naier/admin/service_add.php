<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<script type="text/javascript" >
$(document).ready(function(){
	var editor2 = new UE.ui.Editor();
	editor2.render("serv_introduce");
});
function sForm(){
	//校验表单
	if($("#shopgoodsform").form('validate')){
		UE.getEditor('serv_introduce').sync();
		//同步编辑器中内容
    	$("#shopgoodsform").submit();
	}
}
</script>
</head>
<body nav="服务信息添加页面" class="iframebody">
	<form id="shopgoodsform" action="./service_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="add" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>服务信息</legend>
	    <ul class="myform">
		 	<li class="one"><span class="myinfo">标题：</span><input type="text" class="easyui-validatebox" data-options="required:true" name="serv_title"/><span class="red">*</span></li>
		 	<li class="three"><span class="myinfo">服务介绍：</span></li>
		 	<li class="three"><textarea name="serv_introduce" id="serv_introduce"></textarea>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>