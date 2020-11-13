package main.domain.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import main.domain.common.RecourseId;
import main.domain.common.Thumbnails;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class YoutubeChannelSnippet {

//    private String publishedAt;
    private String title;
    private String description;
    private RecourseId resourceId;
//    private String channelId;
    private Thumbnails thumbnails;


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public RecourseId getResourceId() {
        return resourceId;
    }

    public String getId() {
        return resourceId.getChannelId();
    }

    public Thumbnails getThumbnails() {
        return thumbnails;
    }
}