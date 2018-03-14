/**
 * Created by apple on 2017/12/5.
 */
$().ready(function () {
    $("#add").click(function () {
        console.log("hahahahah");
        let album_name = $("#photographName").val();
        let album_description = $("#description").val();

        var is1= document.getElementsByName("checkbox风光")[0].checked;
        var is2= document.getElementsByName("checkbox人物")[0].checked;
        var is3= document.getElementsByName("checkbox宠物")[0].checked;
        var is4= document.getElementsByName("checkbox建筑")[0].checked;
        var is5= document.getElementsByName("checkbox旅行")[0].checked;
        var is6= document.getElementsByName("checkbox纪实")[0].checked;
        var is7= document.getElementsByName("checkbox黑白")[0].checked;
        var is8= document.getElementsByName("checkbox胶片")[0].checked;
        var is9= document.getElementsByName("checkbox手机")[0].checked;

        var str_type = "";

        if (is1) str_type = str_type + "风光 ";
        if (is2) str_type = str_type + "人物 ";
        if (is3) str_type = str_type + "宠物 ";
        if (is4) str_type = str_type + "建筑 ";
        if (is5) str_type = str_type + "旅行 ";
        if (is6) str_type = str_type + "纪实 ";
        if (is7) str_type = str_type + "黑白 ";
        if (is8) str_type = str_type + "胶片 ";
        if (is9) str_type = str_type + "手机 ";

        console.log(str_type);

        createAlbum(album_name, album_description, str_type, function (message) {
            if(message){
                swal({
                        title: "添加成功!",
                        type: "success",
                        confirmButtonText: "刷新页面",
                        closeOnConfirm: false
                    },
                    function(isConfirm){
                        if(isConfirm){
                            window.location.href = '../person.php';
                        }
                    })
            } else {
                swal({
                    title: "添加失败",
                    type: "error",
                    confirmButtonText: "返回"
                })
            }
        });
    });
});


function createAlbum(name, description, str_type, callback) {
    $.ajax({
        type: 'POST',
        url: '/php/createalbum.php',
        data: {
            name: name,
            description: description,
            str_type: str_type
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


 function showDetails(albumID) {
    console.log("details test");
     $("#details").html("");
     $.ajax({
         type: 'POST',
         url: 'php/photodetails.php',
         data: {
             albumID: albumID,
         },
         success: function (photoDetails) {
             var  myjson =JSON.parse(photoDetails);
             for(var item in myjson) {
                 $("#details").append(
                     '<div class="col-sm-4"  style="height: 200px">' +
                     '<img src="pic/' + myjson[item] + '" >' +
                     '</div>'
                 );
             }
         },
         error: function (xhr, status, error) {
             console.log(xhr.status);
             console.log(status);
         }
     })

 }

