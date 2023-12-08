<?php
	require "dbconnect.php";
	class OrderDetail{
		public $name, $idOrder, $quantity, $price, $status, $idStore, $idService, $img;
		public function __construct($name, $idOrder, $quantity, $price, $status, $idStore, $idService, $img){
			$this->name = $name;
	        $this->idOrder = $idOrder;
	        $this->quantity = $quantity;
	        $this->price = $price;
	        $this->status = $status;
	        $this->idStore = $idStore;
	        $this->idService = $idService;
			$this->img = $img;
		}
	}
	$username = $_POST['username'];

	$query = "
select d.tendichvu, h.iddonhang, c.soluong, (c.soluong * d.giatien) as tongtien , h.trangthai, h.idcuahang, c.iddichvu, d.img from 	
				chitietdonhang c 
				join dichvu d on d.iddichvu = c.iddichvu
				join donhang  h on h.iddonhang = c.iddonhang
				join khachhang k on k.idkhachhang = h.idkhachhang
				where k.username = '$username' and h.trangthai = 'hoanthanh'
				order by h.ngaydathang desc;";

	$data = mysqli_query($connect, $query);
	$arr = array();

	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arr, new OrderDetail($row['tendichvu'] ,$row['iddonhang'] ,$row['soluong'] ,$row['tongtien'] ,$row['trangthai'], $row['idcuahang'], $row['iddichvu'], $row['img'] ));
	}

	echo json_encode($arr);


?>