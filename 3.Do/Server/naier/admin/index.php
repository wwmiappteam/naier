<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>奈尔家政后台系统</title>
<?php include_once 'common.php'; ?>
<script type="text/javascript" >

//加载内容区
function addCentFrm(src){
	contFrm.attr('src',src);
	contFrm.load();
	setTimeout("initframe()",1000);
}
var contPanel;
var contFrm;
$(document).ready(function(){
	$('#aa').accordion({
	});
	
	// 初始对象
	contPanel = $('#main_layout').layout('panel','center');
	// iframe 对象
	contFrm = $('#contentFrm');
	// iframe 自适应
	contFrm.load(function() { 
		// 设置中间panel 标题
		contPanel.panel('setTitle',$(this).contents().find("body").attr("nav"));
	});
	$(".nav li").click(function(){
		$(this).siblings().css("background","");
		$(this).css("background","rgb(251,236,136)");
	});
});
function initframe(){
	// 设置中间panel 标题
	contPanel.panel('setTitle',$(this).contents().find("body").attr("nav"));
	contFrm.height(contFrm.height()-10);
}
</script>
</head>
<body id="main_layout" class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:50px;line-height:50px;padding-left:6px;background:url(./images/beijing.png);font-size:20px;color:#0E2D5F;">
		欢迎  <?php echo $_SESSION['admin'];?>  登录奈尔家政后台系统&nbsp;&nbsp;&nbsp;&nbsp;<a style="float:right;color:#0E2D5F;margin-right:20px;font-size:14px;" href="./login.php?action=logout">安全退出</a>
	</div>
	<div data-options="region:'west',split:true,title:'菜单'" style="width:150px;">
		<div id="aa" class="easyui-accordion" data-options="fit:true" >
		    <div title="菜单选项" data-options="selected:true" style="overflow:auto;">
		        <ul class="nav">
					<li style="background: rgb(251,236,136);"><a href="javascript:void(0)" onclick="addCentFrm('./company_edit.php');" class="icon-gears">公司信息</a></li>
		        	<li><a href="javascript:void(0)" onclick="addCentFrm('./business_list.php');"  class="icon-gears">业务管理</a></li>
		        	<li><a href="javascript:void(0)" onclick="addCentFrm('./service_list.php');"  class="icon-gears">服务管理</a></li>
		        	<li><a href="javascript:void(0)" onclick="addCentFrm('./active_list.php');"  class="icon-gears">新闻活动</a></li>
		        	<li><a href="javascript:void(0)" onclick="addCentFrm('./shop_list.php');"  class="icon-gears">门市管理</a></li>
		        	<li><a href="javascript:void(0)" onclick="addCentFrm('./custom_list.php');"  class="icon-gears">顾客管理</a></li>
		        	<li><a href="javascript:void(0)" onclick="addCentFrm('./admin_list.php');"  class="icon-gears">管理员</a></li>
				</ul>
		    </div>
		    <div title="私人秘书"  style="">
		        <ul class="nav">
		        	<li><a href="javascript:void(0)" onclick="addCentFrm('./se_info_list.php');"  class="icon-gears">私人秘书信息</a></li>
		        	<li><a href="javascript:void(0)" onclick="addCentFrm('./se_region_list.php');"  class="icon-gears">私人秘书区域</a></li>
		        	<li><a href="javascript:void(0)" onclick="addCentFrm('./se_type_list.php');"  class="icon-gears">私人秘书类型</a></li>
		        </ul>
		    </div>
		    <div title="家政人员"  style="">
		        <ul class="nav">
		        	<li><a href="javascript:void(0)" onclick="addCentFrm('./keeper_info_list.php');"  class="icon-gears">人员管理</a></li>
		        	<li><a href="javascript:void(0)" onclick="addCentFrm('./keeper_type_edit.php');"  class="icon-gears">人员分类</a></li>
		        </ul>
		    </div>
		</div>
	</div>
	<div data-options="region:'center',title:'首页',tools:'#mainTt'">
		<iframe id="contentFrm" style="border:0px;margin:0px;padding:0px;" frameborder=0 name='welcome' width="100%" height="100%" scrolling="yes" src="./company_edit.php"></iframe>
	</div>
	<div id="mainTt">
		<a href="javascript:history.back(-1);" class="icon-back" style="margin-right: 5px;" title="返回"></a>
		<a href="javascript:void(0)" onclick="document.getElementById('contentFrm').contentWindow.location.reload(true);" class="icon-reload" style="margin-right: 5px;" title="刷新"></a>
	</div>
</body>
</html>