<?php
	class Store{
		public $idStore, $name, $address, $email, $phone;
		public function __construct($idStore, $name, $address, $email, $phone) {
        $this->idStore = $idStore;
        $this->name = $name;
        $this->address = $address;
        $this->email = $email;
        $this->phone = $phone;
    }
	}

	require "dbconnect.php";
	$query = "select * from cuahang";
	$data = mysqli_query($connect, $query);
	$arr = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arr, new Store($row['idCuaHang'], $row['tenCuaHang'], $row['diaChi'], $row['email'], $row['sdt']));

	}
		echo json_encode($arr);

?>