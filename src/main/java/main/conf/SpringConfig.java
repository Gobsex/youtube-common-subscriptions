package main.conf;

import main.service.UrlService;
import main.service.YoutubeCommentService;
import main.service.YoutubeDataService;
import main.service.YoutubeErrorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class SpringConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    public UrlService urlService(){
        return new UrlService();
    }
    @Bean
    @RequestScope
    public YoutubeErrorService errorService(){
        return new YoutubeErrorService();
    }
    @Bean
    public YoutubeDataService youtubeDataService(){
        return new YoutubeDataService();
    }
    @Bean
    public YoutubeCommentService youtubeCommentService(){
        return new YoutubeCommentService();
    }
}
