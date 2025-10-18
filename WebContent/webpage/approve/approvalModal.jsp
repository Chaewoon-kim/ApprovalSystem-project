<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="approvalModal" class="modal-overlay" style="display:none;">
  <div class="modal-content">
    <h2>결재하기</h2>
    <form action="controller?cmd=approvalProcess" method="post">

      <input type="hidden" name="documentNo" value="${documentDetail.documentNo}">
      <input type="hidden" name="lineOrder" value="${approverLineOrder}">
      <!-- <input type="hidden" name="approverId" value="${sessionScope.employeeId}"> -->
     
      <table>
        <tr>
          <th>결재 상태</th>
          <td>
            <label><input type="radio" name="approvalStatus" value="승인" checked> 승인</label>
            <label><input type="radio" name="approvalStatus" value="반려"> 반려</label>
          </td>
        </tr>
        <tr>
          <th>결재 의견</th>
          <td>
            <textarea name="opinion" maxlength="200" placeholder="결재 의견을 입력하세요."></textarea>
          </td>
        </tr>
      </table>

      <div class="modal-buttons">
        <button type="button" class="btn-cancel" onclick="closeModal(approvalModal)">취소</button>
        <button type="submit" class="btn-submit">결재</button>
      </div>
    </form>
  </div>
</div>
