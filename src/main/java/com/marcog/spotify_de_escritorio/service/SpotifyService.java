package com.marcog.spotify_de_escritorio.service;

import com.marcog.spotify_de_escritorio.model.Cancion;
import com.marcog.spotify_de_escritorio.model.Usuario;
import com.marcog.spotify_de_escritorio.repository.CancionRepository;
import com.marcog.spotify_de_escritorio.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotifyService {
    private final CancionRepository cancionRepository;
    private final UsuarioRepository usuarioRepository;

    // Botón "Me gusta"
    public void agregarAFavoritos(Cancion cancionSeleccionada){
        Usuario usuarioActual = SessionManager.usuarioActual;

        if (usuarioActual != null && cancionSeleccionada != null) {
            usuarioActual.getCancionesFavoritas().add(cancionSeleccionada);
            usuarioRepository.save(usuarioActual);
        }
    }

    // Filtro combinado "Modo Rock Clásico"
    public List<Cancion> obtenerRockClasico() {
        return cancionRepository.buscarRockClasico();
    }

    public List<Cancion> buscarParaRadio(String artista){
        return cancionRepository.buscarParaRadio(artista);
    }
}

