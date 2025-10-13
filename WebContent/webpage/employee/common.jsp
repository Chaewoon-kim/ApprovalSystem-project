<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<link href="webpage/employee/common.css" rel="stylesheet">
</head>
<body>
 <!-- 헤더 -->
  <nav class="top-nav">
    <div class="logo">로고자리</div>
    <div class="profile">
      <span class="label">${rank}</span>
      <span class="label">${name}</span>
      님, 환영합니다.
      <img class="profile-img" src="https://cdn-icons-png.flaticon.com/512/6522/6522516.png">
    </div>
  </nav>

  <div class="container">
    <!-- 사이드바 -->
    <nav class="side-nav">
      <div class="draft">
        <div class="make">기안서 작성</div>
      </div>
      <div class="menu">
        <div class="menu-title">▼ 사원 관리</div>
        <div class="submenu">
          <div>접근 권한 관리</div>  
        </div>
      </div>  
      <div class="menu">
        <div class="menu-title">▼ 양식 관리</div>
        <div class="submenu">
          <div>양식 설정</div>
          <div>양식 목록</div>
        </div>
      </div>
       
      <div class="logout">로그아웃</div>
    </nav>
    <script src="webpage/employee/common.js"></script>
</body>