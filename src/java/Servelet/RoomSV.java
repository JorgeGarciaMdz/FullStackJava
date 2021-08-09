/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servelet;

import Service.RoomService;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Entity.Room;

/**
 *
 * @author jorge
 */
@WebServlet(name = "RoomSV", urlPatterns = {"/api/v1/room/*"})
public class RoomSV extends HttpServlet {

    private final RoomService rs;
    private final Gson _gson;

    public RoomSV() {
        rs = new RoomService();
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
            out.println("<title>Servlet RoomSV</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RoomSV at " + request.getContextPath() + "</h1>");
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
        }else if( request.getParameter("capacity") != null){
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(_gson.toJson(rs.findByCapacity(Integer.parseInt(request.getParameter("capacity")))));
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
        if ( request.getPathInfo() == null || request.getPathInfo().equals("/")){
            Room r = _gson.fromJson(JsonToObject(request), Room.class);
            rs.create(r);
            if(r.getId() == null ){
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }
    
    @Override
    protected void doPut( HttpServletRequest request, HttpServletResponse reponse)
            throws ServletException, IOException {
        if( request.getPathInfo() == null || request.getPathInfo().equals("/")){
            Room r = _gson.fromJson(JsonToObject(request), Room.class);
            if(!rs.update(r)){
                reponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }
    
    @Override
    protected void doDelete( HttpServletRequest request, HttpServletResponse reponse)
            throws ServletException, IOException {
        if( request.getParameter("id") != null ){
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
    
    private String JsonToObject( HttpServletRequest request) throws IOException{
        StringBuilder buffer = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while( ( line = reader.readLine()) != null){
                buffer.append(line);
            }
            return buffer.toString();
    }

}
