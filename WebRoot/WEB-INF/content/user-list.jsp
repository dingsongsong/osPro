<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<body>
	<br>
	<c:forEach items="${items}" var="item">
		<div>
			${item.currentTime}时刻:读者${item.readerCount}|写者${item.writerCount}<br>
			阅览室:<br>
			<c:forEach items="${item.users}" var="user">
				<c:if test="${!empty user.state}">
				${user.state}<br>
				</c:if>
			</c:forEach>
			读者等待队列:
			<c:forEach items="${item.readers}" var="reader">
			${reader.id}&nbsp;&nbsp;
		</c:forEach>
			<br>写者等待队列:
			<c:forEach items="${item.writers}" var="writer">
			${writer.id}&nbsp;&nbsp;
		</c:forEach>
			<br> <br>
		</div>
	</c:forEach>
</body>
</html>
