<?php
if(isset($_GET['email'])){
$email = $_GET['email'];
$con = mysqli_connect("mysql.phoenixnsec.com","phoenixar","tech.phoenix","phoenixdb1");

$q= "select * from marks where email ='".$email."'";
$qry ="select * from registration where email ='".$email."'";
$responsse = mysqli_query($con,$qry);
$res = mysqli_query($con,$q);
$response=array();

   if($row= mysqli_fetch_array($responsse)){
    $name=$row['name'];
    $phone=$row['phone'];
    $em=$row['email'];
    $institue=$row['institute'];
    $team=$row['team'];
    
    
    if($row= mysqli_fetch_array($res)){
        $marks=$row['marks'];
         $response[]=array('msg'=>'You Have Already given the Test.
Name:'.$name.'
Email:'.$email.'
Team Name:'.$team.'
Institute Name:'.$institue.'
Marks Obtained:'.$marks,'flag'=>'1');
 $fp = fopen('Quiz/'.$email.'.json','w');
        fwrite($fp,json_encode($response));
        fclose($fp);
        header("location:Quiz/".$email.".json");
        
    }
else
{
          $response[]=array('msg'=>'Welcome to Brainstormer 2015 App Quizz round.
Please Confirm your credentials and hit Ok to start the Quizz

Name:'.$name.'
Email:'.$email.'
Phone No.:'.$phone.'
Team Name:'.$team.'
Institute Name:'.$institue,'flag'=>'0');
 $fp = fopen('Quiz/'.$email.'.json','w');
        fwrite($fp,json_encode($response));
        fclose($fp);
        header("location:Quiz/".$email.".json");
    
}

}

else{

          $response[]=array('msg'=>'You Have not yet Registered.
Please Register Yourself First','flag'=>'1');
 $fp = fopen('Quiz/'.$email.'.json','w');
        fwrite($fp,json_encode($response));
        fclose($fp);
        header("location:Quiz/".$email.".json");}


}
?>