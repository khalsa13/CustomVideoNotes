package com.example.videonotesph1;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class HomeScreenAdapter extends BaseAdapter {
    private Context ctx;
    private ArrayList<HomeModel>list;

    public HomeScreenAdapter(Context ctx, ArrayList<HomeModel> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final HomeModel obj = list.get(i);
        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(ctx);
            view = layoutInflater.inflate(R.layout.homescreen_ui, null);
        }
        final AppCompatTextView title = view.findViewById(R.id.sampledesc);
        final AppCompatImageView icon = view.findViewById(R.id.sampleicon);

        Drawable yourDrawable;
        try {
            InputStream inputStream = ctx.getContentResolver().openInputStream(Uri.parse(obj.getImage()));
            yourDrawable = Drawable.createFromStream(inputStream, Uri.parse(obj.getImage()).toString());
        } catch (FileNotFoundException e) {
            Log.e("error",e.getMessage());
            yourDrawable = ctx.getResources().getDrawable(R.drawable.ic_launcher_background);
        }
        title.setText(obj.getName());
        icon.setBackgroundDrawable(yourDrawable);

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String video_url_ID = obj.getVideo_ID();
                Intent appIntent = new Intent(ctx,Canvas_videoplayer_activity.class);
                appIntent.putExtra("VIDEO_THUMBNAIL", obj.getImage());
                appIntent.putExtra("VIDEO_ID",obj.getVideo_ID());
                appIntent.putExtra("VIDEO_TITLE",obj.getName());
                ctx.startActivity(appIntent);
            }
        });
        return view;
    }
}
