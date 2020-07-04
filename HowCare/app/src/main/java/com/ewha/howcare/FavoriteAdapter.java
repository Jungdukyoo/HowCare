package com.ewha.howcare;

import static com.ewha.howcare.ConvertClass.CATEGORY_BEFORE_CARE;
import static com.ewha.howcare.ConvertClass.CATEGORY_IN_CARE;
import static com.ewha.howcare.ConvertClass.CATEGORY_STRANGE;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{


  private int category;
  ArrayList<FavoriteItem> mDataset;
  FirebaseAnalytics mFirebaseAnalytics;

  public FavoriteAdapter(ArrayList<FavoriteItem> mDataset, FirebaseAnalytics firebaseAnalytics) {
    this.mDataset = mDataset;
    this.mFirebaseAnalytics = firebaseAnalytics;
  }

  @Override
  public FavoriteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new FavoriteAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).
        inflate(R.layout.recycler_favorite, parent, false));
  }

  @Override
  public void onBindViewHolder(FavoriteAdapter.ViewHolder holder, int position) {
    holder.setData(mDataset.get(position), position);
  }

  @Override
  public int getItemCount() {
    return mDataset.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    ImageView thimbImage;
    TextView titleText;
    TextView subtitleText;
    TextView numText;
    ConstraintLayout cl;

    public ViewHolder(View itemView) {
      super(itemView);
      this.thimbImage = itemView.findViewById(R.id.favorite_thumbimg);
      this.titleText = itemView.findViewById(R.id.favorite_title);
      this.subtitleText = itemView.findViewById(R.id.favorite_text);
      this.numText = itemView.findViewById(R.id.favorite_pagenum);
      this.cl = itemView.findViewById(R.id.favorite_cl);
    }

    public void setData(FavoriteItem item, int positon) {
      String resName = "@drawable/" + item.getThumbnail();

      int resID = thimbImage.getContext().getResources().getIdentifier(resName, "drawable",
          thimbImage.getContext().getPackageName());

      thimbImage.setImageResource(resID);
      subtitleText.setText(item.getTitle());
      titleText.setText(ConvertClass.getMainTitle(item.getPagenum()));
      numText.setText("0"+String.valueOf(item.getPagenum()));

      int category = ConvertClass.getCategory(item.getPagenum());

      int color;
      switch (category) {
        case CATEGORY_BEFORE_CARE:
          color = R.color.light_mustard;
          break;
        case CATEGORY_IN_CARE:
          color = R.color.melon;
          break;
        case CATEGORY_STRANGE:
          color = R.color.seafoam_blue_two;
          break;
        default:
          color = R.color.yellowish_orange;
          break;
      }
      numText.setTextColor(itemView.getResources().getColor(color));

      if(positon%2==0) {
        this.cl.setBackgroundColor(cl.getResources().getColor(R.color.white));
      }
      else {
        this.cl.setBackgroundColor(cl.getResources().getColor(R.color.white_three));
      }

      cl.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          ReadDB.getInstance().setLastRead(item.getPagenum(),item.getItemnum(),cl.getContext());



          Intent intent;
          Context context = cl.getContext();
          intent = new Intent(context, ContentActivity.class);
          intent.putExtra("contentPage",item.getPagenum());
          ItemsDB.getInstance().setSlide_itemnum(item.getItemnum());

          //add log
          Date dt = new Date();
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
          String realtime = sdf.format(dt).toString();
          String date = new SimpleDateFormat("yyyyMMdd",Locale.KOREA).format(dt).toString();
          Log.d("LOGTEST","Content"+item.getPagenum()+" "+item.getItemnum());
          /*UserCode Part
          FirebaseDatabase.getInstance().getReference().child("userlist")
              .child(OptionsDB.getInstance().getUsername(context))
              .child("log").child(date)
              .child(String.valueOf(dt.getTime()))
              .setValue(new UserLog(dt.getTime(),item.getPagenum(),item.getItemnum(),5));

           */
          OptionsDB.getInstance().logcheck = true;

          ConvertClass.setAlalytics((Activity)context,mFirebaseAnalytics,item.getPagenum(),item.getItemnum());

          Bundle bundle = new Bundle();
          bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "1");
          bundle.putString(FirebaseAnalytics.Param.ITEM_ID, ConvertClass.getScreenName(item.getPagenum(), item.getItemnum()));
          mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
          Log.d("FA::SETALALYTICS","~~");


          context.startActivity(intent);
        }
      });
    }
  }
}