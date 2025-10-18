$(document).ready(function() {
	const docNo = $('#docNo').val();
    const container = $('.addComments-container');
    const name = writerName;    // JSP 전역 변수
    const rank = writerRank;    // JSP 전역 변수

    let editMode = false;
    let editCommentNo = null;

    const renderForm = (buttonText = "등록") => {
        const formHtml = `
          <div class="flex-container">
              <div class="comment-container comment-text">
                <input type="hidden" id="writerId" value="${writerId}">
                <div class="name-area">
                   <span>${name}</span> <span class="rank">${rank}</span>
                </div>
                <div class="comment-area">
                   <textarea id="commentContents" class="comment-area"
                      placeholder="댓글을 입력하세요"></textarea>
                </div>
             </div>
             <div class="date-area">
                <button id="submitComment" class="comment-btn">${buttonText}</button>
             </div>
           </div>
        `;
        container.html(formHtml);
    };

    // 최초 렌더링
    renderForm();

    // 댓글 등록 / 수정 버튼 클릭
    container.on('click', '#submitComment', function() {
        const commentContents = $('#commentContents').val().trim();
        const writerId = $('#writerId').val();

        if (commentContents === "") {
            alert("댓글 내용을 입력해주세요.");
            return;
        }

        if (!editMode) {
            // ✅ 일반 등록 모드
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
                    if (response === true || response === "true") {
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
        } else {
            // ✅ 수정 모드
            $.ajax({
                url: 'controller',
                type: 'POST',
                data: {
                    cmd: 'updateComment',
                    commentNumber: editCommentNo,
                    commentContents: commentContents
                },
                dataType: "json",
                success: function(response) {
                    if (response === true || response === "true") {
                        alert("댓글이 수정되었습니다.");
                        editMode = false;
                        editCommentNo = null;
                        renderForm("등록"); // 버튼 다시 등록으로 복구
                        loadComments();
                    } else {
                        alert("댓글 수정에 실패했습니다.");
                    }
                },
                error: function() {
                    alert("서버 오류로 댓글 수정에 실패했습니다.");
                }
            });
        }
    });

    // ✅ 다른 JS에서 “수정 모드로 전환” 시 쓸 함수
    window.enterEditMode = function(commentNo, content) {
        editMode = true;
        editCommentNo = commentNo;
        renderForm("수정 완료"); // 버튼 문구 변경
        $('#commentContents').val(content); // 기존 댓글 내용 표시
    };
});
