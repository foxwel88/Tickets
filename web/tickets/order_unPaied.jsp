<%@ page import="edu.nju.tickets.model.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.nju.tickets.util.TimeUtil" %>
<%@ page import="edu.nju.tickets.OrderVO" %>
<%@ page import="edu.nju.tickets.model.User" %>
<%@ page import="java.util.logging.Level" %>
<%@ page import="edu.nju.tickets.util.LevelUtil" %>
<%@ page import="edu.nju.tickets.model.Coupon" %>
<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Tickets - 待支付订单</title>
        

        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="../css/font-awesome.min.css">
        <link rel="stylesheet" href="../css/owl.carousel.css">
        <link rel="stylesheet" href="../style.css">
        <link rel="stylesheet" href="../css/responsive.css">

    </head>
<body>

<%
    List<Coupon> couponList = (List<Coupon>)request.getAttribute("couponList");
    List<OrderVO> orderVOList = (List<OrderVO>)request.getAttribute("orderList");


    User user = (User)request.getAttribute("user");
    double count = LevelUtil.getCount(user.getLevel());
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
                        <a href="/orderUnPaied">待支付订单</a>
                </div>

                <%
                    if (orderVOList.size() <= 0) {
                %>

                        <table cellspacing="0" class="shop_table cart">
                            <thead>
                            <tr>
                                <th>当前无待支付订单</th>
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
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            double price = 0.0;
                            int orderId = orderVOList.get(0).getId();
                            for (int i = 0; i < 1; ++i) {
                                OrderVO orderVO = orderVOList.get(i);
                                price = price + orderVO.getPrice();
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

                                <td>
                                    <input type="submit" style="" id="cancelOrderBtn" value="取消" class="button">
                                </td>
                            </tr>
                        <%
                            }

                            double payPrice = price * count;
                        %>
                            <tr>
                                <td class="actions" colspan="8">
                                    <div class="coupon" style="width:30%">
                                        <label style="width:20%; float: left;" >优惠券:</label>
                                        <select rel="calc_shipping_state" onchange="setCoupon()" style="width:75%" class="country_to_state">
                                            <option value="">请选择...</option>
                                                <%
                                                    for (Coupon coupon: couponList) {
                                                %>
                                                        <option value="xx" >全场<%=coupon.getName()%></option>
                                                <%
                                                    }
                                                %>
                                        </select>
                                    </div>
                                    <div class="coupon" style="width:60%; float:right">
                                        <label style="width:20%; float:left; margin-right: 10px">请输入优惠码:</label>
                                        <input type="text" style="width:45%; float:left" placeholder="" value="" id="coupon_code" class="input-text" name="coupon_code">
                                        <input type="submit" style="width:30%; float:right" value="应用优惠码" name="apply_coupon" class="button">
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>

                    <div class="cart_totals">
                        <h2>结算</h2>

                        <table cellspacing="0">
                            <tbody>
                                <tr class="cart-subtotal">
                                    <th>订单总额</th>
                                    <td colspan="3">¥<%=price%></td>
                                </tr>

                                <tr >
                                    <th>会员等级折扣</th>
                                    <td><%=countStr%></td>
                                    <th>优惠券优惠</td>
                                    <td id="couponTd">无</td>
                                </tr>

                                <tr class="order-total">
                                    <th>支付金额</th>
                                    <td colspan="3">¥<strong id="priceStrong"><%=payPrice%></strong></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <div class="cart_pay">

                        <h2>在线支付</h2>

                        <p class="form-row form-row-wide">
                            <select rel="calc_shipping_state" style="width:100%" class="country_to_state" id="calc_shipping_country" name="calc_shipping_country">
                                <option value="">请选择付款方式</option>
                                <option value="AX">TicketsPay</option>
                                <option value="AF">网银</option>
                                
                            </select>
                        </p>

                        <p class="form-row form-row-wide">
                            <input type="text" style="width:100%" id="payAccount" placeholder="TicketsPay账号" class="input-text">
                        </p>

                        <p class="form-row form-row-wide">
                            <input type="password" style="width:45%" id="payAccountPassWord" placeholder="付款码" class="input-text">
                            <button class="button" style="float:right; width:45%" id="payBtn" type="submit">确认支付</button>
                        </p>
                        <script>
                            $(document).ready(function () {

                                $("#payBtn").click(function() {
                                    let userName = "<%=user.getUserName()%>";
                                    let orderId = <%=orderId%>;
                                    let payAccount = $("#payAccount").val();
                                    let payAccountPassWord = $("#payAccountPassWord").val();

                                    console.log(userName);
                                    console.log(orderId);
                                    console.log(payAccount);
                                    console.log(payAccountPassWord);


                                    payOrder(userName, orderId, payAccount, payAccountPassWord, function (message) {
                                        console.log(message);
                                        if (message == "SUCCESS") {
                                            swal({
                                                    title: "支付成功!",
                                                    type: "success",
                                                    confirmButtonText: "返回Tickets",
                                                    closeOnConfirm: false
                                                },
                                                function (isConfirm) {
                                                    if (isConfirm) {
                                                        window.location.href = '/orderFinished';
                                                    }
                                                })
                                        }
                                        if (message == "NO_PAY_ACCOUNT") {
                                            swal({
                                                title: "不存在此 TicketsPay 账户",
                                                type: "error",
                                                confirmButtonText: "返回"
                                            })
                                        }
                                        if (message == "WRONG_PAY_PASSWORD") {
                                            swal({
                                                title: "支付密码错误",
                                                type: "error",
                                                confirmButtonText: "返回"
                                            })
                                        }
                                        if (message == "NO_ENOUGH_MONEY") {
                                            swal({
                                                title: "余额不足",
                                                type: "error",
                                                confirmButtonText: "返回"
                                            })
                                        }
                                    });

                                });

                                $("#cancelOrderBtn").click(function() {
                                    let orderId = <%=orderId%>;

                                    console.log(orderId);

                                    cancelUnPaiedOrder(orderId, function (message) {
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
                                                        window.location.href = '/orderUnPaied';
                                                    }
                                                })
                                        }else {
                                            swal({
                                                title: "取消失败",
                                                type: "error",
                                                confirmButtonText: "返回"
                                            })
                                        }
                                    });
                                });
                            });

                            function cancelUnPaiedOrder(orderId, callback) {
                                $.ajax({
                                    type: 'POST',
                                    url: '/cancelUnPaiedOrder',
                                    data: {
                                        orderId: orderId
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

                            function payOrder(userName, orderId, payAccount, payAccountPassWord, callback) {
                                $.ajax({
                                    type: 'POST',
                                    url: '/payOrder',
                                    data: {
                                        userName: userName,
                                        orderId: orderId,
                                        payAccount: payAccount,
                                        payAccountPassWord: payAccountPassWord
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

                            function setCoupon() {
                                console.log("hahah");
                                document.getElementById("couponTd").innerHTML= "20";
                                let newPrice = (<%=payPrice%> - 20).toFixed(2);
                                document.getElementById("priceStrong").innerHTML = newPrice;
                            }
                        </script>
                    </div>
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