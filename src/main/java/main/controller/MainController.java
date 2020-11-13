package main.controller;

import main.entity.YoutubeChannelFormatted;
import main.exception.YoutubeDataException;
import main.service.YoutubeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController
public class MainController {
    @Autowired
    YoutubeDataService youtubeDataService;
    @GetMapping("/subscriptions")
    public ResponseEntity<List<YoutubeChannelFormatted>> index(
            @RequestParam("userId") List<String> userIds,
            @RequestParam(defaultValue = "false") boolean ignoreErrors,
            @RequestParam(defaultValue = "1.0") double threshold)
            throws YoutubeDataException, InterruptedException, IOException {
        List<YoutubeChannelFormatted> commonChannels = youtubeDataService.getCommonChannelsByUserIds(userIds, ignoreErrors, threshold);
        return new ResponseEntity(commonChannels,HttpStatus.OK);
    }


    @GetMapping("/comments")
    public ResponseEntity<List<YoutubeChannelFormatted>> test(@RequestParam String videoId,@RequestParam(defaultValue = "1.0") double threshold)

            throws YoutubeDataException, InterruptedException, IOException {
        List<YoutubeChannelFormatted> common = youtubeDataService.getCommonChannelsByVideoComments(videoId, threshold);
        return new ResponseEntity(common,HttpStatus.OK);
    }


}
