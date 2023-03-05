package com.pastorin.webrickandmortyapi.service;

import com.pastorin.webrickandmortyapi.response.ByFiltersResponse;
import com.pastorin.webrickandmortyapi.response.EpisodeResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface EpisodeService {

    Mono<List<ByFiltersResponse>> findAllEpisodesByName(String name);

    Mono<List<ByFiltersResponse>> findAllEpisodesByEpisode(String ep);

    Mono<EpisodeResponse.EpisodeResultResponse> findById(String id);
}
