package com.pastorin.webrickandmortyapi.controller;

import com.pastorin.webrickandmortyapi.response.ByFiltersResponse;
import com.pastorin.webrickandmortyapi.response.EpisodeResponse;
import com.pastorin.webrickandmortyapi.service.EpisodeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/rickAndMortyApi/episode")
public class EpisodeController {

    @Autowired
    EpisodeService service;

    @GetMapping("/{id}")
    public ResponseEntity<Mono<EpisodeResponse.EpisodeResultResponse>> getById(@PathVariable(value = "id", required = true) String id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/episode/")
    public ResponseEntity<Mono<List<ByFiltersResponse>>> getByEpisode(@RequestParam(value = "episode", required = true) String episode){
        return new ResponseEntity<>(service.findAllEpisodesByEpisode(episode), HttpStatus.OK);
    }

    @GetMapping("/name/")
    public ResponseEntity<Mono<List<ByFiltersResponse>>> getByName(@RequestParam(value = "name", required = true) String name){
        return new ResponseEntity<>(service.findAllEpisodesByName(name), HttpStatus.OK);
    }
}
