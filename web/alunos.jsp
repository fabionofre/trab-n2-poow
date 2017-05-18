<%-- 
    Document   : alunos
    Created on : 08/05/2017, 22:09:14
    Author     : Fabin_000
--%>

<%@page import="servlets.AlunoCrud"%>
<%@page import="modelo.Unidade"%>
<%@page import="controle.UnidadeImpl"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Usuario"%>
<%@page import="controle.UsuarioImpl"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Alunos</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
        <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
        <meta charset="ISO-8859-1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%
            UsuarioImpl uImpl = new UsuarioImpl();
            UnidadeImpl unImpl = new UnidadeImpl();
            Usuario uEdit = null;
            Boolean modalEditar;
            if(request.getParameter("modalOpen") != null){
                String modalOpen = request.getParameter("modalOpen");
                if(modalOpen.equals("false")){
                    AlunoCrud.modalEditar = true;
                    uEdit = uImpl.get(Integer.valueOf(request.getParameter("id")));
                }else{
                    AlunoCrud.modalEditar = false;
                }
            }else{
                AlunoCrud.modalEditar = false;
            }
            List<Usuario> usuarios = uImpl.getAll();
            List<Unidade> unidades = unImpl.getAll();
        %>
        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
            <header class="mdl-layout__header">
              <div class="mdl-layout__header-row">
                <!-- Title -->
                <span class="mdl-layout-title">Alunos</span>
                <!-- Add spacer, to align navigation to the right -->
                <div class="mdl-layout-spacer"></div>
                <!-- Navigation. We hide it in small screens. -->
                <nav class="mdl-navigation mdl-layout--large-screen-only">
                  <a class="mdl-navigation__link" href="">Foto</a>
                  <a class="mdl-navigation__link" href="">Menu</a>
                </nav>
              </div>
            </header>
            <div class="mdl-layout__drawer">
              <span class="mdl-layout-title">Configuração</span>
              <nav class="mdl-navigation">
                <a class="mdl-navigation__link" href="alunos.jsp">Alunos</a>
                <a class="mdl-navigation__link" href="professores.jsp">Professores</a>
                <a class="mdl-navigation__link" href="disciplina.html">Disciplinas</a>
              </nav>
            </div>
            <main class="mdl-layout__content">
                <div class="page-content">
                    <table class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp" style="position: absolute;left: 37%;top: 25%">
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
                              <td><a href="alunoCrud?op=delete&id=<%=u.getId()%>"><i class="material-icons" style="color: red">delete</i></a><a href="alunos.jsp?modalOpen=false&id=<%=u.getId()%>" class="editButton"><i class="material-icons" style="color: blue">create</i></a><td>
                            </tr> 
                          <%}%>
                        </tbody>
                    </table>  
                </div>
                <button id="saveButton" style="position: absolute; top: 85%;left: 90%" class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored">
                    <i class="material-icons">add</i>
                </button>
            </main>
        </div>
        <!-- Conteúdo do modal salvar -->
        <dialog id="dialog" class="mdl-dialog">
            <h3 class="mdl-dialog__title">Cadastro de Alunos</h3>
            <form action="alunoCrud?op=post" method="post">
                <div class="mdl-dialog__content">
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <input class="mdl-textfield__input" type="text" required id="nome" name="nome">
                            <label class="mdl-textfield__label" for="nome">Nome</label>
                        </div>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <input class="mdl-textfield__input" type="text" required id="sobrenome" name="sobrenome">
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
                        <div>
                            <input type="text" id="user">
                            <label for="user">User name</label>
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
                </div>
                <div class="mdl-dialog__actions">
                    <button type="submit" class="mdl-button">Salvar</button>
                </div>
            </form>
        </dialog>
        <!-- Conteúdo do modal editar -->
        <% if(AlunoCrud.modalEditar) { %>
        <dialog id="dialogEdit" class="mdl-dialog">
            <h3 class="mdl-dialog__title">Editar Aluno</h3>
            <form action="alunoCrud?op=put&id=<%=uEdit.getId()%>" method="post">
                <div class="mdl-dialog__content">
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <input class="mdl-textfield__input" value="<%=uEdit.getNome()%>" type="text" id="nome" name="nome">
                            <label class="mdl-textfield__label" for="nome">Nome</label>
                        </div>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <input class="mdl-textfield__input" value="<%=uEdit.getSobrenome()%>" type="text" id="sobrenome" name="sobrenome">
                            <label class="mdl-textfield__label" for="sobrenome">Sobrenome</label>
                        </div>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <input class="mdl-textfield__input" value="<%=uEdit.getLogin()%>" type="text" id="login" name="login">
                            <label class="mdl-textfield__label" for="login">Login</label>
                        </div>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <input class="mdl-textfield__input" value="<%=uEdit.getSenha()%>" type="text" id="senha" name="senha">
                            <label class="mdl-textfield__label" for="senha">Senha</label>
                        </div>
                        <div class="mdl-selectfield">
                            <label>Unidade</label>
                            <select class="browser-default" name="unidade">
                                <% for(Unidade un: unidades){
                                %>
                                <option <%=un.getId() == uEdit.getUnidade().getId() ? "selected": ""%> value="<%=un.getId()%>"><%=un.getDescricao()%></option>
                                <%
                                    }
                                %>
                            </select>
                        </div>
                </div>
                <div class="mdl-dialog__actions">
                    <button type="submit" class="mdl-button">Salvar</button>
                </div>
            </form>
        </dialog>
        <% } %>
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
    </body>
</html>
