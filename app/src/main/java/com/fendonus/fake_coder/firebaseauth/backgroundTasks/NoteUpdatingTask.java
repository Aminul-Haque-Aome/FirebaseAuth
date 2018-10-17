package com.fendonus.fake_coder.firebaseauth.backgroundTasks;

import android.os.AsyncTask;

import com.fendonus.fake_coder.firebaseauth.dataAccessObjects.NoteDataAccessor;
import com.fendonus.fake_coder.firebaseauth.models.NoteModel;

public class NoteUpdatingTask extends AsyncTask<NoteModel, Void, Void> {
    private NoteDataAccessor mNoteDataAccessor;

    public NoteUpdatingTask(NoteDataAccessor mNoteDataAccessor) {
        this.mNoteDataAccessor = mNoteDataAccessor;
    }

    @Override
    protected Void doInBackground(NoteModel... noteModels) {
        mNoteDataAccessor.updateNote(noteModels[0]);
        return null;
    }
}
