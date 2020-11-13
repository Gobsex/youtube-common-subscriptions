package main.domain.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Setter;

import java.util.List;
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Replies {
    private List<YoutubeComment> comments;

    public List<YoutubeComment> getComments() {
        return comments;
    }
}
