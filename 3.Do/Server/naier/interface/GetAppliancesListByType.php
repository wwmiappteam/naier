<?php
	//4、根据类型查品牌的接口
	include_once 'common.php';
	$currentPage = $_REQUEST['currentPage'];
	$pageRows = $_REQUEST['pageRows'];
	//类型ID
	$typeId = $_REQUEST['typeId'];
	//品牌ID
	$brandId = $_REQUEST['brandId'];
	$sql = "select id,title,originalPrice,currentPrice,imgsrc,comment,ishot from shop_goods ";
	if($typeId!=""){
		$sql .=" where cid='".$typeId;
	}
	if($typeId!=""&&$brandId!=""){
		$sql .= " and bid=".$brandId;
	}else if($brandId!=""){
		$sql .=" where bid=".$brandId;
	}
	$result = mysql_query($sql,$con);
	$json = array();
  	$json["currentPage"] = $currentPage;
	$json["totalPage"] =  ceil(mysql_num_rows($result)/intval($pageRows));
	$sql = $sql." order by id desc limit ".(intval($currentPage)-1)*$pageRows.",".$pageRows;
	$json["currentPage"] = $currentPage;
	$json["appliancesList"] = array();
	$result = mysql_query($sql,$con);
	while($row=mysql_fetch_assoc($result)){
  		$tmp = array();
  		$tmp['appliancesId']=$row['cid'];
  		$tmp['appliancesName']=$row['title'];
  		$tmp['originalPrice']=$row['originalPrice'];
  		$tmp['currentPrice']=$row['currentPrice'];
  		$tmp['imgUrl']=$base.$row['imgsrc'];
  		$tmp['comment']=$row['comment'];
  		$tmp['isHot']=$row['ishot'];
  		$tmp['isNew']=$row['isnew'];
  		array_push($json["appliancesList"], $tmp);
  	}
  	$json['msg']="";
  	echo json($json);
	
?>