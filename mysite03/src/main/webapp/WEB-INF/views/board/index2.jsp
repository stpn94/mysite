<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/board" method="get">
					<input type="text" id="kwd" name="kwd" value="${keyword }">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${map.list }" var="vo" varStatus="status">
						<tr>
							<!-- 번호 -->
							<!-- 총 게시글수 - (현재페이지-1)*(리스팅되는 게시물의 수)-반복카운트 -->
							<td>${map.totalCount - (map.currentPage - 1)* map.listSize - status.index }</td>
							<!-- 제목 -->
							<!-- 총 게시글수 - (현재페이지-1)*(리스팅되는 게시물의 수)-반복카운트 -->
							<c:choose>
								<c:when test="${vo.depth > 0 }">
									<td class="left" style="text-align:left; padding-left:${20*vo.depth }px">
										<img src="${pageContext.request.contextPath }/assets/images/reply.png"> <a href="${pageContext.request.contextPath }/board/view/${vo.no }?p=${map.currentPage }&kwd=${map.keyword }">${vo.title }</a>
									</td>
								</c:when>
								<c:otherwise>
									<td class="left" style="text-align: left">
										<a href="${pageContext.request.contextPath }/board/view/${vo.no }?p=${map.currentPage }&kwd=${map.keyword }">${vo.title }</a>
									</td>
								</c:otherwise>
							</c:choose>
							<td>${vo.userName }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>
							<td>
								<c:choose>
									<c:when test="${not empty authUser && authUser.no == vo.userNo }">
										<a href="${pageContext.request.contextPath }/board/delete/${vo.no }?p=${map.currentPage }&kwd=${map.keyword }" class="del" style="background-image:url(${pageContext.request.contextPath }/assets/images/recycle.png)">삭제</a>
									</c:when>
									<c:otherwise>
										&nbsp;
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</table>
				<div class="pager">
					<ul>
						<!-- 이전 블럭 가장 높은 페이지가 0보다 크면 ◀(전블록 이동 이벤트 이미지) 추가 -->
						<c:if test="${map.prevPage > 0 }">
							<li><a href="${pageContext.request.contextPath }/board?p=${map.prevPage }&kwd=${map.keyword }">◀</a></li>
						</c:if>
						<!-- (현재블럭의 가장 낮은 페이지) 부터 (현재블럭의 가장낮은 페이지) + (리스팅되는 게시물의 수) - 1 만큼 반복한다.-->
						<c:forEach begin="${map.beginPage }" end="${map.beginPage + map.listSize - 1 }" var="page">
							<c:choose>
								<!-- (현재블럭의 가장 높은 페이지)< 현재페이지 -->
								<c:when test="${map.endPage < page }">
									<li>${page }</li>
								</c:when>
								<!-- (현재블럭의 가장 높은 페이지)< 현재페이지 -->
								<c:when test="${map.currentPage == page }">
									<li class="selected">${page }</li>
								</c:when>
								<c:otherwise>
									<li><a href="${pageContext.request.contextPath }/board?p=${page }&kwd=${map.keyword }">${page }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<!-- 다음 블럭 가장 작은 페이지가 0보다 크면 ▶(다음블록 이동 이벤트 이미지) 추가 -->
						<c:if test="${map.nextPage > 0 }">
							<li><a href="${pageContext.request.contextPath }/board?p=${map.nextPage }&kwd=${map.keyword }">▶</a></li>
						</c:if>
					</ul>
				</div>
				<div class="bottom">
					<c:if test="${not empty authUser }">
						<a href="${pageContext.request.contextPath }/board/write?p=${map.currentPage }&kwd=${map.keyword }" id="new-book">글쓰기</a>
					</c:if>
				</div>
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>