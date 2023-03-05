package com.pastorin.webrickandmortyapi.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Data
public class EpisodeResponse {

    private List<EpisodeResultResponse> results;

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @Data
    public static class EpisodeResultResponse {


        private String id;
        private String name;
        private String air_date;
        private String episode;
        private List<String> characters;
        private String url;
    }
}
