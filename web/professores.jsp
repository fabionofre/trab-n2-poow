    <%@page import="controle.UnidadeImpl"%>
<%@page import="modelo.Unidade"%>
<%@page import="modelo.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="controle.UsuarioImpl"%>
<%-- 
    Document   : professores.jsp
    Created on : 18/05/2017, 21:22:27
    Author     : Laboratorio
--%>

<%@page import="auxiliar.VariaveisGlobais"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        VariaveisGlobais.tituloNavbar = "Professores";
        UsuarioImpl uImpl = new UsuarioImpl();
        UnidadeImpl unImpl = new UnidadeImpl();
        List<Usuario> usuarios = uImpl.getAll(2);
        List<Unidade> unidades = unImpl.getAll();
    %>
    <body>
        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
            <%@include file="Componentes/navbar.jsp"%>
            <main class="mdl-layout__content">
                <div class="page-content">
                    <% if(!usuarios.isEmpty()) { %>
                    <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" style="position: absolute;left: 37%;top: 25%">
                        <thead>
                          <tr>
                            <th>Nome</th>
                            <th>Sobrenome</th>
                            <th>Ações</th>
                          </tr>
                        </thead>
                        <tbody>
                            <% for(Usuario u: usuarios){%>
                            <tr>
                              <td><%=u.getNome()%></td>
                              <td><%=u.getSobrenome()%></td>
                              <td><i class="material-icons" style="color: #B22222">delete</i> <i class="material-icons" style="color: #6495ED">create</i> </td>
                            </tr> 
                            <% } %>
                        </tbody>
                    </table>
                    <% } %>
                </div>
                    <button id="saveButton" style="position: absolute; top: 85%;left: 90%" class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored">
                        <i class="material-icons">add</i>
                    </button>
                 </main>  
            <dialog id="dialog" class="mdl-dialog">
            <h3 class="mdl-dialog__title">Cadastro de Professores</h3>
            <form action="alunoCrud?op=post&fu=prof" method="post">
                <div class="mdl-dialog__content">
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <input class="mdl-textfield__input" type="text" required id="nome" name="nome">
                            <label class="mdl-textfield__label" for="nome">Nome</label>
                        </div>
                       
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <input class="mdl-textfield__input" required type="text" id="sobrenome" name="sobrenome">
                            <label class="mdl-textfield__label" for="sobrenome">Sobrenome</label>
                        </div>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <input class="mdl-textfield__input" required type="text" id="login" name="login">
                            <label class="mdl-textfield__label" for="login">Login</label>
                        </div>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <input class="mdl-textfield__input" required type="password" id="senha" name="senha">
                            <label class="mdl-textfield__label" for="senha">Senha</label>
                        </div>
                        <div class="mdl-selectfield">
                            <label>Unidade</label>
                            <select class="browser-default" name="unidade">
                                <% for(Unidade un: unidades){
                                %>
                                <option value="<%=un.getId()%>"><%=un.getDescricao()%></option>
                                <%
                                    }
                                %>
                            </select>
                        </div>
                        <div class="mdl-selectfield">
                            <label>Curso</label>
                            <select class="browser-default" name="curso">
                                <option value="1">Sistemas de Informação</option>
                                <option value="2">Redes</option>
                            </select>
                        </div>
                </div>
                <div class="mdl-dialog__actions">
                    <button type="submit" class="mdl-button">Salvar</button>
                </div>
            </form>
        </dialog>
        
        <script type="text/javascript">
            (function() {
                'use strict';
                var dialogButton = document.querySelector('#saveButton');
                var dialogEditButton = document.querySelector('.editButton');
                var dialog = document.querySelector('#dialog');
                var dialogEdit = document.querySelector('#dialogEdit');
                if(dialogEdit)
                    dialogEdit.showModal();
                
                if (! dialog.showModal) {
                  dialogPolyfill.registerDialog(dialog);
                }
                dialogButton.addEventListener('click', function() {
                   dialog.showModal();
                });
                dialog.querySelector('button:not([disabled])')
                .addEventListener('click', function() {
                  dialog.close();
                });               
            }());
        </script>
        </div>
    </body>
</html>
