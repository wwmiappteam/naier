<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http：//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?php 
	include_once 'common.php'; 
	include_once 'phpexcel/PHPExcel.php';
	
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
				foreach ($data as $k=>$items){
					if(!empty($items['data'])){
						foreach ($items['data'] as $key=>$item){
							if($key == 1){
								continue;
							}
							$sql = "insert into secretary_info(title,type_id,region_id,address,tel,description,special,price,
							images,update_time) values(";
							$timefmt = date("Y-m-d H:i");
							$sql = $sql."'$item[A]','$type_id','$region_id','$address','$tel','$description','$special','$price','$imgurl','$timefmt')";
							mysql_query($sql,$con);
						}
					}
				}
			}
		
		exit();
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
</body>
</html>