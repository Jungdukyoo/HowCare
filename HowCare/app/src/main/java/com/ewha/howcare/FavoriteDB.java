package com.ewha.howcare;

import static android.content.Context.MODE_PRIVATE;
import static com.ewha.howcare.ItemsDB.PAGE_ITEMNUM;
import static com.ewha.howcare.ItemsDB.TOTAL_PAGE;

import android.content.Context;
import android.content.SharedPreferences;

public class FavoriteDB {

  SharedPreferences sp;
  SharedPreferences.Editor editor;
  Context mContext = null;
  private boolean isFavorite[][] = new boolean[9][5];


  private static final FavoriteDB ourInstance = new FavoriteDB();

  public static FavoriteDB getInstance() {
    return ourInstance;
  }

  private FavoriteDB() {
  }

  public void setSharedPreference() {
    this.sp =  mContext.getSharedPreferences("pref",MODE_PRIVATE);
    editor = sp.edit();

    for(int i=1;i<=TOTAL_PAGE;i++) {
      for (int j = 1; j <= PAGE_ITEMNUM[i]; j++) {
        String prefKey = "favorite_" + i + "_" + j;
        int isclicked = sp.getInt(prefKey, -1);

        if (isclicked == 1)
          isFavorite[i][j] = true;
        else
          isFavorite[i][j] = false;
      }
    }
  }
  public boolean getIsFavorite(int pagenumber,int itemnumber, Context context) {
    if(this.mContext==null) {
      this.mContext = context;
      setSharedPreference();
    }
    return isFavorite[pagenumber][itemnumber];
  }

  public void setFavorite(int pagenumber, int itemnumber,boolean favoreite, Context context) {
    if(this.mContext==null) {
      this.mContext = context;
      setSharedPreference();
    }

    String prefKey = "favorite_" + pagenumber + "_" + itemnumber;
    editor.putInt(prefKey, favoreite?1:0);
    editor.commit();

    isFavorite[pagenumber][itemnumber]=favoreite;
  }
}
