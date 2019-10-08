/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.ArticleDAO;
import enity.Article;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SaleController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            ArticleDAO am = new ArticleDAO();
            ArrayList<Article> arr;
            
            int page = 1;
            int pagesize = 3;
            if(request.getParameter("page") != null){
                page = Integer.parseInt(request.getParameter("page"));
            }
            int totalPage = am.getTotalRowsSale();
            if(totalPage % pagesize == 0){
                totalPage = totalPage/pagesize;
            }else{
                totalPage = totalPage/pagesize + 1;
            }
            
            arr= (ArrayList<Article>) am.getArticleInPageHasPageSizeInSale(page, pagesize);
            request.setAttribute("sale", "sale");
            request.setAttribute("Machines", arr);
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.getRequestDispatcher("sale.jsp").forward(request, response);
        }catch(Exception ex){
            System.out.println(ex.getMessage().toString());
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
