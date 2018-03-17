<!DOCTYPE html>
<%@ page import="edu.nju.tickets.model.User" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tickets - 个人中心</title>
    

    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/sweet-alert.css">
    <link rel="stylesheet" href="../css/owl.carousel.css">
    <link rel="stylesheet" href="../css/font-awesome.css">
    <link rel="stylesheet" href="../style.css">
    <link rel="stylesheet" href="../css/responsive.css">
    <link rel="stylesheet" href="../css/seat.css">

</head>
<body>

<%@include file="head_null.jsp" %>

<div style="width: 40%; margin:40px auto;" >
    <div id="customer_details" class="col2-set">
        <div class="woocommerce-billing-fields">

            <p id="placeName_field" class="form-row form-row-first validate-required">
                <label class="" for="placeName">场馆名称</label>
                <input type="text" value="" id="placeName" class="input-text ">
            </p>

            <p id="placeAddress_field" class="form-row form-row-first validate-required">
                <label class="" for="placeAddress">场馆地址</label>
                <input type="text" value="" id="placeAddress" class="input-text ">
            </p>

            <p id="placeDescribe_field" class="form-row form-row-first validate-required">
                <label class="" for="placeDescribe">场馆描述</label>
                <textarea id="placeDescribe" class="input-text "></textarea>
            </p>

            <label class="" for="placeDescribe">座位设置</label>
        </div>
    </div>
    <div class="woocommerce-billing-fields">
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
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
                            <input id="seatInfo0" style="width: 80%" type="text" placeholder="请输入座位编排，例如：10,11,12,13,14" value=""/>
                        </div>
                    </div>
                </div>
            </div>
            <input type="submit" style="margin-left: 23px" data-value="Place order" value="增加区域" id="addDistrictBtn" class="button alt">
            <input type="submit" style="float:right;  margin-right: 23px" data-value="Place order" value="注册" id="placeSignUpBtn" class="button alt">

    </div>
</div>



<%@include file="tail.jsp" %>

<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/owl.carousel.min.js"></script>
<script src="../js/jquery.sticky.js"></script>
<script src="../js/jquery.easing.1.3.min.js"></script>
<script src="../js/main.js"></script>
<script src="../js/sweet-alert.js"></script>

<script>
      $(document).ready(function(){
          $("#menu_user").addClass("active");
      });
</script>

<script>
    $(document).ready(function() {
        var length = 0;
        $("#addDistrictBtn").click(function () {
            length = length + 1;
            $("#accordion").append(`<div class="panel panel-default">
                                                <div class="panel-heading" role="tab" id="heading` + length + `">
                                                    <h4 class="panel-title">
                                                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse` + length + `" aria-expanded="false" aria-controls="collapse` + length +`">
                                                            <input id="seatName`+ length + `" type="text" placeholder="请输入区域名称" style="height: 25px"/>
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


        $("#placeSignUpBtn").click(function () {
            let placeName = $("#placeName").val();
            let placeAddress = $("#placeAddress").val();
            let placeDescribe = $("#placeDescribe").val();
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

            console.log(placeName);
            console.log(placeAddress);
            console.log(placeDescribe);
            console.log(nameString);
            console.log(infoString);


            placeSignUp(placeName, placeAddress, placeDescribe, nameString, infoString, function (message) {
                console.log(message);
                if (message !== -1) {
                    swal({
                            title: "场馆注册编号为" + message,
                            type: "success",
                            confirmButtonText: "登陆",
                            closeOnConfirm: false
                        },
                        function (isConfirm) {
                            if (isConfirm) {
                                window.location.href = '/placeInfo';
                            }
                        })
                } else {
                    swal({
                        title: "注册失败",
                        type: "error",
                        confirmButtonText: "返回"
                    })
                }
            });
        });
    });

    function placeSignUp(placeName, placeAddress, placeDescribe, nameString, infoString, callback) {
        $.ajax({
            type: 'POST',
            url: '/placeSignUpPost',
            data: {
                placeName: placeName,
                placeAddress: placeAddress,
                placeDescribe: placeDescribe,
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