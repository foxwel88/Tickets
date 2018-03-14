<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tickets - 历史订单</title>


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
                        <li><a href="/orderUnPaied">待支付订单</a></li>
                        <li><a href="/orderFinished">已完成订单</a></li>
                        <li><a href="/orderOld">历史订单</a></li>
                    </ul>
                </div>
            </div>

            <div class="col-md-10">
                <div class="product-breadcroumb">
                        <a href="">首页</a>
                        <a href="/orderUnPaied">订单</a>
                        <a href="/orderOld">历史订单</a>
                </div>

                <form method="post" action="#">
                    <table cellspacing="0" class="shop_table cart">
                        <thead>
                            <tr>
                                <th>订单编号</th>
                                <th>下单时间</th>
                                <th>演出</th>
                                <th>订单类型</th>
                                <th>数量</th>
                                <th>座位信息</th>
                                <th>金额</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="cart_item">
                                <td>
                                    000000001
                                </td>

                                <td>
                                    2018-03-13<br>16:01
                                </td>
                                
                                <td>
                                    <a href="cart.html">XXXXX演唱会<br>[2018-05-15]</a> 
                                </td>

                                <td>
                                    选座订单 
                                </td>

                                <td>
                                    4
                                </td>

                                <td>
                                    北二区3排2座<br>
                                    北二区3排3座<br>
                                    北二区3排4座<br>
                                    北二区3排5座<br>
                                </td>

                                <td>
                                    ¥260.0
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>

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
            $("#menu_order").addClass("active");
        });
    </script>
  </body>
</html>