package main.service;

import main.domain.channel.YoutubeChannelItem;
import main.domain.channel.YoutubeChannelResponse;
import main.entity.YoutubeChannelFormatted;
import main.exception.YoutubeDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

@Component
public class YoutubeSubscriptionsService {
    @Autowired
    UrlService urlService;
    @Autowired
    YoutubeErrorService youtubeErrorService;

    public List<YoutubeChannelFormatted> getListOfChannels(@NonNull String userId) {
        System.out.println("begin getListOfChannels");

        long before = System.currentTimeMillis();

        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = urlService.getChannelsRequestBuilder(userId);
        ResponseEntity<YoutubeChannelResponse> response = restTemplate.getForEntity(builder.toUriString(), YoutubeChannelResponse.class);

        YoutubeChannelResponse data = response.getBody();
        List<YoutubeChannelFormatted> list = new ArrayList<>();
        list.addAll(loadChannels(data));
        while (data.getNextPageToken()!=null){

            builder.replaceQueryParam("pageToken",data.getNextPageToken());
            response = restTemplate.getForEntity(builder.toUriString(), YoutubeChannelResponse.class);

            //todo

            data = response.getBody();
            list.addAll(loadChannels(data));
        }
        long after = System.currentTimeMillis();
        System.out.println("getListOfChannels :" + (after-before));
        return list;
    }
    public void validateUsers(@NonNull List<String> userIds) throws YoutubeDataException, IOException {

        for (int i = 0; i < userIds.size()-1; i++) {
            for (int j = i+1; j < userIds.size() ; j++) {
                if(userIds.get(i).trim().equals(userIds.get(j).trim())){
                    userIds.remove(j);
                    youtubeErrorService.addError(HttpStatus.CONFLICT,userIds.get(i));
                    j--;
                }
            }
        }

        if (youtubeErrorService.hasErrors()){
            throw new YoutubeDataException();
        }
        for (int i = 0; i < userIds.size(); i++) {
            HttpURLConnection request = (HttpURLConnection) new URL(urlService.getPingUrl(userIds.get(i)).toUriString()).openConnection();
            int responseCode = request.getResponseCode();
            if (responseCode >= 400 && responseCode < 500) {
                if (responseCode == 404) {
                    youtubeErrorService.addError(HttpStatus.NOT_FOUND, "userId[" + i + "]");
                }
                else if (responseCode == 403) {
                    youtubeErrorService.addError(HttpStatus.FORBIDDEN, "userId[" + i + "]");
                }
                else youtubeErrorService.addError(HttpStatus.BAD_REQUEST);
                userIds.remove(i);
                i--;
            }
        }
        System.out.println(userIds.size());
        if (youtubeErrorService.hasErrors()){
            throw new YoutubeDataException();
        }
    }

    private List<YoutubeChannelFormatted> loadChannels(@NonNull YoutubeChannelResponse data){

        List<YoutubeChannelFormatted> list = new ArrayList<>(50);

        List<YoutubeChannelItem> items = data.getItems();
        for (YoutubeChannelItem i:items) {
            list.add(new YoutubeChannelFormatted(i));
        }
        return list;
    }






    public List<YoutubeChannelFormatted> getListOfAllChannels(@NonNull List<String> userIds) throws YoutubeDataException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        List<YoutubeChannelFormatted> allChannels = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < userIds.size(); i++) {
            executorService.submit(new ListOfChannelsTread(userIds.get(i),"userId["+i+"]",allChannels));
        }
        executorService.shutdown();
        executorService.awaitTermination(1000, TimeUnit.SECONDS);
        if (youtubeErrorService.hasErrors()){
            throw new YoutubeDataException();
        }
        return allChannels;
    }
    private class ListOfChannelsTread implements Runnable{
        private String userId;
        private String cause;
        private List<YoutubeChannelFormatted> list;
        public ListOfChannelsTread(String userId,String cause,List<YoutubeChannelFormatted> list) {
            this.list = list;
            this.userId= userId;
            this.cause = cause;
        }


        @Override
        public void run() {
            List<YoutubeChannelFormatted> listOfChannels= null;
            try {
                listOfChannels = getListOfChannels(userId);
            }
            catch (HttpClientErrorException e) {
                if (e.getRawStatusCode()==403){
                    youtubeErrorService.addError(HttpStatus.FORBIDDEN,cause);
                }
                else if(e.getRawStatusCode()==404){
                    youtubeErrorService.addError(HttpStatus.NOT_FOUND,cause);
                }
            }
            list.addAll(listOfChannels);
        }
    }
}
