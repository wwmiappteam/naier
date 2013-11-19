<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<script type="text/javascript" >
$(document).ready(function(){
	var editor1 = new UE.ui.Editor();
	editor1.render("intro");
});
function sForm(){
	//校验表单
	if($("#aform").form('validate')){
		//同步编辑器中内容
		UE.getEditor('intro').sync();
    	$("#aform").submit();
	}
}
</script>
</head>
<body nav="品牌添加页面" class="iframebody">
	<form id="aform" action="./shopbrand_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="add" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>品牌信息</legend>
	    <ul class="myform">
		 	<li class="one"><span class="myinfo">品牌名：</span><input type="text" class="easyui-validatebox" data-options="required:true" name="brand"/><span class="red">*</span></li>
		 	<li class="one"><span class="myinfo">所属分类：</span><input name="cids[]" class="easyui-combotree" multiple  data-options="cascadeCheck:false,url:'./shopcategory_service.php?action=list',method:'get',idField: 'id',treeField: 'cat',width:220" ></li>
		 	<li class="one"><span class="myinfo">LOGO：</span><input type="file" name="logo" class="myfile"/> </li>
		 	<li class="three1"><span class="myinfo">品牌介绍：</span></li>
		 	<li class="three"><textarea name="intro" id="intro"></textarea></li>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>