<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<script type="text/javascript" >
$(document).ready(function(){
	//var editor1 = new UE.ui.Editor();
	//editor1.render("body");
//	var editor3 = new UE.ui.Editor();
//	editor3.render("memo");
});
function sForm(){
	//校验表单
	if($("#shopgoodsform").form('validate')){
		//同步编辑器中内容
    	$("#shopgoodsform").submit();
	}
}
</script>
</head>
<body nav="人员信息添加页面" class="iframebody">
	<form id="shopgoodsform" action="./keeper_info_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="add" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>人员信息</legend>
	    <ul class="myform">
		 	<li class="one"><span class="myinfo">姓名：</span><input type="text" class="easyui-validatebox" data-options="required:true" name="keeper_name"/><span class="red">*</span></li>
		 	<li class="one"><span class="myinfo">人员分类：</span>
		 		<select name="keeper_type_id" class="myselect">
		 		<?php 
					$sql = "select * from keeper_type order by id asc";
					$result = mysql_query($sql,$con);
					$firstindex = true;
		 			while($row=mysql_fetch_assoc($result)){
		 				if($firstindex==true){
			 				echo "<option selected='selected' value='".$row["id"]."'>".$row["type_name"]."</option>";
			 				$firstindex = false;
			 				continue;
		 				}
		 				echo "<option value='".$row["id"]."'>".$row["type_name"]."</option>";
		 			}
		 		?>
		 		</select>
		 	</li>
		 	<li class="one"><span class="myinfo">性别：</span><select name="keeper_gender"><option selected="selected" value="男">男</option><option value="女">女</option></select></li>
		 	<li class="one"><span class="myinfo">年龄：</span><input type="text" name="keeper_age"/></li>
		 	<li class="one"><span class="myinfo">照片：</span><input type="file" name="keeper_photo" class="myfile"/></li>
		 	<li class="one"><span class="myinfo">经验：</span><input type="text" name="keeper_experience"/></li>
		 	<li class="one"><span class="myinfo">级别：</span><input class="easyui-numberspinner" data-options="min:0,max:5,editable:true,precision:0" type="text" name="keeper_level"/></li>
		 	<li class="one"><span class="myinfo">专业分值：</span><input class="easyui-numberspinner" data-options="min:0,max:10,editable:true,precision:0" type="text" name="keeper_professional"/></li>
		 	<li class="one"><span class="myinfo">态度分值：</span><input class="easyui-numberspinner" data-options="min:0,max:10,editable:true,precision:0" type="text" name="keeper_attitude"/></li>
		 	<li class="one"><span class="myinfo">勤劳分值：</span><input class="easyui-numberspinner" data-options="min:0,max:10,editable:true,precision:0" type="text" name="keeper_hardworking"/></li>
		 	<li class="one"><span class="myinfo">细心分值：</span><input class="easyui-numberspinner" data-options="min:0,max:10,editable:true,precision:0" type="text" name="keeper_attentive"/></li>
		 	<li class="one"><span class="myinfo">特长：</span><input type="text" name="keeper_special"/></li>
		 	<li class="three"><span class="myinfo">自我介绍：</span><textarea rows="4" cols="100" name="keeper_introduce"></textarea></li>
		 	<li class="one"><span class="myinfo">是否首页显示：</span><input type="checkbox" value="true" name="keeper_ispush" />【勾选后将在私人管家首页显示】</li>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>