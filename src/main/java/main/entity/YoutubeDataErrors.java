package main.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
@JsonFormat(shape=JsonFormat.Shape.OBJECT)

public enum YoutubeDataErrors {

    USERNOTFOUND(404,"user not found"), PRIVATECHANNELS(403,"user has private channels"),EMPTYFIELD(400,"empty field")
    ,IDENTICALUSERS(404,"identical users"),ERROR(500,"something went wrong");

    private int code;
    private String type;

    YoutubeDataErrors(int code, String type) {
        this.code = code;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
