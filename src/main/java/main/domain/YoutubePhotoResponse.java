package main.domain;

import lombok.Data;

@Data
public class YoutubePhotoResponse {
    private String url;

    public String getUrl() {
        return url;
    }
}