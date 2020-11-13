package main.service;

import main.domain.channel.YoutubeChannelResponse;
import main.domain.comment.Replies;
import main.domain.comment.YoutubeComment;
import main.domain.comment.YoutubeCommentResponse;
import main.domain.comment.YoutubeСommentItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.*;

@Service
public class YoutubeCommentService {
    @Autowired
    UrlService urlService;

    @Autowired
    YoutubeErrorService youtubeErrorService;


    public List<String> getListOfUserIdByVideoId(String videoId){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = urlService.getCommentsRequestBuilder(videoId);

        ResponseEntity<YoutubeCommentResponse> response = restTemplate.getForEntity(builder.toUriString(), YoutubeCommentResponse.class);
        YoutubeCommentResponse data = response.getBody();

        Set<String > userIds=  new HashSet<>();
        List<String> testUserId = new ArrayList<>();
        while (data.getNextPageToken()!=null){
            builder.replaceQueryParam("pageToken",data.getNextPageToken());
            response = restTemplate.getForEntity(builder.build(false).toUriString(), YoutubeCommentResponse.class);


            List<YoutubeСommentItem> items = data.getItems();
            for (YoutubeСommentItem commentElement : items) {
                userIds.add(commentElement.getSnippet().getTopLevelYoutubeComment().getSnippet().getAuthorChannelId().getValue());
                Replies elementReplies = commentElement.getReplies();
                if(elementReplies!=null){
                    List<YoutubeComment> replies = elementReplies.getComments();
                    for (YoutubeComment reply: replies) {
                        userIds.add(reply.getSnippet().getAuthorChannelId().getValue());
                    }
                }
            }

            //todo

            data = response.getBody();

        }
        return new ArrayList<>(userIds);
    }
}
