const reqListTbody = document.querySelector(".form-table tbody");

let getReport = function(result){
	content = '';
	result.forEach(report => {
		statusStyle = ''
		switch(report.processStatus){
		case "반려":
			statusStyle = 'reject';
			break;
		case "완료":
			statusStyle = 'complete';
			break;
		}
		let row = `
		<tr>
			<td>${report.deadline}</td>
			<td>${report.draftDate || '-'}</td>
			<td>${report.completionDate || '-'}</td>
			<td><a class="doc-link" href="#" data-doc-no="${report.documentNo}">${report.title}</a> </td>
			<td>${report.approvedDocumentNo || '-'}</td>
			<td><button class="flag ${statusStyle}">${report.processStatus}</button></td>
		</tr>
		`;
		content += row;
	});
	reqListTbody.innerHTML = content;
	getDetailReport();
}

let getDetailReport = function(){
	const docLinks = document.querySelectorAll('.doc-link');
	
	docLinks.forEach(link=>{
		link.addEventListener('click', function(e){
			e.preventDefault();
			window.location.href = `controller?cmd=getDetailReport&documentNo=${this.dataset.docNo}`;
		})
	})
}
let loadPageData = function(page, status){
	$.ajax({
		url: "controller",
		data: {
			cmd: "getReqList",
			processStatus: status,
			page: page
		},
		dataType: "json",
		success: getReport
	});
}
$(document).ready(function(){
	loadPageData(1, '');
	const pageNumbers = document.querySelectorAll(".page-number");
	
	//페이지네이션
	pageNumbers.forEach(num => {
		num.addEventListener('click', function(e){
			e.preventDefault();
			const pageNum = this.innerText;
			loadPageData(pageNum, '');
		});
	});
	
	//상태별 조회
	const statusSelect = document.querySelector("#statusSelect");
	statusSelect.addEventListener('change', function(e){
		loadPageData(1, e.target.value);
	})
});