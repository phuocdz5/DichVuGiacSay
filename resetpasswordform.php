<?php
    require_once 'dbconnect.php';
    $email = $_GET['key'];
    $sql = "SELECT * FROM khachhang WHERE email ='$email'";
    $result =mysqli_query($connect,$sql);
    $thongbao="";
    
    if(mysqli_num_rows($result)===1){
        if(isset($_POST['submit'])){
            $password = $_POST['password'];
            $cPassword = $_POST['cpassword'];
            if($password =="" || $cPassword==""){
                $thongbao= "Không được để trống"; 
            }else{
                if($password == $cPassword){
                    $update = "UPDATE khachhang SET password='$password' WHERE email ='$email'";
                    if(mysqli_query($connect,$update)){
                        $thongbao= "Cập nhật mật khẩu thành công";
                    }
                }else{
                    $thongbao= "Mật khẩu không trùng khớp";
                }
            }
        }
    }
    
?>
<!DOCTYPE html>
<html>

    <head>
        <title>Cập nhật mật khẩu mới</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link href="//fonts.googleapis.com/css?family=Quicksand:300,400,500,700" rel="stylesheet">
        <style>
        body {
            background: url("./images/b2.jpg");
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-position: center;
            background-size: cover;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            color: #FFF;
            font-family: 'Quicksand', sans-serif;
            text-align: center;
        }

        body a {
            transition: 0.5s all;
            -webkit-transition: 0.5s all;
            -moz-transition: 0.5s all;
            -o-transition: 0.5s all;
            -ms-transition: 0.5s all;
            text-decoration: none;
        }

        input[type="button"], input[type="submit"] {
            transition: 0.5s all;
            -webkit-transition: 0.5s all;
            -moz-transition: 0.5s all;
            -o-transition: 0.5s all;
            -ms-transition: 0.5s all;
        }

        h1 {
            font-size: 45px;
            font-weight: 300;
            color: #fff;
            letter-spacing: 7px;
            margin: 65px auto;
        }

        .w3layoutscontaineragileits {
            width: 30%;
            margin: 0 auto;
            background: rgba(0, 0, 0, 0.5);
            padding: 30px;
        }

        h2 {
            font-size: 35px;
            font-weight: 300;
            margin-bottom: 30px;
            color: #fff;
            text-transform: uppercase;
        }

        input[type="text"], input[type="password"]{
            width: 84%;
            padding: 15px 10px 15px;
            font-size: 14px;
            background: transparent;
            border: 2px solid #537b35;
            outline: none;
            margin-bottom: 26px;
            color: #fff;
            font-family: 'Quicksand', sans-serif;
        }
        input[type="text"]{
            background: url("../images/user.png") no-repeat 382px 11px;
            background-size: 25px;
        }
        input[type="submit"] {
            font-size: 16px;
            padding: 10px 65px;
            background-color: #537b35;
            color: #FFF;
            border: none;
            border-radius: 0px;
            outline: none;
            float:none;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #fff;
            color: #537b35;
        }

        .aitssendbuttonw3ls p {
            text-align: center;
            margin-top: 40px;
            text-transform: capitalize;
            letter-spacing: 1px;
        }

        .aitssendbuttonw3ls p a {
            color: #fff;
            font-weight: 600;
            padding: 8px 20px;
            border: 1px solid #fff;
        }

        .aitssendbuttonw3ls p a span {
            font-size: 18px;
        }

        .aitssendbuttonw3ls p a:hover {
            color: #537b35;
            border: 1px solid #537b35;
        }

        ::-webkit-input-placeholder {
            color: #FFF;
        }

        ::-moz-placeholder {
            color: #FFF;
        }

        ::-moz-placeholder {
            color: #FFF;
        }

        ::-ms-input-placeholder {
            color: #FFF;
        }

        .w3footeragile {
            margin: 100px 0px 20px;
        }

        .w3footeragile p a {
            color: #000;
            font-weight:700;
        }

        .w3footeragile p {
            letter-spacing: 2px;
            color: #fff;
        }

        .w3footeragile p a:hover {
            color: #fff;
        }

        /*--- Responsive Code ---*/

        @media screen and (max-width: 1440px) {
            .w3layoutscontaineragileits {
                width: 33%;
            }
        }

        @media screen and (max-width: 1366px) {
            .w3layoutscontaineragileits {
                width: 35%;
            }
            .w3footeragile {
                margin: 100px 10px 20px;
            }
        }

        @media screen and (max-width: 1280px) {
            .w3layoutscontaineragileits {
                width: 37%;
            }
        }

        @media screen and (max-width: 1080px) {
            .w3layoutscontaineragileits {
                width: 45%;
            }
        }

        @media screen and (max-width: 1024px) {
            h1 {
                font-size: 45px;
                margin: 50px auto;
            }
            .w3layoutscontaineragileits {
                width: 47%;
            }
            .w3footeragile {
                margin: 50px 10px 20px;
            }
        }

        @media screen and (max-width: 991px) {
            .w3layoutscontaineragileits {
                width: 48%;
            }
        }
        @media screen and (max-width: 900px) {
            .w3layoutscontaineragileits {
                width:53%;
            }
        }

        @media screen and (max-width: 853px) {
            .w3layoutscontaineragileits {
                width: 58%;
            }
        }

        @media screen and (max-width: 800px) {
            .w3layoutscontaineragileits {
                width: 59%;
            }
        }

        @media screen and (max-width: 768px) {
            .w3layoutscontaineragileits {
                width: 61%;
            }
        }

        @media screen and (max-width: 736px) {
            h1 {
                font-size: 40px;
                margin: 40px auto;
            }
            .w3layoutscontaineragileits {
                width: 65%;
            }
            h2 {
                font-size: 35px;
            }
            .aitssendbuttonw3ls p {
                font-size: 14px;
            }
        }

        @media screen and (max-width: 667px) {
            .w3layoutscontaineragileits {
                width: 70%;
            }
            .w3layoutscontaineragileits input[type="text"],
            .w3layoutscontaineragileits input[type="password"],
            .w3layoutscontaineragileits input[type="email"] {
                width: 87%;
            }
            .w3footeragile p {
                letter-spacing: 1px;
                line-height: 24px;
            }
        }

        @media screen and (max-width: 640px) {
            h1 {
                margin: 35px auto;
            }
            .w3layoutscontaineragileits {
                width: 74%;
            }
        }

        @media screen and (max-width: 603px) {
            .w3layoutscontaineragileits {
                width: 78%;
            }
        }

        @media screen and (max-width: 600px) {
            .w3layoutscontaineragileits {
                width: 78%;
            }
            .w3layoutscontaineragileits input[type="text"],
            .w3layoutscontaineragileits input[type="password"],
            .w3layoutscontaineragileits input[type="email"] {
                width: 87%;
            }
            .w3footeragile p {
                line-height: 24px;
            }
        }

        @media screen and (max-width: 568px) {
            h1 {
                font-size: 35px;
                margin: 25px auto;
            }
            h2 {
                font-size: 30px;
            }
            .w3layoutscontaineragileits input[type="text"],
            .w3layoutscontaineragileits input[type="password"],
            .w3layoutscontaineragileits input[type="email"] {
                width: 92%;
            }
            ul.tick {
                margin-bottom: 20px;
            }
            ul.tick li input[type="checkbox"]+label {
                font-size: 15px;
            }
            ul.tick li input[type="checkbox"]+label span:first-child {
                width: 15px;
                height: 15px;
                top: 0;
                border: 1px solid #EEE;
            }
            input[type="submit"] {
                font-size: 15px;
                padding: 10px 40px;
            }
            .w3footeragile p {
                font-size: 14px;
            }
            .contact-form1 {
                padding: 60px 33px;
            }
        }

        @media screen and (max-width: 480px) {
            h1 {
                font-size: 35px;
                margin: 25px auto;
                letter-spacing: 4px;
            }
            .w3layoutscontaineragileits {
                width: 70%;
            }
            .aitssendbuttonw3ls {
                text-align: center;
            }
            ul.agileinfotickwthree {
                padding-left: 0;
            }
            .aitssendbuttonw3ls p {
                float: left;
            }
            .w3layoutscontaineragileits input[type="text"],
            .w3layoutscontaineragileits input[type="password"],
            .w3layoutscontaineragileits input[type="email"] {
                width: 94%;
                padding: 13px 8px 13px;
            }
            input[type="email"] {
                background: url(../images/e-mail.png) no-repeat 300px 11px;
                background-size: 23px;
            }
            input[type="password"] {
                background: url(../images/password.png) no-repeat 300px 11px;
                background-size: 23px;
            }
            ul.agileinfotickwthree a {
                margin-right: 5px;
            }
            ul.agileinfotickwthree li input[type="checkbox"]+label span:first-child{
                left:5px;
            }
            ul.agileinfotickwthree li input[type="checkbox"]+label {
                padding-left: 35px;
            }
            input[type="text"] {
                background: url(../images/user.png) no-repeat 300px 11px;
                background-size: 25px;
            }
        }

        @media screen and (max-width: 414px) {
            .w3layoutscontaineragileits {
                width: 70%;
            }
            h1 {
                font-size: 30px;
                letter-spacing: 3px;
            }
            input[type="email"] {
                background: url(../images/e-mail.png) no-repeat 255px 11px;
                background-size: 23px;
            }
            input[type="password"] {
                background: url(../images/password.png) no-repeat 255px 11px;
                background-size: 23px;
            }
            .aitssendbuttonw3ls p {
                font-size: 13px;
            }
            .aitssendbuttonw3ls p a {
                font-weight: 500;
                padding: 6px 5px;
            }
            
            .w3footeragile p {
                letter-spacing: 0px;
            }
            input[type="text"] {
                background: url(../images/user.png) no-repeat 254px 11px;
                background-size: 25px;
            }

            .contact-form1 {
                padding: 60px 33px;
            }
            h2 {
                font-size: 28px;
            }
            .w3footeragile {
                margin: 20px 10px;
            }
        }

        @media screen and (max-width: 384px) {
            h1 {
                letter-spacing: 0;
            }
            .w3layoutscontaineragileits {
                width: 78%;
            }
            input[type="text"], input[type="password"], input[type="email"] {
                font-size: 13px;
            }
            .contact-form1 {
                padding: 50px 18px;
            }
            h2 {
                font-size: 26px;
            }
        }
        @media screen and (max-width: 375px) {
            .w3layoutscontaineragileits {
                width: 80%;
            }
            .contact-form1 {
                padding: 60px 14px;
            }
            h2 {
                font-size: 26px;
            }
        }
        @media screen and (max-width: 320px) {
            h1 {
                font-size: 28px;
                font-weight: 500;
            }
            h2 {
                font-size: 25px;
            }
            .w3layoutscontaineragileits {
                width: 86%;
            }
            .w3layoutscontaineragileits {
                padding: 20px 15px;
            }
            input[type="email"] {
                background: url(../images/e-mail.png) no-repeat 230px 11px;
                background-size: 23px;
            }
            input[type="password"] {
                background: url(../images/password.png) no-repeat 230px 11px;
                background-size: 23px;
            }
            ul.agileinfotickwthree li input[type="checkbox"]+label span:first-child {
                width: 13px;
                height: 13px;
                top: 0px;
                left:56px;
            }
            ul.agileinfotickwthree li input[type="checkbox"]+label {
                padding-left: 85px;
            }
            ul.agileinfotickwthree a {
                margin-right: 72px;
                margin-top: 9px;
            }
            ul.agileinfotickwthree {
                margin-bottom: 15px;
            }
            .aitssendbuttonw3ls p {
                float: none;
                text-align: center;
                font-size: 14px;
                margin-top: 20px;
            }
            .aitssendbuttonw3ls p a,.aitssendbuttonw3ls p a:hover {
                margin-left: 13px;
                border: none;
            }
            input[type="text"] {
                background: url(../images/user.png) no-repeat 229px 11px;
                background-size: 25px;
            }
            

        }

        </style>
    </head>

    <body>

        <h1>A-Z LAUNDRY</h1>

        <div class="w3layoutscontaineragileits">
        <h2>Cập nhật mật khẩu mới</h2>
            <form action="" method="POST">
                <input type="password" Name="password" placeholder="Nhập mật khẩu mới" >
                <input type="password" Name="cpassword" placeholder="Nhập lại mật khẩu" >
                <div class="aitssendbuttonw3ls">
                    <h2 style= "color:rgba(255, 0, 0, 1);  font-size: 15px;">
                        <?php
                            if (isset($thongbao)&&($thongbao)!="") {
                                echo $thongbao;
                            }
                        ?>
                    </h2>
                    <input type="submit" name="submit" value="ĐỒNG Ý">
                    <div class="clear"></div>
                </div>
            </form>
        </div>
        

    </body>

</html>
