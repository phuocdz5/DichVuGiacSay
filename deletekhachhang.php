<?php
require "dbconnect.php";

$email = $_POST['email'];
$result = array();

// Kiểm tra xem người dùng với email cụ thể có tồn tại hay không
$query = "SELECT * FROM khachhang WHERE email = '$email'";
$data = mysqli_query($connect, $query);

if (mysqli_num_rows($data) === 1) {
    // Tạm thời tắt kiểm tra khóa ngoại
    mysqli_query($connect, "SET foreign_key_checks = 0");

    // Xóa người dùng từ bảng khachhang
    $deleteQuery = "DELETE FROM khachhang WHERE email = '$email'";
    if (mysqli_query($connect, $deleteQuery)) {
        echo "Xoá tài khoản thành công";
    } else {
        echo "Lỗi xoá người dùng: " . mysqli_error($connect);
    }

    // Bật lại kiểm tra khóa ngoại
    mysqli_query($connect, "SET foreign_key_checks = 1");
}