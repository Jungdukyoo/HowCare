package com.ewha.howcare;

import static com.ewha.howcare.DetailAdapter.TYPE_CONTENTHEAD;
import static com.ewha.howcare.DetailAdapter.TYPE_CONTENTITEM;
import static com.ewha.howcare.DetailAdapter.TYPE_EMPTY;
import static com.ewha.howcare.DetailAdapter.TYPE_HEAD;
import static com.ewha.howcare.DetailAdapter.TYPE_IMAGE;
import static com.ewha.howcare.DetailAdapter.TYPE_PRECONTENT;
import static com.ewha.howcare.DetailAdapter.TYPE_READTITLE;

import android.content.ClipData.Item;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.RequiresPermission.Read;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DetailFragment extends Fragment {

  private RecyclerView recyclerView;
  private DetailAdapter mAdapter;
  private RecyclerView.LayoutManager layoutManager;
  private ArrayList<ArrayList<ContentItem>> items = new ArrayList<ArrayList<ContentItem>>();
  int tabposition;
  int currentTab = 1;
//  int category = 1;
  int pagetype = 0;
  ArrayList<ContentItem> clickeditem = new ArrayList<>();
  private FirebaseAnalytics mFirebaseAnalytics;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());

    tabposition = getArguments().getInt("postion");
    pagetype = getArguments().getInt("pagetype");
    currentTab = getArguments().getInt("itemnum");
    ItemsDB.getInstance().setCurrentPage(pagetype,currentTab);

    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_detail, container, false);
    recyclerView = ((RecyclerView) rootView.findViewById(R.id.recyclerView));

