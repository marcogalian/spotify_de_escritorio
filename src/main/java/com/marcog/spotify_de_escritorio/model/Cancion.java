package com.marcog.spotify_de_escritorio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "canciones")
@NoArgsConstructor
@AllArgsConstructor
public class Cancion {
    @Id
    private String id;
    private String titulo;
    private String artista;
    private String album;
    private double duracion;
    private List<String> listaGeneros;

    public static Object builder() {
    }
}
