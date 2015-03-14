<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- jQuery library -->
<script type="text/javascript" src="<c:url value='/resources/js/libs/jquery.js'/>"></script>

<script type="text/javascript" src="<c:url value='/resources/js/libs/validationengine.js'/>"></script>
<c:choose>
	<c:when test="${loc eq 'en'}">
		<script type="text/javascript" src="<c:url value='/resources/js/locales/validationengine.en.js'/>"></script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript" src="<c:url value='/resources/js/locales/validationengine.ru.js'/>"></script>
	</c:otherwise>
</c:choose>

<script type="text/javascript" src="<c:url value='/resources/js/bootstrap/bootstrap.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/libs/analitics.js'/>"></script>

<!-- date time picker -->
<script type="text/javascript" src="<c:url value='/resources/js/libs/datetimepicker.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/locales/datetimepicker.ru.js'/>"></script>

<!-- jQuery validate -->
<script type="text/javascript" src="<c:url value='/resources/js/libs/jquery.validate.min.js'/>"></script>
<c:if test="${loc eq 'ru'}">
	<script type="text/javascript" src="<c:url value='/resources/js/locales/messages_ru.js'/>"></script>
</c:if>

<!-- customization js -->
<script type="text/javascript" src="<c:url value='/resources/js/app.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/changes.js'/>"></script>

