/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Thalya
 */
public class UsuarioFuncao {
    
    private long idUsuario;
    private int idFuncao;
    
    public UsuarioFuncao(long idUsuario, int idFuncao){
        
        this.idUsuario = idUsuario;
        this.idFuncao = idFuncao;
        
    }

    /**
     * @return the idUsuario
     */
    public long getIdUsuario() {
        return idUsuario;
    }

    /**
     * @return the idFuncao
     */
    public int getIdFuncao() {
        return idFuncao;
    }
    
}
