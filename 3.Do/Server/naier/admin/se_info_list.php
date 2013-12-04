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
			   url: "./se_info_service.php",
			   data: "action=delete&id="+id,
			   success: function(msg){
					$('#dg').datagrid("reload");
			   }
		});
	}
}
function optFormater(value, rowData, rowIndex) {
	return "<a class='icon icon_edit' title='编辑' href='./se_info_edit.php?id="+rowData.id+"' ></a>"+
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
<body nav="私人秘书信息管理页面" class="iframebody">
	 <div class="tools-pannel">
		 <a href="./se_info_add.php" data-options="iconCls:'icon-add'" class="easyui-linkbutton">添加信息</a>
	 </div>
	 <table id="dg" style="" class="easyui-datagrid"
            data-options="rownumbers:true,nowrap:false,singleSelect:true,pagination:true,url:'./se_info_service.php?action=list',method:'get',pageList:[10,20],toolbar:'#tb'">
        <thead>
            <tr>
                <th data-options="field:'title',width:100">标题</th>
                <th data-options="field:'type_name',width:70">分类名</th>
                <th data-options="field:'region_name',width:70">区域名</th>
                <th data-options="field:'address',width:100">地址</th>
                <th data-options="field:'tel',width:100">电话</th>
                <th data-options="field:'images',width:100,formatter:imgFormatter">图片</th>
                <th data-options="field:'description',width:130">描述</th>
                <th data-options="field:'special',width:130">特色</th>
                <th data-options="field:'price',width:70">人均消费</th>
                <th data-options="field:'update_time',width:110">创建时间</th>
                <th field="optFormater" width="150px" formatter="optFormater"  align="center">操作</th>
            </tr>
        </thead>
    </table>
</body>
</html>