<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../webpage/employee/common.jsp" %>
<head>
<link href="webpage/table.css" rel="stylesheet">
</head>
 <main class="form-list">
      <h1>결재 신청 목록</h1>
      
      
       <!-- 결재 상태 필터 -->
  <div class="status-filter">
    <label for="statusSelect">결재 상태: </label>
    <select id="statusSelect">
      <option value="all">전체</option>
      <option value="complete">진행중</option>
      <option value="reject">반려</option>
      <option value="reject">완료</option>
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
          <tr>
            <td>2025-09-28</td>
            <td>2025-09-17</td>
            <td>-</td>
            <td><a class="doc-link" href="#">비아이지스틸_소재처리 방안 보고</a> </td>
            <td>- </td>
            <td><button class="flag">진행중</button></td>
          </tr>
          <tr>
            <td>2025-09-28</td>
            <td>2025-09-10</td>
            <td>-</td>
            <td><a href="#">신규 행정사원 채용 면접 실시 계획</a></td>
            <td>-</td>
            <td><button class="flag reject">반려</button></td>
          </tr>
          <tr>
            <td>2025-09-30</td>
            <td>2025-09-14</td>
            <td>2025-09-16</td>
            <td><a href="#">휴가 신청 </a></td>
            <td>D25-1226</td>
            <td><button class="flag complete">완료</button></td>
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

    </main>