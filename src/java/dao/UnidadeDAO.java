/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Unidade;

/**
 *
 * @author Fabin_000
 */
public interface UnidadeDAO {
    
    public List<Unidade> getAll();
    public Unidade get(Integer id);
    public void put(Unidade un);
    public void delete(Integer id);
    public void post(Unidade un);
    
}
