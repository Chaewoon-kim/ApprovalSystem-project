$(document).ready(function() {
    const docNo = $('#docNo').val();
    const container = $('.addComments-container');

    const formHtml = `
        <div class="add-comment-form">
            <textarea id="commentContents" placeholder="댓글을 입력하세요"></textarea>
            <input type="hidden" id="writerId" value="${writerId}"> 
            <button id="submitComment" type="button">등록</button>
        </div>
    `;
    container.html(formHtml);

    $('#submitComment').on('click', function() {
        const commentContents = $('#commentContents').val().trim();
        const writerId = $('#writerId').val();

        if (commentContents === "") {
            alert("댓글 내용을 입력해주세요.");
            return;
        }

        $.ajax({
            url: 'controller',
            type: 'POST',
            data: {
                cmd: 'addComment',
                documentNo: docNo,
                writerId: writerId,
                commentContents: commentContents
            },
            dataType: "json",
            success: function(response) {
                if (response === true) {
                    alert("댓글이 등록되었습니다.");
                    $('#commentContents').val("");
                    loadComments();
                } else {
                    alert("댓글 등록에 실패했습니다.");
                }
            },
            error: function() {
                alert("서버와 통신 중 오류가 발생했습니다.");
            }
        });
    });
});
