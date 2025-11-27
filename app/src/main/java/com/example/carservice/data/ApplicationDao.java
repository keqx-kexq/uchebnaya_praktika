package com.example.carservice.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface ApplicationDao {
    @Insert
    void insert(Application application);

    @Update
    void update(Application application);

    @Query("SELECT * FROM applications ORDER BY id DESC")
    List<Application> getAllApplications();

    @Query("SELECT COUNT(*) FROM applications WHERE task = 1")
    int getCompletedCount();

    @Query("SELECT COUNT(*) FROM applications WHERE task = 0")
    int getPendingCount();
}
