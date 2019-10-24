<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Find us</title>
        <link type="text/css" rel="stylesheet" href="public/css/style.css">
        <link href="public/css/findus.css" rel="stylesheet" type="text/css"/>
        <link href="public/css/container.css" rel="stylesheet" type="text/css"/>
        <link href="public/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="public/css/footer.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="wrap">
            <%@include file="component/header.jsp" %>
            <div class="content">
                <div class="main-content">
                    <div class="content-left">
                        <h2>Find us</h2>
                        <div class="info">
                            <div class="contact">
                                <h3>Address and Contact</h3>
                                <p>${address}</p>
                            </div>
                            <div class="tel-mail">
                                <p>
                                    <span>Tel: </span>
                                    ${tel}
                                </p>
                                <p>
                                    <span>Email: </span>
                                    ${mail}
                                </p>
                            </div>
                        </div>
                        <div class="opening">
                            <h3>Opening Hours</h3>
                            <c:forEach var="i" items="${openHours}">
                                <p>${i}</p>
                            </c:forEach>
                        </div>
                        <div class="map">
                            <div class="border-map">
                                <img src="public/image/${map}" />
                            </div>
                        </div>
                    </div>
                    <%@include file="component/container.jsp" %>
                </div>
                <%@include file="component/footer.jsp" %>
            </div>
        </div>
    </body>
</html>
