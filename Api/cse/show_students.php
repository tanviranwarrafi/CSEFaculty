<?php

include 'connection.php';

$batch = $_POST['batch'];

$sql = "SELECT * FROM student WHERE batch='$batch'";

$res = mysqli_query($con,$sql);

$result = array();

while($row = mysqli_fetch_array($res)){
		array_push($result,array(
				"name"=>$row["name"],
				"Id_no"=>$row["Id_no"],
				"reg_no"=>$row["reg_no"],
				"pass"=>$row["pass"],
				"sessioN"=>$row["sessioN"],
				"batch"=>$row["batch"],
				"contact"=>$row["contact"],
				"email"=>$row["email"]
			)
		);
	}
	echo json_encode(array("result"=>$result));

?>