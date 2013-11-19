<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<?php 
	$id = $_REQUEST["id"];
	$sql = "select * from shop_goods where id=$id";
	$result = mysql_query($sql,$con);
	$goods = mysql_fetch_assoc($result);
?>
<script type="text/javascript" >
$(document).ready(function(){
//	var editor1 = new UE.ui.Editor();
//	editor1.render("body");
	var editor2 = new UE.ui.Editor();
	editor2.render("canshu");
//	var editor3 = new UE.ui.Editor();
//	editor3.render("memo");

	$('#cid').combotree('setValues', [<?php echo $goods["cid"]?>]);
	
});
function sForm(){
	//校验表单
	if($("#shopgoodsform").form('validate')){
		//同步编辑器中内容
//		UE.getEditor('body').sync();
		UE.getEditor('canshu').sync();
//		UE.getEditor('memo').sync();
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
<body nav="商品编辑页面" class="iframebody">
	<form id="shopgoodsform" action="./shopgoods_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="edit" />
	<input type="hidden" name="id" value="<?php echo $goods["id"] ?>" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>商品信息</legend>
	    <ul class="myform">
	    	<li>
	    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<?php if($goods["imgsrc"]!=""){?><span id="img0" >商品图片：<img  width="100px" height="75px" src="<?php echo $base.$goods["imgsrc"];?>" /></span><?php }?>
	    		<span id="imglist">
	    	<?php 
	    		$sqlimgs = "select * from img_resource where goodsid=".$goods['id'];
				$resultimgs = mysql_query($sqlimgs,$con);
				$index =1;
		 		while($img=mysql_fetch_assoc($resultimgs)){
		 				?>
		    		<?php if($img["imgsrc"]!=""){?>列表图片<?php echo $index;?>：<img  width="100px" height="75px" src="<?php echo $base.$img["imgsrc"];?>" /><?php }?>
		 				<?php $index++;
		 		}
		 	?>
		 	</span>
	    	</li>
	    	<hr/>
		 	<li class="one"><span class="myinfo">商品名：</span><input type="text" value="<?php echo $goods["title"];?>" class="easyui-validatebox" data-options="required:true" name="title"/><span class="red">*</span></li>
		 	<li class="one"><span class="myinfo">所属活动：</span>
		 		<select name="saleid" class="myselect">
		 			<option <?php if($goods["saleid"]=="-1"){echo 'selected="selected"';} ?>  value="-1"></option>
		 		<?php 
					$sql = "select * from shop_sales order by id desc";
					$result = mysql_query($sql,$con);
		 			while($row=mysql_fetch_assoc($result)){
		 			if($goods["saleid"]==$row["id"]){
		 				echo "<option selected='selected' value='".$row["id"]."'>".$row["title"]."</option>";
		 			}else{
		 				echo "<option value='".$row["id"]."'>".$row["title"]."</option>";
		 			}
		 			}
		 		?>
		 		</select>
		 	</li>
		 	<li class="one"><span class="myinfo">所属分类：</span><input name="cid" id="cid" class="easyui-combotree" data-options="url:'./shopcategory_service.php?action=listAll',method:'get'" ></li>
		 	<li class="one"><span class="myinfo">所属品牌：</span>
		 		<select name="bid" class="myselect">
		 			<option <?php if($goods["bid"]=="-1"){echo 'selected="selected"';} ?> value="-1"></option>
		 		<?php 
					$sql = "select * from shop_brand where bid!=1 order by bid desc";
					$result = mysql_query($sql,$con);
		 			while($row=mysql_fetch_assoc($result)){
			 			if($goods["bid"]==$row["bid"]){
			 				echo "<option selected='selected' value='".$row["bid"]."'>".$row["brand"]."</option>";
			 			}else{
			 				echo "<option value='".$row["bid"]."'>".$row["brand"]."</option>";
			 			}
		 			}
		 		?>
		 		</select>
		 	</li>
		 	<li class="one"><span class="myinfo">商品编号：</span><input value="<?php echo $goods["bn"]; ?>" type="text" name="bn"/></li>
		 	<li class="one"><span class="myinfo">商品型号：</span><input value="<?php echo $goods["specify"]; ?>" type="text" name="specify"/></li>
		 	<li class="one"><span class="myinfo">商品等级：</span><input value="<?php echo $goods["level"]; ?>" type="text" name="level"/></li>
		 	<li class="one"><span class="myinfo">生产商：</span><input value="<?php echo $goods["factory"]; ?>" type="text" name="factory"/></li>
		 	<li class="one"><span class="myinfo">商品产地：</span><input value="<?php echo $goods["madein"]; ?>" type="text" name="madein"/></li>
		 	<li class="one"><span class="myinfo">商品原价：</span><input value="<?php echo $goods["originalPrice"]; ?>" class="easyui-numberspinner" data-options="min:0.00,editable:true,precision:2" type="text" name="originalPrice"/></li>
		 	<li class="one"><span class="myinfo">商品现价：</span><input class="easyui-numberspinner" data-options="min:0.00,editable:true,precision:2" type="text" value="<?php echo $goods["currentPrice"]; ?>" name="currentPrice"/></li>
		 	<li class="one"><span class="myinfo">商品单位：</span><input value="<?php echo $goods["unit"]; ?>" type="text" name="unit"/></li>
		 	<li class="one"><span class="myinfo">促销商品：</span><input type="checkbox" <?php if ($goods["ishot"]=="1"){?> checked="checked"<?php }?> name="ishot" value="1" /> <span class="myinfo">最新商品：</span><input type="checkbox" <?php if ($goods["isnew"]=="1"){?> checked="checked"<?php }?> name="isnew" value="1" /> </li>
		 	<li class="one"><span class="myinfo">商品描述：</span><input value="<?php echo $goods["comment"]; ?>" type="text" name="comment" /> </li>
		 	<li class="one"><span class="myinfo">商品图片：</span><a onclick="showfile(this,'img0');" href="javascript:void(0);" >修改图片</a><input disabled="disabled" style="display:none;" type="file" name="imgsrc" title="商品图片宽高比为4:3" class="myfile"/> </li>
		 	<li class="one"><span class="myinfo">列表图片1：</span><a onclick="showfile(this,'imglist');" href="javascript:void(0);" >修改图片</a><input disabled="disabled" style="display:none;" type="file"  name="imgsrc1" class="myfile"/> </li>
		 	<li class="one"><span class="myinfo">列表图片2：</span><a onclick="showfile(this,'imglist');" href="javascript:void(0);" >修改图片</a><input disabled="disabled" style="display:none;" type="file" name="imgsrc2" class="myfile"/> </li>
		 	<li class="one"><span class="myinfo">列表图片3：</span><a onclick="showfile(this,'imglist');" href="javascript:void(0);" >修改图片</a><input disabled="disabled" style="display:none;" type="file" name="imgsrc3" class="myfile"/> </li>
		 	<li class="one"><span class="myinfo">列表图片4：</span><a onclick="showfile(this,'imglist');" href="javascript:void(0);" >修改图片</a><input disabled="disabled" style="display:none;" type="file" name="imgsrc4" class="myfile"/> </li>
		 	<!--<li class="three1"><span class="myinfo">商品介绍：</span></li>
		 	<li class="three"><textarea name="body" id="body"><?php echo $goods["body"]?></textarea></li>
		 	--><li class="three1"><span class="myinfo">商品参数：</span></li>
		 	<li class="three"><textarea name="canshu" id="canshu"><?php $cc = stripslashes($goods["canshu"]);echo $cc?></textarea></li>
		 	<!--<li class="three1"><span class="myinfo">商品特色：</span></li>
		 	<li class="three"><textarea name="memo" id="memo"><?php echo $goods["memo"]?></textarea></li>
		 	--><li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>