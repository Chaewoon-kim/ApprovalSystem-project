const selectList = document.querySelectorAll("select");
let dt = new Date();
const currentYear = dt.getFullYear();

var yearOptions = function(){
	let content = "<option>년도</option>";
	for (var year = currentYear; year < currentYear+10; year++) {
		content += "<option value=" + year + ">" + year +"년" + "</option>";
	}
	selectList[0].innerHTML = content;
}

var monthOptions = function(){
	let content = "<option>월</option>";
	for (var month = 1; month <= 12; month++) {
		content += "<option value=" + month+ ">" + month + "월" + "</option>";
	}
	selectList[1].innerHTML = content;
}

var dayOptions = function(month){
	let content = "<option>일</option>";
	let days = 31;
	switch (month){
		case 4: case 6: case 9: case 11:
			days = 30;
			break;
		case 2:
			days = 28;
			break;
	}
	for (var day = 1; day <= days; day++) {
		content += "<option value= " + day+ ">" + day + "일" + "</option>";
	}
	selectList[2].innerHTML = content;
}

yearOptions();
monthOptions();
selectList[1].addEventListener('change', function(e) {
    const selectedMonth = parseInt(e.target.value); 
    dayOptions(selectedMonth); 
});