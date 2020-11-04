package main.exception;

public class YoutubeDataException extends Exception {
    public YoutubeDataException() {
        super();
    }

    public YoutubeDataException(String message) {
        super(message);
    }

    public YoutubeDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public YoutubeDataException(Throwable cause) {
        super(cause);
    }

    protected YoutubeDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
