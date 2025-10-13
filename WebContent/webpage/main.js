// main.js 파일 내용

// 아코디언 토글 기능
document.querySelectorAll('.accordion-header').forEach(header => {
    header.addEventListener('click', () => {
        const content = header.nextElementSibling;
        const arrowImg = header.querySelector('img');
        if (content.style.display === "block") {
            content.style.display = "none";
            arrowImg.src = "./img/arrow.png";	
        } else {
            content.style.display = "block";
            arrowImg.src = "./img/arrow-down.png";	
        }
    });
});

// 검색 기능 구현
document.addEventListener('DOMContentLoaded', () => {
    const searchInput = document.querySelector('.search-input');
    const orgTree = document.querySelector('.accordion-container');
    const searchResults = document.querySelector('#search-results');
    const approvalTableBody = document.querySelector('#approvalTable tbody');

    // 검색 기능
    searchInput.addEventListener('input', (event) => {
        const searchTerm = event.target.value.toLowerCase().trim();
        const allUserItems = document.querySelectorAll('.user-item');

        if (searchTerm.length > 0) {
            orgTree.style.display = 'none';
            searchResults.style.display = 'block';
            
            searchResults.innerHTML = '';
            let resultsFound = false;

            allUserItems.forEach(item => {
                const name = item.querySelector('.user-info').firstChild.textContent.toLowerCase();
                const info = item.querySelector('small').textContent.toLowerCase();
                if (name.includes(searchTerm) || info.includes(searchTerm)) {
                    searchResults.appendChild(item.cloneNode(true));
                    resultsFound = true;
                }
            });

            if (!resultsFound) {
                searchResults.innerHTML = `<div class="no-results">검색 결과가 없습니다.</div>`;
            }
        } else {
            orgTree.style.display = 'block';
            searchResults.style.display = 'none';
        }
    });

    // 결재자 추가/삭제 기능 (이벤트 위임)
    document.body.addEventListener('click', (event) => {
        const addBtn = event.target.closest('.add-btn');
        if (addBtn) {
            const userItem = addBtn.closest('.user-item');
            if (!userItem) return;

            const name = userItem.querySelector('.user-info').firstChild.textContent.trim();
            const infoText = userItem.querySelector('small').textContent.trim();
            const parts = infoText.split('>').map(part => part.trim());
            
            let department = '';
            let rank = '';
            if (parts.length >= 2) {
                department = parts[parts.length - 2];
                rank = parts[parts.length - 1];
            }
            
            const existingNames = Array.from(approvalTableBody.querySelectorAll('td:first-child')).map(td => td.textContent);
            if (existingNames.includes(name)) {
                alert('이미 추가된 사용자입니다.');
                return;
            }
            
            const newRow = document.createElement('tr');
            newRow.innerHTML = `
                <td>${name}</td>
                <td>${department}</td>
                <td>${rank}</td>
                <td><button type="button" class="delete-btn"><img src="https://cdn-icons-png.flaticon.com/512/1214/1214428.png" alt="삭제"></button></td>
            `;
            
            const approverSectionHeader = approvalTableBody.querySelector('.section-header-row:last-child');
            if (approverSectionHeader) {
                approverSectionHeader.parentNode.insertBefore(newRow, approverSectionHeader.nextSibling);
            } else {
                approvalTableBody.appendChild(newRow);
            }
        }
        
        const deleteBtn = event.target.closest('.delete-btn');
        if (deleteBtn) {
            const rowToRemove = deleteBtn.closest('tr');
            if (rowToRemove.classList.contains('applicant-row')) {
                alert('신청자는 삭제할 수 없습니다.');
                return;
            }
            rowToRemove.remove();
        }
    });
});