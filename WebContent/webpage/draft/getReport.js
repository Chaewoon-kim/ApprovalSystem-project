let status = '';
let startPage = 1;
let endPage = 1;
let totalPage = 1;

const reqListTbody = document.querySelector(".form-table tbody");

let setPage = function(totalCount, currentPage = 1){ 
	let pagination = $(".pagination");
	pagination.empty();
	
	totalPage = 1;
	if(totalCount > 0)
		totalPage = Math.ceil(totalCount / 8);
	
	endPage = totalPage;
	if(totalPage > 10) endPage = 10;
	
	for(let idx = startPage; idx <= endPage; idx++){
        const isActive = (idx == currentPage) ? ' active' : '';
		pagination.append(`<div class='page-number${isActive}' data-page='${idx}'>${idx}</div>`);
	}
    
}

$(document).on("click", ".page-number", function(e){
	let pageElem = $(this);
	if(pageElem.hasClass("active"))
		return;
	
	loadPageData(pageElem, status); 
});


let getReport = function(result, currentPage){
	const totalCount = result.length > 0 && result[0].totalCount ? result[0].totalCount : 0;
	
	setPage(totalCount, currentPage); 
    
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
			<td><a class="doc-link" href="#" data-doc-no="${report.documentNo}">${report.title}</a> </td>
			<td>${report.approvedDocumentNo || '-'}</td>
			<td><button class="flag ${statusStyle}">${report.processStatus}</button></td>
		</tr>
		`;
		content += row;
	});
	if(!result || result.length == 0){
		content = "<tr><td colspan='6'>결재 신청된 문서가 없습니다.</td></tr>";
	}
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

let loadPageData = function(pageInfo, status){
    let pageNum;
    let pageElem;
   
    if (typeof pageInfo === 'object' && pageInfo.jquery) {
        pageElem = pageInfo;
        pageNum = pageElem.data("page");
    } else {
        pageNum = pageInfo;
    }

	$.ajax({
		url: "controller",
		data: {
			cmd: "getReqList",
			processStatus: status,
			page: pageNum
		},
		dataType: "json",
		success: function(result){
			getReport(result, pageNum); 
            
            if (pageElem) {
            	$(".page-number.active").removeClass("active");
                $(`.page-number[data-page='${pageNum}']`).addClass("active");
            }
		}
	});
}

$(document).ready(function(){
	loadPageData(1, ''); 
	
	const statusSelect = document.querySelector("#statusSelect");
	statusSelect.addEventListener('change', function(e){
		status = e.target.value;
		loadPageData(1, e.target.value);
	})
});