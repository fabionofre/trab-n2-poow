/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.UsuarioCursoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.UsuarioCurso;

/**
 *
 * @author Laboratorio
 */
public class UsuarioCursoImpl implements UsuarioCursoDAO {
    
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    @Override
    public List<UsuarioCurso> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioCurso get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void put(UsuarioCurso uC) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void post(UsuarioCurso uC) {       
        String sql = "INSERT INTO UsuarioCurso (id_usuario, id_curso) VALUES(?,?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, uC.getIdUsuario());
            stmt.setInt(2, uC.getIdCurso());
            stmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioCursoImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
    }

    
    
}
