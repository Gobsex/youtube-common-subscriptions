package main.domain;

import lombok.Data;

@Data
class RecourseIdResponse {
    private String kind;
    private String channelId;

    public String getChannelId() {
        return channelId;
    }
}