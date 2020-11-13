package main.service;

import main.entity.YoutubeDataError;
import main.entity.YoutubeDataErrors;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.*;

@Component
@RequestScope
public class YoutubeErrorService {
//    List<YoutubeDataError> errors = Collections.synchronizedList(new ArrayList<>());
    private Set<YoutubeDataError> errors = Collections.synchronizedSet(new HashSet<>());
    private HttpStatus mainStatus;

    public void setIgnoreErrors(boolean ignoreErrors) {
        this.ignoreErrors = ignoreErrors;
    }

    private boolean ignoreErrors;

    public HttpStatus getMainStatus() {
        return mainStatus;
    }

    public boolean hasErrors(){
        if(ignoreErrors){
            return false;
        }
        return !errors.isEmpty();
    }

    public void addError(@NonNull HttpStatus status,String userId){
        mainStatus = status;
        errors.add(new YoutubeDataError(status,userId));
    }
    public void addError(@NonNull HttpStatus status){
        errors.add(new YoutubeDataError(status));
    }

    public Set<YoutubeDataError> getErrors() {
//        Set<YoutubeDataError> temp =  new HashSet<>(errors);
//        errors.clear();
        return errors;
    }

    public void setErrors(Set<YoutubeDataError> errors) {
        this.errors = errors;
    }

    public void clear(){
        errors.clear();
    }
}
