<?php
	require "dbconnect.php";
	 class XacNhan {
    public $quantity, $price, $img, $name, $description, $address;

    // Constructor
    public function __construct($quantity, $price, $img, $name, $description, $address) {
        $this->quantity = $quantity;
        $this->price = $price;
        $this->img = $img;
        $this->name = $name;
        $this->description = $description;
        $this->address = $address;
    }
}

$iddonhang = $_POST['iddonhang'];

	$query = "select c.idctdh, c.soluong, dv.giatien , dv.img, dv.tendichvu,  dv.mota, d.diachi from chitietdonhang c
					join donhang d on d.iddonhang = c.iddonhang
					join dichvu dv on dv.iddichvu = c.iddichvu
					where d.idDonHang = '$iddonhang'
					order by d.ngaydathang desc;";
	$data = mysqli_query($connect, $query);

	$mangHoaDon = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangHoaDon, new XacNhan( $row['soluong'], $row['giatien'], $row['img'], $row['tendichvu'], $row['mota'], $row['diachi'] ));
	}

	echo json_encode($mangHoaDon);


?>