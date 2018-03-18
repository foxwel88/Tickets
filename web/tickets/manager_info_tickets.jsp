<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="edu.nju.tickets.model.util.SeatInfo" %>
<%@ page import="edu.nju.tickets.util.TimeUtil" %>
<%@ page import="edu.nju.tickets.model.util.SeatState" %>
<%@ page import="edu.nju.tickets.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>Tickets - 经理</title>
    

    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/owl.carousel.css">
    <link rel="stylesheet" href="../style.css">
    <link rel="stylesheet" href="../css/responsive.css">

  </head>
<body>

    <%@include file="head_manager.jsp" %>

    <%
        TicketsAccount ticketsAccount = (TicketsAccount) request.getAttribute("ticketsAccount");
    %>
    <div class="single-product-area">
        <div class="container">
            <div class="col-md-2">
                <div class="single-sidebar">
                    <ul>
                        <li><a href="/managerInfoPlace">场馆数据统计</a></li>
                        <li><a href="/managerInfoUser">用户数据统计</a></li>
                        <li><a href="/managerInfoTickets">财务数据统计</a></li>
                    </ul>
                </div>
            </div>
                
            <div class="col-md-10">
                <div class="product-breadcroumb">
                    <a href="">Tickets经理</a>
                    <a href="/managerInfoTickets">财务数据统计</a>
                </div>

                        <table cellspacing="0" class="shop_table cart">
                            <thead>
                                <tr>
                                    <th style="width: 20%" colspan="3">未支付订单</th>
                                    <th style="width: 20%" colspan="3">已完成订单</th>
                                </tr>
                                <tr>
                                    <th style="width: 20%">购票订单数</th>
                                    <th style="width: 20%">购票数</th>
                                    <th style="width: 20%">收入</th>
                                    <th style="width: 20%">购票订单数</th>
                                    <th style="width: 20%">购票数</th>
                                    <th style="width: 20%">收入</th>
                                </tr>
                            </thead>
                            <tbody>

                                        <tr class="cart_item">
                                            <td>
                                                <%=ticketsAccount.getUnPaiedOrderNum()%>
                                            </td>

                                            <td>
                                                <%=ticketsAccount.getUnPaiedTicketNum()%>
                                            </td>

                                            <td>
                                                <%=ticketsAccount.getUnPaiedPrice()%>
                                            </td>

                                            <td>
                                                <%=ticketsAccount.getPaiedOrderNum()%>
                                            </td>

                                            <td>
                                                <%=ticketsAccount.getPaiedTicketNum()%>
                                            </td>

                                            <td>
                                                <%=ticketsAccount.getPaiedPrice()%>
                                            </td>
                                        </tr>

                            </tbody>
                        </table>

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
      $("#menu_managerInfo").addClass("active");
    });
</script>

</body>
</html>