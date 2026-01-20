package com.marcog.spotify_de_escritorio.controller;

import com.marcog.spotify_de_escritorio.model.Cancion;
import com.marcog.spotify_de_escritorio.service.SpotifyService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView; // <--- ESTE ES EL BUENO
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SpotifyController {

    private final SpotifyService spotifyService;

    @FXML
    private ListView<Cancion> songListView;

    @FXML
    private TextField artistSearchField;

    @FXML
    public void initialize(){
        List<Cancion> todas = spotifyService.obtenerTodas();
        songListView.setItems(FXCollections.observableArrayList(todas));
    }

    @FXML
    public void handleLikeButton(){
        Cancion seleccionada = songListView.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            spotifyService.agregarAFavoritos(seleccionada);
            mostrarAlerta("Éxito!" , "Canción añadida a favoritos.");
        }
    }

    @FXML
    public void handleRockClasicoFilter(){
        List<Cancion> canciones = spotifyService.obtenerRockClasico();
        songListView.setItems(FXCollections.observableArrayList(canciones));
    }

    private void mostrarAlerta(String titulo, String mensaje){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    public void handleArtistSearch() {
        String busqueda = artistSearchField.getText();
        // Llamamos al servicio que usa la @Query con regex
        List<Cancion> resultados = spotifyService.buscarParaRadio(busqueda);

        // Actualizamos la lista con los resultados encontrados
        songListView.setItems(FXCollections.observableArrayList(resultados));
    }

    @FXML
    public void handleReset() {
        artistSearchField.clear(); // Limpiamos el texto de búsqueda
        List<Cancion> todas = spotifyService.obtenerTodas();
        songListView.setItems(FXCollections.observableArrayList(todas));
    }
}
