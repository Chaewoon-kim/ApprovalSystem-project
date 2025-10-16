<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>부재/위임 설정</title>
	<!-- JQuery JS -->
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	
	<link href="webpage/table.css" rel="stylesheet">
	<link href="webpage/employee/common.css" rel="stylesheet">
	<link href="webpage/report.css" rel="stylesheet" >
	<link href="webpage/employee/integration.css" rel="stylesheet">
</head>
<body>
	<!-- 헤더 -->
	<jsp:include page="/webpage/employee/common.jsp"/>

 	<!-- 메인 콘텐츠 -->
    <main class="form-list">
		<div class="page-title">알림 수신 목록</div>

		<!-- 결재 상태 필터 -->
		<div class="status-filter">
			<label for="statusSelect">결재 상태: </label> <select id="statusSelect">
				<option value="all">전체</option>
				<option value="complete">완료</option>
				<option value="reject">반려</option>
				<option value="reject">결재요청</option>
			</select>
		</div>
		
		<div class="btn-container">
			<button class="form-btn">
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
        <tbody>
          <tr>
            <td><input type="checkbox" class="row-check"></td>
      		<td>안읽음</td>
      		<td>2025-09-26</td>
      		<td>결재요청</td>
      		<td><a class="doc-link" href="#">비아이지스틸_소재처리 방안 보고</a></td>
      		<td>-</td>
      		<td><button class="flag">결재대기</button></td>
          </tr>
          <tr>
            <td><input type="checkbox" class="row-check"></td>
      		<td>안읽음</td>
      		<td>2025-09-24</td>
      		<td>완료</td>
      		<td><a class="doc-link" href="#">휴가 신청 요청</a></td>
      		<td>D25-1201</td>
      		<td><button class="flag complete">완료</button></td>
          </tr>
          <tr>
            <td><input type="checkbox" class="row-check" disabled></td>
      		<td>읽음</td>
      		<td>2025-09-22</td>
      		<td>반려</td>
      		<td><a class="doc-link" href="#">출장 신청합니다.</a></td>
      		<td>-</td>
      		<td><button class="flag reject">반려</button></td>
          </tr>
          <tr>
            <td><input type="checkbox" class="row-check" disabled></td>
      		<td>읽음</td>
      		<td>2025-09-22</td>
      		<td>반려</td>
      		<td><a class="doc-link" href="#">출장 신청합니다.</a></td>
      		<td>-</td>
      		<td><button class="flag reject">반려</button></td>
          </tr>
          <tr>
            <td><input type="checkbox" class="row-check" disabled></td>
      		<td>읽음</td>
      		<td>2025-08-01</td>
      		<td>댓글</td>
      		<td><a class="doc-link" href="#">심재용 대표이사: 문서가 이게 뭐야! 다시 해와!!!!</a></td>
      		<td>-</td>
      		<td>-</td>
          </tr>
          
        </tbody>
      </table>
  		
      

      <!-- 페이지네이션 -->
<div class="pagination">
  <a href="#prev" class="page-arrow">&lt;</a>
  <a href="#1" class="page-number active">1</a>
  <a href="#2" class="page-number">2</a>
  <a href="#3" class="page-number">3</a>
  <a href="#next" class="page-arrow">&gt;</a>
</div>


   <script src="common.js"></script>   
    
</body>
</html>