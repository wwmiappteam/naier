<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<?php 
	$id = $_REQUEST["id"];
	$sql = "select * from active where id=$id";
	$result = mysql_query($sql,$con);
	$info = mysql_fetch_assoc($result);
?>
<script type="text/javascript" >
$(document).ready(function(){
	var editor2 = new UE.ui.Editor();
	editor2.render("active_description");
});
function sForm(){
	//校验表单
	if($("#shopgoodsform").form('validate')){
		UE.getEditor('active_description').sync();
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
<body nav="新闻活动页面" class="iframebody">
	<form id="shopgoodsform" action="./active_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="edit" />
	<input type="hidden" name="id" value="<?php echo $info["id"]?>" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>新闻活动信息</legend>
	    <ul class="myform">
	    	<li>
	    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<?php if($info["active_poster"]!=""){?><span id="img0" >海报：<img  width="100px" height="50px" src="<?php echo $base.$info["active_poster"];?>" /></span><?php }?>
	    	</li>
	    	<hr/>
		 	<li class="one"><span class="myinfo">活动标题：</span><input type="text" value="<?php echo $info['active_title'];?>" class="easyui-validatebox" data-options="required:true" name="active_title"/><span class="red">*</span></li>
		 	<li class="one"><span class="myinfo">活动图片：</span><a onclick="showfile(this,'img0');" href="javascript:void(0);" >修改图片</a><input disabled="disabled" style="display:none;" type="file" name="active_poster" class="myfile"/> </li>
		 	<li class="one"><span class="myinfo">开始时间：</span><input type="text" value="<?php echo $info['active_start'];?>" name="active_start"/></li>
		 	<li class="one"><span class="myinfo">结束时间：</span><input type="text" value="<?php echo $info['active_end'];?>" name="active_end"/></li>
		 	<li class="one"><span class="myinfo">电话：</span><input type="text"  value="<?php echo $info['active_tel'];?>" name="active_tel"/></li>
		 	<li class="three"><span class="myinfo">活动描述：</span></li>
		 	<li class="three"><textarea name="active_description" id="active_description"><?php echo $info['active_description'];?></textarea>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>