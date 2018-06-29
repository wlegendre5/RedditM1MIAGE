<!DOCTYPE html>
<html>

<head>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
  <script src="script.js"></script>
  <link rel="stylesheet" href="style.css">
</head>



<body>
  <h1>Welcome to our Reddit App</h1>
<!--
  <form method="POST" onsubmit="window.location.href = '/auth'; return false;">
    <input type="text" name="login" size="30" placeholder="add login">
    <input type="text" name="passwd" placeholder="add password">
    <input class="btn-primary" type="submit" value="Log In">
  </form>-->
  
<br>
  	<p colspan="2">Who are you ? :</p>
<br>
      <form action="auth" method="get"> 
    	    User:<br>
         <input type="text" name="userID" value="Votre nom d'user"><br>
         <input type="submit" value="Connexion" class="btn-primary"><br>
      </form>

</body>

</html>