package main.entity;

import lombok.Data;
import lombok.NonNull;
import main.domain.channel.YoutubeChannelItem;
import main.domain.channel.YoutubeChannelSnippet;
import main.service.UrlService;

import java.util.Objects;

@Data
public class YoutubeChannelFormatted {
    private double frequency = 0.0;
    private String id;
    private String href;
    private String preview;
    private String channelName;





    private String channelDescription;
    private long subscribers;

    public YoutubeChannelFormatted(@NonNull YoutubeChannelItem response) {

        YoutubeChannelSnippet snippet = response.getSnippet();
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

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YoutubeChannelFormatted channel = (YoutubeChannelFormatted) o;
        return Objects.equals(id, channel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getId() {
        return id;
    }
}
