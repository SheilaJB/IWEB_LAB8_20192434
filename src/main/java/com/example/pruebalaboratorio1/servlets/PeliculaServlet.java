package com.example.pruebalaboratorio1.servlets;

import com.example.pruebalaboratorio1.beans.genero;
import com.example.pruebalaboratorio1.beans.pelicula;
import com.example.pruebalaboratorio1.beans.streaming;
import com.example.pruebalaboratorio1.daos.listasDao;
import com.example.pruebalaboratorio1.daos.peliculaDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "pelicula-servlet", value = "/listaPeliculas")
public class PeliculaServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String action = request.getParameter("action")== null ? "listar" : request.getParameter("action");
        peliculaDao peliculaDao = new peliculaDao();
        String vista ="";
        //listasDao listaDao = new listasDao();

        switch (action) {
            case "listar":

                ArrayList<pelicula> listaPeliculas = peliculaDao.listarPeliculas();
                ArrayList<genero> listaGeneros = listasDao.listarGeneros();
                ArrayList<streaming> listaStreaming = listasDao.listarStraming();
                request.setAttribute("listaPeliculas", listaPeliculas);
                request.setAttribute("listaGeneros", listaGeneros);
                request.setAttribute("listaStreaming", listaStreaming);

                RequestDispatcher view = request.getRequestDispatcher("listaPeliculas.jsp");
                view.forward(request,response);
                break;
            case "new":

                break;
            case "borrar":

                response.sendRedirect(request.getContextPath()+"/listaPeliculas?action=listar");
                break;

        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String action = request.getParameter("action");
        peliculaDao peliculaDao = new peliculaDao();
        //listasDao listaDao = new listasDao();
        ArrayList<genero> listaGeneros = listasDao.listarGeneros();
        ArrayList<streaming> listaStreaming = listasDao.listarStraming();

        switch (action) {


            case "filtrar":

                RequestDispatcher viewFiltro = request.getRequestDispatcher("listaPeliculas.jsp");
                viewFiltro.forward(request,response);
                break;

            case "editar":


                int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
                String titulo = request.getParameter("titulo");
                String director = request.getParameter("director");
                int anoPublicacion = Integer.parseInt(request.getParameter("anoPublicacion"));
                double rating = Double.parseDouble(request.getParameter("rating"));
                double boxOffice = Double.parseDouble(request.getParameter("boxOffice"));
                String genero = request.getParameter("genero");
                String duracion = request.getParameter("duracion");
                String premioOscar = request.getParameter("premioOscar");
                //Boolean idStreaming = request.getParameter("idStreaming");

                //peliculaDao.editarPelicula(idPelicula, titulo,director,anoPublicacion,rating,boxOffice, genero, duracion, idStreaming, premioOscar);
                //int idPelicula, String titulo, String director, int anoPublicacion, double rating, double boxOffice, String duracion, String streaming, boolean premioOscar
                response.sendRedirect(request.getContextPath()+"/listaPeliculas?action=listar");
                break;


        }
    }




}
