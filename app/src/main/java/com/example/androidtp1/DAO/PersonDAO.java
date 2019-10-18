package com.example.androidtp1.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.androidtp1.model.PersonDB;

import java.util.List;

@Dao
public interface PersonDAO {

    @Query("SELECT * FROM person_table ")
    LiveData<List<PersonDB>> getAll();

    @Query("SELECT * FROM person_table WHERE personId IN (:personIds)")
    LiveData<List<PersonDB>> loadAllByIds(int[] personIds);


    @Query("SELECT * FROM person_table WHERE nom LIKE :first AND " +
            "prenom LIKE :last LIMIT 1")
    PersonDB findByName(String first, String last);

    @Insert
    void insertAll(PersonDB... people);

    @Insert
    void insert(PersonDB personDB);

    @Delete
    void delete(PersonDB personDB);

    @Delete
    void deleteAll(PersonDB... people);
}
