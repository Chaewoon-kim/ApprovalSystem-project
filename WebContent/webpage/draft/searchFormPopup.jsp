<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<link href="webpage/draft/searchFormPopup.css" rel="stylesheet">
</head>
<div id="searchForm" class="modal-overlay" style="display:none;">
	<div class="modal-content">
		<div class="vert">
		<div><h2>기안서 작성</h2></div>
		<div>
			<input type="text" class="input">
		</div>
		<div class="selectForm">
			<div class="menu">
				<div class="menu-title">
					<img src="img/arrow.png">
					<span>출장</span>
				</div>
				<div class="submenu">
					<div>출장 신청서</div>
				</div>
			</div>
			<div class="menu">
				<div class="menu-title">
					<img src="img/arrow.png">
					<span>인사</span>
				</div>
				<div class="submenu">
					<div>휴가 신청서</div>
				</div>
			</div>
		</div>
		<div class="popup-btns">
			<button class="popup-btn-true" type="submit">확인</button>
			<button class="popup-btn-false" type="reset" onclick="closeModal(searchForm)">취소</button>
		</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	const getForms = fucntion(data){
		//구현
	}
	$(document).ready(function(){
		$(searchForm).click(
			function(){
				$.ajax({
					url: "controller",
					data: {
						cmd: "getForms"
						keyword: keyword 
					},
					success: getForms
				})
			}
		)
	})
</script>