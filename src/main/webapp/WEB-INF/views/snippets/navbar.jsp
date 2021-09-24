<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="ua.epam.radchenko.presentation.util.constants.Views" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="myLib" uri="/WEB-INF/tag/requestedViewTag" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.lang"/>

<c:set var="homeView" scope="page" value="${Views.HOME_VIEW}"/>
<c:set var="catalogView" scope="page" value="${Views.CATALOG_VIEW}"/>
<c:set var="signInView" scope="page" value="${Views.SIGN_IN_VIEW}"/>
<c:set var="signUpView" scope="page" value="${Views.SIGN_UP_VIEW}"/>
<c:set var="cartView" scope="page" value="${Views.CART_VIEW}"/>
<c:set var="ticketsView" scope="page" value="${Views.ORDERS_VIEW}"/>
<c:set var="adminCatalogView" scope="page" value="${Views.ADMIN_CATALOG_VIEW}"/>
<c:set var="adminCreateExhibitionView" scope="page" value="${Views.CREATE_EXHIBITION_VIEW}"/>
<c:set var="currView" scope="page">
    <myLib:viewUri/>
</c:set>

<div class="container head-bg">
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
        <div class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
            <a href="<c:url value="/app/"/>" class="top-logo">
                <img class="top-img" alt="<fmt:message key="navbar.main"/>" src="<c:url value="/src/img/logo.svg"/>">
            </a>
            <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="langDropdownMenuLink" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fa fa-language fa-lg" aria-hidden="true">&nbsp;</i>
                        ${sessionScope.locale.getLanguage().toUpperCase()}
                    </a>
                    <div class="dropdown-menu" aria-labelledby="langDropdownMenuLink">
                        <c:forEach items="${applicationScope.supportedLocales}" var="lang">
                            <a class="dropdown-item" href="?lang=${lang}">${lang.toUpperCase()}</a>
                        </c:forEach>
                    </div>
                </li>
            </ul>
        </div>


        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li>

            </li>
            <li><a href="<c:url value="/app/catalog"/>" class="nav-link px-2 link-secondary"><fmt:message key="navbar.catalog"/></a></li>
            <c:if test="${sessionScope.user.isAdmin()}">
                <li>
                    <a class="nav-link px-2 link-secondary
                            <c:if test="${adminCatalogView.equals(currView)}">
                                active
                            </c:if>"
                       href="<c:url value="/app/admin/catalog"/>">
                        <i class="fa fa-list fa-lg" aria-hidden="true">&nbsp;</i>
                        <fmt:message key="admin.management.catalog"/>
                    </a>
                </li>
                <li>
                    <a class="nav-link px-2 link-secondary
                            <c:if test="${adminCreateExhibitionView.equals(currView)}">
                                active
                            </c:if>"
                       href="<c:url value="/app/admin/catalog/exhibition-create"/>">
                        <i class="fa fa-plus-square fa-lg" aria-hidden="true">&nbsp;</i>
                        <fmt:message key="admin.management.create.exhibition"/>
                    </a>
                </li>
            </c:if>
            <c:if test="${sessionScope.user.isUser()}">
                <li
                        <c:if test="${cartView.equals(currView)}">
                            class="active"
                        </c:if>
                >
                    <a class="nav-link" href="<c:url value="/app/cart"/>">
                        <i class="fa fa-shopping-cart fa-lg" aria-hidden="true">&nbsp;</i>
                        <fmt:message key="cart"/>&nbsp;
                        <span class="badge badge-light">
                                <c:choose>
                                    <c:when test="${not empty sessionScope.shoppingCart}">
                                        ${sessionScope.shoppingCart.items.size()}
                                    </c:when>
                                    <c:otherwise>
                                        0
                                    </c:otherwise>
                                </c:choose>
                            </span>
                    </a>
                </li>
                <li
                        <c:if test="${ticketsView.equals(currView)}">
                            class="active"
                        </c:if>
                >
                    <a class="nav-link" href="<c:url value="/app/orders"/>">
                        <i class="fa fa-home fa-list fa-lg" aria-hidden="true">&nbsp;</i>
                        <fmt:message key="user.tickets"/>
                    </a>
                </li>
            </c:if>
        </ul>



        <div class="col-md-3 text-end">
            <c:if test="${sessionScope.user eq null}">
                <a href="<c:url value="/app/signin"/>" class="btn btn-outline-primary me-2"><i class="fa fa-sign-in fa-lg" aria-hidden="true">&nbsp;</i> <fmt:message key="signin"/></a>
                <a href="<c:url value="/app/signup"/>" class="btn btn-primary"><i class="fa fa-user-plus fa-lg" aria-hidden="true">&nbsp;</i> <fmt:message key="signup"/></a>
            </c:if>
            <c:if test="${not empty sessionScope.user}">
                    <a class="nav-link dropdown-toggle" href="#" id="profileDropdownMenuLink" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fa fa-user-circle-o fa-lg" aria-hidden="true">&nbsp;</i>
                        <c:out value="${sessionScope.user.firstName}"/>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="profileDropdownMenuLink">
                        <a class="dropdown-item
                            <c:if test="${profileView.equals(currView)}">
                                    active
                            </c:if>"
                           href="<c:url value="/app/profile"/>"><fmt:message key="profile"/>
                        </a>
                        <a class="dropdown-item" href="<c:url value="/app/signout"/>"><fmt:message key="signout"/></a>
                    </div>
            </c:if>
        </div>
    </header>
</div>

