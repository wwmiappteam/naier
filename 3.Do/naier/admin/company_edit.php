<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<?php 
	$sql = "select * from company  limit 0,1";
	$result = mysql_query($sql,$con);
	$company=mysql_fetch_assoc($result);
?>
<script type="text/javascript" >
$(document).ready(function(){
	var editor1 = new UE.ui.Editor();
	editor1.render("company_about");
});
function sForm(){
	//校验表单
	//同步编辑器中内容
	UE.getEditor('company_about').sync();
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
<body nav="公司信息页面" class="iframebody">
	<form id="aform" action="./company_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="edit" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>公司信息</legend>
	    <ul class="myform">
	    	<li>
	    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<?php if($company['company_pictures1']!=""){?><span id="img1" >首页图片1：<img  width="100px" height="50px" src="<?php echo $base.$company['company_pictures1'];?>" /></span><?php }?>
	    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<?php if($company['company_pictures2']!=""){?><span id="img2" >首页图片2：<img  width="100px" height="50px" src="<?php echo $base.$company['company_pictures2'];?>" /></span><?php }?>
	    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<?php if($company['company_pictures3']!=""){?><span id="img3" >首页图片3：<img  width="100px" height="50px" src="<?php echo $base.$company['company_pictures3'];?>" /></span><?php }?>
	    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<?php if($company['company_pictures4']!=""){?><span id="img4" >首页图片4：<img  width="100px" height="50px" src="<?php echo $base.$company['company_pictures4'];?>" /></span><?php }?>
	    	</li>
	    	<hr/>
		 	<li class="one"><span class="myinfo">咨询电话：</span><input value="<?php echo $company["company_advise_tel"]?>" type="text" name="company_advise_tel"/></li>
		 	<li class="one"><span class="myinfo">投诉电话：</span><input value="<?php echo $company["company_complain_tel"]?>" type="text" name="company_complain_tel"/></li>
		 	<li class="one"><span class="myinfo">私人秘书电话：</span><input value="<?php echo $company["company_secretary_tel"]?>" type="text" name="company_secretary_tel"/></li>
		 	<li class="one"><span class="myinfo">私人管家电话：</span><input value="<?php echo $company["company_keeper_tel"]?>" type="text" name="company_keeper_tel"/></li>
		 	<li class="one"><span class="myinfo">公司地址：</span><input value="<?php echo $company["company_address"]?>" type="text" name="company_address"/></li>
		 	<li class="one"><span class="myinfo">公司邮箱：</span><input value="<?php echo $company["company_email"]?>" type="text" name="company_email"/></li>
		 	<li class="one"><span class="myinfo">公司QQ：</span><input value="<?php echo $company["company_qq"]?>" type="text" name="company_qq"/></li>
		 	<li class="one"><span class="myinfo">首页图片1：</span><a onclick="showfile(this,'img1');" href="javascript:void(0);" >修改图片</a><input disabled="disabled" style="display:none;" type="file"  name="imgsrc1" class="myfile"/> </li>
		 	<li class="one"><span class="myinfo">首页图片2：</span><a onclick="showfile(this,'img2');" href="javascript:void(0);" >修改图片</a><input disabled="disabled" style="display:none;" type="file" name="imgsrc2" class="myfile"/> </li>
		 	<li class="one"><span class="myinfo">首页图片3：</span><a onclick="showfile(this,'img3');" href="javascript:void(0);" >修改图片</a><input disabled="disabled" style="display:none;" type="file" name="imgsrc3" class="myfile"/> </li>
		 	<li class="one"><span class="myinfo">首页图片4：</span><a onclick="showfile(this,'img4');" href="javascript:void(0);" >修改图片</a><input disabled="disabled" style="display:none;" type="file" name="imgsrc4" class="myfile"/> </li>
		 	<li class="three"><span class="myinfo">关于我们：</span></li>
		 	<li class="three"><textarea name="company_about" id="company_about"><?php echo $company["company_about"]?></textarea></li>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>