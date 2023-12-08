<?php
require "dbconnect.php";

    $idDonHang = $_POST['idDonHang'];
    $iddichvu = $_POST['idDichVu'];
    $soluong = $_POST['soLuong'];

$query  = "INSERT INTO chitietdonhang VALUES ( '' ,'$idDonHang', '$iddichvu', '$soluong')";

$data = mysqli_query($connect, $query);

if ($data) {
    echo "success";
}
?>
