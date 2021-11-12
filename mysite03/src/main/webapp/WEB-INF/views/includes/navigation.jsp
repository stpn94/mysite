<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<div id="navigation">
			<ul>
				<c:if test='${not empty authUser }'>
					<c:choose>
						<c:when test='${authUser.role == "ADMIN" }'>
							<li><a href="${pageContext.request.contextPath }/admin">관리자 페이지</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.request.contextPath }">${authUser.name }</a></li>
						</c:otherwise>
					</c:choose>
				</c:if>
				<li><a href="${pageContext.request.contextPath }/guestbook">방명록</a></li>
				<li><a href="${pageContext.request.contextPath }/guestbook/spa">방명록(SPA)</a></li>
				<li><a href="${pageContext.request.contextPath }/board">게시판</a></li>
				<li><a href="${pageContext.request.contextPath }/gallery">갤러리</a></li>
			</ul>
		</div>