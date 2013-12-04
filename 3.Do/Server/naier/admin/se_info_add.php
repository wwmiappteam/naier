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
	var editor2 = new UE.ui.Editor();
	editor2.render("canshu");
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
<body nav="私人秘书信息添加页面" class="iframebody">
	<form id="shopgoodsform" action="./se_info_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="add" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>私人秘书信息</legend>
	    <ul class="myform">
		 	<li class="one"><span class="myinfo">标题：</span><input type="text" class="easyui-validatebox" data-options="required:true" name="title"/><span class="red">*</span></li>
		 	<li class="one"><span class="myinfo">所属分类：</span><input name="cid" class="easyui-combotree " required="true" data-options="url:'./se_type_service.php?action=list',method:'get',idField: 'id',treeField: 'cat'" /></li>
		 	<li class="one"><span class="myinfo">所属区域：</span>
		 		<select name="region_id" class="myselect">
		 		<?php 
					$sql = "select * from secretary_region order by id desc";
					$result = mysql_query($sql,$con);
					$index = 1;
		 			while($row=mysql_fetch_assoc($result)){
		 				if($index==1){
			 				echo "<option selected='selected' value='".$row["id"]."'>".$row["region_name"]."</option>";
			 				continue;
		 				}
		 				echo "<option value='".$row["id"]."'>".$row["region_name"]."</option>";
		 			}
		 		?>
		 		</select>
		 	</li>
		 	<li class="one"><span class="myinfo">地址：</span><input type="text" name="address"/></li>
		 	<li class="one"><span class="myinfo">电话：</span><input type="text" name="tel"/></li>
		 	<li class="three"><span class="myinfo">描述：</span><textarea rows="4" cols="100" name="description"></textarea></li>
		 	<li class="three"><span class="myinfo">特色：</span><textarea rows="4" cols="100" name="special"></textarea></li>
		 	<li class="one"><span class="myinfo">人均价格：</span><input type="text" name="price"/></li>
		 	<li class="one"><span class="myinfo">图片：</span><input type="file" name="images" class="myfile"/></li>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>