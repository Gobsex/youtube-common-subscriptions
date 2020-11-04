package main.service;

import main.domain.YoutubeChannelResponse;
import main.domain.YoutubeDataResponse;
import main.entity.YoutubeChannel;
import main.entity.YoutubeDataErrors;
import main.exception.YoutubeDataException;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;
import java.util.concurrent.*;

@Component
public class YoutubeDataService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    YoutubeErrorService youtubeErrorService;

    public List<YoutubeChannel> getListOfChannels(@NonNull String userId) {
        long before = System.currentTimeMillis();

        RestTemplate restTemplate1 = new RestTemplate();
        UriComponentsBuilder builder = UrlService.getChannelsRequestBuilder(userId);
        ResponseEntity<YoutubeDataResponse> response = restTemplate1.getForEntity(builder.toUriString(), YoutubeDataResponse.class);

        YoutubeDataResponse data = response.getBody();
        List<YoutubeChannel> list = new ArrayList<>();
        list.addAll(loadChannels(data));
        while (data.getNextPageToken()!=null){

            builder.replaceQueryParam("pageToken",data.getNextPageToken());
            response = restTemplate1.getForEntity(builder.toUriString(), YoutubeDataResponse.class);

            //todo

            data = response.getBody();
            list.addAll(loadChannels(data));
        }
        long after = System.currentTimeMillis();
        System.out.println("getListOfChannels :" + (after-before));
        return list;
    }
    private void validateUsers(String userId1, String userId2) throws YoutubeDataException {
        if (userId1.trim().equals("")||userId1==null){
            youtubeErrorService.addError(YoutubeDataErrors.EMPTYFIELD,"userId1");
        }
        if (userId2.trim().equals("")||userId2==null){
            youtubeErrorService.addError(YoutubeDataErrors.EMPTYFIELD,"userId2");
        }

        if (userId1.equals(userId2)&&(!userId1.trim().equals("")&&userId1!=null&&!userId2.trim().equals("")&&userId2!=null)) {
            youtubeErrorService.addError(YoutubeDataErrors.IDENTICALUSERS);
        }
        if (youtubeErrorService.hasErrors()){
            throw new YoutubeDataException();
        }

        try {
            HttpURLConnection request = (HttpURLConnection)new URL(UrlService.getPingUrl(userId1).toUriString()).openConnection();
            int responseCode = request.getResponseCode();
            if(responseCode==404){
                youtubeErrorService.addError(YoutubeDataErrors.USERNOTFOUND,"userId1");
            }
            else if (responseCode==403){
                youtubeErrorService.addError(YoutubeDataErrors.PRIVATECHANNELS,"userId1");
            }
            HttpURLConnection request1 = (HttpURLConnection)new URL(UrlService.getPingUrl(userId2).toUriString()).openConnection();
            int responseCode1 = request1.getResponseCode();
            if(responseCode1==404){
                youtubeErrorService.addError(YoutubeDataErrors.USERNOTFOUND,"userId2");
            }
            else if (responseCode1==403){
                youtubeErrorService.addError(YoutubeDataErrors.PRIVATECHANNELS,"userId2");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (youtubeErrorService.hasErrors()){
            throw new YoutubeDataException();
        }
    }

    private List<YoutubeChannel> loadChannels(@NonNull YoutubeDataResponse data){

        List<YoutubeChannel> list = new ArrayList<>(50);

        List<YoutubeChannelResponse> items = data.getItems();
        for (YoutubeChannelResponse i:items) {
            list.add(new YoutubeChannel(i));
        }

        return list;
    }


    private class ListOfChannelsTread implements Callable<List<YoutubeChannel>>{
        private String userId;
        private String cause;
        public ListOfChannelsTread(String userId,String cause) {
            this.userId= userId;
            this.cause = cause;
        }


        @Override
        public List<YoutubeChannel> call() throws Exception {
            List<YoutubeChannel> listOfChannels= null;
            try {
                listOfChannels = getListOfChannels(userId);
            }
            catch (HttpClientErrorException e) {
                if (e.getRawStatusCode()==403){

                    youtubeErrorService.addError(YoutubeDataErrors.PRIVATECHANNELS,cause);
                }
                else if(e.getRawStatusCode()==404){
                    youtubeErrorService.addError(YoutubeDataErrors.USERNOTFOUND,cause);

                }
            }
            return listOfChannels;
        }
    }



    public List<YoutubeChannel> getListOfCommonChannels(@NonNull String userId1,@NonNull String userId2) throws YoutubeDataException {
        validateUsers(userId1,userId2);

        if(youtubeErrorService.hasErrors()){
            throw new YoutubeDataException();
        }

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<YoutubeChannel> listOfChannels1 = null;
        List<YoutubeChannel> listOfChannels2 = null;
        List<YoutubeChannel> listOfCommonChannels = new ArrayList<>(20);
        long before = System.currentTimeMillis();

        Future<List<YoutubeChannel>> listFuture1 = executorService.submit(new ListOfChannelsTread(userId1,"userId1"));
        Future<List<YoutubeChannel>> listFuture2 = executorService.submit(new ListOfChannelsTread(userId2,"userId2"));
        executorService.shutdown();
        long after = System.currentTimeMillis();
        System.out.println("2 tread starts :" + (after-before));

        try {
            long before1 = System.currentTimeMillis();
            executorService.awaitTermination(100, TimeUnit.SECONDS);
            listOfChannels1 = listFuture1.get();
            listOfChannels2 = listFuture2.get();
            long after1 = System.currentTimeMillis();
            System.out.println("2 treads done :" + (after1-before1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (youtubeErrorService.hasErrors()){
            throw new YoutubeDataException();
        }
        for (int i = 0; i < listOfChannels1.size(); i++) {
            for (int j = 0; j < listOfChannels2.size(); j++) {
                if(listOfChannels1.get(i).getId().equals(listOfChannels2.get(j).getId())){
                    listOfChannels1.get(i).calculateHref();
                    listOfCommonChannels.add(listOfChannels1.get(i));

                }
            }
        }
        return listOfCommonChannels;
    }
}
