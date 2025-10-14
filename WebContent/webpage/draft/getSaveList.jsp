<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../employee/common.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<head>
<link rel="stylesheet" href="webpage/table.css">
</head>
<main class="form-list">
      <h1>임시 저장 목록</h1>
      
      <!-- 목록 테이블 -->
      <table class="form-table">
        <thead>
          <tr>
            <th>임시저장날짜</th>
            <th>결재양식</th>
            <th>제목</th>
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
  <a href="#next" class="page-arrow">&gt;</a>
</div>

</main>
<script src="webpage/draft/getSaveList.js"></script>