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
    <link rel="stylesheet" href="../css/owl.carousel.css">
    <link rel="stylesheet" href="../style.css">
    <link rel="stylesheet" href="../css/responsive.css">

  </head>
<body>

<jsp:useBean id="user" class="edu.nju.tickets.model.User" scope="page"></jsp:useBean>
<%
    User emptyUser = new User("", "", "", "");
    User user1 = (User) request.getAttribute("user");
    if (user1 == null) user1 = emptyUser;
    pageContext.setAttribute("user", user1);
%>

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
                    <a href="">个人信息</a>
                </div>
                <div class="product-content-right">
                    <div id="customer_details" class="col2-set">
                        <div class="col-main">
                            <div class="woocommerce-billing-fields">

                                <h3>个人信息</h3>

                                <div style="position: relative;">
                                    <table class="shop_table">
                                        <tbody>
                                            <tr>
                                                <th>用户名</th>
                                                <td colspan="3"><jsp:getProperty name="user" property="userName" /></td>
                                            </tr>

                                            <tr >
                                                <th>邮箱</th>
                                                <td colspan="3"><jsp:getProperty name="user" property="emailAddress" /></td>
                                            </tr>

                                            <tr>
                                                <th>联系电话</th>
                                                <td colspan="3"><jsp:getProperty name="user" property="phone" /></td>
                                            </tr>
                                        </tbody>

                                    </table>
                                </div>
                            </div>

                            <div class="woocommerce-billing-fields">

                                <h3>会员信息</h3>

                                <div id="order_review" style="position: relative;">
                                    <table class="shop_table">
                                        <tbody>
                                        <tr>
                                            <th>会员积分</th>
                                            <td colspan="3"><jsp:getProperty name="user" property="integral" /></td>
                                        </tr>

                                        <tr >
                                            <th>会员等级</th>
                                            <td><jsp:getProperty name="user" property="level" />级会员</td>
                                            <th>升级所需积分</td>
                                            <td>380</td>
                                        </tr>

                                        <tr>
                                            <th>会员等级折扣</th>
                                            <td colspan="3">10%</td>
                                        </tr>
                                        </tbody>

                                    </table>
                                </div>
                            </div>

                            <div class="woocommerce-billing-fields">

                                <h3>优惠券信息</h3>

                                <div style="position: relative;">
                                    <table class="shop_table">
                                        <tbody>

                                            <tr>
                                                <th>2018/03 到期</th>
                                                <td colspan="3">全场满 300 减 20</td>
                                            </tr>
                                            <tr>
                                                <th>2018/03 到期</th>
                                                <td colspan="3">全场满 300 减 20</td>
                                            </tr>
                                            <tr>
                                                <th>2018/03 到期</th>
                                                <td colspan="3">全场满 300 减 20</td>
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
</body>
</html>