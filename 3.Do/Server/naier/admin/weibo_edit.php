<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<?php 
	$id = $_REQUEST["id"];
	$sql = "select * from weibo where id=$id";
	$result = mysql_query($sql,$con);
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
<body nav="微博编辑页面" class="iframebody">
	<form id="aform" action="./weibo_service.php" method="post" >
	<input type="hidden" name="action" value="edit" />
	<input type="hidden" name="id" value="<?php echo $info["id"]?>" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>微博信息</legend>
	    <ul class="myform">
		 	<li class="one"><span class="myinfo">微博名：</span><input value="<?php echo $info["name"]?>" type="text" class="easyui-validatebox" data-options="required:true" name="name"/><span class="red">*</span></li>
		 	<li class="one"><span class="myinfo">KEY：</span><input value="<?php echo $info["appkey"]?>" type="text"  name="appkey"/></li>
		 	<li class="one"><span class="myinfo">密码：</span><input value="<?php echo $info["appsecret"]?>" type="text"  name="appsecret"/> </li>
		 	<li class="one"><span class="myinfo">推广网站：</span><input value="<?php echo $info["redirecturl"]?>" type="text"  name="redirecturl"/> </li>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>