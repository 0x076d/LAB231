package com.controller;

import com.entity.Article;
import com.model.ArticleModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetailsPost extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
        ArticleModel model;
        try {
            model = new ArticleModel();
            String id = request.getParameter("id"); // get id of post
            Article article = model.getDetailsPost(Integer.parseInt(id));
            request.setAttribute("content", article);
            request.setAttribute("urlCover", article);
            request.getRequestDispatcher("detailsPost.jsp").forward(request, response);
        } catch (Exception ex) {
            response.sendRedirect("error.jsp");
            Logger.getLogger(DetailsPost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
