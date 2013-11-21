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
			   url: "./active_service.php",
			   data: "action=delete&id="+id,
			   success: function(msg){
					$('#dg').datagrid("reload");
			   }
		});
	}
}
function optFormater(value, rowData, rowIndex) {
	return "<a class='icon icon_edit' title='编辑' href='./active_edit.php?id="+rowData.id+"' ></a>"+
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
<body nav="新闻活动管理页面" class="iframebody">
	 <div class="tools-pannel">
		 <a href="./active_add.php" data-options="iconCls:'icon-add'" class="easyui-linkbutton">添加新闻活动信息</a>
	 </div>
	 <table id="dg" style="" class="easyui-datagrid"
            data-options="rownumbers:true,singleSelect:true,pagination:true,url:'./active_service.php?action=list',method:'get',pageList:[10,20],toolbar:'#tb'">
        <thead>
            <tr>
                <th data-options="field:'active_title',width:200">新闻活动标题</th>
                <th data-options="field:'active_poster',width:100,formatter:imgFormatter">活动海报</th>
                <th data-options="field:'active_start',width:150">活动开始时间</th>
                <th data-options="field:'active_end',width:150">活动结束时间</th>
                <th data-options="field:'active_tel',width:150">电话</th>
                <th data-options="field:'update_time',width:110">创建时间</th>
                <th field="optFormater" width="150px" formatter="optFormater"  align="center">操作</th>
            </tr>
        </thead>
    </table>
</body>
</html>