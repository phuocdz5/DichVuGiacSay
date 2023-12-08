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
	$idStore = $_POST['id'];
	$query = "select * from cuahang where idcuahang = $idStore";
	$data = mysqli_query($connect, $query);

	while ($row = mysqli_fetch_assoc($data)) {
		$store = new Store($row['idCuaHang'], $row['tenCuaHang'], $row['diaChi'], $row['email'], $row['sdt']);
		echo json_encode($store);

	}
?>