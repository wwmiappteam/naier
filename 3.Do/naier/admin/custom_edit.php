<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<?php 
	$id = $_REQUEST["id"];
	$sql = "select * from member where memberid=$id";
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
<body nav="会员编辑页面" class="iframebody">
	<form id="aform" action="./member_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="edit" />
	<input type="hidden" name="id" value="<?php echo $info["memberid"];?>" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>会员信息</legend>
	    <ul class="myform">
		 	<li class="one"><span class="myinfo">会员帐号：</span><input value="<?php echo $info["mobilenum"];?>" type="text" class="easyui-validatebox" data-options="required:true" name="mobilenum"/><span class="red">*</span></li>
		 	<li class="one"><span class="myinfo">姓名：</span><input value="<?php echo $info["name"];?>" type="text" name="name"/></li>
		 	<li class="one"><span class="myinfo">积分 ：</span><input value="<?php echo $info["points"];?>" type="text" name="points"/></li>
		 	<li class="one"><span class="myinfo">职业：</span><input value="<?php echo $info["work"];?>" type="text" name="work"/></li>
		 	<li class="one"><span class="myinfo">性别：</span><select name="sex"><option <?php if($info["sex"]=="0"){echo "selected=selected";}?> value="0">男</option><option <?php if($info["sex"]=="1")echo "selected=selected";?> value="1">女</option></select></li>
		 	<li class="one"><span class="myinfo">生日：</span><input value="<?php echo $info["birthday"];?>" type="text" name="birthday"/></li>
		 	<li class="one"><span class="myinfo">住址：</span><input value="<?php echo $info["addr"];?>" type="text" name="addr"/></li>
		 	<li class="one"><span class="myinfo">电子邮箱：</span><input value="<?php echo $info["email"];?>" type="text" name="email"/></li>
		 	<li class="one"><span class="myinfo">身份证号码：</span><input value="<?php echo $info["passcode"];?>" type="text" name="passcode"/></li>
		 	<li class="one"><span class="myinfo">QQ：</span><input value="<?php echo $info["qq"];?>" type="text" name="qq"/></li>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>