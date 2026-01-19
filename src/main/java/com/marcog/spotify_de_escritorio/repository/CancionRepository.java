package com.marcog.spotify_de_escritorio.repository;

import com.marcog.spotify_de_escritorio.model.Cancion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CancionRepository extends MongoRepository<Cancion, String> {
    // Filtro Combinando "Modo Rock Clásico" (insercion de arrays)
    @Query("{ 'listaGeneros' :  { '$all' :  ['Rock', 'Clasico']}} ")
    List<Cancion> buscarRockClasico();

    // Buscador Rápido con el estilo del profesor
    // Usamos $regex para que busque nombres similares y $options: 'i' para ignorar mayúsculas
    @Query("{ 'artista': { '$regex': ?0, '$options': 'i' }, 'duracion': { '$lt': 3.0 } }")
    List<Cancion> buscarParaRadio(String artista);
}
