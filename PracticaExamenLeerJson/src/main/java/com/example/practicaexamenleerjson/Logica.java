package com.example.practicaexamenleerjson;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Logica {


    private static Connection conexion;
    public void conectar() throws ClassNotFoundException, SQLException, IOException {
        Properties configuration = new Properties();
        configuration.load(R.getProperties("configBBDD.properties"));
        String host = configuration.getProperty("host");
        String port = configuration.getProperty("port");
        String name = configuration.getProperty("name");
        String username = configuration.getProperty("username");
        String password = configuration.getProperty("password");

        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                username, password);
    }

    public void desconectar() throws ClassNotFoundException, SQLException, IOException{

        conexion.close();
    }


    public int anadirJugador(Pelicula pelicula) throws SQLException, IOException, ClassNotFoundException {

        try {



            String sql="INSERT INTO peliculas (titulo, fecha, director, genero, ciudad) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement sentencia=conexion.prepareStatement(sql);
            sentencia.setString(1, pelicula.getTitulo());
            sentencia.setString(2, pelicula.getFecha());
            sentencia.setString(3, pelicula.getDirector());
            sentencia.setString(4, pelicula.getGenero());
            sentencia.setString(5, pelicula.getCiudad());



            int jugadorAnadido=sentencia.executeUpdate();


            return jugadorAnadido;


        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        } finally {
        }

        return 0;
    }

    public int eliminarJugador(Pelicula pelicula) throws SQLException, IOException, ClassNotFoundException {

        try {



            String sql= "delete from peliculas where titulo=?";

            PreparedStatement sentencia=conexion.prepareStatement(sql);
            sentencia.setString(1, pelicula.getTitulo());
            int borrado=sentencia.executeUpdate();

            return borrado;


        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return 0;
    }

    public int modificarJugador(Pelicula pelicula) throws SQLException, IOException, ClassNotFoundException {

        try {

            String sql="Update jugadores set titulo=?, fecha=?, director=?, genero=?, ciudad=? where titulo=?";
            PreparedStatement sentencia=conexion.prepareStatement(sql);
            sentencia.setString(1, pelicula.getTitulo());
            sentencia.setString(2, pelicula.getFecha());
            sentencia.setString(3, pelicula.getDirector());
            sentencia.setString(4, pelicula.getGenero());
            sentencia.setString(5, pelicula.getCiudad());

            int jugadoresActualizados=sentencia.executeUpdate();

            return jugadoresActualizados;

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public List<Pelicula> obtenerPeliculas() throws SQLException, IOException, ClassNotFoundException {

        List<Pelicula> peliculas=new ArrayList<>();

        String sql="select * from peliculas";


        PreparedStatement sentencia=conexion.prepareStatement(sql);
        ResultSet resultado=sentencia.executeQuery();
        while (resultado.next()){
            Pelicula pelicula=new Pelicula();
            pelicula.setId(Integer.parseInt(resultado.getString(1)));
            pelicula.setTitulo(resultado.getString(2));
            pelicula.setFecha(resultado.getString(3));
            pelicula.setDirector(resultado.getString(4));
            pelicula.setGenero(resultado.getString(5));
            pelicula.setCiudad(resultado.getString(6));


            peliculas.add(pelicula);

        }

        return peliculas;
    }


}
