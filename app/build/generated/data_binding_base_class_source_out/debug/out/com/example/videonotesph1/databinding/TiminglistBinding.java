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
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class TiminglistBinding implements ViewBinding {
  @NonNull
  private final MaterialCardView rootView;

  @NonNull
  public final MaterialCardView cardviewForTime;

  @NonNull
  public final AppCompatTextView time;

  private TiminglistBinding(@NonNull MaterialCardView rootView,
      @NonNull MaterialCardView cardviewForTime, @NonNull AppCompatTextView time) {
    this.rootView = rootView;
    this.cardviewForTime = cardviewForTime;
    this.time = time;
  }

  @Override
  @NonNull
  public MaterialCardView getRoot() {
    return rootView;
  }

  @NonNull
  public static TiminglistBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static TiminglistBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.timinglist, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static TiminglistBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      MaterialCardView cardviewForTime = (MaterialCardView) rootView;

      id = R.id.time;
      AppCompatTextView time = rootView.findViewById(id);
      if (time == null) {
        break missingId;
      }

      return new TiminglistBinding((MaterialCardView) rootView, cardviewForTime, time);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
