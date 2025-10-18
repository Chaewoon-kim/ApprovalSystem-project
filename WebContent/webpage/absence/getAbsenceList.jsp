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

</head>
<body>

<main class="form-list">
  <h1>부재 일정</h1>

  <div class="btn-container">
    <button class="form-btn" type="button" onclick="location.href='controller?cmd=getAddAbsenceUI'"><img src="./img/list.png">부재추가</button>
    <button class="form-btn" type="button" id="endAbsenceBtn"><img src="./img/x.png"> <span>조기종료</span></button>
    <button class="form-btn" type="button" id="deleteAbsenceBtn"><img src="./img/x.png"> <span>삭제</span></button>
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
    <tbody id="absenceTableBody"></tbody>
  </table>
  <div class="pagination absencePagination"></div>

  <h1>대결 일정</h1>
  <table class="form-table">
    <thead>
      <tr>
        <th>대결 시작</th>
        <th>대결 종료</th>
        <th>위임자</th>
        <th>부재 사유</th>
      </tr>
    </thead>
    <tbody id="proxyTableBody"></tbody>
  </table>
  <div class="pagination proxyPagination"></div>
</main>


<script type="text/javascript">
$(document).ready(function(){

  let absencePage = 1;
  let proxyPage = 1;

  function reqAbsenceList(aPage, pPage){
    $.ajax({
      url: "controller",
      data: {
        cmd: "getAbsenceProxyList",
        absencePage: aPage,
        proxyPage: pPage
      },
      dataType: "json",
      success: function(data){
        renderAbsenceTable(data.absenceList);
        setAbsencePagination(data.absenceCurrentPage, data.absenceTotalPages);

        renderProxyTable(data.proxyList);
        setProxyPagination(data.proxyCurrentPage, data.proxyTotalPages);
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
	    let disabled = "";
	    if (item.absenceUsage === "종료") { disabled = "disabled"; }

	    let row = "<tr class='absence-row' data-absencedateno='" + item.absenceDateNo + "' data-absenceusage='" + item.absenceUsage + "'>"
	      + "<td><input type='checkbox' class='row-check' data-absencedateno='" + item.absenceDateNo + "' data-absenceusage='" + item.absenceUsage + "' " + disabled + "></td>"
	      + "<td>" + (item.absenceStartDate || '') + "</td>"
	      + "<td>" + (item.absenceEndDate || '') + "</td>"
	      + "<td>" + ((item.proxyName || '') + ' ' + (item.proxyRank || '')) + "</td>"
	      + "<td>" + (item.absenceReason || '') + "</td>"
	      + "<td><button class='" 
	      + (item.absenceUsage === "위임" ? "flag" : "flag complete") + "'>" 
	      + (item.absenceUsage || '') 
	      + "</button></td></tr>";
	    tbody.append(row);
	  });
	}

	$(document).on("click", ".absence-row", function(e) {
	  if ($(e.target).is("input[type='checkbox']")) return;

	  let usage = $(this).data("absenceusage");
	  if (usage !== "대기") {
	    alert("‘대기’ 상태인 일정만 수정할 수 있습니다.");
	    return;
	  }

	  let absenceDateNo = $(this).data("absencedateno");
	  location.href = "controller?cmd=getModifyUI&absenceDateNo=" + absenceDateNo;
	});


  function renderProxyTable(list){
    let tbody = $("#proxyTableBody");
    tbody.empty();

    if(!list || list.length == 0){
      tbody.append("<tr><td colspan='5'>대결 일정이 없습니다.</td></tr>");
      return;
    }

    $.each(list, function(i, item){
      let row = "<tr>"
        + "<td>" + (item.absenceStartDate || '') + "</td>"
        + "<td>" + (item.absenceEndDate || '') + "</td>"
        + "<td>" + (item.absenteeName + ' ' + item.absenteeRank) + "</td>"
        + "<td>" + (item.absenceReason || '') + "</td></tr>";
      tbody.append(row);
    });
  }

  function setAbsencePagination(current, total){
    let pagination = $(".absencePagination");
    pagination.empty();

    for (var i = 1; i <= total; i++) {
      let activeClass = (i == current) ? "active-page" : "";
      pagination.append("<a href='#' class='page-link absence-page " + activeClass + "' data-page='" + i + "'>" + i + "</a>");
    }
  }

  function setProxyPagination(current, total){
    let pagination = $(".proxyPagination");
    pagination.empty();

    for (var i = 1; i <= total; i++) {
      let activeClass = (i == current) ? "active-page" : "";
      pagination.append("<a href='#' class='page-link proxy-page " + activeClass + "' data-page='" + i + "'>" + i + "</a>");
    }
  }

  $(document).on("click", ".absence-page", function(e){
    e.preventDefault();
    absencePage = parseInt($(this).data("page"));
    reqAbsenceList(absencePage, proxyPage); 
  });

  $(document).on("click", ".proxy-page", function(e){
    e.preventDefault();
    proxyPage = parseInt($(this).data("page"));
    reqAbsenceList(absencePage, proxyPage);
  });

  $(document).on('change', '#checkAll', function() {
    let isChecked = $(this).is(':checked');
    $('.row-check').prop('checked', isChecked);
  });

  $(document).on('change', '.row-check', function() {
    let allChecked = $('.row-check').length === $('.row-check:checked').length;
    $('#checkAll').prop('checked', allChecked);
  });

  // endAbsence
  $(document).on("click", "#endAbsenceBtn", function() {
    let checkedRows = $(".row-check:checked");
    if (checkedRows.length === 0) {
      alert("조기종료할 일정을 선택하세요.");
      return;
    }

    let hasWaiting = false;
    let endTargets = [];

    checkedRows.each(function() {
      let usage = $(this).data("absenceusage");
      let no = $(this).data("absencedateno");

      if (usage === "대기") {
        hasWaiting = true;
      } else if (usage === "위임") {
        endTargets.push(no);
      }
    });

    if (hasWaiting) {
      alert("대기중 일정은 조기종료할 수 없습니다. (삭제만 가능)");
      return;
    }

    if (endTargets.length === 0) {
      alert("위임중인 일정만 조기종료할 수 있습니다.");
      return;
    }

    $.ajax({
      url: "controller",
      type: "POST",
      data: {
        cmd: "endAbsence",
        absenceDateNo: endTargets[0]
      },
      dataType: "json",
      success: function(response) {
        alert(response.message);
        if (response.success) {
          reqAbsenceList(absencePage, proxyPage); 
        }
      },
      error: function() {
        alert("조기종료 처리 중 오류가 발생했습니다.");
      }
    });
  });

//deleteAbsence
$(document).on("click", "#deleteAbsenceBtn", function() {
  let checkedRows = $(".row-check:checked");
  if (checkedRows.length === 0) {
    alert("삭제할 부재 일정을 선택하세요.");
    return;
  }

  let deleteTargets = [];
  checkedRows.each(function() {
    if ($(this).data("absenceusage") === "대기") {
      deleteTargets.push($(this).data("absencedateno"));
    }
  });

  if (deleteTargets.length === 0) {
    alert("대기중인 일정만 삭제할 수 있습니다.");
    return;
  }

  if (!confirm(deleteTargets.length + "건의 부재 일정을 삭제하시겠습니까?")) return;

  
  $.ajax({
    url: "controller",
    type: "POST",
    traditional: true, // 배열 전송시 필요,,
    data: {
      cmd: "deleteAbsence",
      absenceDateNos: deleteTargets
    },
    dataType: "json",
    success: function(response) {
      alert(response.message);
      if (response.success) {
        reqAbsenceList(absencePage, proxyPage);
      }
    },
    error: function() {
      alert("삭제 중 오류가 발생했습니다.");
    }
  });
});
  reqAbsenceList(absencePage, proxyPage);
});


</script>

</body>
</html>
