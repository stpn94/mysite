<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>​
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>​
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>

	<div id="container">
			<div id="header">
			<c:import url="/WEB-INF/views/includes/header.jsp" />
		</div>
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${title}</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content" style ="white-space:pre-line">
		
								   ${contents}
								
							</div>
							
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="${pageContext.request.contextPath}/board?a=list">글목록</a>
					
				<c:choose>
					<c:when test="${authUser.no eq userNo}">
						<div class="bottom">
				
							<a href="${pageContext.request.contextPath}/board?a=modifyform&no=${no}"
								id="new-book">글수정</a>								
						</div>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${not empty authUser}">
			
				     <a href="${pageContext.request.contextPath}/board?a=replyform&no=${no}">답글 달기</a>						
					</c:when>
				</c:choose>
				
				
						 
				</div>
			</div>
		</div>
	
		<div id="navigation">
			<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		</div>
		<div id="footer">
			<c:import url="/WEB-INF/views/includes/footer.jsp" />
		</div>

	</div>
</body>
</html>