package com.ewha.howcare;

import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;

public class SlideChildViewHolder extends ChildViewHolder{
  private TextView mTitleText;
  private ImageView mReadImage;
  private TextView mTopmargin;
  private TextView mBottommargin;
  private ConstraintLayout mCl;


  public SlideChildViewHolder(View itemView) {
    super(itemView);
    mTitleText = (TextView) itemView.findViewById(R.id.nav_recycler_itemtitle);
    mReadImage = (ImageView) itemView.findViewById(R.id.nav_recycler_isread);
    mTopmargin = (TextView) itemView.findViewById(R.id.nav_recycler_item_top_margin);
    mBottommargin = (TextView) itemView.findViewById(R.id.nav_recycler_item_bottom_margin);
    mCl = (ConstraintLayout) itemView.findViewById(R.id.nav_recycler_cl);
  }

  public void bind(SlideChild slideChild) {
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