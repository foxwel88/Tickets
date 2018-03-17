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
    <title>Tickets - 个人中心</title>
    

    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/owl.carousel.css">
    <link rel="stylesheet" href="../style.css">
    <link rel="stylesheet" href="../css/responsive.css">

  </head>
<body>

    <%@include file="head_manager.jsp" %>

    <%
        List<PrePlace> prePlaceList = (List<PrePlace>)request.getAttribute("prePlaceList");
    %>
    <div class="single-product-area">
        <div class="container">
            <div class="col-md-2">
                <div class="single-sidebar">
                    <ul>
                        <li><a href="/managerCheck">场馆修改审核</a></li>
                        <li><a href="/managerCheck">场馆申请审核</a></li>
                    </ul>
                </div>
            </div>
                
            <div class="col-md-10">
                <div class="product-breadcroumb">
                    <a href="">Tickets经理</a>
                    <a href="">场馆修改审核</a>
                </div>
                <%
                    if (prePlaceList.size() <= 0) {
                %>
                        <table cellspacing="0" class="shop_table cart">
                            <thead>
                            <tr>
                                <th>当前无待审核的场馆修改申请</th>
                            </tr>
                            </thead>
                        </table>
                <%
                    } else {
                %>
                        <table cellspacing="0" class="shop_table cart">
                            <thead>
                                <tr>
                                    <th style="width: 8%">申请编号</th>
                                    <th style="width: 8%">场馆编号</th>
                                    <th style="width: 12%">场馆名称</th>
                                    <th style="width: 12%">场馆地址</th>
                                    <th style="width: 25%">场馆描述</th>
                                    <th style="width: 25%">座位情况</th>
                                    <th style="width: 10%">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (int i = 0; i < prePlaceList.size(); ++i) {
                                        PrePlace prePlace = prePlaceList.get(i);
                                %>
                                        <tr class="cart_item">
                                            <td>
                                                <%=prePlace.getId()%>
                                            </td>

                                            <td>
                                                <%=prePlace.getPlaceId()%>
                                            </td>

                                            <td>
                                                <%=prePlace.getName()%>
                                            </td>

                                            <td>
                                                <%=prePlace.getAddress()%>
                                            </td>

                                            <td>
                                                <%=prePlace.getDescribe()%>
                                            </td>

                                            <td>
                                                <%
                                                    SeatInfo seatInfo = prePlace.getSeatInfo();
                                                    for (int j = 0; j < seatInfo.getDistrictList().size(); ++j) {
                                                        if (j != 0) out.print("<br>");
                                                        out.print(seatInfo.getDistrictName(j) + ":" + seatInfo.getDistrictList().get(j).getInfoString());
                                                    }
                                                %>

                                            </td>

                                            <td>
                                                <input type="submit" id="checkModifyBtn<%=prePlace.getId()%>" style="margin-bottom: 10px" value="确认" class="button">
                                                <input type="submit" id="unCheckModifyBtn<%=prePlace.getId()%>" value="拒绝" class="button">
                                                <script>
                                                    $(document).ready(function (){
                                                        $("#checkModifyBtn<%=prePlace.getId()%>") .click(function() {
                                                            let prePlaceId = <%=prePlace.getId()%>
                                                            console.log(prePlaceId);

                                                            checkModify(prePlaceId, function(message) {
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

                                                        $("#unCheckModifyBtn<%=prePlace.getId()%>") .click(function() {
                                                            let prePlaceId = <%=prePlace.getId()%>
                                                            console.log(prePlaceId);

                                                            unCheckModify(prePlaceId, function(message) {
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

                                                    function checkModify(prePlaceId, callback) {
                                                        $.ajax({
                                                            type: 'POST',
                                                            url: '/checkModify',
                                                            data: {
                                                                prePlaceId: prePlaceId,
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

                                                    function unCheckModify(prePlaceId, callback) {
                                                        $.ajax({
                                                            type: 'POST',
                                                            url: '/unCheckModify',
                                                            data: {
                                                                prePlaceId: prePlaceId,
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