package com.andreyzholudev.gasstation.dataaccess.entities;

/**
 * Created by AndreyZholudev on 4/19/2016.
 */
public class Tag {

    public int id;
    public String tagName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Tag(int id, String tagName) {
        this.id = id;
        this.tagName = tagName;
    }

}
