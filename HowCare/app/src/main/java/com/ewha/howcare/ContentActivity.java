package com.ewha.howcare;

import static com.ewha.howcare.NavigationAdapter.NAVI_CATEGORY;
import static com.ewha.howcare.NavigationAdapter.NAVI_TITLE;

import android.content.Intent;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;
import com.google.android.material.tabs.TabLayout.Tab;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class ContentActivity extends AppCompatActivity {

    Intent intent;
    final static int CATEGORY_BEFORE_CARE = 1;
    final static int CATEGORY_IN_CARE = 2;
    final static int CATEGORY_STRANGE = 3;

    int category;

    int tab1rc,tab2rc,tab3rc,tab4rc;
    int tab1rc_un,tab2rc_un,tab3rc_un,tab4rc_un;

    ImageView toolbarTitle;


    SlideParentAdapter mAdapter1;
    RecyclerView recyclerView1;
    SlideParentAdapter mAdapter2;
    RecyclerView recyclerView2;
    SlideParentAdapter mAdapter3;
    RecyclerView recyclerView3;

    ViewPager viewPager;
    DetailPageAdapter pageAdapter;

    private FirebaseAnalytics mFirebaseAnalytics;

    int pagetype=1;
    int itemnum=1;



  @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_content);

      intent=new Intent(this.getIntent());
      pagetype = intent.getIntExtra("contentPage",1);
  //       itemnum = intent.getIntExtra("contentItem",1);
      itemnum = ItemsDB.getInstance().getCurrentPage(pagetype);

      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      toolbarTitle = findViewById(R.id.toolbar_title);

      setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayShowCustomEnabled(true);
      getSupportActionBar().setDisplayShowTitleEnabled(false);

      mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
//
//      mFirebaseAnalytics
//        .setCurrentScreen(ContentActivity.this, ConvertClass.getScreenName(pagetype, itemnum), null);


      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
      ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
          this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
      drawer.addDrawerListener(toggle);
      toggle.syncState();




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
//          MyTTS.getInstance().makeTTSStop(getApplicationContext());

          Intent intent = new Intent(ContentActivity.this,FavoriteActivity.class);
          startActivity(intent);
          finish();
        }
      });

      ConstraintLayout referenceCL = findViewById(R.id.slide_reference_cl);
      referenceCL.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
