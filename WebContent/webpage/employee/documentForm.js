document.querySelector("h1").innerText = '${form.formName}';
document.querySelector(".inputForm").innerHTML = '<p>휴가 종류: 연차</p><p>휴가 기간: 2025년 09월 18일 ~ 20205년 09월 19일</p><p>휴가 사유: 예비군</p>';
let tableList = document.querySelectorAll(".tables");

var getTodayFormatted = function() {
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0');
    const date = String(today.getDate()).padStart(2, '0');
    
    return `${year}.${month}.${date}`;
}

var addDrafterTable = function(table, data){
    let content = '<table class="table-bordered">';
    const tablehead = ["기안자", "소속", "기안일", "문서번호"];
    let inputData = ["","","",""];

    if(data != null){
        inputData = [data.name, data.department, getTodayFormatted(), ""];
    }
    
    for(i = 0; i < 4 ; i++){
        content += '<tr>'
        content += '<th class="table-bgColor draft-th">'+ tablehead[i]+'</th>';
        content += '<td class="input-table">'+ inputData[i]+'</td>';
        content += '</tr>';
    }
    content += '</table>';
    return content;
}

var addProposalTable = function(table, data){
    let content = '<table class="table-bordered">';
    let inputData = ["","",""];
    if(data != null){
        inputData = [data.rank, data.name,""];
    }
    content += '<tr><th rowspan="4" class="table-bgColor rotated-text">신청</th></tr>'
    for(i = 0; i < 3 ; i++){
        content += '<tr>';
        content += '<td class="input-table">'+ inputData[i]+'</td>';
        content += '</tr>';
    }
    content += '</table>';
    return content;
}

var addApprovalTable = function(table, data){
    let content = '<table class="table-bordered">';
    content += '<tr><th rowspan="4" class="table-bgColor rotated-text">승인</th></tr>';
    let rankCells = '';
    let nameCells = '';
    let signCells = '';
    for(i = 0;  i < data.length ; i++){
        const department = data[i].department ? data[i].department + '/' : '';
        const name = data[i].name ? data[i].name : '';
        rankCells += '<td class="input-table">' + department + data[i].rank + '</td>';
        nameCells += '<td class="input-table">' + name + '</td>';
        signCells += '<td class="input-table"></td>';
        }
    content += '<tr>' + rankCells + '</tr>';
    content += '<tr>' + nameCells + '</tr>';
    content += '<tr>' + signCells + '</tr>';
    content += '</table>';
    return content;
}

// Data
const drafterTableData = { name: "지연우", department: "개발팀", rank: "사원" };
const approvalTableData1 = [
    {rank: "대리"},
    {rank: "과장"},
    {rank: "부장"}
];
const approvalTableData2 = [
    { name: "배성윤", department: "개발팀", rank: "대리" },
    { name: "성민서 ", department: "개발팀", rank: "과장" },
    { name: "김채운", department: "개발팀", rank: "부장" }
];
//양식 생성할때
//tableList[0].innerHTML = addDrafterTable(tableList[0]);
//tableList[1].innerHTML = addProposalTable(tableList[1]);
//tableList[2].innerHTML = addApprovalTable(tableList[2], approvalTableData1);

//양식 작성할때
 tableList[0].innerHTML = addDrafterTable(tableList[0], drafterTableData);
 tableList[1].innerHTML = addProposalTable(tableList[1], drafterTableData);
 tableList[2].innerHTML = addApprovalTable(tableList[2], approvalTableData2);