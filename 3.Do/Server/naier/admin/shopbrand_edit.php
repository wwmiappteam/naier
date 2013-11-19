<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<?php 
	$id = $_REQUEST["id"];
	$sql = "select * from shop_brand where bid=".$id;
	$result = mysql_query($sql,$con);
	$bra=mysql_fetch_assoc($result);
?>
<script type="text/javascript" >
$(document).ready(function(){
	var editor1 = new UE.ui.Editor();
	editor1.render("intro");
	<?php 
		$catsql = "select sb.cid,sc.cat from category_brand sb,shop_category sc where sb.cid=sc.cid and bid=".$id;
		$catresult = mysql_query($catsql,$con);
		$cats = "";
		while($cat=mysql_fetch_assoc($catresult)){
			$cats = $cats.$cat["cat"]." ";
		}
	?>
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
function editcat(){
	$("#cid").show();
	$("#mya").remove();
	$("input[name=changec]").remove();
}
</script>
</head>
<body nav="品牌编辑页面" class="iframebody">
	<form id="aform" action="./shopbrand_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="edit" />
	<input type="hidden" name="id" value="<?php echo $bra["bid"]?>" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>品牌信息</legend>
	    <ul class="myform">
	    	<li>
	    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<?php if($bra["logo"]!=""){?><span id="img0" >LOGO：<img  width="100px" height="75px" src="<?php echo $base.$bra["logo"];?>" /></span><?php }?>
	    	</li>
	    	<hr/>
		 	<li class="one"><span class="myinfo">品牌名：</span><input value="<?php echo $bra["brand"]?>" type="text" class="easyui-validatebox" data-options="required:true" name="brand"/><span class="red">*</span></li>
		 	<li class="one"><span class="myinfo">所属分类：</span><span id="mya"><?php echo $cats; ?><a href="javascript:void(0)" onclick="editcat();" >修改</a></span><input type="hidden" name="changec" value="1" /><span id="cid" style="display:none;" ><input   name="cids[]" class="easyui-combotree" multiple  data-options="cascadeCheck:false,url:'./shopcategory_service.php?action=listAll',method:'get',width:220" ></span></li>
		 	<li class="one"><span class="myinfo">LOGO：</span><a onclick="showfile(this,'img0');" href="javascript:void(0);" >修改图片</a><input disabled="disabled" id="file" style="display:none;" type="file" name="logo" class="myfile"/> </li>
		 	<li class="three1"><span class="myinfo">品牌介绍：</span></li>
		 	<li class="three"><textarea name="intro" id="intro"><?php echo $bra["intro"]?></textarea></li>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>