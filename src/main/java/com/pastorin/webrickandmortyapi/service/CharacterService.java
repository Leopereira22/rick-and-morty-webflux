package com.pastorin.webrickandmortyapi.service;

import com.pastorin.webrickandmortyapi.response.ByFiltersResponse;
import com.pastorin.webrickandmortyapi.response.CharacterResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CharacterService {

    Mono<List<ByFiltersResponse>> findAllCharactersByGender(String gender);

    Mono<CharacterResponse.CharacterResultResponse> findById(String id);

    Mono<List<ByFiltersResponse>> findAllCharactersByStatus(String status);

    Mono<List<ByFiltersResponse>> findAllCharactersByName(String name);

    Mono<List<ByFiltersResponse>> findAllCharactersBySpecies(String species);
}
