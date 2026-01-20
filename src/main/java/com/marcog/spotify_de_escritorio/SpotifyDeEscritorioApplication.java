package com.marcog.spotify_de_escritorio;

import com.marcog.spotify_de_escritorio.util.Navigator;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpotifyDeEscritorioApplication extends Application {

    private ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        springContext = SpringApplication.run(SpotifyDeEscritorioApplication.class);
    }

    @Override
    public void start(Stage stage) {
        // Pedimos el navegador a Spring y le entregamos la ventana
        Navigator navigator = springContext.getBean(Navigator.class);
        navigator.setStage(stage);

        // Cargamos la pantalla inicial de forma limpia
        navigator.loadScene("/vistas/login-view.fxml", "Spotify - Iniciar Sesión", 500, 600);
    }

    @Override
    public void stop() {
        springContext.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}