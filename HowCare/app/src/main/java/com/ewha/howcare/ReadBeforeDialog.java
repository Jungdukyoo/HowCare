package com.ewha.howcare;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReadBeforeDialog extends Dialog {
  private TextView titleText;
  private TextView subtitleText;
  private ImageView closeButton;
  private TextView numberText;
  private TextView continueText;
  private ImageView continueImage;

  private int pagenum;
  private int itemnum;


  public ReadBeforeDialog(Context context, int pagenum, int itemnum, FirebaseAnalytics mFirebaseAnalytics) {
    super(context);
    requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
    getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  //다이얼로그의 배경을 투명으로 만듭니다.
    setContentView(R.layout.activity_readbefore);     //다이얼로그에서 사용할 레이아웃입니다.

    titleText = findViewById(R.id.readbefore_title);
    subtitleText = findViewById(R.id.readbefore_subtitle);
    closeButton = findViewById(R.id.readbefore_close);
    numberText = findViewById(R.id.readbefore_number);
    continueText = findViewById(R.id.readbefore_continue);
    continueImage = findViewById(R.id.readbefore_continueback);

    this.pagenum = pagenum;
    this.itemnum = itemnum;

    String substr = ItemsDB.getInstance().getDbList().get(pagenum).get(itemnum).get(0).getItem();
    subtitleText.setText(substr);
    numberText.setText("0"+pagenum);
    String str=ConvertClass.getMainTitle(pagenum);
    str="  "+str;
    titleText.setText(str);


    int color;
    if(pagenum<=4) {
      color = R.color.light_mustard;
      continueImage.setImageResource(R.mipmap.rectangle_yellow);
    }
    else if(pagenum<=7) {
      color = R.color.melon;
      continueImage.setImageResource(R.mipmap.rectangle_red);
    }
    else {
      color = R.color.seafoam_blue_two;
      continueImage.setImageResource(R.mipmap.rectangle_green);
    }

    numberText.setTextColor(context.getResources().getColor(color));

    closeButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dismiss();
      }
    });

    continueImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent;
        intent = new Intent(context, ContentActivity.class);
        intent.putExtra("contentPage",pagenum);
        ItemsDB.getInstance().setSlide_itemnum(itemnum);



        //add log
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        String realtime = sdf.format(dt).toString();
        String date = new SimpleDateFormat("yyyyMMdd",Locale.KOREA).format(dt).toString();
        Log.d("LOGTEST","Content"+pagenum+" "+itemnum);
        /*
        FirebaseDatabase.getInstance().getReference().child("userlist")
            .child(OptionsDB.getInstance().getUsername(context))
            .child("log").child(date)
            .child(String.valueOf(dt.getTime()))
            .setValue(new UserLog(dt.getTime(),pagenum,itemnum));


         */
        OptionsDB.getInstance().logcheck=true;

//        mFirebaseAnalytics.setCurrentScreen(getOwnerActivity(), ConvertClass.getScreenName(pagenum, itemnum), null /* class override */);
//        Log.d("FA::areadbefore",ConvertClass.getScreenName(pagenum, itemnum)+" pagetype : "+pagenum+" current : "+itemnum);

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, ConvertClass.getScreenName(pagenum, itemnum));
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        context.startActivity(intent);
        dismiss();
      }
    });
  }
}
