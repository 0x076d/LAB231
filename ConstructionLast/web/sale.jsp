<%-- 
    Document   : sale
    Created on : Jan 9, 2019, 9:54:09 PM
    Author     : Vu
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/home.css"/>
        <link rel="stylesheet" href="css/sale.css"/>

    </head>
    <body>
        <div class="center">
            <%@include file="header.jsp" %>

            <div class="main-content">
                <article class="main">
                    <div class="margin">
                        <div class="article">
                            <span class="h2 font-bold">Machines for Sale</span>
                            <c:forEach var="i" begin="0" step="1" end="${Machines.size()-1}">
                                <c:if test="${i<=Machines.size()}">
                                    <section class="info">
                                        <img src="${Machines.get(i).imglink}" class="img-sale"/>
                                        <a class="sale-title" href="DetailController?id=${Machines.get(i).id}">${Machines.get(i).title}</a>
                                        <p>${Machines.get(i).fullDescription}</p>
                                    </section>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="paging">
                        <c:forEach var="i" begin="1" step="1" end="${totalPage}">
                            <c:if test="${page != i}">
                                <a href="SaleController?page=${i}">${i}</a> |
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

