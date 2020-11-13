package main.domain.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class YoutubeCommentTreadSnippet {

    private String channelId;
    private String videoId;
    private YoutubeComment topLevelComment;

    public String getChannelId() {
        return channelId;
    }

    public String getVideoId() {
        return videoId;
    }

    public YoutubeComment getTopLevelYoutubeComment() {
        return topLevelComment;
    }
}
