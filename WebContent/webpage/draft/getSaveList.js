let startPage = 1;
let endPage = 1;
let totalPage = 1;


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

let getSaveList = function(result, currentPage){
	const totalCount = result.length > 0 && result[0].totalCount ? result[0].totalCount : 0;
	setPage(totalCount, currentPage);
	content = '';
	result.forEach(report => {
		let row = `
		<tr class="save-row" data-documentno="${report.documentNo}">
			<td>${report.temporarySaveDate}</td>
			<td>${report.formName}</td>
			<td>${report.title}</td>
			<td><button class="flag save">${report.processStatus}</button></td>
		</tr>
		`;
		content += row;
	});
	if(!result || result.length == 0){
		content = "<tr><td colspan='4'>임시 저장된 문서가 없습니다.</td></tr>";
	}
	reqListTbody.innerHTML = content;
}

let loadPageData = function(pageInfo){
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
			cmd: "getTempList",
			page: pageNum
		},
		dataType: "json",
		success: function(result){
			getSaveList(result, pageNum);
			
			if (pageElem) {
            	$(".page-number.active").removeClass("active");
                $(`.page-number[data-page='${pageNum}']`).addClass("active");
            }
		}
	});
}
$(document).ready(function(){
	const reqListTbody = document.querySelector(".form-table tbody");
	loadPageData(1);
});

$(document).on('click', '.save-row', function(e){
	if ($(e.target).is("button")) return;
	
	let documentNo = $(this).data("documentno")
	window.location.href = `controller?cmd=getTempDoc&documentNo=${documentNo}`;
})