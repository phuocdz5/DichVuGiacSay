<?php
	require "dbconnect.php";

	$idkhachhang = $_POST['idkhachhang'];
	$iddichvu = $_POST['iddichvu'];
	$nhanxet = $_POST['nhanxet'];
	$sao = $_POST['sao'];
	$query = "insert into danhgia values (null , '$idkhachhang', '$sao', '$nhanxet', '$iddichvu')";

	if (mysqli_query($connect, $query)) {
		echo "success";
	}

?>