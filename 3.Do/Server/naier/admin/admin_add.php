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
	if($("#aform").form('validate')){
		//同步编辑器中内容
    	$("#aform").submit();
	}
}
</script>
</head>
<body nav="管理员添加页面" class="iframebody">
	<form id="aform" action="./admin_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="add" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>管理员信息</legend>
	    <ul class="myform">
		 	<li class="one"><span class="myinfo">用户名：</span><input type="text" class="easyui-validatebox" data-options="required:true" name="login" title="请注意管理员用户名必须唯一" /><span class="red">*</span></li>
		 	<li class="one"><span class="myinfo">密码：</span><input type="password"  name="password"/></li>
		 	<li class="one"><span class="myinfo">姓名：</span><input type="text"  name="name"/> </li>
		 	<li class="one"><span class="myinfo">电话：</span><input type="text"  name="phone"/> </li>
		 	<li class="one"><span class="myinfo">QQ：</span><input type="text"  name="qq"/> </li>
		 	<li class="one"><span class="myinfo">备注：</span><input type="text"  name="note"/> </li>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>