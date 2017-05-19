/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controle.DisciplinaImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Disciplina;

/**
 *
 * @author Laboratorio
 */
@WebServlet(name = "DisciplinaCrud", urlPatterns = {"/disciplinaCrud"})
public class DisciplinaCrud extends HttpServlet {
    
    public static boolean modalEditar = false;

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
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        
        String operacao = request.getParameter("op");
        DisciplinaImpl dImpl = new DisciplinaImpl();
        
        if(operacao.equals("delete")){           
            dImpl.delete(Integer.valueOf(request.getParameter("codigo")));
        }else if(operacao.equals("post")){
            Disciplina d = new Disciplina();
            d.setCodigo(Integer.valueOf(request.getParameter("codigo")));  
            d.setDescricao(request.getParameter("descricao"));            
            dImpl.post(d);
        }else if(operacao.equals("put")){
            Disciplina d = new Disciplina();
            d.setCodigo(Integer.valueOf(request.getParameter("codigo")));
            d.setDescricao(request.getParameter("descricao"));       
            
            dImpl.put(d, Integer.valueOf(request.getParameter("codigoantigo")));
        }
        
        response.sendRedirect("disciplinas.jsp");
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DisciplinaCrud.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
             processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DisciplinaCrud.class.getName()).log(Level.SEVERE, null, ex);
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

}
