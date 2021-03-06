// Generated by view binder compiler. Do not edit!
package com.example.videonotesph1.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.divyanshu.draw.widget.DrawView;
import com.example.videonotesph1.LockableScrollView;
import com.example.videonotesph1.R;
import com.google.android.youtube.player.YouTubePlayerView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityCanvasVideoplayerActivityBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatImageView addPage;

  @NonNull
  public final RelativeLayout canvasArea;

  @NonNull
  public final AppCompatImageView erase;

  @NonNull
  public final RelativeLayout extraBottomSpace;

  @NonNull
  public final AppCompatImageView redo;

  @NonNull
  public final RelativeLayout relativeLayout;

  @NonNull
  public final AppCompatButton saveNoteFull;

  @NonNull
  public final LockableScrollView scrollView;

  @NonNull
  public final RecyclerView timer;

  @NonNull
  public final LinearLayout timingsList;

  @NonNull
  public final AppCompatTextView titleNotes;

  @NonNull
  public final AppCompatImageView undo;

  @NonNull
  public final YouTubePlayerView videoview;

  @NonNull
  public final AppCompatTextView viewmode;

  @NonNull
  public final AppCompatImageView write;

  @NonNull
  public final DrawView writingArea;

  private ActivityCanvasVideoplayerActivityBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatImageView addPage, @NonNull RelativeLayout canvasArea,
      @NonNull AppCompatImageView erase, @NonNull RelativeLayout extraBottomSpace,
      @NonNull AppCompatImageView redo, @NonNull RelativeLayout relativeLayout,
      @NonNull AppCompatButton saveNoteFull, @NonNull LockableScrollView scrollView,
      @NonNull RecyclerView timer, @NonNull LinearLayout timingsList,
      @NonNull AppCompatTextView titleNotes, @NonNull AppCompatImageView undo,
      @NonNull YouTubePlayerView videoview, @NonNull AppCompatTextView viewmode,
      @NonNull AppCompatImageView write, @NonNull DrawView writingArea) {
    this.rootView = rootView;
    this.addPage = addPage;
    this.canvasArea = canvasArea;
    this.erase = erase;
    this.extraBottomSpace = extraBottomSpace;
    this.redo = redo;
    this.relativeLayout = relativeLayout;
    this.saveNoteFull = saveNoteFull;
    this.scrollView = scrollView;
    this.timer = timer;
    this.timingsList = timingsList;
    this.titleNotes = titleNotes;
    this.undo = undo;
    this.videoview = videoview;
    this.viewmode = viewmode;
    this.write = write;
    this.writingArea = writingArea;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCanvasVideoplayerActivityBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCanvasVideoplayerActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_canvas_videoplayer_activity, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCanvasVideoplayerActivityBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.add_page;
      AppCompatImageView addPage = rootView.findViewById(id);
      if (addPage == null) {
        break missingId;
      }

      id = R.id.canvasArea;
      RelativeLayout canvasArea = rootView.findViewById(id);
      if (canvasArea == null) {
        break missingId;
      }

      id = R.id.erase;
      AppCompatImageView erase = rootView.findViewById(id);
      if (erase == null) {
        break missingId;
      }

      id = R.id.extraBottomSpace;
      RelativeLayout extraBottomSpace = rootView.findViewById(id);
      if (extraBottomSpace == null) {
        break missingId;
      }

      id = R.id.redo;
      AppCompatImageView redo = rootView.findViewById(id);
      if (redo == null) {
        break missingId;
      }

      id = R.id.relativeLayout;
      RelativeLayout relativeLayout = rootView.findViewById(id);
      if (relativeLayout == null) {
        break missingId;
      }

      id = R.id.save_note_full;
      AppCompatButton saveNoteFull = rootView.findViewById(id);
      if (saveNoteFull == null) {
        break missingId;
      }

      id = R.id.scrollView;
      LockableScrollView scrollView = rootView.findViewById(id);
      if (scrollView == null) {
        break missingId;
      }

      id = R.id.timer;
      RecyclerView timer = rootView.findViewById(id);
      if (timer == null) {
        break missingId;
      }

      id = R.id.timingsList;
      LinearLayout timingsList = rootView.findViewById(id);
      if (timingsList == null) {
        break missingId;
      }

      id = R.id.title_Notes;
      AppCompatTextView titleNotes = rootView.findViewById(id);
      if (titleNotes == null) {
        break missingId;
      }

      id = R.id.undo;
      AppCompatImageView undo = rootView.findViewById(id);
      if (undo == null) {
        break missingId;
      }

      id = R.id.videoview;
      YouTubePlayerView videoview = rootView.findViewById(id);
      if (videoview == null) {
        break missingId;
      }

      id = R.id.viewmode;
      AppCompatTextView viewmode = rootView.findViewById(id);
      if (viewmode == null) {
        break missingId;
      }

      id = R.id.write;
      AppCompatImageView write = rootView.findViewById(id);
      if (write == null) {
        break missingId;
      }

      id = R.id.writing_area;
      DrawView writingArea = rootView.findViewById(id);
      if (writingArea == null) {
        break missingId;
      }

      return new ActivityCanvasVideoplayerActivityBinding((ConstraintLayout) rootView, addPage,
          canvasArea, erase, extraBottomSpace, redo, relativeLayout, saveNoteFull, scrollView,
          timer, timingsList, titleNotes, undo, videoview, viewmode, write, writingArea);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
