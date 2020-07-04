package com.ewha.howcare;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.speech.tts.TextToSpeech;
import java.util.Locale;

public class MyTTS {

  TextToSpeech myTTS=null;
  Context mContext=null;

  private static final MyTTS ourInstance = new MyTTS();
  public static MyTTS getInstance() {
    return ourInstance;
  }

  private MyTTS() {
  }

  public void setMyTTS(Context mContext) {
    if(this.mContext==mContext) return;

    this.mContext = mContext;

    myTTS = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
      @Override
      public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
          //사용할 언어를 설정
          int result = myTTS.setLanguage(Locale.KOREA);
          //언어 데이터가 없거나 혹은 언어가 지원하지 않으면...
//          if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
//            Toast.makeText(MainActivity.this, "이 언어는 지원하지 않습니다.", Toast.LENGTH_SHORT).show();
//          } else {
//            btnEnter.setEnabled(true);
//            //음성 톤
//            textToSpeech.setPitch(0.7f);
//            //읽는 속도
//            textToSpeech.setSpeechRate(1.2f);
//          }
        }
      }
    });
  }

  public boolean isContextSame(Context context) {
    return mContext==context;
  }

  public boolean isTTSSpeak() {
    if(myTTS == null) return false;

    return myTTS.isSpeaking();
  }

  public void makeTTSStop(Context mContext) {
    if(myTTS==null) return;
    if(this.mContext!=mContext) return;
    myTTS.stop();
  }

  public void makeTTSShutdown(Context mContext) {
    if(myTTS==null) return;
    if(this.mContext!=mContext) return;
    myTTS.shutdown();
  }



  public void setTTSRead(String text, int type) {
    if(myTTS==null) return;

    myTTS.speak(text,type,null);
  }

  public void setTTSSilent(int time, int type) {

    if(myTTS==null) return;

    if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
      myTTS.playSilentUtterance(time,type,null);
    }
    else {
      myTTS.playSilence(time,type,null);
    }
  }
}
