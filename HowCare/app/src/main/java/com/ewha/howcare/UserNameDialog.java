package com.ewha.howcare;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.UUID;

public class UserNameDialog extends Dialog {
  private TextView titleText;
  private EditText editText;
  private Button okButton;
  private DatabaseReference mDatabase;
  String uniqueID = "";

  public UserNameDialog(Context context, FirebaseAnalytics mFirebaseAnalytics) {
    super(context);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    setCancelable(false);
    setCanceledOnTouchOutside(false);
    mDatabase = FirebaseDatabase.getInstance().getReference();

    String uniqueID ;
    SharedPreferences sharedPreferences = context.getSharedPreferences("pref",MODE_PRIVATE);

    uniqueID = sharedPreferences.getString("option_uuid","");
    if(uniqueID.equals("")) {
      Log.d("UUIDDB",uniqueID);

      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putString("option_uuid",UUID.randomUUID().toString());
      editor.commit();
    }

    Log.d("UUIDDBNEW",uniqueID);
//    uniqueID = OptionsDB.getInstance().getUUID(context);

    setOnCancelListener(new OnCancelListener() {
      @Override
      public void onCancel(DialogInterface dialog) {
        dismiss();
        ((Activity) context).finish();
      }
    });

    setContentView(R.layout.dialog_username);     //다이얼로그에서 사용할 레이아웃입니다.

    titleText = findViewById(R.id.titleText);
    editText = findViewById(R.id.editText);
    editText.requestFocus();
    okButton = findViewById(R.id.button);

    okButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (editText.getText().toString().equals("")) {
          titleText.setText("사용자의 이름을 입력해야 합니다.\n사용자의 이름을 입력해 주세요.");
        } else {
          OptionsDB.getInstance()
              .setUserName(context, editText.getText().toString());
          mFirebaseAnalytics.setUserProperty("user_code", editText.getText().toString());

          mDatabase.child("users").child(uniqueID).child("usercode").setValue(editText.getText().toString());


          Intent intent = new Intent(context,Main2Activity.class);
          context.startActivity(intent);
          dismiss();
          ((Activity) context).finish();
        }
      }
    });
  }
}
