package main.domain.comment;

import lombok.Data;

@Data
public class YoutubeСommentItem {
    private String kind;
    private String etag;
    private String id;
    private YoutubeCommentTreadSnippet snippet;
    private Replies replies;

    public YoutubeCommentTreadSnippet getSnippet() {
        return snippet;
    }

    public Replies getReplies() {
        return replies;
    }
}
