<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<?php 
	$id = $_REQUEST["id"];
	$sql = "select * from shop where id=$id";
	$result = mysql_query($sql,$con);
	$info = mysql_fetch_assoc($result);
?>
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
function showfile(obj,imgid){
	$(obj).next().show().removeAttr("disabled");
	$(obj).hide();
	$("#"+imgid).hide();
}
</script>
</head>
<body nav="门市页面" class="iframebody">
	<form id="shopgoodsform" action="./shop_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="edit" />
	<input type="hidden" name="id" value="<?php echo $info["id"]?>" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>门市信息</legend>
	     <ul class="myform">
		 	<li class="one"><span class="myinfo">门市名称：</span><input value=<?php echo $info['shop_name'];?> type="text" class="easyui-validatebox" data-options="required:true" name="shop_name"/><span class="red">*</span></li>
		 	<li class="one"><span class="myinfo">电话：</span><input value=<?php echo $info['shop_tel'];?> type="text" name="shop_tel"/></li>
		 	<li class="one"><span class="myinfo">地址：</span><input value=<?php echo $info['shop_address'];?> type="text" name="shop_address"/></li>
		 	<li class="one"><span class="myinfo">百度经度：</span><input value=<?php echo $info['baidu_longitude'];?> type="text" name="baidu_longitude"/></li>
		 	<li class="one"><span class="myinfo">百度纬度：</span><input value=<?php echo $info['baidu_latitude'];?> type="text" name="baidu_latitude"/></li>
		 	<li class="one"><span class="myinfo">谷歌经度：</span><input value=<?php echo $info['google_longitude'];?> type="text" name="google_longitude"/></li>
		 	<li class="one"><span class="myinfo">谷歌纬度：</span><input value=<?php echo $info['google_latitude'];?> type="text" name="google_latitude"/></li>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>