<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>접근권한 관리</title>
	<!-- JQuery JS -->
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	
	<link href="webpage/table.css" rel="stylesheet">
	<link href="webpage/employee/common.css" rel="stylesheet">
	<link href="webpage/employee/integration.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/webpage/employee/common.jsp" />
	
	<main class="form-list">
			<div class="page-title">접근 권한 관리</div>
			<!-- 등록상태 필터 -->
			<div class="status-filter">
				<label for="statusSelect">등록 상태: </label> 
				<select id="statusSelect">
					<option value="전체">전체</option>
					<option value="등록">등록</option>
					<option value="미등록">미등록</option>
				</select>
			</div>
			
			<!-- 목록 테이블 -->
			<div class="content-container">
			<table class="form-table">
				<thead>
					<tr>
						<th>사번</th>
						<th>이름</th>
						<th>부서</th>
						<th>직급</th>
						<th>사용자 등록</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			</div>
			
			<!-- 페이지네이션 -->
			<div class="pagination"></div>
		</div>
	</main>
</body>

<script src="webpage/employee/common.js"></script>
<script src="webpage/manager/employeeAccess.js"></script>
<script type="text/javascript">	
		let currentSelect = '';
		let startPage = 1;
		let endPage = 1;
		let totalPage = 1;

		// 최초 실행
		$(document).ready(function(){
			init();
		});
		function init(){
			reqPage(currentSelect, true);
		}
		function getFilter(filter){
			switch(filter){
			case "전체":
				filter = '';
				break;
			case "등록":
				filter = 'Y';
				break;
			case "미등록":
				filter = 'N';
				break;
			}
			return filter;
		}
		// 이벤트 등록
		// 페이지 정보 획득
		$(document).on("change", "#statusSelect", function(e){
			currentSelect = getFilter($(this).val());
			reqPage(currentSelect, true);
		});
		//
		$(document).on("click", ".page-number", function(e){
			if(!$(this).hasClass("active")){
				let pageNo = $(this).data("page");
				if(pageNo == null) pageNo = 1;
				
				reqEmployee(pageNo, currentSelect);
				clickPage($(this));
			}
		});
		
		$(document).on("click", ".access-btn", function(e){
			let empId = $(this).data("name");
			reqInvertAccessPermission($(this), empId);
			
		});
		
		function clickPage(pageElem){
			$(".page-number.active").removeClass("active");
			pageElem.addClass("active");
		}
		
		// 비동기 사용자 수 획득
		function reqPage(filter, loadFirstPage){
			$.ajax({
				url : "controller",
				data : {
					cmd : "getEmployeeCount",
					filter: filter
				},
				success : function(data){
					setPage(data.result);
					if(loadFirstPage){
						let firstPage = $(".page-number").eq(0).data("page");
						if(firstPage != null) reqEmployee(firstPage, filter);
					}
				}
			});
		}

		// UI에 페이지네이션 표시
		function setPage(empCount){
			let pagination = $(".pagination");
			pagination.empty();
			
			totalPage = 1;
			if(empCount > 0)
				totalPage = Math.ceil(empCount / 8);
			
			endPage = totalPage;
			if(totalPage > 10) endPage = 10;
			
			for(let idx = startPage; idx <= endPage; idx++){
				pagination.append(`<div class='page-number' data-page='${idx}'>${idx}</div>`);
			}
			clickPage($(".page-number").eq(0));
		}
		
		// 사용자 정보 획득
		function reqEmployee(page, filter){
			$.ajax({
				url: "controller",
				data: {
					cmd: "getEmployees",
					page: page,
					filter: filter
				},
				success: function(data){
					setTable(data.result);
				},
				error: function(err){
				      console.error("getEmployee error:", err);
			    }
			});
		}
		function setTable(data){
			let tableBody = $(".form-table tbody");
			tableBody.empty();
			let row="";
			$.each(data, function(i, emp){
				if(emp.managerPermission == 'Y') return;
				row += `<tr>
		        <td><div id='empId'>${emp.employeeId}</div></td>
		        <td>${emp.name}</td>
		        <td>${emp.department}</td>
		        <td>${emp.rank}</td>
		        <td><div class="btn ${emp.accessPermission == 'Y' ? 'btn-event' : 'btn-event active'} access-btn" data-name="${emp.employeeId}">${emp.accessPermission == 'Y' ? '해제':'등록'}</div></td>
		    	</tr>`;
			});
			tableBody.append(row);
		}
		
		function reqInvertAccessPermission(empElem, empId){
			$.ajax({
				url: "controller",
				data: {
					cmd: "invertAccessPermission",
					empId: empId
				},
				success: function(data){
					setAccessPermission(data.result);
				}
			});
		}
		function setAccessPermission(result){
			reqEmployee($(".page-number.active").data("page"), currentSelect);
		}
	</script>
</body>
</html>
