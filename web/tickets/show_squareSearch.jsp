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
%>
    <%@include file="head.jsp" %>


    <div class="single-product-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <div class="row">

                <%
                    for (int i = 0; i < showList.size(); ++i) {
                        Show show = showList.get(i);
                        SeatState seatState = show.getSeatState();
                        double lowPrice = 100000;
                        for (int j = 0; j < seatState.getPriceList().size(); ++j) {
                            if (seatState.getPriceList().get(j) < lowPrice) {
                                lowPrice = seatState.getPriceList().get(j);
                            }
                        }
                %>
                        <div class="col-md-3 col-sm-6">
                            <div class="single-shop-product">
                                <div class="product-upper">
                                    <img src="../showpic/<%=show.getId()%>.jpg" alt="">
                                </div>
                                <h2><a href="/showSingle?showId=<%=show.getId()%>"><%=show.getName()%></a></h2>
                                <div class="product-carousel-price">
                                    <ins>¥<%=lowPrice%>起</ins>
                                </div>

                                <div class="product-option-shop">
                                    <a class="add_to_cart_button"  href="/showSingle?showId=<%=show.getId()%>">立即下单</a>
                                </div>
                            </div>
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
            $("#menu_show").addClass("active");
        });
    </script>



</body>
</html>