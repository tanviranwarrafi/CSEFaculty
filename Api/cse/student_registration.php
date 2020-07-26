<?php 

include 'connection.php';
 
 $name = $_POST['name'];
 $Id_no = $_POST['Id_no'];
 $reg_no = $_POST['reg_no'];
 $pass = $_POST['pass'];
 $sessioN = $_POST['sessioN'];
 $batch = $_POST['batch'];
 $contact = $_POST['contact'];
 $email = $_POST['email'];
 
 $sql = "insert into student (name,Id_no,reg_no,pass,sessioN,batch,contact,email) values('$name','$Id_no','$reg_no','$pass','$sessioN','$batch','$contact','$email')";
 
 if(mysqli_query($con,$sql)){
	echo json_encode(array('response' => 'success'));
 }else{
	echo json_encode(array('response' => 'error'));
 }
 
?>