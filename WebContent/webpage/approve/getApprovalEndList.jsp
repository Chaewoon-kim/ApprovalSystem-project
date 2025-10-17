<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../employee/common.jsp" %>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title>결재 처리 목록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="webpage/table.css">
<link rel="stylesheet" href="webpage/employee/common.css">
</head>
<body>

    <main class="form-list">
      <h1>결재 처리 목록</h1>
	  
	  <!-- filtering -->
      <form action="controller">
        <input type="hidden" name="cmd" value="getEndList">
        <label for="statusSelect">결재 상태:</label>
        <select id="statusSelect" name="processStatus">
          <option value="all" <c:if test="${processStatus == null}">selected</c:if>>전체</option>
          <option value="완료" <c:if test="${processStatus == '완료'}">selected</c:if>>완료</option>
          <option value="반려" <c:if test="${processStatus == '반려'}">selected</c:if>>반려</option>
        </select>
      </form>

      <!-- table -->
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
        
        <tbody id="endListTable"></tbody>
      </table>
	
      <div class="pagination"></div>
      
    </main>
  
<script type="text/javascript">
$(document).ready(function(){

	let currentStatus = "all";
	let currentPage = 1;

	
	function reqEndList(status, page){
		$.ajax({
			url: "controller",
			data: {
				cmd: "getEndList",
				processStatus: status,
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
		let tbody = $("#endListTable");
		tbody.empty();

		if(!success || !list || list.length == 0){
			tbody.append("<tr><td colspan='6'>결재 처리된 문서가 없습니다.</td></tr>");
			return;
		}

		$.each(list, function(i, item){
			let row = "<tr class='end-row' data-documentno='" + item.documentNo + "'>"
				+ "<td>" + item.draftDate + "</td>"
				+ "<td>" + item.completionDate + "</td>"
				+  "<td>" + item.title + "</td>"
				+ "<td>" + item.department + "</td>"
				+ "<td>" + (item.approvedDocumentNo || '') + "</td>"
				+ "<td><button class='flag " + (item.processStatus == "완료" ? "complete" : "reject") + "'>"
				+ item.processStatus + "</button></td>"
				+ "</tr>";
			tbody.append(row);
		});
	}
	
	function setPagination(current, total){
		let pagination = $(".pagination");
		pagination.empty();

		for(var i = 1; i <= total; i++){
			var activeClass = (i == current) ? "active-page" : "";
			var pageLink = "<a href='#' class='page-link " + activeClass + "' data-page='" + i + "'>" + i + "</a>";
			pagination.append(pageLink);
		}
	}

	// event
	$(document).on("change", "#statusSelect", function(){
		currentStatus = $(this).val();
		currentPage = 1;
		reqEndList(currentStatus, currentPage);
	});

	$(document).on("click", ".page-link", function(e){
		e.preventDefault();
		currentPage = parseInt($(this).data("page"));
		reqEndList(currentStatus, currentPage);
	});

	$(document).on("click", ".end-row", function(e){
		    if ($(e.target).is("button")) return;

		    let documentNo = $(this).data("documentno");
		    location.href = "controller?cmd=getDetailReport&documentNo=" + documentNo;
		  });
	
	reqEndList(currentStatus, currentPage);

});
</script>
</body>
</html>
