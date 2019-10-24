package com.controller;

import com.entity.Information;
import com.model.InfoModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
//            List<Information> info = model.getInfoPage();
            Map<String, String> info = model.getInfoPage();
            request.setAttribute("address", info.get("address"));
            request.setAttribute("tel", info.get("tel"));
            request.setAttribute("mail", info.get("mail"));
            ArrayList<String> openHours = new ArrayList<>();

            for (Map.Entry<String, String> entry : info.entrySet()) {
                if(entry.getKey().contains("day")){
                    openHours.add(entry.getKey() + ":"+ entry.getValue());
                }
            }
            request.setAttribute("openHours", openHours);
            request.setAttribute("map", info.get("imgCover"));
            request.setAttribute("FindUsColor", "setColor");
            request.getRequestDispatcher("findus.jsp").forward(request, response);
        } catch (Exception ex) {
            response.sendRedirect("error.jsp");
            Logger.getLogger(InfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