//    ItemDB itemdb = new ItemDB(category);
//    items = itemdb.getDbList().get(tabposition);

    items = ItemsDB.getInstance().getDbList().get(pagetype);

    Log.d("FRAGMENT","postion "+tabposition+" pagetype : "+pagetype);

    layoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(layoutManager);

    recyclerView.addOnItemTouchListener(new RecyclerViewOnItemClickListener(getActivity(),
        recyclerView, new RecyclerViewOnItemClickListener.OnItemClickListener() {
      @Override
      public void onItemClick(View v, int position) {
        if(clickeditem.get(position).getType() == TYPE_READTITLE) {
          ReadDB.getInstance().setIsRead(pagetype,currentTab,getContext());

          int idx = Integer.parseInt(clickeditem.get(position).getItem().substring(0,1));
          currentTab = idx;//TODO

          clickeditem = new ArrayList<>();
          for(int i=1;i<currentTab;i++) {
            clickeditem.add(new ContentItem(items.get(i).get(0).getItem(),TYPE_READTITLE));
          }
          clickeditem.addAll(items.get(currentTab));
          clickeditem.add(new ContentItem("",TYPE_EMPTY));
          for(int i=currentTab+1;i<=ItemsDB.PAGE_ITEMNUM[pagetype];i++) {
            clickeditem.add(new ContentItem(items.get(i).get(0).getItem(),TYPE_READTITLE));
          }

          ReadDB.getInstance().setIsRead(pagetype,currentTab,getActivity());
          ReadDB.getInstance().setLastRead(pagetype,currentTab,getActivity());


          //add log
          Date dt = new Date();
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
          String realtime = sdf.format(dt).toString();
          String date = new SimpleDateFormat("yyyyMMdd",Locale.KOREA).format(dt).toString();
          Log.d("LOGTEST","Content"+pagetype+" "+currentTab+" fragment");
          /*
          FirebaseDatabase.getInstance().getReference().child("userlist")
              .child(OptionsDB.getInstance().getUsername(getContext()))
              .child("log").child(date)
              .child(String.valueOf(dt.getTime()))
              .setValue(new UserLog(dt.getTime(),pagetype,currentTab));


           */

          mFirebaseAnalytics.setCurrentScreen(getActivity(), ConvertClass.getScreenName(pagetype, currentTab), null /* class override */);
          Log.d("FA::f",ConvertClass.getScreenName(pagetype, currentTab)+" pagetype : "+pagetype+" current : "+currentTab);

          Bundle bundle = new Bundle();
          bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "1");
          bundle.putString(FirebaseAnalytics.Param.ITEM_ID, ConvertClass.getScreenName(pagetype, currentTab));
          mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

          ItemsDB.getInstance().setCurrentPage(pagetype,currentTab);

          if(mAdapter.textToSpeech != null) {
            if (mAdapter.textToSpeech.isSpeaking()) {
              mAdapter.textToSpeech.stop();
            }
          }
          MyTTS.getInstance().makeTTSStop(getContext());
          mAdapter.itemChanged(clickeditem,pagetype,tabposition,currentTab);
//          mAdapter = new DetailAdapter(items.get(currentTab), category);
//          recyclerView.setAdapter(mAdapter);
          recyclerView.scrollToPosition(0);
          Log.d("CLICK","FRAGMENT");
        }
      }

      @Override
      public void onItemLongClick(View v, int position) {

      }
    }
        ));

    Log.d("CURRENTAB","currnettab : "+String.valueOf(currentTab)+" / "+String.valueOf(pagetype)+" item size : "+String.valueOf(items.size()));
    clickeditem = new ArrayList<>();
    for(int i=1;i<currentTab;i++) {
      clickeditem.add(new ContentItem(items.get(i).get(0).getItem(),TYPE_READTITLE));
    }
    clickeditem.addAll(items.get(currentTab));
    clickeditem.add(new ContentItem("",TYPE_EMPTY));

    for(int i=currentTab+1;i<=ItemsDB.PAGE_ITEMNUM[pagetype];i++) {
      clickeditem.add(new ContentItem(items.get(i).get(0).getItem(),TYPE_READTITLE));
    }
    ReadDB.getInstance().setLastRead(pagetype,currentTab,getActivity());

    mAdapter = new DetailAdapter(clickeditem,getContext(), pagetype,tabposition,currentTab);
    recyclerView.setAdapter(mAdapter);

    Log.d("CLICK",String.valueOf(items.size()));


    return rootView;
    //return inflater.inflate(R.layout.fragment_chat, container, false);.

  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    // Inflate the menu; this adds items to the action bar if it is present.
    inflater.inflate(R.menu.main2, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_heart) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }


  @Override
  public void onStart() {
    Log.d(this.getClass().getSimpleName(), "onStart()");
    super.onStart();
  }

  @Override
  public void onResume() {
    Log.d(this.getClass().getSimpleName(), "onResume()");
    super.onResume();
//    mAdapter.textToSpeech.stop();
    MyTTS.getInstance().makeTTSStop(getContext());
  }

  @Override
  public void onPause() {
    Log.d(this.getClass().getSimpleName(), "onPause()");
    super.onPause();
  }


  @Override
  public void onStop() {
    Log.d(this.getClass().getSimpleName(), "onStop()");
    super.onStop();
    MyTTS.getInstance().makeTTSStop(getContext());
//    mAdapter.textToSpeech.stop();
  }

  @Override
  public void onDestroyView() {
    Log.d(this.getClass().getSimpleName(), "onDestroyView()");
    super.onDestroyView();
  }

  @Override
  public void onDestroy() {
    Log.d(this.getClass().getSimpleName(), "onDestroy()");
    MyTTS.getInstance().makeTTSShutdown(getContext());

    super.onDestroy();
  }

  @Override
  public void onDetach() {
    Log.d(this.getClass().getSimpleName(), "onDetach()");
    MyTTS.getInstance().makeTTSShutdown(getContext());

    super.onDetach();

//    mAdapter.textToSpeech.shutdown();
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    Log.d(this.getClass().getSimpleName(), "onSaveInstanceState()");
    super.onSaveInstanceState(outState);
  }
}
