// 댓글 목록 표시 및 이벤트 처리
let displayComments = function(result) {
   const commentsContainer = document.getElementById("comments-container");
   let commentsHtml = "";

   if (!commentsContainer) {
      console.error("오류: 'comments-container' 요소를 찾을 수 없습니다.");
      return;
   }

   // 댓글 없을 때
   if (!result || result.length === 0) {
      commentsContainer.innerHTML = '<p class="no-comments-msg">등록된 댓글이 없습니다.</p>';
      return;
   }

//   console.log("댓글 데이터:", result);
   
   const writerId = document.getElementById("writerId")?.value;
   const drafter = writerId; // 로그인한 사용자 (기안자)
   
  
   // 댓글 목록 렌더링
   result.forEach(comment => {
      let commentBlock = `
         <div class="comment-item" data-comment-no="${comment.commentNumber}">
            <div class="flex-container">
               <div class="comment-container comment-text">
                  <div class="name-area">
                     <span>${comment.writerName}</span>
                  </div>
                  <div>
                     <span class="comment-content">${comment.commentContents}</span>
                     ${
                        comment.writerId === drafter
                           ? `
                           <span class="ud-area">
                              <span class="edit-comment-btn" data-no="${comment.commentNumber}">수정</span>
                              · 
                              <span class="delete-comment-btn" data-no="${comment.commentNumber}">삭제</span>
                           </span>`
                           : ""
                     }
                  </div>
               </div>
               <div class="date-area">
                  <span class="date-text">${comment.commentInDate}</span>
               </div>
            </div>
         </div>
      `;
      commentsHtml += commentBlock;
   });

   commentsContainer.innerHTML = commentsHtml;

   // 댓글 삭제 기능
   document.querySelectorAll(".delete-comment-btn").forEach(btn => {
	   btn.addEventListener("click", function() { 
		   const commentNo = this.getAttribute("data-no"); if (!confirm("댓글을 삭제하시겠습니까?")) return; 
		   $.ajax({ 
			   url: "controller", 
			   type: "POST", 
			   data: { 
				   cmd: "deleteComment", 
				   commentNumber: commentNo 
				   }, 
				   success: function(response) { 
					   console.log("삭제 응답:", response); 
					   if (response) { 
						   alert("댓글이 삭제되었습니다."); 
						   loadComments(); 
					   } else { 
						   alert("댓글 삭제에 실패했습니다."); 
						   } 
					   }, 
					   error: function(xhr, status, error) 
					   { console.error("댓글 삭제 AJAX 오류:", status, error); 
					   alert("서버 오류로 댓글 삭제에 실패했습니다."); 
				}
         });
      });
   });

   // 댓글 수정 기능 (입력창 재활용)
   document.querySelectorAll(".edit-comment-btn").forEach(btn => {
      btn.addEventListener("click", function() {
         const commentNo = this.getAttribute("data-no");
         const commentItem = document.querySelector(`.comment-item[data-comment-no="${commentNo}"]`);
         const originalContent = commentItem.querySelector(".comment-content").textContent.trim();

         if (typeof enterEditMode === "function") {
            enterEditMode(commentNo, originalContent);
         } else {
            console.error("enterEditMode 함수가 존재하지 않습니다. (addComment.js 확인)");
         }
      });
   });
};

// 댓글 조회
let loadComments = function() {
   const commentsContainer = document.getElementById("comments-container");
   if (commentsContainer) {
      commentsContainer.innerHTML = '<p class="loading-msg">댓글을 불러오는 중...</p>';
   }

   const documentNo = document.getElementById("docNo").value;

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
};

// 페이지 로드 시 자동 실행
$(document).ready(function() {
   loadComments();
});
