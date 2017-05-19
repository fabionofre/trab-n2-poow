/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Unidade;

/**
 *
 * @author Laboratorio
 */
public class DisciplinaImpl {
    
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;
    
    public DisciplinaImpl() throws ClassNotFoundException{
        this.conn = ConnectionFactory.getConnection();
    }
    
        public List<Unidade> getAll() {
        List<Unidade> unidades = new ArrayList<>();
        String sql = "Select id, descricao from unidade";
        try {
            stmt = conn.prepareStatement(sql);
            rs  = stmt.executeQuery();
            
            while(rs.next()){
                Unidade un = new Unidade();
                un.setId(rs.getInt(1));
                un.setDescricao(rs.getString(2));

                
                unidades.add(un);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return unidades;
    }
    
}
