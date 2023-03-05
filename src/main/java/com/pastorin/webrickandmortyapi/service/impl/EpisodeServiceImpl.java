package com.pastorin.webrickandmortyapi.service.impl;

import com.pastorin.webrickandmortyapi.request.EpisodeRequest;
import com.pastorin.webrickandmortyapi.response.ByFiltersResponse;
import com.pastorin.webrickandmortyapi.response.EpisodeResponse;
import com.pastorin.webrickandmortyapi.service.EpisodeService;
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
public class EpisodeServiceImpl implements EpisodeService {

    private final WebClient webClient;

    private static final String BASE_URL_EPISODE = "https://rickandmortyapi.com/api/episode/";

    private static final String URL_API_EPISODE = "/rickAndMortyApi/episode/";

    public EpisodeServiceImpl(WebClient.Builder builder) {
        webClient = builder.baseUrl(BASE_URL_EPISODE).build();
    }

    public Mono<List<ByFiltersResponse>> findAllEpisodesByName(String name) {
        var results = getEpisode(EpisodeRequest.builder().name(name).build(), webClient);
        return results.flatMap(
                episodes -> Flux.fromIterable(episodes.getResults())
                        .map(episode -> new ByFiltersResponse(episode.getId(), episode.getName(), URL_API_EPISODE + episode.getId()))).collectList();
    }

    public Mono<List<ByFiltersResponse>> findAllEpisodesByEpisode(String ep) {
        var results = getEpisode(EpisodeRequest.builder().episode(ep).build(), webClient);
        return results.flatMap(
                episodes -> Flux.fromIterable(episodes.getResults())
                        .map(episode -> new ByFiltersResponse(episode.getId(), episode.getName(), URL_API_EPISODE + episode.getId()))).collectList();
    }

    public Mono<EpisodeResponse.EpisodeResultResponse> findById(String id) {
        return webClient
                .get()
                .uri(id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, errors -> {
                    throw new RuntimeException(errors.logPrefix());
                })
                .bodyToMono(EpisodeResponse.EpisodeResultResponse.class);
    }

    private Flux<EpisodeResponse> getEpisode(EpisodeRequest request, WebClient webClient) {
        log.info("search episode");

        return webClient
                .get()
                .uri(formatterUri(request))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, errors -> {
                    throw new RuntimeException(errors.logPrefix());
                })
                .bodyToFlux(EpisodeResponse.class);
    }

    private String formatterUri(EpisodeRequest request) {
        StringBuilder uri = new StringBuilder();

        ParamUtil.addParam(uri, "name", request.getName());
        ParamUtil.addParam(uri, "episode", request.getEpisode());

        return uri.toString();
    }
}
