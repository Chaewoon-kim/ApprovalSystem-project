<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
{
	"employees" : [<c:forEach items="${employeeList}" var="employeeVo" varStatus="status">
		{
			"employeeId": "${employeeVo.employeeId}",
			"name": "${employeeVo.name}",
			"department": "${employeeVo.department}",
			"rank": "${employeeVo.rank}"
		}<c:if test="${!status.last}">,</c:if></c:forEach>	
	]
}