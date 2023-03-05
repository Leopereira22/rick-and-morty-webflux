package com.pastorin.webrickandmortyapi.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationRequest {

    private String name;
    private String type;
    private String dimension;
}
