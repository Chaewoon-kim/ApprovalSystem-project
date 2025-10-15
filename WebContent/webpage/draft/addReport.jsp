<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../employee/common.jsp"/>
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
			<button class="form-btn" type="submit" form="approveDoc">
				<img src="./img/check-square.png"> <span>결재요청</span>
			</button>
			<button class="form-btn">
				<img src="./img/user-plus.png"> <span>결재선수정</span>
			</button>
			<button class="form-btn">
				<img src="./img/download.png"> <span>임시저장</span>
			</button>
			<button class="form-btn">
				<img src="./img/x-circle.png"> <span>작성취소</span>
			</button>
		</div>
		<hr>
		<form id="approveDoc" action="controller?cmd=addDocAction">
				<input type="hidden" name="formId" value="D2">
				<div class="document-content">
					<label>
						<span>문서 제목</span>
						<input name="title">
					</label>
					<label>
						<span>결재 기한</span>
						<div class="deadline">
							<select class="deadlineYear" name="deadlineYear"></select>
							<select class="deadlineMonth" name="deadlineMonth"></select>
							<select class="deadlineDay" name="deadlineDay"><option>일</option></select>
						</div>
					</label>
				</div>
	
			<hr>
			
			<%@ include file="../employee/documentForm.jsp" %>
			</form>
		<hr>
		
    </main>
    <script src="webpage/draft/addReport.js"></script>