<?php
session_start();
$con = mysql_connect("mysql.phoenixnsec.com","phoenixar","tech.phoenix");
if(!$con)
	die(mysql_error().'could not connect');

$db = mysql_select_db("phoenixdb1");
if(!$db)
	die(mysql_error().'could not select');

$name = $_SESSION['name'];
$email = $_SESSION['email'];
$team = $_SESSION['team'];
$phone = $_SESSION['phone'];
$type = $_SESSION['type'];
$institute = $_SESSION['institute'];
session_destroy();

$a =0;
$f=0;
for($i=0;$i<=5;$i++){
    $n=rand(0,9);
    $a=$a*10+$n;
       }
$qry = "insert into registration values('$name','$email', '$team', '$phone', '$type', '$institute')";
$qry2 = "insert into regid values('$email',$a)";
mysql_query($qry2);
$res = mysql_query($qry);

if($res){
	
    $response[]=array('res'=>'You are successfully Registered');
 $fp = fopen('Quiz/'.$email.'.json','w');
        fwrite($fp,json_encode($response));
        fclose($fp);
        header("location:Quiz/".$email.".json");
    }
else{

$response[]=array('res'=>'Something went wrong Please try again later');
 $fp = fopen('Quiz/'.$email.'.json','w');
        fwrite($fp,json_encode($response));
        fclose($fp);
        header("location:Quiz/".$email.".json");
        }

session_destroy();
?>

