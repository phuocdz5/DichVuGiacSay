<?php
	class Cart {
    public $idCart, $idService, $quantity, $price, $name, $description, $img;

    public function __construct($idCart, $idService, $quantity, $price, $name, $description, $img) {
        $this->idCart = $idCart;
        $this->idService = $idService;
        $this->quantity = $quantity;
        $this->price = $price;
        $this->name = $name;
        $this->description = $description;
        $this->img = $img;
    }
}
 	require "dbconnect.php";
 	$username  = $_POST['username'];
 	$query = "select g.idgiohang, g.iddichvu, sum(g.soluong) as soluong, d.giatien, d.tendichvu, d.mota, d.img from giohang g
                    join dichvu d on g.iddichvu = d.iddichvu
                    join khachhang k on k.idkhachhang = g.idkhachhang
                    where k.username = '$username'
                    group by g.iddichvu";

 	$data = mysqli_query($connect, $query);
 	$arr = array();
 	while ($row = mysqli_fetch_assoc($data)) {
 		array_push($arr, new Cart($row['idgiohang'], $row['iddichvu'], $row['soluong'], $row['giatien'], $row['tendichvu'], $row['mota'], $row['img']));
 	}
 	echo json_encode($arr);
?>