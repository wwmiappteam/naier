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
	return "<a  class='icon icon_edit' title='修改' href='./order_edit.php?id="+rowData.orderid+"'></a><a onclick=deletedata("+rowData.orderid+"); class='icon icon_delete' title='删除' href='#'></a>";
}
function sexFormater(value, rowData, rowIndex) {
	if(value=="0"){return "男";}else{return "女";}
}
function deletedata(id){
	if(confirm("确认删除？")){
		$.ajax({
			   type: "POST",
			   url: "./order_service.php",
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
	var keeper_name = $("#keeper_name").val();
	options.pageNumber =1;//查询页数
	options.queryParams = {//查询条件
		"custom_username" : custom_username,
		"custom_name" : custom_name,
		"keeper_name" : keeper_name
	};
	$('#dg').datagrid(options).datagrid('acceptChanges');
}
</script>
</head>
<body nav="顾客预约管理页面" class="iframebody">
	 <div class="search-box">
		 <ul class="ul">
		 	<li>
		 		顾客帐号：<input type="text" name="custom_username" id="custom_username"/>
		 	</li>
		 	<li>
		 		顾客姓名：<input type="text" name="custom_name" id="custom_name"/>
		 	</li>
		 	<li>
		 		家政人员姓名：<input type="text" name="keeper_name" id="keeper_name"/>
		 	</li>
		 </ul>
		 <div class="clear"></div>
	 </div>
	 <div class="tools-pannel">
		 <a href="javascript:void(0);" onclick="query();" data-options="iconCls:'icon-search'" class="easyui-linkbutton" >搜索</a>&nbsp;&nbsp;&nbsp;&nbsp;
	 </div>
	 <table id="dg" style="" class="easyui-datagrid"
            data-options="rownumbers:true,singleSelect:true,pagination:true,url:'./order_service.php?action=list',method:'get',pageList:[10,20],toolbar:'#tb'">
        <thead>
            <tr>
                <th data-options="field:'custom_username',width:150">顾客帐号</th>
                <th data-options="field:'custom_name',width:150">顾客姓名</th>
                <th data-options="field:'keeper_name',width:150">家政人员姓名</th>
                <th data-options="field:'type_name',width:150">家政人员分类</th>
                <th data-options="field:'start_time',width:150">预约开始时间</th>
                <th data-options="field:'end_time',width:150">预约结束时间</th>
                <th data-options="field:'order_description',width:150">预约描述</th>
                <th field="optFormater" width="100px" formatter="optFormater"  align="center">操作</th>
            </tr>
        </thead>
    </table>
</body>
</html>