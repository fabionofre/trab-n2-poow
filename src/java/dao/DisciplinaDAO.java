/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Disciplina;

/**
 *
 * @author Laboratorio
 */
public interface DisciplinaDAO {
    
    public List<Disciplina> getAll();
    public Disciplina get(Integer id);
    public void put(Disciplina d);
    public void delete(Integer id);
    public void post(Disciplina d);
       
}
