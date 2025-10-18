<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="searchProxyModal" class="modal-overlay" style="display:none;">
  <div class="searchProxy">
    <div class="modal-content">
      <div class="flex-container">
        <h2>대결자 선택</h2>
        <button class="x-btn" type="button" onclick="closeModal(searchProxyModal)">
          <img src="./img/x.png" alt="닫기">
        </button>
      </div>

      <div class="line-container">
        <div class="org-tree">
          <div class="org-header">조직도</div>
          <div class="org-search">
            <input type="text" placeholder="이름, 부서, 직책 입력" class="search-input">
          </div>

          <div id="search-results"></div>
          <div class="accordion-container"></div>
        </div>
      </div>
    </div>
  </div>
</div>
