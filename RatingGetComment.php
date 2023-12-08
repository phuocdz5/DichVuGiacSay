<?php
	class DanhGia{
		public $username, $sao, $comment;
		public function __construct($username, $sao, $comment){
			$this->username = $username;
			$this->sao = $sao;
			$this->comment = $comment;

		}
	}

	$iddichvu = $_POST['iddichvu'];

	require "dbconnect.php";
	$query = "select k.username, dg.diemdanhgia, dg.binhluan from danhgia dg
		join khachhang k on k.idkhachhang = dg.idkhachhang 
		where dg.iddichvu ='$iddichvu'";

	$arr = array();

	$data = mysqli_query($connect, $query);

	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arr, new DanhGia($row['username'], $row['diemdanhgia'], $row['binhluan']));
	}
	echo json_encode($arr);

?>