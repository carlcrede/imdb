package kea.design.exam.imdb.models;

public class AjaxRequest {

    private String mbid;
    private String rating;
    private String userId;

    public AjaxRequest(String mbid, String rating, String userId) {
        this.mbid = mbid;
        this.rating = rating;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}

