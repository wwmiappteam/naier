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
	return "<a  class='icon icon_edit' title='修改' href='./member_edit.php?id="+rowData.memberid+"'></a><a onclick=deletedata("+rowData.memberid+"); class='icon icon_delete' title='删除' href='#'></a>";
}
function sexFormater(value, rowData, rowIndex) {
	if(value=="0"){return "男";}else{return "女";}
}
function deletedata(id){
	if(confirm("确认删除？")){
		$.ajax({
			   type: "POST",
			   url: "./member_service.php",
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
	//会员ID
	var mobilenum = $("#mobilenum").val();
	options.pageNumber =1;//查询页数
	options.queryParams = {//查询条件
		"mobilenum" : mobilenum
	};
	$('#dg').datagrid(options).datagrid('acceptChanges');
}
</script>
</head>
<body nav="会员管理页面" class="iframebody">
	 <div class="search-box">
		 <ul class="ul">
		 	<li>
		 		会员帐号：<input type="text" name="mobilenum" id="mobilenum"/>
		 	</li>
		 </ul>
		 <div class="clear"></div>
	 </div>
	 <div class="tools-pannel">
		 <a href="javascript:void(0);" onclick="query();" data-options="iconCls:'icon-search'" class="easyui-linkbutton" >搜索</a>&nbsp;&nbsp;&nbsp;&nbsp;
	 </div>
	 <table id="dg" style="" class="easyui-datagrid"
            data-options="rownumbers:true,singleSelect:true,pagination:true,url:'./member_service.php?action=list',method:'get',pageList:[10,20],toolbar:'#tb'">
        <thead>
            <tr>
                <th data-options="field:'mobilenum',width:100">会员帐号</th>
                <th data-options="field:'name',width:80">姓名</th>
                <th data-options="field:'work',width:100">职业</th>
                <th data-options="field:'sex',width:50" formatter="sexFormater">性别</th>
                <th data-options="field:'birthday',width:100">生日</th>
                <th data-options="field:'birthday',width:100">住址</th>
                <th data-options="field:'email',width:100">电子邮箱</th>
                <th data-options="field:'passcode',width:100">身份证号码</th>
                <th data-options="field:'qq',width:100">QQ</th>
                <th data-options="field:'invitecode',width:100">邀请码</th>
                <th data-options="field:'points',width:60">积分</th>
                <th field="optFormater" width="100px" formatter="optFormater"  align="center">操作</th>
            </tr>
        </thead>
    </table>
</body>
</html>