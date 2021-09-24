<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="ua.epam.radchenko.util.type.Gender" %>
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
                <fmt:message key="signup"/>
            </div>
            <div class="card-body mx-auto w-100">
                <form accept-charset="UTF-8" role="form" method="post">

                    <div class="form-group">
                        <div class="col-auto mr-auto w-75">
                        <label for="login"><fmt:message key="login"/></label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend0">
                                    <i class="fa fa-envelope fa-lg" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input type="text" id="login"
                                   name="login"
                                   value="<c:out value="${requestScope.userDTO.login}"/>"
                                   class="form-control form-control-lg
                                   <c:if test="${errors.errorLogin}">
                                            is-invalid
                                   </c:if>"
                                   placeholder="<fmt:message key="login.placeholder"/>" required>
                            <c:if test="${errors.errorLogin}">
                                <div class="invalid-feedback">
                                    <fmt:message key="error.login"/>
                                </div>
                            </c:if>
                        </div>
                        </div>
                    </div>

                    <div class="form-group form-row justify-content-center">
                        <div class="col-auto mr-auto w-75">
                            <label for="firstName"><fmt:message key="firstname"/></label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="inputGroupPrepend">
                                        <i class="fa fa-user fa-2x" aria-hidden="true"></i>
                                    </span>
                                </div>
                                <input type="text" id="firstName"
                                       name="firstName"
                                       value="<c:out value="${requestScope.userDTO.firstName}"/>"
                                       class="form-control form-control-lg
                                       <c:if test="${errors.errorFirstName}">
                                            is-invalid
                                       </c:if>"
                                       placeholder="<fmt:message key="firstname.placeholder"/>"
                                       aria-describedby="inputGroupPrepend" required>
                                <c:if test="${errors.errorFirstName}">
                                    <div class="invalid-feedback">
                                        <fmt:message key="error.firstname"/>
                                    </div>
                                </c:if>
                            </div>
                        </div>

                        <div class="col-auto ml-auto w-75">
                            <label for="lastName"><fmt:message key="lastname"/></label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="inputGroupPrepend2">
                                        <i class="fa fa-user fa-2x" aria-hidden="true"></i>
                                    </span>
                                </div>
                                <input type="text" id="lastName"
                                       name="lastName"
                                       value="<c:out value="${requestScope.userDTO.lastName}"/>"
                                       class="form-control form-control-lg
                                       <c:if test="${errors.errorLastName}">
                                            is-invalid
                                       </c:if>"
                                       placeholder="<fmt:message key="lastname.placeholder"/>"
                                       aria-describedby="inputGroupPrepend" required>
                                <c:if test="${errors.errorLastName}">
                                    <div class="invalid-feedback">
                                        <fmt:message key="error.lastname"/>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-auto mr-auto w-75">
                        <label for="password"><fmt:message key="password"/></label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend3">
                                    <i class="fa fa-lock fa-2x" aria-hidden="true"></i>
                                </span>
                            </div>
                            <input type="password" id="password"
                                   name="password"
                                   class="form-control form-control-lg
                                   <c:if test="${errors.errorPassword}">
                                            is-invalid
                                   </c:if>"
                                   minlength="5"
                                   placeholder="<fmt:message key="password.placeholder"/>" required>
                            <c:if test="${errors.errorPassword}">
                                <div class="invalid-feedback">
                                    <fmt:message key="error.password"/>
                                </div>
                            </c:if>
                        </div>
                        </div>
                    </div>

                    <div class="form-group form-row">
                        <div class="col-auto mr-auto w-75">
                            <label for="gender"><fmt:message key="gender"/></label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend4">
                                    <i class="fa fa-venus-mars fa-lg" aria-hidden="true"></i>
                                </span>
                                </div>
                                <select id="gender"
                                        name="gender"
                                        class="form-control form-control-lg">
                                    <option value="MALE"
                                            <c:if test="${requestScope.userDTO.gender eq Gender.MALE}">
                                                selected
                                            </c:if>
                                    >
                                        <fmt:message key="gender.male"/>
                                    </option>
                                    <option value="FEMALE"
                                            <c:if test="${requestScope.userDTO.gender eq Gender.FEMALE}">
                                                selected
                                            </c:if>
                                    >
                                        <fmt:message key="gender.female"/>
                                    </option>
                                </select>
                            </div>
                        </div>

                        <div class="col-auto mr-auto w-75">
                            <label for="dateOfBirth"><fmt:message key="dateofbirth"/></label>

                            <div class="input-group">
                                <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend5">
                                    <i class="fa fa-calendar fa-lg" aria-hidden="true"></i>
                                </span>
                                </div>
                                <input type="date" id="dateOfBirth"
                                       name="dateOfBirth"
                                       value="<c:out value="${requestScope.userDTO.dateOfBirth}"/>"
                                       class="form-control form-control-lg"
                                       max="3000-12-31" min="1900-01-01" required>
                            </div>
                        </div>

                    </div>

                    <div class="form-group text-center">
                        <button type="submit" class="btn btn-primary btn-lg mt-3"><fmt:message key="signup"/></button>
                    </div>

                </form>
            </div>
            <c:if test="${errors.errorRegistration}">
                <div class="card-footer text-muted">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <fmt:message key="error.registration"/>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</main>

<jsp:include page="/WEB-INF/views/snippets/footer.jsp"/>
</body>
</html>