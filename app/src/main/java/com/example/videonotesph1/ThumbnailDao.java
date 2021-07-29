package com.example.videonotesph1;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface ThumbnailDao {
    @Query("Select * from Thumbnail where id=:id")
    Thumbnail loadById(String id);

    @Query("Select * from Thumbnail")
    List<Thumbnail> loadAll();

    @Insert(onConflict = IGNORE)
    void insertRecord(Thumbnail thumbnail);
}
