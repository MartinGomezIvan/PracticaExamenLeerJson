package com.example.practicaexamenleerjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 630, 605);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch();

//        ObjectMapper JSON_MAPPER = new ObjectMapper();
//
//
//        //SI NO COGE LA RUTA MEJOR HACERLO DE ESTA MANERA
//        // Carga el archivo desde el classpath
//        InputStream inputStream = HelloApplication.class.getResourceAsStream("/peliculas.json");
//
//        ArrayList<Pelicula> peliculas = JSON_MAPPER.readValue(
//                inputStream,
//                JSON_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, Pelicula.class));
//
//        //Para hacerlo metiendo la ruta directamente
////        ArrayList<Pelicula> peliculas =
////                JSON_MAPPER.readValue(new File("/src/main/resources/peliculas.json"),
////                        JSON_MAPPER.getTypeFactory()
////                                .constructCollectionType(ArrayList.class, Pelicula.class));
//
//        for (int i = 0; i < peliculas.size(); i++) {
//
//            System.out.println("Id: " + peliculas.get(i).getId() + " ");
//            System.out.println("Titulo: " + peliculas.get(i).getTitulo() + " ");
//            System.out.println("Fecha: " + peliculas.get(i).getFecha() + " ");
//            System.out.println("Director: " + peliculas.get(i).getDirector() + " ");
//            System.out.println("Genero: " + peliculas.get(i).getGenero() + " ");
//            System.out.println("Ciudad: " + peliculas.get(i).getCiudad() + " ");


//            String titulo = peliculas.get(i).getTitulo();
//            Date fecha = peliculas.get(i).getFecha();
//            String director = peliculas.get(i).getDirector();
//            String genero = peliculas.get(i).getGenero();
//            String ciudad = peliculas.get(i).getCiudad();
//            String mostrar1= FXCollections.observableArrayList(titulo);
//        }
    }
}