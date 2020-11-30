package kea.design.exam.imdb.models;

public class Track {
    private String length;
    private String name;

    public Track(String length, String name) {
        this.length = length;
        this.name = name;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
