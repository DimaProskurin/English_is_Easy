package com.example.englishiseasy;

/**
 * Created by Дмитрий on 04.05.2016.
 */
public class Sentence {
    private String text;
    private String group;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Sentence(String text, String group, String time){
        this.text = text;
        this.group = group;
        this.time = time;
    }
}
