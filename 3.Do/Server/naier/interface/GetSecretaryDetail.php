<?php
	include_once 'common.php';
	$id = $_REQUEST["id"];
  	$sql = "select a.*,b.region_name as regionName,c.cat as typeName from secretary_info a,secretary_region b,secretary_type c where a.type_id=c.cid and a.region_id=b.id  and a.id=$id";
	$result = mysql_query($sql,$con);
	$info = mysql_fetch_assoc($result);
	
	$json = array();
	$result = mysql_query($sql,$con);
	if($row=mysql_fetch_assoc($result)){
  		$tmp = array();
  		$tmp['id']=$row['id'];
  		$tmp['typeName']=$row['typeName'];
  		$tmp['regionName']=$row['regionName'];
  		$tmp['title']=$row['title'];
  		$tmp['address']=$row['address'];
  		$tmp['tel']=$row['tel'];
  		$tmp['special']=$row['special'];
  		$tmp['price']=$row['price'];
  		$tmp['description']=$row['description'];
  		$tmp['images'] = "";
  		if($row['images']!=""){
  			$tmp['images'] = $base.$row['images'];
  		}
  		$json["data"]=$tmp;
  	}else{
  		
  	}
  	$json['msg']="";
  	echo json($json);
	
?>