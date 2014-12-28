package com.hromadske.tv.ck.entities;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by cheb on 12/27/14.
 */
public class BaseEntity {
    private int id;
    private String title;
    @JsonProperty("introtext")
    private String introText;
    @JsonProperty("fulltext")
    private String fullText;
    private long created;
    private String video;
    private String image;

    public BaseEntity() {
    }

    public BaseEntity(int id, String title, String introText, String fullText, long created, String video, String image) {
        this.id = id;
        this.title = title;
        this.introText = introText;
        this.fullText = fullText;
        this.created = created;
        this.video = video;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroText() {
        return introText;
    }

    public void setIntroText(String introText) {
        this.introText = introText;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

