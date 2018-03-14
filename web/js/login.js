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