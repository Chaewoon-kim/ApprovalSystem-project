document.addEventListener("DOMContentLoaded", function() {
  document.querySelectorAll(".btn").forEach(function(btn) {
    btn.addEventListener("click", function() {
      if (btn.classList.contains("btn-use")) {
        btn.classList.remove("btn-use");
        btn.classList.add("btn-unuse");
        btn.textContent = "미사용";
      } else {
        btn.classList.remove("btn-unuse");
        btn.classList.add("btn-use");
        btn.textContent = "사용";
      }
    });
  });
});
