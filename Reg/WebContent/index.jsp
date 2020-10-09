<%@include  file="headerfile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</head>
<body>
	<hr>
	<h1 style="background-color:grey;color:white;text-align:center;font-size:48px;padding:10px">Welcome To The E-Asset Management Portal</h1>
	<br>
	<h1 style="background-color:grey;color:white;text-align:center">About the Application</h1>
	<br>
	<p>Your very own portal for borrowing essentials. From our wide range of products such as latops, mobiles, books etc. 
	   we shall ensure that your learning never stops! So log on and find your necessities, all at one place!</p>
	<br>
	<!-- Product Slideshow -->
	<h1 style="background-color:grey;color:white;text-align:center">OUR PRODUCTS</h1>
	<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" style="height:600px;width:80%; margin:Auto">
  		<ol class="carousel-indicators">
    		<li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
    		<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
  		</ol>
  		<div class="carousel-inner">
    	<div class="carousel-item active">
      		<img class="d-block w-100" style="height: 700px;" src="images/laptop.jpg" alt="First slide" style="">
    	</div>
    	<div class="carousel-item">
      		<img class="d-block w-100" style="height: 700px;" src="images/mobile.jpg" alt="Second slide">
    	</div>
  	</div>
  	<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
    	<span class="carousel-control-prev-icon" aria-hidden="true"></span>
    	<span class="sr-only">Previous</span>
  	</a>
  	<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
    	<span class="carousel-control-next-icon" aria-hidden="true"></span>
    	<span class="sr-only">Next</span>
  	</a>
	</div>
	<br>
	<br>
	<br>
	<br>
	<hr/>
	<!-- Cards for login and registration functionality -->
	<div class="row" style="width:80%;margin:auto">
  	<div class="col-sm-6">
    	<div class="card">
      		<div class="card-body" style="margin:auto;">
        		<h5 class="card-title">New here? Click below to register!</h5>
        		<a href="registration.jsp" class="btn btn-danger" style="margin-left:33%">Register Now</a>
      		</div>
    	</div>
  	</div>
  	<div class="col-sm-6" style="margin:auto">
    	<div class="card">
    		<div class="card-body" style="margin:auto">
        		<h5 class="card-title">Already registered? Click below to login!</h5>
        		<a href="#" class="btn btn-danger" style="margin-left:33%">Login</a>
      		</div>
    	</div>
  	</div>
	</div>
	<a href="registration.jsp">here</a>
</body>
</html>
<!-- added demo changes -->