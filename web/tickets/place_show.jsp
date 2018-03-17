<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="edu.nju.tickets.model.Place" %>
<%@ page import="edu.nju.tickets.model.util.SeatInfo" %>
<%@ page import="edu.nju.tickets.model.Show" %>
<%@ page import="edu.nju.tickets.util.TimeUtil" %>
<%@ page import="edu.nju.tickets.model.util.SeatState" %>
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
    List<Show> showList = (List<Show>)request.getAttribute("showList");
    Show show = (Show)request.getAttribute("show");
    Place place = (Place)request.getAttribute("place");
%>

    <%@include file="head_place.jsp" %>
    <div class="single-product-area">
        <div class="container">
            <div class="col-md-2">
                <div class="single-sidebar">
                    <ul>

                        <a href="/placeCreateShow"><button class="button" style="margin-bottom: 5px; margin-left:-10px; width:80%; " id="createShow" type="submit">创建新演出</button></a>
                        <%
                            for (int i = 0; i < showList.size(); ++i) {
                        %>
                                <li><a href="/placeShow?showId=<%=showList.get(i).getId()%>"><%=showList.get(i).getName()%></a></li>
                        <%
                            }
                        %>

                    </ul>
                </div>
            </div>

            <div class="col-md-10">

                <%
                    if (show == null) {
                %>

                        <table cellspacing="0" class="shop_table cart">
                            <thead>
                            <tr>
                                <th>当前无演出</th>
                            </tr>
                            </thead>
                        </table>
                <%
                     } else {
                %>
                        <div class="product-breadcroumb">
                            <a href="">演出管理</a>
                            <a href=""><%=show.getName()%></a>
                        </div>
                        <table cellspacing="0" class="shop_table cart">
                            <thead>
                                <tr>
                                    <th>演出编号</th>
                                    <th>演出时间</th>
                                    <th>演出名称</th>
                                    <th>演出简介</th>
                                    <th>总座位数</th>
                                    <th>已卖出票数</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="cart_item">
                                    <td>
                                        <%=show.getId()%>
                                    </td>

                                    <td>
                                        <%=TimeUtil.getDayString(show.getTime())%>
                                        <br>
                                        <%=TimeUtil.getTimeString(show.getTime())%>
                                    </td>

                                    <td>
                                        <%=show.getName()%>
                                    </td>

                                    <td style="width: 30%">
                                        <%=show.getDescribe()%>
                                    </td>

                                    <td>
                                        <%=show.getSeatState().getSeatNum()%>
                                    </td>

                                    <td>
                                        <%=show.getSeatState().getSellNum()%>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="actions" colspan="6">

                                        <div class="coupon" style="width:60%; ">
                                            <label style="width:20%; float:left; margin-right: 5px; margin-top: 10px">在线检票:</label>
                                            <input type="text" style="width:45%; float:left" placeholder="请扫入票 ID" value="" id="coupon_code" class="input-text" name="coupon_code">
                                            <input type="submit" style="width:30%; float:right" value="确认" name="apply_coupon" class="button">
                                        </div>
                                        <div class="coupon" style="width:30%; margin-top: 10px; float:right">
                                            <label style=" float: left;" >已入场: 10人</label>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>

                        <div class="cart_totals">
                            <%
                                SeatInfo seatInfo = place.getSeatInfo();
                                SeatState seatState = show.getSeatState();
                            %>
                            <h3>座位区域价格</h3>

                            <table cellspacing="0">
                                <tbody>
                                    <tr class="cart-subtotal">
                                        <th>座位区域</th>
                                        <td style="width: 60%">价格</td>
                                    </tr>
                                    <%
                                        for (int i = 0; i < seatInfo.getDistrictList().size(); ++i) {
                                            String seatName = seatInfo.getDistrictName(i);
                                            double seatPrice = seatState.getPrice(i);
                                    %>
                                            <tr class="order-total">
                                                <th><%=seatName%></th>
                                                <td style="width: 60%"><strong>¥<%=seatPrice%></strong></td>
                                            </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>

                        <div class="cart_pay">

                            <h3>现场售票</h3>

                            <p class="form-row form-row-wide">
                                <input type="text" style="width:100%" id="payAccount" placeholder="Tickets账号" class="input-text">
                            </p>

                            <p class="form-row form-row-wide">
                                <input type="password" style="width:45%" id="payAccountPassWord" placeholder="密码" class="input-text">
                                <button class="button" style="float:right; width:45%" id="payBtn" type="submit">确认支付</button>
                            <div class="woocommerce-billing-fields">
                                <div id="order_review" style="position: relative;">
                                    <table class="shop_table">
                                        <thead>
                                        <th>区域</th>
                                        <th>价格</th>
                                        <th>数量</th>
                                        <th></th>
                                        </thead>
                                        <tbody>
                                        <%
                                            for (int i = 0; i < seatInfo.getDistrictList().size(); ++i) {
                                        %>
                                            <tr>
                                                <th><%=seatInfo.getDistrictList().get(i).getName()%></th>
                                                <td>¥ <%=seatState.getPriceList().get(i)%></td>
                                                <td>
                                                    <div class="quantity">
                                                        <input type="number" id="ticketNum<%=i%>" size="4" class="input-text qty text" title="Qty" value="0" name="quantity" min="0" step="1">
                                                    </div>

                                                </td>
                                                <td><input type="submit" style="" value="确认" id="placeCreateNotOrderBtn<%=i%>" class="button"></td>
                                            </tr>
                                            <script>
                                                $(document).ready(function () {
                                                    $("#placeCreateNotOrderBtn<%=i%>").click(function() {
                                                        let showId = <%=show.getId()%>;
                                                        let ticketNum = $("#ticketNum<%=i%>").val();
                                                        let districtId = <%=i%>;

                                                        console.log(showId);console.log(ticketNum);console.log(districtId);

                                                        createNotOrder(showId, ticketNum, districtId, function (message) {
                                                            console.log(message);
                                                            if (message == "SUCCESS") {
                                                                swal({
                                                                        title: "现场购票成功!",
                                                                        type: "success",
                                                                        confirmButtonText: "返回",
                                                                        closeOnConfirm: false
                                                                    },
                                                                    function (isConfirm) {
                                                                        if (isConfirm) {
                                                                            window.location.reload();
                                                                        }
                                                                    })
                                                            } else {
                                                                swal({
                                                                    title: "订单创建失败",
                                                                    type: "error",
                                                                    confirmButtonText: "返回"
                                                                })
                                                            }
                                                        });

                                                    });
                                                });

                                                function createNotOrder(showId, ticketNum, districtId, callback) {
                                                    $.ajax({
                                                        type: 'POST',
                                                        url: '/createNotOrder',
                                                        data: {
                                                            showId: showId,
                                                            ticketNum: ticketNum,
                                                            districtId: districtId,
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
                                        <%
                                            }
                                        %>

                                        </tbody>

                                    </table>
                                </div>
                            </div>
                            </p>
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
          $("#menu_showManage").addClass("active");
      });
    </script>
</body>
</html>