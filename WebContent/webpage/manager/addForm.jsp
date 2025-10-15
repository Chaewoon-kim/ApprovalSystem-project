<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>양식 설정</title>
	<!-- JQuery JS -->
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

	<link href="webpage/manager/addForm.css" rel="stylesheet">
	<link href="webpage/employee/common.css" rel="stylesheet">
	<link href="webpage/employee/integration.css" rel="stylesheet">
</head>

<body>
	<jsp:include page="../employee/common.html" />
	
	<!-- 메인 콘텐츠 -->
	<main class="add-form">
	<div class="page-container">
	
		<!-- 페이지 제목 -->
		<div class="page-title">양식 설정</div>
	
		<!-- 양식 카테고리 -->
		<div class="content-container">
		<div class="content-object">
			<label class="content-label" for="formCategorySelect">양식 카테고리 선택</label>  
			<select id="formCategorySelect">
				<option value="업무">업무</option>
				<option value="인사">인사</option>
				<option value="출장">출장</option>
			</select>
		</div>
	
		<!-- 문서 제목 -->
		<div class="content-object">
			<label class="content-label" for="formName">문서 제목</label>
			<input id="formName" type="text" placeholder="예: 업무 기안서"/>
		</div>
	
		<!-- 기본 결재선 -->
		<div class="form-flex">
			<!-- 왼쪽: 결재선 -->
			<div class="approval-line">
				<div class="content-title">기본 결재선 설정</div>
				<div class="confirm-container">
					<!-- 여기에 추가 -->
					<div class="content-object"><button type="button" class="add-approver-btn">+</button></div>
					<div class="content-object">
						<div class="confirmer">
						<label for="final-confirm"> 최종결재자 </label> 
							<select id="final-confirm">
								<option value="대리">대리</option>
								<option value="과장">과장</option>
								<option value="부장">부장</option>
								<option value="차장">차장</option>
								<option value="사장">사장</option>
							</select>
						</div>
					</div>
					
				</div>
	
			</div>
	
			<!-- 오른쪽: 내용 양식 + 양식 설명 묶기 -->
				<div class="right-box">
					<div class="textarea-box">
						<label>내용 양식</label>
						<textarea id="formContent" rows="15">
	시행일자:
	결재부서:
	제목:
	내용:
	    </textarea>
					</div>
	
					<div class="textarea-box">
						<label>양식 설명</label>
						<textarea id="formDescription" rows="8"></textarea>
					</div>
				</div>	
			</div>
			<!-- 버튼 -->
		<button class="submit-btn">설정</button>
		</div>
	</div>
	</main>

	<script src="webpage/employee/common.js"></script>
	<script type="text/javascript">
		let cnt = 0;
		$(".add-approver-btn").click(function(){
			cnt++;
			$(".add-approver-btn").before(
				`<div class="content-object">
				<div class="confirmer">
				<label for="\${cnt}"> 중간결재자 \${cnt}</label> 
				<select id="\${cnt}">
					<option value="대리">대리</option>
					<option value="과장">과장</option>
					<option value="부장">부장</option>
					<option value="차장">차장</option>
					<option value="사장">사장</option>
				</select>
				</div>
				</div>`		
			);
		});
		$(document).on("click", ".submit-btn", function(){
			getValues();
		});
		
		function getValues(){
			data = {};
			data.formCategory = $("#formCategorySelect").val();
			data.formName = $("#formName").val();
			data.confirmer = $(".confirm-container")
				.find(".confirmer select").map(function(){
					return $(this).val();
				}).get();
			data.formContent = $("#formContent").val();
			data.formDescription = $("#formDescription").val();
			
			reqAddForm(data);
		}
		function reqAddForm(data){
			console.log(data);
			$.ajax({
				url: "controller",
				data: {
					cmd: "addForm", 
					data: JSON.stringify(data)
				},
				success: function(data){
					console.log(data);
					window.location.href = "controller?cmd=getForm"; 
				}
			});
		}
	</script>
</body>
</html>
