<?php
	require "dbconnect.php";


	$idkhachhang = $_POST['idKhachHang'];
	$diachi = $_POST['address'];
	$sdt = $_POST['phone'];
	$hoten = $_POST['name'];


	$query ="insert into diachitungdonhang values (null ,'$idkhachhang','$diachi', '$sdt', '$hoten')";
	if (mysqli_query($connect, $query)) {
		echo "succes";
	}
	
?>