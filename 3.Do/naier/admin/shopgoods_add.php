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
//		UE.getEditor('body').sync();
		UE.getEditor('canshu').sync();
//		UE.getEditor('memo').sync();
    	$("#shopgoodsform").submit();
	}
}
</script>
</head>
<body nav="商品添加页面" class="iframebody">
	<form id="shopgoodsform" action="./shopgoods_service.php" method="post" enctype="multipart/form-data">
	<input type="hidden" name="action" value="add" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>商品信息</legend>
	    <ul class="myform">
		 	<li class="one"><span class="myinfo">商品名：</span><input type="text" class="easyui-validatebox" data-options="required:true" name="title"/><span class="red">*</span></li>
		 	<li class="one"><span class="myinfo">所属活动：</span>
		 		<select name="saleid" class="myselect">
		 			<option selected="selected" value="-1"></option>
		 		<?php 
					$sql = "select * from shop_sales order by id desc";
					$result = mysql_query($sql,$con);
		 			while($row=mysql_fetch_assoc($result)){
		 				echo "<option value='".$row["id"]."'>".$row["title"]."</option>";
		 			}
		 		?>
		 		</select>
		 	</li>
		 	<li class="one"><span class="myinfo">所属分类：</span><input name="cid" class="easyui-combotree" data-options="url:'./shopcategory_service.php?action=list',method:'get',idField: 'id',treeField: 'cat'" ></li>
		 	<li class="one"><span class="myinfo">所属品牌：</span>
		 		<select name="bid" class="myselect">
		 			<option selected="selected" value="-1"></option>
		 		<?php 
					$sql = "select * from shop_brand where bid!=1 order by bid desc";
					$result = mysql_query($sql,$con);
		 			while($row=mysql_fetch_assoc($result)){
		 				echo "<option value='".$row["bid"]."'>".$row["brand"]."</option>";
		 			}
		 		?>
		 		</select>
		 	</li>
		 	<li class="one"><span class="myinfo">商品编号：</span><input type="text" name="bn"/></li>
		 	<li class="one"><span class="myinfo">商品型号：</span><input type="text" name="specify"/></li>
		 	<li class="one"><span class="myinfo">商品等级：</span><input type="text" name="level"/></li>
		 	<li class="one"><span class="myinfo">生产商：</span><input type="text" name="factory"/></li>
		 	<li class="one"><span class="myinfo">商品产地：</span><input type="text" name="madein"/></li>
		 	<li class="one"><span class="myinfo">商品原价：</span><input class="easyui-numberspinner" data-options="min:0.00,editable:true,precision:2" type="text" name="originalPrice"/></li>
		 	<li class="one"><span class="myinfo">商品现价：</span><input class="easyui-numberspinner" data-options="min:0.00,editable:true,precision:2" type="text" type="text" name="currentPrice"/></li>
		 	<li class="one"><span class="myinfo">商品单位：</span><input type="text" name="unit"/></li>
		 	<li class="one"><span class="myinfo">促销商品：</span><input type="checkbox" name="ishot" value="1" /> <span class="myinfo">最新商品：</span><input type="checkbox" name="isnew" value="1" /> </li>
		 	<li class="one"><span class="myinfo">商品描述：</span><input type="text" name="comment" /> </li>
		 	<li class="one"><span class="myinfo">商品图片：</span><input type="file" name="imgsrc" title="商品图片宽高比为4:3" class="myfile"/> </li>
		 	<li class="one"><span class="myinfo">列表图片1：</span><input type="file" name="imgsrc1" class="myfile"/> </li>
		 	<li class="one"><span class="myinfo">列表图片2：</span><input type="file" name="imgsrc2" class="myfile"/> </li>
		 	<li class="one"><span class="myinfo">列表图片3：</span><input type="file" name="imgsrc3" class="myfile"/> </li>
		 	<li class="one"><span class="myinfo">列表图片4：</span><input type="file" name="imgsrc4" class="myfile"/> </li>
		 	<!-- <li class="three1"><span class="myinfo">商品介绍：</span></li>
		 	<li class="three"><textarea name="body" id="body"></textarea></li> -->
		 	<li class="three1"><span class="myinfo">商品参数：</span></li>
		 	<li class="three"><textarea name="canshu" id="canshu"><table cellspacing='0' border='1' bordercolor='#7f7f7f' cellpadding='0' style='maring-top:-20px;'>
    <tbody>
        <tr>
            <td valign='middle' rowspan='1' colspan='2' align='center' style='word-break: break-all; background-color: rgb(219, 229, 241);' height='32'>
                <span style='font-size: 14px;'>主体参数<br/></span>
            </td>
        </tr>
        <tr>
            <td width='257' valign='middle' align='right' style='word-break: break-all; background-color: rgb(237, 245, 250);' height='38'>
                <span style='font-size: 14px;'>产品品牌&nbsp;</span>
            </td>
            <td width='554' valign='middle' style='word-break: break-all;' height='38' align='left'>
                <span style='font-size: 14px;'>&nbsp;</span>
            </td>
        </tr>
        <tr>
            <td width='257' valign='middle' align='right' style='word-break: break-all; background-color: rgb(237, 245, 250);' height='36'>
                <span style='text-align: -webkit-right; font-size: 14px;'>&nbsp;</span>
            </td>
            <td width='554' valign='middle' style='word-break: break-all;' height='36' align='left'>
                <span style='font-size: 14px;'>&nbsp;</span>
            </td>
        </tr>
        <tr>
            <td width='257' valign='middle' align='right' style='word-break: break-all; background-color: rgb(237, 245, 250);' height='39'>
                <span style='text-align: -webkit-right; font-size: 14px;'>&nbsp;</span>
            </td>
            <td width='554' valign='middle' style='word-break: break-all;' height='39' align='left'></td>
        </tr>
        <tr>
            <td valign='middle' rowspan='1' colspan='2' align='center' style='word-break: break-all; background-color: rgb(219, 229, 241);' height='32'>
                <span style='font-size: 14px;'>基本参数<br/></span>
            </td>
        </tr>
        <tr>
            <td width='257' valign='middle' align='right' style='word-break: break-all; background-color: rgb(237, 245, 250);' height='38'>
                <span style='font-size: 14px;'>上市时间&nbsp;</span>
            </td>
            <td width='554' valign='middle' style='word-break: break-all;' height='38' align='left'>
                <span style='font-size: 14px;'>&nbsp;</span>
            </td>
        </tr>
        <tr>
            <td width='257' valign='middle' align='right' style='word-break: break-all; background-color: rgb(237, 245, 250);' height='36'>
                <span style='text-align: -webkit-right; font-size: 14px;'>&nbsp;</span>
            </td>
            <td width='554' valign='middle' style='word-break: break-all;' height='36' align='left'>
                <span style='font-size: 14px;'>&nbsp;</span>
            </td>
        </tr>
        <tr>
            <td width='257' valign='middle' align='right' style='word-break: break-all; background-color: rgb(237, 245, 250);' height='39'>
                <span style='text-align: -webkit-right; font-size: 14px;'>&nbsp;</span>
            </td>
            <td width='554' valign='middle' style='word-break: break-all;' height='39' align='left'></td>
        </tr>
    </tbody>
</table>
</textarea></li>
		 	<!-- 
		 	<li class="three1"><span class="myinfo">商品特色：</span></li>
		 	<li class="three"><textarea name="memo" id="memo"></textarea></li> -->
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >提交数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
</body>
</html>