package main.domain;

import lombok.Data;

@Data
public class YoutubeChannelResponse {
    private String kind;
    private String etag;
    private String id;
    private YoutubeChannelInfo snippet;

    public YoutubeChannelInfo getSnippet() {
        return snippet;
    }
}
