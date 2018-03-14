$().ready(function () {
    $("#loginBtn").click(function() {
        let username = $("#username").val();
        let password = $("#passwd").val();
        console.log(username);
        console.log(password);
        login(username, password, function (message) {
            if(message){
                document.cookie="username="+username;
                swal({
                    title: "验证成功!",
                    type: "success",
                    confirmButtonText: "进入首页",
                    closeOnConfirm: false
                },
                    function(isConfirm){
                        if(isConfirm){
                            if (username == 'admin') {
                                window.location.href = '../admin.php';
                            } else {
                                window.location.href = '../home.php';
                            }
                        }
                    })
            } else {
                swal({
                    title: "用户名或密码错误",
                    type: "error",
                    confirmButtonText: "返回"
                })
            }
        });

    });
});

function login(username, password, callback) {
    $.ajax({
        type: 'POST',
        url: '/php/login.php',
        data: {
            username: username,
            password: password
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