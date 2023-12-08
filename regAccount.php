<?php 
    require "dbconnect.php";

    $hoten = $_POST['name'];
    $email = $_POST['email'];
    $sdt = $_POST['phone'];
    $username = $_POST['username'];
    $password = $_POST['password'];
    $msg = array();

    // Truy vấn INSERT
    $insertQuery = "INSERT INTO khachhang VALUES (null, '$hoten', null, '$email', '$sdt', '$username', '$password')";

    // Truy vấn SELECT
    $selectQueryEmail = "SELECT * FROM khachhang k WHERE k.email ='$email'";
	$selectQueryUsername = "SELECT * FROM khachhang k WHERE k.username ='$username'";

    // Kiểm tra xem email và username đã tồn tại chưa
    $checkEmail = mysqli_query($connect, $selectQueryEmail);
    $checkUsername = mysqli_query($connect, $selectQueryUsername);

    if (mysqli_num_rows($checkEmail) !== 0 || mysqli_num_rows($checkUsername)!==0) {
        $msg['status'] = "error";
        echo json_encode($msg);
    } else {
        // Thực thi truy vấn INSERT nếu email và username chưa tồn tại
        if (mysqli_query($connect, $insertQuery)) {
            $msg['status'] = "success";
            echo json_encode($msg);
        }
    }
?>
