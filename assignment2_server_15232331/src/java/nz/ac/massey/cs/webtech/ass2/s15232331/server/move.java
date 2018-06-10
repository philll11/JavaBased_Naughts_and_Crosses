/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.massey.cs.webtech.ass2.s15232331.server;

import java.io.IOException;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        try {
            // Parse game board from request
            Board myGameBoard = (Board)request.getSession().getAttribute("board");
            String[][] myGameGrid = myGameBoard.getMatrix();

            // Get user X coordinates from request
            Enumeration paraObj = request.getParameterNames();
            String paraName = paraObj.nextElement().toString();
            String paraValue = request.getParameter(paraName);

            // Checks whether the coordinates are between 1 and 3
            if(paraValue.matches("^[x]{1}[0-2]{1}[y]{1}[0-2]{1}$")) {

                // Parses them from string to int
                int x = Character.getNumericValue(paraValue.charAt(1));
                int y = Character.getNumericValue(paraValue.charAt(3));
                if (myGameGrid[y][x].equals("-")) {
                    myGameBoard.applyPosition("X", x, y);                      

                    // Places computer position
                    int j = 0, i = 0;
                    while(j < 9) {
                        if(myGameGrid[i][j % 3].equals("-")) {
                            myGameBoard.applyPosition("O", j % 3, i); 
                            break;
                        }
                        ++j;
                        if(j % 3 == 0) {
                            ++i;
                        }
                    }
                    request.getSession().setAttribute("board", myGameBoard);
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                } 
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (NullPointerException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (NoSuchElementException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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
