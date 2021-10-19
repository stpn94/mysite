<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>​
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>​
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<ul>
	<li><a href="${pageContext.request.contextPath}/">이종윤</a></li>
	<li><a href="${pageContext.request.contextPath}/guestbook?a=guestbook">방명록</a></li>
	<li><a href="${pageContext.request.contextPath}/board?a=board">게시판</a></li>
</ul>