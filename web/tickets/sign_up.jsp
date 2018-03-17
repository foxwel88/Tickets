<!DOCTYPE html>
<%@ page import="edu.nju.tickets.model.User" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tickets - 个人中心</title>
    

    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/sweet-alert.css">
    <link rel="stylesheet" href="../css/owl.carousel.css">
    <link rel="stylesheet" href="../style.css">
    <link rel="stylesheet" href="../css/my.css">
    <link rel="stylesheet" href="../css/dl.css">
    <link rel="stylesheet" href="../css/responsive.css">

</head>
<body>


<%@include file="head_null.jsp" %>
<div style="width: 35%; margin:60px auto;" >
    <div class="modal-body">
        <div class="input-group input-group-lg">
            <span class="input-group-addon" id="sizing-s2"><i class="fa fa-user" style="width:18px"></i></span>
            <input type="text" class="form-control" id="userName" placeholder="请输入用户名" aria-describedby="sizing-addon1">
        </div>


        <div class="input-group input-group-lg top20" style="position:relative;">
            <span class="input-group-addon" id="sizing-s1"><i class="fa fa-envelope-o youxiang" style="width:18px"></i></span>
            <input id="email" class="form-control " placeholder="请输入您的邮箱" aria-describedby="sizing-addon1" type="text" name="name" size="30"  autocomplete="off">

            <span class="input-group-btn tccBut">
                <input type="button" id="emailBtn" class="btn btn-success " style="width:130px; font-size:15px;line-height:28px" value="发送验证码">
            </span>
        </div>



        <div class="input-group input-group-lg top20">
            <span class="input-group-addon"><i class="fa fa-qrcode" style="width:18px"></i></span>
            <input type="text" class="form-control" id="checkNumber" placeholder="请输入验证码" aria-describedby="sizing-addon1">
        </div>

        <div class="input-group input-group-lg top20">
            <span class="input-group-addon"><i class="fa fa-phone" style="width:18px"></i></span>
            <input type="text" class="form-control" id="phoneNumber" placeholder="请输入联系手机" aria-describedby="sizing-addon1">
        </div>

        <div class="input-group input-group-lg top20">
            <span class="input-group-addon"><i class="fa fa-lock" style="width:18px"></i></span>
            <input type="password" class="form-control" id="passWord1" placeholder="请输入登录密码" aria-describedby="sizing-addon1">
        </div>

        <div class="input-group input-group-lg top20">
            <span class="input-group-addon" id="sizing-s4"><i class="fa fa-key" style="width:18px" data-trigger="manual" data-placement="left" title="确认密码输入错误！"></i></span>
            <input type="password" class="form-control" id="passWord2" placeholder="确认登录密码" aria-describedby="sizing-addon1">
            <span class="input-group-btn tccBut">
              <input type="button" class="btn btn-success" id="signUpBtn" style="width:130px" value="注册">
          </span>
        </div>
    </div>
</div>

<div class="footer-top-area">
    <div class="container">
        <div class="row">
            <div class="col-md-3">

            </div>

            <div class="col-md-3 ">

            </div>

            <div class="col-md-3">

            </div>

            <div class="col-md-3 ">

            </div>
        </div>
    </div>
</div>
<div class="footer-bottom-area">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="copyright">
                    <p>Copyright &copy; 2018.Company name All rights reserved.<a target="_blank" href="http://www.nju.edu.cn/"> NJU</a></p>
                </div>
            </div>
            <div class="col-md-4">
            </div>
        </div>
    </div>
</div>

    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/owl.carousel.min.js"></script>
    <script src="../js/jquery.sticky.js"></script>
    <script src="../js/jquery.easing.1.3.min.js"></script>
    <script src="../js/main.js"></script>
    <script src="../js/sweet-alert.js"></script>

<script>
      $(document).ready(function(){
          $("#menu_user").addClass("active");
      });
</script>

<script>
    $(document).ready(function () {
        $("#emailBtn").click(function() {
            let emailAddress = $("#email").val();
            console.log(emailAddress);


            sendEmail(emailAddress, function (message) {
                console.log(message);
                if (message == "SUCCESS") {
                    $("#emailBtn").val("重新发送");
                }
                if (message == "FAIL_SENDING_EMAIL") {
                    swal({
                        title: "邮件发送失败",
                        type: "error",
                        confirmButtonText: "返回"
                    })
                }
            });

        });
    });

    function sendEmail(emailAddress, callback) {
        $.ajax({
            type: 'POST',
            url: '/sendEmail',
            data: {
                emailAddress: emailAddress,
            },
            success: function (result) {
                if (callback) {
                    callback(result);
                }
            },
            error: function (XMLHttpRequest, testStatus, errorThrown) {
                console.log(XMLHttpRequest.staus);
                console.log(testStatus);
            }
        });
    }
</script>

<script>
    $(document).ready(function () {
        $("#signUpBtn").click(function() {
            let userName = $("#userName").val();
            let passWord1 = $("#passWord1").val();
            let passWord2 = $("#passWord2").val();
            let emailAddress = $ ("#email").val();
            let checkNumber = $("#checkNumber").val();
            let phone = $("#phoneNumber").val();

            console.log(userName, passWord1, passWord2, emailAddress, checkNumber, phone);
            if (userName.length < 1) {
                swal({
                    title: "不允许的用户名",
                    type: "error",
                    confirmButtonText: "返回"
                })
            }
            if (passWord1 !== passWord2) {
                swal({
                    title: "两次密码输入不一致",
                    type: "error",
                    confirmButtonText: "返回"
                })
            } else {
                signUp(userName, passWord1, emailAddress, checkNumber, phone,
                    function (message) {
                        console.log(message);
                        if (message == "SUCCESS") {
                            swal({
                                    title: "注册成功!",
                                    type: "success",
                                    confirmButtonText: "进入Tickets",
                                    closeOnConfirm: false
                                },
                                function (isConfirm) {
                                    if (isConfirm) {
                                        window.location.href = '/userInfo';
                                    }
                                })
                        }
                        if (message == "USERNAME_EXIST") {
                            swal({
                                title: "用户名已存在",
                                type: "error",
                                confirmButtonText: "返回"
                            })
                        }
                    }
                );
            }
        });
    });

    function signUp(userName, passWord, emailAddress, checkNumber, phone, callback) {
        $.ajax({
            type: 'POST',
            url: '/signUpPost',
            data: {
                userName: userName,
                passWord: passWord,
                emailAddress: emailAddress,
                checkNumber: checkNumber,
                phone: phone,
            },
            success: function (result) {
                if (callback) {
                    callback(result);
                }
            },
            error: function (XMLHttpRequest, testStatus, errorThrown) {
                console.log(XMLHttpRequest.staus);
                console.log(testStatus);
            }
        });
    }
</script>
</body>
</html>