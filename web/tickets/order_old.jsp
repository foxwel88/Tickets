<%@ page import="edu.nju.tickets.OrderVO" %>
<%@ page import="java.util.List" %>
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

<%
    List<OrderVO> orderVOList = (List<OrderVO>)request.getAttribute("orderList");
%>

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

                <%
                    if (orderVOList.size() <= 0) {
                %>

                <table cellspacing="0" class="shop_table cart">
                    <thead>
                    <tr>
                        <th>当前无已完成订单</th>
                    </tr>
                    </thead>
                </table>
                <%
                    } else {
                %>

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
                        <%
                            for (int i = 0; i < orderVOList.size(); ++i) {
                                OrderVO orderVO = orderVOList.get(i);
                        %>
                            <tr class="cart_item">
                                <td>
                                    <%=orderVO.getId()%>
                                </td>

                                <td>
                                    <%=orderVO.getDay()%>
                                    <br>
                                    <%=orderVO.getTime()%>
                                </td>

                                <td>
                                    <a href="/showSingle?showId=<%=orderVO.getShowId()%>"><%=orderVO.getShowName()%><br>[<%=orderVO.getShowDay()%>]</a>
                                </td>

                                <td>
                                    <%=orderVO.getType()%>
                                </td>

                                <td>
                                    <%=orderVO.getNum()%>
                                </td>

                                <td>
                                    <%=orderVO.getSeat()%>
                                </td>

                                <td>
                                    ¥ <%=orderVO.getPrice()%>
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
            $("#menu_order").addClass("active");
        });
    </script>
  </body>
</html>