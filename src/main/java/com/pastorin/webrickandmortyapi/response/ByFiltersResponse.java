package com.pastorin.webrickandmortyapi.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ByFiltersResponse {

    private String id;
    private String name;
    private String url;
}
