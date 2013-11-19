<?php
	include_once 'common.php';
	$sql = "select * from secretary_type where pid=1";
	$result = mysql_query($sql,$con);
	$json = array();
	$json['data'] = array();
	$json['data']['typeList'] = array();
	$json['data']['regionList'] = array();
	while($row=mysql_fetch_assoc($result)){
  		$tmp = array();
  		$tmp['id']=$row['cid'];
  		$tmp['typeName']=$row['cat'];
  		$tmp['childTypeList']=array();
  		$subsql = "select * from secretary_type where pid='$row[cid]'";
		$subresult = mysql_query($subsql,$con);
		while($subrow=mysql_fetch_assoc($subresult)){
			$subtmp = array();
  			$subtmp['id']=$subrow['cid'];
  			$subtmp['typeName']=$subrow['cat'];
			array_push($tmp['childTypeList'], $subtmp);
		}
  		array_push($json['data']["typeList"], $tmp);
  	}
  	$sql = "select * from secretary_region ";
  	$result = mysql_query($sql,$con);
  	while($row=mysql_fetch_assoc($result)){
  		$tmp = array();
  		$tmp['id']=$row['id'];
  		$tmp['regionName']=$row['region_name'];
  		array_push($json['data']['regionList'], $tmp);
  	}
  	$json['msg']="";
  	echo json($json);
?>