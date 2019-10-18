package com.example.androidtp1.Database;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.androidtp1.DAO.PersonDAO;
import com.example.androidtp1.model.PersonDB;

@Database(entities = {PersonDB.class}, version = 1)
public abstract class AppDatabase  extends RoomDatabase {

    private static AppDatabase instance;

    public abstract PersonDAO personDAO();


    public static synchronized AppDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "person_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(AppDatabase.instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private PersonDAO peopleDAO;

        private PopulateDbAsyncTask(AppDatabase db) {
            peopleDAO = db.personDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            PersonDB p1 = new PersonDB("Ada","Lovelace", "02/Oct/1995", "Rennes", "0668852", "ill-de-france");
            PersonDB p2 = new PersonDB("Charles","Babbage", "02/Oct/1995", "Rennes", "0668852", "ill-de-france");

            peopleDAO.insert(p1);
            peopleDAO.insert(p2);
            peopleDAO.insert(p1);
            peopleDAO.insert(p2);


            return null;
        }
    }
}
