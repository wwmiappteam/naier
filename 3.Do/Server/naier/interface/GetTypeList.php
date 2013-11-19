<?php
	//13、查询子类型列表
	include_once 'common.php';
	//类型id
	$typeId = $_REQUEST['typeId'];
	if($typeId==null||$typeId==""){
		$sql = "select cid,cat,pid from shop_category where pid='1' order by cid desc";
	}else{
		$sql = "select cid,cat,pid from shop_category where pid='$typeId' order by cid desc";
	}
	$json = array();
	$json["typeList"] = array();
	$result = mysql_query($sql,$con);
	while($row=mysql_fetch_assoc($result)){
  		$tmp = array();
  		$tmp['typeId']=$row['cid'];
  		$tmp['typeName']=$row['cat'];
  		$tmp['parentId']=$row['pid'];
  		array_push($json["typeList"], $tmp);
  	}
  	$json['msg']="";
  	echo json($json);
?>