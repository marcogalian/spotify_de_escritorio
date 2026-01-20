package com.marcog.spotify_de_escritorio.repository;

import com.marcog.spotify_de_escritorio.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    // Busco al usuario por email para validar el acceso
    Usuario findByEmail(String email);
}