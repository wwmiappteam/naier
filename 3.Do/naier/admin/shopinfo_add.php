<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<script type="text/javascript" >
$(document).ready(function(){
	var editor1 = new UE.ui.Editor();
	editor1.render("sendservicinfo");
	var editor2 = new UE.ui.Editor();
	editor2.render("fixservicinfo");
	var editor3 = new UE.ui.Editor();
	editor3.render("pointinfo");
});
function sForm(){
	//校验表单
	if($("#aform").form('validate')){
		//同步编辑器中内容
		UE.getEditor('sendservicinfo').sync();
		UE.getEditor('fixservicinfo').sync();
		UE.getEditor('pointinfo').sync();
    	$("#aform").submit();
	}
}
</script>
</head>
<body nav="活动添加页面" class="iframebody">
	<form id="aform" action="./shopinfo_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="add" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>卖场信息</legend>
	    <ul class="myform">
		 	<li class="one"><span class="myinfo">卖场名称：</span><input type="text" class="easyui-validatebox" data-options="required:true" name="name"/><span class="red">*</span></li>
		 	<li class="one"><span class="myinfo">安装电话：</span><input type="text" name="installphone"/></li>
		 	<li class="one"><span class="myinfo">售后电话：</span><input type="text" name="servicephone"/></li>
		 	<li class="one"><span class="myinfo">商场地址：</span><input type="text" name="address"/></li>
		 	<!--
		 	<li class="one"><span class="myinfo">商场图片：</span><input type="file" class="myfile" name="img"/></li>
		 	--><li class="one"><span class="myinfo">百度经度：</span><input type="text" name="longitude"/></li>
		 	<li class="one"><span class="myinfo">百度纬度：</span><input type="text" name="latitude"/></li>
		 	<li class="one"><span class="myinfo">谷歌经度：</span><input type="text" name="gLongitude"/></li>
		 	<li class="one"><span class="myinfo">谷歌纬度：</span><input type="text" name="gLatitude"/></li>
		 	<li class="three1"><span class="myinfo" style="width:105px;">送货安装说明：</span></li>
		 	<li class="three"><textarea name="sendservicinfo" id="sendservicinfo"></textarea></li>
		 	<li class="three1"><span class="myinfo" style="width:105px;">维修服务说明：</span></li>
		 	<li class="three"><textarea name="fixservicinfo" id="fixservicinfo"></textarea></li>
		 	<li class="three1"><span class="myinfo" style="width:105px;">积分兑换规则：</span></li>
		 	<li class="three"><textarea name="pointinfo" id="pointinfo"></textarea></li>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>