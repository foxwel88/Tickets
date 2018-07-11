<%@ page import="java.util.List" %>
<!DOCTYPE html>

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

    <script src="../js/echarts.min.js"></script>

</head>
<body>

<%
    List<Object[]> list = (List<Object[]>) request.getAttribute("integralList");
%>
    <%@include file="head.jsp" %>

    <div class="single-product-area">
        <div class="container">
            <div class="col-md-2">
                <div class="single-sidebar">
                    <ul>
                        <li><a href="/userDataIncome">个人购票分析</a></li>
                        <li><a href="/userDataIntegral">积分使用分析</a></li>
                        <li><a href="/userDataPlacetype">购票场馆分析</a></li>
                    </ul>
                </div>
            </div>

            <div class="col-md-10">
                <div class="product-content-right">
                    <div id="customer_details" class="col2-set">
                        <div class="woocommerce-billing-fields">
                            <div id="main" style="width: 600px;height:400px;"></div>
                            <script type="text/javascript">

                                var myChart = echarts.init(document.getElementById('main'));

                                option = {
                                    title: {
                                        text: '积分兑换分析'
                                    },
                                    tooltip : {
                                        trigger: 'axis'
                                    },
                                    legend: {
                                        data:['积分兑换量']
                                    },
                                    toolbox: {
                                        show : true,
                                        feature : {
                                            mark : {show: true},
                                            dataView : {show: true, readOnly: false},
                                            magicType : {show: true, type: ['line', 'bar']},
                                            restore : {show: true},
                                            saveAsImage : {show: true}
                                        }
                                    },
                                    calculable : true,
                                    xAxis : [
                                        {
                                            type : 'category',
                                            boundaryGap : false,
                                            data : [
                                                <%
                                                for (int i = 0; i < list.size(); ++i) {
                                                    if (i != 0) out.print(",");
                                                    out.print("\"" + list.get(i)[0] + "\"");
                                                }
                                                %>
                                            ]
                                        }
                                    ],
                                    yAxis : [
                                        {
                                            type : 'value'
                                        }
                                    ],
                                    series : [
                                        {
                                            name:'积分兑换量',
                                            type:'line',
                                            data:[
                                                <%
                                                for (int i = 0; i < list.size(); ++i) {
                                                    if (i != 0) out.print(",");
                                                    out.print("" + (list.get(i)[1]) + "");
                                                }
                                                %>
                                            ]
                                        }
                                    ]
                                };
                                myChart.setOption(option);
                            </script>
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
        $("#menu_data").addClass("active");
    });
</script>

<script>
    $(document).ready(function () {
        $("#emailBtn").click(function() {
            let emailAddress = $("#emailAddress").val();
            console.log(emailAddress);


            sendEmail(emailAddress, function (message) {
                console.log(message);
                if (message == "SUCCESS") {
                    $("#emailBtn").val("重新发送");
                }
                if (message == "FAIL_SENDING_EMAIL") {
                    swal({
                        title: "邮件发送失败",
                        type: "error",
                        confirmButtonText: "返回"
                    })
                }
            });

        });
    });

    function sendEmail(emailAddress, callback) {
        $.ajax({
            type: 'POST',
            url: '/sendEmail',
            data: {
                emailAddress: emailAddress,
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
    $(document).ready(function () {
        $("#modifyEmailBtn").click(function() {
            let userName = "<%=session.getAttribute("userName")%>";
            let passWord = $("#passWord").val();
            let emailAddress = $("#emailAddress").val();
            let checkNumber = $("#checkNumber").val();

            console.log(userName);
            console.log(passWord);
            console.log(emailAddress);
            console.log(checkNumber);


            modifyEmail(userName, passWord, emailAddress, checkNumber, function (message) {
                console.log(message);
                if (message == "SUCCESS") {
                    swal({
                            title: "修改成功!",
                            type: "success",
                            confirmButtonText: "返回Tickets",
                            closeOnConfirm: false
                        },
                        function (isConfirm) {
                            if (isConfirm) {
                                window.location.href = '/userInfo';
                            }
                        })
                }
                if (message == "NO_USER") {
                    swal({
                        title: "不存在此用户",
                        type: "error",
                        confirmButtonText: "返回"
                    })
                }
                if (message == "WRONG_PASSWORD") {
                    swal({
                        title: "密码错误",
                        type: "error",
                        confirmButtonText: "返回"
                    })
                }
            });

        });
    });

    function modifyEmail(userName, passWord, emailAddress, checkNumber, callback) {
        $.ajax({
            type: 'POST',
            url: '/modifyEmail',
            data: {
                userName: userName,
                passWord: passWord,
                emailAddress: emailAddress,
                checkNumber: checkNumber
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