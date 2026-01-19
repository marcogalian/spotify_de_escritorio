package com.marcog.spotify_de_escritorio.util;

import com.marcog.spotify_de_escritorio.model.Cancion;
import com.marcog.spotify_de_escritorio.model.Usuario;
import com.marcog.spotify_de_escritorio.repository.CancionRepository;
import com.marcog.spotify_de_escritorio.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final CancionRepository cancionRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        // Limpiar la base de datos para no duplicar en cada reinicio
        cancionRepository.deleteAll();
        usuarioRepository.deleteAll();

        // Canciones de prueba
        Cancion c1 = Cancion.builder()
                .titulo("Bohemian Rhapsody")
                .artista("Queen")
                .album("A Night at the Opera")
                .duracion(5.5)
                .listaGeneros(List.of("Rock", "Clasico"))
                .build();

        Cancion c2 = Cancion.builder()
                .titulo("Para Elisa")
                .artista("Beethoven")
                .album("Bagatelas")
                .duracion(2.5)
                .listaGeneros(List.of("Clasico"))
                .build();

        Cancion c3 = Cancion.builder()
                .titulo("Thunderstruck")
                .artista("AC/DC")
                .album("The Razors Edge")
                .duracion(4.52)
                .listaGeneros(List.of("Rock"))
                .build();

        cancionRepository.saveAll(List.of(c1, c2, c3));

        // Creacion de un usuario y lo logueamos
        Usuario marco = new Usuario();
        marco.setNombre("Marco");
        marco.setEmail("marco@spotify.com");
        usuarioRepository.save(marco);

        SessionManager.usuarioActual = marco;
        System.out.println("Datos cargados y sesión iniciada para: " + marco.getNombre());


    }
}
