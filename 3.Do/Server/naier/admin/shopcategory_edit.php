<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php 
	include_once 'common.php'; 
	$id = $_REQUEST["id"];
	$sql = "select * from shop_category where cid=$id";
	$result = mysql_query($sql);
	$cat = mysql_fetch_assoc($result);
?>
<script type="text/javascript" >
$(document).ready(function(){
	
});
function sForm(){
	//校验表单
	if($("#form").form('validate')){
    	$("#form").submit();
	}
}
</script>
</head>
<body nav="分类编辑页面" class="iframebody">
	<form id="form" action="./shopcategory_service.php" method="post" >
	<input type="hidden" name="id" value="<?php echo $id;?>" />
	<input type="hidden" name="action" value="edit" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>分类信息</legend>
	    <ul class="myform">
		 	<li class="one"><span class="myinfo">分类名：</span><input value="<?php echo $cat["cat"];?>" type="text" class="easyui-validatebox" data-options="required:true" name="cat"/><span class="red">*</span></li>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>