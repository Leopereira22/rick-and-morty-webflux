package com.pastorin.webrickandmortyapi.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CharacterRequest {

    Integer id;
    String name;
    String species;
    String type;
    String gender;
    String status;
}
