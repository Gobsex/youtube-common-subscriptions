package main.domain.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Setter;

import java.util.Date;

@Setter
@JsonIgnoreProperties(ignoreUnknown = true)

public class YotubeCommentSnippet {

    private String authorDisplayName;
    private String authorProfileImageUrl;
    private String authorChannelUrl;
    private AuthorChannelId authorChannelId;
    private String videoId;
    private String textDisplay;
    private String textOriginal;
    private String parentId;
    private long likeCount;
    private String moderationStatus;
    private Date publishedAt;

    public String getVideoId() {
        return videoId;
    }

    public String getTextDisplay() {
        return textDisplay;
    }

    public String getTextOriginal() {
        return textOriginal;
    }

    public String getParentId() {
        return parentId;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public String getModerationStatus() {
        return moderationStatus;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public String getAuthorDisplayName() {
        return authorDisplayName;
    }

    public String getAuthorProfileImageUrl() {
        return authorProfileImageUrl;
    }

    public String getAuthorChannelUrl() {
        return authorChannelUrl;
    }

    public AuthorChannelId getAuthorChannelId() {
        return authorChannelId;
    }
}

