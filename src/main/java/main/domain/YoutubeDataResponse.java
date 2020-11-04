package main.domain;

import lombok.Data;

import java.util.List;
@Data
public class YoutubeDataResponse {
    private String nextPageToken;

    private String kind;
    private String etag;
    private PageInfoResponse pageInfo;
    private List<YoutubeChannelResponse> items;
        @Override
    public String toString() {
        return "main.Domain.ResponseYoutubeData{" +
                "nextPageToken='" + nextPageToken + '\'' +
                '}';
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public List<YoutubeChannelResponse> getItems() {
        return items;
    }
}
