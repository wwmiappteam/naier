'<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			   url: "./keeper_info_service.php",
			   data: "action=delete&id="+id,
			   success: function(msg){
					$('#dg').datagrid("reload");
			   }
		});
	}
}
function optFormater(value, rowData, rowIndex) {
	return "<a class='icon icon_edit' title='编辑' href='./keeper_info_edit.php?id="+rowData.id+"' ></a>"+
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
	return '<img  width="95px" height="95px" src="'+value+'"/>';
}
</script>
</head>
<body nav="私人秘书信息管理页面" class="iframebody">
	 <div class="tools-pannel">
		 <a href="./keeper_info_add.php" data-options="iconCls:'icon-add'" class="easyui-linkbutton">添加人员信息</a>
	 </div>
	 <table id="dg" style="" class="easyui-datagrid"
            data-options="rownumbers:true,singleSelect:true,nowrap:false,pagination:true,url:'./keeper_info_service.php?action=list',method:'get',pageList:[10,20],toolbar:'#tb'">
        <thead>
            <tr>
                <th data-options="field:'keeper_name',width:100">姓名</th>
                <th data-options="field:'keeper_ispush_str',width:80">首页显示</th>
                <th data-options="field:'type_name',width:100">所属分类</th>
                <th data-options="field:'keeper_gender',width:50">性别</th>
                <th data-options="field:'keeper_age',width:50">年龄</th>
                <th data-options="field:'keeper_photo',width:100,formatter:imgFormatter">照片</th>
                <th data-options="field:'keeper_experience',width:120">经验</th>
                <th data-options="field:'keeper_level',width:50">级别</th>
                <th data-options="field:'keeper_special',width:150">特长</th>
                <th field="optFormater" width="150px" formatter="optFormater"  align="center">操作</th>
            </tr>
        </thead>
    </table>
</body>
</html>