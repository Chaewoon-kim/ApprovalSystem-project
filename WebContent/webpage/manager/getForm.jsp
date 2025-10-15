<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="true"%>

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
        <div class="btn-keyword">검색</div>
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
	let currentKeyword = "";
	let startPage = 1;
	let endPage = 1;
	let totalPage = 1;
	
	// 최초 실행
	$(document).ready(function(){
		init();
	});
	
	function ajaxRequest(params, success){
		$.ajax({
			url: "controller",
			data: params,
			success: success,
			error: function(err){console.error(err); alert("서버 요청 실패");}
		});
	}
	
	function init(){
		// 첫번째 페이지 로드
		reqPage($("#keyword").val(), true);
	}
	
	// 검색 버튼 이벤트
	$(document).on("click", ".btn-keyword", function(e){
		currentKeyword = $("#keyword").val();
		reqPage(currentKeyword, true);
	});
	
	// 양식 수량 획득
	function reqPage(keyword, loadFirstPage){
		ajaxRequest(
			{
				cmd: "getFormCount",
				keyword: keyword
			},
			function(data){
				setPage(data.result);
				if(loadFirstPage){
					let firstPage = $(".page-number").eq(0);
					if(firstPage.length) reqForm(firstPage, keyword);
				}
			}
		);
	}
	
	// UI에 페이지네이션 표시
	function setPage(formCount){
		let pagination = $(".pagination");
		pagination.empty();
		
		totalPage = 1;
		if(formCount > 0)
			totalPage = Math.ceil(formCount / 8);
		
		endPage = totalPage;
		if(totalPage > 10) endPage = 10;
		
		for(let idx = startPage; idx <= endPage; idx++){
			pagination.append(`<div class='page-number' data-page='${idx}'>${idx}</div>`);
		}
		clickPage($(".page-number").eq(0));
	}
	
	// 페이지 클릭 시 형태 변경
	function clickPage(pageElem){
		$(".page-number.active").removeClass("active");
		pageElem.addClass("active");
	}
	
	// 페이지 번호 클릭 이벤트
	$(document).on("click", ".page-number", function(e){
		let pageElem = $(this);
		if(pageElem.hasClass("active"))
			return;
		
		reqForm(pageElem, currentKeyword);
	});
	
	// 양식 정보 요청(8개)
	function reqForm(pageElem, keyword){
		ajaxRequest(
			{
				cmd: "getForm",
				page: pageElem.data("page"),
				keyword: keyword
			},
			function(data){
				clickPage(pageElem);
				setTable(data.result);
			}
		);
	}
	
	// 양식 정보를 테이블에 출력
	function setTable(data){
		let tableBody = $(".form-table tbody");
		tableBody.empty();

    	let row = "";
		$.each(data, function(i, form){
			row +=`<tr>
	            <td>${form.formId}</td>
	            <td>${form.formCategory}</td>
	            <td>${form.formName}</td>
	            <td>${form.formDescription}</td>
	            <td><div class="btn ${form.formUsage == 'Y' ? 'btn-use' : 'btn-unuse'}" data-form-id="${form.formId}">${form.formUsage == 'Y' ? "사용중" : "미사용"}</div></td>
	      	</tr>`;
		});
    	tableBody.append(row);
	}

	// 양식 사용 변경 이벤트
	$(document).on("click", ".btn", function(e){
		let formId = $(this).data("formId");
		reqInvertFormUsage(formId);
	});
	// 양식 사용 변경 요청
	function reqInvertFormUsage(formId){
		ajaxRequest(
			{
				cmd: "invertFormUsage",
				formId: formId
			}, 
			function(data){
				setFormUsage(data.result);
			}
		);
	}
	function setFormUsage(result){
		if(result)
			reqForm($(".page-number.active").eq(0), $("#keyword").val());
	}
</script>
    
    
</body>
</html>
