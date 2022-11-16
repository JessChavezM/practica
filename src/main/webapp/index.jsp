<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Contacto"%>
<%
    ArrayList<Contacto> lista = (ArrayList) request.getAttribute("lista");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Lista de Productos</h1>
        <p><a href="MainServlet?op=nuevo">Nuevo</a></p>
          <table border="1">
                <tr>
                    <th>Id</th>
                    <th>Descripcion</th>              
                    <th>Precio</th>
                     <th>Cantidad</th>
                    
                </tr> 
                <c:forEach var="item" items="${lista}">        
                <tr>
                    <td>${item.id}</td>
                    <td>${item.descripcion}</td>           
                    <td>${item.precio}</td>
                     <td>${item.cantidad}</td>
                      <td>
                        <a href="MainServlet?op=editar&id=${item.id}" >Editar</a>
                    </td> 
                    <td>
                        <a href="MainServlet?op=eliminar&id=${item.id}" onclick="return(confirm('Estas seguro?'))">Eliminar</a>
                    </td>     
                    
                </tr>
                </c:forEach>
          </table>
    </body>
</html>
























