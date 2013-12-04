<?php 
	include_once 'common_service.php';
	date_default_timezone_set(PRC);
	$action = $_REQUEST["action"];
	if(!isset($_REQUEST["action"])){return;}
	if($action=="list"){
		$page = $_REQUEST["page"];
		$rows = $_REQUEST["rows"];
		$json = array();
  		$sql = "select a.*,b.region_name,c.cat from secretary_info a,secretary_region b,secretary_type c where a.type_id=c.cid and a.region_id=b.id ";
		$result = mysql_query($sql,$con);
  		$json["total"] =  mysql_num_rows($result);
  		$sql = $sql." order by id desc ";
  		if(isset($page)&&isset($rows)){
	  		$sql = $sql." limit ".($page-1)*$rows.",".$rows;
  		}
  		$result = mysql_query($sql,$con);
  		$json["rows"] = array();
  		while($row=mysql_fetch_assoc($result)){
  			$tmp = array();
  			$tmp['id'] = $row['id'];
  			$tmp['title'] = $row['title'];
  			$tmp['address'] = $row['address'];
  			$tmp['tel'] = $row['tel'];
  			if($row['images']!=""){
	  			$tmp['images'] = $base.$row['images'];
  			}else{
  				$tmp['images'] = "";
  			}
  			$tmp['description'] = $row['description'];
  			$tmp['special'] = $row['special'];
  			$tmp['price'] = $row['price'];
  			$tmp['type_name'] = $row['cat'];
  			$tmp['region_name'] = $row['region_name'];
  			$tmp['update_time'] = $row['update_time'];
  			array_push($json["rows"], $tmp);
  		}
  		echo json($json);
	}else if($action=="add"){
		//普通参数获取
		$title = $_REQUEST["title"];
		$type_id = $_REQUEST["cid"];
		$region_id = $_REQUEST["region_id"];
		$address = $_REQUEST["address"];
		$tel = $_REQUEST["tel"];
		$description = $_REQUEST["description"];
		$special = $_REQUEST["special"];
		$price = $_REQUEST["price"];
		
		$index =1;//该参数用于同时上传多个文件时，将其文件名区分开
		$time = strval(time());//当前时间戳
		
		$sql = "insert into secretary_info(title,type_id,region_id,address,tel,description,special,price,
		images,update_time) values(";
		
		$imgurl = "";
		if(isset($_FILES["images"])&&$_FILES["images"]["name"]!=""){
			$arr = explode(".",$_FILES["images"]["name"]);
			$filetype = $arr[count($arr)-1];
			$filename = $time.strval($index).".".$filetype;
			move_uploaded_file($_FILES["images"]["tmp_name"],"./upload/".$filename);
			$imgurl = "/admin/upload/".$filename;
		}
		$timefmt = date("Y-m-d H:i");
		$sql = $sql."'$title','$type_id','$region_id','$address','$tel','$description','$special','$price','$imgurl','$timefmt')";
		mysql_query($sql,$con);
		header("Location:./se_info_list.php");
	}else if($action=="listAll"){
		$json = sub(1);
		echo json($json);
	//编辑分类
	}else if($action=="delete"){
		$sql = "delete from secretary_info where id=".$_REQUEST['id'];
		mysql_query($sql);
		echo "success";
	}else if($action=="edit"){
		//普通参数获取
		$id = $_REQUEST["id"];
		$title = $_REQUEST["title"];
		$type_id = $_REQUEST["cid"];
		$region_id = $_REQUEST["region_id"];
		$address = $_REQUEST["address"];
		$tel = $_REQUEST["tel"];
		$description = $_REQUEST["description"];
		$special = $_REQUEST["special"];
		$price = $_REQUEST["price"];
		
		$index =1;//该参数用于同时上传多个文件时，将其文件名区分开
		$time = strval(time());//当前时间戳
		$imgurl = "";
		$imgsql = "";
		if(isset($_FILES["images"])&&$_FILES["images"]["name"]!=""){
			$arr = explode(".",$_FILES["images"]["name"]);
			$filetype = $arr[count($arr)-1];
			$filename = $time.strval($index).".".$filetype;
			move_uploaded_file($_FILES["images"]["tmp_name"],"./upload/".$filename);
			$imgurl = "/admin/upload/".$filename;
			$imgsql = " ,images='".$imgurl."' ";
		}
		$timefmt = date("Y-m-d H:i");
		$sql = "update secretary_info set title='$title',type_id='$type_id',region_id='$region_id',address='$address',tel='$tel',description='$description',special='$special',price='$price',update_time='$timefmt'".$imgsql." where id=".$id;
		mysql_query($sql,$con);
		header("Location:./se_info_list.php");
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

