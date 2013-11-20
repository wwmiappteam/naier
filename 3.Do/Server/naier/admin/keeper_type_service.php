<?php
include_once 'common_service.php';
$action = $_REQUEST["action"];
if(!isset($_REQUEST["action"])){return;}
//系统用户一览页面请求的数据
if($action=="edit"){
	$type_description1 = $_REQUEST["type_description1"];
	$type_description2 = $_REQUEST["type_description2"];
	$type_description3 = $_REQUEST["type_description3"];
	$type_description4 = $_REQUEST["type_description4"];
	$type_description5 = $_REQUEST["type_description5"];
	$type_description6 = $_REQUEST["type_description6"];
	
	$sql = "update keeper_type set type_description='$type_description1' where type_code='type_code_1'";
	mysql_query($sql);
	$sql = "update keeper_type set type_description='$type_description2' where type_code='type_code_2'";
	mysql_query($sql);
	$sql = "update keeper_type set type_description='$type_description3' where type_code='type_code_3'";
	mysql_query($sql);
	$sql = "update keeper_type set type_description='$type_description4' where type_code='type_code_4'";
	mysql_query($sql);
	$sql = "update keeper_type set type_description='$type_description5' where type_code='type_code_5'";
	mysql_query($sql);
	$sql = "update keeper_type set type_description='$type_description6' where type_code='type_code_6'";
	mysql_query($sql);
	header("location:./keeper_type_edit.php");
}
?>

