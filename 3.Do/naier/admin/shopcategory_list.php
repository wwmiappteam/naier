<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<script type="text/javascript" >
$(document).ready(function(){
	
});
function deletedata(id){
	if(confirm("确认删除？")){
		$.ajax({
			   type: "POST",
			   url: "./shopcategory_service.php",
			   data: "action=delete&id="+id,
			   success: function(msg){
					$('#dg').treegrid("reload");
			   }
		});
	}
}
function optFormater(value, rowData, rowIndex) {
	return "<a class='icon icon_add' title='添加子类' href='./shopcategory_add.php?pid="+rowData.id+"' ></a>"+
			"<a class='icon icon_edit' title='编辑' href='./shopcategory_edit.php?id="+rowData.id+"' ></a>"+
		   "<a onclick=deletedata("+rowData.id+"); class='icon icon_delete' title='删除' href='javascript:void(0);' ></a></a>";
}
</script>
</head>
<body nav="分类管理页面" class="iframebody">
	<div class="tools-pannel">
		 <a href="./shopcategory_add.php" data-options="iconCls:'icon-add'" class="easyui-linkbutton">添加顶级分类</a>
	 </div>
	<table id="dg" class="easyui-treegrid" 
			data-options="
				url: './shopcategory_service.php?action=list',
				method: 'get',
				singleSelect:true,
				idField: 'id',
				treeField: 'cat'
			">
		<thead>
			<tr>
				<th data-options="field:'id'" width="80">分类ID</th>
				<th data-options="field:'cat'" width="350">分类名称</th>
				<th field="optFormater" width="150px" formatter="optFormater"  align="center">操作</th>
			</tr>
		</thead>
	</table>
</body>
</html>