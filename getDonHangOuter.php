<?php
	require "dbconnect.php";
	 class XacNhan {
    public $id, $date, $ship;

    // Constructor
    public function __construct($id, $date, $ship) {
        $this->id = $id;
        $this->date = $date;
        $this->ship = $ship;
    }
}

	$username =  $_POST['username'];

	$query = "select d.iddonhang, d.phivanchuyen, d.ngaydathang  from donhang d
				join khachhang k on k.idkhachhang = d.idkhachhang
				where k.username = '$username' and d.trangthai = 'xacnhan'
				order by d.ngaydathang desc;";
	$data = mysqli_query($connect, $query);

	$mangHoaDon = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangHoaDon, new XacNhan($row['iddonhang'], $row['ngaydathang'],  $row['phivanchuyen']));
	}

	echo json_encode($mangHoaDon);


?>