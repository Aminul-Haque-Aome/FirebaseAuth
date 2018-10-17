package com.fendonus.fake_coder.firebaseauth.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "note_table")
public class NoteModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_column")
    private int id;

    @ColumnInfo(name = "title_column")
    private String title;

    @ColumnInfo(name = "description_column")
    private String description;

    @ColumnInfo(name = "priority_column")
    private String priority;

    public NoteModel(String title, String description, String priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
