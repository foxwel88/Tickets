<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tickets - 个人中心</title>
    

    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/owl.carousel.css">
    <link rel="stylesheet" href="../style.css">
    <link rel="stylesheet" href="../css/responsive.css">

</head>
<body>

    <%@include file="head.jsp" %>
    
    <div class="single-product-area">
        <div class="container">
            <div class="col-md-2">
                <div class="single-sidebar">
                    <ul>
                        <<li><a href="/userInfo">个人信息</a></li>
                        <li><a href="/userChangePassWord">修改密码</a></li>
                        <li><a href="/userChangeEmail">修改邮箱</a></li>
                        <li><a href="/userChangeInfo">修改信息</a></li>
                        <li><a href="/userGetCoupon">获取优惠券</a></li>
                    </ul>
                </div>
            </div>
                
            <div class="col-md-10">
                <div class="product-breadcroumb">
                    <a href="">首页</a>
                    <a href="">个人中心</a>
                    <a href="">修改密码</a>
                </div>
                <div class="product-content-right">
                    <div id="customer_details" class="col2-set">
                        <div class="col-1">
                            <div class="woocommerce-billing-fields">

                                <p id="old_password_field" class="form-row form-row-first validate-required">
                                    <label class="" for="old_password">原密码</label>
                                    <input type="password" value="" placeholder="" id="old_password" name="old_password" class="input-text ">
                                </p>

                                <p id="new_password1_field" class="form-row form-row-first validate-required">
                                    <label class="" for="new_password1">新密码</label>
                                    <input type="password" value="" placeholder="" id="new_password1" name="new_password1" class="input-text ">
                                </p>

                                <p id="new_password2_field" class="form-row form-row-first validate-required">
                                    <label class="" for="new_password2">重复新密码</label>
                                    <input type="password" value="" placeholder="" id="new_password2" name="new_password2" class="input-text ">
                                </p>
                                <input type="submit" data-value="Place order" value="确认修改" id="modifyPassWordBtn" class="button alt">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <%@include file="tail.jsp" %>

    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/owl.carousel.min.js"></script>
    <script src="../js/jquery.sticky.js"></script>
    <script src="../js/jquery.easing.1.3.min.js"></script>
    <script src="../js/main.js"></script>
    <script>
      $(document).ready(function(){
          $("#menu_user").addClass("active");
      });
    </script>

    <script>
        $(document).ready(function () {
            $("#modifyPassWordBtn").click(function() {
                let userName = "<%=session.getAttribute("userName")%>";
                let oldPassWord = $("#old_password").val();
                let newPassWord1 = $("#new_password1").val();
                let newPassWord2 = $("#new_password2").val();
                console.log(userName);
                console.log(oldPassWord);
                console.log(newPassWord1);

                if (newPassWord1 !== newPassWord2) {
                    swal({
                        title: "两次输入不同",
                        type: "error",
                        confirmButtonText: "返回"
                    })
                } else {
                    login(userName, oldPassWord, newPassWord1, function (message) {
                        console.log(message);
                        if (message == "SUCCESS") {
                            swal({
                                    title: "修改成功!",
                                    type: "success",
                                    confirmButtonText: "返回Tickets",
                                    closeOnConfirm: false
                                },
                                function (isConfirm) {
                                    if (isConfirm) {
                                        window.location.href = '/userInfo';
                                    }
                                })
                        }
                        if (message == "NO_USER") {
                            swal({
                                title: "不存在此用户",
                                type: "error",
                                confirmButtonText: "返回"
                            })
                        }
                        if (message == "WRONG_PASSWORD") {
                            swal({
                                title: "密码错误",
                                type: "error",
                                confirmButtonText: "返回"
                            })
                        }
                    });
                }
            });
        });

        function login(userName, oldPassWord, newPassWord, callback) {
            $.ajax({
                type: 'POST',
                url: '/modifyPassWord',
                data: {
                    userName: userName,
                    oldPassWord: oldPassWord,
                    newPassWord: newPassWord
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