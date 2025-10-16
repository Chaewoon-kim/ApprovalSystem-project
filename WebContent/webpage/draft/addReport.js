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
const yearOptions = function(){
	let content = "<option value='' disabled selected>년도</option>";
	for (var year = currentYear; year < currentYear+10; year++) {
		content += "<option value=" + year + ">" + year +"년" + "</option>";
	}
	selectList[0].innerHTML = content;
};

const monthOptions = function(year){
	const startMonth = (year === currentYear) ? currentMonth : 1;
	let content = "<option value='' disabled selected>월</option>";
	for (var month = startMonth; month <= 12; month++) {
		content += "<option value=" + month+ ">" + month + "월" + "</option>";
	}
	selectList[1].innerHTML = content;
};

const dayOptions = function(year, month){
	let content = "<option value='' disabled selected>일</option>";
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
	for (var day = startDay; day <= days; day++) {
		content += "<option value= " + day+ ">" + day + "일" + "</option>";
	}
	selectList[2].innerHTML = content;
};

yearOptions();
monthOptions(currentYear);
dayOptions(currentYear, currentMonth);
selectList[0].addEventListener('change', function(e) {
    selectedYear = parseInt(e.target.value); 
    monthOptions(selectedYear); 
});
selectList[1].addEventListener('change', function(e) {
    selectedMonth = parseInt(e.target.value); 
    dayOptions(selectedYear, selectedMonth); 
});
const handleFormSubmission = function(e){
	e.preventDefault();
    const form = $(this);
    const clickedButtonId = document.activeElement ? document.activeElement.id : '';
    if(clickedButtonId == "tempSaveBtn"){
      e.preventDefault();
      form.attr("action", "controller?cmd=saveTempDoc");
    }
    form.off('submit', handleFormSubmission); 
    form.submit();
    form.on('submit', handleFormSubmission);
    form.attr("action", "controller?cmd=submitDoc");
}
$("#approveDoc").on('submit', handleFormSubmission);

