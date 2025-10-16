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
      <h1>
      	부재/위임 설정
      </h1>
		<hr class="mt"/>
		<form action="controller?cmd=addAbsence" method="post">
		  <input type="hidden" name="proxyId" id="proxyId">
		
		  <label>
		    <span>부재 기간</span>
		    <input name="startDate" type="date" />
		    <div>~</div>
		    <input name="endDate" type="date" />
		  </label>
		
		  <label>
		    <span>부재 사유</span>
		    <textarea name="reason"></textarea>
		  </label>
		
		  <div class="proxy-container">
		    <label class="proxy-label">대결자</label>
		
		    <div class="proxy">
		      <span style="color:#aaa;">대결자 없음</span>
		    </div>
		
		    <button class="form-btn" type="button" onclick="openModal(searchProxyModal)">
		      <img src="./img/user-plus.png" alt=""> <span>대결자 선택</span>
		    </button>
		  </div>
		
		  <hr class="mb"/>
		  <div class="popup-btns">
		    <button class="popup-btn-true" type="submit">확인</button>
		    <button class="popup-btn-false" type="reset" onclick="history.back()">취소</button>
		  </div>
		</form>

    </main>
    <jsp:include page="searchProxy.jsp" />

</body>

</html>