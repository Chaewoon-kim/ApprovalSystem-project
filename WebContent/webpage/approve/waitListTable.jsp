<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


{
  "currentPage": ${currentPage},
  "totalPages": ${totalPages},
  "success": ${not empty waitListGson},
  "list": <c:out value="${waitListGson}" escapeXml="false"/>
}
