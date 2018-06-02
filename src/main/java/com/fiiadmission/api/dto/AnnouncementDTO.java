package com.fiiadmission.api.dto;

import javax.persistence.Column;
import java.sql.Timestamp;

public class AnnouncementDTO {
    private Long id;
    private String info;
    private Timestamp expiry_date;
    private Timestamp publish_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Timestamp getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Timestamp expiry_date) {
        this.expiry_date = expiry_date;
    }

    public Timestamp getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(Timestamp publish_date) {
        this.publish_date = publish_date;
    }
}
