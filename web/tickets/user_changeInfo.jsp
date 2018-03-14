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
                        <li><a href="userInfo">个人信息</a></li>
                        <li><a href="userChangePassWord">修改密码</a></li>
                        <li><a href="userChangeEmail">修改邮箱</a></li>
                        <li><a href="userChangeInfo">修改信息</a></li>
                    </ul>
                </div>
            </div>
                
            <div class="col-md-10">
                <div class="product-breadcroumb">
                    <a href="">首页</a>
                    <a href="">个人中心</a>
                    <a href="">修改个人信息</a>
                </div>
                <div class="product-content-right">
                    <div id="customer_details" class="col2-set">
                        <div class="col-1">
                            <div class="woocommerce-billing-fields">

                                <p id="old_password_field" class="form-row form-row-first validate-required">
                                    <label class="" for="old_password">手机号码</label>
                                    <input type="text" value="" placeholder="" id="old_password" name="old_password" class="input-text ">
                                </p>

                                <p id="new_password1_field" class="form-row form-row-first validate-required">
                                    <label class="" for="new_password1">邮寄地址</label>
                                    <input type="text" value="" placeholder="" id="new_password1" name="new_password1" class="input-text ">
                                </p>

                                <input type="submit" data-value="Place order" value="确认修改" id="place_order" name="woocommerce_checkout_place_order" class="button alt">

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
</body>
</html>