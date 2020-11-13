package main.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RecourseId {
//    private String kind;
    private String channelId;

    public String getChannelId() {
        return channelId;
    }
}