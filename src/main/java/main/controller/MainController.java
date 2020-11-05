package main.controller;

import main.entity.YoutubeDataError;
import main.entity.YoutubeDataErrors;
import main.exception.YoutubeDataException;
import main.service.YoutubeErrorService;
import main.service.YoutubeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {
    @Autowired
    YoutubeErrorService youtubeErrorService;
    @Autowired
    YoutubeDataService service;
    @GetMapping("/")
    public ResponseEntity index(@RequestParam String userId1,
                                @RequestParam String userId2) {
        try {
            return new ResponseEntity(service.getListOfCommonChannels(userId1,userId2),HttpStatus.OK);
        } catch (YoutubeDataException e) {
            List<YoutubeDataError> errors = new ArrayList<>(youtubeErrorService.getErrors());

            youtubeErrorService.clear();
            return new ResponseEntity(errors,HttpStatus.BAD_REQUEST);
        }
    }
}
