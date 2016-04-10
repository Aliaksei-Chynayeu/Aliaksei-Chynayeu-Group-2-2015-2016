<%@ include file="tag/tagInclude.jsp" %>

<tiles:importAttribute name="javascripts" />
<tiles:importAttribute name="stylesheets" />

<!DOCTYPE html>

<html lang="en" class="no-js">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="author" content="Aliaksei Hlazkou" />
	
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
	<!-- stylesheets -->
	<c:forEach var="css" items="${stylesheets}">
		<link rel="stylesheet" type="text/css" href="<c:url value="${css}"/>">
	</c:forEach>
	<!-- end stylesheets -->
</head>

<body>
	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footer" />
	
	<!-- scripts -->
	<c:forEach var="script" items="${javascripts}">
		<script src="<c:url value="${script}"/>"></script>
	</c:forEach>
	<!-- end scripts -->
</body>

</html>