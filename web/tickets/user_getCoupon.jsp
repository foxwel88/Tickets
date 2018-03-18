<!DOCTYPE html>
<%@ page import="edu.nju.tickets.model.User" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="edu.nju.tickets.model.Coupon" %>
<%@ page import="edu.nju.tickets.util.LevelUtil" %>
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

<%
    User user = (User)request.getAttribute("user");
%>

    <%@include file="head.jsp" %>

    <div class="single-product-area">
        <div class="container">
            <div class="col-md-2">
                <div class="single-sidebar">
                    <ul>
                        <li><a href="/userInfo">个人信息</a></li>
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
                    <a href="/userInfo">个人中心</a>
                    <a href="/userGetCoupon">获取优惠券</a>
                </div>
                <div class="product-content-right">
                    <div id="customer_details" class="col2-set">
                        <div class="col-main">
                            <div class="woocommerce-billing-fields">
                                <div style="position: relative;">
                                    <table class="shop_table">
                                        <tbody>
                                            <tr>
                                                <th>全场满 500 减 20</th>
                                                <td>消耗100积分</td>
                                                <td><input type="submit" value="兑换" id="getCoupon1" class="button alt"></td>
                                            </tr>
                                            <tr>
                                                <th>全场满 300 减 20</th>
                                                <td>消耗300积分</td>
                                                <td><input type="submit" value="兑换" id="getCoupon2" class="button alt"></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
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
        $("#getCoupon1").click(function() {
            let userName = "<%=session.getAttribute("userName")%>";
            console.log(userName);


            getCoupon(userName, 1, function (message) {
                console.log(message);
                if (message == "SUCCESS") {
                    swal({
                            title: "兑换成功!",
                            type: "success",
                            confirmButtonText: "返回Tickets",
                            closeOnConfirm: false
                        },
                        function (isConfirm) {
                            if (isConfirm) {
                                window.location.href = '/userInfo';
                            }
                        })
                } else {
                    swal({
                        title: "兑换失败!",
                        type: "error",
                        confirmButtonText: "返回"
                    })
                }
            });
        });
        $("#getCoupon2").click(function() {
            let userName = "<%=session.getAttribute("userName")%>";
            console.log(userName);


            getCoupon(userName, 2, function (message) {
                console.log(message);
                if (message == "SUCCESS") {
                    swal({
                            title: "兑换成功!",
                            type: "success",
                            confirmButtonText: "返回Tickets",
                            closeOnConfirm: false
                        },
                        function (isConfirm) {
                            if (isConfirm) {
                                window.location.href = '/userInfo';
                            }
                        })
                } else {
                    swal({
                        title: "兑换失败!",
                        type: "error",
                        confirmButtonText: "返回"
                    })
                }
            });

        });
    });

    function getCoupon(userName, couponId,  callback) {
        $.ajax({
            type: 'POST',
            url: '/getCoupon',
            data: {
                userName: userName,
                couponId: couponId
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