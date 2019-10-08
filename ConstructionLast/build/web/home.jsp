<%-- 
    Document   : home
    Created on : Jan 9, 2019, 8:17:14 PM
    Author     : Vu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/home.css"/>
        <title>Home</title>
    </head>
    <body>
        <div class="center">
            <%@include file="header.jsp" %>

            <div class="main-content">
                <article class="main">
                    <div class="margin">
                        <section class="article">
                            <div>
                                <span class="h2 font-bold">${introarticle.title}</span>
                            </div>
                            <br>
                            <span class="h4 font-bold">${introarticle.shortDescription}</span>
                            <p>${introarticle.fullDescription}</p>
                            <div class="img-intro-border">
                                <img src="${introarticle.imglink}" class="img-intro"/>
                            </div>
                        </section>
                        <section class="article">
                            <c:forEach var="i" begin="0" step="1" end="${articles.size()-1}">
                                <c:if test="${i<=articles.size()}">
                                    <div class="inline">
                                        <div>
                                            <img src="${articles.get(i).imglink}" class="img-home"/>
                                        </div>
                                        <span class="h3 font-bold"><a href="DetailController?id=${articles.get(i).id}">${articles.get(i).title}</a></span>
                                        <p>${articles.get(i).shortDescription}</p>
                                    </div>
                                </c:if>
                                <c:if test="${i == 2}">
                                    <section class="article"></section>
                                    <br>
                                </c:if>
                            </c:forEach>
                        </section>
                    </div>
                    <div class="paging">
                        <c:forEach var="i" begin="1" step="1" end="${totalPage}">
                            <c:if test="${page != i}">
                                <a href="HomeController?page=${i}">${i}</a> |
                            </c:if>
                            <c:if test="${page == i}">
                                ${i} |
                            </c:if>
                        </c:forEach>
                    </div>
                </article>
                <%@include file="aside.jsp" %>
            </div>

            <%@include file="footer.jsp" %>
        </div>
    </body>
</html>
