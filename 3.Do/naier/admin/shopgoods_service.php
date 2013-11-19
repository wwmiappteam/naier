<?php 
	include_once 'common_service.php';
	$action = $_REQUEST["action"];
	if(!isset($_REQUEST["action"])){return;}
	//商品一览页面请求的数据
	if($action=="list"){
		$page = $_REQUEST["page"];
		$rows = $_REQUEST["rows"];
		$title = $_REQUEST["title"];
		$sql = "select * from shop_goods";
		$param = null;
		if($title!=""){
			$param = array();
			$param['title']=" like '%".$title."%'";
		}
		echo  shopgoods_query_page($con,$sql,$param,$page,$rows,"order by id desc ");
	//添加商品
	}else if($action=="add"){
		//普通参数获取
		$title = $_REQUEST["title"];
		$saleid = $_REQUEST["saleid"];
		$cid = $_REQUEST["cid"];
		$bid = $_REQUEST["bid"];
		$bn = $_REQUEST["bn"];
		$specify = $_REQUEST["specify"];
		$level = $_REQUEST["level"];
		$factory = $_REQUEST["factory"];
		$madein = $_REQUEST["madein"];
		$originalPrice = doubleval($_REQUEST["originalPrice"]);
		$currentPrice = doubleval($_REQUEST["currentPrice"]);
		$unit = $_REQUEST["unit"];
		$ishot = $_REQUEST["ishot"];
		if(!isset($ishot)){
			$ishot = 0;
		}
		$isnew = $_REQUEST["isnew"];
		if(!isset($isnew)){
			$isnew = 0;
		}
		$comment = addslashes(addslashes($_REQUEST["comment"]));
		$body = addslashes(addslashes($_REQUEST["body"]));
		$orgcanshu = $_REQUEST["canshu"]."<style>body{padding:0px;margin:0px;}</style>";
		$cancan = addslashes(addslashes($_REQUEST["canshu"]));
		$canshu = $cancan."<style>body{padding:0px;margin:0px;}</style>";
		$memo = addslashes(addslashes($_REQUEST["memo"]));
		//上传文件获取
		$index =1;//该参数用于同时上传多个文件时，将其文件名区分开
		$time = strval(time());//当前时间戳
		
		$sql = "insert into shop_goods(title,saleid,bn,specify,level,factory,madein,originalPrice,
		currentPrice,unit,ishot,isnew,comment,body,canshu,memo,cid,bid";
		
		if(isset($_FILES["imgsrc"])&&$_FILES["imgsrc"]["name"]!=""){
			$arr = explode(".",$_FILES["imgsrc"]["name"]);
			$filetype = $arr[count($arr)-1];
			$filename = $time.strval($index).".".$filetype;
			move_uploaded_file($_FILES["imgsrc"]["tmp_name"],"./upload/".$filename);
			$imgurl = "/admin/upload/".$filename;
			$index++;
			$sql .=",imgsrc)values('$title','$saleid','$bn','$specify','$level','$factory','$madein',$originalPrice,
			$currentPrice,'$unit',$ishot,$isnew,'$comment','$body','$canshu','$memo','$cid','$bid','$imgurl')";
		}else{
			$sql .=")values('$title','$saleid','$bn','$specify','$level','$factory','$madein','$originalPrice',
			'$currentPrice','$unit',$ishot,$isnew,'$comment','$body','$canshu','$memo','$cid','$bid')";
		}
		mysql_query($sql,$con);
		$goods_id = mysql_insert_id($con);
		
		if(isset($_FILES["imgsrc1"])&&$_FILES["imgsrc1"]["name"]!=""){
			$arr = explode(".",$_FILES["imgsrc1"]["name"]);
			$filetype = $arr[count($arr)-1];
			$filename = $time.strval($index).".".$filetype;
			$imgurl = "/admin/upload/".$filename;
			move_uploaded_file($_FILES["imgsrc1"]["tmp_name"],"./upload/".$filename);
			$index++;
			$sql = "insert into img_resource(goodsid,imgsrc,imgtype) values('$goods_id','$imgurl','$filetype')";
			mysql_query($sql);
		}
		if(isset($_FILES["imgsrc2"])&&$_FILES["imgsrc2"]["name"]!=""){
			$arr = explode(".",$_FILES["imgsrc2"]["name"]);
			$filetype = $arr[count($arr)-1];
			$filename = $time.strval($index).".".$filetype;
			$imgurl = "/admin/upload/".$filename;
			move_uploaded_file($_FILES["imgsrc2"]["tmp_name"],"./upload/".$filename);
			$index++;
			$sql = "insert into img_resource(goodsid,imgsrc,imgtype) values('$goods_id','$imgurl','$filetype')";
			mysql_query($sql);
		}
		if(isset($_FILES["imgsrc3"])&&$_FILES["imgsrc3"]["name"]!=""){
			$arr = explode(".",$_FILES["imgsrc3"]["name"]);
			$filetype = $arr[count($arr)-1];
			$filename = $time.strval($index).".".$filetype;
			$imgurl = "/admin/upload/".$filename;
			move_uploaded_file($_FILES["imgsrc3"]["tmp_name"],"./upload/".$filename);
			$index++;
			$sql = "insert into img_resource(goodsid,imgsrc,imgtype) values('$goods_id','$imgurl','$filetype')";
			mysql_query($sql);
		}
		if(isset($_FILES["imgsrc4"])&&$_FILES["imgsrc4"]["name"]!=""){
			$arr = explode(".",$_FILES["imgsrc4"]["name"]);
			$filetype = $arr[count($arr)-1];
			$filename = $time.strval($index).".".$filetype;
			$imgurl = "/admin/upload/".$filename;
			move_uploaded_file($_FILES["imgsrc4"]["tmp_name"],"./upload/".$filename);
			$index++;
			$sql = "insert into img_resource(goodsid,imgsrc,imgtype) values('$goods_id','$imgurl','$filetype')";
			mysql_query($sql);
		}
		header("Location:./shopgoods_list.php");
	}else if($action=="delete"){
		$sql = "delete from shop_goods where id=".$_REQUEST['id'];
		mysql_query($sql);
		echo "success";
	}else if($action=="edit"){
		//普通参数获取
		$title = $_REQUEST["title"];
		$id = $_REQUEST["id"];
		$saleid = $_REQUEST["saleid"];
		$cid = $_REQUEST["cid"];
		$bid = $_REQUEST["bid"];
		$bn = $_REQUEST["bn"];
		$specify = $_REQUEST["specify"];
		$level = $_REQUEST["level"];
		$factory = $_REQUEST["factory"];
		$madein = $_REQUEST["madein"];
		$originalPrice = doubleval($_REQUEST["originalPrice"]);
		$currentPrice = doubleval($_REQUEST["currentPrice"]);
		$unit = $_REQUEST["unit"];
		$ishot = $_REQUEST["ishot"];
		if(!isset($ishot)){
			$ishot = 0;
		}
		$isnew = $_REQUEST["isnew"];
		if(!isset($isnew)){
			$isnew = 0;
		}
		$comment = addslashes(addslashes($_REQUEST["comment"]));
		$body = addslashes(addslashes($_REQUEST["body"]));
		$orgcanshu = $_REQUEST["canshu"]."<style>body{padding:0px;margin:0px;}</style>";
		$canshu = addslashes(addslashes($_REQUEST["canshu"]))."<style>body{padding:0px;margin:0px;}</style>";
		$memo = addslashes(addslashes($_REQUEST["memo"]));
		//上传文件获取
		$index =1;//该参数用于同时上传多个文件时，将其文件名区分开
		$time = strval(time());//当前时间戳
		
		$sql = "update shop_goods set title='$title',saleid=$saleid,bn='$bn',specify='$specify',level='$level',factory='$factory',madein='$madein',originalPrice='$originalPrice',
		currentPrice='$currentPrice',unit='$unit',ishot='$ishot',isnew='$isnew',comment='$comment',body='$body',canshu='$canshu',memo='$memo',cid='$cid',bid='$bid' where id=$id";
		mysql_query($sql);
		if(isset($_FILES["imgsrc"])){
			
			if($_FILES["imgsrc"]["name"]==""){
				$sql = "update shop_goods set imgsrc = '' where id=$id";
			}else{
				$arr = explode(".",$_FILES["imgsrc"]["name"]);
				$filetype = $arr[count($arr)-1];
				$filename = $time.strval($index).".".$filetype;
				move_uploaded_file($_FILES["imgsrc"]["tmp_name"],"./upload/".$filename);
				$imgurl = "/admin/upload/".$filename;
				$index++;
				$sql = "update shop_goods set imgsrc = '$imgurl' where id=$id";
			}
			
			mysql_query($sql);
		}
		$goods_id = $id;
		
		$flag = true;
		
		if(isset($_FILES["imgsrc1"])&&$_FILES["imgsrc1"]["name"]!=""){
			
			if($flag){
				$sqldel = "delete from img_resource where goodsid=".$id;
				mysql_query($sqldel);
				$flag = false;
			}
			
			$arr = explode(".",$_FILES["imgsrc1"]["name"]);
			$filetype = $arr[count($arr)-1];
			$filename = $time.strval($index).".".$filetype;
			$imgurl = "/admin/upload/".$filename;
			move_uploaded_file($_FILES["imgsrc1"]["tmp_name"],"./upload/".$filename);
			$index++;
			$sql = "insert into img_resource(goodsid,imgsrc,imgtype) values('$goods_id','$imgurl','$filetype')";
			mysql_query($sql);
		}
		if(isset($_FILES["imgsrc2"])&&$_FILES["imgsrc2"]["name"]!=""){
		
			if($flag){
				$sqldel = "delete from img_resource where goodsid=".$id;
				mysql_query($sqldel);
				$flag = false;
			}
			
			$arr = explode(".",$_FILES["imgsrc2"]["name"]);
			$filetype = $arr[count($arr)-1];
			$filename = $time.strval($index).".".$filetype;
			$imgurl = "/admin/upload/".$filename;
			move_uploaded_file($_FILES["imgsrc2"]["tmp_name"],"./upload/".$filename);
			$index++;
			$sql = "insert into img_resource(goodsid,imgsrc,imgtype) values('$goods_id','$imgurl','$filetype')";
			mysql_query($sql);
		}
		if(isset($_FILES["imgsrc3"])&&$_FILES["imgsrc3"]["name"]!=""){
		
			if($flag){
				$sqldel = "delete from img_resource where goodsid=".$id;
				mysql_query($sqldel);
				$flag = false;
			}
			
			$arr = explode(".",$_FILES["imgsrc3"]["name"]);
			$filetype = $arr[count($arr)-1];
			$filename = $time.strval($index).".".$filetype;
			$imgurl = "/admin/upload/".$filename;
			move_uploaded_file($_FILES["imgsrc3"]["tmp_name"],"./upload/".$filename);
			$index++;
			$sql = "insert into img_resource(goodsid,imgsrc,imgtype) values('$goods_id','$imgurl','$filetype')";
			mysql_query($sql);
		}
		if(isset($_FILES["imgsrc4"])&&$_FILES["imgsrc4"]["name"]!=""){
			
		
			if($flag){
				$sqldel = "delete from img_resource where goodsid=".$id;
				mysql_query($sqldel);
				$flag = false;
			}
			
			$arr = explode(".",$_FILES["imgsrc4"]["name"]);
			$filetype = $arr[count($arr)-1];
			$filename = $time.strval($index).".".$filetype;
			$imgurl = "/admin/upload/".$filename;
			move_uploaded_file($_FILES["imgsrc4"]["tmp_name"],"./upload/".$filename);
			$index++;
			$sql = "insert into img_resource(goodsid,imgsrc,imgtype) values('$goods_id','$imgurl','$filetype')";
			mysql_query($sql);
		}
		header("Location:./shopgoods_list.php");
		
	}
?>

