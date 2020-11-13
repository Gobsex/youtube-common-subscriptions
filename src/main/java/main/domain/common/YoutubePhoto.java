package main.domain.common;

import lombok.Data;

@Data
public class YoutubePhoto {
    private String url;

    public String getUrl() {
        return url;
    }
}