<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="true" %>   
<head>
	<link href="webpage/draft/searchFormPopup.css" rel="stylesheet">
</head>
<div id="searchForm" class="modal-overlay" style="display:none;">
	<div class="modal-content">
		<div class="vert">
			<div><h2>기안서 작성</h2></div>
			<div class="form-search">
				<input type="text" placeholder="양식제목 검색">
			</div>
			<div class="selectForm">
			</div>
			<div class="popup-btns">
				<button class="popup-btn-true setForm" type="submit">확인</button>
				<button class="popup-btn-false" type="reset" onclick="closeModal(searchForm)">취소</button>
			</div>
		</div>
	</div>
</div>
<script src="webpage/draft/searchFormPopup.js"></script>