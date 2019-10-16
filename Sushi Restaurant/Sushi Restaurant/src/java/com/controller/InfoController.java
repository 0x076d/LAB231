package com.controller;

import com.entity.Information;
import com.model.InfoModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InfoController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            InfoModel model = new InfoModel();
            List<Information> info = model.getInfoPage();
            request.setAttribute("address", model.getAddress(info));
            request.setAttribute("tel", model.getTel(info));
            request.setAttribute("mail", model.getMail(info));
            request.setAttribute("openHours", model.getOpenHours(info));
            request.setAttribute("map", model.getUrlCover(info));
            request.setAttribute("FindUsColor", "setColor");
            request.getRequestDispatcher("findus.jsp").forward(request, response);
        } catch (Exception ex) {
            response.sendRedirect("error.jsp");
            Logger.getLogger(InfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
