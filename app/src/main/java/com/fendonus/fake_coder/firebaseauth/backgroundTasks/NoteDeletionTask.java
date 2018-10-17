package com.fendonus.fake_coder.firebaseauth.backgroundTasks;

import android.os.AsyncTask;

import com.fendonus.fake_coder.firebaseauth.dataAccessObjects.NoteDataAccessor;
import com.fendonus.fake_coder.firebaseauth.models.NoteModel;

public class NoteDeletionTask extends AsyncTask<NoteModel, Void, Void> {
    private NoteDataAccessor mNoteDataAccessor;

    public NoteDeletionTask(NoteDataAccessor mNoteDataAccessor) {
        this.mNoteDataAccessor = mNoteDataAccessor;
    }

    @Override
    protected Void doInBackground(NoteModel... noteModels) {
        mNoteDataAccessor.deleteNote(noteModels[0]);
        return null;
    }
}
