<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문서상세</title>
<base href="<%=request.getContextPath()%>/">
<!-- 글깨짐 현상 <base>태그로 해결 -->
<link rel="stylesheet" href="webpage/documentForm.css">
<link rel="stylesheet" href="webpage/employee/common.css">
<link rel="stylesheet" href="webpage/report.css">
</head>
<body>
	<div class="screen">
		<div>
			<nav class="top-nav">
				<div class="logo">로고자리</div>

				<div class="profile">
					<span class="label">${sessionScope.User.department}</span>  <span
						class="label">${sessionScope.User.name}</span>  <span
						class="label">${sessionScope.User.rank}</span> 님, 환영합니다.  <img
						class="profile-img"
						src="https://cdn-icons-png.flaticon.com/512/6522/6522516.png">
				</div>
			</nav>
		</div>
		<div class="container">
			<nav class="side-nav">
				<div class="draft">
					<div class="make">기안서 작성</div>
				</div>
				<div class="menu">
					<div class="menu-title">▼ 결재 신청</div>
					<div class="submenu">
						<div>결재 신청 목록</div>
					</div>

				</div>
				<div class="menu">
					<div class="menu-title">▼ 결재 승인</div>
					<div class="submenu">
						<div>결재 대기 목록</div>
						<div>결재 처리 목록</div>
					</div>
				</div>
				<div class="logout">로그아웃</div>
			</nav>


			<main class="add-form">
			<h1>문서상세</h1>

			<div class="message-area">${message}</div>
			 

			<div class="btn-container">
				<button class="form-btn">
					<img src="img/list.png"> <span>목록보기</span>
				</button>
				<button class="form-btn">
					<img src="img/airplay.png"> <span>결재상태</span>
				</button>
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
									<td class="input-table">${documentDetail.documentNo}</td>
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
		</div>
	</div>

	<script>
    document.querySelectorAll(".menu-title").forEach(title =>{
      title.addEventListener("click", () => {
        const submenu = title.nextElementSibling;
        if(submenu.classList.contains("open")){
          submenu.style.maxHeight = submenu.scrollHeight + "px";
          requestAnimationFrame(() => {
            submenu.style.maxHeight = "0";
          })
          submenu.classList.remove("open");
        } else{
          submenu.style.maxHeight = submenu.scrollHeight + "px";
          submenu.classList.add("open");
        }
      });
    });
  </script>
</body>
</html>