<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.lang"/>

<html>
<head>
    <jsp:include page="/WEB-INF/views/snippets/head.jsp"/>
    <title><fmt:message key="navbar.catalog"/></title>
</head>
<body class="d-flex flex-column min-vh-100">
<jsp:include page="/WEB-INF/views/snippets/navbar.jsp"/>
<main role="main" class="container">
    <div class="d-flex justify-content-center align-items-center">
        <h1 class="display-3">
            <strong>
                <fmt:message key="tickets"/>
            </strong>
        </h1>
    </div>
    <c:choose>
        <c:when test="${empty requestScope.activeOrders && empty requestScope.expiredOrders }">
            <div class="d-flex justify-content-center align-items-center mb-5">
                <h1 class="display-4 text-info">
                    <span class="badge badge-info"><fmt:message key="tickets.empty"/></span>
                </h1>
            </div>
        </c:when>
        <c:otherwise>
            <div class=" table-responsive">
                <div><fmt:message key="tickets.available"/></div>
                <table class="table table-striped table-hover text-center align-middle">
                    <thead>
                    <tr class="bg-primary">
                        <th scope="col" class="align-middle">№</th>
                        <th scope="col" class="align-middle"><fmt:message key="cart.name"/></th>
                        <th scope="col" class="align-middle"><fmt:message key="cart.date.start"/></th>
                        <th scope="col" class="align-middle"><fmt:message key="cart.date.end"/></th>
                        <th scope="col" class="align-middle"><fmt:message key="cart.theme"/></th>
                        <th scope="col" class="align-middle"><fmt:message key="cart.price"/></th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="tickets" items="${requestScope.activeOrders}"
                               varStatus="counter">
                        <tr>
                            <th scope="row" class="align-middle">${counter.count}</th>
                            <td class="align-middle overflow-text">
                                <c:out value="${tickets.exhibitionId.exhibitionName}"/>
                            </td>
                            <td class="align-middle">
                                <c:out value="${tickets.exhibitionId.dateStart}"/>
                            </td>
                            <td class="align-middle">
                                <c:out value="${tickets.exhibitionId.dateEnd}"/>
                            </td>
                            <td class="align-middle">
                                <c:out value="${tickets.exhibitionId.theme}"/>
                            </td>
                            <td class="align-middle">
                                <c:out value="${tickets.exhibitionId.price}"/>
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <c:if test="${numberOfActivePages gt 1}">
                    <nav aria-label="Page navigation" class="mb-5">
                        <ul class="pagination pagination-lg justify-content-center">
                            <li class="page-item <c:if test="${activePage eq 1}">disabled</c:if>">
                                <a class="page-link"
                                   href="<c:url value="?pill=active&activePage=${activePage - 1}&expiredPage=${expiredPage}"/>"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <c:forEach begin="1" end="${numberOfActivePages}" varStatus="counter">
                                <li class="page-item <c:if test="${activePage eq counter.count}">disabled</c:if>">
                                    <a class="page-link"
                                       href="<c:url value="?pill=active&activePage=${counter.count}&expiredPage=${expiredPage}"/>">
                                            ${counter.count}
                                    </a>
                                </li>
                            </c:forEach>
                            <li class="page-item <c:if test="${activePage eq numberOfActivePages}">disabled</c:if>">
                                <a class="page-link"
                                   href="<c:url value="?pill=active&activePage=${activePage + 1}&expiredPage=${expiredPage}"/>"
                                   aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </c:if>

            </div>

            <div class=" table-responsive">
                <div><fmt:message key="tickets.expired"/></div>
                <table class="table table-striped table-hover text-center align-middle">
                    <thead>
                    <tr class="bg-primary">
                        <th scope="col" class="align-middle">№</th>
                        <th scope="col" class="align-middle"><fmt:message key="cart.name"/></th>
                        <th scope="col" class="align-middle"><fmt:message key="cart.date.start"/></th>
                        <th scope="col" class="align-middle"><fmt:message key="cart.date.end"/></th>
                        <th scope="col" class="align-middle"><fmt:message key="cart.theme"/></th>
                        <th scope="col" class="align-middle"><fmt:message key="cart.price"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="tickets" items="${requestScope.expiredOrders}"
                               varStatus="counter">
                        <tr>
                            <th scope="row" class="align-middle">${counter.count}</th>
                            <td class="align-middle overflow-text">
                                <c:out value="${tickets.exhibitionId.exhibitionName}"/>
                            </td>
                            <td class="align-middle">
                                <c:out value="${tickets.exhibitionId.dateStart}"/>
                            </td>
                            <td class="align-middle">
                                <c:out value="${tickets.exhibitionId.dateEnd}"/>
                            </td>
                            <td class="align-middle">
                                <c:out value="${tickets.exhibitionId.theme}"/>
                            </td>
                            <td class="align-middle">
                                <c:out value="${tickets.exhibitionId.price}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <c:if test="${numberOfExpiredPages gt 1}">
                    <nav aria-label="Page navigation" class="mb-5">
                        <ul class="pagination pagination-lg justify-content-center">
                            <li class="page-item <c:if test="${expiredPage eq 1}">disabled</c:if>">
                                <a class="page-link"
                                   href="<c:url value="?pill=active&activePage=${activePage}&expiredPage=${expiredPage - 1}"/>"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <c:forEach begin="1" end="${numberOfExpiredPages}" varStatus="counter">
                                <li class="page-item <c:if test="${expiredPage eq counter.count}">disabled</c:if>">
                                    <a class="page-link"
                                       href="<c:url value="?pill=active&activePage=${activePage}&expiredPage=${counter.count}"/>">
                                            ${counter.count}
                                    </a>
                                </li>
                            </c:forEach>
                            <li class="page-item <c:if test="${expiredPage eq numberOfExpiredPages}">disabled</c:if>">
                                <a class="page-link"
                                   href="<c:url value="?pill=active&activePage=${activePage}&expiredPage=${expiredPage + 1}"/>"
                                   aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </c:if>

            </div>
        </c:otherwise>
    </c:choose>

</main>
<jsp:include page="/WEB-INF/views/snippets/footer.jsp"/>
</body>
</html>