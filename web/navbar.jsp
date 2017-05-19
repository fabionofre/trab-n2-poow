<%-- 
    Document   : navbar.jsp
    Created on : 18/05/2017, 21:15:23
    Author     : Laboratorio
--%>

<%@page import="auxiliar.VariaveisGlobais"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>WebAulas</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
        <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
        <meta charset="ISO-8859-1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <header class="mdl-layout__header">
      <div class="mdl-layout__header-row">
        <!-- Title -->
        <span class="mdl-layout-title"><%=VariaveisGlobais.tituloNavbar%></span>
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
        <a class="mdl-navigation__link" href="disciplinas.jsp">Disciplinas</a>
      </nav>
    </div>
</html>
