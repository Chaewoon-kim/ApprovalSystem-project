<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../employee/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부재/위임 설정</title>
<link href="webpage/table.css" rel="stylesheet">
<link href="webpage/employee/common.css" rel="stylesheet">
<link rel="stylesheet" href="webpage/report.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<c:if test="${not empty message}">
  <script>alert('${message}');</script>
  <c:remove var="message" scope="session"/>
</c:if>

</head>
<body>



<main class="form-list">
  <h1>부재 일정</h1>

  <div class="btn-container">
    <button class="form-btn" type="button" onclick="location.href='controller?cmd=getAddAbsenceUI'"><img src="./img/list.png">부재추가</button>
    <button class="form-btn" type="button"><img src="./img/x.png"> <span>조기종료</span></button>
    <button class="form-btn"><img src="./img/x.png"> <span>삭제</span></button>
  </div>

  <table class="form-table">
    <thead>
      <tr>
        <th><input type="checkbox" id="checkAll"></th>
        <th>부재 시작</th>
        <th>부재 종료</th>
        <th>대결자</th>
        <th>부재 사유</th>
        <th>상태</th>
      </tr>
    </thead>
    <tbody id="absenceTableBody">
      <!-- 비동기 데이터 렌더링 -->
    </tbody>
  </table>

  <!-- 페이지네이션 -->
  <div class="pagination"></div>

  <h1>대결 일정</h1>
  <table class="form-table">
    <thead>
      <tr>
        <th><input type="checkbox" id="checkAllProxy"></th>
        <th>대결 시작</th>
        <th>대결 종료</th>
        <th>위임자</th>
        <th>부재 사유</th>
      </tr>
    </thead>
    <tbody id="proxyTableBody">
      <!-- 비동기 데이터 렌더링 -->
    </tbody>
  </table>
  <!-- 페이지네이션 -->
  <div class="pagination"></div>
</main>


<script type="text/javascript">
$(document).ready(function(){

  let currentPage = 1;

  function reqAbsenceList(page){
    $.ajax({
      url: "controller",
      data: {
        cmd: "getAbsenceProxyList",
        page: page
      },
      dataType: "json",
      success: function(data){
        renderAbsenceTable(data.absenceList);
        renderProxyTable(data.proxyList);
        setPagination(data.currentPage, data.totalPages);
      },
      error: function(){
        alert("AJAX error!");
      }
    });
  }

  function renderAbsenceTable(list){
    let tbody = $("#absenceTableBody");
    tbody.empty();

    if(!list || list.length == 0){
      tbody.append("<tr><td colspan='6'>부재 일정이 없습니다.</td></tr>");
      return;
    }

    $.each(list, function(i, item){
      let row = "<tr>"
        + "<td><input type='checkbox' class='row-check'></td>"
        + "<td>" + (item.absenceStartDate || '') + "</td>"
        + "<td>" + (item.absenceEndDate || '') + "</td>"
        + "<td>" + (item.proxyName + ' ' + item.proxyRank) + "</td>"
        + "<td>" + (item.absenceReason || '') + "</td>"
        + "<td><button class='flag complete'>" + (item.absenceUsage || '') + "</button></td>"
        + "</tr>";
      tbody.append(row);
    });
  }

  function renderProxyTable(list){
    var tbody = $("#proxyTableBody");
    tbody.empty();

    if(!list || list.length == 0){
      tbody.append("<tr><td colspan='5'>대결 일정이 없습니다.</td></tr>");
      return;
    }

    $.each(list, function(i, item){
      var row = "<tr>"
        + "<td><input type='checkbox' class='row-check-proxy'></td>"
        + "<td>" + (item.absenceStartDate || '') + "</td>"
        + "<td>" + (item.absenceEndDate || '') + "</td>"
        + "<td>" + (item.absenteeName + ' ' + item.absenteeRank) + "</td>"
        + "<td>" + (item.absenceReason || '') + "</td>"
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
  $(document).on("click", ".page-link", function(e){
    e.preventDefault();
    currentPage = parseInt($(this).data("page"));
    reqAbsenceList(currentPage);
  });

  reqAbsenceList(currentPage);

});
</script>

<script src="webpage/employee/common.js"></script>
</body>
</html>
