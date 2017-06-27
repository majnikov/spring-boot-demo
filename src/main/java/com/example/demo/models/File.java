package com.example.demo.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by maksim on 6/22/17.
 */
@Entity
@Table(name = "files")
public class File extends BaseModel {

    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "note_id", updatable = false)
    private Note note;

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
