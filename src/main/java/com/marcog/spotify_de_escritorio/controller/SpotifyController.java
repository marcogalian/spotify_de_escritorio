package com.marcog.spotify_de_escritorio.controller;

import com.marcog.spotify_de_escritorio.model.Cancion;
import com.marcog.spotify_de_escritorio.service.SpotifyService;
import com.marcog.spotify_de_escritorio.util.SessionManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
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
    private Label sessionLabel;

    @FXML
    public void initialize() {
        // Recupero el nombre del usuario de la sesión global
        if (SessionManager.usuarioActual != null) {
            sessionLabel.setText("Sesión iniciada por: " + SessionManager.usuarioActual.getNombre());
        }

        // Configuro las celdas para que gestionen el color de selección y el alterno
        songListView.setCellFactory(lv -> new ListCell<Cancion>() {
            @Override
            protected void updateItem(Cancion cancion, boolean empty) {
                super.updateItem(cancion, empty);

                if (empty || cancion == null) {
                    setText(null);
                    setGraphic(null);
                    setStyle("-fx-background-color: #121212;");
                } else {
                    setText(cancion.getTitulo() + " - " + cancion.getArtista() + " (" + cancion.getAlbum() + ")");

                    // Lógica de resaltado: Si la celda está seleccionada, usamos el verde Spotify
                    if (isSelected()) {
                        setTextFill(Color.BLACK); // Texto negro para que contraste con el verde
                        setStyle("-fx-background-color: #1DB954; " +
                                "-fx-padding: 12; " +
                                "-fx-font-weight: bold;");
                    } else {
                        // Si no está seleccionada, mantenemos el estilo de filas alternas
                        setTextFill(Color.WHITE);

                        if (getIndex() % 2 == 0) {
                            setStyle("-fx-background-color: #121212; -fx-padding: 12; -fx-border-color: #252525; -fx-border-width: 0 0 1 0;");
                        } else {
                            setStyle("-fx-background-color: #333333; -fx-padding: 12; -fx-border-color: #252525; -fx-border-width: 0 0 1 0;");
                        }
                    }
                }
            }
        });

        // Cargo los datos iniciales
        handleReset();
    }

    @FXML
    public void handleLikeButton() {
        Cancion seleccionada = songListView.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            // Guardo en los favoritos del usuario actual
            spotifyService.agregarAFavoritos(seleccionada);
            mostrarAlerta("¡Hecho!", "Añadida a tus favoritos.");
        }
    }

    @FXML
    public void handleRockClasicoFilter() {
        List<Cancion> canciones = spotifyService.obtenerRockClasico();
        songListView.setItems(FXCollections.observableArrayList(canciones));
    }

    @FXML
    public void handleArtistSearch() {
        String busqueda = artistSearchField.getText();
        List<Cancion> resultados = spotifyService.buscarParaRadio(busqueda);
        songListView.setItems(FXCollections.observableArrayList(resultados));
    }

    @FXML
    public void handleReset() {
        artistSearchField.clear();
        List<Cancion> todas = spotifyService.obtenerTodas();
        songListView.setItems(FXCollections.observableArrayList(todas));
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}