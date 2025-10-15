<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>전자결재 시스템 - 양식 목록</title>
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	
	<link href="webpage/table.css" rel="stylesheet">
	<link href="webpage/employee/common.css" rel="stylesheet">
	<link href="webpage/employee/integration.css" rel="stylesheet">
  	
</head>
<body>

	<jsp:include page="../employee/common.html" />

    <!-- 메인 콘텐츠 -->
    <main class="form-list">
      <h1>양식 목록</h1>

      <!-- 검색 -->
      <div class="search-box">
        <input id="keyword" type="text" placeholder="카테고리, 양식제목 검색">
        <div id="keyword-btn">검색</div>
      </div>

      <!-- 목록 테이블 -->
	<div class="content-container">
      <table class="form-table">
        <thead>
          <tr>
            <th>양식 ID</th>
            <th>카테고리</th>
            <th>양식 제목</th>
            <th>설명</th>
            <th>상태</th>
          </tr>
        </thead>
        <tbody>
        </tbody>
      </table>
      </div>

      <!-- 페이지네이션 -->
      <div class="pagination">
      </div>
    </main>
  </div>

<script src="webpage/employee/common.js"></script>
<script src="webpage/manager/employeeAccess.js"></script>
<script type="text/javascript">
		// 최초 실행
		$(document).ready(function(){
			reqPage("");
			reqForm("1", "");
		});
		// 페이지 정보 획득
		$(document).on("click", "#keyword-btn", function(e){
			let keyword = $("#keyword").val();
			console.log(keyword);
			reqPage(keyword);
		});		
		// 비동기 양식 개수 획득
		function reqPage(keyword){
			$.ajax({
				url : "controller",
				data : {
					cmd : "getFormCount",
					keyword: keyword
				},
				success : function(data){
					setPage(data.result);
					reqForm("1", keyword);
				}
			});
		}
		// UI에 페이지네이션 표시
		function setPage(formCount){
			let pagination = $(".pagination");
			pagination.empty();
			
			let pageNo = (formCount/8) + (formCount%8==0?0:1);
			for(var idx = 1; idx <= pageNo; idx++){
				var row = "<div class='page-number' data-page='"+idx+"'>"+idx+"</div>";
				pagination.append(row);
			}
			clickPage($(".page-number").eq(0));
		}
		function clickPage(pageElem){
			$(".page-number").removeClass("active");
			pageElem.addClass("active");
		}
		
		$(document).on("click", ".page-number", function(e){
			if(!$(this).hasClass("active")){
				let pageNo = $(this).data("page");
				if(pageNo == null) pageNo = 1;
				let keyword = $("#keyword").val();
				
				reqForm(pageNo, keyword);
				clickPage($(this));
			}
		});
		
		function reqForm(page, keyword){
			$.ajax({
				url: "controller",
				data: {
					cmd: "getForm",
					page: page,
					keyword: keyword
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
			
			$.each(data, function(i, form){		    	
		    	let row = 
		    	`<tr>
		            <td>\${form.formId}</td>
		            <td>\${form.formCategory}</td>
		            <td>\${form.formName}</td>
		            <td>\${form.formDescription}</td>
		            <td><button class="btn btn-use">사용</button></td>
	          	</tr>`;
		    	tableBody.append(row);
			});
		}

		function reqInvertFormUsage(formElem, formId){
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
