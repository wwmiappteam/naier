<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<?php 
	$id = $_REQUEST["id"];
	$sql = "select * from keeper_info where id=$id";
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
<body nav="人员信息添加页面" class="iframebody">
	<form id="shopgoodsform" action="./keeper_info_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="edit" />
	<input type="hidden" name="id" value="<?php echo $info["id"]?>" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>人员信息</legend>
		 <ul class="myform">
		 	<li>
	    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<?php if($info["keeper_photo"]!=""){?><span id="img0" >照片：<img  width="50px" height="100px" src="<?php echo $base.$info["keeper_photo"];?>" /></span><?php }?>
	    	</li>
	    	<hr/>
		 	<li class="one"><span class="myinfo">姓名：</span><input value="<?php echo $info['keeper_name']?>" type="text" class="easyui-validatebox" data-options="required:true" name="keeper_name"/><span class="red">*</span></li>
		 	<li class="one"><span class="myinfo">人员分类：</span>
		 		<select name="keeper_type_id" class="myselect">
		 		<?php 
					$sql = "select * from keeper_type order by id asc";
					$result = mysql_query($sql,$con);
		 			while($row=mysql_fetch_assoc($result)){
		 				if($row["id"]==$info["keeper_type_id"]){
			 				echo "<option selected='selected' value='".$row["id"]."'>".$row["type_name"]."</option>";
			 				continue;
		 				}
		 				echo "<option value='".$row["id"]."'>".$row["type_name"]."</option>";
		 			}
		 		?>
		 		</select>
		 	</li>
		 	<li class="one"><span class="myinfo">性别：</span><select name="keeper_gender"><option <?php if($info['keeper_gender']=='男'){echo 'selected="selected';}?> " value="男">男</option><option <?php if($info['keeper_gender']=='女'){echo 'selected="selected';}?> value="女">女</option></select></li>
		 	<li class="one"><span class="myinfo">年龄：</span><input value="<?php echo $info['keeper_age']?>" type="text" name="keeper_age"/></li>
		 	<li class="one"><span class="myinfo">照片：</span><a onclick="showfile(this,'img0');" href="javascript:void(0);" >修改图片</a><input disabled="disabled" style="display:none;" type="file" name="keeper_photo" class="myfile"/> </li>
		 	<li class="one"><span class="myinfo">经验：</span><input value="<?php echo $info['keeper_experience']?>" type="text" name="keeper_experience"/></li>
		 	<li class="one"><span class="myinfo">级别：</span><input class="easyui-numberspinner" data-options="min:0,max:5,editable:true,precision:0" value="<?php echo $info['keeper_level']?>" type="text" name="keeper_level"/></li>
		 	<li class="one"><span class="myinfo">专业分值：</span><input class="easyui-numberspinner" data-options="min:0,max:10,editable:true,precision:0" value="<?php echo $info['keeper_professional']?>" type="text" name="keeper_professional"/></li>
		 	<li class="one"><span class="myinfo">态度分值：</span><input class="easyui-numberspinner" data-options="min:0,max:10,editable:true,precision:0" value="<?php echo $info['keeper_attitude']?>" type="text" name="keeper_attitude"/></li>
		 	<li class="one"><span class="myinfo">勤劳分值：</span><input class="easyui-numberspinner" data-options="min:0,max:10,editable:true,precision:0" value="<?php echo $info['keeper_hardworking']?>" type="text" name="keeper_hardworking"/></li>
		 	<li class="one"><span class="myinfo">细心分值：</span><input class="easyui-numberspinner" data-options="min:0,max:10,editable:true,precision:0" value="<?php echo $info['keeper_attentive']?>" type="text" name="keeper_attentive"/></li>
		 	<li class="one"><span class="myinfo">特长：</span><input value="<?php echo $info['keeper_special']?>" type="text" name="keeper_special"/></li>
		 	<li class="three"><span class="myinfo">自我介绍：</span><textarea rows="4" cols="100" name="keeper_introduce"><?php echo $info['keeper_introduce']?></textarea></li>
		 	<li class="one"><span class="myinfo">是否首页显示：</span><input <?php if($info['keeper_ispush']=="true"){echo 'checked="checked"';}?>  type="checkbox" value="true" name="keeper_ispush" />【勾选后将在私人管家首页显示】</li>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>