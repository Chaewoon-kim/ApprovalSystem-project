const selectList = document.querySelectorAll("select");
let dt = new Date();
const currentYear = dt.getFullYear();
const currentMonth = dt.getMonth() + 1;
const currentDay = dt.getDate();
let selectedYear = currentYear;
let selectedMonth = currentMonth;

const isLeap = function(year) {
    return (year % 4 === 0 && year % 100 !== 0) || (year % 400 === 0);
};
const yearOptions = function(initYear){
	let content = "<option value='' disabled>년도</option>";
	for (var year = currentYear; year < currentYear+10; year++) {
		const isSelected = (year === initYear) ? 'selected' : '';
		content += `<option value="${year}" ${isSelected}>${year}년</option>`;
	}
	selectList[0].innerHTML = content;
};

const monthOptions = function(year, initMonth){
	const startMonth = (year === currentYear) ? currentMonth : 1;
	let content = "<option value='' disabled>월</option>";
	for (var month = startMonth; month <= 12; month++) {
		const isSelected = (month === initMonth) ? 'selected' : '';
        content += `<option value="${month}" ${isSelected}>${month}월</option>`;
	}
	selectList[1].innerHTML = content;
};

const dayOptions = function(year, month, initDay){
	let content = "<option value='' disabled>일</option>";
	let days = 31;
	const startDay = (year === currentYear && month === currentMonth) ? currentDay : 1;
	switch (month){
		case 4: case 6: case 9: case 11:
			days = 30;
			break;
		case 2:
			days = isLeap(year) ? 29 : 28;
			break;
	}
	for (day = startDay; day <= days; day++) {
		const isSelected = (day === initDay) ? 'selected' : '';
        content += `<option value="${day}" ${isSelected}>${day}일</option>`;
	}
	selectList[2].innerHTML = content;
};

const setInitDate = function(deadline){
	const parts = deadline.split('-');
	const initYear = parseInt(parts[0]);
    const initMonth = parseInt(parts[1]);
    const initDay = parseInt(parts[2]);
    
    selectedYear = initYear;
    selectedMonth = initMonth;
    yearOptions(initYear);
    monthOptions(initYear, initMonth);
    dayOptions(initYear, initMonth, initDay);
}

if (formJson && formJson.deadline) {
	setInitDate(formJson.deadline);
} else {
    yearOptions(currentYear);
    monthOptions(currentYear, currentMonth);
    dayOptions(currentYear, currentMonth, currentDay);
}

const handleFormSubmission = function(e){
	e.preventDefault();
    const form = $(this);
    const clickedButtonId = document.activeElement ? document.activeElement.id : '';
    let cmd = "controller?cmd=submitDoc";
    if(clickedButtonId == "tempSaveBtn"){
      cmd = "controller?cmd=saveTempDoc";
    }
    const formData = form.serialize();
    
    $.ajax({
    	url: cmd,
    	data: formData,
    	dataType: 'json',
    	success: function(result){
    		alert(result.message);
    		if(result.success){
    			location.href="controller?cmd=getReqListUI"
    		}
    	},
    	error: function(){
    		alert("결재 요청 중 오류가 발생하였습니다.")
    	}
    })
 
}
$("#approveDoc").on('submit', handleFormSubmission);
$("#cancelBtn").on('click', function(e){
	e.preventDefault();
	console.log("동작")
	window.location.href = "controller?cmd=getReqListUI"
})
