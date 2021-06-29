$("#btnUp").on("click", function(){
    const id = $("#id").val();
    const data = {
        title: $("#title").val(),
        content: $("#content").val(),
    };

    console.log(id);
    console.log(data);

    $.ajax({
        url:"/api/v1/board/" + id,
        method: "PUT",
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf8",
        success: function(){
            alert("수정 성공");
            location.href="/";
        },
        error: function (){
            alert("업데이트 오류");
        }
    })
})

$("#btnDel").on("click", function(){
    const id = $("#id").val();
    $.ajax({
        url:"/api/v1/board/" + id,
        method: "DELETE",
        success: function(){
            alert("삭제 완료");
            location.href = "/";
        },
        error: function (){
            alert("삭제 에러");
        }
    })
})