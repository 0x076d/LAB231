<%-- 
    Document   : contact
    Created on : Jan 9, 2019, 9:22:32 PM
    Author     : Vu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/contact.css" type="text/css"/>
        <link rel="stylesheet" href="css/home.css" type="text/css"/>
        <title>Contact</title>
    </head>
    <body>
        <div class="center">
            <%@include file="header.jsp" %>
            <div class="main-content">
                <article class="main">
                    <div class="margin">
                        <section class="secction-contact">
                            <div class="font-bold"><span class="h2 ">Contact</span></div>
                            <div class="font-bold"><br><span class="h3">Construction Machinery</span></div>
                            <p>Address : ${content.getAddress()}</p>
                            <br>City : ${content.getCity()}
                            <br>Country : ${content.getCountry()}
                            <br>Tel:${content.getTel()}
                            <br>Email:${content.getEmail()}
                            </br>
                        </section>
                        <div>
                            <form method="get" action="MessageController">
                                <div class="row">
                                    <p>Write your message here. Fill out the form:</p>
                                    <div class="span6">
                                        <input id="name" name="name" placeholder="Write your name here" value="${name}" />
                                    </div>
                                    <div class="span6">
                                        <input id="email" name="email" placeholder="Write your email here" type="text" value="${email}" />
                                    </div>
                                </div>
                                <div class="message row">
                                    <textarea id="msg"  name="message" placeholder="Write your message here" >${message}</textarea>
                                    <div class="buttonright">
                                        <button class="buttton" type="submit">Send - Click here</button>
                                    </div>
                                </div>
                            </form>
                        </div>                              
                        <h3>${errorName}</h3>
                        <h3>${errorEmail}</h3>
                        <h3>${errorMessage}</h3>
                        <h3>${success}</h3>
                    </div>
                </article>
                <%@include file="aside.jsp" %>
            </div>
            <%@include file="footer.jsp" %>
        </div>
    </body>
</html>
