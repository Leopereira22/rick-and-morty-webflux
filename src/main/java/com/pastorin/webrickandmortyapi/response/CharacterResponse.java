package com.pastorin.webrickandmortyapi.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.pastorin.webrickandmortyapi.enums.StatusEnum;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterResponse {

    private List<CharacterResultResponse> results;

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CharacterResultResponse {
        private String id;
        private String name;
        private String status;
        private String type;
        private String gender;
        private String species;
        private OriginOfCharacter origin;
        private LocationOfCharacter location;
        private String image;
        private List<String> episode;
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OriginOfCharacter {

        private String name;
        private String url;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LocationOfCharacter {

        private String name;
        private String url;
    }
}
