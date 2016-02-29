<?php
session_start();
$_SESSION['name']=$_GET['name'];
$_SESSION['email'] = $_GET['email'];
$_SESSION['team']= $_GET['team'];
$_SESSION['phone']  = $_GET['phone'];
$_SESSION['type'] =$_GET['type'];
$_SESSION['institute']=$_GET['institute'];

$email = $_SESSION['email'];
$phone=$_SESSION['phone'];
$con = mysqli_connect("mysql.phoenixnsec.com","phoenixar","tech.phoenix","phoenixdb1");
$q= "select * from registration where email ='".$email."' or phone ='".$phone."'";
$res = mysqli_query($con,$q);
$response=array();
if(!$row= mysqli_fetch_array($res)){
header("location:registration.php");
}
else{
 $response[]=array('res'=>'This Email/Phone Number
 has Already been Registered');
 $fp = fopen('Quiz/'.$email.'.json','w');
        fwrite($fp,json_encode($response));
        fclose($fp);
        header("location:Quiz/".$email.".json");}

?>