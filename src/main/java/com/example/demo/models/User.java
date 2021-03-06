package com.example.demo.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by maksim on 6/22/17.
 */
@Entity
@Table(name = "users")
public class User extends BaseModel {

    @NotEmpty
    @Column(name = "name", nullable = false, unique = true)
    private String name;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Note> notes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Note> getNotes() {
//        return notes;
//    }
//
//    public void setNotes(List<Note> notes) {
//        this.notes = notes;
//    }
}
