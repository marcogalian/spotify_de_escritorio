package com.marcog.spotify_de_escritorio.controller;

import com.marcog.spotify_de_escritorio.model.Usuario;
import com.marcog.spotify_de_escritorio.service.SpotifyService;
import com.marcog.spotify_de_escritorio.util.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginController {

    // PRO: Inyectamos el Servicio, NO el Repositorio
    private final SpotifyService spotifyService;
    private final Navigator navigator;

    @FXML private TextField emailField;

    @FXML
    public void handleLogin() {
        try {
            // El controlador delega TODA la responsabilidad al servicio
            Usuario usuario = spotifyService.login(emailField.getText());

            // Si el servicio no lanzó error, navegamos
            navigator.loadScene(
                    "/vistas/spotify-view.fxml",
                    "Spotify - " + usuario.getNombre(),
                    600, 600
            );

        } catch (RuntimeException e) {
            // Manejamos el error que viene del servicio
            lanzarAlerta(Alert.AlertType.ERROR, "Error de Acceso", "Login incorrecto", e.getMessage());
        }
    }

    private void lanzarAlerta(Alert.AlertType tipo, String titulo, String cab, String msg) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(cab);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}