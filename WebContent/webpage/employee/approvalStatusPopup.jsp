<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="webpage/employee/approvalStatusPopup.css">
</head>
<div id="approvalStatusModal" class="modal-overlay" style="display:none;">
	<div class="modal-content">
		<div class="approval-status-container">
			<h2>결재 현황</h2>
			<hr/>
			<div class="approval-status-flow">
				<table class="drafter-table">
					<tr><td>기안</td></tr>
					<tr><td>${documentDetail.employeeName}</td></tr>
					<tr><td>${documentDetail.draftDate}</td></tr>
				</table>
				<img src="./img/chevron-right.png">
				<table class="approvers-table"></table>
			</div>
		
			<h3>결재 의견</h3>
			<hr/>
			<div class="approval-opinions-section"></div>
			<hr/>
			<div class="popup-btns">
				<button class="popup-btn-false" onclick="closeModal(approvalStatusModal)">닫기</button>
			</div>
		</div>
	</div>
</div>
<script src="webpage/employee/approvalStatusPopup.js"></script>