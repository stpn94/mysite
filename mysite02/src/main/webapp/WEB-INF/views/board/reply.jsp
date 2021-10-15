<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>​
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>​
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/includes/header.jsp" />
		</div>
		
	
		
		<div id="content">
			<div id="board">
				<form class="board-form" method="post" action="${pageContext.request.contextPath}/board">
					<input type = "hidden" name = "a" value="reply">
					<input type = "hidden" name = "no" value="${vo.no}">
					<input type = "hidden" name = "groupNo" value="${vo.groupNo}">
					<input type = "hidden" name = "orderNo" value="${vo.orderNo}">
					<input type = "hidden" name = "depth" value="${vo.depth}">

					
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글쓰기</th>
					
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value=""></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<textarea id="content" name="contents"></textarea>
							</td>
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.request.contextPath}/board?a=board">취소</a>
						<input type="submit" value="등록">
					</div>
				</form>				
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