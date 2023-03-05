package com.pastorin.webrickandmortyapi.service.impl;

import com.pastorin.webrickandmortyapi.enums.GenderEnum;
import com.pastorin.webrickandmortyapi.enums.StatusEnum;
import com.pastorin.webrickandmortyapi.exception.RickAndMortyRulesException;
import com.pastorin.webrickandmortyapi.request.CharacterRequest;
import com.pastorin.webrickandmortyapi.response.ByFiltersResponse;
import com.pastorin.webrickandmortyapi.response.CharacterResponse;
import com.pastorin.webrickandmortyapi.service.CharacterService;
import com.pastorin.webrickandmortyapi.util.ParamUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
@Slf4j
public class CharacterServiceImpl implements CharacterService {

    private final WebClient webClient;

    private static final String BASE_URL_CHARACTER = "https://rickandmortyapi.com/api/character/";

    private static final String URL_API_CHARACTER = "/rickAndMortyApi/character/";

    public CharacterServiceImpl(WebClient.Builder builder) {
        webClient = builder.baseUrl(BASE_URL_CHARACTER).build();
    }

    public Mono<List<ByFiltersResponse>> findAllCharactersByGender(String gender) {
        if (!EnumUtils.isValidEnum(GenderEnum.class, gender.toUpperCase()))
            throw new RickAndMortyRulesException("Consider status: (female, male, genderless or unknown).");

        var results = getCharacters(CharacterRequest.builder().gender(gender).build(), webClient);
        return results.flatMap(
                        characters -> Flux.fromIterable(characters.getResults())
                                .map(character -> new ByFiltersResponse(character.getId(), character.getName(), URL_API_CHARACTER + character.getId()))).collectList();
    }

    public Mono<List<ByFiltersResponse>> findAllCharactersByStatus(String status) {
        if (!EnumUtils.isValidEnum(StatusEnum.class, status.toUpperCase()))
            throw new RickAndMortyRulesException("Consider status: (alive, dead or unknown).");

        var results = getCharacters(CharacterRequest.builder().status(status).build(), webClient);
        return results.flatMap(
                characters -> Flux.fromIterable(characters.getResults())
                        .map(character -> new ByFiltersResponse(character.getId(), character.getName(), URL_API_CHARACTER + character.getId()))).collectList();
    }

    public Mono<List<ByFiltersResponse>> findAllCharactersBySpecies(String species) {
        var results = getCharacters(CharacterRequest.builder().species(species).build(), webClient);
        return results.flatMap(
                characters -> Flux.fromIterable(characters.getResults())
                        .map(character -> new ByFiltersResponse(character.getId(), character.getName(), URL_API_CHARACTER + character.getId()))).collectList();
    }

    public Mono<List<ByFiltersResponse>> findAllCharactersByName(String name) {
        var results = getCharacters(CharacterRequest.builder().name(name).build(), webClient);
        return results.flatMap(
                characters -> Flux.fromIterable(characters.getResults())
                        .map(character -> new ByFiltersResponse(character.getId(), character.getName(), URL_API_CHARACTER + character.getId()))).collectList();
    }


    public Mono<CharacterResponse.CharacterResultResponse> findById(String id) {
        return webClient
                .get()
                .uri(id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, errors -> {
                    throw new RuntimeException(errors.logPrefix());
                })
                .bodyToMono(CharacterResponse.CharacterResultResponse.class);
    }

    private Flux<CharacterResponse> getCharacters(CharacterRequest request, WebClient webClient) {
        log.info("search characters");

        return webClient
                .get()
                .uri(formatterUri(request))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, errors -> {
                    throw new RuntimeException(errors.logPrefix());
                })
                .bodyToFlux(CharacterResponse.class);
    }

    private String formatterUri(CharacterRequest request) {
        StringBuilder uri = new StringBuilder();

        ParamUtil.addParam(uri, "name", request.getName());
        ParamUtil.addParam(uri, "species", request.getSpecies());
        ParamUtil.addParam(uri, "gender", request.getGender());
        ParamUtil.addParam(uri, "status", request.getStatus());

        return uri.toString();
    }
}
