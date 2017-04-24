
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<body>
	<c:forEach items="${enti }" var="ente">
		<c:out value="${ente }"></c:out>
		</c:forEach>
</body>