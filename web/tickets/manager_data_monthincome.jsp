<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="edu.nju.tickets.model.Place" %>
<%@ page import="edu.nju.tickets.model.util.SeatInfo" %>
<%@ page import="edu.nju.tickets.model.Show" %>
<%@ page import="edu.nju.tickets.util.TimeUtil" %>
<%@ page import="edu.nju.tickets.model.util.SeatState" %>
<%@ page import="edu.nju.tickets.model.PrePlace" %>
<%@ page import="edu.nju.tickets.model.PlaceAccount" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>Tickets - 经理</title>


      <script src="../js/echarts.min.js"></script>
      <script src="../js/china.js"></script>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/owl.carousel.css">
    <link rel="stylesheet" href="../style.css">
    <link rel="stylesheet" href="../css/responsive.css">

  </head>
<body>

    <%@include file="head_manager.jsp" %>

    <%
        List<Object[]> list = (List<Object[]>) request.getAttribute("incomeList");
    %>
    <div class="single-product-area">
        <div class="container">
            <div class="col-md-2">
                <div class="single-sidebar">
                    <ul>
                        <li><a href="/managerDataMonthIncome">集团运营统计</a></li>
                        <li><a href="/managerDataProvince">省份统计</a></li>
                        <li><a href="/managerDataPlaceIncome">场馆统计</a></li>
                        <li><a href="/managerDataUser">集团用户统计</a></li>
                        <li><a href="/managerDataIntegral">集团积分统计</a></li>
                    </ul>
                </div>
            </div>

            <div class="col-md-10">
                <div class="product-content-right">
                    <div id="customer_details" class="col2-set">
                        <div class="woocommerce-billing-fields">
                            <div id="main" style="width: 800px;height:500px;"></div>
                            <script type="text/javascript">

                                var myChart = echarts.init(document.getElementById('main'));
                                option = {
                                    tooltip : {
                                        trigger: 'axis'
                                    },
                                    toolbox: {
                                        show : true,
                                        feature : {
                                            mark : {show: true},
                                            dataView : {show: true, readOnly: false},
                                            magicType: {show: true, type: ['line', 'bar']},
                                            restore : {show: true},
                                            saveAsImage : {show: true}
                                        }
                                    },
                                    calculable : true,
                                    legend: {
                                        data:['实际收入金额','不含优惠收入金额','出票量','上座率']
                                    },
                                    xAxis : [
                                        {
                                            type : 'category',
                                            data : [<%
                                                    for (int i = 0; i < list.size(); ++i) {
                                                        if (i != 0) out.print(",");
                                                        out.print("\"" + list.get(i)[0] + "\"");
                                                    }
                                                    %>]
                                        }
                                    ],
                                    yAxis : [
                                        {
                                            type : 'value',
                                            name : '收入',
                                            axisLabel : {
                                                formatter: '{value} 元'
                                            }
                                        },
                                        {
                                            containLabel: true,
                                            type : 'value',
                                            name : '                出票量           上座率',
                                            axisLabel : {
                                                formatter: '{value} 张'
                                            }
                                        },
                                        {
                                            type : 'value',
                                            name : '',
                                            axisLabel : {
                                                formatter: '            {value} %'
                                            }
                                        }
                                    ],
                                    series : [

                                        {
                                            name:'实际收入金额',
                                            type:'bar',
                                            data:[<%
                                                    for (int i = 0; i < list.size(); ++i) {
                                                        if (i != 0) out.print(",");
                                                        out.print("" + list.get(i)[1] + "");
                                                    }
                                                    %>]
                                        },
                                        {
                                            name:'不含优惠收入金额',
                                            type:'bar',
                                            data:[<%
                                                    for (int i = 0; i < list.size(); ++i) {
                                                        if (i != 0) out.print(",");
                                                        out.print("" + list.get(i)[2] + "");
                                                    }
                                                    %>]
                                        },
                                        {
                                            name:'出票量',
                                            type:'line',
                                            yAxisIndex: 1,
                                            data:[<%
                                                    for (int i = 0; i < list.size(); ++i) {
                                                        if (i != 0) out.print(",");
                                                        out.print("" + list.get(i)[3] + "");
                                                    }
                                                    %>]
                                        },
                                        {
                                            name:'上座率',
                                            type:'line',
                                            yAxisIndex: 2,
                                            data:[67,85,67,75,67,97]
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
      $("#menu_managerData").addClass("active");
    });
</script>

</body>
</html>