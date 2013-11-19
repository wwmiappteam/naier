<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<?php 
	$id = $_REQUEST["id"];
	$sql = "select * from business where id=$id";
	$result = mysql_query($sql,$con);
	$info = mysql_fetch_assoc($result);
?>
<script type="text/javascript" >
$(document).ready(function(){
	var editor2 = new UE.ui.Editor();
	editor2.render("busi_introduce");
});
function sForm(){
	//校验表单
	if($("#shopgoodsform").form('validate')){
		UE.getEditor('busi_introduce').sync();
		//同步编辑器中内容
    	$("#shopgoodsform").submit();
	}
}
function showfile(obj,imgid){
	$(obj).next().show().removeAttr("disabled");
	$(obj).hide();
	$("#"+imgid).hide();
}
</script>
</head>
<body nav="业务页面" class="iframebody">
	<form id="shopgoodsform" action="./business_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="edit" />
	<input type="hidden" name="id" value="<?php echo $info["id"]?>" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>业务信息</legend>
	    <ul class="myform">
		 	<li class="one"><span class="myinfo">标题：</span><input type="text" class="easyui-validatebox" data-options="required:true" value="<?php echo $info['busi_title'];?>" name="busi_title"/><span class="red">*</span></li>
		 	<li class="one"><span class="myinfo">业务价格：</span><input type="text" value="<?php echo $info['busi_price'];?>" name="busi_price"/></li>
		 	<li class="three"><span class="myinfo">业务介绍：</span></li>
		 	<li class="three"><textarea name="busi_introduce" id="busi_introduce"><?php echo $info['busi_introduce'];?></textarea>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>