<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<?php 
	$sql = "select * from keeper_type ";
	$result = mysql_query($sql,$con);
?>
<script type="text/javascript" >
$(document).ready(function(){
});
function sForm(){
	//校验表单
	//同步编辑器中内容
    $("#aform").submit();
}
function showfile(obj,imgid){
	$(obj).next().show().removeAttr("disabled");
	$(obj).hide();
	$("#"+imgid).hide();
}
function editcat(){
	$("#cid").show();
	$("#mya").remove();
	$("input[name=changec]").remove();
}
</script>
</head>
<body nav="人员类型页面" class="iframebody">
	<form id="aform" action="./keeper_type_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="edit" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>类型信息</legend>
	    <ul class="myform">
	    <?php $index=1; while($row=mysql_fetch_assoc($result)){?>
		 	<li class="three"><span class="myinfo">类型名：</span><?php echo $row['type_name'];?></li>
		 	<li class="three"><span class="myinfo">类型描述：</span><input value="<?php echo $row['type_description']?>" type="text" style="width:600px;" name="type_description<?php echo $index;$index++;?>"/></li>
	    <?php }?>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>