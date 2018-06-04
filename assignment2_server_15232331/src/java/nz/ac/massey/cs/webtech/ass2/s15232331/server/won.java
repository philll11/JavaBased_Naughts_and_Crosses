/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.massey.cs.webtech.ass2.s15232331.server;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "won", urlPatterns = {"/won"})
public class won extends HttpServlet {
    
    // Claculates which player has won
    public String winnerEngine(String[][] gameBoard) {
        if(gameBoard[0][0].equals("X") && gameBoard[0][1].equals("X") && gameBoard[0][2].equals("X")) {
            return "User";
        } else if(gameBoard[1][0].equals("X") && gameBoard[1][1].equals("X") && gameBoard[1][2].equals("X")) {
            return "User";
        } else if(gameBoard[2][0].equals("X") && gameBoard[2][1].equals("X") && gameBoard[2][2].equals("X")) {
            return "User";
        } else if(gameBoard[0][0].equals("X") && gameBoard[1][0].equals("X") && gameBoard[2][0].equals("X")) {
            return "User";
        } else if(gameBoard[0][1].equals("X") && gameBoard[1][1].equals("X") && gameBoard[2][1].equals("X")) {
            return "User";
        } else if(gameBoard[0][2].equals("X") && gameBoard[1][2].equals("X") && gameBoard[2][2].equals("X")) {
            return "User";
        } else if(gameBoard[0][0].equals("X") && gameBoard[1][1].equals("X") && gameBoard[2][2].equals("X")) {
            return "User";
        } else if(gameBoard[0][2].equals("X") && gameBoard[1][1].equals("X") && gameBoard[2][0].equals("X")) {
            return "User";            
        } else if(gameBoard[0][0].equals("O") && gameBoard[0][1].equals("O") && gameBoard[0][2].equals("O")) {
            return "Computer";
        } else if(gameBoard[1][0].equals("O") && gameBoard[1][1].equals("O") && gameBoard[1][2].equals("O")) {
            return "Computer";
        } else if(gameBoard[2][0].equals("O") && gameBoard[2][1].equals("O") && gameBoard[2][2].equals("O")) {
            return "Computer";
        } else if(gameBoard[0][0].equals("O") && gameBoard[1][0].equals("O") && gameBoard[2][0].equals("O")) {
            return "Computer";
        } else if(gameBoard[0][1].equals("O") && gameBoard[1][1].equals("O") && gameBoard[2][1].equals("O")) {
            return "Computer";
        } else if(gameBoard[0][2].equals("O") && gameBoard[1][2].equals("O") && gameBoard[2][2].equals("O")) {
            return "Computer";
        } else if(gameBoard[0][0].equals("O") && gameBoard[1][1].equals("O") && gameBoard[2][2].equals("O")) {
            return "Computer";
        } else if(gameBoard[0][2].equals("O") && gameBoard[1][1].equals("O") && gameBoard[2][0].equals("O")) {
            return "Computer";
        }
        return "None";
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
            HttpSession session = request.getSession(false);
            if(session != null) {
                String[][] myGameBoard = (String[][])request.getSession().getAttribute("board");
                out.println(winnerEngine(myGameBoard));
            } else {
                out.println("404 - Active game not found.");
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
