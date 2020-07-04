package com.ewha.howcare;

import static android.content.Context.MODE_PRIVATE;
import static com.ewha.howcare.ItemsDB.PAGE_ITEMNUM;
import static com.ewha.howcare.ItemsDB.TOTAL_PAGE;

import android.content.Context;
import android.content.SharedPreferences;

public class ReadDB {
  SharedPreferences sp;
  SharedPreferences.Editor editor;
  Context mContext = null;

  //TODO 탭 별 읽던 페이지 배열로 만들어서 저장하기

  private boolean isRead[][] = new boolean[9][5];

  private static final ReadDB ourInstance = new ReadDB();

  public static ReadDB getInstance() {
    return ourInstance;
  }

  private ReadDB() {

  }
  private void setShared() {
    this.sp =  mContext.getSharedPreferences("pref",MODE_PRIVATE);
    editor = sp.edit();
  }

  public void setSharedPreference() {
      setShared();

      for(int i=1;i<=TOTAL_PAGE;i++) {
        for (int j = 1; j <= PAGE_ITEMNUM[i]; j++) {
          String prefKey = "read_" + i + "_" + j;
          int isclicked = sp.getInt(prefKey, -1);

          if (isclicked == 1)
            isRead[i][j] = true;
          else
            isRead[i][j] = false;
        }
      }
  }

  public void ResetReadList(Context context) {
    if(this.mContext==null) {
      this.mContext = context;
      setShared();
    }
    for(int i=1;i<=TOTAL_PAGE;i++) {
      for (int j = 1; j <= PAGE_ITEMNUM[i]; j++) {
        String prefKey = "read_" + i + "_" + j;
        editor.putInt(prefKey,0);
        isRead[i][j]=false;
      }
    }
    editor.commit();
  }

  public boolean getIsRead(int pagenumber,int itemnumber, Context context) {
    if(this.mContext==null) {
      this.mContext = context;
      setSharedPreference();
    }
    return isRead[pagenumber][itemnumber];
  }

  public void setIsRead(int pagenumber, int itemnumber, Context context) {

    if(this.mContext==null) {
      this.mContext = context;
      setSharedPreference();
    }

    String prefKey = "read_" + pagenumber + "_" + itemnumber;
    editor.putInt(prefKey, 1);
    editor.commit();

    isRead[pagenumber][itemnumber]=true;
  }

  public int getLastReadPagenumber(Context context) {
    if(this.mContext==null) {
      this.mContext = context;
      setSharedPreference();
    }
    String prefKey = "lastread_pagenumber";
    return sp.getInt(prefKey, -1);
  }

  public int getLastReadItemnumber(Context context) {
    if(this.mContext==null) {
      this.mContext = context;
      setSharedPreference();
    }
    String prefKey = "lastread_itemnumber";
    return sp.getInt(prefKey, -1);
  }

  public void setLastRead(int pagenumber, int itemnumber,Context context) {
    if(this.mContext==null) {
      this.mContext = context;
      setShared();
    }
    String prefKey = "lastread_pagenumber";
    editor.putInt(prefKey,pagenumber);
    String prefKeyitem = "lastread_itemnumber";
    editor.putInt(prefKeyitem,itemnumber);
    editor.commit();
  }
}
