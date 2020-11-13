package main.entity;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@Data
public class YoutubeDataError {
    private HttpStatus status;
    private String cause;

    public YoutubeDataError(HttpStatus status, String cause) {
        this.status = status;
        this.cause = cause;
    }
    public YoutubeDataError(HttpStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YoutubeDataError that = (YoutubeDataError) o;
        return Objects.equals(cause, that.cause);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cause);
    }
}
