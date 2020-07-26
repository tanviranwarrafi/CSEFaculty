<?php 

include 'connection.php';
 
 $name = $_POST['name'];
 $Id_no = $_POST['Id_no'];
 $contact = $_POST['contact'];
 $email = $_POST['email'];
 
 $sql="UPDATE student SET name='$name',contact='$contact',email='$email' where Id_no ='$Id_no'"; 
 
 if(mysqli_query($con,$sql)){
	echo json_encode(array('response' => 'success'));
 }else{
	echo json_encode(array('response' => 'error'));
 }
 
?>