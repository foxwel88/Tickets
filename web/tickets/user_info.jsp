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

<jsp:useBean id="user" class="edu.nju.tickets.model.User" scope="page"></jsp:useBean>
<%
    User emptyUser = new User("", "", "", "");
    User user1 = (User) request.getAttribute("user");
    if (user1 == null) user1 = emptyUser;
    pageContext.setAttribute("user", user1);

    List<Coupon> couponList = (List<Coupon>)request.getAttribute("couponList");

    double count = LevelUtil.getCount(user1.getLevel());
    String countStr = "";

    if ((1 - count) < 0.000001) {
        countStr = "无折扣";
    } else {
        countStr = ((int)(count * 100)) + "折";
    }
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
                                            <tr>
                                                <th>邮寄地址</th>
                                                <td colspan="3"><jsp:getProperty name="user" property="address" /></td>
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
                                            <td>2000</td>
                                        </tr>

                                        <tr>
                                            <th>会员等级折扣</th>
                                            <td colspan="3"><%=countStr%></td>
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
                                            <%
                                                for (Coupon coupon: couponList) {
                                            %>
                                                    <tr>
                                                        <th><%=coupon.getDateString()%> 到期</th>
                                                        <td colspan="3">全场<%=coupon.getName()%></td>
                                                    </tr>
                                           <%
                                               }
                                           %>
                                        </tbody>

                                    </table>
                                </div>
                            </div>

                            <div class="woocommerce-billing-fields">

                                <h3>结束会员资格</h3>

                                <p id="cancelPassWord_field" class="form-row form-row-first validate-required">
                                    <label class="" for="cancelPassWord">请输入密码</label>
                                    <input type="password" id="cancelPassWord" class="input-text ">
                                </p>

                                <input type="submit" value="确认取消" id="cancelUserBtn" class="button alt">
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
        $("#cancelUserBtn").click(function() {
            let userName = "<%=session.getAttribute("userName")%>";
            let passWord = $("#cancelPassWord").val();
            console.log(userName);
            console.log(passWord);


            cancelUser(userName, passWord, function (message) {
                console.log(message);
                if (message == "SUCCESS") {
                    swal({
                            title: "取消成功!",
                            type: "success",
                            confirmButtonText: "返回Tickets",
                            closeOnConfirm: false
                        },
                        function (isConfirm) {
                            if (isConfirm) {
                                window.location.href = '/logOutUser';
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

        });
    });

    function cancelUser(userName, passWord, callback) {
        $.ajax({
            type: 'POST',
            url: '/cancelUser',
            data: {
                userName: userName,
                passWord: passWord
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