package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controle.UsuarioImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;

/**
 *
 * @author Fabin_000
 */
@WebServlet(urlPatterns = {"/alunoCrud"})
public class AlunoCrud extends HttpServlet {
    
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
        
        Integer idCurso = 1;
        String operacao = request.getParameter("op");
        UsuarioImpl uImpl = new UsuarioImpl();
        Usuario u = new Usuario();
        
        if(operacao.equals("delete")){           
            uImpl.delete(Integer.valueOf(request.getParameter("id")));
        }else if(operacao.equals("post")){
            u.setNome(request.getParameter("nome"));
            u.setSobrenome(request.getParameter("sobrenome"));
            u.setLogin(request.getParameter("login"));
            u.setSenha(request.getParameter("senha"));
            u.getUnidade().setId(Integer.valueOf(request.getParameter("unidade")));
            idCurso = Integer.valueOf(request.getParameter("curso"));
            
        }else if(operacao.equals("put")){
            u.setId(Integer.valueOf(request.getParameter("id")));
            u.setNome(request.getParameter("nome"));
            u.setSobrenome(request.getParameter("sobrenome"));
            u.setLogin(request.getParameter("login"));
            u.setSenha(request.getParameter("senha"));
            u.getUnidade().setId(Integer.valueOf(request.getParameter("unidade")));
            uImpl.put(u);
        }
        
        if(request.getParameter("fu").equals("aluno")){
            uImpl.post(u, idCurso, 1);
            response.sendRedirect("alunos.jsp");
        }else if(request.getParameter("fu").equals("prof")){
            uImpl.post(u, idCurso, 2);
            response.sendRedirect("professores.jsp");
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlunoCrud.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AlunoCrud.class.getName()).log(Level.SEVERE, null, ex);
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
