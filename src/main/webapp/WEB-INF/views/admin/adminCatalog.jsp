<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="ua.epam.radchenko.util.type.Status" %>

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
                <fmt:message key="admin.management.catalog"/>
            </strong>
        </h1>
    </div>
    <c:choose>
        <c:when test="${empty requestScope.catalog}">
            <div class="d-flex justify-content-center align-items-center mb-5">
                <h1 class="display-4 ">
                    <span class="badge badge-info"><fmt:message key="admin.management.catalog.empty"/></span>
                </h1>
            </div>
        </c:when>
        <c:otherwise>
            <div class=" table-responsive">
                <table class="table table-striped table-hover text-center align-middle">
                    <thead>
                    <tr class="bg-primary">
                        <th scope="col">№</th>
                        <th scope="col"><fmt:message key="exhibition.name"/></th>
                        <th scope="col"><fmt:message key="exhibition.status"/></th>
                        <th scope="col"><fmt:message key="exhibition.order.amount"/></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="exhibition" items="${requestScope.catalog}" varStatus="counter">
                        <tr>
                            <th scope="row" class="align-middle">${counter.count}</th>
                            <td class="align-middle overflow-text"><c:out value="${exhibition.exhibitionName}"/></td>
                            <td class="align-middle">
                                <c:if test="${exhibition.exhibitionStatus eq Status.ACTIVE}">
                                <span>
                                    <fmt:message key="exhibition.status.active"/>
                                </span>
                                </c:if>
                                <c:if test="${exhibition.exhibitionStatus eq Status.SUSPENDED}">
                                <span>
                                    <fmt:message key="exhibition.status.suspended"/>
                                </span>
                                </c:if>
                            </td>
                            <td class="align-middle">
                                    ${requestScope.visits.get(counter.count - 1)}
                            </td>
                            <td class="align-middle">
                                <form method="post" class="table-form"
                                      action="<c:url value="/app/admin/catalog/change-status"/>">
                                    <input type="hidden" name="exhibitionId" value="${exhibition.exhibitionId}">
                                    <c:choose>
                                        <c:when test="${exhibition.exhibitionStatus eq Status.SUSPENDED}">
                                            <input type="hidden" name="exhibitionStatus" value="active">
                                            <button type="submit"  class="btn btn-link">
                                                <i class="fa fa-check-circle-o fa-lg" aria-hidden="true">&nbsp;</i>
                                                <fmt:message key="admin.management.catalog.activate"/>
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="hidden" name="exhibitionStatus" value="suspended">
                                            <button type="submit"  class="btn btn-link text-warning">
                                                <i class="fa fa-ban fa-lg" aria-hidden="true">&nbsp;</i>
                                                <fmt:message key="admin.management.catalog.suspend"/>
                                            </button>
                                        </c:otherwise>
                                    </c:choose>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <c:if test="${numberOfPages gt 1}">
                <nav aria-label="Page navigation" class="mb-5">
                    <ul class="pagination pagination-lg justify-content-center">
                        <li class="page-item <c:if test="${page eq 1}">disabled</c:if>">
                            <a class="page-link" href="<c:url value="?page=${page - 1}"/>" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <c:forEach begin="1" end="${numberOfPages}" varStatus="counter">
                            <li class="page-item <c:if test="${page eq counter.count}">disabled</c:if>">
                                <a class="page-link" href="<c:url value="?page=${counter.count}"/>">
                                        ${counter.count}
                                </a>
                            </li>
                        </c:forEach>
                        <li class="page-item <c:if test="${page eq numberOfPages}">disabled</c:if>">
                            <a class="page-link" href="<c:url value="?page=${page + 1}"/>" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </c:if>
        </c:otherwise>
    </c:choose>
</main>


<jsp:include page="/WEB-INF/views/snippets/footer.jsp"/>
</body>
</html>