<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
	$(function() {
		$("#btn-check-email")
				.click(
						function() {
							var email = $("#email").val();
							if (email == '') {
								return;
							}

							console.log(email);
							$
									.ajax({
										url : "${pageContext.request.contextPath }/user/api/checkemail?email="
												+ email,
										type : "get",
										dataType : "json",
										error : function(xhr, status, e) {
											console.error(status, e);
										},
										success : function(response) {
											console.log(response);

											if (response.result != "success") {
												console.error(response.message);
												return;
											}

											if (response.data) {
												alert("존재하는 이메일입니다. 다른 이메일을 사용하세요.");
												$("#email").val("");
												$("#email").focus();
												return;
											}

											$("#btn-check").hide();
											$("#img-check").show();
										}
									});
						});
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />


		<div id="content">
			<div id="user">
				<form:form modelAttribute="userVo" id="join-form" name="joinForm" method="post" action="${pageContext.request.contextPath }/user/join">
					<label class="block-label" for="name">이름</label>
					<form:input path="name" />
					<p style="color: #f00; text-align: left; padding-left: 0">
						<spring:hasBindErrors name="userVo">
							<c:if test="${errors.hasFieldErrors('name') }">
								<spring:message code="${errors.getFieldError('name').codes[0] }" />
							</c:if>
						</spring:hasBindErrors>
					</p>

					<form:input path="email" />
					<input type="button" id="btn-check" value="중복체크">
					<img id="img-check" src="${pageContext.request.contextPath }/assets/images/check.png" style="width: 18px; vertical-align: bottom; display: none" />
					<p style="color: #f00; text-align: left; padding-left: 0">
						<form:errors path="email" />
					</p>

					<label class="block-label">
					<spring:message code="user.join.label.password" />
					</label>
					<form:password path="password" />
					<p style="color: #f00; text-align: left; padding-left: 0">
						<form:errors path="password" />
					</p>

					<fieldset>
						<legend>성별</legend>
						<label>여</label>
						<input type="radio" name="gender" value="female" checked="checked">
						<label>남</label>
						<input type="radio" name="gender" value="male">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="가입하기">

				</form:form>
			</div>
		</div>
		<p id="test"></p>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>