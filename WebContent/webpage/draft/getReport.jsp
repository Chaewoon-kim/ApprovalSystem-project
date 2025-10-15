<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../employee/common.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<head>
<link rel="stylesheet" href="webpage/table.css">
</head>
<main class="form-list">
      <h1>결재 신청 목록</h1>
      
      
       <!-- 결재 상태 필터 -->
  <div class="status-filter">
    <label for="statusSelect">결재 상태: </label>
    <select id="statusSelect">
      <option value="">전체</option>
      <option value="진행중">진행중</option>
      <option value="반려">반려</option>
      <option value="완료">완료</option>
    </select>
  </div>
  
      <!-- 목록 테이블 -->
      <table class="form-table">
        <thead>
          <tr>
            <th>마감일</th>
            <th>기안일</th>
            <th>완료일</th>
            <th>제목</th>
            <th>문서번호</th>
            <th>결재상태</th>
          </tr>
        </thead>
        <tbody>
          
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

    </main>
 <script src="webpage/draft/getReport.js"></script>
    
  