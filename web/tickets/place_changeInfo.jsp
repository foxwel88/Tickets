<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="edu.nju.tickets.model.Place" %>
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

  </head>
<body>

<%
    Place place = (Place)request.getAttribute("place");
    String placeIdStr = String.valueOf(place.getId());
    while (placeIdStr.length() < 7) placeIdStr = "0" + placeIdStr;
%>
    <%@include file="head_place.jsp" %>

    <div class="single-product-area">
        <div class="container">
            <div class="col-md-2">
                <div class="single-sidebar">
                    <ul>
                        <li><a href="/placeInfo">场馆信息</a></li>
                        <li><a href="/placeChangeInfo">修改信息</a></li>
                        <li><a href="/placeChangeSeatInfo">修改场馆座位</a></li>
                    </ul>
                </div>
            </div>
                
            <div class="col-md-10">
                <div class="product-breadcroumb">
                    <a href="">场馆管理</a>
                    <a href="">场馆信息</a>
                </div>
                <div class="product-content-right">
                    <div id="customer_details" class="col2-set">
                        <div class="col-1">
                            <div class="woocommerce-billing-fields">

                                <p id="placeName_field" class="form-row form-row-first validate-required">
                                    <label class="" for="placeName">场馆名称</label>
                                    <input type="text" value="<%=place.getName()%>" id="placeName" class="input-text ">
                                </p>

                                <p id="placeAddress_field" class="form-row form-row-first validate-required">
                                    <label class="" for="placeAddress">场馆地址</label>
                                    <input type="text" value="<%=place.getAddress()%>" id="placeAddress" class="input-text ">
                                </p>

                                <p id="placeDescribe_field" class="form-row form-row-first validate-required">
                                    <label class="" for="placeDescribe">场馆描述</label>
                                    <textarea id="placeDescribe" class="input-text "><%=place.getDescribe()%></textarea>
                                </p>
                                <input type="submit" data-value="Place order" value="确认修改" id="changePlaceInfoBtn" class="button alt">

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
      $("#menu_placeManage").addClass("active");
  });
</script>

<script>
    $(document).ready(function() {
        $("#changePlaceInfoBtn").click(function () {
            let placeId = <%=place.getId()%>;
            let placeName = $("#placeName").val();
            let placeAddress = $("#placeAddress").val();
            let placeDescribe = $("#placeDescribe").val();

            console.log(placeId);
            console.log(placeName);
            console.log(placeAddress);
            console.log(placeDescribe);


            changePlaceInfo(placeId, placeName, placeAddress, placeDescribe, function (message) {
                console.log(message);
                if (message !== -1) {
                    swal({
                            title: "修改申请编号为" + message,
                            type: "success",
                            confirmButtonText: "返回Tickets",
                            closeOnConfirm: false
                        },
                        function (isConfirm) {
                            if (isConfirm) {
                                window.location.href = '/placeInfo';
                            }
                        })
                } else {
                    swal({
                        title: "修改失败",
                        type: "error",
                        confirmButtonText: "返回"
                    })
                }
            });
        });
    });

    function changePlaceInfo(placeId, placeName, placeAddress, placeDescribe, callback) {
        $.ajax({
            type: 'POST',
            url: '/changePlaceInfo',
            data: {
                placeId: placeId,
                placeName: placeName,
                placeAddress: placeAddress,
                placeDescribe: placeDescribe
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