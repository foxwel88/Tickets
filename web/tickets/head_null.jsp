<%--
  Created by IntelliJ IDEA.
  User: foxwel
  Date: 2018/3/14
  Time: 下午2:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="../css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="../css/font-awesome.min.css" rel="stylesheet" media="screen">
<link href="../css/login.css" rel="stylesheet" media="screen">
<link href="../css/dl.css" rel="stylesheet" media="screen">
<link href="../css/sweet-alert.css" rel="stylesheet" type="text/css" media="all" />


<div class="header-area">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="user-menu">
                    <ul>
                    </ul>
                </div>
            </div>

            <div class="col-md-4">
            </div>
        </div>
    </div>
</div> <!-- End header area -->

<div class="site-branding-area">
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
                <div class="logo">
                    <h1><a href="./"><img src="../img/logo.png"></a></h1>
                </div>
            </div>

            <div class="col-sm-6">
            </div>
        </div>
    </div>
</div> <!-- End site branding area -->

<div class="mainmenu-area">
    <div class="container">
        <div class="row">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li id="menu_index"><a href="#">&nbsp;Tickets&nbsp;注册&nbsp;</a></li>
                </ul>
            </div>
        </div>
    </div>
</div> <!-- End mainmenu area -->

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="background: white">
        <div class="modal-content2 tcc">
            <div class="modal-header2" style="margin-top:20px">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    ×
                </button>
                <h4 class="modal-title" id="myModalLabel">

                </h4>
            </div>
            <div class="modal-body tcck">
                <div class="input-group input-group-lg parentCls">
                    <span class="input-group-addon"><i class="fa fa-envelope-o"></i></span>
                    <input type="text" class="form-control inputElem" placeholder="请输入登录邮箱"  id="login_userName" style="width: 466px;">
                </div>
                <div class="input-group input-group-lg " style="margin-top:20px">
                    <span class="input-group-addon"><i class="fa fa-unlock-alt" style="width:18px"></i></span>
                    <input type="password" class="form-control" placeholder="请输入登录密码" id="login_passWord" style="height:50px">
                    <span class="input-group-btn tccBut">
                        <button class="btn btn-success" type="button" id="loginBtn">登 录</button>
                    </span>
                </div>
            </div>

            <div class="modal-footer2">
                <div style="float:left">
                    <a href="http://www.jq22.com/pwd.aspx">忘记密码?</a></div>
                <div style="float:right"><a href="http://www.jq22.com/register.aspx">注册新用户</a></div>
                <div style="clear:right"></div>
            </div>

        </div>
    </div>
</div>


<div class="modal fade" id="placeModal" tabindex="-1" role="dialog" aria-labelledby="placeModelLabel" aria-hidden="true">
    <div class="modal-dialog" style="background: white">
        <div class="modal-content2 tcc">
            <div class="modal-header2" style="margin-top:20px">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    ×
                </button>
                <h4 class="modal-title" id="placeModelLabel">

                </h4>
            </div>
            <div class="modal-body tcck">
                <div class="input-group input-group-lg parentCls">
                    <span class="input-group-addon"><i class="fa fa-envelope-o"></i></span>
                    <input type="text" class="form-control inputElem" placeholder="请输入场馆识别码"  id="login_placeUserName" style="width: 466px;">
                </div>
                <div class="input-group input-group-lg " style="margin-top:20px">
                    <span class="input-group-addon"><i class="fa fa-unlock-alt" style="width:18px"></i></span>
                    <input type="password" class="form-control" placeholder="请输入登录密码" id="login_placePassWord" style="height:50px">
                    <span class="input-group-btn tccBut">
                        <button class="btn btn-success" type="button" id="placeLoginBtn">登 录</button>
                    </span>
                </div>
            </div>

            <div class="modal-footer2">
                <div style="float:left">
                    <a href="http://www.jq22.com/pwd.aspx">忘记密码?</a></div>
                <div style="float:right"><a href="http://www.jq22.com/register.aspx">注册新用户</a></div>
                <div style="clear:right"></div>
            </div>

        </div>
    </div>
</div>


<script src="../js/jquery.min.js"></script>
<script src="../js/login.js"></script>
<script src="../js/sweet-alert.js"></script>