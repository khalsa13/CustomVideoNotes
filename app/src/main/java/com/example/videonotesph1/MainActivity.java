package com.example.videonotesph1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

// fateh key AIzaSyBMGCvqtr2F6jpiO2T4zRlxkFQGT60D4I4
// my key    AIzaSyBrrYlNkBNfbNns5yz30YHlH3AMl5Kwo_U
public class MainActivity extends AppCompatActivity {
    private static String GOOGLE_YOUTUBE_API_KEY = Youtubeconfig.getApiKey();  //here you should use your api key for testing purpose you can use this api also
    private static String CHANNLE_GET_URL ;
    private static String RecommendChannel;
    private ArrayList<YoutubeDataModel> youtubeSearchResultsList = new ArrayList<>();
    AppCompatImageView searchVideo,recommendVideo;
    AppCompatEditText searchitem,recommendItem;
    searchResultsAdapter newAdapter;
    RecommendAdapter adapter;
    LinearLayout recommendLayout;
    RecyclerView displaySearchResults,displayRecommendresults;
    int RecommendFlag=0;
    String thumbnailImage;
    LottieAnimationView loadAnimation;
    //https://www.googleapis.com/youtube/v3/search/?key=<YOUR_KEY>&part=snippet&q=youtu.be/M7lc1UVf-VE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpVerticalrecyclerView();
        setUpHorizontalrecyclerView();
        recommendLayout = findViewById(R.id.recommendlayout);
        recommendLayout.setVisibility(View.INVISIBLE);
        searchVideo = findViewById(R.id.startsearch);
        searchitem = findViewById(R.id.searchText);
        loadAnimation = findViewById(R.id.loadingAnimation);
        //share button intent

