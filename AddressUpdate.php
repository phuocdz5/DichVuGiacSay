
<?php
	require "dbconnect.php";
	
	$id = $_POST['idAddress'];
	$name = $_POST['name'];
	$phone = $_POST['phone'];
	$address = $_POST['address'];


	$query = "UPDATE diachitungdonhang 
          SET diachi = '$address', sdt = '$phone', hoten = '$name' 
          WHERE iddctdh = '$id'";

	$data = mysqli_query($connect, $query);
	if ($data) {
		echo "success";
	}else{
		echo "fail";
	}


?>