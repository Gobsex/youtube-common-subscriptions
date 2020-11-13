package main.service;

import main.entity.YoutubeChannelFormatted;
import main.exception.YoutubeDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class YoutubeDataService {
    @Autowired
    YoutubeSubscriptionsService youtubeSubscriptionsService;
    @Autowired
    YoutubeCommentService youtubeCommentService;
    @Autowired
    YoutubeErrorService youtubeErrorService;

    public List<YoutubeChannelFormatted> getCommonChannelsByUserIds(@NonNull List<String> userIds, boolean ignoreErrors, double threshold) throws IOException, YoutubeDataException, InterruptedException {
        youtubeErrorService.setIgnoreErrors(ignoreErrors);
        youtubeSubscriptionsService.validateUsers(userIds);
        List<YoutubeChannelFormatted> listOfAllChannels = youtubeSubscriptionsService.getListOfAllChannels(userIds);
        Set<YoutubeChannelFormatted> uniqueChannels = new HashSet<>(listOfAllChannels);
        List<YoutubeChannelFormatted> channelList = new ArrayList<>();

        for (YoutubeChannelFormatted channel : uniqueChannels) {
            channel.setFrequency((double)(Collections.frequency(listOfAllChannels, channel)-1)/(userIds.size()-1));
            channel.calculateHref();
            channelList.add(channel);
        }
        return filterByFrequency(channelList, threshold);
    }

    private List<YoutubeChannelFormatted> filterByFrequency(@NonNull List<YoutubeChannelFormatted> list, double threshold){
        List<YoutubeChannelFormatted> result = new ArrayList<>();
        for (YoutubeChannelFormatted channel:list) {
            if(channel.getFrequency()>=threshold){
                result.add(channel);
            }
        }
        return result;
    }

    public List<YoutubeChannelFormatted> getCommonChannelsByVideoComments(String videoId, double threshold) throws InterruptedException, YoutubeDataException, IOException {
        List<String> userIds = youtubeCommentService.getListOfUserIdByVideoId(videoId);
        System.out.println(userIds.size());
        return getCommonChannelsByUserIds(userIds,true,threshold);
    }
    public List<String> getListOfUserIdByVideoComments(String videoId) throws InterruptedException, YoutubeDataException, IOException {
        return youtubeCommentService.getListOfUserIdByVideoId(videoId);
//        return getCommonChannelsByUserIds(userIds,true,threshold);
    }

}
