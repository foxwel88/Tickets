<!DOCTYPE html>
<%@ page import="edu.nju.tickets.model.User" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="edu.nju.tickets.model.Show" %>
<%@ page import="edu.nju.tickets.model.Place" %>
<%@ page import="edu.nju.tickets.model.util.SeatState" %>
<%@ page import="edu.nju.tickets.model.util.SeatInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tickets - 选座</title>
    

    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/owl.carousel.css">
    <link rel="stylesheet" href="../style.css">
    <link rel="stylesheet" href="../css/responsive.css">
      <link rel="stylesheet" href="../css/selectseat.css">

  </head>
<body>

<jsp:useBean id="user" class="edu.nju.tickets.model.User" scope="page"></jsp:useBean>
<%
    String userName = (String)request.getSession().getAttribute("userName");
    Show show = (Show)request.getAttribute("show");
    Place place = (Place)request.getAttribute("place");
    SeatState seatState = show.getSeatState();
    SeatInfo seatInfo = place.getSeatInfo();
    int districtId = (int)request.getAttribute("districtId");
    int startSeatId = seatInfo.getSeatId(districtId, 0, 0);
%>

    <%@include file="head.jsp" %>

    <div class="single-product-area">
        <div class="container">
            <div class="col-md-2">
                <div class="single-sidebar">
                    <ul>
                        <%
                            for (int i = 0; i < seatInfo.getDistrictList().size(); ++i) {

                        %>
                                <li><a href="/showSelectSeat?showId=<%=show.getId()%>&districtId=<%=i%>"><%=seatInfo.getDistrictName(i)%>[¥<%=seatState.getPrice(i)%>]</a></li>
                        <%
                            }
                        %>
                    </ul>
                </div>
            </div>
                
            <div class="col-md-10">
                <div class="demo">
                    <div id="seat-map">
                        <div class="front"><%=seatInfo.getDistrictName(districtId)%></div>
                    </div>
                    <div class="booking-details">
                        <p>座位：</p>
                        <ul id="selected-seats"></ul>
                        <p>票数：<span id="counter">0</span></p>
                        <p>总计：<b>￥<span id="total">0</span></b></p>
                        <button id="createSelectOrderBtn" class="checkout-button">下单</button>
                        <div id="legend"></div>
                    </div>
                    <div style="clear:both"></div>
                </div>

            </div>
        </div>
    </div>

<script type="text/javascript">
    var price = <%=seatState.getPrice(districtId)%>; //票价
    var result = new Set;
    $(document).ready(function() {
        var $cart = $('#selected-seats'), //座位区
            $counter = $('#counter'), //票数
            $total = $('#total'); //总计金额

        var sc = $('#seat-map').seatCharts({
            map: [  //座位图
                <%
                    int maxSize = 0;
                    List<Integer> disList = seatInfo.getDistrictList().get(districtId).getInfoList();
                    for (int i = 0; i < disList.size(); ++i) {
                        if (disList.get(i) > maxSize) {
                            maxSize = disList.get(i);
                        }
                    }
                    for (int i = 0; i < disList.size(); ++i) {
                        if (i!= 0) {
                            out.print(",");
                        }
                        out.print("\'");
                        for (int j = 0; j < disList.get(i); ++j) {
                            out.print('a');
                        }
                        if (i == 0) {
                            if (disList.get(0) < maxSize) {
                                for (int j = 0; j < (maxSize - disList.get(0)); ++j) {
                                    out.print('_');
                                }
                            }
                        }
                        out.print("\'");
                    }
                %>
            ],
            naming : {
                top : false,
                getLabel : function (character, row, column) {
                    return column;
                }
            },
            legend : { //定义图例
                node : $('#legend'),
                items : [
                    [ 'a', 'available',   '可选座' ],
                    [ 'a', 'unavailable', '已售出']
                ]
            },
            click: function () {
                if (this.status() === 'available') {
                    if (sc.find('selected').length > 5) {
                        return 'available';
                    }
                    let rowId = this.settings.row;
                    let sitId = this.settings.label - 1;
                    result.add(rowId + "_" + sitId);
                    console.log(result);

                    $('<li>' + (rowId + 1) + '排' + (sitId + 1) + '座</li>')
                        .attr('id', 'cart-item-'+this.settings.id)
                        .data('seatId', this.settings.id)
                        .appendTo($cart);
                    $counter.text(sc.find('selected').length+1);
                    $total.text(recalculateTotal(sc)+price);

                    return 'selected';
                } else if (this.status() === 'selected') {
                    let rowId = this.settings.row;
                    let sitId = this.settings.label - 1;
                    result.delete(rowId + "_" + sitId);
                    console.log(result);

                    $counter.text(sc.find('selected').length-1);
                    $total.text(recalculateTotal(sc)-price);
                    $('#cart-item-'+this.settings.id).remove();

                    return 'available';
                } else if (this.status() === 'unavailable') {
                    return 'unavailable';
                } else {
                    return this.style();
                }
            }
        });
        <%
            out.print("sc.get([");
            boolean flag = false;
            for (int i = 0; i < disList.size(); ++i) {
                for (int j = 0; j < disList.get(i); ++j) {
                    if (seatState.getSeatSate(seatInfo.getSeatId(districtId, i, j)).equals("true")) {
                        if (flag) {
                            out.print(",");
                        }
                        flag = true;

                        out.print("\'");
                        out.print((i+1) + "_" + (j + 1));
                        out.print("\'");
                    }
                }
            }
            out.print("]).status('unavailable')");
        %>

    });

    function recalculateTotal(sc) {
        var total = 0;
        sc.find('selected').each(function () {
            total += price;
        });

        return total;
    }

    $(document).ready(function () {
        $("#createSelectOrderBtn").click(function() {
            let flag = "false";
            let s = "";
            for(let str of result) {
                if (flag === "true") {
                    s = s + "@";
                }
                flag = "true";
                s = s + str;
            }
            let userName = "<%=(String)session.getAttribute("userName")%>";
            let showId = <%=show.getId()%>;
            let price = <%=seatState.getPrice(districtId)%>;
            let districtId = <%=districtId%>;

            console.log(userName);console.log(showId);console.log(price);console.log(s);

            createSelectOrder(userName, showId, price, s, districtId, function (message) {
                console.log(message);
                if (message == "SUCCESS") {
                    swal({
                            title: "订单创建成功!",
                            type: "success",
                            confirmButtonText: "去支付",
                            closeOnConfirm: false
                        },
                        function (isConfirm) {
                            if (isConfirm) {
                                window.location.href = '/orderUnPaied';
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

    function createSelectOrder(userName, showId, price, seatStr, districtId, callback) {
        $.ajax({
            type: 'POST',
            url: '/createSelectOrder',
            data: {
                userName: userName,
                showId: showId,
                price: price,
                districtId: districtId,
                seatStr: seatStr,
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
<script>

</script>


<%@include file="tail.jsp" %>

<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/owl.carousel.min.js"></script>
<script src="../js/jquery.sticky.js"></script>
<script src="../js/jquery.easing.1.3.min.js"></script>
<script src="../js/main.js"></script>
<script src="../js/jquery.seat-charts.min.js"></script>
    <script>
      $(document).ready(function(){
          $("#menu_show").addClass("active");
      });
    </script>
</body>
</html>