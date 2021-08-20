/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servelet;

import Service.ReservationService;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Entity.Reservation;

/**
 *
 * @author jorge
 */
@WebServlet(name = "ReservationSV", urlPatterns = {"/api/v1/reservation/*"})
public class ReservationSV extends HttpServlet {

    private final ReservationService rs;
    private final Gson _gson;

    public ReservationSV() {
        rs = new ReservationService();
        _gson = new Gson();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ReservationSV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReservationSV at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        //processRequest(request, response);
        if (request.getParameter("id") != null) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(_gson.toJson(rs.findById(Integer.parseInt(request.getParameter("id")))));
            out.flush();
            return;
        } else if (request.getParameter("room_id") != null) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(_gson.toJson(rs.findByRoomId(Integer.parseInt(request.getParameter("room_id")))));
            out.flush();
        } else if( request.getParameter("date_from") != null && request.getParameter("date_to") != null && 
                    request.getParameter("id_employee") != null ){
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(_gson.toJson(rs.findByDateAndEmployee(request.getParameter("date_from"), request.getParameter("date_to"),
                    request.getParameter(("id_employee")))));
            out.flush();
        } else if( request.getParameter("date_from") != null && request.getParameter("date_to") != null){
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(_gson.toJson(rs.findByDate(request.getParameter("date_from"), request.getParameter("date_to"))));
            out.flush();
            return;
        } else if (request.getPathInfo() == null || request.getPathInfo().equals("/")) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(_gson.toJson(rs.findAll()));
            out.flush();
            return;
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
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
        //processRequest(request, response);
        if (request.getPathInfo() == null || request.getPathInfo().equals("/")) {
            Reservation r = _gson.fromJson(JsonToObject(request), Reservation.class);
            rs.create(r);
            if (r.getId() == null) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            return;
        }
        if (request.getPathInfo().equals("/data")) {
            DTO_Reservation dto_r = _gson.fromJson(JsonToObject(request), DTO_Reservation.class);
            rs.create(dto_r);
            if (dto_r.getId() != 0) {
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(_gson.toJson(dto_r));
                out.flush();
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse reponse)
            throws ServletException, IOException {
        if (request.getPathInfo() == null || request.getPathInfo().equals("/")) {
            Reservation r = _gson.fromJson(JsonToObject(request), Reservation.class);
            if (!rs.update(r)) {
                reponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse reponse)
            throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            rs.delete(Integer.parseInt(request.getParameter("id")));
        }
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

    private String JsonToObject(HttpServletRequest request) throws IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }
}
