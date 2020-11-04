package main.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ThumbnailsResponse {
    @JsonProperty("default")
    private YoutubePhotoResponse defaultA;
    private YoutubePhotoResponse medium;
    private YoutubePhotoResponse high;

    public YoutubePhotoResponse getDefaultA() {
        return defaultA;
    }
}
