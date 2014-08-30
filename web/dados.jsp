<%-- 
    Document   : dados
    Created on : Aug 30, 2014, 9:33:28 AM
    Author     : jprestes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuario Autenticado</title>
    </head>
    <body>
        <h1>Tokens</h1>
        <h2>Access Token: <c:out value="${sessionScope.tokenInfo.accessToken}" /></h2>
        <h2>Refresh Token: <c:out value="${sessionScope.tokenInfo.refreshToken}" /></h2>
        <hr /><br />
        <h1>Usuario</h1>
        <h2>Email: <c:out value="${sessionScope.userInfo.email}" /></h2>
        <h2>Nome: <c:out value="${sessionScope.userInfo.givenName}" /> &nbsp; <c:out value="${sessionScope.userInfo.familyName}" /></h2>
    </body>
</html>
