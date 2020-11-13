package main.domain.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Thumbnails {
    @JsonProperty("default")
    private YoutubePhoto defaultA;
    private YoutubePhoto medium;
    private YoutubePhoto high;

    public YoutubePhoto getDefaultA() {
        return defaultA;
    }
}
