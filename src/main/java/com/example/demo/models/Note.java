package com.example.demo.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by maksim on 6/22/17.
 */
@Entity
@Table(name = "notes")
public class Note extends BaseModel {

    @NotEmpty
    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @Column (name = "user_id", updatable = false, nullable = false, insertable = false)
    private Integer userId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
