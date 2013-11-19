<?php
	//5、获取家电详情 
	include_once 'common.php';
	//家电ID
	$appliancesId = $_REQUEST['appliancesId'];
	$userId = $_REQUEST['userId'];
	
	if($userId!=""){
		$sql = "select * from member where memberid=$userId";
		$result = mysql_query($sql);
		$member = mysql_fetch_assoc($result);
		if($member["shopviewnum"]=="4"){
			$points = intval($member["points"])+$shopgoodsview_point;
			$addPoint = "update member set points=$points,shopviewnum=6 where memberid=".$member["memberid"];
			mysql_query($addPoint);	
		}else if(intval($member["shopviewnum"])<4){
			$num = intval($member["shopviewnum"])+1;
			$addnum = "update member set shopviewnum=$num where memberid=".$member["memberid"];
			mysql_query($addnum);	
		}
		
	}
	
	$sql = "select id,title,originalPrice,currentPrice,imgsrc,canshu from shop_goods where id=$appliancesId";
	$result = mysql_query($sql,$con);
	$json = array();
	if($row=mysql_fetch_assoc($result)){
  		$json['appliancesName']=$row['title'];
  		$json['originalPrice']=$row['originalPrice'];
  		$json['currentPrice']=$row['currentPrice'];
  		$json['imgUrl']=$base.$row['imgsrc'];
  		$sql = "select imgsrc from img_resource where goodsid='".$row["id"]."'";
  		$result = mysql_query($sql,$con);
  		$json["imgUrlList"] = array();
  		$json['specification']=$row['canshu'];
  		while ($row=mysql_fetch_assoc($result)){
  			array_push($json["imgUrlList"], $base.$row["imgsrc"]);
  		}
  	}
  	echo json($json);
?>