package com.example.pruebalaboratorio1.daos;

import com.example.pruebalaboratorio1.beans.genero;
import com.example.pruebalaboratorio1.beans.pelicula;
import com.example.pruebalaboratorio1.beans.streaming;

import java.sql.*;
import java.util.ArrayList;

public class peliculaDao extends baseDao{

    public ArrayList<pelicula> listarPeliculas(){

        ArrayList<pelicula> listaPeliculas = new ArrayList<>();


        try {
            Connection conn =this.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "SELECT A.*, B.NOMBRE, C.NOMBRESERVICIO FROM  \n" +
                    "(SELECT * FROM PELICULA ) AS A \n" +
                    "INNER JOIN \n" +
                    "(SELECT * FROM GENERO) AS B\n" +
                    "ON A.IDGENERO = B.IDGENERO\n" +
                    "INNER JOIN \n" +
                    "(SELECT * FROM STREAMING) AS C\n" +
                    "ON A.IDSTREAMING = C.IDSTREAMING\n" +
                    "ORDER BY RATING DESC , BOXOFFICE DESC";


            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                pelicula movie = new pelicula();
                genero genero = new genero();
                streaming streaming = new streaming();
                int idPelicula = rs.getInt(1);
                movie.setIdPelicula(idPelicula);
                String titulo = rs.getString("titulo");
                movie.setTitulo(titulo);
                String director = rs.getString("director");
                movie.setDirector(director);
                int anoPublicacion = rs.getInt("anoPublicacion");
                movie.setAnoPublicacion(anoPublicacion);
                double rating = rs.getDouble("rating");
                movie.setRating(rating);
                double boxOffice = rs.getDouble("boxOffice");
                movie.setBoxOffice(boxOffice);
                int idGenero = rs.getInt("idGenero");
                String nombregenero = rs.getString("nombre");
                movie.setDuracion(rs.getString("duracion"));
                movie.setStreaming(rs.getString("nombreServicio"));
                movie.setPremioOscar(rs.getBoolean("premioOscar"));
                movie.setGenero(nombregenero);
                //boolean validador= validarBorrado(movie);
                //movie.setValidadorBorrado(validador);
                listaPeliculas.add(movie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPeliculas;
    }/*
    public ArrayList<pelicula> FiltrarPeliculaGenero(){
        ArrayList<pelicula> listar
    };*/

    public ArrayList<pelicula> listarPeliculasFiltradas(int idGenero, int idStreaming) {

        ArrayList<pelicula> listaPeliculasFiltradas= new ArrayList<>();


        return listaPeliculasFiltradas;
    }

    // AGREGAR CAMPOS FALTANTES (GENERO, STREAMING)
    public void editarPelicula(int idPelicula, String titulo, String director, int anoPublicacion, double rating, double boxOffice, String duracion, String streaming, boolean premioOscar, String genero){


        try (Connection conn  =this.getConnection()) {
            String sql = "UPDATE PELICULA SET titulo = ?, director = ?, anoPublicacion = ? ," +
                    "rating = ?, boxOffice = ?, duracion =?, premioOscar=?, isStreaming=? WHERE IDPELICULA = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, titulo);
                pstmt.setString(2, director);
                pstmt.setInt(3, anoPublicacion);
                pstmt.setDouble(4, rating);
                pstmt.setDouble(5, boxOffice);
                pstmt.setInt(6, idPelicula);
                pstmt.setString(7, duracion);
                pstmt.setString(8, streaming);
                pstmt.setBoolean(9, premioOscar);
                pstmt.setString(10, genero);
                pstmt.executeUpdate();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }




    }


    public void borrarPelicula(int idPelicula) {

        // NOTA: PARA BORRAR UNA PELICULA CORRECTAMENTE NO OLVIDAR PRIMERO BORRARLA DE LA TABLA PROTAGONSITAS


    }


}
