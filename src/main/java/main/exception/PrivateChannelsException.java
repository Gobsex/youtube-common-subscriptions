package main.exception;

public class PrivateChannelsException extends Exception {
    public PrivateChannelsException() {
        super();
    }

    public PrivateChannelsException(String message) {
        super(message);
    }

    public PrivateChannelsException(String message, Throwable cause) {
        super(message, cause);
    }

    public PrivateChannelsException(Throwable cause) {
        super(cause);
    }

    protected PrivateChannelsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
