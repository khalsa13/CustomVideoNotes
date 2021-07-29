package com.example.videonotesph1;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Canvas_videoplayer_activity extends YouTubeBaseActivity {
    YouTubePlayerView playerView;
    YouTubePlayer running_Video;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    AppCompatImageView erase,write,redo,undo,add_page;
    com.divyanshu.draw.widget.DrawView drawView;
    AppCompatTextView title_Notes,viewmode;
    RecyclerView timingList;
    timerAdapter adapter;
    ArrayList<timeModel>list_0f_times, sendData ;
    com.example.videonotesph1.LockableScrollView scrollView;
    AppCompatButton save_note;
    String SAVE_START_TIME,PREV_START_TIME;
    int count=0,writeModeEnabled,flag=0;
    RelativeLayout extraBottomSpace;
    GithubTypeConverters gs;
    String videoId,videoTitle,Thumbnail_String;
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        running_Video.release();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Log.d("abcdmsg","hanji");
        running_Video.release();
        Thumbnail thumbnail =new Thumbnail();
        thumbnail.setId(videoId);
        thumbnail.setThumbnail(Thumbnail_String);
        RecordsDatabase.getInstance(getApplicationContext()).thumbnailDao().insertRecord(thumbnail);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_videoplayer_activity);
        Intent intent = getIntent();
        videoId = intent.getStringExtra("VIDEO_ID");
        videoTitle = intent.getStringExtra("VIDEO_TITLE");
        Thumbnail_String = intent.getStringExtra("VIDEO_THUMBNAIL");
        Log.d("printmsg",videoId+"  "+videoTitle+" "+Thumbnail_String);
        //intiializes
        gs = new GithubTypeConverters();
        playerView = findViewById(R.id.videoview);
        erase = findViewById(R.id.erase);
        write = findViewById(R.id.write);
        drawView = findViewById(R.id.writing_area);
        redo = findViewById(R.id.redo);
        undo = findViewById(R.id.undo);
        title_Notes = findViewById(R.id.title_Notes);
        add_page = findViewById(R.id.add_page);
        list_0f_times = new ArrayList<>();
        sendData = new ArrayList<>();
        scrollView = findViewById(R.id.scrollView);
        save_note = findViewById(R.id.save_note_full);
        viewmode = findViewById(R.id.viewmode);
        extraBottomSpace = findViewById(R.id.extraBottomSpace);
        setuptimerview();
        //other declarations
        save_note.setVisibility(View.INVISIBLE);
        save_note.setClickable(false);
        write.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.changeColor), android.graphics.PorterDuff.Mode.MULTIPLY);
        drawView.setStrokeWidth(6f);
        drawView.setColor(Color.BLACK);
        viewmode.setVisibility(View.INVISIBLE);
        writeModeEnabled = 1;
        //youtube pplayer initialization
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(videoId);
                running_Video = youTubePlayer;
                youTubePlayer.play();
                Note note_returned = RecordsDatabase.getInstance(getApplicationContext()).recordDao().loadById(videoId);
                if(note_returned != null){
                    Toast.makeText(getBaseContext(), "Saved Notes found", Toast.LENGTH_SHORT).show();
                    //old video //populate adpaters
                    save_note.setVisibility(View.INVISIBLE);
                    add_page.setVisibility(View.INVISIBLE);
                    viewmode.setVisibility(View.VISIBLE);
                    write.setVisibility(View.INVISIBLE);
                    erase.setVisibility(View.INVISIBLE);
                    redo.setVisibility(View.INVISIBLE);
                    undo.setVisibility(View.INVISIBLE);
                    extraBottomSpace.setBackgroundColor(Color.WHITE);
                    title_Notes.setBackgroundColor(Color.WHITE);
                    drawView.setColor(Color.WHITE);
                    scrollView.setBackgroundColor(Color.WHITE);
                    timingList.setBackgroundColor(Color.WHITE);
                    title_Notes.setHint("");
                    title_Notes.setText(videoTitle);
                    String str = note_returned.getContent();
                    List<recordsModel> ls = gs.stringToSomeObjectList(str);
                    for (int i = 0; i < ls.size(); i++) {
                        timeModel obj = new timeModel(ls.get(i).getTime(), ls.get(i).getPath());
                        obj.setTime(ls.get(i).getTime());
                        obj.setImage(ls.get(i).getPath());
                        sendData.add(obj);
                    }
                    writeModeEnabled = 0;
                    adapter = new timerAdapter(Canvas_videoplayer_activity.this, sendData, running_Video,
                            videoId, drawView, writeModeEnabled, scrollView);
                    timingList.setAdapter(adapter);
                }
                Log.d("success","loaded");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        running_Video = new YouTubePlayer() {
            @Override
            public void release() {

            }

            @Override
            public void cueVideo(String s) {

            }

            @Override
            public void cueVideo(String s, int i) {

            }

            @Override
            public void loadVideo(String s) {

            }

            @Override
            public void loadVideo(String s, int i) {

            }

            @Override
            public void cuePlaylist(String s) {

            }

            @Override
            public void cuePlaylist(String s, int i, int i1) {

            }

            @Override
            public void loadPlaylist(String s) {

            }

            @Override
            public void loadPlaylist(String s, int i, int i1) {

            }

            @Override
            public void cueVideos(List<String> list) {

            }

            @Override
            public void cueVideos(List<String> list, int i, int i1) {

            }

            @Override
            public void loadVideos(List<String> list) {

            }

            @Override
            public void loadVideos(List<String> list, int i, int i1) {

            }

            @Override
            public void play() {

            }

            @Override
            public void pause() {

            }

            @Override
            public boolean isPlaying() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public void next() {

            }

            @Override
            public void previous() {

            }

            @Override
            public int getCurrentTimeMillis() {
                return 0;
            }

            @Override
            public int getDurationMillis() {
                return 0;
            }

            @Override
            public void seekToMillis(int i) {

            }

            @Override
            public void seekRelativeMillis(int i) {

            }

            @Override
            public void setFullscreen(boolean b) {

            }

            @Override
            public void setOnFullscreenListener(OnFullscreenListener onFullscreenListener) {

            }

            @Override
            public void setFullscreenControlFlags(int i) {

            }

            @Override
            public int getFullscreenControlFlags() {
                return 0;
            }

            @Override
            public void addFullscreenControlFlag(int i) {

            }

            @Override
            public void setPlayerStyle(PlayerStyle playerStyle) {

            }

            @Override
            public void setShowFullscreenButton(boolean b) {

            }

            @Override
            public void setManageAudioFocus(boolean b) {

            }

            @Override
            public void setPlaylistEventListener(PlaylistEventListener playlistEventListener) {

            }

            @Override
            public void setPlayerStateChangeListener(PlayerStateChangeListener playerStateChangeListener) {

            }

            @Override
            public void setPlaybackEventListener(PlaybackEventListener playbackEventListener) {

            }
        };
        playerView.initialize(Youtubeconfig.getApiKey(), onInitializedListener);
        //if video already watched and has notes saved
        save_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFinalNote();
                //other functionalities
                Toast.makeText(getApplicationContext(), "Note Saved", Toast.LENGTH_SHORT).show();
                scrollView.setClickable(false);
                scrollView.setBackgroundColor(Color.BLACK);
                save_note.setClickable(false);
                add_page.setClickable(false);
                erase.setClickable(false);
                undo.setClickable(false);
                write.setClickable(false);
                redo.setClickable(false);
                save_note.setVisibility(View.INVISIBLE);
                add_page.setVisibility(View.INVISIBLE);
                erase.setVisibility(View.INVISIBLE);
                undo.setVisibility(View.INVISIBLE);
                write.setVisibility(View.INVISIBLE);
                redo.setVisibility(View.INVISIBLE);
                viewmode.setVisibility(View.VISIBLE);
                onBackPressed();
            }
        });
        //onclick listeners

        add_page.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                    flag=1;
                    save_note.setVisibility(View.VISIBLE);
                    save_note.setClickable(true);
                    //make changes to xml
                    title_Notes.setHint("");
                    title_Notes.setText(videoTitle);
                    title_Notes.setBackgroundColor(Color.WHITE);
                    scrollView.setBackgroundColor(Color.WHITE);
                    timingList.setBackgroundColor(Color.WHITE);
                    //save bitmap
                    String saved_name = "";
                    if (count > 0) {
                        Bitmap saveImage = drawView.getBitmap();
                        saved_name = storeImage(saveImage);
                    }
                    //clear previous
                    scrollView.scrollTo(0,0);
                    drawView.clearCanvas();
                    drawView.setBackgroundColor(Color.WHITE);
                    int startTime = running_Video.getCurrentTimeMillis() / 1000;
                    int p1 = startTime / 60;
                    int p2 = startTime % 60;
                    if (p1 >= 1)
                        SAVE_START_TIME = p1 + ":" + (p2);
                    else
                        SAVE_START_TIME = "0:" + startTime;
                    timeModel obj = new timeModel(SAVE_START_TIME);
                    obj.setTime(SAVE_START_TIME);
                    list_0f_times.add(obj);
                    adapter = new timerAdapter(Canvas_videoplayer_activity.this, list_0f_times, running_Video,
                                        videoId, drawView, writeModeEnabled,scrollView);
                    timingList.setAdapter(adapter);
                    //save to database
                    if (count > 0) {

                        //getValues
                        recordsModel newElement = new recordsModel(saved_name,PREV_START_TIME,videoTitle);
                        Note single_note = RecordsDatabase.getInstance(getApplicationContext()).recordDao().loadById(videoId);
                        if(single_note!=null){
                            String p = single_note.getContent();
                            List<recordsModel>list = gs.stringToSomeObjectList(p);
                            list.add(newElement);
                            String returnedList = gs.someObjectListToString((list));
                            Note note = new Note(videoId,returnedList);
                            RecordsDatabase.getInstance(getApplicationContext()).recordDao().insertRecord(note);
                        }else{
                            List<recordsModel>list = new ArrayList<>();
                            list.add(newElement);
                            String returnedList = gs.someObjectListToString((list));
                            Note note = new Note(videoId,returnedList);
                            RecordsDatabase.getInstance(getApplicationContext()).recordDao().insertRecord(note);
                        }
                    }
                    PREV_START_TIME = SAVE_START_TIME;
                    count++;
                }
        });
        redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.redo();
                write.clearColorFilter();
                erase.clearColorFilter();
                undo.clearColorFilter();

            }
        });
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.undo();
                write.clearColorFilter();
                erase.clearColorFilter();
                redo.clearColorFilter();
            }
        });
        erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                erase.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.changeColor), android.graphics.PorterDuff.Mode.MULTIPLY);
                write.clearColorFilter();
                undo.clearColorFilter();
                redo.clearColorFilter();
                drawView.setStrokeWidth(30f);
                drawView.setColor(Color.WHITE);
            }
        });

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                write.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.changeColor), android.graphics.PorterDuff.Mode.MULTIPLY);
                erase.clearColorFilter();
                undo.clearColorFilter();
                redo.clearColorFilter();
                drawView.setStrokeWidth(6f);
                drawView.setColor(Color.BLACK);
            }
        });


    }
    private void setuptimerview() {
        timingList = findViewById(R.id.timer);
        timingList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        timingList.setLayoutManager(linearLayoutManager);
    }
    public String storeImage(Bitmap bm) {
        Random rand = new Random();
        int x = rand.nextInt(1000000);
        String mImageName = "IMAGE-" + x;
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bm, mImageName, "Hello");
        Log.d("imagename", path);
        return path;
    }
    private void  saveFinalNote(){
        Bitmap saveImage = drawView.getBitmap();
        String saved_name = storeImage(saveImage);
        //save to database
        //db1
        Thumbnail thumbnail =new Thumbnail();
        thumbnail.setId(videoId);
        thumbnail.setThumbnail(Thumbnail_String);
        //db2

        RecordsDatabase.getInstance(getApplicationContext()).thumbnailDao().insertRecord(thumbnail);
        recordsModel newElement = new recordsModel(saved_name,PREV_START_TIME,videoTitle);
        Note single_note = RecordsDatabase.getInstance(getApplicationContext()).recordDao().loadById(videoId);

        if(single_note!=null){
            String p = single_note.getContent();
            List<recordsModel>list = gs.stringToSomeObjectList(p);
            list.add(newElement);
            String returnedList = gs.someObjectListToString((list));
            Note note = new Note(videoId,returnedList);
            RecordsDatabase.getInstance(getApplicationContext()).recordDao().insertRecord(note);
        }else{
            List<recordsModel>list = new ArrayList<>();
            list.add(newElement);
            String returnedList = gs.someObjectListToString((list));
            Note note = new Note(videoId,returnedList);
            RecordsDatabase.getInstance(getApplicationContext()).recordDao().insertRecord(note);
        }
       // onBackPressed();
    }
}


