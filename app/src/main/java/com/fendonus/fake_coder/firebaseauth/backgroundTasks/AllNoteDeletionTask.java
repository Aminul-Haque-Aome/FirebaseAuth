package com.fendonus.fake_coder.firebaseauth.backgroundTasks;

import android.os.AsyncTask;
import com.fendonus.fake_coder.firebaseauth.dataAccessObjects.NoteDataAccessor;

public class AllNoteDeletionTask extends AsyncTask<Void, Void, Void> {
    private NoteDataAccessor mNoteDataAccessor;

    public AllNoteDeletionTask(NoteDataAccessor mNoteDataAccessor) {
        this.mNoteDataAccessor = mNoteDataAccessor;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mNoteDataAccessor.deleteAllNotes();
        return null;
    }
}
