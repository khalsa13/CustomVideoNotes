package com.example.videonotesph1;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class searchResultsAdapter extends RecyclerView.Adapter<searchResultsAdapter.ViewHolder>{
    ArrayList<YoutubeDataModel> data;
    Context ctx;
    String thumbnail;
     searchResultsAdapter(Context context, ArrayList<YoutubeDataModel> data){
        this.ctx = context;
        this.data = data;
        Log.d("data",data.size()+"");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.searchitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d("data",position+"");
        Glide.with(holder.img.getContext()).load(data.get(position).getThumbnail()).into(holder.img);
        holder.txt.setText(data.get(position).getTitle());
        final String str = data.get(position).getPublishedAt();
        holder.publish.setText(str.substring(0,str.indexOf("T")));
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] path = new String[1];
                Glide.with(holder.img.getContext()).asBitmap().load(data.get(position).
                                        getThumbnail()).listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        thumbnail =storeImage(resource);
                        return true;
                    }
                }).submit();
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String id = data.get(position).getVideo_id();
                Intent appIntent = new Intent(ctx,Canvas_videoplayer_activity.class);
                appIntent.putExtra("VIDEO_THUMBNAIL", thumbnail);
                appIntent.putExtra("VIDEO_ID",id);
                appIntent.putExtra("VIDEO_TITLE",data.get(position).getTitle());
                ctx.startActivity(appIntent);

            }
        });
    }
    private String storeImage(Bitmap bm) {
        Random rand = new Random();
        int x = rand.nextInt(1000000);
        String mImageName = "IMAGE-" + x;
        String path = MediaStore.Images.Media.insertImage(ctx.getContentResolver(), bm, mImageName, "Hello");
        Log.d("imagename", path);
        thumbnail = path;
        return path;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
         YouTubeThumbnailView img;
         AppCompatTextView txt,publish;
         ViewHolder(View itemView) {
            super(itemView);
            this.img = itemView.findViewById(R.id.video_thumbnail_image_view);
            this.txt = itemView.findViewById(R.id.video_title_label);
            this.publish = itemView.findViewById(R.id.video_publish);
         }
    }
}
