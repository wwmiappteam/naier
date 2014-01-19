<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php 
	set_time_limit(0);
	error_reporting(0);
	ini_set('html_errors',false);
	ini_set('display_errors',false);
	include_once 'common.php'; 
	include_once 'phpexcel/PHPExcel.php';
	date_default_timezone_set('PRC');
	function getImportData($filename){
		$filePath = $filename;
		$PHPExcel = new PHPExcel();
		$PHPReader = new PHPExcel_Reader_Excel2007();
		if(!$PHPReader->canRead($filename))
		{
			$PHPReader = new PHPExcel_Reader_Excel5();
			if(!$PHPReader->canRead($filePath))
			{
				echo '未发现Excel文件！';
				return;
			}
		}
		$sheetNames  = $PHPReader->listWorksheetNames($filePath);
		//读取Excel文件
		$PHPExcel = $PHPReader->load($filePath);
		//获取工作表的数目
		$sheetCount = $PHPExcel->getSheetCount();
		foreach ($sheetNames as $k=>$v){
			$data[$k]['name'] = $v;
			$sdata = $PHPExcel->getSheet($k)->toArray(null,true,true,true);
			foreach ($sdata as $sheet=>$item){
				if(empty($item['A'])){
					unset($sdata[$sheet]);
				}
				unset($sdata[$sheet]['H']);
			}
			$data[$k]['data'] = $sdata;
		}
		return $data;
	}
	
	if(isset($_FILES["file"])&&$_FILES["file"]["name"]!=""){
		$path = "./upload/".$_FILES["file"]["name"];
		move_uploaded_file($_FILES["file"]["tmp_name"],$path);
		$data = getImportData($path);
		
		if($data){
				$msg = "";
				foreach ($data as $k=>$items){
					//$items['name']这是sheet名，也是数据库中分类名
					$type_name = $items['name'];
					$typedata = mysql_fetch_assoc(mysql_query("select cid from secretary_type where cat='$type_name' "));
					if($typedata['cid']==""){
						$msg = $msg." $type_name sheet（工作表）在系统中不存在该家政人员分类导入失败；<br/>";
						continue;
					}
					$typeid=$typedata['cid'];
					if(!empty($items['data'])){
						foreach ($items['data'] as $key=>$item){
							if($key == 1){
								continue;
							}
							$tmp_item = "REPLACE(REPLACE('".$item[C]."', CHAR(10),''), CHAR(13),'')";
							$regiondata = mysql_fetch_assoc(mysql_query("select id from secretary_region where region_name=$tmp_item "));
							$regionid = "";
							if($regiondata['id']==""){
								$insert_region = "insert into secretary_region(region_name) values('$item[C]')";
								mysql_query($insert_region,$con);
								$regionid = mysql_insert_id();
							}else{
								$regionid=$regiondata['id'];
							}
							$tmp_itemA = "REPLACE(REPLACE('".$item[A]."', CHAR(10),''), CHAR(13),'')";
							$titledata = mysql_fetch_assoc(mysql_query("select title from secretary_info where title=$tmp_itemA "));
							if($titledata['title']==""){
								$sql = "insert into secretary_info(title,type_id,region_id,address,tel,description,special,price,
								images,update_time) values(";
								$timefmt = date("Y-m-d H:i");
								$sql = $sql."'$item[A]','$typeid','$regionid','$item[D]','$item[B]','$item[G]','$item[E]','$item[F]','','$timefmt')";
								mysql_query($sql,$con);
							}
						}
					}
				}
				$updatesql = "UPDATE secretary_info SET title = REPLACE(REPLACE(title, CHAR(10),''), CHAR(13),''),
				address = REPLACE(REPLACE(address, CHAR(10),''), CHAR(13),''),
				tel = REPLACE(REPLACE(tel, CHAR(10),''), CHAR(13),''),
				description =REPLACE(REPLACE(description, CHAR(10),''), CHAR(13),''),
				special = REPLACE(REPLACE(special, CHAR(10),''), CHAR(13),''),
				price = REPLACE(REPLACE(price, CHAR(10),''), CHAR(13),'')";
				mysql_query($updatesql);
				if($msg==""){
					$msg = "全部数据导入成功";
				}else{
					$msg = $msg."其余数据导入成功";
				}
			}
		
	}
?>
<script type="text/javascript" >
$(document).ready(function(){
	
});
function sForm(){
	//校验表单
	if($("#form").form('validate')){
    	$("#form").submit();
	}
}
</script>
</head>
<body nav="数据导入页面" class="iframebody">
	<form id="form" action="./se_type_import.php" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="pid" value="<?php echo $pid;?>" />
	<input type="hidden" name="action" value="add" />
	<fieldset id="myfieldset" class="myfieldset">
	    <legend>导入数据文件</legend>
	    <ul class="myform">
		 	<li class="three"><span class="myinfo">导入excel：</span><input type="file" class="easyui-validatebox" data-options="required:true" name="file"  /></li><span class="red">*</span></li>
		 	<li class="button">
		 		<a onclick="sForm();" href="#" class="easyui-linkbutton" >导入数据</a>
		 	</li>
		 </ul>
	</fieldset>
	</form>
	<?php if($msg!=""){echo $msg;}?>
</body>
</html>