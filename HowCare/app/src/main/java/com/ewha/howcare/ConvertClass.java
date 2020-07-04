package com.ewha.howcare;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;

public class ConvertClass {

  public final static int CATEGORY_BEFORE_CARE = 1;
  public final static int CATEGORY_IN_CARE = 2;
  public final static int CATEGORY_STRANGE = 3;

  public static String getMainTitle(int n) {
    switch (n) {
      case 1:
        return "식사의 중요성";
      case 2:
        return "식사 도구";
      case 3:
        return "식사 시 바른자세";
      case 4:
        return"연하운동";
      case 5:
        return "보조의 실제";
      case 6:
        return "기능유지간호";
      case 7:
        return "구강간호";
      case 8:
        return "식사관련 이상행동";
      default:
        return "";
    }
  }

  public static int getCategory(int pagenum) {
    if(pagenum>=1 && pagenum<=4) {
      return CATEGORY_BEFORE_CARE;
    }
    else if(pagenum<=7) {
      return CATEGORY_IN_CARE;
    }
    else {
      return CATEGORY_STRANGE;
    }
  }

  public static String getScreenName(int pagenum, int itemnum) {
    String str = (char)('A'+pagenum-1)+"-"+itemnum;
    return str;
  }

  public static void setAlalytics(Activity activity, FirebaseAnalytics mFirebaseAnalytics,int pagenum, int itemnum) {
    mFirebaseAnalytics.setCurrentScreen(activity, ConvertClass.getScreenName(pagenum, itemnum), null /* class override */);
    Log.d("FA::aconvert",ConvertClass.getScreenName(pagenum, itemnum)+" pagetype : "+pagenum+" current : "+itemnum);
  }
}
