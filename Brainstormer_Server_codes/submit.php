<?php
$email= $_GET['email'];
$marks = $_GET['marks'];
$con=mysqli_connect("mysql.phoenixnsec.com","phoenixar","tech.phoenix","phoenixdb1");
$qry="insert into marks (email,marks) values ('$email','$marks')";
mysqli_query($con,$qry);


?>