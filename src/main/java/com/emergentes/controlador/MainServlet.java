package com.emergentes.controlador;

import com.emergentes.modelo.Contacto;
import com.emergentes.utiles.ConexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String op;
            op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";
            ArrayList<Contacto> lista = new ArrayList<>();
            ConexionDB canal = new ConexionDB();
            Connection conn = canal.conectar();
            PreparedStatement ps;
            ResultSet rs;

            if (op.equals("list")) {

                String sql = "select *  from productos";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Contacto c =new Contacto();
                    
                    c.setId(rs.getInt("id_producto"));
                    c.setDescripcion(rs.getString("producto"));
                    c.setPrecio(rs.getDouble("precio"));
                    c.setCantidad(rs.getInt("cantidad"));
                    
                    lista.add(c);
                }
                request.setAttribute("lista",lista);
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }
            if (op.equals("nuevo")) {
                Contacto co =new Contacto();
                request.setAttribute("lib",co);
                request.getRequestDispatcher("editar.jsp").forward(request,response);  
            }
              if (op.equals("modificar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                String sql = "select * from productos where id_producto = ?";

                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                com.emergentes.modelo.Contacto pr = new com.emergentes.modelo.Contacto();
                while (rs.next()) {

                    pr.setId(rs.getInt("id_producto"));
                    pr.setDescripcion(rs.getString("producto"));
                    pr.setPrecio(rs.getFloat("precio"));
                    pr.setCantidad(rs.getInt("cantidad"));
                }
                request.setAttribute("lib", pr);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            }
            if (op.equals("eliminar")) {
            int    id=Integer.parseInt(request.getParameter("id"));
                String sql = "delete from productos where id_producto=?";
                ps =conn.prepareStatement(sql);
                ps.setInt(1,id);
                ps.executeUpdate();
                response.sendRedirect("MainServlet");   
                  
            }
        
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  try{

       int   id = Integer.parseInt(request.getParameter("id_producto"));
       String producto = request.getParameter("producto");   
       double precio =Double.parseDouble(request.getParameter("precio"));
       int cantidad=  Integer.parseInt(request.getParameter("cantidad"));
       
        Contacto con =new Contacto();
        
       con.setId(id);
       con.setDescripcion(producto);
       con.setCantidad(cantidad);
       con.setPrecio(precio);
       
       ConexionDB canal = new ConexionDB();
       Connection conn = canal.conectar();
       PreparedStatement ps;
       if(id ==0){
           String sql="insert into productos(producto,precio,cantidad)values(?,?,?)";
          ps = conn.prepareStatement(sql);
          
          ps.setString(1, con.getDescripcion());
          ps.setDouble(2, con.getPrecio());
          ps.setInt(3, con.getCantidad());
             
          ps.executeUpdate();
          response.sendRedirect("MainServlet");
       }
    else {

            //edicion de registro
            String sql = "update productos set producto = ?, precio = ?, cantidad = ? where id_producto = ?";
            try {
                ps = conn.prepareStatement(sql);

                 ps.setString(1, con.getDescripcion());
                ps.setDouble(2, con.getPrecio());
                ps.setInt(3, con.getCantidad());
                ps.setInt(4, con.getId());
                ps.executeUpdate();

            } catch (SQLException ex) {
                System.out.println("error en sql " + ex.getMessage());
            } finally {
                canal.desconectar();
            }
            response.sendRedirect("MainServlet");
        }

       }catch(SQLException e){ 
           System.out.println("Error en SQL "+ e.getMessage());
  }       
    }     
}



























