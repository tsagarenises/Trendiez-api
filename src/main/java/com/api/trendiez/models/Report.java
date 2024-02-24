package com.api.trendiez.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "report")
public class Report {

    @Id
    private String id;

    private String comment;

    @DBRef
    private Story story;

    public Report() {
    }

    public Report(String comment, Story story) {
        this.comment = comment;
        this.story = story;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id='" + id + '\'' +
                ", comment='" + comment + '\'' +
                ", story=" + story +
                '}';
    }
}
