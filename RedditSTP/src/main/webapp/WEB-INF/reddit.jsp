<!DOCTYPE html>
<html ng-app="redditHomePage">

<head>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
  <script src="script.js"></script>
  <link rel="stylesheet" href="style.css">
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
</head>



<body ng-controller="HsController">
  <h1>Welcome to our Reddit App</h1>
  
<br>
  	<h1>TinyReddit :</h1> 
<br>

<ul>
    <li> <a href="topicsgeneration">Generer les topics</a></li>
  	<li><a href="usersgeneration">Generer les users</a></li>
    <li><a href="top10">Top10</a></li>
    <li><a href="getvotes">Mes Topics upvote</a></li>
    <li><a href="deco">Deconnexion</a></li>
  
  
</ul>
  
   <div class="col-md-6" style="padding-top: 10%;">
	  <div class="text-center pagination-centered">
   <form ng-submit="addTopic()">
     <div class="form-group">
    <input type="text" ng-model="slien" size="30" placeholder="add link">
    <input type="text" ng-model="stitle" size="30" placeholder="add title">
    </div>
    <div class="form-group">
    <input type="text" ng-model="sbody" style="width:300px; height:200px;" placeholder="add body">
    </div>
    <div class="form-group">
    <input class="btn-primary" type="submit" value="add">
    </div>
  </form>
  </div>
</div>
<div text-align="center" ng-repeat="x in Topics">
	<div class="jumbotron" style="background:#E6E6E6; ">
    <div class="row">
				<div class="col-md-12" style="color: Black; text-align:left ">
					<h2 id={{x.id}} class="lead">{{x.title}}</h2>
				</div>
				<div class="col-md-8">
					<p class="lead">{{x.body}}<span class="glyphicon glyphicon-arrow-right"></span></p>
				</div>
				<div class="col-md-4">
					<p class="lead">karma : {{x.karma}}</p>
				
				</div>
					<div class="col-md-2">
					      <form ng-submit="upvote(x.id)">
					<span class="glyphicon glyphicon-chevron-up"> <input class="btn-primary" type="submit" value="upvote"></span>
					    </form>
					</div>
				<div class="col-md-2">
					<span class="glyphicon glyphicon-chevron-down">    <form ng-submit="downvote(x.id)">
          <input class="btn-primary" type="submit" value="downvote">
    </form></span>
    	<p class="lead">écrit par : {{x.sender}}</p>
				</div>

			</div>
		</div>
  </div>


<script>
  var init = function() {
    console.log("init called");
    window.init();
  };
</script>

<script src="https://apis.google.com/js/client.js?onload=init"></script>


</body>

</html>