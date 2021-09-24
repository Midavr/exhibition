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
<main role="main" class="container min-vh-100 mb-5">
    <div class="row min-vh-100 justify-content-md-center align-items-center">
        <div class="card w-75 mx-auto">
            <div class="card-header h2 text-center">
                <fmt:message key="admin.create.exhibition"/>
            </div>
            <div class="card-body mx-auto w-100">

                <form accept-charset="UTF-8" role="form" method="post">
                    <input type="hidden" name="exhibitionsId" value="${requestScope.exhibitionDTO.exhibition_id}"/>
                    <input type="hidden" name="exhibitionStatus" value="ACTIVE"/>

                    <div class="form-group">
                        <label for="name">
                            <fmt:message key="exhibition.name"/>
                        </label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend0">
                                    <i class="fa fa-file-text fa-lg" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input type="text" id="name"
                                   name="exhibitionName"
                                   value="<c:out value="${requestScope.exhibitionDTO.exhibition_name}"/>"
                                   class="form-control form-control-lg
                                   <c:if test="${errors.errorExhibitionName}">
                                            is-invalid
                                   </c:if>"
                                   placeholder="<fmt:message key="create.exhibition.name"/>"
                                   required>
                            <c:if test="${errors.errorExhibitionName}">
                                <div class="invalid-feedback">
                                    <fmt:message key="error.exhibition.name"/>
                                </div>
                            </c:if>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="description">
                            <fmt:message key="exhibition.description"/>
                        </label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend1">
                                    <i class="fa fa-file-text fa-lg" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input type="text" id="description"
                                   name="exhibitionDescription"
                                   value="<c:out value="${requestScope.exhibitionDTO.description}"/>"
                                   class="form-control form-control-lg
                                   <c:if test="${errors.errorExhibitionDescription}">
                                            is-invalid
                                   </c:if>"
                                   placeholder="<fmt:message key="create.exhibition.description"/>"
                                   required>
                            <c:if test="${errors.errorExhibitionDescription}">
                                <div class="invalid-feedback">
                                    <fmt:message key="error.exhibition.description"/>
                                </div>
                            </c:if>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="price">
                            <fmt:message key="exhibition.price"/>
                        </label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend2">
                                    <i class="fa fa-file-text fa-lg" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input type="text" id="price"
                                   name="exhibitionPrice"
                                   value="<c:out value="${requestScope.exhibitionDTO.price}"/>"
                                   class="form-control form-control-lg
                                   <c:if test="${errors.errorExhibitionPrice}">
                                            is-invalid
                                   </c:if>"
                                   placeholder="<fmt:message key="create.exhibition.price"/>"
                                   required>
                            <c:if test="${errors.errorExhibitionPrice}">
                                <div class="invalid-feedback">
                                    <fmt:message key="error.exhibition.price"/>
                                </div>
                            </c:if>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="date_start">
                            <fmt:message key="exhibition.date_start"/>
                        </label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend3">
                                    <i class="fa fa-file-text fa-lg" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input type="date" id="date_start"
                                   name="exhibitionDateStart"
                                   value="<c:out value="${requestScope.exhibitionDTO.date_start}"/>"
                                   class="form-control form-control-lg
                                   <c:if test="${errors.errorExhibitionDateStart}">
                                            is-invalid
                                   </c:if>"
                                   placeholder="<fmt:message key="create.exhibition.date_start"/>"
                                   required>
                            <c:if test="${errors.errorExhibitionDateStart}">
                                <div class="invalid-feedback">
                                    <fmt:message key="error.exhibition.date_start"/>
                                </div>
                            </c:if>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="date_end">
                            <fmt:message key="exhibition.date_end"/>
                        </label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend4">
                                    <i class="fa fa-file-text fa-lg" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input type="date" id="date_end"
                                   name="exhibitionDateEnd"
                                   value="<c:out value="${requestScope.exhibitionDTO.date_end}"/>"
                                   class="form-control form-control-lg
                                   <c:if test="${errors.errorExhibitionDateEnd}">
                                            is-invalid
                                   </c:if>"
                                   placeholder="<fmt:message key="create.exhibition.date_end"/>"
                                   required>
                            <c:if test="${errors.errorExhibitionDateEnd}">
                                <div class="invalid-feedback">
                                    <fmt:message key="error.exhibition.date_end"/>
                                </div>
                            </c:if>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="theme">
                            <fmt:message key="exhibition.theme"/>
                        </label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend5">
                                    <i class="fa fa-file-text fa-lg" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input type="text" id="theme"
                                   name="exhibitionTheme"
                                   value="<c:out value="${requestScope.exhibitionDTO.theme}"/>"
                                   class="form-control form-control-lg
                                   <c:if test="${errors.errorExhibitionTheme}">
                                            is-invalid
                                   </c:if>"
                                   placeholder="<fmt:message key="create.exhibition.theme"/>"
                                   required>
                            <c:if test="${errors.errorExhibitionTheme}">
                                <div class="invalid-feedback">
                                    <fmt:message key="error.exhibition.theme"/>
                                </div>
                            </c:if>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="hall">
                            <fmt:message key="exhibition.hall"/>
                        </label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend6">
                                    <i class="fa fa-file-text fa-lg" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input type="text" id="hall"
                                   name="exhibitionHall"
                                   value="<c:out value="${requestScope.exhibitionDTO.hall}"/>"
                                   class="form-control form-control-lg
                                   <c:if test="${errors.errorExhibitionHall}">
                                            is-invalid
                                   </c:if>"
                                   placeholder="<fmt:message key="create.exhibition.hall"/>"
                                   required>
                            <c:if test="${errors.errorExhibitionHall}">
                                <div class="invalid-feedback">
                                    <fmt:message key="error.exhibition.hall"/>
                                </div>
                            </c:if>
                        </div>
                    </div>



                    <div class="form-group text-center">
                        <button type="submit" class="btn btn-primary btn-lg mt-3">
                            <fmt:message key="admin.create.exhibition"/>
                        </button>
                    </div>

                </form>

            </div>
        </div>
    </div>
</main>

<jsp:include page="/WEB-INF/views/snippets/footer.jsp"/>
</body>
</html>