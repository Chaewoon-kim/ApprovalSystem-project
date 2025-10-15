<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <link rel="stylesheet" href="webpage/employee/documentForm.css">
</head>
<div class="documentForm">
    <h1 class="text-center documentTitle"></h1>
    <div class="flex-container">
        <div class="tables">
        </div>

        <div class="flex-container">
            <div class="tables">
            </div>

            <div class="tables">
                
            </div>
        </div>
    </div>

    <hr>
	<c:choose>
	<c:when test="${add}">
		<textarea class="inputForm"></textarea>
	</c:when>
	<c:otherwise>
		<div class="inputForm"></div>
	</c:otherwise>
	</c:choose>
    
</div>

<script type="text/javascript">
const formJson = JSON.parse('${form}');
$(".documentTitle").data('formId', formJson.formName);
$(".documentTitle").text(formJson.formName);
$(".inputForm").html(formJson.formContent);
let tableList = document.querySelectorAll(".tables");

var getTodayFormatted = function() {
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0');
    const day = String(today.getDate()).padStart(2, '0');
    
    return year + '-' + month + '-' + day;
}

var addDrafterTable = function(table, data){
    let content = '<table class="table-bordered">';
    const tablehead = ["기안자", "소속", "기안일", "문서번호"];
    let inputData = ["","","",""];

    if(data != null){
        inputData = [data.name, data.department, getTodayFormatted(), ""];
    }
    
    for(i = 0; i < 4 ; i++){
        content += '<tr>';
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

const drafterTableData = { name: '${name}', department: '${department}', rank: '${rank}'};
const approvalTableData = ${defaultlines};

 tableList[0].innerHTML = addDrafterTable(tableList[0], drafterTableData);
 tableList[1].innerHTML = addProposalTable(tableList[1], drafterTableData);
 tableList[2].innerHTML = addApprovalTable(tableList[2], approvalTableData);
</script>