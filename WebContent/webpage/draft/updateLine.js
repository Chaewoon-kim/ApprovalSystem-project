const rankOrder = ['대표이사', '부장', '차장', '과장', '대리', '사원'];
const dept = ['개발', '인사', '기획', '영업', '경영', '보안']
//2. 부서별로 그룹화
let groupEmployees = function(result){
	const group = {
			CEO: null,
			departments:{}
	};
	result.forEach(emp => {
		const dept = emp.department;
		
		if(!dept || emp.rank == '대표이사'){
			group.CEO = emp;
		}else{
			if(!group.departments[dept]){
				group.departments[dept] = [];
			}
			group.departments[dept].push(emp);
		}
	})
	const sortEmployeeByRank = Object.keys(group.departments).map(deptName => {
        group.departments[deptName].sort((a, b) => 
        rankOrder.indexOf(a.rank) - rankOrder.indexOf(b.rank) // 직급 순으로 정렬
    );
    return { departmentName: deptName, employees: group.departments[deptName] };
});
	return { CEO: group.CEO, departments: sortEmployeeByRank };
};
//1. 비동기로 전체사원조회
let getAllEmployees = function(){
	$.ajax({
		url:"controller",
		data:{
			cmd: "getAllEmployees"
		},
		dataType:"json",
		success: setOrgChart
	});
};
//3-1. 사원별
let addUserItem = function(emp){
	return `<div class="user-item">
			<img src="https://cdn-icons-png.flaticon.com/512/6522/6522516.png" class="profile-icon">
				<div class="user-info">
					${emp.name}
					<small>코스타오피스 &gt; ${emp.department ? emp.department + '팀 · ' : ''}${emp.rank}</small>
				</div>
				<button type="button" class="add-btn">추가</button>
			</div>`
};
//3.조직도 생성
let renderOrgChart = function(groupData){
	let content = '';
	content += `<div class="accordion">
					<div class="accordion-header">
					<img src="img/arrow.png"> <span>코스타 오피스</span>
				</div>
				<div class="accordion-content">`
	if(groupData.CEO){
		content += addUserItem(groupData.CEO);
	}
		groupData.departments.forEach(deptGroup =>{
		const deptName = deptGroup.departmentName;
		content += `<div class="accordion">
						<div class="accordion-header">
						<img src="img/arrow.png"> <span>${deptName}팀</span>
					</div>
					<div class="accordion-content">`
		deptGroup.employees.forEach(emp =>{
			content += addUserItem(emp)
		});
		content += `</div></div>`
	})
	content += `</div></div>`
	document.querySelector(".accordion-container").innerHTML = content;
	attachAccordionListeners(); 

    //최상위 아코디언만 하나 열기
    const firstHeader = document.querySelector(".accordion-container > .accordion > .accordion-header");
    if (firstHeader) {
        firstHeader.nextElementSibling.style.display = "block"; 
        firstHeader.classList.add('open');  
    }
}

let setOrgChart = function(result){
	grouped = groupEmployees(result);
	renderOrgChart(grouped);
};

// 아코디언 토글 기능
let attachAccordionListeners = function(){
	document.querySelectorAll('.accordion-header').forEach(header => {
	    header.addEventListener('click', function() {
	        const content = header.nextElementSibling;
	        
	        if (content.style.display === "block") {
	            content.style.display = "none";
	            this.classList.remove('open');

	        } else {
	            content.style.display = "block";
	            this.classList.add('open');	
	        }
	    });
	});
}


// 검색 기능 구현
document.addEventListener('DOMContentLoaded', () => {
    getAllEmployees();
	const searchInput = document.querySelector('.search-input');
    const orgTree = document.querySelector('.accordion-container');
    const searchResults = document.querySelector('#search-results');
    const approvalTableBody = document.querySelector('#approvalTable tbody');

    // 검색 기능
    searchInput.addEventListener('input', (event) => {
        const searchTerm = event.target.value.toLowerCase().trim();
        const allUserItems = document.querySelectorAll('.user-item');

        if (searchTerm.length > 0) {
            orgTree.style.display = 'none';
            searchResults.style.display = 'block';
            
            searchResults.innerHTML = '';
            let resultsFound = false;

            allUserItems.forEach(item => {
                const name = item.querySelector('.user-info').firstChild.textContent.toLowerCase();
                const info = item.querySelector('small').textContent.toLowerCase();
                if (name.includes(searchTerm) || info.includes(searchTerm)) {
                    searchResults.appendChild(item.cloneNode(true));
                    resultsFound = true;
                }
            });

            if (!resultsFound) {
                searchResults.innerHTML = `<div class="no-results">검색 결과가 없습니다.</div>`;
            }
        } else {
            orgTree.style.display = 'block';
            searchResults.style.display = 'none';
        }
    });

    // 결재자 추가/삭제 기능 (이벤트 위임)
    document.body.addEventListener('click', (event) => {
        const addBtn = event.target.closest('.add-btn');
        if (addBtn) {
            const userItem = addBtn.closest('.user-item');
            if (!userItem) return;

            const name = userItem.querySelector('.user-info').firstChild.textContent.trim();
            const infoText = userItem.querySelector('small').textContent.trim();
            const parts = infoText.split('>').map(part => part.trim());
            
            let department = '';
            let rank = '';
            if (parts.length >= 2) {
                department = parts[parts.length - 2];
                rank = parts[parts.length - 1];
            }
            
            const existingNames = Array.from(approvalTableBody.querySelectorAll('td:first-child')).map(td => td.textContent);
            if (existingNames.includes(name)) {
                alert('이미 추가된 사용자입니다.');
                return;
            }
            
            const newRow = document.createElement('tr');
            newRow.innerHTML = `
                <td>${name}</td>
                <td>${department}</td>
                <td>${rank}</td>
                <td><button type="button" class="delete-btn"><img src="https://cdn-icons-png.flaticon.com/512/1214/1214428.png" alt="삭제"></button></td>
            `;
            
            const approverSectionHeader = approvalTableBody.querySelector('.section-header-row:last-child');
            if (approverSectionHeader) {
                approverSectionHeader.parentNode.insertBefore(newRow, approverSectionHeader.nextSibling);
            } else {
                approvalTableBody.appendChild(newRow);
            }
        }
        
        const deleteBtn = event.target.closest('.delete-btn');
        if (deleteBtn) {
            const rowToRemove = deleteBtn.closest('tr');
            if (rowToRemove.classList.contains('applicant-row')) {
                alert('신청자는 삭제할 수 없습니다.');
                return;
            }
            rowToRemove.remove();
        }
    });
});