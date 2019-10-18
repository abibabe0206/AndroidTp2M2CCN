package com.example.androidtp1.model;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.androidtp1.Services.Repository;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<PersonDB>> people;

    public ViewModel(@NonNull Application application){
        super((application));
        repository = new Repository(application);
        people = repository.getAllPeople();
    }

    public void insert(PersonDB personDB){
        repository.insert(personDB);
    }

    public void delete(PersonDB personDB){
        repository.delete(personDB);
    }

    public LiveData<List<PersonDB>> getPeople() {
        return people;
    }
}
