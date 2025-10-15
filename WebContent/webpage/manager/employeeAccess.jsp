<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>전자결재 시스템 - 양식 목록</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

  	<link href="webpage/table.css" rel="stylesheet">
  	<link href="webpage/employee/common.css" rel="stylesheet">
</head>
	<jsp:include page="../employee/common.html"/>

    <!-- 메인 콘텐츠 -->
    <main class="form-list">
      <h1>접근 권한 관리</h1>
      
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
      <table class="form-table">
        <thead>
          <tr>
            <th>사번</th>
            <th>이름</th>
            <th>부서</th>
            <th>직급</th>
            <th>권한 수정</th>
          </tr>
        </thead>
        <tbody>
        </tbody>
      </table>
		
		<!-- 페이지네이션 -->
		<div class="pagination">
		</div>
</main>
  </div>

	<script src="webpage/employee/common.js"></script>
    <script src="webpage/manager/employeeAccess.js" ></script>
	<script type="text/javascript">		

		// 최초 실행
		$(document).ready(function(){
			reqPage("");
			reqEmployee("1", "");
		});
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
			let filter = $(this).val();
			reqPage(getFilter(filter));
		});
		//
		$(document).on("click", ".page-number", function(e){
			if(!$(this).hasClass("active")){
				let pageNo = $(this).data("page");
				if(pageNo == null) pageNo = 1;
				let filter = $("#statusSelect").val();
				
				reqEmployee(pageNo, getFilter(filter));
				clickPage($(this));
			}
		});
		
		$(document).on("click", ".access-btn", function(e){
			let empId = $(this).data("name");
			reqInvertAccessPermission($(this), empId);
			
		});
		
		function clickPage(pageElem){
			$(".page-number").removeClass("active");
			pageElem.addClass("active");
		}
		
		// 비동기 사용자 수 획득
		function reqPage(filter){
			$.ajax({
				url : "controller",
				data : {
					cmd : "getEmployeeCount",
					filter: filter
				},
				success : function(data){
					setPage(data.empCount);
					reqEmployee("1", filter);
				}
			});
		}

		// UI에 페이지네이션 표시
		function setPage(empCount){
			let pagination = $(".pagination");
			pagination.empty();
			
			let pageNo = (empCount/8) + (empCount%8==0?0:1);
			for(var idx = 1; idx <= pageNo; idx++){
				var row = "<div class='page-number' data-page='"+idx+"'>"+idx+"</div>";
				pagination.append(row);
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
			
			$.each(data, function(i, emp){
				let row = "<tr>" +
		        "<td><div id='empId'>" + emp.employeeId + "</div></td>" +
		        "<td>" + emp.name + "</td>" +
		        "<td>" + emp.department + "</td>" +
		        "<td>" + emp.rank + "</td>" +
		        "<td><div class='btn btn-use access-btn' data-name='"+emp.employeeId+"'>"+(emp.accessPermission == "Y" ? "권한해제":"권한부여 ")+"</button></td>" +
		    	"</tr>";
		    	tableBody.append(row);
			});
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
			reqEmployee($(".page-number.active").data("page"), getFilter($("#statusSelect").val()));
		}
	</script>
</body>
</html>
