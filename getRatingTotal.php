<?php
require "dbconnect.php";
	$iddichvu = $_POST['iddichvu'];
	$query = "select (sum(diemdanhgia) / count(diemdanhgia)) as total from danhgia
	where iddichvu = '$iddichvu'";
	$data = mysqli_query($connect, $query);
	if ($row = mysqli_fetch_assoc($data)) {
			echo $row['total'];
	}
?>