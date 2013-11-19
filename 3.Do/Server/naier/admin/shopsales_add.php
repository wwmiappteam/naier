<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<script type="text/javascript" >
$(document).ready(function(){
	var editor1 = new UE.ui.Editor();
	editor1.render("intro");
	var editor2 = new UE.ui.Editor();
	editor2.render("shareContent");
});
function sForm(){
	//校验表单
	if($("#aform").form('validate')){
		//同步编辑器中内容
		UE.getEditor('intro').sync();
    	$("#aform").submit();
	}
}
</script>
</head>
<body nav="活动添加页面" class="iframebody">
	<form id="aform" action="./shopsales_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="add" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>活动信息</legend>
	    <ul class="myform">
		 	<li class="one"><span class="myinfo">活动标题：</span><input type="text" class="easyui-validatebox" data-options="required:true" name="title"/><span class="red">*</span></li>
		 	<li class="one"><span class="myinfo">活动图片：</span><input type="file" name="logo" title="活动图片宽高比为4:3" class="myfile"/> </li>
		 	<li class="one"><span class="myinfo">海报图片：</span><input type="file" name="poster" title="海报图片宽高比为2:1" class="myfile"/> </li>
		 	<li class="three1"><span class="myinfo">活动介绍：</span></li>
		 	<li class="three"><textarea name="intro" id="intro"></textarea></li>
		 	<li class="three1"><span class="myinfo" style="width:105px;">微博分享内容：</span></li>
		 	<li class="three"><textarea name="shareContent" id="shareContent"></textarea></li>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>