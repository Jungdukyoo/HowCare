package com.ewha.howcare;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class LauncherActivity extends AppCompatActivity {
  private int logo_duration = 4000;
  GifImageView gifImageView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_launcher);
    gifImageView = findViewById(R.id.imageView5);

    try {
      GifDrawable gifFromResource = new GifDrawable( getResources(), R.raw.animation_logo);
      Log.d("GIF DURATION", String.valueOf(gifFromResource.getDuration()));
      logo_duration = gifFromResource.getDuration();
      gifFromResource.reset();
      gifImageView.setImageDrawable(gifFromResource);
    } catch (IOException e) {
      e.printStackTrace();
    }

    Handler hand = new Handler();
    hand.postDelayed(new Runnable() {
      @Override
      public void run() {
        // TODO Auto-generated method stub
        Intent intent = new Intent(LauncherActivity.this, Launcher2Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
      }
    }, logo_duration);
  }

  @Override
  public void onDestroy() {
    gifImageView.setImageBitmap(null);
    RecycleUtils.recursiveRecycle(getWindow().getDecorView());
    System.gc();
    super.onDestroy();
  }
}