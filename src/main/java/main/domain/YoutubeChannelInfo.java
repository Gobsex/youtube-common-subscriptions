package main.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor

public class YoutubeChannelInfo {

    private String publishedAt;
    private String title;
    private String description;
    private RecourseIdResponse resourceId;
    private String channelId;
    private ThumbnailsResponse thumbnails;

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public RecourseIdResponse getResourceId() {
        return resourceId;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getId() {
        return resourceId.getChannelId();
    }

    public ThumbnailsResponse getThumbnails() {
        return thumbnails;
    }
}
