<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../employee/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문서상세</title>
<base href="<%=request.getContextPath()%>/">
<!-- 글깨짐 현상 <base>태그로 해결 -->
<link rel="stylesheet" href="webpage/employee/documentForm.css">
<link rel="stylesheet" href="webpage/modal.css">
<link rel="stylesheet" href="webpage/employee/common.css">
<link rel="stylesheet" href="webpage/report.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="webpage/modal.js"></script>

</head>
<body>
	
			<main class="add-form">
			<h1>문서상세</h1>

			<div class="message-area">${message}</div>
			<div class="btn-container">
				<button class="form-btn" type="button" onclick="history.back()">
					<img src="img/list.png"> <span>목록보기</span>
				</button>
				<c:choose>
			    <c:when test="${approvalStatus eq '결재대기'}">
			        <button class="form-btn" type="button" onclick="openModal(approvalModal)">
			            <img src="img/airplay.png">
			            <span>결재하기</span>
			        </button>
			    </c:when>
			
			    <c:otherwise>
			        <button id="getApprovalStatus" class="form-btn" type="button" onclick="openModal(approvalStatusModal)">
			            <img src="img/airplay.png">
			            <span>결재현황</span>
			        </button>
			    </c:otherwise>
				</c:choose>

			</div>

			<hr>
			<div class="document-content">
				<label> <span>문서제목</span>
					<div>${documentDetail.title}</div>
				</label>
			</div>
			<hr>
			<div class="documentForm">
				<h1 class="text-center">${documentDetail.formName}</h1>
				<div class="flex-container">
					<div class="tables">
						<table class="table-bordered">
							<tbody>
								<tr>
									<th class="table-bgColor draft-th">기안자</th>
									<td class="input-table">${documentDetail.employeeName}</td>
								</tr>
								<tr>
									<th class="table-bgColor draft-th">소속</th>
									<td class="input-table">${documentDetail.employeeDepartment}</td>
								</tr>
								<tr>
									<th class="table-bgColor draft-th">기안일</th>
									<td class="input-table">${documentDetail.draftDate}</td>
								</tr>
								<tr>
									<th class="table-bgColor draft-th">문서번호</th>
									<td class="input-table docNo">${documentDetail.documentNo}</td>
								</tr>
							</tbody>
						</table>
					</div>

					<div class="flex-container">
						<div class="tables">
							<table class="table-bordered">
								<tbody>
									<tr>
										<th rowspan="4" class="table-bgColor rotated-text">신청</th>
									</tr>
									<tr>
										<td class="input-table">${documentDetail.employeeRank}</td>
									</tr>
									<tr>
										<td class="input-table">${documentDetail.employeeName}</td>
									</tr>
									<tr>
										<td class="input-table"></td>
									</tr>
								</tbody>
							</table>
						</div>

						<div class="tables">
							<table class="table-bordered">
								<tbody>
									<tr>
										<th rowspan="4" class="table-bgColor rotated-text">승인</th>
									</tr>
									<tr>
										<td class="input-table">개발팀/대리</td>
										<td class="input-table">개발팀/과장</td>
										<td class="input-table">개발팀/부장</td>
									</tr>
									<tr>
										<td class="input-table">배성윤</td>
										<td class="input-table">성민서</td>
										<td class="input-table">김채운</td>
									</tr>
									<tr>
										<td class="input-table"></td>
										<td class="input-table"></td>
										<td class="input-table"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<hr>

				<div class="inputForm">
					<p>${documentDetail.contents}</p>
				</div>
			</div>
			<hr>
			<div class="document-content">
				<label> <span>결재기한</span>
					<div>${documentDetail.deadline}</div>
				</label>
			</div>
			<hr>
			<div>
				<div class="flex-container">
					<div class="comment-container comment-text">
						<div class="name-area">
							<span>배성윤</span> <span>대리</span>
						</div>
						<div>
							<span>이전 프로젝트 당시 휴가 반납했으니까 보상 휴가 드려야 하지 않을까요?</span>
						</div>
					</div>
					<div class="date-area">
						<span class="date-text">2025-09-25 14:18:25</span>
					</div>
				</div>
				<div class="flex-container">
					<div class="comment-container comment-text">
						<div class="name-area">
							<span>지연우</span> <span>사원</span>
						</div>
						<div>
							<span>헉 감사합니다..ㅠㅠ</span>
						</div>
					</div>
					<div class="date-area">
						<span class="date-text">2025-09-25 14:30:16</span>
					</div>
				</div>
				<div class="flex-container">
					<div class="comment-container comment-text">
						<div class="name-area">
							<span>김채운</span> <span>부장</span>
						</div>
						<div>
							<span>허락할 수 없습니다..!</span>
						</div>
					</div>
					<div class="date-area">
						<span class="date-text">2025-09-25 14:36:42</span>
					</div>
				</div>
				<div class="flex-container">
					<div class="comment-container comment-text">
						<div class="name-area">
							<span>김채운</span> <span>부장</span>
						</div>
						<div class="comment-area">
							<textarea class="comment-area"></textarea>
						</div>
					</div>
					<div class="date-area">
						<button class="comment-btn">등록</button>
					</div>
				</div>
			</div>


			</main>
			<jsp:include page="approvalModal.jsp" />
			<%@ include file="approvalStatusPopup.jsp" %> 
		</div>
	</div>

</body>
</html>