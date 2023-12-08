<?php
	require "dbconnect.php";

	$id = $_POST['id'];
	$query = "delete from diachitungdonhang where iddctdh = $id";

	$data = mysqli_query($connect, $query);

	if ($data) {
		echo "success";
	}



?>