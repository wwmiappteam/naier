<?php
	include_once 'common.php';
	$currentPage = $_REQUEST['currentPage'];
	$pageRows = $_REQUEST['pageRows'];
	$typeId = $_REQUEST['typeID'];
	$regionId = $_REQUEST['regionID'];
	$title = $_REQUEST['title'];
  	$sql = "select a.id,a.title,a.address as address,b.region_name as regionName,c.cat as typeName from secretary_info a,secretary_region b,secretary_type c where a.type_id=c.cid and a.region_id=b.id ";
	if($typeId!=""){
		$sql .=" and (c.cid=".$typeId." or c.pid=".$typeId.") ";
	}
	if($regionId!=""){
		$sql .= " and a.region_id=".$regionId;
	}
	if($title!=""){
		$sql .=" and a.title like '%".$title."%' ";
	}
	$result = mysql_query($sql,$con);
	$json = array();
  	$json["currentPage"] = $currentPage;
	$json["totalPage"] =  ceil(mysql_num_rows($result)/intval($pageRows));
	$sql = $sql." order by id desc limit ".(intval($currentPage)-1)*$pageRows.",".$pageRows;
	$json["currentPage"] = $currentPage;
	$json["data"] = array();
	$result = mysql_query($sql,$con);
	while($row=mysql_fetch_assoc($result)){
  		$tmp = array();
  		$tmp['secretaryID']=$row['id'];
  		$tmp['typeName']=$row['typeName'];
  		$tmp['regionName']=$row['regionName'];
  		$tmp['title']=$row['title'];
  		$tmp['address']=$row['address'];
  		array_push($json["data"], $tmp);
  	}
  	$json['msg']="";
  	echo json($json);
	
?>