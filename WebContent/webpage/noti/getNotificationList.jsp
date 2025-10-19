<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../employee/common.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>알림 목록</title>
	<!-- JQuery JS -->
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	
	<link href="webpage/table.css" rel="stylesheet">
	<link href="webpage/report.css" rel="stylesheet" >
	<link href="webpage/employee/integration.css" rel="stylesheet">
</head>
<body>
	<!-- 헤더 -->

 	<!-- 메인 콘텐츠 -->
    <main class="form-list">
		<div class="page-title">알림 수신 목록</div>

		<!-- 결재 상태 필터 -->
		<div class="status-filter">
			<label for="statusSelect">결재 상태: </label> 
			<select id="statusSelect">
				<option value="전체">전체</option>
				<option value="대결">대결</option>
				<option value="댓글">댓글</option>
				<option value="결재결과">결재 결과</option>
				<option value="결재요청">결재 요청</option>
			</select>
		</div>
		
		<div class="btn-container">
			<button id="readAll" class="form-btn">
				<span>읽음 처리</span>
			</button>
		</div>
      
  		<table class="form-table">
        <thead>
          <tr>
          	<th><input type="checkbox" id="checkAll"></th> <!-- 전체 선택 -->
            <th>읽음상태</th>
            <th>알림 날짜</th>
            <th>알림 유헝</th>
            <th>제목</th>
            <th>문서 번호</th>
            <th>결재 상태</th>
          </tr>
        </thead>
        <tbody  id="notiTable"></tbody>
      </table>
  		
      <!-- 페이지네이션 -->
	  <div class="pagination" id="notiPage"></div>
	  
	  <script src="./webpage/noti/getNoti.js"></script>
</body>
</html>