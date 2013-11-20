<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<?php 
	$id = $_REQUEST["id"];
	$sql = "select * from custom where id=$id";
	$result = mysql_query($sql);
	$info = mysql_fetch_assoc($result);
?>
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
<body nav="顾客编辑页面" class="iframebody">
	<form id="aform" action="./custom_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="edit" />
	<input type="hidden" name="id" value="<?php echo $info["id"];?>" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>顾客信息</legend>
	    <ul class="myform">
		 	<li class="one"><span class="myinfo">帐号：</span><input readonly="readonly" value="<?php echo $info["custom_username"];?>" type="text" class="easyui-validatebox" data-options="required:true" name="custom_username"/></li>
		 	<li class="one"><span class="myinfo">电话：</span><input value="<?php echo $info["custom_cellphone"];?>" type="text" name="custom_cellphone"/></li>
		 	<li class="one"><span class="myinfo">密码 ：</span><input value="<?php echo $info["custom_password"];?>" type="password" name="custom_password"/></li>
		 	<li class="one"><span class="myinfo">姓名：</span><input value="<?php echo $info["custom_name"];?>" type="text" name="custom_name"/></li>
		 	<li class="one"><span class="myinfo">地址：</span><input value="<?php echo $info['custom_address'];?>" type="text" name="custom_address"/></li>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>