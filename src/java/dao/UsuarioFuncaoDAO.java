/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.UsuarioFuncao;

/**
 *
 * @author Laboratorio
 */
public interface UsuarioFuncaoDAO {
    
    public List<UsuarioFuncao> getAll();
    public UsuarioFuncao get(Integer id);
    public void put(UsuarioFuncao uF);
    public void delete(Integer id);
    public void post(UsuarioFuncao uF);
    
}
