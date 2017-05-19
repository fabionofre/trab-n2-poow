/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ConnectionFactory;
import dao.UnidadeDAO;
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
 * @author Fabin_000
 */
public class UnidadeImpl implements UnidadeDAO{
    
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    public UnidadeImpl() throws ClassNotFoundException {
        this.conn = ConnectionFactory.getConnection();
    }
    
    @Override
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
            Logger.getLogger(UnidadeImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return unidades;
    }

    @Override
    public Unidade get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void put(Unidade un) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void post(Unidade un) {
        String sql = "INSERT INTO unidade (descricao) VALUES(?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, un.getDescricao());
            stmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    
}
