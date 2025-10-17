<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>알림 목록</title>
	<!-- JQuery JS -->
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	
	<link href="webpage/table.css" rel="stylesheet">
	<link href="webpage/employee/common.css" rel="stylesheet">
	<link href="webpage/report.css" rel="stylesheet" >
	<link href="webpage/employee/integration.css" rel="stylesheet">
</head>
<body>
	<!-- 헤더 -->
	<jsp:include page="/webpage/employee/common.jsp"/>

 	<!-- 메인 콘텐츠 -->
    <main class="form-list">
		<div class="page-title">알림 수신 목록</div>

		<!-- 결재 상태 필터 -->
		<div class="status-filter">
			<label for="statusSelect">결재 상태: </label> 
			<select id="statusSelect">
				<option value="전체">전체</option>
				<option value="대결">대결</option>
				<option value="댓글">댓글</option>
				<option value="결재결과">결재 결과</option>
				<option value="결재요청">결재 요청</option>
			</select>
		</div>
		
		<div class="btn-container">
			<button class="form-btn">
				<span>읽음 처리</span>
			</button>
		</div>
      
  		<table class="form-table">
        <thead>
          <tr>
          	<th><input type="checkbox" id="checkAll"></th> <!-- 전체 선택 -->
            <th>읽음상태</th>
            <th>알림 날짜</th>
            <th>알림 유헝</th>
            <th>제목</th>
            <th>문서 번호</th>
            <th>결재 상태</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td><input type="checkbox" class="row-check"></td>
      		<td>안읽음</td>
      		<td>2025-09-26</td>
      		<td>결재요청</td>
      		<td><a class="doc-link" href="#">비아이지스틸_소재처리 방안 보고</a></td>
      		<td>-</td>
      		<td><button class="flag">결재대기</button></td>
          </tr>          
        </tbody>
      </table>
  		
      

      <!-- 페이지네이션 -->
	  <div class="pagination"></div>
	
	<script src="common.js"></script>
	<script type="text/javascript">
		let currentSelect = '';
		let startPage = 1;
		let endPage = 10;
		let totalPage = 1;
		let inPage = 8;
	
		// 최초 실행
		$(document).ready(function(){
			init();
		});
		
		function init(){
			reqPage(currentSelect, true);
		}
		
		const filterMap = {
				  "전체": "",
				  "대결": "A",
				  "댓글": "C",
				  "결재결과": "R",
				  "결재요청": "P"
				};

		// 역매핑 자동 생성
		const reverseFilterMap = Object.fromEntries(
		  Object.entries(filterMap).map(([k, v]) => [v, k])
		);

		function getFilter(kor) {
		  return filterMap[kor] ?? '';
		}

		function getFilterName(code) {
		  return reverseFilterMap[code] ?? '전체';
		}

		// 페이지 정보 획득
		$(document).on("change", "#statusSelect", function(e){
			currentSelect = getFilter($(this).val());
			startPage = 1;
			endPage = 10;
			reqPage(currentSelect, true);
		});
		$(document).on("click", ".page-number", function(e){
			if(!$(this).hasClass("active")){
				let pageNo = $(this).data("page");
				if(pageNo == null) pageNo = 1;
				
				reqEmployee(pageNo, currentSelect);
				clickPage($(this));
			}
		});
		// 비동기 알림 수 획득
		function reqPage(filter, loadFirstPage){
			ajaxRequest(
				{
					cmd: "getNotiCount",
					filter: filter
				},
				function(data){
					setPage(data.result);
					if(loadFirstPage){
						let firstPage = $(".page-number").eq(0).data("page");
						if(firstPage != null) reqNoti(firstPage, filter);
					}
				}
			);
		}
		// UI에 페이지네이션 표시
		function setPage(alarmCount){
			let pagination = $(".pagination");
			pagination.empty();
			
			// 0 이상일 경우 계산
			if(alarmCount > 0)
				totalPage = Math.ceil(alarmCount / inPage);
			
			if(totalPage < endPage ) endPage = totalPage;
			
			for(let idx = startPage; idx <= endPage; idx++){
				pagination.append(`<div class='page-number' data-page='${idx}'>${idx}</div>`);
			}
			clickPage($(".page-number").eq(0));
		}
		
		// 알림 정보 획득
		function reqNoti(page, filter){
			ajaxRequest(
				{
					cmd: "getNotiList",
					page: page,
					filter: filter
				},
				function(data){
					setTable(data.result);
				}
			);
		}
		function setTable(data){
			let tableBody = $(".form-table tbody");
			tableBody.empty();
			
			let row="";
			$.each(data, function(i, noti) {
				  const readStatus = noti.readStatus == null ? "안읽음" : "읽음";
				  const notiDate = noti.notiInDate || "";
				  const notiType = getFilterName(noti.notiType || "");
				  const title = noti.title || "";
				  const approvedDoc = noti.approvedDocumentNo || "-";
				  const status = noti.status || "";
				  const documentNo = noti.documentNo || "";

				  row += `
				    <tr class="${readStatus == "읽음" ? "" : "bold"}"
				    	data-document-no="${documentNo}"
				    	data-emp-id="${noti.empId}"
				    	data-noti-type="${noti.notiType}"
				    	data-noti-no="${noti.notiNo}"
				    	data-read-status="${readStatus}""> 
				      <td><input type="checkbox" class="row-check"></td>
				      <td>${readStatus}</td>
				      <td>${notiDate}</td>
				      <td>${notiType}</td>
				      <td><div><span class="text-link">${title}</span></div></td>
				      <td>${approvedDoc}</td>
				      <td><div ${status == ""? "" : "class='flag'"}>${status}</div></td>
				    </tr>
				  `;
				});
			tableBody.append(row);
		}
		$(document).on("click", ".text-link", function(){
			reqReadNoti($(this).closest("tr"));
		});
		function reqReadNoti(notiElem){
			console.log(notiElem.data("notiNo"));
			const readStatus = notiElem.data("readStatus")=="읽음";
			const notiType = notiElem.data("notiType");
			const notiNo = notiElem.data("notiNo");
			
			if(readStatus) {
				clickNoti(notiElem);
				return;
			}
			
			ajaxRequest(
				{
					cmd: "readNoti",
					notiType: notiType,
					notiNo: notiNo
				},
				(data)=>{
					if(data.result) clickNoti(notiElem);
				}
			);
		}
		function clickNoti(notiElem){
			switch(notiElem.data("notiType")){
			case "A":
				window.location.href="controller?cmd=getAbsenceProxyList";
				break;
			case "C":
			case "R":
			case "P":
				window.location.href = "controller?cmd=getDetailReport&documentNo="+notiElem.data("documentNo");
				break;
			}
		}
		function clickPage(pageElem){
			$(".page-number.active").removeClass("active");
			pageElem.addClass("active");
		}
		
		
		
		function ajaxRequest(params, success){
			$.ajax({
				url: "controller",
				data: params,
				success: success,
				error: function(err){console.error(err); alert("서버 요청 실패");}
			});
		}
	
	</script>
</body>
</html>