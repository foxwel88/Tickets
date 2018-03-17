<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="edu.nju.tickets.model.Place" %>
<%@ page import="edu.nju.tickets.model.util.SeatInfo" %>
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
    Place place = (Place)request.getAttribute("place");
    String placeIdStr = String.valueOf(place.getId());
    while (placeIdStr.length() < 7) placeIdStr = "0" + placeIdStr;
%>
    <%@include file="head_place.jsp" %>

    <div class="single-product-area">
        <div class="container">
            <div class="col-md-2">
                <div class="single-sidebar">
                    <ul>
                        <li><a href="/placeInfo">场馆信息</a></li>
                        <li><a href="/placeChangeInfo">修改信息</a></li>
                        <li><a href="/placeChangeSeatInfo">修改场馆座位</a></li>
                    </ul>
                </div>
            </div>
                
            <div class="col-md-10">
                <div class="product-breadcroumb">
                    <a href="">场馆管理</a>
                    <a href="">场馆信息</a>
                </div>
                <div class="product-content-right">
                    <div id="customer_details" class="col2-set">
                        <div class="col-main">
                            <div class="woocommerce-billing-fields">


                                <div style="position: relative;">
                                    <table class="shop_table">
                                        <tbody>
                                            <tr>
                                                <th>场馆识别码</th>
                                                <td colspan="3"><%=placeIdStr%></td>
                                            </tr>

                                            <tr >
                                                <th>场馆名词</th>
                                                <td colspan="3"><%=place.getName()%></td>
                                            </tr>

                                            <tr>
                                                <th>场馆地址</th>
                                                <td colspan="3"><%=place.getAddress()%></td>
                                            </tr>


                                            <tr>
                                                <th>场馆描述</th>
                                                <td colspan="3"><%=place.getDescribe()%></td>
                                            </tr>

                                            <tr>
                                                <th>场馆状态</th>
                                                <td colspan="3"><%=place.getState()%></td>
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
          $("#menu_placeManage").addClass("active");
      });
    </script>
</body>
</html>