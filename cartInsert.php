<?php
	require "dbconnect.php";

	$idkhachhang = $_POST['idkhachhang'];
	$iddichvu = $_POST['iddichvu'];
	$soluong = '1';

	$query = "insert into giohang values(null, '$idkhachhang', '$iddichvu', '$soluong')";


	if (mysqli_query($connect, $query)) {
		echo "success";
	}

?>