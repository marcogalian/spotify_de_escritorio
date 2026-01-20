package com.marcog.spotify_de_escritorio.controller;

import com.marcog.spotify_de_escritorio.model.Usuario;
import com.marcog.spotify_de_escritorio.repository.UsuarioRepository;
import com.marcog.spotify_de_escritorio.util.Navigator;
import com.marcog.spotify_de_escritorio.util.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginController {

    private final UsuarioRepository usuarioRepository;
    private final Navigator navigator; // Inyectamos el navegador moderno

    @FXML private TextField emailField;

    @FXML
    public void handleLogin() {
        Usuario usuario = usuarioRepository.findByEmail(emailField.getText());

        if (usuario != null) {
            SessionManager.usuarioActual = usuario;

            // Navegamos a la vista principal sin rollos de FXMLLoader aquí
            navigator.loadScene("/vistas/spotify-view.fxml", "Spotify - " + usuario.getNombre(), 800, 600);
        } else {
            lanzarAlerta(Alert.AlertType.ERROR, "Error", "Usuario no encontrado", "El email no existe.");
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