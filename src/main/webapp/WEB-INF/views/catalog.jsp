<%--@elvariable id="numberOfPages" type="java.lang.Integer"--%>
<%--@elvariable id="page" type="java.lang.Integer"--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="ua.epam.radchenko.util.type.Status" %>
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

<div class="container">
    <div class="row">
        <div class="col-md-3"><fmt:message key="exhibition.sortby"/>:
            <select class="custom-select d-block" onchange="location = this.value;">
                <option <c:if test="${activeSortingType eq 'PRICE'}">hidden selected</c:if> value="<c:url value="?page=1&way=${activeSortingWay}
											&type=PRICE&date=${activeSortingDate}"/>"><fmt:message key="exhibition.price"/></option>
                <option <c:if test="${activeSortingType eq 'THEME'}">hidden selected</c:if> value="<c:url value="?page=1&way=${activeSortingWay}
											&type=THEME&date=${activeSortingDate}"/>"><fmt:message key="exhibition.theme"/></option>
            </select>
        </div>

        <div class="col-md-3"><fmt:message key="exhibition.order"/>:
            <select class="custom-select d-block" onchange="location = this.value;">
                <option <c:if test="${activeSortingWay eq 'DESC'}">hidden selected</c:if> value="<c:url value="?page=1&way=DESC
											&type=${activeSortingType}&date=${activeSortingDate}"/>"><fmt:message key="desc"/></option>
                <option <c:if test="${activeSortingWay eq 'ASC'}">hidden selected</c:if> value="<c:url value="?page=1&way=ASC
											&type=${activeSortingType}&date=${activeSortingDate}"/>"><fmt:message key="asc"/></option>
            </select>
        </div>

        <div class="col-md-3">
            <fmt:message key="exhibition.sort.date"/>:
            <form class="input-group">
                <input hidden name="page" value="1">
                <input hidden name="way" value="${activeSortingWay}">
                <input hidden name="type" value="${activeSortingType}">
                <input class="form-control" name="date" type="date" required>
                <div class="input-group-append">
                    <button class="btn btn-secondary" type="submit" id="submit"><fmt:message key="exhibition.sort"/></button>
                </div>
            </form>
        </div>
        <div class="col-md-3">
            <fmt:message key="exhibition.reset.text"/>:
            <a class="btn btn-secondary d-block" href="?"><fmt:message key="exhibition.reset"/></a>
        </div>
    </div>


<c:if test="${empty requestScope.catalog}">
    <div class="d-flex justify-content-center align-items-center mb-5">
        <h1 class="display-4 text-info">
            <span class="badge badge-info"><fmt:message key="exhibition.empty"/></span>
        </h1>
    </div>
</c:if>
<c:forEach var="exhibition" items="${requestScope.catalog}" varStatus="counter">
    <div class="card border-dark mb-5 catalog-table" id="exhibition-${exhibition.exhibitionId}">
        <div class="row no-gutters ">
            <div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div><fmt:message key="exhibition.name"/>:</div> <span class="text-muted"><c:out value="${exhibition.exhibitionName}"/></span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div><fmt:message key="exhibition.description"/>:</div> <span class="text-muted"><c:out value="${exhibition.description}"/></span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div><fmt:message key="exhibition.price"/>:</div> <span class="text-muted"><c:out value="${exhibition.price}"/></span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div><fmt:message key="exhibition.date_start"/>:</div> <span class="text-muted"><c:out value="${exhibition.dateStart}"/></span></li>
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div><fmt:message key="exhibition.date_end"/>:</div> <span class="text-muted"><c:out value="${exhibition.dateEnd}"/></span></li>
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div><fmt:message key="exhibition.theme"/>:</div> <span class="text-muted"><c:out value="${exhibition.theme}"/></span></li>
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div><fmt:message key="exhibition.hall"/>:</div> <span class="text-muted"><c:out value="${exhibition.hall}"/></span></li>
                </ul>
                <c:if test="${sessionScope.user.isUser()}">
                    <div class="card-footer d-flex justify-content-sm-center justify-content-lg-end ">
                        <c:if test="${!isExpired.get(counter.count - 1)}">
                            <c:if test="${exhibition.exhibitionStatus eq Status.ACTIVE}">
                                <form accept-charset="UTF-8" role="form" method="post"
                                      action="<c:url value="/app/cart/add"/>">
                                    <div class="input-group ">
                                        <input type="hidden" class="form-control" name="exhibitionId"
                                               value="${exhibition.exhibitionId}">
                                        <button type="submit" class="btn btn-info">
                                            <i class="fa fa-shopping-cart fa-lg" aria-hidden="true">&nbsp;</i>
                                            <fmt:message key="exhibition.add.to.cart"/>
                                        </button>
                                    </div>
                                </form>
                            </c:if>
                            <c:if test="${exhibition.exhibitionStatus eq Status.SUSPENDED}">
                                <fmt:message key="exhibition.suspended"/>
                            </c:if>
                        </c:if>
                        <c:if test="${isExpired.get(counter.count - 1)}">
                            <fmt:message key="exhibition.ended"/>
                        </c:if>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</c:forEach>



<c:if test="${numberOfPages gt 1}">
    <nav aria-label="Page navigation" class="mb-5">
        <ul class="pagination pagination-lg justify-content-center">
            <li class="page-item <c:if test="${page eq 1}">disabled</c:if>">
                <a class="page-link" href="<c:url value="?page=${page - 1}&way=${activeSortingWay}
											&type=${activeSortingType}&date=${activeSortingDate}"/>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:forEach begin="1" end="${numberOfPages}" varStatus="counter">
                <li class="page-item <c:if test="${page eq counter.count}">disabled</c:if>">
                    <a class="page-link" href="<c:url value="?page=${counter.count}&way=${activeSortingWay}
											&type=${activeSortingType}&date=${activeSortingDate}"/>">
                            ${counter.count}
                    </a>
                </li>
            </c:forEach>
            <li class="page-item <c:if test="${page eq numberOfPages}">disabled</c:if>">
                <a class="page-link" href="<c:url value="?page=${page + 1}&way=${activeSortingWay}
											&type=${activeSortingType}&date=${activeSortingDate}"/>" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</c:if>
</div>
<jsp:include page="/WEB-INF/views/snippets/footer.jsp"/>
</body>