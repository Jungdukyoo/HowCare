package com.ewha.howcare;

import static com.ewha.howcare.NavigationAdapter.NAVI_CATEGORY;
import static com.ewha.howcare.NavigationAdapter.NAVI_TITLE;

import android.content.ClipData.Item;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kingfisher.easyviewindicator.RecyclerViewIndicator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  ImageView thumb1;
  ImageView thumb2;
  ImageView thumb3;
  ImageView thumb4;
  ImageView thumb5;
  ImageView thumb6;
  ImageView thumb7;
  ImageView thumb8;


  SlideParentAdapter mAdapter1;
  RecyclerView recyclerView1;
  SlideParentAdapter mAdapter2;
  RecyclerView recyclerView2;
  SlideParentAdapter mAdapter3;
  RecyclerView recyclerView3;

  FirebaseAnalytics mFirebaseAnalytics;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

    /* UserCode Part
    String musername = OptionsDB.getInstance().getUsername(Main2Activity.this);

    Log.d("USERNAME",musername);
    FirebaseMessaging.getInstance().subscribeToTopic(musername)
        .addOnCompleteListener(new OnCompleteListener<Void>() {
          @Override
          public void onComplete(@NonNull Task<Void> task) {
            String msg = "구독";
            if (!task.isSuccessful()) {
              msg = "구독 실패";
            }
            Log.d("TOKENID", msg);
          }
        });
     */


    setContentView(R.layout.activity_main2);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowCustomEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(false);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();


    Date dt = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd", Locale.KOREA);
    String datetext = sdf.format(dt).toString();

    if(datetext.equals("2019. 07. 10") || datetext.equals("2019. 07. 17") ||
        datetext.equals("2019. 07. 24") || datetext.equals("2019. 07. 03")) {
      ReadDB.getInstance().ResetReadList(Main2Activity.this);
    }

    ArrayList<NavigationItem> naviList = new ArrayList<>();


    naviList.add(new NavigationItem("식사 전 Care",NAVI_CATEGORY,1,1,1));
    for(int i=1;i<= ItemsDB.TOTAL_PAGE;i++) {

      if(i==5)
        naviList.add(new NavigationItem("식사 중.후 Care",NAVI_CATEGORY,2,i,1));

      if(i==8)
        naviList.add(new NavigationItem("식사 관련 이상행동 Care",NAVI_CATEGORY,3,i,1));

      String title="";
      switch (i) {
        case 1:
          title = "식사의 중요성";
          break;
        case 2:
          title = "식사도구";
          break;
        case 3:
          title = "식사 시 바른자세";
          break;
        case 4:
          title = "연하운동";
          break;
        case 5:
          title = "보조의 실제";
          break;
        case 6:
          title = "기능유지간호";
          break;
        case 7:
          title = "구강간호";
          break;
        case 8:
          title = "식사관련 이상행동";
          break;
        default:
          break;
      }

      NavigationItem item = new NavigationItem(title,NAVI_TITLE,i,i,1);
      naviList.add(item);

    }


    recyclerView1 = findViewById(R.id.navi_recyclerview1);
    NavigationAdapter mAdapter = new NavigationAdapter(naviList,mFirebaseAnalytics);
    recyclerView1.setAdapter(mAdapter);
    recyclerView1.setLayoutManager(new LinearLayoutManager(this));



    ConstraintLayout favoriteCL = findViewById(R.id.slide_favorite_cl);
    favoriteCL.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(Main2Activity.this,FavoriteActivity.class);
        startActivity(intent);
      }
    });

    ConstraintLayout referenceCL = findViewById(R.id.slide_reference_cl);
    referenceCL.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(Main2Activity.this,ReferenceActivity.class);
        startActivity(intent);
      }
    });

    ConstraintLayout checklistCL = findViewById(R.id.slide_checklist_cl);
    checklistCL.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(Main2Activity.this,ChecklistActivity.class);
        startActivity(intent);
      }
    });


    View.OnClickListener myOnlyhandler = new View.OnClickListener() {
      public void onClick(View v) {
        Intent intent;
        intent = new Intent(Main2Activity.this, ContentActivity.class);
        int imageNum = 0;
        switch(v.getId()) {
          case R.id.mainThumb1:
            imageNum = 1;
            break;
          case R.id.mainThumb2:
            imageNum = 2;
            break;
          case R.id.mainThumb3:
            imageNum = 3;
            break;
          case R.id.mainThumb4:
            imageNum = 4;
            break;
          case R.id.mainThumb5:
            imageNum = 5;
            break;
          case R.id.mainThumb6:
            imageNum = 6;
            break;
          case R.id.mainThumb7:
            imageNum = 7;
            break;
            case R.id.mainThumb8:
              imageNum = 8;
              break;
        }
        ReadDB.getInstance().setLastRead(imageNum,1,getApplicationContext());
        ItemsDB.getInstance().setCurrentPage(imageNum,1);

        intent.putExtra("contentPage",imageNum);

        if(imageNum == 1 || imageNum == 5 || imageNum ==8) {

          //add log
          Date dt = new Date();
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
          String realtime = sdf.format(dt).toString();
          String date = new SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(dt).toString();
          Log.d("LOGTEST", "Content" + imageNum + " " + 1);
          /* UserCode Part
          FirebaseDatabase.getInstance().getReference().child("userlist")
              .child(OptionsDB.getInstance().getUsername(Main2Activity.this))
              .child("log").child(date)
              .child(String.valueOf(dt.getTime()))
              .setValue(new UserLog(dt.getTime(), imageNum, 1));

           */
//
//          mFirebaseAnalytics
//              .setCurrentScreen(Main2Activity.this, ConvertClass.getScreenName(imageNum, 1), null);

          Bundle bundle = new Bundle();
          bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "1");
          bundle.putString(FirebaseAnalytics.Param.ITEM_ID, ConvertClass.getScreenName(imageNum, 1));
          mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

          Log.d("FA::amain2activity",
              ConvertClass.getScreenName(imageNum, 1) + " pagetype : " + imageNum + " current : "
                  + 1);
        }

        startActivity(intent);
      }
    };

    thumb1 = findViewById(R.id.mainThumb1);
    thumb2 = findViewById(R.id.mainThumb2);
    thumb3 = findViewById(R.id.mainThumb3);
    thumb4 = findViewById(R.id.mainThumb4);
    thumb5 = findViewById(R.id.mainThumb5);
    thumb6 = findViewById(R.id.mainThumb6);
    thumb7 = findViewById(R.id.mainThumb7);
    thumb8 = findViewById(R.id.mainThumb8);

    thumb1.setOnClickListener(myOnlyhandler);
    thumb2.setOnClickListener(myOnlyhandler);
    thumb3.setOnClickListener(myOnlyhandler);
    thumb4.setOnClickListener(myOnlyhandler);
    thumb5.setOnClickListener(myOnlyhandler);
    thumb6.setOnClickListener(myOnlyhandler);
    thumb7.setOnClickListener(myOnlyhandler);
    thumb8.setOnClickListener(myOnlyhandler);


    int lastread_pagenum = ReadDB.getInstance().getLastReadPagenumber(this);
    int lastread_itemnum = ReadDB.getInstance().getLastReadItemnumber(this);

    if(lastread_pagenum!=-1 && lastread_itemnum!=-1) {

      ReadBeforeDialog dialog = new ReadBeforeDialog(this, lastread_pagenum, lastread_itemnum,mFirebaseAnalytics);
      dialog.show();
    }
  }


  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main2, menu);
    menu.removeItem(R.id.action_fontsize);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_heart) {
      Intent intent = new Intent(Main2Activity.this,FavoriteActivity.class);
      startActivity(intent);
      return true;
    }

    if(id == R.id.action_search) {
      Intent intent = new Intent(Main2Activity.this,SearchActivity.class);
      startActivity(intent);
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      // Handle the camera action
    } else if (id == R.id.nav_gallery) {

    } else if (id == R.id.nav_slideshow) {

    } else if (id == R.id.nav_manage) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}
