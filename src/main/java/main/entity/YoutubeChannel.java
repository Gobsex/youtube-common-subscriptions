package main.entity;

import lombok.Data;
import lombok.NonNull;
import main.domain.YoutubeChannelInfo;
import main.domain.YoutubeChannelResponse;
import main.service.UrlService;

@Data
public class YoutubeChannel {
    private String id;
    private String href;
    private String preview;
    private String channelName;





    private String channelDescription;
    private long subscribers;

    public YoutubeChannel(@NonNull YoutubeChannelResponse response) {

        YoutubeChannelInfo snippet = response.getSnippet();
        this.id = snippet.getId();
        //todo
        this.href = snippet.getId();
        this.preview = snippet.getThumbnails().getDefaultA().getUrl();
        this.channelName = snippet.getTitle();
        this.channelDescription = snippet.getDescription();
        //todo
        this.subscribers = 0;
    }
    public String getChannelName() {
        return channelName;
    }
    public void calculateHref(){
        this.href = UrlService.getChannelUrl(id);
    }
    public String getId() {
        return id;
    }
}
