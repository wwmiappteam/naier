<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<script type="text/javascript" >
$(document).ready(function(){
});
function sForm(){
	//校验表单
	if($("#shopgoodsform").form('validate')){
		//同步编辑器中内容
    	$("#shopgoodsform").submit();
	}
}
</script>
</head>
<body nav="门市信息添加页面" class="iframebody">
	<form id="shopgoodsform" action="./shop_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="add" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>门市信息</legend>
	    <ul class="myform">
		 	<li class="one"><span class="myinfo">门市名称：</span><input type="text" class="easyui-validatebox" data-options="required:true" name="shop_name"/><span class="red">*</span></li>
		 	<li class="one"><span class="myinfo">电话：</span><input type="text" name="shop_tel"/></li>
		 	<li class="one"><span class="myinfo">地址：</span><input type="text" name="shop_address"/></li>
		 	<li class="one"><span class="myinfo">百度经度：</span><input type="text" name="baidu_longitude"/></li>
		 	<li class="one"><span class="myinfo">百度纬度：</span><input type="text" name="baidu_latitude"/></li>
		 	<li class="one"><span class="myinfo">谷歌经度：</span><input type="text" name="google_longitude"/></li>
		 	<li class="one"><span class="myinfo">谷歌纬度：</span><input type="text" name="google_latitude"/></li>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>