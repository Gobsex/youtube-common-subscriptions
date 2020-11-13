package main.domain.comment;

import lombok.Data;
import main.domain.common.PageInfo;
import main.domain.channel.YoutubeChannelItem;

import java.util.List;

@Data
public class YoutubeCommentResponse {
    private String nextPageToken;
    private String kind;
    private String etag;
    private PageInfo pageInfo;
    private List<YoutubeСommentItem> items;
        @Override
    public String toString() {
        return "main.Domain.ResponseYoutubeData{" +
                "nextPageToken='" + nextPageToken + '\'' +
                '}';
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public List<YoutubeСommentItem> getItems() {
        return items;
    }
}
