
package com.emergentes.controlador;
//erro de tomcat cambiar de javx a jakarta todos los que eran java a hora son jakarta
import com.emergentes.modelo.producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet(name = "PrinControl", urlPatterns = {"/PrinControl"})
public class PrinControl extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       HttpSession ses =  request.getSession();
       
       int id, pos;
       
       if (ses.getAttribute("listprod")== null){
           ArrayList<producto> listaux = new ArrayList<producto>();
       ses.setAttribute("listprod", listaux);
       }
       
       ArrayList<producto> lista = (ArrayList<producto>)ses.getAttribute("listprod");
       
       String op = request.getParameter("op");
       String option = (op != null) ? op : "view";
      
       producto obj1 = new producto();
       
       switch (option){
           case "nuevo":
               request.setAttribute("miproducto", obj1);
               request.getRequestDispatcher("editar.jsp").forward(request, response);
               break;
           case "editar":
               id = Integer.parseInt(request.getParameter("id"));
               pos = buscarIndice(request, id);
               obj1 = lista.get(pos);
               request.setAttribute("miproducto", obj1);
               request.getRequestDispatcher("editar.jsp").forward(request, response);
               break;
           case "eliminar":
               id = Integer.parseInt(request.getParameter("id"));
               pos = buscarIndice(request, id);
               lista.remove(pos);
               response.sendRedirect("index.jsp");
               break;              
           case "view" : 
               //ses.setAttribute("listprod", lista);
               response.sendRedirect("index.jsp");
               break;
    }
}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      HttpSession ses = request.getSession();
      ArrayList<producto> lista = (ArrayList<producto>)ses.getAttribute("listprod"); 
      producto obj1 = new producto();
      int idt;
      
      obj1.setId(Integer.parseInt(request.getParameter("id")));
      obj1.setDescripcion(request.getParameter("descripcion"));
      obj1.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
      obj1.setPrecio(Integer.parseInt(request.getParameter("precio")));
      obj1.setCategoria(request.getParameter("categoria"));
        
      idt = obj1.getId();
      if(idt == 0){
      obj1.setId(ultimoId(request));
      lista.add(obj1);
      }
      else{
      lista.set(buscarIndice(request, idt), obj1);
      }
      response.sendRedirect("index.jsp");
                 
    }

    private int ultimoId(HttpServletRequest request){
    HttpSession ses = request.getSession();
    ArrayList<producto> lista = (ArrayList<producto>)ses.getAttribute("listprod");
    
    int idaux = 0;
    for (producto item : lista){
    idaux  = item.getId();
    }
    return  idaux +1;
    }

    
    private int buscarIndice(HttpServletRequest request, int id){
        
        HttpSession ses = request.getSession();
        ArrayList<producto> lista = (ArrayList<producto>)ses.getAttribute("listprod");
        
        int i = 0 ;
        if(lista.size()>0){
            while(i < lista.size()){
                if(lista.get(i).getId()== id)
                {
                break;
                }
                else {
                i++;
                }
            }
            }
        return i;
        }
    }

