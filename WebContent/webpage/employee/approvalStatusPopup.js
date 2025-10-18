$(document).ready(function(){
	const documentNo = $("#docNo").val();
	$('#getApprovalStatus').click(
			function(){
				getApprovalStatus(documentNo);
			}
		);
});
const getApprovalStatus = function(documentNo){
	$.ajax({
		url: "controller",
		data: {
			cmd: "getApprovalStatus",
			documentNo: documentNo
		},
		dataType: "json",
		success: setApprovalStatus
	})
};
const setApprovalStatus = function(result){
	approvers.innerHTML = approversTable(result);
	opinions.innerHTML = opinionsTable(result);
};

const approvers = document.querySelector(".approvers-table");
const opinions = document.querySelector(".approval-opinions-section") ;
		
const approversTable = function(data){
	let content = '';
	let rankCells = '';
	let nameCells = '';
	let resultCells = '';
	let readCells = '';
		    
	for(i = 0;  i < data.length ; i++){
		const department = data[i].department ? data[i].department : "";
		rankCells += '<td>' + department + "팀/" + data[i].rank + '</td>';
		nameCells += '<td>' + data[i].name + '</td>';
		result = (data[i].approvalStatus == "반려") ? data[i].approvalStatus : data[i].approvalDate ? data[i].approvalDate : "-"; 
		resultCells += '<td>'+ result +'</td>';
		readStatus = data[i].requestReadStatus ? "읽음" : "안읽음";
		readCells += '<td>' + readStatus + '</td>';
	}
		    
	content += '<tr>' + rankCells + '</tr>';
	content += '<tr>' + nameCells + '</tr>';
	content += '<tr>' + resultCells + '</tr>';
	content += '<tr>' + readCells + '</tr>';
	content += '</table>';
		    
	return content
};
		
const opinionsTable = function(data){
	let content = '';
	for (i = 0;  i < data.length ; i++) {
		content += '<div class="opinion-items"> <div class="opinion-items-info">';
		content += '<div>' + data[i].name + '</div>';
		const department = data[i].department ? data[i].department + "/" : "";
		content += '<div>' + department + data[i].rank + '</div></div><div class="opinion-items-comment">';
		const opinion = data[i].opinion || "-";
		content += '<span>' + opinion + '</span></div><div class="opinion-items-status">'
		result = data[i].approvalStatus; 
		content += '<div>' + result + '</div>';
		const date = data[i].approvalDate || "";
		content += '<div>'+ date +'</div></div></div>'
	}
		return content
};