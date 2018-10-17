package com.fendonus.fake_coder.firebaseauth.backgroundTasks;

import android.os.AsyncTask;

import com.fendonus.fake_coder.firebaseauth.dataAccessObjects.NoteDataAccessor;
import com.fendonus.fake_coder.firebaseauth.models.NoteModel;

public class NoteInsertionTask extends AsyncTask<NoteModel, Void, Void> {
    private NoteDataAccessor mNoteDataAccessor;

    public NoteInsertionTask(NoteDataAccessor mNoteDataAccessor) {
        this.mNoteDataAccessor = mNoteDataAccessor;
    }

    @Override
    protected Void doInBackground(NoteModel... noteModels) {
        mNoteDataAccessor.insertNote(noteModels[0]);
        return null;
    }
}
