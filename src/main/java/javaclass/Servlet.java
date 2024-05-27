package javaclass;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Crear una variable de nuevo Usuario
        //para saber si es un Usuario nuevo o
        //antiguo
        boolean nuevoUsu=true;
        //Creamos una variable visitacu que comenzara
        //en 0 no servira como acumulador
        int visitacu=0;
        //Vamos a obtener el arreglo de las cookie
        Cookie[] cookies = req.getCookies();
        //vamos a validar si exite o no la cookie
        if (cookies != null) {
            //es un bucle con nueva funcion cookie
            //que sirve para recorrer toda la informacion
            //dentro de cookie
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("VisitanteRecurente")&& cookie.getValue().equals("si")){
                    nuevoUsu=false;
                }
                //Si encuentra la cookie se obtiene un valor y lo hace en entero
                if(cookie.getName().equals("visitacu")){
                    visitacu= Integer.parseInt(cookie.getValue());
                }

            }
            String mensaje=null;
            if (nuevoUsu) {
                Cookie visitanCookie = new Cookie("VisitanteRecurente", "si");
                resp.addCookie(visitanCookie);
                mensaje="Gracias por ingresar por primera vez";
                visitacu=1;
            }else {
                visitacu++;
                mensaje="Gracias por ingresar nuevamente "+visitacu;

            }
            Cookie visitCountCookie = new Cookie("visitacu", Integer.toString(visitacu));
            resp.addCookie(visitCountCookie);

            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<h1>"+mensaje+"</h1>");


        }



    }
}
