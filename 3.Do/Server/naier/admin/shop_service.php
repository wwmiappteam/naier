<?php 
	include_once 'common_service.php';
	date_default_timezone_set(PRC);
	$action = $_REQUEST["action"];
	if(!isset($_REQUEST["action"])){return;}
	if($action=="list"){
		$page = $_REQUEST["page"];
		$rows = $_REQUEST["rows"];
		$sql = "select * from shop ";
		$result = mysql_query($sql,$con);
		$json = array();
  		$json["total"] =  mysql_num_rows($result);
  		$sql = $sql." order by id desc ";
  		if(isset($page)&&isset($rows)){
	  		$sql = $sql." limit ".($page-1)*$rows.",".$rows;
  		}
  		$result = mysql_query($sql,$con);
  		$json["rows"] = array();
  		while($row=mysql_fetch_assoc($result)){
  			array_push($json["rows"], $row);
  		}
  		echo json($json);
	}else if($action=="add"){
		//普通参数获取
		$shop_name = $_REQUEST["shop_name"];
		$shop_tel = $_REQUEST["shop_tel"];
		$shop_address = $_REQUEST["shop_address"];
		$baidu_longitude = $_REQUEST["baidu_longitude"];
		$baidu_latitude = $_REQUEST["baidu_latitude"];
		$google_longitude = $_REQUEST["google_longitude"];
		$google_latitude = $_REQUEST["google_latitude"];
		$sql = "insert into shop(shop_name,shop_tel,shop_address,baidu_longitude,baidu_latitude,google_longitude,google_latitude) 
		values ('$shop_name','$shop_tel','$shop_address','$baidu_longitude','$baidu_latitude','$google_longitude','$google_latitude')";
		mysql_query($sql,$con);
		header("Location:./shop_list.php");
	}else if($action=="listAll"){
		$json = sub(1);
		echo json($json);
	//编辑分类
	}else if($action=="delete"){
		$sql = "delete from shop where id=".$_REQUEST['id'];
		mysql_query($sql);
		echo "success";
	}else if($action=="edit"){
		//普通参数获取
		$id = $_REQUEST["id"];
		$shop_name = $_REQUEST["shop_name"];
		$shop_tel = $_REQUEST["shop_tel"];
		$shop_address = $_REQUEST["shop_address"];
		$baidu_longitude = $_REQUEST["baidu_longitude"];
		$baidu_latitude = $_REQUEST["baidu_latitude"];
		$google_longitude = $_REQUEST["google_longitude"];
		$google_latitude = $_REQUEST["google_latitude"];
		$sql = "update shop set  shop_name='$shop_name',shop_tel='$shop_tel',shop_address='$shop_address',baidu_longitude='$baidu_longitude',baidu_latitude='$baidu_latitude',google_longitude='$google_longitude',google_latitude='$google_latitude' where id=$id ";
		mysql_query($sql,$con);
		header("Location:./shop_list.php");
	}
	
	function sub($pid){
		$sqlsub = "select * from secretary_info where pid=$pid";
		$resultsub = mysql_query($sqlsub);
		$childrensub = array();
		while($row=mysql_fetch_assoc($resultsub)){
			$tmpsub = array();
			$tmpsub["id"] =  $row["cid"];
			$tmpsub["text"] =  $row["cat"];
			$subsql = "select * from secretary_info where pid=".$tmpsub["id"];
			$subresult = mysql_query($subsql);
			if(mysql_num_rows($subresult)>0){
				$tmpsub["children"]=sub($tmpsub["id"]);
			}
  			array_push($childrensub, $tmpsub);
  		}
  		return $childrensub;
	}
?>

