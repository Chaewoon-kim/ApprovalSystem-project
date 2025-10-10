//예시 데이터
const approvalTableData = [
           { name: "배성윤", department: "개발팀", rank: "대리", status: "승인", date: "2025.09.17.", comment: "", readStatus: "2025.09.17."},
           { name: "성민서 ", department: "개발팀", rank: "과장", status: "승인", date: "2025.09.17.", comment: "", readStatus: "2025.09.17." },
           { name: "김채운", department: "개발팀", rank: "부장", status: "반려", date: "2025.09.18.", comment: "신규 프로젝트 파견으로 현재 부서 내 인원이 부족한 상황입니다. 일정을 고려하여 다른 날짜에 다시 신청해 주시길 바랍니다.", readStatus: "2025.09.18." }
	    ];
const approvers = document.querySelector(".approvers-table")
const opinions = document.querySelector(".approval-opinions-section") 
		
const approversTable = function(data){
	let content = '';
	let rankCells = '';
	let nameCells = '';
	let resultCells = '';
	let readCells = '';
		    
	for(i = 0;  i < data.length ; i++){
		rankCells += '<td>' + data[i].department + data[i].rank + '</td>';
		nameCells += '<td>' + data[i].name + '</td>';
		result = (data[i].status == "반려") ? data[i].status : data[i].date; 
		resultCells += '<td>'+ result +'</td>';
		readStatus = (data[i].readStatus == null) ? "안읽음" : "읽음";
		readCells += '<td>' + readStatus + '</td>';
	}
		    
	content += '<tr>' + rankCells + '</tr>';
	content += '<tr>' + nameCells + '</tr>';
	content += '<tr>' + resultCells + '</tr>';
	content += '<tr>' + readCells + '</tr>';
	content += '</table>';
		    
	return content
}
		
const opinionsTable = function(data){
	let content = '';
	for (i = 0;  i < data.length ; i++) {
		content += '<div class="opinion-items"> <div class="opinion-items-info">';
		content += '<div>' + data[i].name + '</div>';
		content += '<div>' + data[i].department + '/' + data[i].rank + '</div></div><div class="opinion-items-comment">';
		const commentText = data[i].comment || "-";
		content += '<span>' + commentText + '</span></div><div class="opinion-items-status">'
		result = (data[i].status) ? "승인" : "반려"; 
		content += '<div>' + result + '</div>';
		content += '<td>'+ data[i].date +'</td></div></div>'
	}
		return content
}
approvers.innerHTML = approversTable(approvalTableData);
opinions.innerHTML = opinionsTable(approvalTableData);