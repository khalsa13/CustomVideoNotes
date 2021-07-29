package com.example.videonotesph1;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface dao {

    @Query("Select * from note")
    List<Note> loadAllRecords();

    @Query("Select * from note where note_id=:id")
    Note loadById(String id);

/*    @Query("Select * from note where note_id=:id and note_content like '%' || :str|| '%'")
    Note loadBySubstring(String id,String str);*/

    @Insert(onConflict = REPLACE)
    void insertRecord(Note note);


}
