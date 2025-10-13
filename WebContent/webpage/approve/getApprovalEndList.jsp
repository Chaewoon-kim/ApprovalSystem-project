<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<meta charset="UTF-8">
<title>결재 처리 목록</title>
	<link href="webpage/table.css" rel="stylesheet">
	<link href="webpage/employee/common.css" rel="stylesheet">



    <!-- 헤더 -->
  <nav class="top-nav">
    <div class="logo">로고자리</div>
    <div class="profile">
      <span class="label">개발팀</span>
      <span class="label">김채운</span>
      <span class="label">부장</span>
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
        <div class="menu-title">▼ 결재 신청</div>
        <div class="submenu">
          <div>결재 신청 목록</div>  
        </div>
      </div>  
      <div class="menu">
        <div class="menu-title">▼ 결재 승인</div>
        <div class="submenu">
          <div>결재 대기 목록</div>
          <div>결재 처리 목록</div>
        </div>
      </div>  
      <div class="logout">로그아웃</div>
    </nav>

 <!-- 메인 콘텐츠 -->
    <main class="form-list">
      <h1>결재 대기 목록</h1>

      <!-- 목록 테이블 -->
      <table class="form-table">
        <thead>
          <tr>
            <th>마감일</th>
            <th>기안일</th>
            <th>기안자</th>
            <th>제목</th>
            <th>기안부서</th>
            <th>결재상태</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>2025-09-22</td>
            <td>2025-09-12</td>
            <td>지연우</td>
            <td><a href="#"> 비아이지스틸_소재처리 방안 보고 </a> </td>
            <td>기획 </td>
            <td><button class="flag">결재대기</button></td>
          </tr>
          <tr>
            <td>2025-09-22</td>
            <td>2025-09-15</td>
            <td>지연우</td>
            <td> <a href="#">휴가 신청</a></td>
            <td>개발1팀</td>
            <td><button class="flag">결재대기</button></td>
          </tr>
          
        </tbody>
      </table>

      <!-- 페이지네이션 -->
      <div class="pagination">
        <span>&lt; 1 &gt;</span>
      </div>
    </main>
  </div>
    
   <script src="common.js"></script>

</body>
</body>