package com.fendonus.fake_coder.firebaseauth.databases;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.fendonus.fake_coder.firebaseauth.config.Constant;
import com.fendonus.fake_coder.firebaseauth.dataAccessObjects.NoteDataAccessor;
import com.fendonus.fake_coder.firebaseauth.models.NoteModel;
import com.fendonus.fake_coder.firebaseauth.utilities.ToastUtility;

@Database(entities = {NoteModel.class}, version = 1)
public abstract class NoteDataBase extends RoomDatabase {
    private static NoteDataBase instance;

    public static synchronized NoteDataBase getDataBaseInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    NoteDataBase.class,
                    Constant.DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // ... this method is called once in a life time of an App
            ToastUtility.showLog("Database", "Created Successfully");
        }
    };

    public abstract NoteDataAccessor getNoteDao();

}
