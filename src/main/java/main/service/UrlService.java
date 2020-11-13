package main.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
@PropertySource("classpath:youtube.properties")
@Component
public class UrlService {
    @Getter
    @Setter
    @Value("${youtubeApi.host}")
    private String youtubeApiHost;


    @Getter
    @Setter
    @Value("${youtubeApi.key}")
    private String youtubeApiKey;

    private static String youtubeHost;


    public UriComponentsBuilder getCommentsRequestBuilder(String videoId){

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme("https").host(youtubeApiHost).path("youtube/v3/commentThreads")
                .queryParam("part", "snippet,replies")
                .queryParam("maxResults","50")
                .queryParam("key",youtubeApiKey)
                .queryParam("videoId", videoId);
        return builder;

    }
    public UriComponentsBuilder getChannelsRequestBuilder(String userId){

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme("https").host(youtubeApiHost).path("youtube/v3/subscriptions")
                .queryParam("part", "snippet")
                .queryParam("maxResults","50")
                .queryParam("key",youtubeApiKey)
                .queryParam("channelId", userId);
        //UC_zza04uKGj4IUzCeNNMnJw
        return builder;

    }
    @Value("${youtube.host}")
    public void setNameStatic(String youtubeHost){
        UrlService.this.youtubeHost = youtubeHost;
    }
    public UriComponentsBuilder getPingUrl(String userId){
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme("https").host(youtubeApiHost).path("youtube/v3/subscriptions")
                .queryParam("maxResults","1")
                .queryParam("key",youtubeApiKey)
                .queryParam("channelId", userId);
        //UC_zza04uKGj4IUzCeNNMnJw
        return builder;
    }
    static public String getChannelUrl(String userId){
        UriComponents builder = UriComponentsBuilder.newInstance()
                .scheme("http").host(youtubeHost).path("/channel/{channelId}")
                .buildAndExpand(userId);
        return builder.toUriString();
    }

}
