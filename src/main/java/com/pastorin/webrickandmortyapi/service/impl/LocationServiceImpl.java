package com.pastorin.webrickandmortyapi.service.impl;

import com.pastorin.webrickandmortyapi.request.LocationRequest;
import com.pastorin.webrickandmortyapi.response.ByFiltersResponse;
import com.pastorin.webrickandmortyapi.response.LocationResponse;
import com.pastorin.webrickandmortyapi.service.LocationService;
import com.pastorin.webrickandmortyapi.util.ParamUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

    private final WebClient webClient;

    private static final String BASE_URL_CHARACTER = "https://rickandmortyapi.com/api/location/";

    private static final String URL_API_LOCATION = "/rickAndMortyApi/location/";

    public LocationServiceImpl(WebClient.Builder builder) {
        webClient = builder.baseUrl(BASE_URL_CHARACTER).build();
    }

    public Mono<List<ByFiltersResponse>> findAllLocationByName(String name) {
        var results = getLocation(LocationRequest.builder().name(name).build(), webClient);
        return results.flatMap(
                location -> Flux.fromIterable(location.getResults())
                        .map(loc -> new ByFiltersResponse(loc.getId(), loc.getName(), URL_API_LOCATION + loc.getId()))).collectList();
    }

    public Mono<List<ByFiltersResponse>> findAllLocationByType(String type) {
        var results = getLocation(LocationRequest.builder().type(type).build(), webClient);
        return results.flatMap(
                location -> Flux.fromIterable(location.getResults())
                        .map(loc -> new ByFiltersResponse(loc.getId(), loc.getName(), URL_API_LOCATION + loc.getId()))).collectList();
    }

    public Mono<List<ByFiltersResponse>> findAllLocationByDimension(String dimension) {
        var results = getLocation(LocationRequest.builder().dimension(dimension).build(), webClient);
        return results.flatMap(
                location -> Flux.fromIterable(location.getResults())
                        .map(loc -> new ByFiltersResponse(loc.getId(), loc.getName(), URL_API_LOCATION + loc.getId()))).collectList();
    }

    public Mono<LocationResponse.LocationResultsResponse> findById(String id) {
        return webClient
                .get()
                .uri(id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, errors -> {
                    throw new RuntimeException(errors.logPrefix());
                })
                .bodyToMono(LocationResponse.LocationResultsResponse.class);
    }

    private Flux<LocationResponse> getLocation(LocationRequest request, WebClient webClient) {
        log.info("search locations");

        return webClient
                .get()
                .uri(formatterUri(request))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, errors -> {
                    throw new RuntimeException(errors.logPrefix());
                })
                .bodyToFlux(LocationResponse.class);
    }

    private String formatterUri(LocationRequest request) {
        StringBuilder uri = new StringBuilder();

        ParamUtil.addParam(uri, "name", request.getName());
        ParamUtil.addParam(uri, "type", request.getType());
        ParamUtil.addParam(uri, "dimension", request.getDimension());

        return uri.toString();
    }
}
