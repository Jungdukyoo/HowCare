package com.ewha.howcare;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NavigationAdapter extends RecyclerView.Adapter<ViewHolder>{

  private int category;
  ArrayList<NavigationItem> mDataset;
  FirebaseAnalytics mFirebaseAnalytics;

  public final static int NAVI_TITLE = 1;
  public final static int NAVI_ITEM = 2;
  public final static int NAVI_CATEGORY = 3;

  private final String totalTitles[][] = {{""},{"1. 식사는 즐거워야 한다", "2. 입으로 먹는 것이 중요하다", "3. "
      + "식사 전 준비사항"},{"1. 식사도구의 중요성","2. 숟가락/젓가락","3. 식기류"},{"1. 바른자세의 중요성","2. 상황별 바른"
      + " 자세"},{"1. 목운동","2. 입체조","3. 침샘 마사지 운동","4. 삼킴 운동"},{"1. 옆에 앉아 돕는다","2. 음식물은 "
      + "아래에서 위로 가져간다.","3. 삼킨 것을 확인한다.","4. 질식을 예방한다."},{"1. 기능유지간호란?"},{"1. 구강간호의 "
      + "중요성","2. 구강간호의 순서"},{"1. 식사 거부","2. 이식증","3. 편식","4. 식사한지 잊음"}};


  public NavigationAdapter(ArrayList<NavigationItem> mDataset, FirebaseAnalytics firebaseAnalytics) {
    this.mDataset = mDataset;
    this.mFirebaseAnalytics = firebaseAnalytics;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    RecyclerView.ViewHolder viewHolder;
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());

    switch (viewType) {
      case NAVI_TITLE:
        View v1 = inflater.inflate(R.layout.navi_recycler_head, parent, false);
        viewHolder = new viewHolderTitle(v1);
        break;
      case NAVI_ITEM:
        View v2 = inflater.inflate(R.layout.nav_recycler_item, parent, false);
        viewHolder = new viewHolderItem(v2);
        break;
      case NAVI_CATEGORY:
        View v4 = inflater.inflate(R.layout.nav_recycler_category, parent, false);
        viewHolder = new viewHolderCategory(v4);
        break;
      default:
        View v3 = inflater.inflate(R.layout.navi_recycler_head, parent, false);
        viewHolder = new viewHolderTitle(v3);
        break;
    }

    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, int position) {
    int viewType = viewHolder.getItemViewType();
    switch (viewType) {
      case NAVI_TITLE:
        ((viewHolderTitle) viewHolder).bind(mDataset.get(position), position);
        return;
      case NAVI_ITEM:
        ((viewHolderItem) viewHolder).bind(mDataset.get(position), position);
        return;
      case NAVI_CATEGORY:
        ((viewHolderCategory) viewHolder).bind(mDataset.get(position), position);
        return;
      default:
        ((viewHolderItem) viewHolder).bind(mDataset.get(position), position);
        return;
    }
  }

  @Override
  public int getItemCount() {
    return mDataset.size();
  }


  @Override
  public int getItemViewType(int position) {
    return mDataset.get(position).getType();
  }


  public class viewHolderCategory extends RecyclerView.ViewHolder {

    private TextView mTitleView;
    private TextView mMarginView;

    public viewHolderCategory(View itemView) {
      super(itemView);
      mTitleView = (TextView) itemView.findViewById(R.id.nav_title_text);
      mMarginView = (TextView) itemView.findViewById(R.id.nav_title_margin);
    }

    public void bind(NavigationItem slideParent, int pos) {
      mTitleView.setText(slideParent.getTitle());

      if(slideParent.getNumber()==1)
        mMarginView.setVisibility(View.GONE);
      else
        mMarginView.setVisibility(View.VISIBLE);

      int color;
      if(slideParent.getNumber()==1) {
        mTitleView.setBackgroundColor(Color.parseColor("#FFCC72"));
      }
      else if(slideParent.getNumber()==2) {
        mTitleView.setBackgroundColor(Color.parseColor("#FF9D76"));
      }
      else {
        mTitleView.setBackgroundColor(Color.parseColor("#73E1D0"));
      }
    }
  }


  public class viewHolderTitle extends RecyclerView.ViewHolder {

    private TextView mTitleView;
    private TextView mNumberView;
    private ConstraintLayout mCL;

    public viewHolderTitle(View itemView) {
      super(itemView);
      mTitleView = (TextView) itemView.findViewById(R.id.parent_title);
      mNumberView = (TextView) itemView.findViewById(R.id.parent_number);
      mCL = (ConstraintLayout) itemView.findViewById(R.id.headCL);
    }

    public void bind(NavigationItem slideParent, int pos) {
      mTitleView.setText(slideParent.getTitle());
      mNumberView.setText(String.valueOf(slideParent.getPagenum()));
      int color;
      if(slideParent.getPagenum()<=4) {
        color = R.color.light_mustard;
      }
      else if(slideParent.getPagenum()<=7) {
        color = R.color.melon;
      }
      else {
        color = R.color.seafoam_blue_two;
      }
      mNumberView.setTextColor(mNumberView.getResources().getColor(color));

      mCL.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          if(slideParent.isOpend()) {
            int len = totalTitles[slideParent.getPagenum()].length;
            for(int i=0;i<len;i++) {
              mDataset.remove(pos+1);
//              notifyItemRemoved(pos+1);
            }
            slideParent.setOpend(false);
//            notifyItemRangeRemoved(pos+1,len);

          } else {
            int len = totalTitles[slideParent.getPagenum()].length;
            for(int i=0;i<len;i++) {
              NavigationItem item = new NavigationItem(totalTitles[slideParent.getPagenum()][len-i-1],NAVI_ITEM,
                  len-i,slideParent.getPagenum(),len-i);
              if(i==0) item.setBottom(true);
              if(i==len-1) item.setTop(true);

              mDataset.add(pos+1, item);
//              notifyItemInserted(pos+1);
            }
            slideParent.setOpend(true);
//            notifyItemRangeInserted(pos+1,len);

          }

          notifyDataSetChanged();
//          notifyItemRangeChanged(pos,mDataset.size()-pos-1); //CHECK
        }
      });
    }
  }


  public class viewHolderItem extends RecyclerView.ViewHolder {
    private TextView mTitleText;
    private ImageView mReadImage;
    private TextView mTopmargin;
    private TextView mBottommargin;
    private ConstraintLayout mCl;

    public viewHolderItem(View itemView) {
      super(itemView);
      mTitleText = (TextView) itemView.findViewById(R.id.nav_recycler_itemtitle);
      mReadImage = (ImageView) itemView.findViewById(R.id.nav_recycler_isread);
      mTopmargin = (TextView) itemView.findViewById(R.id.nav_recycler_item_top_margin);
      mBottommargin = (TextView) itemView.findViewById(R.id.nav_recycler_item_bottom_margin);
      mCl = (ConstraintLayout) itemView.findViewById(R.id.nav_recycler_cl);
    }

    public void bind(NavigationItem slideChild, int pos) {
      mTitleText.setText(slideChild.getTitle());
      if(!ReadDB.getInstance().getIsRead(slideChild.getPagenum(),slideChild.getItemnum(),mTitleText.getContext())) {
        mReadImage.setVisibility(View.VISIBLE);
        int pagenum = slideChild.getPagenum();
        if(pagenum<=4) mReadImage.setImageResource(R.mipmap.read_yellow);
        else if(pagenum<=7) mReadImage.setImageResource(R.mipmap.read_red);
        else mReadImage.setImageResource(R.mipmap.read_green);
      }
      else {
//      mReadImage.setVisibility(View.GONE);
        mReadImage.setImageResource(R.mipmap.read_black);
      }

      if(slideChild.isBottom()) {
        mBottommargin.setVisibility(View.VISIBLE);
      }
      else {
        mBottommargin.setVisibility(View.GONE);
      }

      if(slideChild.isTop()) {
        mTopmargin.setVisibility(View.VISIBLE);
      }
      else {
        mTopmargin.setVisibility(View.GONE);
      }

      mCl.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          ReadDB.getInstance().setLastRead(slideChild.getPagenum(),slideChild.getItemnum(),mCl.getContext());
          ReadDB.getInstance().setIsRead(slideChild.getPagenum(),slideChild.getItemnum(),mCl.getContext());

          Intent intent;
          Context context = mCl.getContext();
          intent = new Intent(context, ContentActivity.class);
          intent.putExtra("contentPage",slideChild.getPagenum());
//        intent.putExtra("contentItem",slideChild.getItemnum());
//        intent.setFlags(FLAG_ACTIVITY_SINGLE_TOP);

          ItemsDB.getInstance().setSlide_itemnum(slideChild.getItemnum());

          //add log
          Date dt = new Date();
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
          String realtime = sdf.format(dt).toString();
          String date = new SimpleDateFormat("yyyyMMdd",Locale.KOREA).format(dt).toString();
          Log.d("LOGTEST","Content"+slideChild.getPagenum()+" "+slideChild.getItemnum());
          /*
          FirebaseDatabase.getInstance().getReference().child("userlist")
              .child(OptionsDB.getInstance().getUsername(context))
              .child("log").child(date)
              .child(String.valueOf(dt.getTime()))
              .setValue(new UserLog(dt.getTime(),slideChild.getPagenum(),slideChild.getItemnum(),3));

           */
          OptionsDB.getInstance().logcheck = true;

//          ConvertClass.setAlalytics((Activity)context,mFirebaseAnalytics,slideChild.getPagenum(), slideChild.getItemnum());

          Bundle bundle = new Bundle();
          bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "1");
          bundle.putString(FirebaseAnalytics.Param.ITEM_ID, ConvertClass.getScreenName(slideChild.getPagenum(), slideChild.getItemnum()));
          mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


          ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
          List<RunningTaskInfo> runningTaskInfoList = activityManager.getRunningTasks(1);
          String activityname = runningTaskInfoList.get(0).topActivity.getClassName();
          Log.d("ACTIVITYNAME",activityname);

          if(activityname.equals("com.ewha.howcare.ContentActivity")) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            MyTTS.getInstance().makeTTSStop(context);
            ((Activity) context).finish();
            ((Activity) context).overridePendingTransition(0, 0);
          }

          mCl.getContext().startActivity(intent);
        }
      });
    }
  }
}