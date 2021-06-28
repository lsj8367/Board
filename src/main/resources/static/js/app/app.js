//잘되는것 확인
// alert($("#title").val());

$("#btnSave").on("click", function(){
    const data = {
        title : $("#title").val(),
        content : $("#content").val(),
        author : $("#author").val(),
    };


    $.ajax({
        url: '/api/v1/board/save',
        method: "POST",
        contentType: 'application/json; charset=UTF-8',
        data: JSON.stringify(data),
        dataType: "JSON",
        success: function(data){
            alert("데이터 저장")
            location.href = "/";
        },
        error: function(){
            alert("에러");
        }
    });
});