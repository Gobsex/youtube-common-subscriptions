package main.domain.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import main.domain.common.PageInfo;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class YoutubeChannelResponse {
    private String nextPageToken;

//    private String kind;
//    private String etag;
//    private PageInfo pageInfo;
    private List<YoutubeChannelItem> items;
        @Override
    public String toString() {
        return "main.Domain.ResponseYoutubeData{" +
                "nextPageToken='" + nextPageToken + '\'' +
                '}';
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public List<YoutubeChannelItem> getItems() {
        return items;
    }
}
