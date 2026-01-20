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
        // Limpiar la base de datos para no duplicar en cada reinicio
        cancionRepository.deleteAll();
        usuarioRepository.deleteAll();

        // Canciones de prueba
        Cancion c1 = Cancion.builder().titulo("Bohemian Rhapsody").artista("Queen").album("A Night at the Opera").duracion(5.5).listaGeneros(List.of("Rock", "Clasico")).build();
        Cancion c2 = Cancion.builder().titulo("Thunderstruck").artista("AC/DC").album("The Razors Edge").duracion(4.5).listaGeneros(List.of("Rock")).build();
        Cancion c3 = Cancion.builder().titulo("Killer Queen").artista("Queen").album("Sheer Heart Attack").duracion(2.99).listaGeneros(List.of("Rock")).build();
        Cancion c4 = Cancion.builder().titulo("Lazing on a Sunday Afternoon").artista("Queen").album("A Night at the Opera").duracion(1.07).listaGeneros(List.of("Rock")).build();
        Cancion c5 = Cancion.builder().titulo("T.N.T.").artista("AC/DC").album("High Voltage").duracion(2.5).listaGeneros(List.of("Rock")).build();

        cancionRepository.saveAll(List.of(c1, c2, c3, c4, c5));

        // Creacion de un usuario y lo logueamos(Reto de pensamiento)
        Usuario marco = new Usuario();
        marco.setNombre("Marco");
        marco.setEmail("marco@spotify.com");
        usuarioRepository.save(marco);

        SessionManager.usuarioActual = marco;
        System.out.println("Datos cargados y sesión iniciada para: " + marco.getNombre());


    }
}
