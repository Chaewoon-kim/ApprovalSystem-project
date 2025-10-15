<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.GsonBuilder" %>
<%Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
String result = gson.toJson(request.getAttribute("result"));
out.print(result);%>
