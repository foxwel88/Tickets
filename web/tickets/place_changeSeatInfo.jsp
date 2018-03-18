<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="edu.nju.tickets.model.Place" %>
<%@ page import="edu.nju.tickets.model.util.SeatInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>Tickets - 场馆管理</title>
    

    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/bootstrap.css">
    <link rel="stylesheet" href="../css/font-awesome.css">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/owl.carousel.css">
    <link rel="stylesheet" href="../style.css">
    <link rel="stylesheet" href="../css/responsive.css">
    <link rel="stylesheet" href="../css/seat.css">

  </head>
<body>

<%
    Place place = (Place)request.getAttribute("place");
    String placeIdStr = String.valueOf(place.getId());
    while (placeIdStr.length() < 7) placeIdStr = "0" + placeIdStr;
    SeatInfo seatInfo = place.getSeatInfo();

    int districtNum = 1;

    if ((seatInfo == null) || (seatInfo.getDistrictList() == null) || (seatInfo.getDistrictList().size() == 0)) {
        districtNum = 1;
    } else {
        districtNum = seatInfo.getDistrictList().size();
    }
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
                    <a href="/placeInfo">场馆管理</a>
                    <a href="/placeChangeSeatInfo">修改场馆座位</a>
                </div>

                <div class="product-content-right">
                    <div class="col2-set">
                        <div class="col-3">
                            <div class="woocommerce-billing-fields">
                                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                                <%
                                    if ((seatInfo == null) || (seatInfo.getDistrictList() == null) || (seatInfo.getDistrictList().size() == 0)) {
                                %>
                                        <div class="panel panel-default">
                                            <div class="panel-heading" role="tab" id="heading0">
                                                <h4 class="panel-title">
                                                    <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse0" aria-expanded="true" aria-controls="collapse0">
                                                        <input id="seatName0" type="text" value="" placeholder="请输入区域名称" style="height: 30px"/>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapse0" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading0">
                                                <div class="panel-body">
                                                    <input id="seatInfo0" style="width: 80%" type="text" placeholder="请输入座位编排，例如：10,11,12,13,14"/>
                                                </div>
                                            </div>
                                        </div>
                                <%
                                    } else {
                                %>
                                        <div class="panel panel-default">
                                            <div class="panel-heading" role="tab" id="heading0">
                                                <h4 class="panel-title">
                                                    <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse0" aria-expanded="true" aria-controls="collapse0">
                                                        <input id="seatName0" type="text" value="<%=seatInfo.getDistrictList().get(0).getName()%>" style="height: 30px"/>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapse0" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading0">
                                                <div class="panel-body">
                                                    <input id="seatInfo0" style="width: 80%" type="text" value="<%=seatInfo.getDistrictList().get(0).getInfoString()%>"/>
                                                </div>
                                            </div>
                                        </div>
                                <%
                                        for (int i = 1; i < seatInfo.getDistrictList().size(); ++i) {
                                %>
                                            <div class="panel panel-default">
                                                <div class="panel-heading" role="tab" id="heading<%=i%>">
                                                    <h4 class="panel-title">
                                                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse<%=i%>" aria-expanded="false" aria-controls="collapse<%=i%>">
                                                            <input id="seatName<%=i%>" type="text" value="<%=seatInfo.getDistrictList().get(i).getName()%>" style="height: 30px"/>
                                                        </a>
                                                    </h4>
                                                </div>
                                                <div id="collapse<%=i%>" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading<%=i%>">
                                                    <div class="panel-body">
                                                        <input id="seatInfo<%=i%>" style="width: 80%" type="text" value="<%=seatInfo.getDistrictList().get(i).getInfoString()%>"/>
                                                    </div>
                                                </div>
                                            </div>
                                <%
                                        }
                                    }
                                %>
                            </div>
                                <input type="submit" style="margin-left: 23px" data-value="Place order" value="增加区域" id="addDistrictBtn" class="button alt">
                                <input type="submit" style="float:right; margin-right: 23px" data-value="Place order" value="确认修改" id="changePlaceSeatInfoBtn" class="button alt">
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
        var length = <%=districtNum%>;
        length = length - 1;
        console.log(length);
        $("#addDistrictBtn").click(function () {
            length = length + 1;
            console.log(length);
            $("#accordion").append(`<div class="panel panel-default">
                                                <div class="panel-heading" role="tab" id="heading` + length + `">
                                                    <h4 class="panel-title">
                                                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse` + length + `" aria-expanded="false" aria-controls="collapse` + length +`">
                                                            <input id="seatName`+ length + `" type="text" placeholder="请输入区域名称" style="height: 30px"/>
                                                        </a>
                                                    </h4>
                                                </div>
                                                <div id="collapse` + length + `" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading` + length + `">
                                                    <div class="panel-body">
                                                        <input id="seatInfo`+ length + `" style="width: 80%" type="text" placeholder="请输入座位编排，例如：10,11,12,13,14"/>
                                                    </div>
                                                </div>
                                            </div>`);
        });


        $("#changePlaceSeatInfoBtn").click(function () {
            let placeId = <%=place.getId()%>;
            let nameString = "";
            let infoString = "";

            for (let i = 0; i <= length; ++i) {
                if (i !== 0) {
                    nameString += "@";
                    infoString += "@";
                }
                nameString += ($("#seatName" + i).val());
                infoString += ($("#seatInfo" + i).val());
            }

            console.log(placeId);
            console.log(nameString);
            console.log(infoString);


            changePlaceSeatInfo(placeId, nameString, infoString, function (message) {
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
                                window.location.href = '/placeChangeSeatInfo';
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

    function changePlaceSeatInfo(placeId, nameString, infoString, callback) {
        $.ajax({
            type: 'POST',
            url: '/changePlaceSeatInfo',
            data: {
                placeId: placeId,
                nameString: nameString,
                infoString: infoString,
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