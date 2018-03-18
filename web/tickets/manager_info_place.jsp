<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="edu.nju.tickets.model.Place" %>
<%@ page import="edu.nju.tickets.model.util.SeatInfo" %>
<%@ page import="edu.nju.tickets.model.Show" %>
<%@ page import="edu.nju.tickets.util.TimeUtil" %>
<%@ page import="edu.nju.tickets.model.util.SeatState" %>
<%@ page import="edu.nju.tickets.model.PrePlace" %>
<%@ page import="edu.nju.tickets.model.PlaceAccount" %>
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
        List<PlaceAccount> placeAccountList = (List<PlaceAccount>)request.getAttribute("placeAccountList");
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
                    <a href="/managerInfoPlace">场馆数据统计</a>
                </div>
                <%
                    if (placeAccountList.size() <= 0) {
                %>
                        <table cellspacing="0" class="shop_table cart">
                            <thead>
                            <tr>
                                <th>当前无场馆数据</th>
                            </tr>
                            </thead>
                        </table>
                <%
                    } else {
                %>
                        <table cellspacing="0" class="shop_table cart">
                            <thead>
                                <tr>
                                    <th style="width: 8%">场馆编号</th>
                                    <th style="width: 12%">场馆名称</th>
                                    <th style="width: 12%">场馆地址</th>
                                    <th style="width: 25%">场馆描述</th>
                                    <th style="width: 10%">场馆演出数量</th>
                                    <th style="width: 10%">场馆售出票数量</th>
                                    <th style="width: 25%">场馆销售金额</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (int i = 0; i < placeAccountList.size(); ++i) {
                                        PlaceAccount placeAccount = placeAccountList.get(i);
                                %>
                                        <tr class="cart_item">
                                            <td>
                                                <%=placeAccount.getId()%>
                                            </td>

                                            <td>
                                                <%=placeAccount.getPlace().getName()%>
                                            </td>

                                            <td>
                                                <%=placeAccount.getPlace().getAddress()%>
                                            </td>

                                            <td>
                                                <%=placeAccount.getPlace().getDescribe()%>
                                            </td>

                                            <td>
                                                <%=placeAccount.getShowNum()%>
                                            </td>

                                            <td>
                                                <%=placeAccount.getTicketNum()%>

                                            </td>

                                            <td>
                                                <%=placeAccount.getPrice()%>

                                            </td>
                                        </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                <%
                    }
                %>
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