/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ConnectionFactory;
import dao.DisciplinaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Disciplina;
import modelo.Unidade;

/**
 *
 * @author Laboratorio
 */
public class DisciplinaImpl implements DisciplinaDAO{
    
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;
    
    public DisciplinaImpl() throws ClassNotFoundException{
        this.conn = ConnectionFactory.getConnection();
    }
        
    @Override
    public List<Disciplina> getAll() {
        List<Disciplina> disciplinas = new ArrayList<>();
        String sql = "Select codigo, descricao from disciplina";
        try {
            stmt = conn.prepareStatement(sql);
            rs  = stmt.executeQuery();
            
            while(rs.next()){
                Disciplina d = new Disciplina();
                d.setCodigo(rs.getInt(1));
                d.setDescricao(rs.getString(2));

                
                disciplinas.add(d);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return disciplinas;
    }
    
    @Override
    public Disciplina get(Integer codigo) {
        String sql = "SELECT * FROM disciplina WHERE codigo = ?";
        Disciplina u = new Disciplina();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();
            
            rs.next();
            u.setCodigo(rs.getInt(1));
            u.setDescricao(rs.getString(2));
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
        return u;
    }

    @Override
    public void put(Disciplina d, Integer codigo) {
       String sql = "UPDATE disciplina set codigo = ?, descricao = ? WHERE codigo = ?";
            try {
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, d.getCodigo());
                stmt.setString(2, d.getDescricao());
                stmt.setInt(3, codigo);

                stmt.execute();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
    }

    @Override
    public void delete(Integer codigo) {
        String sql = "DELETE FROM disciplina WHERE codigo = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codigo);

            stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void post(Disciplina d) {
        String sql = "INSERT INTO disciplina (codigo, descricao) VALUES(?,?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, d.getCodigo());
            stmt.setString(2, d.getDescricao());
            stmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
}
