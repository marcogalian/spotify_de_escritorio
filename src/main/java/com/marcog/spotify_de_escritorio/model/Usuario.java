package com.marcog.spotify_de_escritorio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "usuarios")
public class Usuario {
    @Id
    private String id;
    private String nombre;
    private String email;

    // Inicializo con un ArrayList vacío para evitar NullPointerException
    private List<Cancion> cancionesFavoritas = new ArrayList<>();
}
