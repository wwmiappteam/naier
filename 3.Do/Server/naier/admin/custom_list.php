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
	return "<a  class='icon icon_edit' title='修改' href='./custom_edit.php?id="+rowData.id+"'></a><a onclick=deletedata("+rowData.id+"); class='icon icon_delete' title='删除' href='#'></a>";
}
function sexFormater(value, rowData, rowIndex) {
	if(value=="0"){return "男";}else{return "女";}
}
function deletedata(id){
	if(confirm("确认删除？")){
		$.ajax({
			   type: "POST",
			   url: "./custom_service.php",
			   data: "action=delete&id="+id,
			   success: function(msg){
					$('#dg').datagrid("reload");
			   }
		});
	}
}

function query(){
	//获取datagrid的参数设置
	var options = $('#dg').datagrid('options');
	//顾客ID
	var custom_username = $("#custom_username").val();
	var custom_name = $("#custom_name").val();
	options.pageNumber =1;//查询页数
	options.queryParams = {//查询条件
		"custom_username" : custom_username,
		"custom_name" : custom_name
	};
	$('#dg').datagrid(options).datagrid('acceptChanges');
}
</script>
</head>
<body nav="顾客管理页面" class="iframebody">
	 <div class="search-box">
		 <ul class="ul">
		 	<li>
		 		帐号：<input type="text" name="custom_username" id="custom_username"/>
		 	</li>
		 	<li>
		 		姓名：<input type="text" name="custom_name" id="custom_name"/>
		 	</li>
		 </ul>
		 <div class="clear"></div>
	 </div>
	 <div class="tools-pannel">
		 <a href="javascript:void(0);" onclick="query();" data-options="iconCls:'icon-search'" class="easyui-linkbutton" >搜索</a>&nbsp;&nbsp;&nbsp;&nbsp;
	 </div>
	 <table id="dg" style="" class="easyui-datagrid"
            data-options="rownumbers:true,singleSelect:true,pagination:true,url:'./custom_service.php?action=list',method:'get',pageList:[10,20],toolbar:'#tb'">
        <thead>
            <tr>
                <th data-options="field:'custom_username',width:150">帐号</th>
                <th data-options="field:'custom_name',width:150">姓名</th>
                <th data-options="field:'custom_cellphone',width:150">电话</th>
                <th data-options="field:'custom_address',width:250">住址</th>
                <th field="optFormater" width="100px" formatter="optFormater"  align="center">操作</th>
            </tr>
        </thead>
    </table>
</body>
</html>