//          MyTTS.getInstance().makeTTSStop(getApplicationContext());

          Intent intent = new Intent(ContentActivity.this,ReferenceActivity.class);
          startActivity(intent);
          finish();
        }
      });

      ConstraintLayout checklistCL = findViewById(R.id.slide_checklist_cl);
      checklistCL.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent intent = new Intent(ContentActivity.this,ChecklistActivity.class);
          startActivity(intent);
        }
      });

      TabLayout tabLayout = findViewById(R.id.tablayout);

      ImageView tb1 = (ImageView) LayoutInflater.from(this).inflate(R.layout.custom_tab2,null);
      ImageView tb2 = (ImageView) LayoutInflater.from(this).inflate(R.layout.custom_tab2,null);
      ImageView tb3 = (ImageView) LayoutInflater.from(this).inflate(R.layout.custom_tab2,null);
      ImageView tb4 = (ImageView) LayoutInflater.from(this).inflate(R.layout.custom_tab2,null);

      tabLayout.getTabAt(0).setCustomView(tb1);
      tabLayout.getTabAt(1).setCustomView(tb2);
      tabLayout.getTabAt(2).setCustomView(tb3);
      tabLayout.getTabAt(3).setCustomView(tb4);



    if(pagetype>=1 && pagetype<=4) {
        category = CATEGORY_BEFORE_CARE;
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this,R.color.apricot));
      }
      else if(pagetype<=7) {
        category = CATEGORY_IN_CARE;
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this,R.color.melon));
      }
      else {
        category = CATEGORY_STRANGE;
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this,R.color.apricot));
      }

      switch (category) {
        case CATEGORY_BEFORE_CARE:
          tab1rc = R.mipmap.tab_shiksa;
          tab2rc = R.mipmap.tab_shiksadogu;
          tab3rc = R.mipmap.tab_bareun;
          tab4rc = R.mipmap.tab_yeonha;

          tab1rc_un = R.mipmap.tab_shiksa_un;
          tab2rc_un = R.mipmap.tab_shiksadogu_un;
          tab3rc_un = R.mipmap.tab_bareun_un;
          tab4rc_un = R.mipmap.tab_yeonha_un;
//          tabFour.setVisibility(View.VISIBLE);
          tabLayout.setVisibility(View.VISIBLE);
//          toolbarTitle.setText("식사 전 Care");
          toolbarTitle.setImageResource(R.mipmap.care_before);
          toolbar.setBackgroundColor(getResources().getColor(R.color.apricot));
          break;
        case CATEGORY_IN_CARE:

          tab1rc = R.mipmap.tab_bojo;
          tab2rc = R.mipmap.tab_gi;
          tab3rc = R.mipmap.tab_gugang;

          tab1rc_un = R.mipmap.tab_bojo_un;
          tab2rc_un = R.mipmap.tab_gi_un;
          tab3rc_un = R.mipmap.tab_gugang_un;

          tabLayout.removeTabAt(3);
          tabLayout.setVisibility(View.VISIBLE);
          toolbarTitle.setImageResource(R.mipmap.care_after);
          toolbar.setBackgroundColor(getResources().getColor(R.color.melon));
          break;
        case CATEGORY_STRANGE:
          tabLayout.setVisibility(View.GONE);
          toolbarTitle.setImageResource(R.mipmap.care_strange);
          toolbar.setBackgroundColor(getResources().getColor(R.color.seafoam_blue));
          break;
      }

      tb1.setImageResource(tab1rc);
      tb2.setImageResource(tab2rc_un);
      tb3.setImageResource(tab3rc_un);
      tb4.setImageResource(tab4rc_un);

      viewPager = findViewById(R.id.viewPager);

