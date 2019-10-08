<%-- 
    Document   : detail
    Created on : Jan 9, 2019, 9:52:40 PM
    Author     : Vu
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/home.css"/>
        <link rel="stylesheet" href="css/about_detail.css"/>
        <title>Detail</title>
    </head>
    <body>
        <div class="center">
            <%@include file="header.jsp" %>

            <div class="main-content">
                <article class="main">
                    <div class="margin">
                        <span class="h2 font-bold">${content.title}</span>
                        <div class="content-detail">
                            <div>
                                <img src="${content.imglink}" class="img-about-detail"/>
                            </div>
                            <p>${content.shortDescription}</p>
                            <p>${content.fullDescription}</p>
                        </div>
                    </div>
                </article>
                <%@include file="aside.jsp" %>
            </div>
            <%@include file="footer.jsp" %>
        </div>
    </body>
</html>

