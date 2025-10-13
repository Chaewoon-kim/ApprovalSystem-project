<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 날짜 포맷팅 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<tbody id="endListTable">
  <c:forEach var="item" items="${endList}">
    <tr>
      <td><fmt:formatDate value="${item.draftDate}" pattern="yyyy-MM-dd" /></td>
      <td><fmt:formatDate value="${item.completionDate}" pattern="yyyy-MM-dd" /></td>
      <td>${item.title}</td>
      <td>${item.department}</td>
      <td>${item.documentNo}</td>
      <td>
        <button class="flag ${item.processStatus == '완료' ? 'complete' : 'reject'}">
          ${item.processStatus}
        </button>
      </td>
    </tr>
  </c:forEach>
</tbody>

<div class="pagination" id="pagination">
      <c:forEach var="i" begin="1" end="3">
    <a href="#" class="page-link" data-page="${i}">${i}</a>
  </c:forEach>
 </div>
  
