package com.example.videonotesph1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.divyanshu.draw.widget.DrawView;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class timerAdapter extends RecyclerView.Adapter<timerAdapter.ViewHolder> {
    private ArrayList<timeModel>data;
    private Context ctx;
    private YouTubePlayer yt;
    private String id;
    private int mode;
    private com.divyanshu.draw.widget.DrawView dv;
    private  GithubTypeConverters gs;
    private ScrollView scrollView;
    int prev=0;
    timerAdapter(Context context, ArrayList<timeModel> mdata, YouTubePlayer running_Video, String id, DrawView drawView, int mode, ScrollView sv){
        this.ctx = context;
        this.data = mdata;
        this.yt = running_Video;
        this.id = id;
        this.dv = drawView;
        this.mode = mode;
        this.scrollView =sv;
    }
    @NonNull
    @Override
    public timerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.timinglist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final timerAdapter.ViewHolder holder, final int position) {
        gs = new GithubTypeConverters();
        holder.timer.setText(data.get(position).getTime());
        holder.timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String x = (String) holder.timer.getText();
                String[] p1 = x.split(":");
                final int x1 = Integer.parseInt(p1[0]) * 60;
                final int y1 = Integer.parseInt(p1[1]);
                final int skip = (x1 + y1) * 1000;
                Note note = RecordsDatabase.getInstance(ctx).recordDao().loadById(id);
                String str=null;
                final recordsModel element;
                if(note!=null)
                    str = note.getContent();

                if(gs.stringToSomeObjectList(str).size() >position)
                    element = gs.stringToSomeObjectList(str).get(position);
                else
                    element = null;
                Log.d("printing",str+"");
                if(element!=null && mode == 1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                    builder.setMessage("You want to switch time frame? Your progress on the current page will be lost.");
                    builder.setCancelable(false);
                    builder.setTitle("Warning!");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            scrollView.scrollTo(0,0);
                            func(element);
                            yt.seekToMillis(skip);
                            prev=1;
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }else if(element!=null && mode==0){
                    scrollView.scrollTo(0,0);
                    func(element);
                    yt.seekToMillis(skip);
                }
                else if(mode==1 && element==null && prev ==1){
                    scrollView.scrollTo(0,0);
                    dv.clearCanvas();
                    dv.setBackgroundColor(Color.WHITE);
                    yt.seekToMillis(skip);
                    Toast.makeText(ctx,"Current Frame Active",Toast.LENGTH_SHORT).show();
                    prev =0;
                }
                else if(mode==1 && element==null){

                    //nothing to do you are on the same frame
                }

            }
        });
    }

    public void func(recordsModel ls){
        dv.clearCanvas();
        Drawable yourDrawable;
        try {
            InputStream inputStream = ctx.getContentResolver().openInputStream(Uri.parse(ls.getPath()));
            yourDrawable = Drawable.createFromStream(inputStream, Uri.parse(ls.getPath()).toString());
        } catch (FileNotFoundException e) {
            yourDrawable = ctx.getResources().getDrawable(R.drawable.ic_launcher_background);
        }

        dv.setBackground(yourDrawable);

    }
    @Override
    public int getItemCount() {
        return data.size();
    }
     static class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView timer;
        YouTubePlayer.OnInitializedListener onInitializedListener;
        ViewHolder(View itemView) {
            super(itemView);
            this.timer = itemView.findViewById(R.id.time);
        }
    }
}
