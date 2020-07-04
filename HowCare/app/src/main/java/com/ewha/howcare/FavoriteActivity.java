package com.ewha.howcare;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;

public class FavoriteActivity  extends AppCompatActivity {

  ArrayList<FavoriteItem> favoriteItems = new ArrayList<>();
  RecyclerView recyclerView;
  FirebaseAnalytics mFirebaseAnalytics;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_favorite);
    mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


    recyclerView = findViewById(R.id.recyclerView);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowCustomEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(false);

    FavoriteDB favoriteDB = FavoriteDB.getInstance();
    ItemsDB itemsDB = ItemsDB.getInstance();

    for(int i=1;i<=ItemsDB.TOTAL_PAGE;i++) {
      for(int j=1;j<=ItemsDB.PAGE_ITEMNUM[i];j++) {
        if(favoriteDB.getIsFavorite(i,j,this)) {
          favoriteItems.add(new FavoriteItem(itemsDB.getDbList().get(i).get(j).get(0).getItem(),
              "mini0"+i+"_"+j, i, j, true));
        }
      }
    }

    Log.d("CLICKTITLE",".."+favoriteItems.size());
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    FavoriteAdapter mAdapter = new FavoriteAdapter(favoriteItems, mFirebaseAnalytics);

    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(mAdapter);
  }
}