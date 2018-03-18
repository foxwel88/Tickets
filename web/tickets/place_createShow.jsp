<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="edu.nju.tickets.model.Place" %>
<%@ page import="edu.nju.tickets.model.util.SeatInfo" %>
<%@ page import="edu.nju.tickets.model.Show" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tickets - 场馆管理</title>
    

    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/owl.carousel.css">
    <link rel="stylesheet" href="../style.css">
    <link rel="stylesheet" href="../css/responsive.css">
    <link href='../css/rome.css' rel='stylesheet' type='text/css' />
</head>

<body>

<%
    List<Show> showList = (List<Show>)request.getAttribute("showList");
    Place place = (Place)request.getAttribute("place");
    SeatInfo seatInfo = place.getSeatInfo();
%>

    <%@include file="head_place.jsp" %>
    <div class="single-product-area">
        <div class="container">
            <div class="col-md-2">
                <div class="single-sidebar">
                    <ul>

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
                <div class="product-breadcroumb">
                    <a href="">演出管理</a>
                    <a href="">创建新演出</a>
                </div>

                <div class="product-content-right">
                    <div id="customer_details" class="col2-set">
                        <div class="col-3">
                            <div class="woocommerce-billing-fields">
                                <p id="showName_field" class="form-row form-row-first validate-required">
                                    <label class="" for="showName">演出名称</label>
                                    <input type="text" value="" id="showName" class="input-text">
                                </p>

                                <p id="showDescribe_field" class="form-row form-row-first validate-required">
                                    <label class="" for="showDescribe">演出简介</label>
                                    <textarea placeholder="" id="showDescribe" class="input-text"></textarea>
                                </p>

                                <p id="showTime_field" class="form-row form-row-first validate-required">
                                    <label class="" for="dt">演出时间</label>

                                    <input id='dt' class="input-text">
                                </p>


                            </div>

                            <div class="woocommerce-billing-fields">

                                <div id="order_review" style="position: relative;">
                                    <table class="shop_table" cellspacing="0">
                                        <tbody>
                                        <tr class="cart-subtotal">
                                            <th>座位区域</th>
                                            <td style="width: 60%">价格</td>
                                        </tr>
                                        <%
                                            for (int i = 0; i < seatInfo.getDistrictList().size(); ++i) {
                                                String seatName = seatInfo.getDistrictName(i);
                                        %>
                                        <tr class="order-total">
                                            <th><%=seatName%></th>
                                            <td style="width: 60%">
                                                <input type="text" value="" id="districtPrice<%=i%>" class="input-text">
                                            </td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <input type="submit" value="创建演出" id="createShowBtn"  class="button alt">

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
<script src='../js/rome.js'></script>
<script src='../js/example.js'></script>

<script>
    $(document).ready(function(){
      $("#menu_showManage").addClass("active");
    });
</script>

<script>
    $(document).ready(function() {
        $("#createShowBtn").click(function () {
            let placeId = <%=place.getId()%>;
            let showName = $("#showName").val();
            let showDescribe = $("#showDescribe").val();
            let showTime = $("#dt").val();
            let priceString = "";

            for (let i = 0; i < <%=seatInfo.getDistrictList().size()%>; ++i) {
                if (i !== 0) {
                    priceString += ",";
                }
                priceString += ($("#districtPrice" + i).val());
            }

            console.log(placeId);
            console.log(showName);
            console.log(showDescribe);
            console.log(showTime);
            console.log(priceString);


            createShow(placeId, showName, showDescribe, showTime, priceString, function (message) {
                console.log(message);
                if (message !== -1) {
                    swal({
                            title: "创建成功",
                            type: "success",
                            confirmButtonText: "返回Tickets",
                            closeOnConfirm: false
                        },
                        function (isConfirm) {
                            if (isConfirm) {
                                window.location.href = '/placeShow?showId=' + message;
                            }
                        })
                } else {
                    swal({
                        title: "创建失败",
                        type: "error",
                        confirmButtonText: "返回"
                    })
                }
            });
        });
    });

    function createShow(placeId, showName, showDescribe, showTime, priceString, callback) {
        $.ajax({
            type: 'POST',
            url: '/createShow',
            data: {
                placeId: placeId,
                showName: showName,
                showDescribe: showDescribe,
                showTime: showTime,
                priceString: priceString
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