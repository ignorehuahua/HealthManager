package com.njzhikejia.echohealth.healthlife.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class Message {

    @Id(autoincrement = true)
    private long id;
    private String title;
    private String content;

    public Message() {
    }

    public Message(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Generated(hash = 722260467)
    public Message(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
}