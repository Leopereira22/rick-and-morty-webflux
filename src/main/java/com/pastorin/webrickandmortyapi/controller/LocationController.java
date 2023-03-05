package com.pastorin.webrickandmortyapi.controller;

import com.pastorin.webrickandmortyapi.response.ByFiltersResponse;
import com.pastorin.webrickandmortyapi.response.LocationResponse;
import com.pastorin.webrickandmortyapi.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/rickAndMortyApi/location")
public class LocationController {

    @Autowired
    LocationService service;

    @GetMapping("/{id}")
    public Mono<LocationResponse.LocationResultsResponse> getLocationById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/type/")
    public ResponseEntity<Mono<List<ByFiltersResponse>>> getByType(@RequestParam(value = "type", required = true) String type){
        return new ResponseEntity<>(service.findAllLocationByType(type), HttpStatus.OK);
    }

    @GetMapping("/name/")
    public ResponseEntity<Mono<List<ByFiltersResponse>>> getByName(@RequestParam(value = "name", required = true) String name){
        return new ResponseEntity<>(service.findAllLocationByName(name), HttpStatus.OK);
    }

    @GetMapping("/dimension/")
    public ResponseEntity<Mono<List<ByFiltersResponse>>> getByDimension(@RequestParam(value = "dimension", required = true) String dimension){
        return new ResponseEntity<>(service.findAllLocationByDimension(dimension), HttpStatus.OK);
    }
}
