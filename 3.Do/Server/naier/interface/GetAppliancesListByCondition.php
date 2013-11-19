<?php
	//5、根据品牌、类型和商品名称查询商品列表 
	include_once 'common.php';
	$currentPage = $_REQUEST['currentPage'];
	$pageRows = $_REQUEST['pageRows'];
	//类型ID
	$typeId = $_REQUEST['typeId'];
	//品牌ID
	$brandId = $_REQUEST['brandId'];
	//商品名称
	$appliancesName = $_REQUEST['appliancesName'];
	
	$sql = "select id,title,originalPrice,currentPrice,imgsrc,comment,ishot,isnew,canshu from shop_goods ";
	$where = array();
	if($typeId!=""){
		$where[] =" cid in (select cid from shop_category where shop_category.pid=$typeId ) or cid=$typeId ";
	}
	if($brandId!=""){
		$where[] = " bid=".$brandId;
	}
	if($appliancesName!=""){
		$where[] = " title like '%".$appliancesName."%'";
	}
	$where = join(" AND ", $where);
	if($where != ""){
		$sql = $sql." where ".$where;
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
  		$tmp['appliancesId']=$row['id'];
  		$tmp['appliancesName']=$row['title'];
  		$tmp['originalPrice']=$row['originalPrice'];
  		$tmp['currentPrice']=$row['currentPrice'];
  		$tmp['imgUrl']=$base.$row['imgsrc'];
  		$tmp['comment']=$row['comment'];
  		$tmp['isPromote']=$row['ishot'];
  		$tmp['isNew']=$row['isnew'];
  		array_push($json["appliancesList"], $tmp);
  	}
  	$json['msg']="";
  	echo json($json);
	
?>