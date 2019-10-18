package com.example.androidtp1.Services;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.androidtp1.DAO.PersonDAO;
import com.example.androidtp1.Database.AppDatabase;
import com.example.androidtp1.model.PersonDB;

import java.util.List;

public class Repository {

    private PersonDAO personDAO;
    private LiveData<List<PersonDB>> people;

    public Repository(Application application){
        AppDatabase database = AppDatabase.getInstance((application));
        personDAO = database.personDAO();
        people = personDAO.getAll();
    }

    public void  insert(PersonDB personDB) {
        new InsertPersonAsyncTask(personDAO).execute(personDB);
    }


    public void delete(PersonDB personDB) {
        new DeletePersonAsyncTask(personDAO).execute(personDB);
    }

    public void deleteAllPeople() {
        new DeleteAllPeoplesAsyncTask(personDAO).execute();
    }

    public LiveData<List<PersonDB>> getAllPeople() {
        return  people;
    }

    private static class InsertPersonAsyncTask extends AsyncTask<PersonDB, Void, Void> {
        private PersonDAO personDAO;

        private InsertPersonAsyncTask(PersonDAO personDAO) {
            this.personDAO = personDAO;
        }

        @Override
        protected Void doInBackground(PersonDB... people) {
            personDAO.insert(people[0]);
            return null;
        }
    }


    private static class DeletePersonAsyncTask extends AsyncTask<PersonDB, Void, Void> {
        private PersonDAO personDAO;

        private DeletePersonAsyncTask(PersonDAO personDAO) {
            this.personDAO = personDAO;
        }

        @Override
        protected Void doInBackground(PersonDB... people) {
            personDAO.delete(people[0]);
            return null;
        }
    }

    private static class DeleteAllPeoplesAsyncTask extends AsyncTask<Void, Void, Void> {
        private PersonDAO pplDao;

        private DeleteAllPeoplesAsyncTask(PersonDAO pplDao) {
            this.pplDao = pplDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            pplDao.deleteAll();
            return null;
        }
    }
}
