package com.example.videonotesph1;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class GithubTypeConverters {
    private  Gson gson = new Gson();

    @TypeConverter
    public  List<recordsModel> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<recordsModel>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public  String someObjectListToString(List<recordsModel> someObjects) {
        return gson.toJson(someObjects);
    }
}
