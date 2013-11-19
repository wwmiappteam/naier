<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<?php 
	$id = $_REQUEST["id"];
	$sql = "select * from shop_sales where id=$id";
	$result = mysql_query($sql);
	$sale = mysql_fetch_assoc($result);
?>
<script type="text/javascript" >
$(document).ready(function(){
	var editor1 = new UE.ui.Editor();
	editor1.render("intro");
	var editor2 = new UE.ui.Editor();
	editor2.render("shareContent");
});
function sForm(){
	//校验表单
	if($("#aform").form('validate')){
		//同步编辑器中内容
		UE.getEditor('intro').sync();
    	$("#aform").submit();
	}
}
function showfile(obj,imgid){
	$(obj).next().show().removeAttr("disabled");
	$(obj).hide();
	$("#"+imgid).hide();
}
</script>
</head>
<body nav="活动编辑页面" class="iframebody">
	<form id="aform" action="./shopsales_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="edit" />
	<input type="hidden" name="id" value="<?php echo $sale["id"];?>" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>活动信息</legend>
	    <ul class="myform">
	    	<li>
	    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<?php if($sale["logo"]!=""){?><span id="img1" >活动图片：<img  width="100px" height="75px" src="<?php echo $base.$sale["logo"];?>" /></span><?php }?>
	    		<?php if($sale["poster"]!=""){?><span id="img2" >海报图片：<img  width="100px" height="75px" src="<?php echo $base.$sale["poster"];?>" /></span><?php }?>
	    	</li>
	    	<hr/>
		 	<li class="one"><span class="myinfo">活动标题：</span><input value="<?php echo $sale["title"]?>" type="text" class="easyui-validatebox" data-options="required:true" name="title"/><span class="red">*</span></li>
		 	<li class="one"><span class="myinfo">活动图片：</span><a onclick="showfile(this,'img1');" href="javascript:void(0);" >修改图片</a><input style="display:none" disabled="disabled" type="file" name="logo" title="活动图片宽高比为4:3" class="myfile"/> </li>
		 	<li class="one"><span class="myinfo">海报图片：</span><a onclick="showfile(this,'img2');" href="javascript:void(0);" >修改图片</a><input style="display:none" disabled="disabled" type="file" name="poster" title="海报图片宽高比为2:1" class="myfile"/> </li>
		 	<li class="three1"><span class="myinfo">活动介绍：</span></li>
		 	<li class="three"><textarea name="intro" id="intro"><?php echo $sale["intro"]?></textarea></li>
		 	<li class="three1"><span class="myinfo" style="width:105px;">微博分享内容：</span></li>
		 	<li class="three"><textarea name="shareContent" id="shareContent"><?php echo $sale["share_content"]?></textarea></li>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>