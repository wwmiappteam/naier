<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<?php 
	$id = $_REQUEST["id"];
	$sql = "select * from complain_view where complainid=$id";
	$result = mysql_query($sql);
	$info = mysql_fetch_assoc($result);
?>
<script type="text/javascript" >
$(document).ready(function(){
});
function sForm(){
	//校验表单
	if($("#aform").form('validate')){
		//同步编辑器中内容
    	$("#aform").submit();
	}
}
</script>
</head>
<body nav="投诉信息编辑页面" class="iframebody">
	<form id="aform" action="./order_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="edit" />
	<input type="hidden" name="id" value="<?php echo $info["orderid"];?>" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>投诉信息</legend>
	    <ul class="myform">
		 	<li class="one"><span class="myinfo">顾客帐号：</span><input readonly="readonly" value="<?php echo $info["custom_username"];?>" type="text" class="easyui-validatebox" data-options="required:true" name="custom_username"/></li>
		 	<li class="one"><span class="myinfo">顾客姓名：</span><input readonly="readonly" value="<?php echo $info["custom_name"];?>" type="text" name="custom_name"/></li>
		 	<li class="one"><span class="myinfo">家政人员姓名：</span>
		 		<select name="keeper_id" class="myselect">
		 		<?php 
					$sql = "select * from keeper_info order by id asc";
					$result = mysql_query($sql,$con);
		 			while($row=mysql_fetch_assoc($result)){
		 					if($row["id"]==$info['keeper_id']){
				 				echo "<option selected='selected' value='".$row["id"]."'>".$row["keeper_name"]."</option>";
		 					}else{
				 				echo "<option  value='".$row["id"]."'>".$row["keeper_name"]."</option>";
		 					}
		 			}
		 		?>
		 		</select>
		 	<li class="one"><span class="myinfo">投诉内容：</span><input value="<?php echo $info["order_description"];?>" type="text" name="order_description"/></li>
		 	
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>