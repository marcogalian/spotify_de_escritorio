package com.marcog.spotify_de_escritorio.repository;

import com.marcog.spotify_de_escritorio.model.Cancion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CancionRepository extends MongoRepository<Cancion, String> {
    // Filtro Combinando "Modo Rock Clásico" (insercion de arrays)
    @Query("{ 'listaGeneros' :  { '$all' :  ['Rock', 'Clasico']}} ")
    List<Cancion> buscarRockClasico();

    // Buscador Rápido (Búsqueda por artista y duración < 3 minutos)
    // El nombre del artista viene del TextField (?0) y el tiempo es fijo (< 3.0)
    @Query("{ 'artista':  ?0, 'duracion': {'$lt':  3.0 }}")
    List<Cancion> bucarParaRadio(String artista);
}
