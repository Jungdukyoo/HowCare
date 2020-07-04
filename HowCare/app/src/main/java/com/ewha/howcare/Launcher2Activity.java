package com.ewha.howcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.fujiyuu75.sequent.Sequent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class Launcher2Activity extends AppCompatActivity {

  private FirebaseAnalytics mFirebaseAnalytics;
  LinearLayout linearLayout;
  private DatabaseReference mDatabase;
  String username;
  String uniqueID;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    mDatabase = FirebaseDatabase.getInstance().getReference();

    setContentView(R.layout.activity_launcher2);
    linearLayout = findViewById(R.id.logo_layout);

    username = OptionsDB.getInstance().getUsername(Launcher2Activity.this);

    //= OptionsDB.getInstance().getUUID(Launcher2Activity.this);
    SharedPreferences sharedPreferences = (Launcher2Activity.this).getSharedPreferences("pref",MODE_PRIVATE);

    uniqueID = sharedPreferences.getString("option_uuid","");
    uniqueID = "nnone";

    if(uniqueID.equals("")) {
      Log.d("UUIDDB",uniqueID);

      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putString("option_uuid",UUID.randomUUID().toString());
      editor.commit();
    }

    Sequent.origin(linearLayout).start();
    Handler hand = new Handler();
    hand.postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent intent = new Intent(Launcher2Activity.this, Main2Activity.class);
        startActivity(intent);
        finish();

        //UserCode Part
        /*
        if (username.equals("")) {
          UserNameDialog dialog = new UserNameDialog(Launcher2Activity.this, mFirebaseAnalytics);
          dialog.show();
        } else {
          mFirebaseAnalytics.setUserProperty("user_code", username);
          mDatabase.child("users").child(uniqueID).child("usercode").setValue(username);

          Intent intent = new Intent(Launcher2Activity.this, Main2Activity.class);
          startActivity(intent);
          finish();
        }*/
      }
    }, 2500);
  }

  @Override
  public void onDestroy() {
    RecycleUtils.recursiveRecycle(getWindow().getDecorView());
    System.gc();
    super.onDestroy();
  }
}