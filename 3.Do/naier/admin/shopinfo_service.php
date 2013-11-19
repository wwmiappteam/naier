<?php 
	include_once 'common_service.php';
	$action = $_REQUEST["action"];
	//商品一览页面请求的数据
	if($action=="list"){
		$page = $_REQUEST["page"];
		$rows = $_REQUEST["rows"];
		$sql = "select * from shop_info order by id desc";
		$result = mysql_query($sql,$con);
		$json = array();
		$json["total"] =  mysql_num_rows($result);
		$json["rows"] = array();
		while($row=mysql_fetch_assoc($result)){
			$row['img']=$base.$row['img'];
			array_push($json["rows"], $row);
  		}
		echo json($json);
	}else if($action == "add"){
		//普通参数获取
		$name = $_REQUEST["name"];
		$address = $_REQUEST["address"];
		$installphone = $_REQUEST["installphone"];
		$servicephone = $_REQUEST["servicephone"];
		$longitude = $_REQUEST["longitude"];
		$latitude = $_REQUEST["latitude"];
		$gLongitude = $_REQUEST["gLongitude"];
		$gLatitude = $_REQUEST["gLatitude"];
		$sendservicinfo = addslashes(addslashes($_REQUEST["sendservicinfo"]));
		$fixservicinfo = addslashes(addslashes($_REQUEST["fixservicinfo"]));
		$pointinfo = addslashes(addslashes($_REQUEST["pointinfo"]));
		$time = strval(time());//当前时间戳
		if(isset($_FILES["img"])&&$_FILES["img"]["name"]!=""){
			$arr = explode(".",$_FILES["img"]["name"]);
			$filetype = $arr[count($arr)-1];
			$filename = $time.".".$filetype;
			move_uploaded_file($_FILES["img"]["tmp_name"],"./upload/".$filename);
			$img = "/admin/upload/".$filename;
		}
		$sql = "insert into shop_info(name,address,installphone,servicephone,longitude,latitude,gLongitude,gLatitude,sendservicinfo,fixservicinfo,pointinfo,img) values('$name','$address','$installphone','$servicephone','$longitude','$latitude','$gLongitude','$gLatitude','$sendservicinfo','$fixservicinfo','$pointinfo','$img');";
		mysql_query($sql,$con);
		header("Location:./shopinfo_list.php");
	}else if($action=="delete"){
		$sql = "delete from shop_info where id=".$_REQUEST['id'];
		mysql_query($sql);
		echo "success";
	}else if($action == "edit"){
		//普通参数获取
		$id = $_REQUEST["id"];
		$name = $_REQUEST["name"];
		$address = $_REQUEST["address"];
		$installphone = $_REQUEST["installphone"];
		$servicephone = $_REQUEST["servicephone"];
		$longitude = $_REQUEST["longitude"];
		$latitude = $_REQUEST["latitude"];
		$gLongitude = $_REQUEST["gLongitude"];
		$gLatitude = $_REQUEST["gLatitude"];
		$sendservicinfo = addslashes(addslashes($_REQUEST["sendservicinfo"]));
		$fixservicinfo = addslashes(addslashes($_REQUEST["fixservicinfo"]));
		$pointinfo = addslashes(addslashes($_REQUEST["pointinfo"]));
		
		$sql = "update shop_info set name='$name',address='$address',installphone='$installphone',servicephone='$servicephone',longitude='$longitude',latitude='$latitude',gLongitude='$gLongitude',gLatitude='$gLatitude',sendservicinfo='$sendservicinfo',fixservicinfo='$fixservicinfo',pointinfo='$pointinfo' where id=$id";
		mysql_query($sql,$con);
		header("Location:./shopinfo_list.php");
	}
?>

