<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<?php 
	$id= $_REQUEST["id"];
	$sql = "select * from sys_user where id=$id";
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
function bForm(){
	//校验表单
		//同步编辑器中内容
    	$("#bform").submit();
}
</script>
</head>
<body nav="管理员添加页面" class="iframebody">
	<form id="aform" action="./admin_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="edit" />
	<input type="hidden" name="id" value="<?php echo $info['id']; ?>" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>管理员信息</legend>
	    <ul class="myform">
		 	<li class="one"><span class="myinfo">用户名：</span><input type="text"  value='<?php echo $info['login'];?>' disabled="disabled" /> </li>
		 	<li class="one"><span class="myinfo">姓名：</span><input value="<?php echo $info['name'];?>" type="text"  name="name"/> </li>
		 	<li class="one"><span class="myinfo">电话：</span><input value="<?php echo $info['phone'];?>" type="text"  name="phone"/> </li>
		 	<li class="one"><span class="myinfo">QQ：</span><input value="<?php echo $info['qq'];?>" type="text"  name="qq"/> </li>
		 	<li class="one"><span class="myinfo">备注：</span><input value="<?php echo $info['note'];?>" type="text"  name="note"/> </li>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
	<form id="bform" action="./admin_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="resetpwd" />
	<input type="hidden" name="id" value="<?php echo $info['id']; ?>" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>重置密码</legend>
	    <ul class="myform">
		 	<li class="one"><span class="myinfo">密码：</span><input type="password"  name="password"/> </li>
		 	<li class="button">
		 		<a onclick="bForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>