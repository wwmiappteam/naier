<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<?php 
	$id = $_REQUEST["id"];
	$sql = "select * from secretary_info where id=$id";
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
<body nav="私人秘书信息添加页面" class="iframebody">
	<form id="shopgoodsform" action="./se_info_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="edit" />
	<input type="hidden" name="id" value="<?php echo $info["id"]?>" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>私人秘书信息</legend>
	    <ul class="myform">
	    	<li>
	    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<?php if($info["images"]!=""){?><span id="img0" >图片：<img  width="100px" height="50px" src="<?php echo $base.$info["images"];?>" /></span><?php }?>
	    	</li>
	    	<hr/>
		 	<li class="one"><span class="myinfo">标题：</span><input type="text" value="<?php echo $info['title']?>" class="easyui-validatebox" data-options="required:true" name="title"/><span class="red">*</span></li>
		 	<li class="one"><span class="myinfo">所属分类：</span><input name="cid" value="<?php echo $info['type_id']?>" class="easyui-combotree " required="true" data-options="url:'./se_type_service.php?action=listAll',method:'get',idField: 'id',treeField: 'cat'" /></li>
		 	<li class="one"><span class="myinfo">所属区域：</span>
		 		<select name="region_id" class="myselect">
		 		<?php 
					$sql = "select * from secretary_region order by id desc";
					$result = mysql_query($sql,$con);
		 			while($row=mysql_fetch_assoc($result)){
		 				if($info['region_id']==$row["id"]){
			 				echo "<option selected='selected' value='".$row["id"]."'>".$row["region_name"]."</option>";
			 				continue;
		 				}
		 				echo "<option value='".$row["id"]."'>".$row["region_name"]."</option>";
		 			}
		 		?>
		 		</select>
		 	</li>
		 	<li class="one"><span class="myinfo">地址：</span><input value="<?php echo $info['address']?>" type="text" name="address"/></li>
		 	<li class="one"><span class="myinfo">电话：</span><input value="<?php echo $info['tel']?>" type="text" name="tel"/></li>
		 	<li class="three"><span class="myinfo">描述：</span><textarea rows="4" cols="100" name="description"><?php echo $info['description']?></textarea></li>
		 	<li class="three"><span class="myinfo">特色：</span><textarea rows="4" cols="100" name="special"><?php echo $info['special']?></textarea></li>
		 	<li class="one"><span class="myinfo">人均价格：</span><input value="<?php echo $info['price']?>" type="text" name="price"/></li>
		 	<li class="one"><span class="myinfo">图片：</span><a onclick="showfile(this,'img0');" href="javascript:void(0);" >修改图片</a><input disabled="disabled" style="display:none;" type="file" name="images" class="myfile"/> </li>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>