package com.example.practicaexamenleerjson;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBBDD {


    private static Connection conexion;


    public static void conectar() throws ClassNotFoundException, SQLException, IOException {
        boolean conect=false;
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

    public void openConexion() throws SQLException {//Para realizar la conexion con la bbdd.

        this.conexion=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/equipofutbol", "root", "ivan");
    }
    public void closeConexion() throws SQLException {//Para cerrar la conexion con la bbdd.

        this.conexion.close();
    }
}
