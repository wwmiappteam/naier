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
			   url: "./shopgoods_service.php",
			   data: "action=delete&id="+id,
			   success: function(msg){
					$('#dg').datagrid("reload");
			   }
		});
	}
}
function optFormater(value, rowData, rowIndex) {
	return "<a class='icon icon_edit' title='编辑' href='./shopgoods_edit.php?id="+rowData.id+"' ></a>"+
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
</script>
</head>
<body nav="商品管理页面" class="iframebody">
	 <div class="search-box">
		 <ul class="ul">
		 	<li>
		 		商品名：<input type="text" name="title" id="title"/>
		 	</li>
		 </ul>
		 <div class="clear"></div>
	 </div>
	 <div class="tools-pannel">
		 <a href="javascript:void(0);" onclick="query();" data-options="iconCls:'icon-search'" class="easyui-linkbutton" >搜索</a>&nbsp;&nbsp;&nbsp;&nbsp;
		 <a href="./shopgoods_add.php" data-options="iconCls:'icon-add'" class="easyui-linkbutton">添加商品</a>
	 </div>
	 <table id="dg" style="" class="easyui-datagrid"
            data-options="rownumbers:true,singleSelect:true,pagination:true,url:'./shopgoods_service.php?action=list',method:'get',pageList:[10,20],toolbar:'#tb'">
        <thead>
            <tr>
                <th data-options="field:'title',width:100">商品名</th>
                <th data-options="field:'bn',width:80">编号</th>
                <th data-options="field:'cid_name',width:150">所属分类</th>
                <th data-options="field:'bid_name',width:100">所属品牌</th>
                <th data-options="field:'saleid_name',width:150">所属活动</th>
                <th data-options="field:'originalPrice',width:100">原价</th>
                <th data-options="field:'currentPrice',width:100">现价</th>
                <th data-options="field:'ishot',width:100">热销商品</th>
                <th data-options="field:'isnew',width:100">最新商品</th>
                <th field="optFormater" width="150px" formatter="optFormater"  align="center">操作</th>
            </tr>
        </thead>
    </table>
</body>
</html>