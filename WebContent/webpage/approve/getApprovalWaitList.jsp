<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결재 대기 목록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="webpage/table.css">
<link rel="stylesheet" href="webpage/employee/common.css">
</head>

<jsp:include page="../employee/common.html" />
<body>

 
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
        <tbody id="waitListTable">
         
        </tbody>
      </table>

      <!-- 페이지네이션 -->
      <div class="pagination">
      </div>
    </main>
  </div>
  
  <script>
  $(function() {
    let currentPage = 1;

 
    function loadWaitList(page) {
      $.ajax({
        url: "controller",
        data: { cmd: "getWaitList", page: page },
        dataType: "json",
        success: function(data) {
          let tbody = $("#waitListTable").empty();
          let pagination = $(".pagination").empty();
		  
          if (!data.success) {
	          tbody.append('<tr><td colspan="6">결재 처리된 문서가 없습니다.</td></tr>');
	          return;
	        }
          
          $.each(data.list, function(i, item) {
            let row =
              '<tr>' +
                '<td>' + item.deadline + '</td>' +
                '<td>' + item.draftDate + '</td>' +
                '<td>' + item.name + '</td>' +
                '<td><a href="#">' + item.title + '</a></td>' +
                '<td>' + item.department + '</td>' +
                '<td><button class="flag">' + item.approvalStatus + '</button></td>' +
              '</tr>';
            tbody.append(row);
          });

          for (var i = 1; i <= data.totalPages; i++) {
            var activeClass = (i === data.currentPage) ? "active-page" : "";
            pagination.append('<a href="#" class="page-link ' + activeClass + '" data-page="' + i + '">' + i + '</a>');
          }
        },
        error: function() {
          alert("error!");
        }
      });
    }

    $(document).on("click", ".page-link", function(e) {
      e.preventDefault();
      currentPage = parseInt($(this).data("page"));
      loadWaitList(currentPage);
    });

    loadWaitList(currentPage);
  });


  </script>
    
   <script src="webpage/employee/common.js"></script>

</body>
</body>
</html>