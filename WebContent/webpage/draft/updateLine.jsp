<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<head>
<link rel="stylesheet" href="webpage/line.css">
<link rel="stylesheet" href="webpage/popup.css">
</head>
<body>
	<div id="updateLine" class="modal-overlay" style="display:none;">
		<div class="modal-content">
			<h2>결재선 수정</h2>
		<form action="#" method="post">
			<div class="flex">

				<div class="org-tree">
					<div class="org-header">조직도</div>
					<div class="org-search">
						<input type="text" placeholder="이름, 부서, 직책 입력"
							class="search-input">
					</div>
					
					<div id="search-results"></div>

					<div class="accordion-container"></div>
				</div>

				<div class="approval-line">
					<table id="approvalTable">
						<thead>
							<tr>
								<th>이름</th>
								<th>부서</th>
								<th>직급</th>
								<th style="width: 50px;"></th> 
							</tr>
						</thead>
						<tbody>
							<tr class="section-header-row">
								<td colspan="4" class="section-header">기안자</td>
							</tr>
							<tr class="applicant-row">
								<td>지연우</td>
								<td>개발팀</td>
								<td>사원</td>
								<td></td> 
							</tr>
							<tr class="section-header-row">
								<td colspan="4" class="section-header">결재자</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<div class="popup-btns">
				<button class="popup-btn-true" id="editApprover">확인</button>
				<button type="button" class="popup-btn-false" onclick="closeModal(updateLine)")>취소</button>
			</div>
		</form>
		</div>
	</div>
	
	<script src="webpage/draft/updateLine.js"></script>
</body>