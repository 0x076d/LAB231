/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enity.Article;
import model.ArticleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            ArticleDAO am = new ArticleDAO();
            ArrayList<Article> arr;
            
            Article a = am.getArticleIntroByType("intro");
            int page = 1;
            int pagesize = 6;
            if(request.getParameter("page") != null){
                page = Integer.parseInt(request.getParameter("page"));
            }
            int totalPage = am.getTotalRowsInHome();
            System.out.println("totalPage IS : "+totalPage);
            System.out.println("page is: "+page);
            if(totalPage % pagesize == 0){
                totalPage = totalPage/pagesize;
            }else{
                totalPage = totalPage/pagesize + 1;
            }
            
            arr = (ArrayList<Article>) am.getArticleInPageHasPageSizeInHome(page, pagesize);
            request.setAttribute("home", "home");
            request.setAttribute("articles", arr);
            request.setAttribute("introarticle", a);
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }catch (Exception e){
            System.out.println(e.getMessage().toString());
            response.sendRedirect("errorpage.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
