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
import java.sql.Statement;
import modelo.UsuarioCurso;
import modelo.UsuarioFuncao;

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
    public List getAll(Integer funcao) {  
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id, nome, sobrenome, login, id_unidade FROM usuario u, usuario_funcao  ufuncao WHERE u.ativo = 1 AND u.id = ufuncao.id_usuario and ufuncao.id_funcao="+funcao;
        try {
            stmt = conn.prepareStatement(sql);
            rs  = stmt.executeQuery();
            
            while(rs.next()){
                Usuario u = new Usuario();
                u.setId(rs.getInt(1));
                u.setNome(rs.getString(2));
                u.setSobrenome(rs.getString(3));
                u.setLogin(rs.getString(4));
                u.getUnidade().setId(rs.getInt(5));
                
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
                + " WHERE u.id_unidade = un.id AND u.id = ? AND u.ativo = 1";
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
        String sql = "UPDATE usuario set nome = ?, sobrenome = ?, login = ?, id_unidade = ? WHERE id = ?";
            try {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, u.getNome());
                stmt.setString(2, u.getSobrenome());
                stmt.setString(3, u.getLogin());
                stmt.setInt(4, u.getUnidade().getId());
                stmt.setInt(5, u.getId());

                stmt.execute();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
    }

    @Override
    public void delete(Integer id) {
        String sql = "UPDATE usuario SET ativo = 0 where id = ?";
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
    public void post(Usuario u, Integer idCurso, Integer idFuncao) {
        String sql = "INSERT INTO usuario (nome, sobrenome, login, senha, id_unidade, ativo) VALUES(?,?,?,sha2(?,224),?, 1)";
        long idUsuario = 0;
        try {
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getSobrenome());
            stmt.setString(3, u.getLogin());
            stmt.setString(4, u.getSenha());
            stmt.setInt(5, u.getUnidade().getId());
            stmt.executeUpdate();
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idUsuario = generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
                UsuarioCursoImpl uCursoImpl = new UsuarioCursoImpl();
                UsuarioFuncaoImpl uFuncaoImpl = new UsuarioFuncaoImpl();
                UsuarioFuncao uF = new UsuarioFuncao(idUsuario, idFuncao);
                uFuncaoImpl.post(uF);
                UsuarioCurso uC = new UsuarioCurso(idUsuario, idCurso);
                uCursoImpl.post(uC);
            }catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    public String notNull(String msg){
        return (msg == null? "" : msg);
    }
    
}
