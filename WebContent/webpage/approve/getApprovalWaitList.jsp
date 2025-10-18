<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../employee/common.jsp" %>

<c:if test="${not empty message}">
  <script>alert('${message}');</script>
  <c:remove var="message" scope="session"/>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결재 대기 목록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="webpage/table.css">
</head>

<body>
<main class="form-list">
  <h1>결재 대기 목록</h1>

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

  <div class="pagination"></div>
</main>


<script type="text/javascript">
$(document).ready(function(){

  let currentPage = 1;

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
      error: function(){
        alert("error!");
      }
    });
  }

  function setTable(list, success){
	  let tbody = $("#waitListTable");
	  tbody.empty();

	  if(!success || !list || list.length == 0){
	    tbody.append("<tr><td colspan='6'>결재 대기 문서가 없습니다.</td></tr>");
	    return;
	  }

	  $.each(list, function(i, item){
	    let row = "<tr class='wait-row' data-documentno='" + item.documentNo + "'>"
	      + "<td>" + (item.deadline || '') + "</td>"
	      + "<td>" + (item.draftDate || '') + "</td>"
	      + "<td>" + (item.name || '') + "</td>"
	      + "<td>" + (item.title || '') + "</td>"
	      + "<td>" + (item.department || '') + "</td>"
	      + "<td><button class='flag'>" + item.approvalStatus + "</button></td>"
	      + "</tr>";
	    tbody.append(row);
	  });
	}

  function setPagination(current, total){
    let pagination = $(".pagination");
    pagination.empty();

    for(var i = 1; i <= total; i++){
      var activeClass = (i == current) ? "active" : "";
      var pageLink = "<div class='page-number " + activeClass + "' data-page='" + i + "'>" + i + "</div>";
      pagination.append(pageLink);
    }
  }

  // event
  $(document).on("click", ".page-number", function(e){
    e.preventDefault();
    currentPage = parseInt($(this).data("page"));
    reqWaitList(currentPage);
  });
  
  $(document).on("click", ".wait-row", function(e){
    if ($(e.target).is("button")) return;

    let documentNo = $(this).data("documentno");
    location.href = "controller?cmd=getDetailReport&documentNo=" + documentNo;
  });


  reqWaitList(currentPage);

});
</script>
</body>
</html>
