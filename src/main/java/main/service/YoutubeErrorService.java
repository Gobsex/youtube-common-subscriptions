package main.service;

import main.entity.YoutubeDataError;
import main.entity.YoutubeDataErrors;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class YoutubeErrorService {
//    List<YoutubeDataError> errors = Collections.synchronizedList(new ArrayList<>());
    Set<YoutubeDataError> errors = Collections.synchronizedSet(new HashSet<>());

    public boolean hasErrors(){
        return !errors.isEmpty();
    }
    public void addError(YoutubeDataErrors error,String userId){
        errors.add(new YoutubeDataError(error,userId));
    }
    public void addError(YoutubeDataErrors error){
        errors.add(new YoutubeDataError(error));
    }

    public Set<YoutubeDataError> getErrors() {
        return errors;
    }

    public void setErrors(Set<YoutubeDataError> errors) {
        this.errors = errors;
    }

    public void clear(){
        errors.clear();
    }
}
