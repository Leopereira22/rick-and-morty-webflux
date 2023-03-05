package com.pastorin.webrickandmortyapi.controller;

import com.pastorin.webrickandmortyapi.response.ByFiltersResponse;
import com.pastorin.webrickandmortyapi.response.CharacterResponse;
import com.pastorin.webrickandmortyapi.service.CharacterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/rickAndMortyApi/character")
public class CharacterController {

    CharacterService service;


    @GetMapping("/{id}")
    public ResponseEntity<Mono<CharacterResponse.CharacterResultResponse>> getById(@PathVariable(value = "id", required = true) String id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/gender/")
    public ResponseEntity<Mono<List<ByFiltersResponse>>> getByGender(@RequestParam(value = "gender", required = true) String gender){
        return new ResponseEntity<>(service.findAllCharactersByGender(gender), HttpStatus.OK);
    }

    @GetMapping("/status/")
    public ResponseEntity<Mono<List<ByFiltersResponse>>> getByStatus(@RequestParam(value = "status", required = true) String status){
        return new ResponseEntity<>(service.findAllCharactersByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/name/")
    public ResponseEntity<Mono<List<ByFiltersResponse>>> getByName(@RequestParam(value = "name", required = true) String name){
        return new ResponseEntity<>(service.findAllCharactersByName(name), HttpStatus.OK);
    }

    @GetMapping("/species/")
    public ResponseEntity<Mono<List<ByFiltersResponse>>> getBySpecies(@RequestParam(value = "species", required = true) String species){
        return new ResponseEntity<>(service.findAllCharactersBySpecies(species), HttpStatus.OK);
    }
}
