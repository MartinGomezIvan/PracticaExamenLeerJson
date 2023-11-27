package com.example.practicaexamenleerjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    public static final ObjectMapper JSON_MAPPER=new ObjectMapper();

    @FXML
    private Button btAnadir;

    @FXML
    private Button btBorrar;

    @FXML
    private RadioButton btBurgos;

    @FXML
    private RadioButton btLeon;

    @FXML
    private Button btLimpiar;

    @FXML
    private Button btModificar;

    @FXML
    private RadioButton btValladolid;

    @FXML
    private ToggleGroup ciudades;

    @FXML
    private TextField txtDirector;

    @FXML
    private TextField txtFecha;

    @FXML
    private TextField txtGenero;

    @FXML
    private TextField txtTitulo;
    @FXML
    private TableColumn col_ciudad;

    @FXML
    private TableColumn col_director;

    @FXML
    private TableColumn col_fecha;

    @FXML
    private TableColumn col_genero;

    @FXML
    private TableColumn col_titulo;
    @FXML
    private TableColumn col_id;

    @FXML
    private TableView<Pelicula> tablaPelis;

    Logica conectarDAO=new Logica();
    public  HelloController() {

        try {
            conectarDAO.conectar();
            System.out.println("");
        } catch (SQLException sql) {
            System.out.println("Error al conectar con la base de datos");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error al iniciar la aplicación");
        } catch (IOException ioe) {
            System.out.println("Error al cargar la configuración");
        }

        System.out.println(System.getProperty("user.home"));
    }

    @FXML
    void anadirPelicula(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {


        String titulo=txtTitulo.getText();
        String fecha=txtFecha.getText();
        String director=txtDirector.getText();
        String genero=txtGenero.getText();
        String ciudad;
        if (btBurgos.isSelected()){
            ciudad="Burgos";
        } else if (btValladolid.isSelected()) {
            ciudad="Valladolid";
        } else {
            ciudad="Leon";
        }

        Pelicula anadirPelicula=new Pelicula(titulo, fecha, director, genero, ciudad);

        if (conectarDAO.anadirJugador(anadirPelicula)>=1){
            System.out.println("Jugador añadido con exito");
        }

        actualizarDatos();

    }

    @FXML
    void borrarPelicula(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {

        Pelicula borrarPelicula=new Pelicula();
        borrarPelicula=tablaPelis.getSelectionModel().getSelectedItem();
        if (borrarPelicula==null){
            System.out.println("Seleccione un jugador");
        }
        if(conectarDAO.eliminarJugador(borrarPelicula)>=1){
            System.out.println("Pelicula borrada con exito");
        }

        actualizarDatos();
    }

    @FXML
    void limpiarCampos(ActionEvent event) {

    }

    @FXML
    void modificarPelicula(ActionEvent event) {

    }



    private void importarDatos(){

        try {

            InputStream inputStream = HelloApplication.class.getResourceAsStream("/peliculas.json");

            ArrayList<Pelicula> peliculas = JSON_MAPPER.readValue(
                    inputStream,
                    JSON_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, Pelicula.class));

//            ArrayList<Pelicula> peliculas=
//                    JSON_MAPPER.readValue(new File("src/main/resources/peliculas.json"),
//                            JSON_MAPPER.getTypeFactory()
//                                    .constructCollectionType(ArrayList.class, Pelicula.class));

            for (int i=0; i<peliculas.size();i++){
                String titulo=peliculas.get(i).getTitulo();
                String fecha=peliculas.get(i).getFecha();
                String director=peliculas.get(i).getDirector();
                String genero=peliculas.get(i).getGenero();
                String ciudad=peliculas.get(i).getCiudad();

                Pelicula pelicula=new Pelicula(titulo, fecha, director, genero, ciudad);
                conectarDAO.anadirJugador(pelicula);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void cargarDatos(){

        Pelicula cargarPelicula= tablaPelis.getSelectionModel().getSelectedItem();

        txtTitulo.setText(cargarPelicula.getTitulo());
        txtGenero.setText(cargarPelicula.getGenero());
        txtDirector.setText(cargarPelicula.getDirector());
        txtFecha.setText(cargarPelicula.getFecha());

        if (cargarPelicula.getCiudad().equals("Valladolid")){
            btValladolid.setSelected(true);
        } else if (cargarPelicula.getCiudad().equals("Leon")) {
            btLeon.setSelected(true);
        } else {
            btBurgos.setSelected(true);
        }

    }

    private void actualizarDatos(){

        try {
            List<Pelicula> peliculas = conectarDAO.obtenerPeliculas();
            ObservableList<Pelicula> data = FXCollections.observableArrayList(peliculas);

            tablaPelis.setItems(data);

            this.col_id.setCellValueFactory(new PropertyValueFactory("id"));
            this.col_titulo.setCellValueFactory(new PropertyValueFactory("titulo"));
            this.col_fecha.setCellValueFactory(new PropertyValueFactory("fecha"));
            this.col_director.setCellValueFactory(new PropertyValueFactory("director"));
            this.col_genero.setCellValueFactory(new PropertyValueFactory("genero"));
            this.col_ciudad.setCellValueFactory(new PropertyValueFactory("ciudad"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            List<Pelicula> peliculas = conectarDAO.obtenerPeliculas();
            ObservableList<Pelicula> data = FXCollections.observableArrayList(peliculas);

            tablaPelis.setItems(data);

            this.col_id.setCellValueFactory(new PropertyValueFactory("id"));
            this.col_titulo.setCellValueFactory(new PropertyValueFactory("titulo"));
            this.col_fecha.setCellValueFactory(new PropertyValueFactory("fecha"));
            this.col_director.setCellValueFactory(new PropertyValueFactory("director"));
            this.col_genero.setCellValueFactory(new PropertyValueFactory("genero"));
            this.col_ciudad.setCellValueFactory(new PropertyValueFactory("ciudad"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
