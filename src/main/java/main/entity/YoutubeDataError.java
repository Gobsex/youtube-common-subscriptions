package main.entity;

import lombok.Data;

import java.util.Objects;

@Data
public class YoutubeDataError {
    private YoutubeDataErrors error;
    private String cause;

    public YoutubeDataError(YoutubeDataErrors error, String cause) {
        this.error = error;
        this.cause = cause;
    }
    public YoutubeDataError(YoutubeDataErrors error) {
        this.error = error;
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
