$(document).ready(function () {
    $("#loginBtn").click(function() {
        let userName = $("#login_userName").val();
        let passWord = $("#login_passWord").val();
        login(userName, passWord,
            function (message) {
                console.log(message);
                if(message == "SUCCESS"){
                    swal({
                        title: "登陆成功!",
                        type: "success",
                        confirmButtonText: "进入Tickets",
                        closeOnConfirm: false
                    },
                    function(isConfirm){
                        if(isConfirm){
                            window.location.href = '/userInfo';
                        }
                    })
                }

                if(message == "root"){
                    swal({
                            title: "欢迎Tickets经理!",
                            type: "success",
                            confirmButtonText: "进入Tickets",
                            closeOnConfirm: false
                        },
                        function(isConfirm){
                            if(isConfirm){
                                window.location.href = '/managerCheck';
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
            }
        );

    });

    $("#placeLoginBtn").click(function() {
        let userName = $("#login_placeUserName").val();
        let passWord = $("#login_placePassWord").val();
        console.log(userName);
        console.log(passWord);
        placeLogIn(userName, passWord,
            function (message) {
                console.log(message);
                if(message == "SUCCESS"){
                    swal({
                            title: "登陆成功!",
                            type: "success",
                            confirmButtonText: "进入Tickets",
                            closeOnConfirm: false
                        },
                        function(isConfirm){
                            if(isConfirm){
                                window.location.href = '/placeInfo';
                            }
                        })
                }
                if (message == "NO_USER") {
                    swal({
                        title: "不存在此场馆",
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
            }
        );

    });
});

function login(userName, passWord, callback) {
    $.ajax({
        type: 'POST',
        url: '/loginCheck',
        data: {
            userName: userName,
            passWord: passWord
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

function placeLogIn(userName, passWord, callback) {
    $.ajax({
        type: 'POST',
        url: '/placeLogInCheck',
        data: {
            userName: userName,
            passWord: passWord
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