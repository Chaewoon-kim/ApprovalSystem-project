<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../employee/common.jsp" %>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="webpage/employee/documentForm.css">
<link rel="stylesheet" href="webpage/report.css">
</head>
   <main class="add-form">
      <h2>
      	기안서 작성
      </h2>
		<div class="btn-container">
			<button class="form-btn" id="submitBtn" type="submit" form="approveDoc">
				<img src="./img/check-square.png"> <span>결재요청</span>
			</button>
			<button class="form-btn" onclick="openModal(updateLine)">
				<img src="./img/user-plus.png"> <span>결재선수정</span>
			</button>
			<button class="form-btn" id="tempSaveBtn" type="submit" form="approveDoc">
				<img src="./img/download.png"> <span>임시저장</span>
			</button>
			<button class="form-btn">
				<img src="./img/x-circle.png"> <span>작성취소</span>
			</button>
		</div>
		<hr>
		<form id="approveDoc" action="controller?cmd=submitDoc" method="post">
				<div class="document-content">
					<label>
						<span>문서 제목</span>
						<input name="title" required>
					</label>
					<label>
						<span>결재 기한</span>
						<div class="deadline">
							<select class="deadlineYear" name="deadlineYear" required></select>
							<select class="deadlineMonth" name="deadlineMonth" required></select>
							<select class="deadlineDay" name="deadlineDay" required></select>
						</div>
					</label>
				</div>
	
			<hr>

			<%@ include file="../employee/documentForm.jsp" %>
			</form>
		<hr>
    </main>
    <jsp:include page="./updateLine.jsp" />
    <script src="webpage/draft/addReport.js"></script>