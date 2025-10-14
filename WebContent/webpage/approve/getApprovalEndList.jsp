<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title>결재 처리 목록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="webpage/table.css">
<link rel="stylesheet" href="webpage/employee/common.css">
</head>

<body>
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
      <div class="draft"><div class="make">기안서 작성</div></div>

      <div class="menu">
        <div class="menu-title">▼ 결재 신청</div>
        <div class="submenu">
          <a href="controller?cmd=getApplyList">결재 신청 목록</a>
        </div>
      </div>

      <div class="menu">
        <div class="menu-title">▼ 결재 승인</div>
        <div class="submenu">
          <a href="controller?cmd=getWaitList">결재 대기 목록</a>
          <a href="controller?cmd=getEndList" class="active">결재 처리 목록</a>
        </div>
      </div>

      <div class="logout">로그아웃</div>
    </nav>

    <!-- 메인 콘텐츠 -->
    <main class="form-list">
      <h1>결재 처리 목록</h1>

      <!-- 결재 상태 필터 -->
      <form method="get" action="controller">
        <input type="hidden" name="cmd" value="getEndList">
        <label for="statusSelect">결재 상태:</label>
        <select id="statusSelect" name="processStatus">
          <option value="all" <c:if test="${processStatus == null}">selected</c:if>>전체</option>
          <option value="완료" <c:if test="${processStatus == '완료'}">selected</c:if>>완료</option>
          <option value="반려" <c:if test="${processStatus == '반려'}">selected</c:if>>반려</option>
        </select>
      </form>

      <!-- 목록 테이블 -->
      <table class="form-table">
        <thead>
          <tr>
            <th>기안일</th>
            <th>완료일</th>
            <th>제목</th>
            <th>기안부서</th>
            <th>문서번호</th>
            <th>결재상태</th>
          </tr>
        </thead>
        <tbody id="endListTable">
        </tbody>
      </table>
	
	
      <!-- 페이지네이션 -->
      <div class="pagination">
      </div>
      
    </main>
  </div>
  
  <script type="text/javascript">
  $(function() {
	  var currentStatus = "all";
	  var currentPage = 1;
	  
	  // 왜 안되니 ? 
	  function formatDate(dateString) {
		    if (!dateString) return ""; // null/undefined 방지
		    var date = new Date(dateString);
		    if (isNaN(date)) return dateString; // 파싱 실패 시 원본 그대로
		    var year = date.getFullYear();
		    var month = ('0' + (date.getMonth() + 1)).slice(-2);
		    var day = ('0' + date.getDate()).slice(-2);
		    return year + '-' + month + '-' + day;
		  }
	  
	  function loadEndList(status, page) {
	    $.ajax({
	      url: "controller",
	      data: { cmd: "getEndList", processStatus: status, page: page },
	      dataType: "json",
	      success: function(data) {
	        var tbody = $("#endListTable");
	        tbody.empty();
	        var pagination = $(".pagination");
	        pagination.empty();

	        if (!data.success) {
	          tbody.append('<tr><td colspan="6">결재 처리된 문서가 없습니다.</td></tr>');
	          return;
	        }

	        $.each(data.list, function(index, item) {
	          var draftDate = formatDate(item.draftDate);
	          var completionDate = formatDate(item.completionDate);

	          var row = 
	            '<tr>' +
	              '<td>' + draftDate + '</td>' +
	              '<td>' + completionDate + '</td>' +
	              '<td>' + item.title + '</td>' +
	              '<td>' + item.department + '</td>' +
	              '<td>' + item.documentNo + '</td>' +
	              '<td>' +
	                '<button class="flag ' + (item.processStatus === "완료" ? "complete" : "reject") + '">' +
	                  item.processStatus +
	                '</button>' +
	              '</td>' +
	            '</tr>';
	          tbody.append(row);
	        });

	        // 페이지네이션
	        for (var i = 1; i <= data.totalPages; i++) {
	          var activeClass = (i === data.currentPage) ? "active-page" : "";
	          pagination.append('<a href="#" class="page-link ' + activeClass + '" data-page="' + i + '">' + i + '</a>');
	        }
	      },
	      error: function() {
	        alert("데이터를 불러오는 중 오류 발생!");
	      }
	    });
	  }

	  $("#statusSelect").change(function() {
	    currentStatus = $(this).val();
	    currentPage = 1;
	    loadEndList(currentStatus, currentPage);
	  });

	  $(document).on("click", ".page-link", function(e) {
	    e.preventDefault();
	    currentPage = parseInt($(this).data("page"));
	    loadEndList(currentStatus, currentPage);
	  });

	  // 페이지 최초 로드
	  loadEndList(currentStatus, currentPage);
  });

</script>
  <script src="webpage/employee/common.js"></script>
</body>
</html>
