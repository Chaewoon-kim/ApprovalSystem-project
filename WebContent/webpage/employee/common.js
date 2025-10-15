// 사이드 메뉴 토글
document.querySelectorAll(".menu-title").forEach(function(title) {
  title.addEventListener("click", function() {
	
    var submenu = title.nextElementSibling;
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
  });
});
let changeMode = function(isChecked){
	const managerMenu = document.querySelector(".manager-menu");
	const employeeMenu = document.querySelector(".employee-menu");
	
	managerMenu.style.display = isChecked ? 'block' : 'none';
	employeeMenu.style.display = isChecked ? 'none' : 'block';
}

const changeModeToggle = document.querySelector("#changeMode");

if (changeModeToggle) {
 changeModeToggle.addEventListener('change', function() {
     changeMode(this.checked);
 });
}