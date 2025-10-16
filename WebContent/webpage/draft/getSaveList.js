const reqListTbody = document.querySelector(".form-table tbody");

let getSaveList = function(result){
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
	})
}
let loadPageData = function(page){
	$.ajax({
		url: "controller",
		data: {
			cmd: "getTempList",
			page: page
		},
		dataType: "json",
		success: getSaveList
	});
}
$(document).ready(function(){
	loadPageData(1);
	const pageNumbers = document.querySelectorAll(".page-number");
	
	//페이지네이션
	pageNumbers.forEach(num => {
		num.addEventListener('click', function(e){
			e.preventDefault();
			const pageNum = this.innerText;
			loadPageData(pageNum);
		});
	});
	
});