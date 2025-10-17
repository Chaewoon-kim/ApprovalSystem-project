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

let getSaveList = function(result, currentPage){
	console.log(result)
	const totalCount = result.length > 0 && result[0].totalCount ? result[0].totalCount : 0;
	setPage(totalCount, currentPage);
	content = '';
	result.forEach(report => {
		let row = `
		<tr>
			<td>${report.temporarySaveDate}</td>
			<td>${report.formName}</td>
			<td><a class="doc-link" href="#" data-doc-no="${report.documentNo}">${report.title}</a> </td>
			<td><button class="flag save">${report.processStatus}</button></td>
		</tr>
		`;
		content += row;
	});
	if(!result || result.length == 0){
		content = "<tr><td colspan='4'>임시 저장된 문서가 없습니다.</td></tr>";
	}
	reqListTbody.innerHTML = content;
	getTempDoc();
}
let getTempDoc = function(){
	const docLinks = document.querySelectorAll('.doc-link');
		
	docLinks.forEach(link=>{
		link.addEventListener('click', function(e){
			e.preventDefault();
			window.location.href = `controller?cmd=getTempDoc&documentNo=${this.dataset.docNo}`;
		})
	});
};
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
	loadPageData(1);
	
});