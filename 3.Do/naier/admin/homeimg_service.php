<?php 
	include_once 'common_service.php';
	$action = $_REQUEST["action"];
	//商品一览页面请求的数据
	if($action=="delete"){
		$sql = "delete from img_resource where id=".$_REQUEST['id'];
		mysql_query($sql);
		echo "success";
		header("Location:./homeimg.php");
	}else if($action=="edit"){
		//普通参数获取
		
		$time = strval(time());//当前时间戳
		if(isset($_FILES["img"])){
			if($_FILES["img"]["name"]==""){
			}else{
				$arr = explode(".",$_FILES["img"]["name"]);
				$filetype = $arr[count($arr)-1];
				$filename = $time.".".$filetype;
				move_uploaded_file($_FILES["img"]["tmp_name"],"./upload/".$filename);
				$imgurl = "/admin/upload/".$filename;
				
				$sql = "insert into img_resource(imgsrc,imgtype) values('$imgurl','1')";
				echo $sql;
			mysql_query($sql);
			}
		}
		header("Location:./homeimg.php");
	}
?>

