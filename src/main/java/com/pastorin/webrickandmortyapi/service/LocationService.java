package com.pastorin.webrickandmortyapi.service;

import com.pastorin.webrickandmortyapi.response.ByFiltersResponse;
import com.pastorin.webrickandmortyapi.response.LocationResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface LocationService {

    Mono<List<ByFiltersResponse>> findAllLocationByName(String name);
    Mono<List<ByFiltersResponse>> findAllLocationByType(String type);
    Mono<List<ByFiltersResponse>> findAllLocationByDimension(String dimension);

    Mono<LocationResponse.LocationResultsResponse> findById(String id);
}
