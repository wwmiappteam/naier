<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php 
	include_once 'common.php'; 
	$pid = $_REQUEST["pid"];
	if($pid==null&&$pid==""){
		$pid = 1;
	}
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
<body nav="分类添加页面" class="iframebody">
	<form id="form" action="./shopcategory_service.php" method="post" >
	<input type="hidden" name="pid" value="<?php echo $pid;?>" />
	<input type="hidden" name="action" value="add" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>分类信息</legend>
	    <ul class="myform">
		 	<li class="one"><span class="myinfo">分类名：</span><input type="text" class="easyui-validatebox" data-options="required:true" name="cat"/><span class="red">*</span></li>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>