<?php 
	include_once 'common_service.php';
	$action = $_REQUEST["action"];
	//商品一览页面请求的数据
	if($action=="list"){
		$page = $_REQUEST["page"];
		$rows = $_REQUEST["rows"];
		$sql = "select * from shop_brand order by bid desc";
		$result = mysql_query($sql,$con);
		$json = array();
		while($row=mysql_fetch_assoc($result)){
			if($row["logo"]!=""){
				$row["logo"] = $base.$row["logo"];
			}
  			array_push($json, $row);
  		}
		echo json($json);
	}else if($action == "add"){
		//普通参数获取
		$brand = $_REQUEST["brand"];
		$intro = addslashes(addslashes($_REQUEST["intro"]));
		$time = strval(time());//当前时间戳
		if(isset($_FILES["logo"])&&$_FILES["logo"]["name"]!=""){
			$arr = explode(".",$_FILES["logo"]["name"]);
			$filetype = $arr[count($arr)-1];
			$filename = $time.".".$filetype;
			move_uploaded_file($_FILES["logo"]["tmp_name"],"./upload/".$filename);
			$imgurl = "/admin/upload/".$filename;
			$sql .= ",";
			$logo = $imgurl;
		}
		$sql = "insert into shop_brand(brand,logo,intro) values('$brand','$logo','$intro');";
		mysql_query($sql,$con);
		$cid = mysql_insert_id($con);
		if(count($_REQUEST["cids"])>0){
			foreach ($_REQUEST["cids"] as $value){
				$sql = "insert into category_brand(cid,bid) values('$value','$cid')";
				mysql_query($sql);
			}
		}
		header("Location:./shopbrand_list.php");
	}else if($action=="delete"){
		$sql = "delete from shop_brand where bid=".$_REQUEST['id'];
		mysql_query($sql);
		echo "success";
	}else if($action=="edit"){
		//普通参数获取
		$brand = $_REQUEST["brand"];
		$id = $_REQUEST["id"];
		$intro = addslashes(addslashes($_REQUEST["intro"]));
		
		$sql = "update shop_brand set brand = '$brand',intro='$intro' where bid=$id";
		
		mysql_query($sql);
		
		$time = strval(time());//当前时间戳
		if(isset($_FILES["logo"])){
			
			if($_FILES["logo"]["name"]==""){
				$sql = "update shop_brand set logo=''  where bid=$id";
			}else{
				$arr = explode(".",$_FILES["logo"]["name"]);
				$filetype = $arr[count($arr)-1];
				$filename = $time.".".$filetype;
				move_uploaded_file($_FILES["logo"]["tmp_name"],"./upload/".$filename);
				$imgurl = "/admin/upload/".$filename;
				$sql .= ",";
				$logo = $imgurl;
				
				$sql = "update shop_brand set logo='$logo'  where bid=$id";
			}
			mysql_query($sql);
		}
		if(!isset($_REQUEST['changec'])){
			$sql = "delete from category_brand where bid=$id";
			mysql_query($sql);
			if(count($_REQUEST["cids"])>0){
				foreach ($_REQUEST["cids"] as $value){
					$sql = "insert into category_brand(cid,bid) values('$value','$id')";
					mysql_query($sql);
				}
			}
		}
		header("Location:./shopbrand_list.php");
	}
?>

