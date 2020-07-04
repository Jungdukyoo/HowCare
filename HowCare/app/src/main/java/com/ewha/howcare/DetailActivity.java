package com.ewha.howcare;

import static com.ewha.howcare.DetailAdapter.TYPE_HEAD;
import static com.ewha.howcare.DetailAdapter.TYPE_PRECONTENT;
import static com.ewha.howcare.DetailAdapter.TYPE_PRESUBTEXT;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

  private RecyclerView recyclerView;
  private RecyclerView.Adapter mAdapter;
  private RecyclerView.LayoutManager layoutManager;
  private ArrayList<ContentItem> items = new ArrayList();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


    items.add(new ContentItem("3. TEST",TYPE_HEAD));
    items.add(new ContentItem("blah blah blah blah blah blah blah blah ",TYPE_PRECONTENT));
    items.add(new ContentItem("blah blah blah blah blah blah blah blah ",TYPE_PRESUBTEXT));
    items.add(new ContentItem("blah blah blah blah blah blah blah blah ",TYPE_PRESUBTEXT));
    items.add(new ContentItem("blah blah blah blah blah blah blah blah ",TYPE_PRECONTENT));
    items.add(new ContentItem("blah blah blah blah blah blah blah blah ",TYPE_PRESUBTEXT));
    items.add(new ContentItem("blah blah blah blah blah blah blah blah ",TYPE_PRESUBTEXT));
    items.add(new ContentItem("blah blah blah blah blah blah blah blah ",TYPE_PRESUBTEXT));
    items.add(new ContentItem("blah blah blah blah blah blah blah blah ",TYPE_PRESUBTEXT));
//    items.add(new ContentItem("before eat",TYPE_CONTENTHEAD));
//    items.add(new ContentItem("",TYPE_PRESUBTEXT));


    layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
   // mAdapter = new DetailAdapter(items);
    recyclerView.setAdapter(mAdapter);


//    TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
//    tabLayout.addTab(tabLayout.newTab().setText("첫번째"));
//    tabLayout.addTab(tabLayout.newTab().setText("두번째"));
//    tabLayout.addTab(tabLayout.newTab().setText("세번째"));
//    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

  }

}