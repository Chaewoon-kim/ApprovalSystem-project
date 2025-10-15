<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<body>

<jsp:include page="../employee/common.html" />

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
    <tbody id="waitListTable"></tbody>
  </table>

  <!-- 페이지네이션 -->
  <div class="pagination"></div>
</main>

<script type="text/javascript">
$(document).ready(function(){

  var currentPage = 1;

  function reqWaitList(page){
    $.ajax({
      url: "controller",
      data: {
        cmd: "getWaitList",
        page: page
      },
      dataType: "json",
      success: function(data){
        setTable(data.list, data.success);
        setPagination(data.currentPage, data.totalPages);
      },
      error: function(xhr, status, err){
        console.log("AJAX Error:", status, err);
        console.log("응답 내용:", xhr.responseText);
        alert("error!");
      }
    });
  }

  function setTable(list, success){
    var tbody = $("#waitListTable");
    tbody.empty();

    if(!success || !list || list.length == 0){
      tbody.append("<tr><td colspan='6'>결재 대기 문서가 없습니다.</td></tr>");
      return;
    }

    $.each(list, function(i, item){
      var row = "<tr>"
        + "<td>" + (item.deadline || '') + "</td>"
        + "<td>" + (item.draftDate || '') + "</td>"
        + "<td>" + (item.name || '') + "</td>"
        + "<td><a href='#'>" + item.title + "</a></td>"
        + "<td>" + (item.department || '') + "</td>"
        + "<td><button class='flag'>" + item.approvalStatus + "</button></td>"
        + "</tr>";
      tbody.append(row);
    });
  }

  //  페이지네이션 생성
  function setPagination(current, total){
    var pagination = $(".pagination");
    pagination.empty();

    for(var i = 1; i <= total; i++){
      var activeClass = (i == current) ? "active-page" : "";
      var pageLink = "<a href='#' class='page-link " + activeClass + "' data-page='" + i + "'>" + i + "</a>";
      pagination.append(pageLink);
    }
  }

  // 이벤트 등록
  $(document).on("click", ".page-link", function(e){
    e.preventDefault();
    currentPage = parseInt($(this).data("page"));
    reqWaitList(currentPage);
  });

  reqWaitList(currentPage);

});
</script>

<script src="webpage/employee/common.js"></script>
</body>
</html>
