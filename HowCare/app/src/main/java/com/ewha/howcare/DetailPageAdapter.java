package com.ewha.howcare;

import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import android.os.Bundle;

public class DetailPageAdapter extends FragmentPagerAdapter {
  private int numOfTabs;
  private int pagetype;
  private int itemnum;

  public DetailPageAdapter(FragmentManager fm, int numOfTabs,int pagetype,int itemnum) {
    super(fm);
    this.numOfTabs = numOfTabs;
    this.pagetype = pagetype;
    this.itemnum = itemnum;
  }


  @Override
  public Fragment getItem(int position) {
    Fragment fragment = new DetailFragment(); // Fragment 생성
    Bundle bundle = new Bundle(3); // 파라미터는 전달할 데이터 개수
    Log.d("POSTION",String.valueOf(position));
    Log.d("PAGEADAPTER PAGETYPE",String.valueOf(pagetype));

    bundle.putInt("postion", position); // key , value

    if(pagetype<=4) pagetype = position+1;
    else if(pagetype<=7) pagetype = position+5;
    else pagetype = 8;

    bundle.putInt("pagetype", pagetype); // key , value
    bundle.putInt("itemnum", ItemsDB.getInstance().getSlide_itemnum()); // key , value

    fragment.setArguments(bundle);

    return fragment;
  }

  @Override
  public int getCount() {
    return numOfTabs;
  }

  @Override
  public int getItemPosition(Object object) {
      return POSITION_NONE;
  }
}
