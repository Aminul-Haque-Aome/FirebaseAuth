package com.fendonus.fake_coder.firebaseauth.dataAccessObjects;

import android.arch.lifecycle.LiveData;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.fendonus.fake_coder.firebaseauth.models.NoteModel;

@Dao
public interface NoteDataAccessor {

    @Insert
    void insertNote(NoteModel noteModel);

    @Update
    void updateNote(NoteModel noteModel);

    @Delete
    void deleteNote(NoteModel noteModel);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY priority_column DESC")
    LiveData<List<NoteModel>> getAllNotes();

}
