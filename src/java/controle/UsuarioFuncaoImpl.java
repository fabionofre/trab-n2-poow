/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ConnectionFactory;
import dao.UsuarioFuncaoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.UsuarioFuncao;

/**
 *
 * @author Laboratorio
 */
public class UsuarioFuncaoImpl implements UsuarioFuncaoDAO{
    
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;
    
    public UsuarioFuncaoImpl() throws ClassNotFoundException {
        this.conn = ConnectionFactory.getConnection();
    }

    @Override
    public List<UsuarioFuncao> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioFuncao get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void put(UsuarioFuncao uF) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void post(UsuarioFuncao uF) {
        String sql = "INSERT INTO usuario_funcao (id_usuario, id_funcao) VALUES(?,?)";
        try {       
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, uF.getIdUsuario());
            stmt.setInt(2, uF.getIdFuncao());
            stmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioCursoImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
}
