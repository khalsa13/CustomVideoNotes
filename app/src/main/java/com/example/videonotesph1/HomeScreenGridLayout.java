package com.example.videonotesph1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenGridLayout extends AppCompatActivity {
    FloatingActionButton switch_activity;
    GithubTypeConverters gs;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        setContentView(R.layout.activity_home_screen_grid_layout);
        switch_activity = findViewById(R.id.floating_action_button);

        GridView gridView = findViewById(R.id.gridview);
        gs = new GithubTypeConverters();
        List<Note> list  = RecordsDatabase.getInstance(getApplicationContext()).recordDao().loadAllRecords();
        ArrayList<HomeModel>savedNotes = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            String str = list.get(i).getContent();
            Thumbnail thumbnail  = RecordsDatabase.getInstance(getApplicationContext()).thumbnailDao().loadById(list.get(i).getNote_id());
            List<recordsModel>ls = gs.stringToSomeObjectList(str);
            savedNotes.add(new HomeModel(ls.get(0).getSubject(),thumbnail.getThumbnail(),thumbnail.getId()));
        }
        HomeScreenAdapter homeScreenAdapter = new HomeScreenAdapter(this, savedNotes);
        gridView.setAdapter(homeScreenAdapter);
        switch_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_grid_layout);
        try{
            Bundle extras = getIntent().getExtras();
            String value1 = extras.getString(Intent.EXTRA_TEXT);
            String toPass = value1.substring(8);
            Intent i = new Intent(this,MainActivity.class);
            i.putExtra("youtube_videoId",toPass);
            startActivity(i);
            finish();
        }
        catch (Exception e){
            Log.e("errormsg",e.toString());
        }
        switch_activity = findViewById(R.id.floating_action_button);
        GridView gridView = findViewById(R.id.gridview);
        gs = new GithubTypeConverters();
        List<Note> list  = RecordsDatabase.getInstance(getApplicationContext()).recordDao().loadAllRecords();
        ArrayList<HomeModel>savedNotes = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            String str = list.get(i).getContent();
            Thumbnail thumbnail  = RecordsDatabase.getInstance(getApplicationContext()).thumbnailDao().loadById(list.get(i).getNote_id());
            List<recordsModel>ls = gs.stringToSomeObjectList(str);
            savedNotes.add(new HomeModel(ls.get(0).getSubject(),thumbnail.getThumbnail(),thumbnail.getId()));
        }
        HomeScreenAdapter homeScreenAdapter = new HomeScreenAdapter(this, savedNotes);
        gridView.setAdapter(homeScreenAdapter);
        switch_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}
