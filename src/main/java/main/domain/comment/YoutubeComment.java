package main.domain.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Setter;

import java.util.Date;
@Setter
public class YoutubeComment {
     private String id;
     private YotubeCommentSnippet snippet;


     public String getId() {
          return id;
     }

     public YotubeCommentSnippet getSnippet() {
          return snippet;
     }


}
