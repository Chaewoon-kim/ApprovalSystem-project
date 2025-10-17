let displayComments = function(result) {
    const commentsContainer = document.getElementById("comments-container"); 
    let commentsHtml = '';
    
    if (!commentsContainer) {
        console.error("오류: 'comments-container' 요소를 찾을 수 없습니다.");
        return;
    }

    if (!result || result.length === 0) {
        commentsContainer.innerHTML = '<p class="no-comments-msg">등록된 댓글이 없습니다.</p>';
        return;
    }

    
    result.forEach(comment => {
        let commentBlock = `
        <div class="comment-item">
            <div class="comment-header">
                <span class="employee-name">${comment.writerName}</span>
                <span class="job-title">(${comment.jobTitle ? comment.jobTitle : '직급 정보 없음'})</span>
                <span class="reg-date">${comment.commentInDate}</span>
            </div>
            <p class="comment-content">${comment.commentContents}</p>
        </div>
        `;
        commentsHtml += commentBlock;
    });

    // 최종 HTML 삽입
    commentsContainer.innerHTML = commentsHtml;
}

/**
 * 댓글 데이터를 서버에서 AJAX로 불러오는 함수.
 * JSP EL을 이용해 문서 번호를 파라미터로 전달합니다.
 */
let loadComments = function() {
    const commentsContainer = document.getElementById("comments-container");
    if (commentsContainer) {
        commentsContainer.innerHTML = '<p class="loading-msg">댓글을 불러오는 중...</p>';
    }

    const documentNo = '${documentDetail.documentNo}'; 
    
    if (!documentNo) {
        console.error("문서 번호(documentNo)가 정의되지 않았습니다.");
        return;
    }
    
    $.ajax({
        url: "controller", 
        data: {
            cmd: "getComments",
            documentNo: documentNo 
        },
        type: "GET", 
        dataType: "json",
        success: displayComments, 

        
        error: function(xhr, status, error) {
            console.error("댓글 로드 AJAX 오류:", status, error);
        }
    });
}


$(document).ready(function(){
    loadComments();
});
