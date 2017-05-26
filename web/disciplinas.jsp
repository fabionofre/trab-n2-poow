<%-- 
    Document   : disciplinas
    Created on : 18/05/2017, 21:25:07
    Author     : Laboratorio
--%>

<%@page import="java.util.List"%>
<%@page import="servlets.DisciplinaCrud"%>
<%@page import="controle.DisciplinaImpl"%>
<%@page import="modelo.Disciplina"%>
<%@page import="auxiliar.VariaveisGlobais"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <body>
            <%
            VariaveisGlobais.tituloNavbar = "Disciplinas";
            DisciplinaImpl dImpl = new DisciplinaImpl();
            Disciplina dEdit = null;
            Boolean modalEditar;
            if(request.getParameter("modalOpen") != null){
                String modalOpen = request.getParameter("modalOpen");
                if(modalOpen.equals("false")){
                    DisciplinaCrud.modalEditar = true;
                    dEdit = dImpl.get(Integer.valueOf(request.getParameter("codigo")));
                }else{
                    DisciplinaCrud.modalEditar = false;
                }
            }else{
                DisciplinaCrud.modalEditar = false;
            }
            List<Disciplina> disciplinas = dImpl.getAll();
        %>
        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
            <%@include file="Componentes/navbar.jsp" %>
             <main class="mdl-layout__content">
                <div class="page-content">
                    <% if(!disciplinas.isEmpty()) { %>
                    <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" style="position: absolute;left: 37%;top: 25%">
                        <thead>
                          <tr>
                            <th>Código</th>
                            <th style="text-align: center">Nome</th>
                            <th>Ações</th>
                          </tr>
                        </thead>
                        <tbody>
                            <% for(Disciplina d: disciplinas){%>
                            <tr>
                                <td><%=d.getCodigo()%></td>
                                <td><%=d.getDescricao()%></td>
                                <td><a href="disciplinaCrud?op=delete&codigo=<%=d.getCodigo()%>"><i class="material-icons" style="color: red">delete</i></a><a href="disciplinas.jsp?modalOpen=false&codigo=<%=d.getCodigo()%>" class="editButton"><i class="material-icons" style="color: blue">create</i></a></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                    <% } %>
                </div> 
                <button id="saveButton" style="position: absolute; top: 85%;left: 90%" class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored">
                    <i class="material-icons">add</i>
                </button>
            </main>                      
        </div>       
       <dialog id="dialog" class="mdl-dialog">
            <h3 class="mdl-dialog__title">Cadastro de Disciplinas</h3>
            <form action="disciplinaCrud?op=post" method="post">
                <div class="mdl-dialog__content">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="text" required id="codigo" name="codigo">
                        <label class="mdl-textfield__label" for="nome">Código</label>
                    </div>
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="text" required id="descricao" name="descricao">
                        <label class="mdl-textfield__label" for="descricao">Nome</label>
                    </div>
                    <div class="mdl-selectfield">
                        <label>Ministrada por</label>
                        <select class="browser-default" name="unidade">
                            <% //for(Professor p: professores){
                            %>
                            <option value="<//%=p.getId()%>"><//%=p.getDescricao()%></option>
                            <%
                                //}
                            %>
                        </select>
                    </div>
                </div>
                <div class="mdl-dialog__actions">
                    <button type="submit" class="mdl-button">Salvar</button>
                </div>
            </form>
        </dialog>
        <% if(DisciplinaCrud.modalEditar) { %>
        <dialog id="dialogEdit" class="mdl-dialog">
            <h3 class="mdl-dialog__title">Editar Disciplina</h3>
            <form action="disciplinaCrud?op=put&codigoantigo=<%=dEdit.getCodigo()%>" method="post">
                <div class="mdl-dialog__content">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" value="<%=dEdit.getCodigo()%>" type="text" id="codigo" name="codigo">
                        <label class="mdl-textfield__label" for="codigo">Código</label>
                    </div>
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" value="<%=dEdit.getDescricao()%>" type="text" id="descricao" name="descricao">
                        <label class="mdl-textfield__label" for="descricao">Nome</label>
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
