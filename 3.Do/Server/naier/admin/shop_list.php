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
			   url: "./shop_service.php",
			   data: "action=delete&id="+id,
			   success: function(msg){
					$('#dg').datagrid("reload");
			   }
		});
	}
}
function optFormater(value, rowData, rowIndex) {
	return "<a class='icon icon_edit' title='编辑' href='./shop_edit.php?id="+rowData.id+"' ></a>"+
   "<a onclick=deletedata("+rowData.id+"); class='icon icon_delete' title='删除' href='#'></a>";
}
function query(){
	//获取datagrid的参数设置
	var options = $('#dg').datagrid('options');
	//文章主标题
	var title = $("#title").val();
	options.pageNumber =1;//查询页数
	options.queryParams = {//查询条件
		"title" : title
	};
	$('#dg').datagrid(options).datagrid('acceptChanges');
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
<body nav="门市管理页面" class="iframebody">
	 <div class="tools-pannel">
		 <a href="./shop_add.php" data-options="iconCls:'icon-add'" class="easyui-linkbutton">添加门市信息</a>
	 </div>
	 <table id="dg" style="" class="easyui-datagrid"
            data-options="rownumbers:true,singleSelect:true,pagination:true,url:'./shop_service.php?action=list',method:'get',pageList:[10,20],toolbar:'#tb'">
        <thead>
            <tr>
                <th data-options="field:'shop_name',width:150">门市名称</th>
                <th data-options="field:'shop_tel',width:100">电话</th>
                <th data-options="field:'shop_address',width:150">地址</th>
                <th data-options="field:'baidu_longitude',width:100">百度经度</th>
                <th data-options="field:'baidu_latitude',width:100">百度纬度</th>
                <th data-options="field:'google_longitude',width:100">谷歌经度</th>
                <th data-options="field:'google_latitude',width:100">谷歌纬度</th>
                <th field="optFormater" width="150px" formatter="optFormater"  align="center">操作</th>
            </tr>
        </thead>
    </table>
</body>
</html>