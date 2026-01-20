package com.marcog.spotify_de_escritorio.service;

import com.marcog.spotify_de_escritorio.model.Cancion;
import com.marcog.spotify_de_escritorio.model.Usuario;
import com.marcog.spotify_de_escritorio.repository.CancionRepository;
import com.marcog.spotify_de_escritorio.repository.UsuarioRepository;
import com.marcog.spotify_de_escritorio.util.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotifyService {
    private final CancionRepository cancionRepository;
    private final UsuarioRepository usuarioRepository;

    /**
     * Lógica profesional de Login:
     * El servicio valida la existencia y gestiona la sesión.
     */
    public Usuario login(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            // En empresas se suelen usar excepciones personalizadas
            throw new RuntimeException("Credenciales no válidas");
        }

        // Si es válido, el servicio establece la sesión
        SessionManager.usuarioActual = usuario;
        return usuario;
    }

    public void agregarAFavoritos(Cancion cancionSeleccionada){
        Usuario usuarioActual = SessionManager.usuarioActual;

        if (usuarioActual != null && cancionSeleccionada != null) {
            usuarioActual.getCancionesFavoritas().add(cancionSeleccionada);
            usuarioRepository.save(usuarioActual);
        }
    }

    public List<Cancion> obtenerRockClasico() {
        return cancionRepository.buscarRockClasico();
    }

    public List<Cancion> buscarParaRadio(String artista){
        return cancionRepository.buscarParaRadio(artista);
    }

    public List<Cancion> obtenerTodas() {
        return cancionRepository.findAll();
    }
}