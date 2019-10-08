/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.MessageDAO;
import context.DBContext;
import enity.Message;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vu
 */
public class MessageController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String message = request.getParameter("message");
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("message", message);

            String errorName = "";
            String errorEmail = "";
            String errorMessage = "";
            int error = 0;

            Pattern name_pattern = Pattern.compile("[a-zA-Z ]{1,50}$");
            Pattern email_pattern = Pattern.compile("^[a-zA-Z0-9-\\+]+(\\.[_a-zA-Z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            Pattern message_pattern = Pattern.compile("[a-zA-Z0-9 ]{1,150}$");

            Matcher message_matcher = message_pattern.matcher(message);
            Matcher name_matcher = name_pattern.matcher(name);
            Matcher email_matcher = email_pattern.matcher(email);
            message = message.trim();
            name = name.trim();
            email = email.trim();

            if (name.equals("")) {
                errorName += "Name cannot be Null!";
                error++;
            } else if (!name_matcher.matches()) {
                errorName += "Name is Invalid!";
                error++;
            }

            if (email.equals("")) {
                errorEmail += "Email cannot be Null!";
                error++;
            } else if (!email_matcher.matches()) {
                errorEmail += "Email is Invalid!";
                error++;
            }

            if (message.equals("")) {
                errorMessage += "Message cannot be Null!";
                error++;
            } else if (!message_matcher.matches()) {
                errorMessage += "Mesage is Invalid!";
                error++;
            }

            if (error == 0 && name != null && email != null && message != null) {
                Message m = new Message();
                m.setName(name);
                m.setEmail(email);
                m.setMessage(message);
                System.out.println("error = " + error);

                new MessageDAO().insertMessage(m);
                String e = "Message is submitted!";
                request.removeAttribute("name");
                request.removeAttribute("email");
                request.removeAttribute("message");
                request.setAttribute("success", e);
                request.getRequestDispatcher("ContactController").include(request, response);
            } else {
                request.setAttribute("errorName", errorName);
                request.setAttribute("errorEmail", errorEmail);
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("ContactController").include(request, response);
            }

        } catch (Exception ex) {
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
