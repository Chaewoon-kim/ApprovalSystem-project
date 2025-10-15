<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<link href="webpage/employee/common.css" rel="stylesheet">
<link href="webpage/draft/searchFormPopup.css" rel="stylesheet">
<link href="webpage/modal.css" rel="stylesheet">
<link href="webpage/popup.css" rel="stylesheet">
<script src="webpage/modal.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<!-- 헤더 -->
	<nav class="top-nav">
		<div class="logo">로고자리</div>
		<div class="profile">
			<c:if test="${isManager}">
				<div class="change-mode-container">
					<span class="label">관리자모드ㅤ</span> <input type="checkbox"
						id="changeMode"> <label for="changeMode"></label>
				</div>
			</c:if>
			<span class="label">${rank}</span> <span class="label">${name}</span>
			님, 환영합니다. <img class="alarm-img"
				src="img/bell.png">
		</div>
	</nav>

	<div class="container">
		<!-- 사이드바 -->
		<nav class="side-nav">
			<div class="employee-menu">
				<div class="draft">
					<button class="make" onclick="openModal(searchForm)">기안서 작성</button>
				</div>
				<div class="menu">
					<div class="menu-title">
						<img src="img/arrow.png">
						<span>결재신청</span>
					</div>
					<div class="submenu">
						<a href="controller?cmd=getReqListUI">결재 신청 목록</a>
						<a href="controller?cmd=getTempListUI">임시 저장 목록</a>
					</div>
				</div>
				<c:if test="${rank != '사원'}">
					<div class="menu">
						<div class="menu-title">
							<img src="img/arrow.png">
							<span>결재 승인</span>
						</div>
						<div class="submenu">
							<a href="#">결재 대기 목록</a> <a href="#">결재 처리 목록</a> <a href="#">부재/위임
								설정</a>
						</div>
					</div>
				</c:if>
				<div class="menu">
					<div class="menu-title">
						<img src="img/arrow.png">
						<span>알림목록</span>
					</div>
					<div class="submenu">
						<a href="#">수신목록</a>
					</div>
				</div>
			</div>
			<div class="manager-menu">
				<div class="menu">
					<div class="menu-title">
						<img src="img/arrow.png">
						<span>사원 관리</span>
					</div>
					<div class="submenu">
						<a href="#">접근 권한 관리</a>
					</div>
				</div>
				<div class="menu">
					<div class="menu-title">
						<img src="img/arrow.png">
						<span>양식 관리</span>
					</div>
					<div class="submenu">
						<a href="#">양식 설정</a>
						<a href="#">양식 목록</a>
					</div>
				</div>
			</div>
			

			<div class="logout">로그아웃</div>
		</nav>
		<jsp:include page="../draft/searchFormPopup.jsp" />
		<script src="webpage/employee/common.js"></script>
</body>