<?php
	//3、根据类型查品牌的接口
	include_once 'common.php';
	//类型id
	$typeId = $_REQUEST['typeId'];
	if($typeId==null||$typeId==""){
		$sql = "select bid,brand from shop_brand order by bid desc";
	}else{
		$sql = "select shop_brand.bid,shop_brand.brand from shop_brand,category_brand,shop_category where shop_category.cid='".
		$typeId."' and category_brand.cid=shop_category.cid and category_brand.bid=shop_brand.bid";
	}
	$json = array();
	$json["brandList"] = array();
	$result = mysql_query($sql,$con);
	while($row=mysql_fetch_assoc($result)){
  		$tmp = array();
  		$tmp['brandId']=$row['bid'];
  		$tmp['brandName']=$row['brand'];
  		array_push($json["brandList"], $tmp);
  	}
  	$json['msg']="";
  	echo json($json);
?>