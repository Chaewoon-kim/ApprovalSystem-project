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
    } else {
      submenu.style.maxHeight = submenu.scrollHeight + "px";
      submenu.classList.add("open");
    }
  });
});
