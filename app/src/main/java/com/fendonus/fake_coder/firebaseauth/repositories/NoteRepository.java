package com.fendonus.fake_coder.firebaseauth.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.util.List;

import com.fendonus.fake_coder.firebaseauth.backgroundTasks.AllNoteDeletionTask;
import com.fendonus.fake_coder.firebaseauth.backgroundTasks.NoteDeletionTask;
import com.fendonus.fake_coder.firebaseauth.backgroundTasks.NoteInsertionTask;
import com.fendonus.fake_coder.firebaseauth.backgroundTasks.NoteUpdatingTask;
import com.fendonus.fake_coder.firebaseauth.dataAccessObjects.NoteDataAccessor;
import com.fendonus.fake_coder.firebaseauth.databases.NoteDataBase;
import com.fendonus.fake_coder.firebaseauth.models.NoteModel;

public class NoteRepository {

    private NoteDataAccessor mNoteDataAccessor;
    private LiveData<List<NoteModel>> mNoteList;

    public NoteRepository(Application application) {
        NoteDataBase noteDataBase = NoteDataBase.getDataBaseInstance(application);
        mNoteDataAccessor = noteDataBase.getNoteDao();
        mNoteList = mNoteDataAccessor.getAllNotes();
    }

    public void insert(NoteModel noteModel) {
        new NoteInsertionTask(mNoteDataAccessor).execute(noteModel);
    }

    public void update(NoteModel noteModel) {
        new NoteUpdatingTask(mNoteDataAccessor).execute(noteModel);
    }

    public void delete(NoteModel noteModel) {
        new NoteDeletionTask(mNoteDataAccessor).execute(noteModel);
    }

    public void deleteAll() {
        new AllNoteDeletionTask(mNoteDataAccessor).execute();
    }

    public LiveData<List<NoteModel>> getList() {
        return mNoteList;
    }
}
