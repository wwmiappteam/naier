<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<script type="text/javascript" >
$(document).ready(function(){
	var editor2 = new UE.ui.Editor();
	editor2.render("active_description");
});
function sForm(){
	//校验表单
	if($("#shopgoodsform").form('validate')){
		UE.getEditor('active_description').sync();
		//同步编辑器中内容
    	$("#shopgoodsform").submit();
	}
}
</script>
</head>
<body nav="新闻活动信息添加页面" class="iframebody">
	<form id="shopgoodsform" action="./active_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="add" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>新闻活动信息</legend>
	    <ul class="myform">
		 	<li class="one"><span class="myinfo">活动标题：</span><input type="text" class="easyui-validatebox" data-options="required:true" name="active_title"/><span class="red">*</span></li>
		 	<li class="one"><span class="myinfo">活动图片：</span><input type="file" name="active_poster" class="myfile"/></li>
		 	<li class="one"><span class="myinfo">开始时间：</span><input type="text" name="active_start"/></li>
		 	<li class="one"><span class="myinfo">结束时间：</span><input type="text" name="active_end"/></li>
		 	<li class="one"><span class="myinfo">电话：</span><input type="text" name="active_tel"/></li>
		 	<li class="three"><span class="myinfo">活动描述：</span></li>
		 	<li class="three"><textarea name="active_description" id="active_description"></textarea>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>