package com.marcog.spotify_de_escritorio.util;

import com.marcog.spotify_de_escritorio.model.Cancion;
import com.marcog.spotify_de_escritorio.model.Usuario;
import com.marcog.spotify_de_escritorio.repository.CancionRepository;
import com.marcog.spotify_de_escritorio.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CancionRepository cancionRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        // Limpio las colecciones para evitar datos duplicados al reiniciar
        cancionRepository.deleteAll();
        usuarioRepository.deleteAll();

        // Cargo las 5 canciones necesarias para que funcionen todos los filtros
        cancionRepository.saveAll(List.of(
                Cancion.builder().titulo("Bohemian Rhapsody").artista("Queen").album("A Night at the Opera").duracion(5.5).listaGeneros(List.of("Rock", "Clasico")).build(),
                Cancion.builder().titulo("Thunderstruck").artista("AC/DC").album("The Razors Edge").duracion(4.5).listaGeneros(List.of("Rock")).build(),
                Cancion.builder().titulo("Killer Queen").artista("Queen").album("Sheer Heart Attack").duracion(2.99).listaGeneros(List.of("Rock")).build(),
                Cancion.builder().titulo("Lazing on a Sunday Afternoon").artista("Queen").album("A Night at the Opera").duracion(1.07).listaGeneros(List.of("Rock")).build(),
                Cancion.builder().titulo("T.N.T.").artista("AC/DC").album("High Voltage").duracion(2.5).listaGeneros(List.of("Rock")).build()
        ));

        // Registro al usuario de prueba para poder loguear
        Usuario marco = new Usuario();
        marco.setNombre("Marco");
        marco.setEmail("marco@spotify.com");
        usuarioRepository.save(marco);


    }
}