package com.ewha.howcare;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

  SearchView mSearchView;
  RecyclerView recyclerView;
  LinearLayout noresultView;
  LinearLayout beforesearchView;
  SearchAdapter mAdapter;
  TextView noResultTextView;

  FirebaseAnalytics mFirebaseAnalytics;

  ArrayList<SearchResultItem> resultItemArrayList = new ArrayList<>();
  ArrayList<ArrayList<ArrayList<ContentItem>>> itemsDB;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_serarch);

    itemsDB = ItemsDB.getInstance().getDbList();

    recyclerView = findViewById(R.id.recyclerView);
    noresultView = findViewById(R.id.searchrecycler_noresult);
    beforesearchView = findViewById(R.id.searchrecycler_beforesearch);
    noResultTextView = findViewById(R.id.noresult_text);

    mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowCustomEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);

    toolbar.inflateMenu(R.menu.search_menu);
//    SearchManager searchManager = (SearchManager)getSystemService(SEARCH_SERVICE);
//    SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());

    mSearchView = findViewById(R.id.searchView);//(SearchView) toolbar.getMenu().findItem(R.id.action_search).getActionView();
//    mSearchView.setSearchableInfo(searchableInfo);

    mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String s) {
        Log.d("QUERYSUBMIT",s);
        mAdapter.setSearch_type(SearchAdapter.SEARCH_RESULT_MODE);
        mAdapter.setQuery(s);
        mAdapter.notifyDataSetChanged();
        return false;
      }
      @Override
      public boolean onQueryTextChange(String s) {
        Log.d("QUERYCHANGE",s);
        if(s.length()==0) {
          beforesearchView.setVisibility(View.VISIBLE);
          noresultView.setVisibility(View.GONE);
          resultItemArrayList.clear();
          mAdapter.setSearch_type(SearchAdapter.IN_SEARCH_MODE);
          mAdapter.setQuery(s);
          mAdapter.notifyDataSetChanged();
          return false;
        } else {
          beforesearchView.setVisibility(View.GONE);
        }

        resultItemArrayList.clear();
        for(int i=1;i<itemsDB.size();i++) {
          ArrayList<ArrayList<ContentItem>> tabItem = new ArrayList<ArrayList<ContentItem>>();
          tabItem = itemsDB.get(i);
          for(int j=1;j<tabItem.size();j++) {
            ArrayList<ContentItem> contentItem = tabItem.get(j);
            for(int k=0;k<contentItem.size();k++) {
              if(contentItem.get(k).getTitle().contains(s)) {
                resultItemArrayList.add(new SearchResultItem(contentItem, i,j,k));
                break;
              }
              if(contentItem.get(k).getItem().contains(s)) {
                resultItemArrayList.add(new SearchResultItem(contentItem, i,j,k));
                break;
              }
            }
          }
        }

        if(resultItemArrayList.size()==0) {
          String str = "‘"+s+"’ 에 대한 결과가 없습니다.\n새로운 검색을 시도하십시오.";
          noResultTextView.setText(str);
          noresultView.setVisibility(View.VISIBLE);
        } else {
          noresultView.setVisibility(View.GONE);
        }
        mAdapter.setSearch_type(SearchAdapter.IN_SEARCH_MODE);
        mAdapter.notifyDataSetChanged();
        return false;
      }
    });

//    mSearchView.setOnfocus { view, hasFocus ->
//      if (hasFocus) {
//        searchView.showKeyboard()
//      }
//      else {
//        searchView.hideKeyboard()
//      }
//    }
//    mSearchView.setActivated(true);
    mSearchView.setFocusable(true);
    mSearchView.setIconified(false);
    mSearchView.requestFocus();
//    mSearchView.requestFocusFromTouch();
    mSearchView.setBackgroundColor(Color.BLACK);

    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    mAdapter = new SearchAdapter(resultItemArrayList,"", this,mFirebaseAnalytics);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(mAdapter);
    beforesearchView.setVisibility(View.VISIBLE);

    mSearchView.setBackgroundColor(Color.parseColor("#ff7972"));
    int plate = getResources().getIdentifier("android:id/search_plate", null, null);
    View v = mSearchView.findViewById(plate);
    v.setBackgroundColor(Color.parseColor("#ff7972"));
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()){
      case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
        finish();
        return true;
      }
    }
    return super.onOptionsItemSelected(item);
  }
}