        Intent intent = getIntent();
        String Video_Id = intent.getStringExtra("youtube_videoId");
        if(Video_Id!=null)
        {

            Log.d("printmsg",Video_Id+"is this");
            CHANNLE_GET_URL = "https://www.googleapis.com/youtube/v3/search/?key="+GOOGLE_YOUTUBE_API_KEY+"&part=snippet&q="+Video_Id;
            RecommendFlag =10;
            new RequestYoutubeAPI().execute();
        }
        //otherwise direct flow
        searchVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadAnimation.setVisibility(View.INVISIBLE);
                RecommendFlag = 0;
                Toast.makeText(getApplicationContext(),"Searching.. Please Wait",Toast.LENGTH_SHORT).show();
                String SEARCHING_VAL = Objects.requireNonNull(searchitem.getText()).toString();
                SEARCHING_VAL = SEARCHING_VAL.replaceAll("\\s", "+");
                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                if(!SEARCHING_VAL.equals("")){
                    CHANNLE_GET_URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + SEARCHING_VAL + "&type=video&maxResults=15&key=" + GOOGLE_YOUTUBE_API_KEY + "";
                    new RequestYoutubeAPI().execute();
                }

            }
        });
        searchitem.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loadAnimation.setVisibility(View.INVISIBLE);
                    RecommendFlag = 0;
                    try {
                        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    Toast.makeText(getApplicationContext(),"Searching.. Please Wait",Toast.LENGTH_SHORT).show();
                    String SEARCHING_VAL = Objects.requireNonNull(searchitem.getText()).toString();
                    SEARCHING_VAL = SEARCHING_VAL.replaceAll("\\s", "+");
                    if(!SEARCHING_VAL.equals("")){
                        CHANNLE_GET_URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + SEARCHING_VAL + "&type=video&maxResults=15&key=" + GOOGLE_YOUTUBE_API_KEY + "";
                        new RequestYoutubeAPI().execute();
                    }
                }
                return handled;
            }
        });
    }

    private void setUpVerticalrecyclerView() {
        displaySearchResults = findViewById(R.id.search_result);
        displaySearchResults.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        displaySearchResults.setLayoutManager(linearLayoutManager);
    }
    private void setUpHorizontalrecyclerView() {
        displayRecommendresults = findViewById(R.id.recommed);
        displayRecommendresults.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        displayRecommendresults.setLayoutManager(linearLayoutManager);
    }

    /**
     * populate the recyclerView and implement the click event here
     */

    private class RequestYoutubeAPI extends AsyncTask<Void, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            if (response != null) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.e("response", jsonObject.toString());
                    youtubeSearchResultsList = parseVideoListFromResponse(jsonObject);
                    //getrecommend list after actual search
                    if(RecommendFlag == 10){
                        Intent directIntent = new Intent(getApplicationContext(),Canvas_videoplayer_activity.class);
                        directIntent.putExtra("VIDEO_ID",youtubeSearchResultsList.get(0).getVideo_id());
                        directIntent.putExtra("VIDEO_TITLE",youtubeSearchResultsList.get(0).getTitle());
                        Glide.with(getApplicationContext()).asBitmap().load(youtubeSearchResultsList.get(0).getThumbnail()).listener(new RequestListener<Bitmap>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                thumbnailImage =storeImage(resource);
                                return true;
                            }
                        }).submit();
                        try {
                            TimeUnit.MILLISECONDS.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.d("printmsgnow",youtubeSearchResultsList.get(0).getDescription());

                        Log.d("printmsgnow",thumbnailImage);
                        directIntent.putExtra("VIDEO_THUMBNAIL", thumbnailImage);
                        startActivity(directIntent);

                    }
                    if(RecommendFlag == 1){
                        newAdapter = new searchResultsAdapter(MainActivity.this,youtubeSearchResultsList);
                        displaySearchResults.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        displaySearchResults.setAdapter(newAdapter);
                        RecommendFlag = 2;
                        loadAnimation.setVisibility(View.VISIBLE);
                        loadAnimation.playAnimation();
                        CHANNLE_GET_URL = RecommendChannel;
                        new RequestYoutubeAPI().execute();
                        recommendLayout.setVisibility(View.INVISIBLE);
                    }else{
                        adapter = new RecommendAdapter(MainActivity.this,youtubeSearchResultsList);
                        displayRecommendresults.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));
                        displayRecommendresults.setAdapter(adapter);
                        loadAnimation.setVisibility(View.INVISIBLE);
                        loadAnimation.pauseAnimation();
                        recommendLayout.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected String doInBackground(Void... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(CHANNLE_GET_URL);
            httpClient.getConnectionManager().getSchemeRegistry().register(
                    new Scheme("https", SSLSocketFactory.getSocketFactory(), 443)
            );
            Log.e("URL", CHANNLE_GET_URL);
            try {
                HttpResponse response = httpClient.execute(httpGet);
                HttpEntity httpEntity = response.getEntity();
                String json = EntityUtils.toString(httpEntity);
                return json;
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }
    public ArrayList<YoutubeDataModel> parseVideoListFromResponse(JSONObject jsonObject) {
        ArrayList<YoutubeDataModel> mList = new ArrayList<>();

        if (jsonObject.has("items")) {
            try {
                String idRelatedVideo="";
                JSONArray jsonArray = jsonObject.getJSONArray("items");
                Log.d("data",jsonArray.length()+"  hello");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json = jsonArray.getJSONObject(i);
                    if (json.has("id")) {
                        JSONObject jsonID = json.getJSONObject("id");
                        String video_id = "";
                        if (jsonID.has("videoId")) {
                            video_id = jsonID.getString("videoId");
                            idRelatedVideo = video_id;
                            if(RecommendFlag == 0){
                                RecommendFlag = 1;
                                RecommendChannel ="https://www.googleapis.com/youtube/v3/search?part=snippet&relatedToVideoId="+ idRelatedVideo +"&type=video&&maxResults=10&key="+GOOGLE_YOUTUBE_API_KEY;
                                Log.d("data",RecommendChannel+"");
                            }
                        }
                        if (jsonID.has("kind")) {
                            if (jsonID.getString("kind").equals("youtube#video")) {
                                YoutubeDataModel youtubeObject = new YoutubeDataModel();
                                JSONObject jsonSnippet = json.getJSONObject("snippet");
                                String title = jsonSnippet.getString("title");
                                String description = jsonSnippet.getString("description");
                                String publishedAt = jsonSnippet.getString("publishedAt");
                                String thumbnail = jsonSnippet.getJSONObject("thumbnails").getJSONObject("high").getString("url");

                                youtubeObject.setTitle(title);
                                youtubeObject.setDescription(description);
                                youtubeObject.setPublishedAt(publishedAt);
                                youtubeObject.setThumbnail(thumbnail);
                                youtubeObject.setVideo_id(video_id);
                                Log.d("presentingbefore",publishedAt+" ");
                                mList.add(youtubeObject);
                            }
                        }
                    }

                }

                return mList;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return mList;

    }
    private String storeImage(Bitmap bm) {
        Random rand = new Random();
        int x = rand.nextInt(1000000);
        String mImageName = "IMAGE-" + x;
        String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), bm, mImageName, "Hello");
        Log.d("imagename", path);
        return path;
    }

}
