/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.massey.cs.webtech.ass2.s15232331.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet(name = "move", urlPatterns = {"/move"})
public class move extends HttpServlet {

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
        try(PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);            
            if(session != null) {
                // Initializes URL values
                Enumeration paraObj = request.getParameterNames();
                String paraName = paraObj.nextElement().toString();
                String paraValue = request.getParameter(paraName);
                
                // Checks whether user input is correct
                if(paraValue.matches("^[x]{1}[1-3]{1}[y]{1}[1-3]{1}$")) {   
                    // Parse user input
                    int newX = Character.getNumericValue(paraValue.charAt(1)) - 1;
                    int newY = Character.getNumericValue(paraValue.charAt(3)) - 1;
                    
                    String[][] myGameBoard = (String[][])request.getSession().getAttribute("board");
                    
                    // Checks whether grid has been previously selected
                    if (myGameBoard[newY][newX].equals("-")) {
                        myGameBoard[newY][newX] = "X";                        
                        
                        int j = 0, i = 0;
                        boolean state = false;
                        while(j < 9) {
                            if(myGameBoard[i][j % 3].equals("-")) {
                                myGameBoard[i][j % 3] = "O";
                                state = true;
                                break;
                            }
                            ++j;
                            if(j % 3 == 0) {
                                ++i;
                            }
                        }
                        if(state == false) {
                            // There are no more moves left to play
                        }
                        request.getSession().setAttribute("board", myGameBoard);
                    } else {
                        out.println("400 Bad Request");
                    }
                } else {
                    out.println("400 Bad Request");
                }
            } else {
                out.println("404 - Not an active session.");
            }
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
