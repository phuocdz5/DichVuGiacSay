<?php
require "dbconnect.php";

    $idDonHang = $_POST['idDonHang'];
    $idkhachhang = $_POST['idkhachhang'];
    $ngaydathang = $_POST['ngaydathang'];
    $tongtien = $_POST['tongtien'];
    $phivanchuyen = '15000';
    $trangthai = 'xacnhan';
    $hoten = $_POST['hoten'];
    $sdt = $_POST['sdt'];
    $diachi = $_POST['diachi'];

$query  = "INSERT INTO donhang VALUES ('$idDonHang', '$idkhachhang', '$ngaydathang', null, '$tongtien', '$phivanchuyen', '$trangthai', null, '$hoten', '$sdt', '$diachi')";

$data = mysqli_query($connect, $query);

if ($data) {
    echo "success";
}
?>
