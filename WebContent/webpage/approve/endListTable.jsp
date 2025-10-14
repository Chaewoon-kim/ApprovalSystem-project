<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
{
  "currentPage" : ${currentPage},
  "totalPages" : ${totalPages},
  "success": ${not empty endList},
  "list": [
    <c:forEach var="item" items="${endList}" varStatus="status">
      {
        "draftDate": "${item.draftDate}'",
        "completionDate": "${item.completionDate}'",
        "title": "${item.title}",
        "department": "${item.department}",
        "documentNo": "${item.documentNo}",
        "processStatus": "${item.processStatus}"
      }<c:if test="${!status.last}">,</c:if>
    </c:forEach>
    
  ]
}
