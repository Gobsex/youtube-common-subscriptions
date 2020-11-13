package main.domain.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class YoutubeChannelItem {
//    private String kind;
//    private String etag;
//    private String id;
    private YoutubeChannelSnippet snippet;

    public YoutubeChannelSnippet getSnippet() {
        return snippet;
    }
}
