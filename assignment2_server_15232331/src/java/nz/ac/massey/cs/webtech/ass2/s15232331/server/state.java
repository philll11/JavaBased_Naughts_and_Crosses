/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.massey.cs.webtech.ass2.s15232331.server;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet(name = "state", urlPatterns = {"/state"})
public class state extends HttpServlet {

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
            HttpSession session = request.getSession(false);
            if(session != null) {
                // Initializes URL value
                Enumeration paraObj = request.getParameterNames();
                String paraName = paraObj.nextElement().toString();
                String paraValue = request.getParameter(paraName);
                
                String[][] myGameBoard = (String[][])request.getSession().getAttribute("board");
                
                // Checks whether png or txt has been requested
                if(paraValue.equals("txt")) {
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    StringBuilder sb = new StringBuilder();
                    int i = 0, j = 0;
                    while(j < 9) {
                        if(myGameBoard[i][j % 3].equals("-")){                                                    
                            sb.append("_");
                        } else {
                            sb.append(myGameBoard[i][j % 3]);                            
                        }
                        sb.append(" ");
                        ++j;
                        if(j % 3 == 0) {
                            sb.append("\n");
                            ++i;
                        }                    
                    }
                    out.println(sb.toString());
                    
                } else {
                    response.setContentType("image/png");                
                    ServletOutputStream out = response.getOutputStream();

                    BufferedImage image = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = image.createGraphics();

                    StringBuilder sb = new StringBuilder();
                    int i = 0, j = 0;
                    while(j < 9) {
                        if(myGameBoard[i][j % 3].equals("-")){                                                    
                            sb.append("_");
                        } else {
                            sb.append(myGameBoard[i][j % 3]);                            
                        }
                        sb.append(" ");
                        ++j;
                        if(j % 3 == 0) {
                            g.setColor(Color.white);
                            g.drawString(sb.toString(), 0, 10 + (10 * i));
                            sb = new StringBuilder();                        
                            ++i;
                        }                    
                    }

                    javax.imageio.ImageIO.write(image, "png", out);

                    g.dispose();
                    out.close();
                }                
            } else {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("404 - Not an active session.");
                out.close();
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
