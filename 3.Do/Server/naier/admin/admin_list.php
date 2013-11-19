<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php include_once 'common.php'; ?>
<script type="text/javascript" >
$(document).ready(function(){
	
});
function optFormater(value, rowData, rowIndex) {
	 return "<a class='icon icon_edit' title='编辑' href='./admin_edit.php?id="+rowData.id+"'></a>"+
   "<a onclick=deletedata("+rowData.id+"); class='icon icon_delete' title='删除' href='javascript:void(0);'></a>";
}
function deletedata(id){
	if(confirm("确认删除？")){
		$.ajax({
			   type: "POST",
			   url: "./admin_service.php",
			   data: "action=delete&id="+id,
			   success: function(msg){
					$('#dg').datagrid("reload");
			   }
		});
	}
}
function imgFormatter(value, rowData, rowIndex) {
	// 如果当前无图片，显示div撑大datagrid
	if(value == ''){
		return '';
	}
	return '<img  width="120px" height="60px" src="'+value+'"/>';
}
</script>
</head>
<body nav="管理员页面" class="iframebody">
	 <div class="tools-pannel">
		 <a href="./admin_add.php" data-options="iconCls:'icon-add'" class="easyui-linkbutton">添加管理员</a>
	 </div>
	 <table id="dg" style="" class="easyui-datagrid"
            data-options="rownumbers:true,singleSelect:true,url:'./admin_service.php?action=list',method:'get',pageList:[10,20],toolbar:'#tb'">
        <thead>
            <tr>
                <th data-options="field:'login',width:100">用户名</th>
                <th data-options="field:'name',width:100">姓名</th>
                <th data-options="field:'phone',width:150">电话</th>
                <th data-options="field:'qq',width:150">QQ</th>
                <th data-options="field:'note',width:250">备注</th>
                <th field="optFormater" width="150px" formatter="optFormater"  align="center">操作</th>
            </tr>
        </thead>
    </table>
</body>
</html>