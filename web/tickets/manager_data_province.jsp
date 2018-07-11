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
        List<Object[]> list = (List<Object[]>)request.getAttribute("provinceList");
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
                            <div id="main" style="width: 800px;height:700px;"></div>
                            <div id="main1" style="width: 600px;height:400px; margin-top: 30px"></div>
                            <script type="text/javascript">

                    var myChart = echarts.init(document.getElementById('main'));
                    option = {
                        title : {
                            text: '各省份场馆收入统计',
                            subtext: '数据为实际收入'
                        },
                        tooltip : {
                            trigger: 'item'
                        },
                        dataRange: {
                            orient: 'horizontal',
                            min: 10000,
                            max: 500000,
                            text:['高','低'],           // 文本，默认为数值文本
                            splitNumber:0
                        },
                        toolbox: {
                            show : true,
                            orient: 'vertical',
                            x:'right',
                            y:'center',
                            feature : {
                                mark : {show: true},
                                dataView : {show: true, readOnly: false}
                            }
                        },
                        series : [
                            {
                                name: '各省份收入',
                                type: 'map',
                                mapType: 'china',
                                mapLocation: {
                                    x: 'left'
                                },
                                selectedMode : 'multiple',
                                itemStyle:{
                                    normal:{label:{show:true}},
                                    emphasis:{label:{show:true}}
                                },
                                data:[
                                    <%
                                                    for (int i = 0; i < list.size(); ++i) {
                                                        if (i != 0) out.print(",");
                                                        out.print("{name:'" + list.get(i)[0] + "',value:" + list.get(i)[1] + "}");
                                                    }
                                                    %>
                                ]
                            }
                        ],
                        animation: false
                    };
                    myChart.setOption(option,true);
                </script>
                            <script type="text/javascript">
                                var myChart1 = echarts.init(document.getElementById('main1'));
                                option1 = {
                                    title : {
                                        text: '各省份收入比例',
                                    },
                                    tooltip : {
                                        trigger: 'item',
                                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                                    },
                                    toolbox: {
                                        show : true,
                                        feature : {
                                            mark : {show: true},
                                            dataView : {show: true, readOnly: false},
                                            magicType : {
                                                show: true,
                                                type: ['pie', 'funnel'],
                                                option: {
                                                    funnel: {
                                                        x: '25%',
                                                        width: '50%',
                                                        funnelAlign: 'left',
                                                        max: 1548
                                                    }
                                                }
                                            },
                                            saveAsImage : {show: true}
                                        }
                                    },
                                    calculable : true,
                                    series : [
                                        {
                                            name:'访问来源',
                                            type:'pie',
                                            radius : '55%',
                                            center: ['50%', '60%'],
                                            data:[
                                                <%
                                                    for (int i = 0; i < list.size(); ++i) {
                                                        if (i != 0) out.print(",");
                                                        out.print("{name:'" + list.get(i)[0] + "',value:" + list.get(i)[1] + "}");
                                                    }
                                                    %>
                                            ]
                                        }
                                    ]
                                };
                                myChart1.setOption(option1);
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