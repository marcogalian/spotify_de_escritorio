package com.marcog.spotify_de_escritorio.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Navigator {

    private final ApplicationContext springContext;
    @Setter
    private Stage stage; // Referencia a la ventana principal

    public void loadScene(String fxmlPath, String title, int width, int height) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            // Hacemos que Spring cree los controladores para que funcionen las inyecciones (@Autowired/@RequiredArgsConstructor)
            loader.setControllerFactory(springContext::getBean);

            Parent root = loader.load();
            stage.setScene(new Scene(root, width, height));
            stage.setTitle(title);
            stage.centerOnScreen(); // Centralizamos siempre automáticamente
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}