<?php
	require "dbconnect.php";

	$username = $_POST['username'];
	$name = $_POST['name'];
	$phone = $_POST['phone'];
	$email = $_POST['email'];
	$address = $_POST['address'];

	$query = "update khachhang set 
	hoten = '$name',
	diachi = '$address',
	email = '$email',
	sdt = '$phone'
	where username = '$username'
	";


	if (mysqli_query($connect, $query)) {
		echo "success";
	}

?>