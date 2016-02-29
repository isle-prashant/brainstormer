<script language="javascript">
function isEmpty(id)
{
	if(document.getElementById(id).value.length==0)
	{
		return true;
	}
	else
	{
		return false;
	}
}


function validate()
{
    if(isEmpty('ques'))
    {
    alert("question can't be blank");
    return false;
    }

    if(isEmpty('optA')||isEmpty('optB')||isEmpty('optC')||isEmpty('optD'))
    {
    alert("Option can't be blank");
    return false;
    }
    
    
   
   
}

</script>
<form name="f1" method="post" action="Quizz.php">
 <textarea  name="ques" id="ques" rows="2" cols="80" placeholder="Question Goes here"></textarea>
<br /><br />
<input type="text" name="optA" size="50" id="optA"placeholder="Option A goes here"/>&nbsp;&nbsp;
<input type="text" name="optB" size="50"  id="optB" placeholder="Option B goes here"/>
<br /><br /> 
<input type="text" name="optC" size="50"  id="optC" placeholder="Option C goes here"/>&nbsp;&nbsp;
<input type="text" name="optD" size="50" id="optD" placeholder="Option D goes here"/>
<br /><br />
<input type="radio" id="0" name="ans" value="0" />OptionA &nbsp; &nbsp;
<input type="radio" id="1" name="ans" value="1" />OptionB &nbsp; &nbsp;
<input type="radio" id="2" name="ans" value="2" />OptionC &nbsp; &nbsp;
<input type="radio" id="3" name="ans" value="3" />OptionD
<br /><br /> <br /><br /> 
<input type="submit" value="Submit" onclick="return validate();" />
<br /><br />
<input type="submit" value="Logout" name="logout"/>

</form>

<?php
session_start();
if(!isset($_SESSION['user']))
{
    header("location:Qindex.php");
}
if(isset($_POST['logout'])){
    session_destroy();
header("location:Qindex.php");
}
if(isset($_POST['ques'])){
    $con=mysqli_connect("mysql.phoenixnsec.com","phoenixar","tech.phoenix","phoenixdb1");
    $qry = "insert into questions values('".$_POST['ques']."','".$_POST['optA']."','".$_POST['optB']."','".$_POST['optC']."','".$_POST['optD']."','".$_POST['ans']. "')";
    mysqli_query($con,$qry);
    
    $q= "select * from questions";
    $result=mysqli_query($con,$q);
    $responsse = array();
    $posts = array();
    while($row=mysqli_fetch_array($result))
    {
        $Q = $row['Q'];
        $A = $row['A'];
        $B = $row['B'];
        $C = $row['C'];
        $D = $row['D'];
        $correct = $row['Correct'];
        $posts[] = array('Q'=>$Q,'A'=>$A,'B'=>$B,'C'=>$C,'D'=>$D,'correct'=>$correct);
       

    }
    
     $responsse=$posts;
        $fp = fopen('questions.json','w');
        fwrite($fp,json_encode($responsse));
        fclose($fp);
    
    echo $_POST['ques']."<br/>";
    echo $_POST['ans'];
}

?>