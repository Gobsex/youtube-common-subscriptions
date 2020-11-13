package main;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Data{


    private long id;
    private boolean include_reel;
    private boolean fetch_mutual;
    private int first;
//    private String after;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isInclude_reel() {
        return include_reel;
    }

    public void setInclude_reel(boolean include_reel) {
        this.include_reel = include_reel;
    }

    public boolean isFetch_mutual() {
        return fetch_mutual;
    }

    public void setFetch_mutual(boolean fetch_mutual) {
        this.fetch_mutual = fetch_mutual;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }


}
