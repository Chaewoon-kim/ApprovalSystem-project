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
/* Notification js */
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

let notiBody = $("#notiBody");
$(document).on("click", "#openNoti", ()=>{
	reqNoti(1, "", $("#notiBody"), makeModalContent);
});
//알림 정보 획득
function reqNoti(page, filter, elem, setFunc){
	ajaxRequest(
		{
			cmd: "getNotiList",
			page: page,
			filter: filter
		},
		function(data){
			setTable(data.result, elem, setFunc);
		}
	);
}
const makeModalContent = (tbody)=>{
	let cnt = 0;
	const $tbody = $(tbody);
	
	$tbody.children().each((index, element)=>{
		const $tr = $(element);
		let row = "<td><div class='notiObject'><div class='noti-label'>"+$tr.data("title")+"</div><div class='noti-label'>"+$tr.data("notiDate")+"</div></div></td>";
		if($tr.data("readStatus") == "안읽음"){
	    	$tr.append(row);
			cnt++;
		}
	});
	if(cnt == 0){
		$tbody.append("<tr><td><div>알림이 없습니다.</div></td></tr>");
	}
}
$("#notiList").click(function(){
	location.href = "controller?cmd=getNotiList";
});
$(document).on("click", ".notiObject", function(){
	list = [];
	let val = $(this).closest("tr").data();
	list.push(val);
	notiModal.hide();
	reqReadNoti(list, true);
});

function reqReadNoti(notiList, isClicked){
	if(notiList == null || notiList.length == 0) return;
				
	// 읽은 알림을 눌렀을 때
	if(isClicked && (notiList.at(0).readStatus == "읽음")) {
		clickNoti(notiList.at(0));
		return;
	}
	
	ajaxRequest(
		{
			cmd: "readNoti",
			notiList: JSON.stringify(notiList)
		},
		(data)=>{
			if(isClicked && data.result) {
				clickNoti(notiList.at(0));
			}
			else if(data.result){
				reqNoti(1, currentSelect, tableElem, makeContent);
			}
		}
	);
}
function setTable(data, elem, setFunc){
	elem.empty();
	
	let row="";
	$.each(data, function(i, noti) {
		  const readStatus = noti.readStatus == null ? "안읽음" : "읽음";
		  const notiDate = noti.notiInDate || "";
		  const notiType = noti.notiType;
		  const title = noti.title || "";
		  const approvedDoc = noti.approvedDocumentNo || "-";
		  const status = noti.status || "";
		  const documentNo = noti.documentNo || "";
		  const notiNo = noti.notiNo || "";

		  row += '<tr class="' + (readStatus === "읽음" ? "" : "bold") + '"' +
	       ' data-document-no="' + documentNo + '"' +
	       ' data-emp-id="' + noti.empId + '"' +
	       ' data-noti-type="' + notiType + '"' +
	       ' data-noti-no="' + notiNo + '"' +
	       ' data-status="' + status + '"' +
	       ' data-read-status="' + readStatus + '"' +
	       ' data-approved-doc="' + approvedDoc + '"' +
	       ' data-noti-date="' + notiDate + '"' +
	       ' data-title="' + title + '">' +
	       '</tr>';;
		});
	elem.append(row);
	setFunc(elem);
}
function clickNoti(notiInfo){
			switch(notiInfo["notiType"]){
			case "A":
				window.location.href="controller?cmd=getAbsenceProxyList";
				break;
			case "C":
			case "R":
			case "P":
				window.location.href = "controller?cmd=getDetailReport&documentNo="+notiInfo["documentNo"];
				break;
			}
		}
function ajaxRequest(params, success){
	$.ajax({
		url: "controller",
		data: params,
		success: success,
		error: function(err){console.error(err); alert("서버 요청 실패");}
	});
}



$(".logout").on('click', function(){
	window.location.href="controller?cmd=logoutAction";
})
