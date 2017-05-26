/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controle.UnidadeImpl;
import controle.UsuarioImpl;
import modelo.Unidade;
import modelo.Usuario;

/**
 *
 * @author Fabin_000
 */
public class Main {
    
    public static void main(String[] args) throws ClassNotFoundException{
        
        UsuarioImpl uImpl = new UsuarioImpl();
        
        UnidadeImpl unidadeImpl = new UnidadeImpl();
        
        Unidade unidade = new Unidade();
        unidade.setDescricao("UNINORTE");
        //unidadeImpl.post(unidade);
        
        Usuario u = new Usuario();
         /*
        u.setNome("Fábio");
        u.setSobrenome("Onofre");
        u.setLogin("fabionofre");
        u.setSenha("fabio123");
        u.getUnidade().setId(1);
        u.setNome("Fernando");
        u.setSobrenome("Silva");
        u.setLogin("fernando");
        u.setSenha("fernando123");
        u.getUnidade().setId(2);
        u.setNome("Thalya");
        u.setSobrenome("Silva");
        u.setLogin("thalyaa");
        u.setSenha("thalya123");
        u.getUnidade().setId(2);
         */
        
        

        
        
        
        
        
                
        
        
        
        
    }
    
}
