<?php 

include 'connection.php';

 $Id_no = $_POST['Id_no'];
 $pass = $_POST['pass'];

 $sql = "SELECT * FROM student WHERE Id_no='$Id_no' && pass='$pass'";
 
 $result=mysqli_query($con,$sql);
 
 if(mysqli_num_rows($result)>0){
	//echo "Success";
	echo json_encode(array('response' => 'success'));
 }else{
	//echo "Error";
	echo json_encode(array('response' => 'error'));
 }
 
?>