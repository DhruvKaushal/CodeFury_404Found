function openForm1() {
	document.getElementById("myForm1").style.display = "flex";
}

function closeForm1() {
	document.getElementById("myForm1").style.display = "none";
}
	
function openForm2() {
  	document.getElementById("myForm2").style.display = "flex";
}


function closeForm2() {
  document.getElementById("myForm2").style.display = "none";
}
	
function openForm() {
  	document.getElementById("myForm").style.display = "flex";
}

function closeForm() {
	  document.getElementById("myForm").style.display = "none";
	}

function validateForm1(){
	var borrowTime = document.forms["addAssetTypeForm"]["borrow"].value;
	var ban = document.forms["addAssetTypeForm"]["ban"].value;
	var fine = document.forms["addAssetTypeForm"]["fine"].value;
	
	if(!(borrowTime % 1 === 0)){
		alert("The Lending period can just be a whole number !!!");
		return false;
	}
	if(!(ban % 1 === 0)){
		alert("The Ban period can just be a whole number!!!");
		return false;
	}
	if(isNan(fine)){
		alert("Please Enter a numeric value for Fine!!!");
		return false;
	}
}

function validateForm2(){
	
	var quantity = document.forms["addAssetForm"]["quantity"].value;

	if(!(quantity % 1 === 0 && quantity != 0 )){
		alert("The Quantity can be a numberic value greater than 0 !!!");
		return false;
	}
		
}