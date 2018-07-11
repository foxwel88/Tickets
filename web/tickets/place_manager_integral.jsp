<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="edu.nju.tickets.model.Place" %>
<%@ page import="edu.nju.tickets.model.util.SeatInfo" %>
<%@ page import="edu.nju.tickets.model.PlaceAccount" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tickets - 场馆管理</title>


      <script src="../js/echarts.min.js"></script>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/owl.carousel.css">
    <link rel="stylesheet" href="../style.css">
    <link rel="stylesheet" href="../css/responsive.css">

  </head>
<body>

<%
    List<Object[]> list = (List<Object[]>) request.getAttribute("integralList");
    List<Object[]> list1 = (List<Object[]>) request.getAttribute("incomeList");
%>
    <%@include file="head_place.jsp" %>

    <div class="single-product-area">
        <div class="container">
            <div class="col-md-2">
                <div class="single-sidebar">
                    <ul>
                        <li><a href="/placeCalc">场馆财务</a></li>
                        <li><a href="/placeDataUser">客户分析</a></li>
                        <li><a href="/placeDataSeatPercent">出票与上座率分析</a></li>
                        <li><a href="/placeDataIncome">收入分析</a></li>
                        <li><a href="/placeDataIntegral">积分兑换分析</a></li>
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
                                            text: '集团积分兑换率与优惠分析'
                                        },
                                        tooltip : {
                                            trigger: 'axis'
                                        },
                                        legend: {
                                            data:['积分兑换率','优惠量']
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
                                                type : 'value',
                                                name : '兑换率',
                                                axisLabel : {
                                                    formatter: '{value}%'
                                                }
                                            },
                                            {
                                                type : 'value',
                                                name : '优惠量'
                                            }
                                        ],
                                        series : [
                                            {
                                                name:'积分兑换率',
                                                type:'line',
                                                data:[
                                                    <%
                                                    for (int i = 0; i < list.size(); ++i) {
                                                        if (i != 0) out.print(",");
                                                        out.print("" + ((BigDecimal)list.get(i)[1]).doubleValue()*100 + "");
                                                    }
                                                    %>
                                                ]
                                            },
                                            {
                                                name:'优惠量',
                                                type:'line',
                                                yAxisIndex: 1,
                                                data:[
                                                    <%
                                                    for (int i = 0; i < list.size(); ++i) {
                                                        if (i != 0) out.print(",");
                                                        out.print("" + ((double)list1.get(i)[1] - ((double)list1.get(i)[2])) + "");
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
          $("#menu_placeCalc").addClass("active");
      });
    </script>
</body>
</html>