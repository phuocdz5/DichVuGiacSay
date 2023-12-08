<?php
    require_once 'dbconnect.php';
    require 'PHPMailer/src/Exception.php';
    require 'PHPMailer/src/PHPMailer.php';
    require 'PHPMailer/src/SMTP.php';
    use PHPMailer\PHPMailer\PHPMailer;
    use PHPMailer\PHPMailer\SMTP;
    use PHPMailer\PHPMailer\Exception;
    $email = $_POST['email'];
    $sql ="SELECT * FROM khachhang WHERE email ='$email'";
    $check =mysqli_query($connect,$sql);
    $mail = array();
    if(mysqli_num_rows($check)===1){
        $mail = new PHPMailer(true);
        try {
            //Server settings
            $mail->isSMTP();                                            //Send using SMTP
            $mail->Host       = 'smtp.gmail.com';                     //Set the SMTP server to send through
            $mail->SMTPAuth   = true;                                   //Enable SMTP authentication
            $mail->Username   = 'phuocntpd07826@fpt.edu.vn';                     //SMTP username
            $mail->Password   = 'rdggsxrfxqzehgfl';                               //SMTP password
            $mail->SMTPSecure = 'tls';            //Enable implicit TLS encryption
            $mail->Port       = 587;              //TCP port to connect to; use 587 if you have set `SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS`

            //Recipients
            $mail->setFrom('phuocntpd07826@fpt.edu.vn', 'A-Z LAUNDRY');
            $mail->addAddress($email);     //Add a recipient
            $mail->Subject = 'FORGOT PASSWORD Dich Vu Giat Say';
            $mail->Body    = "Click here the Link Below: http://localhost/giatsay/resetpasswordform.php?key=$email";

            $mail->send();
            $msg['mail']='send';
            echo json_encode($msg);
        } catch (Exception $e) {
            echo "Message could not be sent. Mailer Error: {$mail->ErrorInfo}";
        }
    }else{
        $msg['mail']='notmail';
        echo json_encode($msg);
    }
   
?>