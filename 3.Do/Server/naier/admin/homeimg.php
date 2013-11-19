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
<body nav="首页图片页面" class="iframebody">
	<form id="aform" action="./homeimg_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="edit" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>首页图片信息</legend>
	    <ul class="myform">
	    	<li>
	    	<?php 
				$sql = "select * from img_resource where imgtype='1' ";
				$result = mysql_query($sql,$con);
				while($img = mysql_fetch_assoc($result)){
			?>
	    		<img  width="105px" height="75px" src="<?php echo $base.$img["imgsrc"];?>" /> <a href="./homeimg_service.php?action=delete&id=<?php echo $img["id"]?>">删除图片 </a> 
	    		<?php }?>
	    	</li>
	    	<hr/>
		 	<li class="one"><span class="myinfo">首页图片：</span><input  id="file" type="file" name="img" class="myfile"/> </li>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>