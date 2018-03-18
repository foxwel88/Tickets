<%@ page import="edu.nju.tickets.model.Show" %>
<%@ page import="edu.nju.tickets.model.Place" %>
<%@ page import="edu.nju.tickets.model.util.SeatInfo" %>
<%@ page import="edu.nju.tickets.model.util.SeatState" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tickets - 演出</title>

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

    SeatState seatState = show.getSeatState();

    SeatInfo seatInfo = place.getSeatInfo();

    double lowPrice = 100000;
    for (int i = 0; i < seatState.getPriceList().size(); ++i) {
        if (seatState.getPriceList().get(i) < lowPrice) {
            lowPrice = seatState.getPriceList().get(i);
        }
    }
%>
    <%@include file="head.jsp" %>

    <div class="single-product-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <div class="row">
                <div class="col-md-9">
                    <div class="product-content-right">
                        <div class="product-breadcroumb">
                            <a href="">首页</a>
                            <a href="">演出</a>
                            <a href="/showSingle?showId=<%=show.getId()%>"><%=show.getName()%></a>
                        </div>
                        
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="product-images">
                                    <div class="product-main-img">
                                        <img src="../showpic/<%=show.getId()%>.jpg" alt="">
                                    </div>
                                    
                                    <div class="product-gallery">

                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-sm-8">
                                <div class="product-inner">
                                    <h2 class="order-id"><%=show.getName()%></h2>
                                    <div class="product-inner-price">
                                        <ins>¥ <%=lowPrice%> 起</ins>
                                    </div>    
                                    

                                    
                                    <div class="product-inner-category">
                                        <p>演出场馆: <a href=""><%=place.getName()%></a></p>
                                        <br>
                                        <p>演出时间: <a href=""><%=show.getTime()%></a></p>
                                    </div>
                                    <div class="woocommerce-info">
                                        本演出支持在线选座，不如试试？
                                        <a class="showlogin"  href="/showSelectSeat?showId=<%=show.getId()%>&districtId=0">选座下单</a>
                                    </div>
                                    <div role="tabpanel">
                                        <ul class="product-tab" role="tablist">
                                            <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">演出简介</a></li>
                                            <li role="presentation"><a href="#placePic" aria-controls="profile" role="tab" data-toggle="tab">场馆地图</a></li>
                                            <li role="presentation"><a href="#simpleOrder" aria-controls="profile" role="tab" data-toggle="tab">立刻下单</a></li>
                                        </ul>
                                        <div class="tab-content">
                                            <div role="tabpanel" class="tab-pane fade in active" id="home">
                                                <p><%=show.getDescribe()%></p>
                                            </div>
                                            <div role="tabpanel" class="tab-pane fade" id="placePic">

                                            </div>
                                            <div role="tabpanel" class="tab-pane fade" id="simpleOrder">
                                                <div class="woocommerce-info">
                                                    本演出支持在线选座，不如试试？
                                                    <a class="showlogin"  href="/showSelectSeat?showId=<%=show.getId()%>&districtId=0" >选座下单</a>
                                                </div>

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
                                                                        <td><input type="submit" style="" value="下单" id="createNotOrderBtn<%=i%>" class="button"></td>
                                                                    </tr>
                                                                    <script>
                                                                        $(document).ready(function () {
                                                                            $("#createNotOrderBtn<%=i%>").click(function() {
                                                                                let showId = <%=show.getId()%>;
                                                                                let ticketNum = $("#ticketNum<%=i%>").val();
                                                                                let districtId = <%=i%>;

                                                                                console.log(showId);console.log(ticketNum);console.log(districtId);

                                                                                createNotOrder(showId, ticketNum, districtId, function (message) {
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
                                            </div>
                                        </div>
                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                    </div>                    
                </div>


                <div class="col-md-3">
                    <div class="single-sidebar">
                        <h2 class="sidebar-title">热门演出</h2>
                        <%
                            for (int i = 0; i < 3; ++i) {
                                Show show11 = showList.get(i);
                        %>
                                <div class="thubmnail-recent">
                                    <img src="../showpic/<%=show11.getId()%>.jpg" class="recent-thumb" alt="">
                                    <h2><a href="/showSingle?showId=<%=show11.getId()%>"><%=show11.getName()%></a></h2>
                                </div>
                        <%
                            }
                        %>
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
            $("#menu_show").addClass("active");
        });
    </script>



</body>
</html>