package com.pastorin.webrickandmortyapi.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponse {

    private List<LocationResultsResponse> results;

    @Data
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LocationResultsResponse {
        private String id;
        private String name;
        private String type;
        private String dimension;
        private List<String> residents;
        private String url;
        private String created;
    }
}
