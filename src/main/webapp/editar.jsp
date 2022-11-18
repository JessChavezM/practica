<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Contacto"%>
<%
 Contacto lib = (Contacto)request.getAttribute("lib");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
           <h1>
           
            <c:if test="${lib.id != 0}">Editar Producto</c:if>
            </h1>
        <form action="MainServlet" method="post">
            <input type="hidden" name="id_producto" value="${lib.id}">
          <table>
                <tr>
                    <td>Producto</td>
                    <td><input type="text" name="producto" value="${lib.descripcion}" ></td>
                 </tr>
                  <tr>
                    <td>Precio</td>
                    <td><input type="text" name="precio" value="${lib.precio}" ></td>
                  </tr>
                 <tr>
                    <td>Cantidad</td>
                    <td><input type="text" name="cantidad" value="${lib.cantidad}" ></td>
                 </tr>
                 <tr>
                    <td></td>
                    <td><input type="submit" ></td>
                 </tr>
                 
            </table>
        </form>
    </body>
</html>