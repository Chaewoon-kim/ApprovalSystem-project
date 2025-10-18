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
				<span class="reg-date">${comment.commentInDate}</span>
			</div>
			<p class="comment-content">${comment.commentContents}</p>
		</div>
		`;
		commentsHtml += commentBlock;
	});


	commentsContainer.innerHTML = commentsHtml;
}

let loadComments = function() {
	const commentsContainer = document.getElementById("comments-container");
	if (commentsContainer) {
		commentsContainer.innerHTML = '<p class="loading-msg">댓글을 불러오는 중...</p>';
	}

	const documentNo = $("#docNo").val();

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