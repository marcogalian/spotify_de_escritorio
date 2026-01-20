package com.marcog.spotify_de_escritorio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpotifyDeEscritorioApplication extends Application {

    private ConfigurableApplicationContext springContext;

    @Override
    public void init(){
        // Arranca Spring antes que javaFX
        springContext = SpringApplication.run(SpotifyDeEscritorioApplication.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Cargamos el FXML usando el contexto de Spring
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/spotify-view.fxml"));
        loader.setControllerFactory(springContext::getBean);

        Parent root = loader.load();
        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Spotify de Escritorio - Ejercicio 1");
        stage.show();
    }

    @Override
    public void stop() {
        // Se cierra Spring al cerrar la ventana
        springContext.close();
    }

    public static void main(String[] args){
        // Se lanza en JavaFX
        launch(args);
    }
}
