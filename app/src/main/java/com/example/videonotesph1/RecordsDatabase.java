package com.example.videonotesph1;

import android.content.Context;
import android.provider.SyncStateContract;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Database(entities = {Note.class,Thumbnail.class},version = 2)
@TypeConverters({GithubTypeConverters.class})

public abstract class RecordsDatabase extends RoomDatabase {
    public abstract dao recordDao();
    public abstract ThumbnailDao thumbnailDao();
    private static RecordsDatabase DB;

    public static RecordsDatabase getInstance(Context context) {
        if(DB==null){ // <= ERROR HERE, should be == null
            DB = Room.databaseBuilder(context.getApplicationContext(),
                    RecordsDatabase.class, "ROOM_DB").allowMainThreadQueries().build();
        }
        return DB;
    }

    public void cleanUp(){
        DB = null;
    }
}
