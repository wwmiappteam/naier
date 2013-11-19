<?php 
	include_once 'common_service.php';
	$action = $_REQUEST["action"];
	//商品一览页面请求的数据
	if($action=="list"){
		$page = $_REQUEST["page"];
		$rows = $_REQUEST["rows"];
		$sql = "select * from shop_sales order by id desc";
		$result = mysql_query($sql,$con);
		$json = array();
		$json["total"] =  mysql_num_rows($result);
		$json["rows"] = array();
		while($row=mysql_fetch_assoc($result)){
			if($row["poster"]!=""){
				$row["poster"] = $base.$row["poster"];
			}
			if($row["logo"]!=""){
				$row["logo"] = $base.$row["logo"];
			}
			array_push($json["rows"], $row);
  		}
		echo json($json);
	}else if($action == "add"){
		//普通参数获取
		$title = $_REQUEST["title"];
		$intro = addslashes(addslashes($_REQUEST["intro"]));
		$shareContent = addslashes(addslashes($_REQUEST["shareContent"]));
		$time = strval(time());//当前时间戳
		if(isset($_FILES["poster"])&&$_FILES["poster"]["name"]!=""){
			$arr = explode(".",$_FILES["poster"]["name"]);
			$filetype = $arr[count($arr)-1];
			$filename = $time.".".$filetype;
			move_uploaded_file($_FILES["poster"]["tmp_name"],"./upload/".$filename);
			$poster = "/admin/upload/".$filename;
		}
		if(isset($_FILES["logo"])&&$_FILES["logo"]["name"]!=""){
			$arr1 = explode(".",$_FILES["logo"]["name"]);
			$filetype1 = $arr[count($arr1)-1];
			$time1 = strval(time())."1";//当前时间戳
			$filename1 = $time1.".".$filetype1;
			move_uploaded_file($_FILES["logo"]["tmp_name"],"./upload/".$filename1);
			$logo = "/admin/upload/".$filename1;
		}
		$sql = "insert into shop_sales(title,poster,intro,logo,share_content) values('$title','$poster','$intro','$logo','$shareContent');";
		mysql_query($sql,$con);
		header("Location:./shopsales_list.php");
	}else if($action=="delete"){
		$sql = "delete from shop_sales where id=".$_REQUEST['id'];
		mysql_query($sql);
		echo "success";
	}else if($action == "edit"){
		//普通参数获取
		$id = $_REQUEST["id"];
		$intro = addslashes(addslashes($_REQUEST["intro"]));
		$shareContent = addslashes(addslashes($_REQUEST["shareContent"]));
		
		$sql = "update shop_sales set title='$title',intro='$intro',shareContent='$shareContent' where id=$id";
		mysql_query($sql);
		
		$time = strval(time());//当前时间戳
		if(isset($_FILES["poster"])){
			if($_FILES["poster"]["name"]==""){
				$sql = "update shop_sales set poster=''  where id=$id";
			}else{
				$arr = explode(".",$_FILES["poster"]["name"]);
				$filetype = $arr[count($arr)-1];
				$filename = $time.".".$filetype;
				move_uploaded_file($_FILES["poster"]["tmp_name"],"./upload/".$filename);
				$poster = "/admin/upload/".$filename;
				
				$sql = "update shop_sales set poster='$poster'  where id=$id";
			}
			mysql_query($sql);
		}
		if(isset($_FILES["logo"])){
			
			if($_FILES["logo"]["name"]==""){
				$sql = "update shop_sales set logo=''  where id=$id";
			}else{
				$arr1 = explode(".",$_FILES["logo"]["name"]);
				$filetype1 = $arr[count($arr1)-1];
				$time1 = strval(time())."1";//当前时间戳
				$filename1 = $time1.".".$filetype1;
				move_uploaded_file($_FILES["logo"]["tmp_name"],"./upload/".$filename1);
				$logo = "/admin/upload/".$filename1;
				
				$sql = "update shop_sales set logo='$logo'  where id=$id";
			}
			mysql_query($sql);
		}
		header("Location:./shopsales_list.php");
	}
?>

