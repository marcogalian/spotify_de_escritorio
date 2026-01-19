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
        // TODO cargar lista inicial aqui
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
}
