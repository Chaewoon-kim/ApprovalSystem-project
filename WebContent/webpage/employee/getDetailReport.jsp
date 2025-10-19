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
<link rel="stylesheet" href="webpage/approve/approveModal.css">
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
			<input id="docNo" type="hidden" value="${documentDetail.documentNo}">
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
									<td class="input-table">${documentDetail.approvedDocumentNo}</td>
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
										<c:forEach var="approval" items="${approvalLines}">
											<td class="input-table">
												${approval.department} ${approval.rank}</td>
										</c:forEach>
									</tr>
									<tr>
										<c:forEach var="approval" items="${approvalLines}">
											<td class="input-table">${approval.name}</td>
										</c:forEach>
									</tr>
									<tr>
										<c:forEach var="approval" items="${approvalLines}">
											<td class="input-table">${approval.approvalStatus}</td>
										</c:forEach>
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
			
			
			<!-- 댓글 조회 비동기 부분 -->
			<div id="comments-container"></div>
			
			
		
		<!-- 댓글 작성 비동기 부분 -->
		<div class="addComments-container"></div>
		
		<script>
		    const writerId = "${sessionScope.employeeId}";
		    const writerName = "${sessionScope.name}";
		    const writerRank = "${sessionScope.rank}";
		</script>

		
		<script src="webpage/employee/addComment.js"></script>
		<script src="webpage/employee/getComments.js"></script>
				
			
		<jsp:include page="/webpage/approve/approvalModal.jsp" />
		
		</main>
		

<%@ include file="approvalStatusPopup.jsp" %> 

</body>
</html>