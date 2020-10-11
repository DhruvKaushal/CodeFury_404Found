<%@include  file="headerfile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" href="index.css">
</head>
<body>
  <div class="container">
	<hr>
	
	<h1 style="background-color:#303030;color:white;text-align:center;font-size:48px;padding:10px">Welcome To The AMP</h1>
	<hr>
	
	<!-- Product Slideshow -->
	<h1 style="background-color:grey;color:white;text-align:center">Our Products</h1>
	<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
  		<ol class="carousel-indicators">
    		<li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
    		<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
  		</ol>
  		<div class="carousel-inner">
    	<div class="carousel-item active">
      		<img class="d-block w-100" src="images/laptop.jpg" alt="First slide" class="responsive">
    	</div>
    	<div class="carousel-item">
      		<img class="d-block w-100" src="images/mobile.jpg" alt="Second slide" class="responsive">
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
	<h1 style="background-color:grey;color:white;text-align:center">About the Application</h1>
	<br>
	<p>Welcome to the Asset Management Portal, or the AMP as we like to call ourselves. This is your very own portal for borrowing essentials. From our wide range of products such as latops, mobiles, books etc. 
	   we shall ensure that your learning never stops! So log on and find your necessities, all at one place!</p>
	<hr/>
	<!-- Cards for login and registration functionality -->
	<div class="row" style="width:80%;margin:auto">
  	<div class="col-sm-4">
    	<div class="card">
      		<div class="card-body" style="margin:auto;">
        		<h5 class="card-title">New here? Click below to register!</h5>
        		<a href="registration.jsp" class="btn btn-danger" style="margin-left:33%">Register Now</a>
      		</div>
    	</div>
  	</div>
  	<div class="col-sm-4" style="margin:auto">
    	<div class="card">
    		<div class="card-body" style="margin:auto">
        		<h5 class="card-title">Already registered? Click below to login!</h5>
        		<a href="#" class="btn btn-danger" style="margin-left:40%">Login</a>
      		</div>
    	</div>
  	</div>
  	<div class="col-sm-4" style="margin:auto">
    	<div class="card">
    		<div class="card-body" style="margin:auto">
        		<h5 class="card-title">Upload a JSON File?</h5>
        		  <form action = "ImportServlet" method = "post" enctype = "multipart/form-data">
				      <div class="custom-file">
				         
				         <input type="file" class="custom-file-input" id="inputGroupFile02"  name = "file" size = "50">
				    	 <label class="custom-file-label" for="inputGroupFile02">Choose file</label>

				        <center> <button type = "submit" class="btn btn-danger" value = "Upload File" >Upload File</button></center>
				      </div>
			      </form>
      		</div>
    	</div>
  	</div>
	</div>
  </div>
</body>
</html>
<!-- added demo changes -->