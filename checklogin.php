<?php
	require "dbconnect.php";
	
	class User {
		public $id, $name, $address, $email, $phone; 
		public function __construct($id,$name, $address, $email, $phone){
			$this->id = $id;
			$this->name = $name;
			$this->address = $address;
			$this->email = $email;
			$this->phone = $phone;
		}
	}
	
	$us = $_POST['username'];
	$pw = $_POST['password'];
	$result = array();
	$query = "SELECT * FROM khachhang k WHERE k.username = '$us' AND k.password ='$pw'";
	$data = mysqli_query($connect, $query);

	if (mysqli_num_rows($data) === 1) {
		$result['status'] = "success";
		$userData = array();

		while ($row = mysqli_fetch_assoc($data)) {
			$user = new User($row['idKhachHang'], $row['hoTen'], $row['diaChi'], $row['email'], $row['sdt']);
			$userData[] = $user;
		}

		$result['users']=$userData;
		echo json_encode($result);
	} else {
		$result['status'] = "error";
		echo json_encode($result);
	}
?>
