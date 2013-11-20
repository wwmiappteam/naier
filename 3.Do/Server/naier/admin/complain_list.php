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
	var optstr =  "";
	if(rowData.status=="0"){
		optstr = optstr+"<a onclick=chulidata("+rowData.complainid+"); class='icon icon_approval' title='处理' href='#'></a>";
	}
	optstr = optstr +"<a onclick=deletedata("+rowData.complainid+"); class='icon icon_delete' title='删除' href='#'></a>";
	return optstr;
}
function statusFormater(value, rowData, rowIndex) {
	if(value=="0"){return "未处理";}else{return "已处理";}
}
function deletedata(id){
	if(confirm("确认删除？")){
		$.ajax({
			   type: "POST",
			   url: "./complain_service.php",
			   data: "action=delete&id="+id,
			   success: function(msg){
					$('#dg').datagrid("reload");
			   }
		});
	}
}
function chulidata(id){
	if(confirm("确认修改为已处理？")){
		$.ajax({
			   type: "POST",
			   url: "./complain_service.php",
			   data: "action=chuli&id="+id,
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
<body nav="投诉管理页面" class="iframebody">
	 <table id="dg" style="" class="easyui-datagrid"
            data-options="rownumbers:true,singleSelect:true,pagination:true,url:'./complain_service.php?action=list',method:'get',pageList:[10,20],toolbar:'#tb'">
        <thead>
            <tr>
                <th data-options="field:'custom_username',width:150">顾客帐号</th>
                <th data-options="field:'custom_name',width:150">顾客姓名</th>
                <th data-options="field:'keeper_name',width:150">家政人员姓名</th>
                <th data-options="field:'type_name',width:150">家政人员分类</th>
                <th data-options="field:'complain_content',width:150">投诉内容</th>
                <th data-options="field:'status',width:150" formatter="statusFormater">是否处理</th>
                <th field="optFormater" width="150px" formatter="optFormater"  align="center">操作</th>
            </tr>
        </thead>
    </table>
</body>
</html>