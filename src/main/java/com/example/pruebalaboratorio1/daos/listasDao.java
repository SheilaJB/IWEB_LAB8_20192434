package com.example.pruebalaboratorio1.daos;

import com.example.pruebalaboratorio1.beans.genero;
import com.example.pruebalaboratorio1.beans.streaming;
import com.example.pruebalaboratorio1.beans.pelicula;

import java.sql.*;
import java.util.ArrayList;

public class listasDao {


    public static ArrayList<genero> listarGeneros() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";
        String sql = "SELECT * FROM mydb.genero;";

        ArrayList<genero> listaGeneros = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                genero generoPelicula = new genero();
                generoPelicula.setIdGenero(rs.getInt(1));
                generoPelicula.setNombre(rs.getString(2));

                listaGeneros.add(generoPelicula);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaGeneros;
    }

    public static ArrayList<streaming> listarStraming() {

        ArrayList<streaming> listaStreaming = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";
        String sql = "SELECT * FROM mydb.streaming;";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                streaming streamingPelicula = new streaming();
                streamingPelicula.setIdStreaming(rs.getString(1));
                streamingPelicula.setNombreServicio(rs.getString(2));

                listaStreaming.add(streamingPelicula);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaStreaming;
    }
}
