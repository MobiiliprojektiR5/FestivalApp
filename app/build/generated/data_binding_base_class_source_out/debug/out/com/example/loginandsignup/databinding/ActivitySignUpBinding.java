// Generated by view binder compiler. Do not edit!
package com.example.loginandsignup.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.loginandsignup.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySignUpBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatButton SignUpbtn;

  @NonNull
  public final TextInputEditText confirmPassEt;

  @NonNull
  public final TextInputLayout confirmPasswordLayout;

  @NonNull
  public final TextInputEditText emailEt;

  @NonNull
  public final TextInputLayout emailLayout;

  @NonNull
  public final TextInputEditText passET;

  @NonNull
  public final TextInputLayout passwordLayout;

  @NonNull
  public final TextView textView;

  private ActivitySignUpBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatButton SignUpbtn, @NonNull TextInputEditText confirmPassEt,
      @NonNull TextInputLayout confirmPasswordLayout, @NonNull TextInputEditText emailEt,
      @NonNull TextInputLayout emailLayout, @NonNull TextInputEditText passET,
      @NonNull TextInputLayout passwordLayout, @NonNull TextView textView) {
    this.rootView = rootView;
    this.SignUpbtn = SignUpbtn;
    this.confirmPassEt = confirmPassEt;
    this.confirmPasswordLayout = confirmPasswordLayout;
    this.emailEt = emailEt;
    this.emailLayout = emailLayout;
    this.passET = passET;
    this.passwordLayout = passwordLayout;
    this.textView = textView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySignUpBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySignUpBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_sign_up, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySignUpBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.SignUpbtn;
      AppCompatButton SignUpbtn = ViewBindings.findChildViewById(rootView, id);
      if (SignUpbtn == null) {
        break missingId;
      }

      id = R.id.confirmPassEt;
      TextInputEditText confirmPassEt = ViewBindings.findChildViewById(rootView, id);
      if (confirmPassEt == null) {
        break missingId;
      }

      id = R.id.confirmPasswordLayout;
      TextInputLayout confirmPasswordLayout = ViewBindings.findChildViewById(rootView, id);
      if (confirmPasswordLayout == null) {
        break missingId;
      }

      id = R.id.emailEt;
      TextInputEditText emailEt = ViewBindings.findChildViewById(rootView, id);
      if (emailEt == null) {
        break missingId;
      }

      id = R.id.emailLayout;
      TextInputLayout emailLayout = ViewBindings.findChildViewById(rootView, id);
      if (emailLayout == null) {
        break missingId;
      }

      id = R.id.passET;
      TextInputEditText passET = ViewBindings.findChildViewById(rootView, id);
      if (passET == null) {
        break missingId;
      }

      id = R.id.passwordLayout;
      TextInputLayout passwordLayout = ViewBindings.findChildViewById(rootView, id);
      if (passwordLayout == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      return new ActivitySignUpBinding((ConstraintLayout) rootView, SignUpbtn, confirmPassEt,
          confirmPasswordLayout, emailEt, emailLayout, passET, passwordLayout, textView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
