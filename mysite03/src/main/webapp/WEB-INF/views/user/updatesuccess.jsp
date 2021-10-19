<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>​
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>​
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/user.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		</div>
		<div id="content">
			<div id="user">
				<p class="jr-success">
					업데이트를 성공했습니다. <br>
					<br>

				</p>
			</div>
		</div>
		<div id="navigation">
			<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		</div>
		<div id="footer">
			<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
		</div>
	</div>
</body>
</html>