// 사이드 메뉴 토글
$(document).on('click', '.menu-title', function(){
	const title = this;
	const submenu = title.nextElementSibling;
	if (submenu.classList.contains("open")) {
      submenu.style.maxHeight = submenu.scrollHeight + "px";
      requestAnimationFrame(function() {
        submenu.style.maxHeight = "0";
      });
      submenu.classList.remove("open");
      title.classList.remove("open");
    } else {
      submenu.style.maxHeight = submenu.scrollHeight + "px";
      submenu.classList.add("open");
      title.classList.add("open");
    }
})
let changeMode = function(isChecked){
	const managerMenu = document.querySelector(".manager-menu");
	const employeeMenu = document.querySelector(".employee-menu");
	
	managerMenu.style.display = isChecked ? 'block' : 'none';
	employeeMenu.style.display = isChecked ? 'none' : 'block';
	
	localStorage.setItem('isManagerMode', isChecked);
}

const changeModeToggle = document.querySelector("#changeMode");

if (changeModeToggle) {
	 changeModeToggle.addEventListener('change', function() {
	     changeMode(this.checked);
	 });

	const savedMode = localStorage.getItem('isManagerMode');
	const isManagerMode = savedMode === 'true';

	changeModeToggle.checked = isManagerMode;

	changeMode(isManagerMode);
}

const openNotiBtn = $("#openNoti");
const closeNotiBtn = $("#closeNoti");
const modalOverlay = $("#notiO	verlay");
const notiModal = $("#notiModal");

openNotiBtn.on("click", ()=>{
	notiModal.toggle();
});
closeNotiBtn.on("click", ()=>{
	notiModal.hide();
});
$(document).on("click", (e) => {
	  if (!$(e.target).closest(".noti-modal, #openNoti").length > 0) {
		  notiModal.hide();
	  }
});



