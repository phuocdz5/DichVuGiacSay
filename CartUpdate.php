<?php
	require "dbconnect.php";

	$soluong = $_POST['soluong'];
	$idgiohang = $_POST['idgiohang'];
	$query = "update giohang set soluong = '$soluong' where idgiohang = '$idgiohang'";

	if (mysqli_query($connect, $query)) {
		echo "success";
	}


?>