// Generated by view binder compiler. Do not edit!
package com.example.videonotesph1.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import com.example.videonotesph1.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.youtube.player.YouTubeThumbnailView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class SearchitemBinding implements ViewBinding {
  @NonNull
  private final MaterialCardView rootView;

  @NonNull
  public final AppCompatTextView videoPublish;

  @NonNull
  public final YouTubeThumbnailView videoThumbnailImageView;

  @NonNull
  public final AppCompatTextView videoTitleLabel;

  private SearchitemBinding(@NonNull MaterialCardView rootView,
      @NonNull AppCompatTextView videoPublish,
      @NonNull YouTubeThumbnailView videoThumbnailImageView,
      @NonNull AppCompatTextView videoTitleLabel) {
    this.rootView = rootView;
    this.videoPublish = videoPublish;
    this.videoThumbnailImageView = videoThumbnailImageView;
    this.videoTitleLabel = videoTitleLabel;
  }

  @Override
  @NonNull
  public MaterialCardView getRoot() {
    return rootView;
  }

  @NonNull
  public static SearchitemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static SearchitemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.searchitem, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static SearchitemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.video_publish;
      AppCompatTextView videoPublish = rootView.findViewById(id);
      if (videoPublish == null) {
        break missingId;
      }

      id = R.id.video_thumbnail_image_view;
      YouTubeThumbnailView videoThumbnailImageView = rootView.findViewById(id);
      if (videoThumbnailImageView == null) {
        break missingId;
      }

      id = R.id.video_title_label;
      AppCompatTextView videoTitleLabel = rootView.findViewById(id);
      if (videoTitleLabel == null) {
        break missingId;
      }

      return new SearchitemBinding((MaterialCardView) rootView, videoPublish,
          videoThumbnailImageView, videoTitleLabel);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
