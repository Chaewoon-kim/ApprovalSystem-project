<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../employee/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addAbsence</title>
<link rel="stylesheet" href="webpage/employee/documentForm.css">
<link rel="stylesheet" href="webpage/employee/common.css">
<link rel="stylesheet" href="webpage/absence/addAbsence.css">
<link rel="stylesheet" href="webpage/popup.css">
<link rel="stylesheet" href="webpage/modal.css">
<link rel="stylesheet" href="webpage/line.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="webpage/modal.js"></script>
<script src="webpage/absence/searchProxy.js"></script>

<c:if test="${not empty message}">
  <script>alert('${message}');</script>
  <c:remove var="message" scope="session"/>
</c:if>

</head>
<body>

   <main class="add-absence">
  <h1>부재/위임 설정</h1>
  <hr class="mt"/>

  <form id="absenceForm" method="post">
    <!-- 수정 모드일 경우 absenceDateNo hidden 추가 -->
    <c:if test="${not empty absence}">
      <input type="hidden" name="absenceDateNo" value="${absence.absenceDateNo}">
    </c:if>

    <!-- 대결자 ID -->
    <input type="hidden" name="proxyId" id="proxyId" value="${absence.proxyId}">

    <!-- 부재 기간 -->
    <label>
      <span>부재 기간</span>
      <input name="startDate" type="date" value="${absence.absenceStartDate}" />
      <div>~</div>
      <input name="endDate" type="date" value="${absence.absenceEndDate}" />
    </label>

    <!-- 부재 사유 -->
    <label>
      <span>부재 사유</span>
      <textarea name="reason">${absence.absenceReason}</textarea>
    </label>

    <!-- 대결자 -->
<div class="proxy-container">
  <label class="proxy-label">대결자</label>

  <!-- 표시 영역 -->
  <div class="proxy">
    <c:choose>
      <c:when test="${empty absence.proxyId}">
        <span style="color:#aaa;">대결자 없음</span>
      </c:when>
    </c:choose>
  </div>

  <input type="hidden" id="proxyId" name="proxyId" value="${absence.proxyId}" />

	  <button class="form-btn" type="button" onclick="openModal(searchProxyModal)">
	    <img src="./img/user-plus.png" alt=""> <span>대결자 선택</span>
	  </button>
	</div>

    <hr class="mb"/>

    <!-- 버튼 -->
    <div class="popup-btns">
      <button class="popup-btn-true" type="submit">
        <c:choose>
          <c:when test="${not empty absence}">수정</c:when>
          <c:otherwise>등록</c:otherwise>
        </c:choose>
      </button>
      <button class="popup-btn-false" type="button" onclick="history.back()">취소</button>
    </div>
  </form>
</main>

    <jsp:include page="searchProxy.jsp" />
    
<script>
$(document).ready(function() {

	$("form").on("submit", function(e) {
		  e.preventDefault();

		  var usage = "${absence.absenceUsage}";
		  
		  // usage가 비어 있지 않고 '대기'가 아닐 경우
		  if (usage && usage.trim() !== "대기") {
		    alert("‘대기’ 상태인 부재 일정만 수정할 수 있습니다.");
		    return;
		  }

		  var isUpdate = $("input[name='absenceDateNo']").length > 0;
		  var cmdValue = isUpdate ? "modifyAbsence" : "addAbsence";

		  var data = {
		    cmd: cmdValue,
		    absenceDateNo: $("input[name='absenceDateNo']").val(),
		    startDate: $("input[name='startDate']").val(),
		    endDate: $("input[name='endDate']").val(),
		    reason: $("textarea[name='reason']").val(),
		    proxyId: $("#proxyId").val()
		  };

		  $.ajax({
		    url: "controller",
		    type: "POST",
		    data: data,
		    dataType: "json",
		    success: function(response) {
		      alert(response.message);
		      if (response.success) {
		        location.href = "controller?cmd=getAbsenceProxyList";
		      }
		    },
		    error: function() {
		      alert(isUpdate ? "부재 수정 중 오류가 발생했습니다." : "부재 등록 중 오류가 발생했습니다.");
		    }
		  });
		});


  
  let proxyId = $("#proxyId").val();
  if (proxyId) {
    $.ajax({
      url: "controller",
      data: { cmd: "getAllEmployees" },
      dataType: "json",
      success: function(result) {
        var emp = null;
        for (var i = 0; i < result.length; i++) {
          if (result[i].employeeId === proxyId) {
            emp = result[i];
            break;
          }
        }
        if (emp) {
          $(".proxy").html(
            '<span class="name">' + emp.name + '</span>' +
            '<span class="rank">' + emp.rank + '</span>'
          );
        }
      },
      error: function() {
        console.log("사원 목록 조회 실패");
      }
    });
  }

});
</script>

</body>

</html>