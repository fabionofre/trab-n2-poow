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
import modelo.Usuario;
import dao.UsuarioDAO;

/**
 *
 * @author Fabin_000
 */
public class UsuarioImpl implements UsuarioDAO {
    
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    public UsuarioImpl() throws ClassNotFoundException {
        this.conn = ConnectionFactory.getConnection();
    }

    @Override
    public List getAll() {  
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "Select id, nome, sobrenome, login, senha, id_unidade from usuario";
        try {
            stmt = conn.prepareStatement(sql);
            rs  = stmt.executeQuery();
            
            while(rs.next()){
                Usuario u = new Usuario();
                u.setId(rs.getInt(1));
                u.setNome(rs.getString(2));
                u.setSobrenome(rs.getString(3));
                u.setLogin(rs.getString(4));
                u.setSenha(rs.getString(5));
                u.getUnidade().setId(rs.getInt(6));
                
                usuarios.add(u);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public Usuario get(Integer id) {
               String sql = "SELECT u.id, u.nome, u.sobrenome, u.login, u.senha, un.id, un.descricao FROM usuario u, unidade un"
                + " WHERE u.id_unidade = un.id AND u.id = ?";
        Usuario u = new Usuario();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            rs.next();
            u.setId(rs.getInt(1));
            u.setNome(notNull(rs.getString(2)));
            u.setSobrenome(notNull(rs.getString(3)));
            u.setLogin(notNull(rs.getString(4)));
            u.setSenha(notNull(rs.getString(5)));
           
            
            u.getUnidade().setId(rs.getInt(6));
            u.getUnidade().setDescricao(notNull(rs.getString(7)));
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
        return u;
    }

    @Override
    public void put(Usuario u) {
        String sql = "UPDATE usuario set nome = ?, sobrenome = ?, login = ?, senha = ?, id_unidade = ? WHERE id = ?";
            try {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, u.getNome());
                stmt.setString(2, u.getSobrenome());
                stmt.setString(3, u.getLogin());
                stmt.setString(4, u.getSenha());
                stmt.setInt(5, u.getUnidade().getId());
                stmt.setInt(6, u.getId());

                stmt.execute();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void post(Usuario u) {
        String sql = "INSERT INTO usuario (nome, sobrenome, login, senha, id_unidade) VALUES(?,?,?,?,?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getSobrenome());
            stmt.setString(3, u.getLogin());
            stmt.setString(4, u.getSenha());
            stmt.setInt(5, u.getUnidade().getId());
            
            stmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    public String notNull(String msg){
        return (msg == null? "" : msg);
    }
    
}
