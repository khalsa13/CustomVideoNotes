// Generated by view binder compiler. Do not edit!
package com.example.videonotesph1.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.videonotesph1.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class HomescreenUiBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatTextView sampledesc;

  @NonNull
  public final AppCompatImageView sampleicon;

  private HomescreenUiBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatTextView sampledesc, @NonNull AppCompatImageView sampleicon) {
    this.rootView = rootView;
    this.sampledesc = sampledesc;
    this.sampleicon = sampleicon;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static HomescreenUiBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static HomescreenUiBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.homescreen_ui, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static HomescreenUiBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.sampledesc;
      AppCompatTextView sampledesc = rootView.findViewById(id);
      if (sampledesc == null) {
        break missingId;
      }

      id = R.id.sampleicon;
      AppCompatImageView sampleicon = rootView.findViewById(id);
      if (sampleicon == null) {
        break missingId;
      }

      return new HomescreenUiBinding((ConstraintLayout) rootView, sampledesc, sampleicon);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