//      DetailPageAdapter pageAdapter = new DetailPageAdapter(getSupportFragmentManager(),
//          tabLayout.getTabCount(),category);
      pageAdapter = new DetailPageAdapter(getSupportFragmentManager(),
          tabLayout.getTabCount(),pagetype,itemnum);
      viewPager.setAdapter(pageAdapter);
      viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

      viewPager.setOffscreenPageLimit(0);

      tabLayout.addOnTabSelectedListener(new OnTabSelectedListener() {
        @Override
        public void onTabSelected(Tab tab) {
          //int cposition = tabLayout.getSelectedTabPosition();
          int cposition = tab.getPosition();
          int newpagetype = cposition;
          if(category==CATEGORY_BEFORE_CARE) newpagetype+=1;
          else if(category==CATEGORY_IN_CARE) newpagetype+=5;
          else newpagetype +=8;

          int currentitem = ItemsDB.getInstance().getCurrentPage(newpagetype);
          ReadDB.getInstance().setLastRead(newpagetype,currentitem,getApplicationContext());

          if(OptionsDB.getInstance().logcheck) {
            OptionsDB.getInstance().logcheck=false;
          }
          else {
            //add log
            Date dt = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
            String realtime = sdf.format(dt).toString();
            String date = new SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(dt).toString();
            Log.d("LOGTEST", "Content" + newpagetype + " " + currentitem);
            /*
            FirebaseDatabase.getInstance().getReference().child("userlist")
                .child(OptionsDB.getInstance().getUsername(ContentActivity.this))
                .child("log").child(date)
                .child(String.valueOf(dt.getTime()))
                .setValue(new UserLog(dt.getTime(), newpagetype, currentitem));

             */
            mFirebaseAnalytics.setCurrentScreen(ContentActivity.this, ConvertClass.getScreenName(newpagetype, currentitem), null /* class override */);
            Log.d("FA::acontentactivity",ConvertClass.getScreenName(newpagetype, currentitem)+" pagetype : "+pagetype+" current : "+currentitem);

            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "1");
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, ConvertClass.getScreenName(newpagetype, currentitem));
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
          }

//          MyTTS.getInstance().makeTTSStop(getApplicationContext());
          viewPager.setCurrentItem(cposition);

          switch (cposition) {
            case 0:
              tb1.setImageResource(tab1rc);
              break;
            case 1:
              tb2.setImageResource(tab2rc);
              break;
            case 2:
              tb3.setImageResource(tab3rc);
              break;
            case 3:
              tb4.setImageResource(tab4rc);
              break;
            default :
              break;
          }
        }

        @Override
        public void onTabUnselected(Tab tab) {
          int cposition = tab.getPosition();

          switch (cposition) {
            case 0:
              tb1.setImageResource(tab1rc_un);
              break;
            case 1:
              tb2.setImageResource(tab2rc_un);
              break;
            case 2:
              tb3.setImageResource(tab3rc_un);
              break;
            case 3:
              tb4.setImageResource(tab4rc_un);
              break;
            default :
              break;
          }
        }

        @Override
        public void onTabReselected(Tab tab) {

        }
      });

      switch (category) {
        case CATEGORY_BEFORE_CARE :
          viewPager.setCurrentItem(pagetype-1);
          break;
        case CATEGORY_IN_CARE :
          viewPager.setCurrentItem(pagetype-5);
          break;
        case CATEGORY_STRANGE :
          viewPager.setCurrentItem(pagetype-8);
          break;
          default:
            break;
      }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main2, menu);
      menu.removeItem(R.id.action_heart);
      return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      int id = item.getItemId();

      //noinspection SimplifiableIfStatement
      if (id == R.id.action_fontsize) {
        OptionsDB.getInstance().changeFontSize(getBaseContext());
        pageAdapter.notifyDataSetChanged();
        return true;
      }

      if(id == R.id.action_search) {
//        MyTTS.getInstance().makeTTSStop(getApplicationContext());
        Intent intent = new Intent(ContentActivity.this,SearchActivity.class);
        startActivity(intent);
        finish();
        return true;
      }
      return super.onOptionsItemSelected(item);
    }

  @Override
  protected void onPause() {
    Date dt = new Date();
    String date = new SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(dt).toString();

    Log.d("backbutton","finish");

    /*
    FirebaseDatabase.getInstance().getReference().child("userlist")
        .child(OptionsDB.getInstance().getUsername(ContentActivity.this))
        .child("log").child(date)
        .child(String.valueOf(dt.getTime()))
        .setValue(new UserLog(dt.getTime(), 1));

     */
    super.onPause();
  }

  @Override
  protected void onResume() {
    mFirebaseAnalytics
        .setCurrentScreen(ContentActivity.this, ConvertClass.getScreenName(pagetype, itemnum), null);

    super.onResume();
    Log.d("ONRESUME",pagetype+" "+itemnum);

  }


  @Override
  protected void onStart() {
    super.onStart();
    Date dt = new Date();
    String date = new SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(dt).toString();

    Log.d("backbutton","finish");

    /*
    FirebaseDatabase.getInstance().getReference().child("userlist")
        .child(OptionsDB.getInstance().getUsername(ContentActivity.this))
        .child("log").child(date)
        .child(String.valueOf(dt.getTime()))
        .setValue(new UserLog(dt.getTime(),2));
*/

//    mFirebaseAnalytics
//        .setCurrentScreen(ContentActivity.this, ConvertClass.getScreenName(pagetype, itemnum), null);

//    mFirebaseAnalytics.setCurrentScreen(ContentActivity.this, ConvertClass.getScreenName(newpagetype, currentitem), null /* class override */);
//    Log.d("FA::a",ConvertClass.getScreenName(newpagetype, currentitem)+" pagetype : "+pagetype+" current : "+currentitem);

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
  public void onDestroy() {
    RecycleUtils.recursiveRecycle(getWindow().getDecorView());
    System.gc();
    super.onDestroy();
  }
}
