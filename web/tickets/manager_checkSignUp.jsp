<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="edu.nju.tickets.model.Place" %>
<%@ page import="edu.nju.tickets.model.util.SeatInfo" %>
<%@ page import="edu.nju.tickets.model.Show" %>
<%@ page import="edu.nju.tickets.util.TimeUtil" %>
<%@ page import="edu.nju.tickets.model.util.SeatState" %>
<%@ page import="edu.nju.tickets.model.PrePlace" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tickets - 经理</title>
    

    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/owl.carousel.css">
    <link rel="stylesheet" href="../style.css">
    <link rel="stylesheet" href="../css/responsive.css">

  </head>
<body>

    <%@include file="head_manager.jsp" %>

    <%
        List<Place> placeList = (List<Place>)request.getAttribute("placeList");
    %>
    <div class="single-product-area">
        <div class="container">
            <div class="col-md-2">
                <div class="single-sidebar">
                    <ul>
                        <li><a href="/managerCheck">场馆修改审核</a></li>
                        <li><a href="/managerCheckSignUp">场馆注册审核</a></li>
                    </ul>
                </div>
            </div>
                
            <div class="col-md-10">
                <div class="product-breadcroumb">
                    <a href="/managerCheck">Tickets经理</a>
                    <a href="/managerCheckSignUp">场馆注册审核</a>
                </div>
                <%
                    if (placeList.size() <= 0) {
                %>
                        <table cellspacing="0" class="shop_table cart">
                            <thead>
                            <tr>
                                <th>当前无待审核的场馆注册申请</th>
                            </tr>
                            </thead>
                        </table>
                <%
                    } else {
                %>
                        <table cellspacing="0" class="shop_table cart">
                            <thead>
                                <tr>
                                    <th style="width: 10%">场馆编号</th>
                                    <th style="width: 12%">场馆名称</th>
                                    <th style="width: 15%">场馆地址</th>
                                    <th style="width: 25%">场馆描述</th>
                                    <th style="width: 28%">座位情况</th>
                                    <th style="width: 10%">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (int i = 0; i < placeList.size(); ++i) {
                                        Place place = placeList.get(i);
                                %>
                                        <tr class="cart_item">
                                            <td>
                                                <%=place.getId()%>
                                            </td>

                                            <td>
                                                <%=place.getName()%>
                                            </td>

                                            <td>
                                                <%=place.getAddress()%>
                                            </td>

                                            <td>
                                                <%=place.getDescribe()%>
                                            </td>

                                            <td>
                                                <%
                                                    SeatInfo seatInfo = place.getSeatInfo();
                                                    for (int j = 0; j < seatInfo.getDistrictList().size(); ++j) {
                                                        if (j != 0) out.print("<br>");
                                                        out.print(seatInfo.getDistrictName(j) + ":" + seatInfo.getDistrictList().get(j).getInfoString());
                                                    }
                                                %>

                                            </td>

                                            <td>
                                                <input type="submit" id="checkSingUpBtn<%=place.getId()%>" style="margin-bottom: 10px" value="确认" class="button">
                                                <input type="submit" id="unCheckSignUpBtn<%=place.getId()%>" value="拒绝" class="button">
                                                <script>
                                                    $(document).ready(function (){
                                                        $("#checkSingUpBtn<%=place.getId()%>") .click(function() {
                                                            let placeId = <%=place.getId()%>
                                                            console.log(placeId);

                                                            checkSignUp(placeId, function(message) {
                                                                if(message){
                                                                    swal({
                                                                            title: "确认成功!",
                                                                            type: "success",
                                                                            confirmButtonText: "返回 Tickets",
                                                                            closeOnConfirm: false
                                                                        },
                                                                        function(isConfirm){
                                                                            if(isConfirm){
                                                                                window.location.reload();
                                                                            }
                                                                        })
                                                                } else {
                                                                    swal({
                                                                        title: "确认失败!",
                                                                        type: "error",
                                                                        confirmButtonText: "返回"
                                                                    })
                                                                }
                                                            });

                                                        });

                                                        $("#unCheckSignUpBtn<%=place.getId()%>") .click(function() {
                                                            let placeId = <%=place.getId()%>
                                                            console.log(placeId);

                                                            unCheckSignUp(placeId, function(message) {
                                                                if(message){
                                                                    swal({
                                                                            title: "拒绝成功!",
                                                                            type: "success",
                                                                            confirmButtonText: "返回 Tickets",
                                                                            closeOnConfirm: false
                                                                        },
                                                                        function(isConfirm){
                                                                            if(isConfirm){
                                                                                window.location.reload();
                                                                            }
                                                                        })
                                                                } else {
                                                                    swal({
                                                                        title: "拒绝失败!",
                                                                        type: "error",
                                                                        confirmButtonText: "返回"
                                                                    })
                                                                }
                                                            });

                                                        });
                                                    });

                                                    function checkSignUp(placeId, callback) {
                                                        $.ajax({
                                                            type: 'POST',
                                                            url: '/checkSignUp',
                                                            data: {
                                                                placeId: placeId,
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

                                                    function unCheckSignUp(placeId, callback) {
                                                        $.ajax({
                                                            type: 'POST',
                                                            url: '/unCheckSignUp',
                                                            data: {
                                                                placeId: placeId,
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
      $("#menu_managerCheck").addClass("active");
    });
</script>

</body>
</html>