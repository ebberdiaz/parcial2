<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.producto"%>
<%
ArrayList<producto> lista = (ArrayList<producto>)session.getAttribute("listprod");

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>SEGUNDO PARCIAL TEM-742</h1>
        
        <h2>Nombre: Ebber Bruno Diaz Montes</h2>
       
        <h2>CI:5963796</h2>
       <br>
        <h1>LISTA DE VIDEO JUEGOS DISPONIBLES</h1>
         <br>
        <a href="PrinControl?op=nuevo">Nuevo Producto</a>
        
        <table border="1">
            <tr>
                <th>ID</th>
                <th>DESCRIPCION</th>
                <th>CANTIDAD</th>
                <th>PRECIO</th>
                <th>CATEGORIA</th>
                <th>EDITAR</th>
                <th>ELIMINAR</th>
            </tr>
            <% 
            if (lista != null){
            for(producto item : lista){
            %>
             <tr>
                <th><%= item.getId()%></th>
                <th><%= item.getDescripcion()%></th>
                <th><%= item.getCantidad()%></th>
                <th><%= item.getPrecio()%></th>
                <th><%= item.getCategoria()%></th>
                <th><a href="PrinControl?op=editar&id=<%= item.getId()%>">Editar</a></th>
                <th><a href="PrinControl?op=eliminar&id=<%= item.getId()%>"
                       onclick = "return confirm ('Esta Seguro de Eliminar el Producto?')">Eliminar</a></th>
            </tr>
            <% 
                    }
             }
            %>
        </table>
    </body>
</html>
