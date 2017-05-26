/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.UsuarioCurso;

/**
 *
 * @author Laboratorio
 */
public interface UsuarioCursoDAO {
    
    public List<UsuarioCurso> getAll();
    public UsuarioCurso get(Integer id);
    public void put(UsuarioCurso uC);
    public void delete(Integer id);
    public void post(UsuarioCurso uC);
    
}
