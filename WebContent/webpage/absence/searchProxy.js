var rankOrder = ['대표이사', '부장', '차장', '과장', '대리', '사원'];
var deptList = ['개발', '인사', '기획', '영업', '경영', '보안'];

function getAllEmployees() {
	$.ajax({
		url: "controller",
		data: { cmd: "getAllEmployees" },
		dataType: "json",
		success: function(result) {
			setOrgChart(result);
		},
		error: function() {
			console.log("사원 조회 실패");
		}
	});
}

// 부서별 그룹화
function groupEmployees(result) {
	var group = { CEO: null, departments: {} };

	$.each(result, function(i, emp) {
		var dept = emp.department;
		if (!dept || emp.rank === '대표이사') {
			group.CEO = emp;
		} else {
			if (!group.departments[dept]) group.departments[dept] = [];
			group.departments[dept].push(emp);
		}
	});

	var sorted = [];
	$.each(group.departments, function(deptName, emps) {
		emps.sort(function(a, b) {
			return rankOrder.indexOf(a.rank) - rankOrder.indexOf(b.rank);
		});
		sorted.push({ departmentName: deptName, employees: emps });
	});
	return { CEO: group.CEO, departments: sorted };
}

// 사원 HTML
function addUserItem(emp) {
	var html = '';
	html += '<div class="user-item" data-employeeid="' + emp.employeeId + '" data-name="' + emp.name + '" data-rank="' + emp.rank + '">';
	html += '  <img src="https://cdn-icons-png.flaticon.com/512/6522/6522516.png" class="profile-icon">';
	html += '  <div class="user-info">';
	html += '    ' + emp.name;
	html += '    <small>코스타오피스 &gt; ' + (emp.department ? emp.department + '팀 · ' : '') + emp.rank + '</small>';
	html += '  </div>';
	html += '  <button type="button" class="add-btn">추가</button>';
	html += '</div>';
	return html;
}

// 조직도 렌더링
function renderOrgChart(groupData) {
	var content = '';

	content += '<div class="accordion">';
	content += '  <div class="accordion-header">';
	content += '    <img src="img/arrow.png"> <span>코스타 오피스</span>';
	content += '  </div>';
	content += '  <div class="accordion-content">';

	if (groupData.CEO) {
		content += addUserItem(groupData.CEO);
	}

	for (var i = 0; i < groupData.departments.length; i++) {
		var deptGroup = groupData.departments[i];
		content += '<div class="accordion">';
		content += '  <div class="accordion-header">';
		content += '    <img src="img/arrow.png"> <span>' + deptGroup.departmentName + '팀</span>';
		content += '  </div>';
		content += '  <div class="accordion-content">';
		for (var j = 0; j < deptGroup.employees.length; j++) {
			content += addUserItem(deptGroup.employees[j]);
		}
		content += '  </div>';
		content += '</div>';
	}

	content += '  </div>';
	content += '</div>';

	$('.accordion-container').html(content);
	attachAccordionListeners();

	// 첫 번째 아코디언 자동 열기
	var first = $('.accordion-container > .accordion > .accordion-header').first();
	if (first.length) {
		first.addClass('open').next('.accordion-content').show();
	}
}

// 아코디언 토글
function attachAccordionListeners() {
	$('.accordion-header').off('click').on('click', function() {
		var content = $(this).next('.accordion-content');
		if (content.is(':visible')) {
			content.slideUp(150);
			$(this).removeClass('open');
		} else {
			content.slideDown(150);
			$(this).addClass('open');
		}
	});
}

// 전역 저장
function setOrgChart(result) {
	window.allEmployees = result;
	var grouped = groupEmployees(result);
	renderOrgChart(grouped);
}

// 문서 로드 후 실행
$(document).ready(function() {
	
	getAllEmployees();

	let searchInput = $('.search-input');
	let orgTree = $('.accordion-container');
	let searchResults = $('#search-results');

	// 검색 기능 (이름, 부서, 직급)
	searchInput.on('input', function() {
		let term = $(this).val().toLowerCase().trim();
		if (!window.allEmployees) return;

		if (term.length > 0) {
			orgTree.hide();
			searchResults.show().empty();

			let matched = [];
			$.each(window.allEmployees, function(i, emp) {
				let name = emp.name ? emp.name.toLowerCase() : '';
				let dept = emp.department ? emp.department.toLowerCase() : '';
				let rank = emp.rank ? emp.rank.toLowerCase() : '';

				if (name.indexOf(term) !== -1 || dept.indexOf(term) !== -1 || rank.indexOf(term) !== -1) {
					matched.push(addUserItem(emp));
				}
			});

			if (matched.length > 0) {
				searchResults.html(matched.join(''));
			} else {
				searchResults.html('<div class="no-results">검색 결과가 없습니다.</div>');
			}
		} else {
			orgTree.show();
			searchResults.hide();
		}
	});

	// 추가 버튼 클릭 시 -> 대결자 표시 + 모달 닫기
	$(document).on('click', '.add-btn', function() {
		let user = $(this).closest('.user-item');
		let name = user.data('name');
		let rank = user.data('rank');
		let employeeId = user.data('employeeid');
		let proxy = $('.proxy');

		// 부재추가/수정에서 자기자신 대결자선택시 알림 
		if (employeeId === loginId) {
	      alert("자기 자신은 대결자로 지정할 수 없습니다.");
	      return;
	    }
		
		if (proxy.find('.name').length) {
			alert('이미 대결자가 지정되어 있습니다.');
			return;
		}

		proxy.html(
			'<span class="name">' + name + '</span>' +
			'<span class="rank">' + rank + '</span>' +
			'<img src="./img/orange_x.png" class="proxy-remove" alt="삭제">'
		);
		
		$('#proxyId').val(employeeId);

		// 모달 닫기
		if (typeof closeModal === 'function') {
			closeModal(searchProxyModal);
		} else {
			$(searchProxyModal).hide();
		}
	});

	// 대결자 제거
	$(document).on('click', '.proxy-remove', function() {
		let proxy = $(this).closest('.proxy');
		proxy.html('<span style="color:#aaa;">대결자 없음</span>');
		$('#proxyId').val('');
	});
});

