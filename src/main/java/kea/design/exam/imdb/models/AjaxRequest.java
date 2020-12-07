package kea.design.exam.imdb.models;

public class AjaxRequest {

    private Object data;

    public AjaxRequest(Object data) {

        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

