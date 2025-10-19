let currentSelect = '';
		let currentPage = 1;
		let startPage = 1;
		let endPage = 10;
		let totalPage = 1;
		let inPage = 8;
		let tableElem = $("#notiTable");
		// 최초 실행
		$(document).ready(function(){
			init();
		});
		
		function init(){
			reqPage(currentSelect, true);
			tableElem = $("#notiTable");
		}
		
		const filterMap = {
				  "전체": "",
				  "대결": "A",
				  "댓글": "C",
				  "결재요청": "R",
				  "결재결과": "P"
				};

		// 역매핑 자동 생성
		const reverseFilterMap = Object.fromEntries(
		  Object.entries(filterMap).map(([k, v]) => [v, k])
		);

		function getFilter(kor) {
		  return filterMap[kor] ?? '';
		}

		function getFilterName(code) {
		  return reverseFilterMap[code] ?? '전체';
		}

		// 페이지 정보 획득
		$(document).on("change", "#statusSelect", function(e){
			currentSelect = getFilter($(this).val());
			startPage = 1;
			endPage = 10;
			reqPage(currentSelect, true);
		});
		$(document).on("click", ".page-number", function(e){
			if(!$(this).hasClass("active")){
				
				currentPage = $(this).data("page");
				
				reqNoti(currentPage, currentSelect, tableElem, makeContent);
				clickPage($(this));
			}
		});
		// 비동기 알림 수 획득
		function reqPage(filter, loadFirstPage){
			ajaxRequest(
				{
					cmd: "getNotiCount",
					filter: filter
				},
				function(data){
					setPage(data.result);
					if(loadFirstPage){
						reqNoti(1, filter, tableElem, makeContent);
					}
				}
			);
		}
		// UI에 페이지네이션 표시
		function setPage(alarmCount){
			let pagination = $("#notiPage");
			pagination.empty();
			
			// 0 이상일 경우 계산
			if(alarmCount > 0)
				totalPage = Math.ceil(alarmCount / inPage);
			
			if(totalPage < endPage ) endPage = totalPage;

			for(let idx = startPage; idx <= endPage; idx++){
				pagination.append("<div class='page-number' data-page='" + idx + "'>" + idx + "</div>");
			}
			clickPage($(".page-number").eq(0));
		}
		
		// 알림 정보 획득
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
		function setTable(data, elem, setFunc){
			elem.empty();
			
			let row="";
			$.each(data, function(i, noti) {
				  const readStatus = noti.readStatus == null ? "안읽음" : "읽음";
				  const notiDate = noti.notiInDate || "";
				  const notiType = noti.notiType;
				  let title = noti.title || "";
				  if(notiType =="A") title ="["+ title +"]님으로부터 대결자로 지정되었습니다.";
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
		const makeContent = (elem) => {
			const $tbody = $(elem);
			$tbody.children().each((index, element)=>{
				const $tr = $(element);
				statusStyle = ''
					switch($tr.data("status")){
					case "반려":
						statusStyle = 'reject';
						break;
					case "완료":
						statusStyle = 'complete';
						break;
					}
				let row = '<td><input type="checkbox" class="row-check"></td>'+'<td>' + $tr.data("readStatus") + '</td>' +
		          '<td>' + $tr.data("notiDate") + '</td>' +
		          '<td>' + getFilterName($tr.data("notiType")) + '</td>' +
		          '<td><div><span class="text-link">' + $tr.data("title") + '</span></div></td>' +
		          '<td>' + $tr.data("approvedDoc") + '</td>' +
		          '<td><div ' + ($tr.data("status") == "" ? "" : "class=\'flag " + statusStyle + "\'") + '>' +
		              $tr.data("status") + '</div></td>';
		              
			      $tr.append(row);
			});
		}
		$(document).on("click", ".text-link", function(){
			let notiObj = $(this).closest("tr");
			notiList = [];
			notiList.push(notiObj.data());
			console.log(notiList[0].readStatus);
			reqReadNoti(notiList, true);
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
					if(isClicked && data.result) clickNoti(notiList.at(0));
					else if(data.result){
						reqNoti(1, currentSelect, tableElem, makeContent);
					}
				}
			);
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
		function clickPage(pageElem){
			$(".page-number.active").removeClass("active");
			pageElem.addClass("active");
			currentPage = pageElem.data("page");
		}
		
		function ajaxRequest(params, success){
			$.ajax({
				url: "controller",
				data: params,
				success: success,
				error: function(err){console.error(err); alert("서버 요청 실패");}
			});
		}
		$(document).on("click", "#readAll", ()=>{
			readAll();
		});
		
		const readAll = ()=>{			
			let tableBody = $(".form-table tbody");
			
			notiList = [];
			tableBody.find(".row-check").each(function(i, noti) {
				if($(this).is(":checked")) {
					let notiObj = $(this).closest("tr");
					
					if(notiObj.data("readStatus") == "읽음") return;
					notiList.push(notiObj.data());
				}
			});
			
			reqReadNoti(notiList);
		}