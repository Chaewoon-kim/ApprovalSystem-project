// 상태 버튼 토글
document.querySelectorAll(".btn").forEach(function(btn) {
  btn.addEventListener("click", function() {
    if (btn.classList.contains("btn-use")) {
      btn.classList.remove("btn-use");
      btn.classList.add("btn-unuse");
      btn.textContent = "권한 해제";
    } else {
      btn.classList.remove("btn-unuse");
      btn.classList.add("btn-use");
      btn.textContent = "권한 부여";
    }
  });
});
