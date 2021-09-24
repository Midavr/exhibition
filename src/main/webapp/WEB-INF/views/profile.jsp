<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="ua.epam.radchenko.util.type.Role" import="ua.epam.radchenko.util.type.Gender" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.lang"/>

<html>
<head>
    <jsp:include page="/WEB-INF/views/snippets/head.jsp"/>
    <title><fmt:message key="navbar.catalog"/></title>
</head>
<body class="d-flex flex-column h-100">
<jsp:include page="/WEB-INF/views/snippets/navbar.jsp"/>
<main role="main" class="container h-100">
    <div class="row h-100 justify-content-md-center align-items-center">
        <div class="card k w-100">
            <div class="row no-gutters ">
                <div class="text-white bg-primary profile-table">
                    <div class="card-header card-title text-center">
                        <h3>
                            <c:out value="${sessionScope.user.login}"/>
                        </h3>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                            <div><fmt:message key="role"/>:</div>
                            <span class="text-muted">
                                    <c:if test="${sessionScope.user.role eq Role.USER}">
                                        <fmt:message key="role.user"/>
                                    </c:if>
                                    <c:if test="${sessionScope.user.role eq Role.ADMIN}">
                                        <fmt:message key="role.admin"/>
                                    </c:if>
                            </span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                            <div><fmt:message key="firstname"/>:</div>
                            <span class="text-muted"><c:out value="${sessionScope.user.firstName}"/></span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                            <div><fmt:message key="lastname"/>:</div>
                            <span class="text-muted"><c:out value="${sessionScope.user.lastName}"/></span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                            <div><fmt:message key="dateofbirth"/>:</div>
                            <span class="text-muted"><c:out value="${sessionScope.user.dateOfBirth}"/></span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                            <div><fmt:message key="gender"/>:</div>
                            <span class="text-muted">
                                <c:if test="${sessionScope.user.gender eq Gender.MALE}">
                                    <fmt:message key="gender.male"/>
                                </c:if>
                                <c:if test="${sessionScope.user.gender eq Gender.FEMALE}">
                                    <fmt:message key="gender.female"/>
                                </c:if>
                            </span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</main>

<jsp:include page="/WEB-INF/views/snippets/footer.jsp"/>
</body>
</html>