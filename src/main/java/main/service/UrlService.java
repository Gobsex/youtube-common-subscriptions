package main.service;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UrlService {
    static public UriComponentsBuilder getChannelsRequestBuilder(String userId){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://youtube.googleapis.com/youtube/v3/subscriptions")
                .queryParam("part", "snippet")
                .queryParam("maxResults","50")
                .queryParam("key","AIzaSyDmxNAr2Jt_pAYKmeKE7Xu3id3SHJyw-UU")
                .queryParam("channelId", userId);
        //UC_zza04uKGj4IUzCeNNMnJw
        return builder;
    }
    static public UriComponentsBuilder getPingUrl(String userId){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://youtube.googleapis.com/youtube/v3/subscriptions")
                .queryParam("maxResults","1")
                .queryParam("key","AIzaSyDmxNAr2Jt_pAYKmeKE7Xu3id3SHJyw-UU")
                .queryParam("channelId", userId);
        //UC_zza04uKGj4IUzCeNNMnJw
        return builder;
    }
    static public String getChannelUrl(String userId){
        UriComponents builder = UriComponentsBuilder.newInstance()
                .scheme("http").host("www.youtube.com").path("/channel/{channelId}")
                .buildAndExpand(userId);
        return builder.toUriString();
    }

}
