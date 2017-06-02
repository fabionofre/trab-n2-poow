/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Usuario;

/**
 *
 * @author Fabin_000
 */
public interface UsuarioDAO {
    
    public List<Usuario> getAll(Integer funcao);
    public Usuario get(Integer id);
    public void put(Usuario u);
    public void delete(Integer id);
    public void post(Usuario u, Integer idCurso, Integer idFuncao);
    
}
