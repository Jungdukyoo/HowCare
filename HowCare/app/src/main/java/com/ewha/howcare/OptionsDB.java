package com.ewha.howcare;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import java.util.ArrayList;
import java.util.UUID;

public class OptionsDB  {

  SharedPreferences sp;
  SharedPreferences.Editor editor;
  Context mContext = null;
  public static final int FONT_SMALL = 1;
  public static final int FONT_MID = 2;
  public static final int FONT_LARGE = 3;

  private int fontsize = 2;
  private String username = "";
  private String uuid = "";
  private int[] checkliststate = new int[13];
  private String[] checklistetc = new String[13];
  private String checklistDate = "";

  public boolean logcheck = false;

  private static final OptionsDB ourInstance = new OptionsDB();

  public static OptionsDB getInstance() {
    return ourInstance;
  }

  private OptionsDB() {
  }

  public void setSharedPreference() {
    this.sp =  mContext.getSharedPreferences("pref",MODE_PRIVATE);
    editor = sp.edit();
  }

  public void changeFontSize(Context context) {
    if(this.mContext == null) {
      this.mContext = context;
      setSharedPreference();
    }
    String prefKey = "option_fontsize";
    int fsize = getFontSize(context);
    switch (fsize) {
      case FONT_SMALL:
        fsize = FONT_MID;
        break;
      case FONT_MID:
        fsize = FONT_LARGE;
        break;
      case FONT_LARGE:
        fsize = FONT_SMALL;
    }
    fontsize = fsize;
    editor.putInt(prefKey, fsize);
    editor.commit();
  }

  public int getFontSize(Context context) {
    if(this.mContext==null) {
      this.mContext = context;
      setSharedPreference();
      String prefKey = "option_fontsize";
      fontsize = sp.getInt(prefKey, FONT_MID);
    }
    return fontsize;
  }

  public void setUserName(Context context, String username) {
    if(this.mContext == null) {
      this.mContext = context;
      setSharedPreference();
    }
    String prefKey = "option_username";
    editor.putString(prefKey, username);
    editor.commit();
    this.username = username;
  }

  public String getUsername(Context context) {
    if(this.mContext==null) {
      this.mContext = context;
      setSharedPreference();
      String prefKey = "option_username";
      username = sp.getString(prefKey, "");
    }
    return username;
  }

  public void setUUID(Context context, String uuid) {
    if(this.mContext == null) {
      this.mContext = context;
      setSharedPreference();
    }
    String prefKey = "option_uuid";
    editor.putString(prefKey, uuid);
    editor.commit();
    this.uuid = uuid;
  }

  public String getUUID(Context context) {
    Log.d("UUIDH",uuid);
    if(this.mContext==null) {
      this.mContext = context;
      setSharedPreference();
      String prefKey = "option_uuid";
      uuid = sp.getString(prefKey, "");
    }
    return uuid;
  }

  public void setCheckliststate(Context context, ArrayList<CheckListItem> checklist) {
    if(this.mContext == null) {
      this.mContext = context;
      setSharedPreference();
    }
    String prefKey = "option_checklist_state";
    int cnt=0;
    for(int i=0;i<checklist.size();i++) {
      if(checklist.get(i).getType() == 2) { //if item
        editor.putInt(prefKey+cnt, checklist.get(i).getState());
        editor.putString(prefKey+"etc"+cnt, checklist.get(i).getEtc());
        checkliststate[cnt]=checklist.get(i).getState();
        checklistetc[cnt]=checklist.get(i).getEtc();
        cnt++;
      }
    }

    editor.commit();
  }

  public int[] getCheckliststate(Context context) {
    if(this.mContext==null) {
      this.mContext = context;
      setSharedPreference();
      String prefKey = "option_checklist_state";

      for(int i=0;i<checkliststate.length;i++) {
        checkliststate[i] = sp.getInt(prefKey+i,0);
      }
    }
    return checkliststate;
  }

  public String[] getChecklistetc(Context context) {
    if(this.mContext==null) {
      this.mContext = context;
      setSharedPreference();
      String prefKey = "option_checklistetc";
      for(int i=0;i<checkliststate.length;i++) {
        checklistetc[i] = sp.getString(prefKey+"etc"+i,"");
      }
    }
    return checklistetc;
  }

  public void setChecklistDate(Context context, String date) {
    if(this.mContext == null) {
      this.mContext = context;
      setSharedPreference();
    }
    String prefKey = "option_checklistdate";
    editor.putString(prefKey, date);
    editor.commit();
    this.checklistDate = date;
  }

  public String getChecklistDate(Context context) {
    if(this.mContext==null) {
      this.mContext = context;
      setSharedPreference();
      String prefKey = "option_checklistdate";
      checklistDate = sp.getString(prefKey, "");
    }
    return checklistDate;
  }
}