<?php
	require "dbconnect.php";
	class Address{
		public $id,  $name, $phone, $address;
		public function __construct($id, $name, $phone, $address){
			$this->id = $id;
			$this->name = $name;
	        $this->phone = $phone;
	        $this->address = $address;
		}
	}
	$username = $_POST['username'];

	$query = "select dc.iddctdh, dc.hoten, dc.sdt, dc.diachi from diachitungdonhang dc
				join khachhang k on k.idkhachhang = dc.idkhachhang
				where k.username = '$username'
				";

	$data = mysqli_query($connect, $query);
	$arr = array();

	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arr, new Address($row['iddctdh'], $row['hoten'] ,$row['sdt'] ,$row['diachi'] ));
	}

	echo json_encode($arr);


?>