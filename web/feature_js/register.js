$().ready(function () {
    $("#registerBtn").click(function() {
        let username = $("#register_username").val();
        let passwd = $("#register_passwd").val();
        let passwd2 = $("#register_passwd2").val();

        var username_length = username.length;
        var passwd_length = passwd.length;

        if (passwd != passwd2) {
            swal({
                title: "两次密码输入不一致",
                type: "error",
                confirmButtonText: "返回"
            })
        } else if (username_length < 6) {
            swal({
                title: "用户名长度不足六位",
                type: "error",
                confirmButtonText: "返回"
            })
        } else if (passwd_length < 6) {
            swal({
                title: "密码长度不足六位",
                type: "error",
                confirmButtonText: "返回"
            })
        } else {
            console.log(username);
            console.log(passwd);
            register(username, passwd, function (message) {

                console.log(message);
                if(message == "success"){
                    document.cookie="username="+username;
                    swal({
                            title: "注册成功!",
                            type: "success",
                            confirmButtonText: "进入首页",
                            closeOnConfirm: false
                        },
                        function(isConfirm){
                            if(isConfirm){
                                window.location.href = '../home.php';
                            }
                        })
                } else if (message == "same") {
                    swal({
                        title: "用户名已存在",
                        type: "error",
                        confirmButtonText: "返回"
                    })
                } else if (message == "fail") {
                    swal({
                        title: "注册失败",
                        type: "error",
                        confirmButtonText: "返回"
                    })
                }
            });
        }


    });
});

function register(username, passwd, callback) {
    $.ajax({
        type: 'POST',
        url: '/php/register.php',
        data: {
            username: username,
            passwd: passwd
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