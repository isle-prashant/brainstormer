<form name="f1" method="post" action="Qindex.php">
<input type="text" name="user" placeholder="Username"/>
<br />
<input type="password" name="pass" placeholder="Password" />
<br />
<input type="submit" value="login"/>

</form>


<?php
session_start();

if (isset($_POST['user'])){
    $u=$_POST['user'];
    $p=$_POST['pass'];

    if($u=="admin"&&$p=="isle"){
        echo "loged in";
        header("location:Quizz.php");
        $_SESSION['user']="admin";
    }
    else echo "Invalid Userid or Password";
}



?>