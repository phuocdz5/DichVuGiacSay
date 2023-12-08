<?php
require "dbconnect.php";


$idkhachhang = $_POST['idkhachhang'];
$iddichvu = $_POST['iddichvu'];
$query = "DELETE FROM giohang WHERE idkhachhang = '$idkhachhang' and iddichvu = '$iddichvu'";

$result = mysqli_query($connect, $query);

if ($result) {
    echo "success";
} else {
    echo "error";
}
?>
