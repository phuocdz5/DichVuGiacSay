<?php
	//lấy giá trị của biến username và oldpassword và newpassword
	//thông qua phương thức POST
	$username = $_POST['username'];
	$oldpw = $_POST['oldpassword'];
	$newpw = $_POST['newpassword'];
	// để kết nối đến cơ sở dữ liệu thông qua file dbconnect.php
	require "dbconnect.php";

	//khai báo câu truy vấn SQL để cập nhật mật khẩu mới cho tài khoản khách hàng username và oldpw
	$query = "update khachhang set password = '$newpw' where username = '$username' and password = '$oldpw'";

	// sử dụng hàm mysqli_query() để thực thi câu truy vấn SQL và trả về kết quả.
	if (mysqli_query($connect, $query)) {
		echo "ok";
	}else{
		echo "fail";
	}


?>