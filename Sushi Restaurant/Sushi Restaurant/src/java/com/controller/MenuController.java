package com.controller;

import com.model.MenuModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class MenuController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        try {
            MenuModel model = new MenuModel();
            int page = 1, pageSize = 2;
            int totalPage = model.getTotalRows();
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            if (totalPage % pageSize == 0) { // calculator total page to show information
                totalPage = totalPage / pageSize;
            } else {
                totalPage = totalPage / pageSize + 1;
            }
            
            if(page > totalPage){
                request.setAttribute("noContent", "No menu here!");
            }else{
                request.setAttribute("menus", model.getMenusFromTo(page, pageSize));
            }          
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("active", "MenuColor");     
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        } catch (Exception ex) {
            response.sendRedirect("error.jsp");
        }
    }
}
